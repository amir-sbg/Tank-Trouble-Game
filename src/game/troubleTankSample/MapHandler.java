package game.troubleTankSample;

import game.troubleTankSample.Awards.Award;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;

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

        graphics2D.drawImage(laserAward_Icon, 100, 430, 3 * ((standardX + standardY) / 2), 3 * ((standardX + standardY) / 2), null);
        graphics2D.drawImage(powerAward2X_Icon, 100, 550, 3 * ((standardX + standardY) / 2), 3 * ((standardX + standardY) / 2), null);
        graphics2D.drawImage(shieldAward_Icon, 690, 450, 3 * ((standardX + standardY) / 2), 3 * ((standardX + standardY) / 2), null);
        graphics2D.drawImage(healerAward_Icon, 780, 450, 3 * ((standardX + standardY) / 2), 3 * ((standardX + standardY) / 2), null);
        graphics2D.drawImage(powerAward3X_Icon, 330, 450, 3 * ((standardX + standardY) / 2), 3 * ((standardX + standardY) / 2), null);

        // graphics2D.drawImage(tankBlue_Icon, 200,430,3*((standardX+standardY)/2),3*((standardX+standardY)/2), null);
        graphics2D.drawImage(tankYellow_Icon, 200, 430, 4 * ((standardX + standardY) / 2), 4 * ((standardX + standardY) / 2), null);
        graphics2D.drawImage(tankDark_Icon, 200, 550, 4 * ((standardX + standardY) / 2), 4 * ((standardX + standardY) / 2), null);
        graphics2D.drawImage(tankGreen_Icon, 790, 450, 4 * ((standardX + standardY) / 2), 4 * ((standardX + standardY) / 2), null);
        graphics2D.drawImage(tankSand_Icon, 880, 450, 4 * ((standardX + standardY) / 2), 4 * ((standardX + standardY) / 2), null);


    }

//    public boolean tankRenderer(ArrayList<Tank> tanks, Graphics2D graphics2D) {
//        BufferedImage bufferedImage;
//
//        OverlapDetector overlapDetector = new OverlapDetector();
//        for (Award award : awards) {
//            for (int i = 0; i < frameYsize; i++) {
//                for (int j = 0; j < frameXsize; j++) {
//                    if (overlapDetector.overlap(
//                            mapsBlocks[i][j].getX1(),
//                            mapsBlocks[i][j].getY1(),
//                            mapsBlocks[i][j].getX3(),
//                            mapsBlocks[i][j].getY3(),
//                            award.getX1(),
//                            award.getY1(),
//                            award.getX3(),
//                            award.getY3())
//
//                    ) return false;
//                }
//            }
//        }
//    }

    public boolean awardRenderer(ArrayList<Award> awards, Graphics2D graphics2D) {
        BufferedImage bufferedImage;
        boolean okOrNot=true;

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

                    ) okOrNot=false;
                }
            }

            if (award.getAwardsType().equals(AWARD_BOX_HEAL_10per))
                graphics2D.drawImage(healerAward_Icon, award.getX1(), award.getY1(), 4 * ((standardX + standardY) / 2), 4 * ((standardX + standardY) / 2), null);

            else if (award.getAwardsType().equals(AWARD_BOX_POWER_2X))
                graphics2D.drawImage(powerAward2X_Icon, award.getX1(), award.getY1(), 4 * ((standardX + standardY) / 2), 4 * ((standardX + standardY) / 2), null);

            else if (award.getAwardsType().equals(AWARD_BOX_POWER_3X))
                graphics2D.drawImage(powerAward3X_Icon, award.getX1(), award.getY1(), 4 * ((standardX + standardY) / 2), 4 * ((standardX + standardY) / 2), null);

            else if (award.getAwardsType().equals(AWARD_BOX_LASER))
                graphics2D.drawImage(laserAward_Icon, award.getX1(), award.getY1(), 4 * ((standardX + standardY) / 2), 4 * ((standardX + standardY) / 2), null);

            else if (award.getAwardsType().equals(AWARD_BOX_SHIELD))
                graphics2D.drawImage(shieldAward_Icon, award.getX1(), award.getY1(), 4 * ((standardX + standardY) / 2), 4 * ((standardX + standardY) / 2), null);


        }
return okOrNot;
    }
}


