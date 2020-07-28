package game.troubleTankSample;

import game.troubleTankSample.Awards.Award;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Area;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;

public class MapHandler {

    private static int numberOf_Vertical_Blocks;
    private static int numberOf_Horizontal_Blocks;
    private int frameXsize;
    private int frameYsize;
    private int standardX;
    private int standardY;
    private Block[][] mapsBlocks;
    private final static String STRONG_WALL = "strongWall";
    private final static String DESTROYABLE_WALL = "destroyableWall";
    private final static String PASSABLE_FIELD = "passableField";
    private final static String AWARD_BOX_SHIELD = "shieldAward";
    private final static String AWARD_BOX_LASER = "laserAward";
    private final static String AWARD_BOX_HEAL_10per = "healAward";
    private final static String AWARD_BOX_POWER_2X = "powerAward2X";
    private final static String AWARD_BOX_POWER_3X = "powerAward3X";
    private BufferedImage strongWall_Icon;
    private BufferedImage destroyableWall_Icon;
    private BufferedImage passableField_Icon;
    private BufferedImage healerAward_Icon;
    private BufferedImage laserAward_Icon;
    private BufferedImage shieldAward_Icon;
    private BufferedImage powerAward2X_Icon;
    private BufferedImage powerAward3X_Icon;
    private BufferedImage tankBlue_Icon;
    private BufferedImage tankYellow_Icon;
    private BufferedImage tankSand_Icon;
    private BufferedImage tankDark_Icon;
    private BufferedImage tankGreen_Icon;
    private String mapAddress;

    public MapHandler() {
        mapAddress = "./maps/r.txt";
        try {
            strongWall_Icon = ImageIO.read(new File("./resources/maps resources/strongWall.png"));
            destroyableWall_Icon = ImageIO.read(new File("./resources/maps resources/destroyableWall.png"));
            passableField_Icon = ImageIO.read(new File("./resources/maps resources/grassField.png"));
            shieldAward_Icon = ImageIO.read(new File("./resources/awards resources/shield.png"));
            laserAward_Icon = ImageIO.read(new File("./resources/awards resources/laser.png"));
            powerAward3X_Icon = ImageIO.read(new File("./resources/awards resources/3Xpower.png"));
            powerAward2X_Icon = ImageIO.read(new File("./resources/awards resources/2Xpower.png"));
            healerAward_Icon = ImageIO.read(new File("./resources/awards resources/healer.png"));
            tankBlue_Icon = ImageIO.read(new File("./resources/tank_blue.png"));
            tankYellow_Icon = ImageIO.read(new File("./resources/tank_yellow.png"));
            tankDark_Icon = ImageIO.read(new File("./resources/tank_dark.png"));
            tankGreen_Icon = ImageIO.read(new File("./resources/tank_green.png"));
            tankSand_Icon = ImageIO.read(new File("./resources/tank_sand.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {

            BufferedReader bufferedReader = new BufferedReader(new FileReader(mapAddress));
            ArrayList<String> textMapLines = new ArrayList<String>();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                textMapLines.add(line);
            }
            frameXsize = textMapLines.get(0).length();
            frameYsize = textMapLines.size();
            mapsBlocks = new Block[frameYsize][frameXsize];

            for (int i = 0; i < frameYsize; i++) {
                for (int j = 0; j < frameXsize; j++) {
                    mapsBlocks[i][j] = new Block((textMapLines.get(i).charAt(j) == '0') ? PASSABLE_FIELD : (textMapLines.get(i).charAt(j) == '1') ? STRONG_WALL : (textMapLines.get(i).charAt(j) == '2') ? DESTROYABLE_WALL : null);
                }
            }


        } catch (FileNotFoundException e) {
            System.err.println("map does not exist!");
        } catch (IOException e) {
        }

    }

    public void setBlocksLocations(int frames_width, int frames_height) {
        standardX = frames_width / (4 * frameXsize - 3);
        standardY = frames_height / (4 * frameYsize - 3);

        int tempX = 0, tempY = 0;
        for (int i = 0; i < frameYsize; i++) {
            for (int j = 0; j < frameXsize; j++) {
                mapsBlocks[i][j].setLocation(tempX, tempY, (j % 2 == 0) ? standardX : 7 * standardX, (i % 2 == 0) ? standardY : 7 * standardY);
                if (j % 2 == 0) tempX += standardX;
                else tempX += (7 * standardX);
            }
            if (i % 2 == 0) tempY += standardY;
            else tempY += (7 * standardY);

            tempX = 0;
        }

        for (int i = 0; i < frameYsize; i++) {
            for (int j = 0; j < frameXsize; j++) {
                // System.out.print(mapsBlocks[i][j].getX1() + "__" + mapsBlocks[i][j].getY1() + "->" + mapsBlocks[i][j].getX3() + "__" + mapsBlocks[i][j].getY3() + "  ");
            }
            System.out.println("\n");
        }
    }

    public void renderer(Graphics2D graphics2D) {

        for (int i = 0; i < 10; i++)
            for (int j = 0; j < 10; j++)
                graphics2D.drawImage(passableField_Icon, 250 * i, 150 * j, 250, 150, null);


        for (int i = 0; i < frameYsize; i++) {
            for (int j = 0; j < frameXsize; j++) {
                BufferedImage bufferedImage;
                if (mapsBlocks[i][j].getBlocksType().equals(PASSABLE_FIELD))
                    bufferedImage = passableField_Icon;
                else if (mapsBlocks[i][j].getBlocksType().equals(STRONG_WALL))
                    bufferedImage = strongWall_Icon;
                else //if(mapsBlocks[i][j].getBlocksType().equals(DESTROYABLE_WALL))
                    bufferedImage = destroyableWall_Icon;


                if (i % 2 == 0 && j % 2 == 1) {
                    for (int t = 0; t < 7; t++) {
                        //graphics2D.rotate(Math.toRadians(30), 100, 430);
                        if (!mapsBlocks[i][j].getBlocksType().equals(PASSABLE_FIELD))
                            graphics2D.drawImage(bufferedImage, (mapsBlocks[i][j].getX1() + t * mapsBlocks[0][0].getBlocksHeight()), (mapsBlocks[i][j].getY1()), (mapsBlocks[0][0].getBlocksHeight()), (mapsBlocks[0][0].getBlocksHeight()), null);
                    }

                } else if (i % 2 == 1 && j % 2 == 0) {
                    for (int t = 0; t < 7; t++) {
                        if (!mapsBlocks[i][j].getBlocksType().equals(PASSABLE_FIELD))
                            graphics2D.drawImage(bufferedImage, (mapsBlocks[i][j].getX1()), (mapsBlocks[i][j].getY1() + t * mapsBlocks[0][0].getBlocksWidth()), (mapsBlocks[0][0].getBlocksHeight()), (mapsBlocks[0][0].getBlocksHeight()), null);
                    }
                } else if (i % 2 == 0 && j % 2 == 0) {
                    if (!mapsBlocks[i][j].getBlocksType().equals(PASSABLE_FIELD))
                        graphics2D.drawImage(bufferedImage, (mapsBlocks[i][j].getX1()), (mapsBlocks[i][j].getY1()), (mapsBlocks[0][0].getBlocksHeight()), (mapsBlocks[0][0].getBlocksHeight()), null);

                }


            }
        }


    }

    public boolean tankRenderer(ArrayList<Tank> tanks, Graphics2D graphics2D) {
        BufferedImage bufferedImage;
        boolean successful = true;
        OverlapDetector overlapDetector = new OverlapDetector();
        for (Tank tank : tanks) {
            for (int i = 0; i < frameYsize; i++) {
                for (int j = 0; j < frameXsize; j++) {
                    if (overlapDetector.overlap(
                            mapsBlocks[i][j].getX1(),
                            mapsBlocks[i][j].getY1(),
                            mapsBlocks[i][j].getX3(),
                            mapsBlocks[i][j].getY3(),
                            tank.getLocX(),
                            tank.getLocY(),
                            tank.getX3(),
                            tank.getY3())

                    ) successful = false;
                }
            }
            graphics2D.rotate(Math.toRadians(tank.getBodyAngel()), tank.getLocX() + (tank.getWidth() / 2), tank.getLocY() + (tank.getHeight() / 2));
            graphics2D.drawImage(tank.getTankBodyImage(), tank.getLocX(), tank.getLocY(), 4 * ((standardX + standardY) / 2), 4 * ((standardX + standardY) / 2), null);
            graphics2D.rotate(Math.toRadians(-tank.getBodyAngel()), tank.getLocX() + (tank.getWidth() / 2), tank.getLocY() + (tank.getHeight() / 2));
        }
        return successful;
    }

    public boolean awardRenderer(ArrayList<Award> awards, Graphics2D graphics2D) {
        BufferedImage bufferedImage;
        boolean successful = true;

        OverlapDetector overlapDetector = new OverlapDetector();
        for (Award award : awards) {
            for (int i = 0; i < frameYsize; i++) {
                for (int j = 0; j < frameXsize; j++) {
                    if (overlapDetector.overlap(
                            mapsBlocks[i][j].getX1(),
                            mapsBlocks[i][j].getY1(),
                            mapsBlocks[i][j].getX3(),
                            mapsBlocks[i][j].getY3(),
                            award.getX1(),
                            award.getY1(),
                            award.getX3(),
                            award.getY3())

                    ) successful = false;
                }
            }
            graphics2D.drawImage(award.getAwardsImage(), award.getX1(), award.getY1(), 4 * ((standardX + standardY) / 2), 4 * ((standardX + standardY) / 2), null);

        }
        return successful;
    }


    public boolean rightIsBlank(Tank tank) {
        int rightBound = tank.getLocX() + tank.getWidth();
        int tankMinY = tank.getLocY();
        int tankMaxY = tank.getLocY() + tank.getHeight();

        for (int i = 0; i < frameYsize; i++) {
            for (int j = 0; j < frameXsize; j++) {
                if (mapsBlocks[i][j].getBlocksType().equals(STRONG_WALL) || mapsBlocks[i][j].getBlocksType().equals(DESTROYABLE_WALL)) {
                    if (mapsBlocks[i][j].getHealth() > 0 && mapsBlocks[i][j].getHealth() != 9999) {
                        int wallMinY = mapsBlocks[i][j].getX1();
                        int wallMaxY = mapsBlocks[i][j].getY1() + mapsBlocks[i][j].getBlocksHeight();
                        if ((tankMinY <= wallMinY && tankMaxY > wallMinY) || (tankMinY >= wallMinY && tankMaxY <= wallMaxY) || (tankMinY < wallMaxY && tankMaxY >= wallMaxY)) {
                            if (rightBound <= mapsBlocks[i][j].getX1() && rightBound + (tank.getHeight() + tank.getWidth()) / 2 >= mapsBlocks[i][j].getX1()) {
                                return false;
                            }
                        }
                    }
                }
            }
        }
        return true;
    }

    public boolean leftIsBlank(Tank tank) {
        int leftBound = tank.getLocX();
        int tankMinY = tank.getLocY();
        int tankMaxY = tank.getLocY() + tank.getHeight();
        for (int i = 0; i < frameYsize; i++) {
            for (int j = 0; j < frameXsize; j++) {
                if (mapsBlocks[i][j].getBlocksType().equals(STRONG_WALL) || mapsBlocks[i][j].getBlocksType().equals(DESTROYABLE_WALL)) {
                    if (mapsBlocks[i][j].getHealth() > 0) {
                        int wallMinY = mapsBlocks[i][j].getY1();
                        int wallMaxY = mapsBlocks[i][j].getY1() + mapsBlocks[i][j].getBlocksHeight();
                        if ((tankMinY <= wallMinY && tankMaxY > wallMinY) || (tankMinY >= wallMinY && tankMaxY <= wallMaxY) || (tankMinY < wallMaxY && tankMaxY >= wallMaxY)) {
                            if (leftBound >= mapsBlocks[i][j].getX1() + mapsBlocks[i][j].getBlocksWidth() && leftBound - (tank.getHeight() + tank.getWidth()) / 2 <= mapsBlocks[i][j].getX1() + mapsBlocks[i][j].getBlocksWidth()) {
                                return false;
                            }
                        }
                    }
                }
            }
        }
        return true;
    }

    public boolean upIsBlank(Tank tank) {
        int upBound = tank.getLocY();
        int tankMinX = tank.getLocX();
        int tankMaxX = tank.getLocX() + tank.getWidth();
        for (int i = 0; i < frameYsize; i++) {
            for (int j = 0; j < frameXsize; j++) {
                if (mapsBlocks[i][j].getBlocksType().equals(STRONG_WALL) || mapsBlocks[i][j].getBlocksType().equals(DESTROYABLE_WALL)) {
                    if (mapsBlocks[i][j].getHealth() > 0) {
                        int wallMinX = mapsBlocks[i][j].getX1();
                        int wallMaxX = mapsBlocks[i][j].getX1() + mapsBlocks[i][j].getBlocksWidth();
                        if ((tankMinX <= wallMinX && tankMaxX > wallMinX) || (tankMinX >= wallMinX && tankMaxX <= wallMaxX) || (tankMinX < wallMaxX && tankMaxX >= wallMaxX)) {
                            if (upBound >= mapsBlocks[i][j].getY1() + mapsBlocks[i][j].getBlocksHeight() && upBound - (tank.getHeight() + tank.getWidth()) / 2 <= mapsBlocks[i][j].getY1() + mapsBlocks[i][j].getBlocksHeight()) {
                                return false;
                            }
                        }
                    }
                }
            }
        }
        return true;
    }

    public boolean downIsBlank(Tank tank) {
        int downBound = tank.getLocY() + tank.getHeight();
        int tankMinX = tank.getLocX();
        int tankMaxX = tank.getLocX() + tank.getWidth();
        for (int i = 0; i < frameYsize; i++) {
            for (int j = 0; j < frameXsize; j++) {
                if (mapsBlocks[i][j].getBlocksType().equals(STRONG_WALL) || mapsBlocks[i][j].getBlocksType().equals(DESTROYABLE_WALL)) {
                    if (mapsBlocks[i][j].getHealth() > 0) {
                        int wallMinX = mapsBlocks[i][j].getX1();
                        int wallMaxX = mapsBlocks[i][j].getX1() + mapsBlocks[i][j].getBlocksWidth();
                        if ((tankMinX <= wallMinX && tankMaxX > wallMinX) || (tankMinX >= wallMinX && tankMaxX <= wallMaxX) || (tankMinX < wallMaxX && tankMaxX >= wallMaxX)) {
                            if (downBound <= mapsBlocks[i][j].getY1() && downBound + (tank.getHeight() + tank.getWidth()) / 2 >= mapsBlocks[i][j].getY1()) {
                                return false;
                            }
                        }
                    }
                }
            }
        }
        return true;
    }

    public boolean isCollided(int x1_1, int y1_1, int x1_2, int y1_2, int x1_3, int y1_3, int x1_4, int y1_4, int x2_1, int y2_1, int x2_2, int y2_2, int x2_3, int y2_3, int x2_4, int y2_4) {
        Polygon firstShape = new Polygon();
        Polygon secondShape = new Polygon();
        firstShape.addPoint(x1_1, y1_1);
        firstShape.addPoint(x1_2, y1_2);
        firstShape.addPoint(x1_3, y1_3);
        firstShape.addPoint(x1_4, y1_4);
        secondShape.addPoint(x2_1, y2_1);
        secondShape.addPoint(x2_2, y2_2);
        secondShape.addPoint(x2_3, y2_3);
        secondShape.addPoint(x2_4, y2_4);
        Area area = new Area(firstShape);
        area.intersect(new Area(secondShape));
        if (area.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    /*
    public void destroy() {
        try {
            Iterator<Projectile> item = GameFrame.getProjectiles().iterator();
            while (item.hasNext()) {
                Projectile projectile = item.next();
                int x1 = (int) projectile.getX1();
                int x2 = (int) projectile.getX2();
                int x3 = (int) projectile.getX3();
                int x4 = (int) projectile.getX4();
                int y1 = (int) projectile.getY1();
                int y2 = (int) projectile.getY2();
                int y3 = (int) projectile.getY3();
                int y4 = (int) projectile.getY4();
                boolean removed = false;
                for (int i = 0; i < frameYsize; i++) {
                    for (int j = 0; j < frameXsize; j++) {
                        if (isCollided(mapsBlocks[i][j].getX1(), mapsBlocks[i][j].getY1(), mapsBlocks[i][j].getX2(), mapsBlocks[i][j].getY2(), mapsBlocks[i][j].getX3(), mapsBlocks[i][j].getY3(), mapsBlocks[i][j].getX4(), mapsBlocks[i][j].getY4(), x1, y1, x2, y2, x3, y3, x4, y4)) {
                            if (mapsBlocks[i][j].getBlocksType().equals(STRONG_WALL)) {
                                if (!removed) {
                                    item.remove();
                                    removed = true;
                                }
                                break;
                            }
                            if (mapsBlocks[i][j].getBlocksType().equals(DESTROYABLE_WALL) && mapsBlocks[i][j].getHealth() > 0) {
                                if (!removed) {
                                    mapsBlocks[i][j].setHealth(mapsBlocks[i][j].getHealth() - projectile.getDamage());
                                    item.remove();
                                    removed = true;
                                }
                                break;
                            }
                        }
                    }
                }
                for (Tank tank : GameFrame.getTanks())
                    if (tank.getHealth() > 0) {
                        int X1, X2, X3, X4, Y1, Y2, Y3, Y4;
                        int tankSize = 3 * (standardX + standardY) / 2;
                        int centerX = tank.getLocX() + tankSize;
                        int centerY = tank.getLocY() + tankSize;
                        int angle = tank.getBodyAngel();
                        double diameter = Math.sqrt(2) * tankSize;
                        X1 = (int) (centerX - Math.cos(Math.toRadians(angle + 45)) * diameter / 2);
                        Y1 = (int) (centerY - Math.sin(Math.toRadians(angle + 45)) * diameter / 2);
                        X2 = (int) (centerX + Math.sin(Math.toRadians(angle + 45)) * diameter / 2);
                        Y2 = (int) (centerY - Math.cos(Math.toRadians(angle + 45)) * diameter / 2);
                        X3 = (int) (centerX + Math.cos(Math.toRadians(angle + 45)) * diameter / 2);
                        Y3 = (int) (centerY + Math.sin(Math.toRadians(angle + 45)) * diameter / 2);
                        X4 = (int) (centerX - Math.sin(Math.toRadians(angle + 45)) * diameter / 2);
                        Y4 = (int) (centerY + Math.cos(Math.toRadians(angle + 45)) * diameter / 2);
                        if (isCollided(x3 + 1, y3 + 1, x3, y3, x4 + 1, y4 + 1, x4, y4, X1, Y1, X2, Y2, X3, Y3, X4, Y4)) {
                            if (!removed) {
                                tank.setHealth(tank.getHealth() - projectile.getDamage());
                            }
                        }
                    }
            }
        } catch (Exception e) {
            System.out.println(":))");
        }
    }
*/

    public int getStandardX() {
        return standardX;
    }

    public int getStandardY() {
        return standardY;
    }
}


