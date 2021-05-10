package game.troubleTankSample;

import game.sample.ball.GameFrame;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

/**
 * ArtificialIntelligence that create enemy tanks from map.txt
 *
 * @author M.Hassan Shahrian
 */
public class ArtificialIntelligenceHandler {

    private ArrayList<Tank> tanks;
    private MapHandler mapHandler;
    private BufferedImage diedTank;


    /**
     * Constructor
     *
     * @param level
     */

    public ArtificialIntelligenceHandler(Integer level, MapHandler mapHandler, ArrayList<Tank> tanks) {
        this.mapHandler = mapHandler;
        this.tanks = tanks;
        try {
            diedTank = ImageIO.read(new File("./resources/diedTank.png"));
        } catch (Exception e) {
            System.out.println("can't find image!");
        }
        Random random = new Random();
        ArrayList<String> bodyTypeImagesAddress = new ArrayList<>();


        bodyTypeImagesAddress.add("./resources/tank_dark.png");


        int xRand, yRand;
        while (true) {
            xRand = random.nextInt(mapHandler.getMapsBlocks()[0].length - 1);
            yRand = random.nextInt(mapHandler.getMapsBlocks().length - 1);
            if (mapHandler.getMapsBlocks()[yRand][xRand].getBlocksType().equals("passableField") && mapHandler.getMapsBlocks()[yRand][xRand].getBlocksWidth() > mapHandler.getStandardX() && mapHandler.getMapsBlocks()[yRand][xRand].getBlocksHeight() > mapHandler.getStandardY()) {

                tanks.add(new Tank(mapHandler.getMapsBlocks()[yRand][xRand].getBlocksCentreX(), mapHandler.getMapsBlocks()[yRand][xRand].getBlocksCentreY(), bodyTypeImagesAddress.get((int) random.nextInt(bodyTypeImagesAddress.size()))));

                break;
            }
        }
    }


    public void update() {
        Iterator<Tank> iterator = tanks.iterator();
        while (iterator.hasNext()) {
            Tank item = iterator.next();
            if (item.getHealth() > 0) {
                move(item);

                attack(item);
            } else {
                item.setTankBodyImage("./resources/diedTank.png");
            }
            //  item.incrementX(-MapHandler.getDeltaX() - MapHandler.getPlusOverX());
            //item.incrementY(-MapHandler.getDeltaY() - MapHandler.getPlusOverY());
        }
    }

    /**
     * @param tank
     */
    public void move(Tank tank) {
        boolean right = false;
        boolean down = false;
        boolean up = false;
        boolean left = false;
        int rightRequest = 0;
        int downRequest = 0;
        int locX = tank.getLocX();
        int locY = tank.getLocY();
        int targetLocX = GameFrame.myTank.getLocX();
        int targetLocY = GameFrame.myTank.getLocY();
        if (Math.pow(targetLocX - locX, 2) + Math.pow(targetLocY - locY, 2) > 10000) {
            if (targetLocX != locX) {
                if (targetLocX - locX > 0) {
                    rightRequest = 10;
                    right = true;
                } else {
                    rightRequest = -10;
                    left = true;
                }
            }
            if (targetLocY != locY) {
                if (targetLocY - locY > 0) {
                    downRequest = 10;
                    down = true;
                } else {
                    downRequest = -10;
                    up = true;
                }
            }
            rotate(tank, right, down, up, left);
            int angle = tank.getBodyAngel();
            double diameter = Math.sqrt(2) * GameFrame.TANK_SIZE;
            int centerX = tank.getLocX() + GameFrame.TANK_SIZE / 2;
            int centerY = tank.getLocY() + GameFrame.TANK_SIZE / 2;
            if (mapHandler.canMove(rightRequest, downRequest, tank)) {
                if (mapHandler.canRotate(diameter, centerX, centerY, 0, tank)) {
                    tank.setLocX(tank.getLocX() + rightRequest);
                    tank.setLocY(tank.getLocY() + downRequest);

//                    tank.incrementX(rightRequest);
//                    tank.incrementY(downRequest);
                } else if (mapHandler.canRotate(diameter, centerX, centerY, 10, tank)) {
                    tank.setBodyAngel(angle + 10);
                    tank.setLocX(tank.getLocX() + rightRequest);
                    tank.setLocY(tank.getLocY() + downRequest);
//                    tank.incrementX(rightRequest);
//                    tank.incrementY(downRequest);
                } else if (mapHandler.canRotate(diameter, centerX, centerY, -10, tank)) {
                    tank.setBodyAngel(angle - 10);
                    tank.setLocX(tank.getLocX() + rightRequest);
                    tank.setLocY(tank.getLocY() + downRequest);
//                    tank.incrementX(rightRequest);
//                    tank.incrementY(downRequest);
                }
            } else if (mapHandler.canMove(0, downRequest, tank)) {
                if (mapHandler.canRotate(diameter, centerX, centerY, 0, tank)) {
                    tank.setLocY(tank.getLocY() + downRequest);
                    //tank.incrementY(downRequest);
                } else if (mapHandler.canRotate(diameter, centerX, centerY, 10, tank)) {
                    tank.setBodyAngel(angle + 10);
                    tank.setLocY(tank.getLocY() + downRequest);
                    //tank.incrementY(downRequest);
                } else if (mapHandler.canRotate(diameter, centerX, centerY, -10, tank)) {
                    tank.setBodyAngel(angle - 10);
                    tank.setLocY(tank.getLocY() + downRequest);
                    //tank.incrementY(downRequest);
                }
            } else if (mapHandler.canMove(rightRequest, 0, tank)) {
                if (mapHandler.canRotate(diameter, centerX, centerY, 0, tank)) {
                    //tank.incrementX(rightRequest);
                    tank.setLocX(tank.getLocX() + rightRequest);
                } else if (mapHandler.canRotate(diameter, centerX, centerY, 1, tank)) {
                    tank.setBodyAngel(angle + 1);
                    tank.setLocY(tank.getLocY() + downRequest);
                    // tank.incrementY(rightRequest);
                } else if (mapHandler.canRotate(diameter, centerX, centerY, -1, tank)) {
                    tank.setBodyAngel(angle - 1);
                    tank.setLocY(tank.getLocY() + downRequest);
                    //tank.incrementY(rightRequest);
                }
            }
        }
    }

    /**
     * @param tank
     * @param right
     * @param down
     * @param up
     * @param left
     */
    private void rotate(Tank tank, boolean right, boolean down, boolean up, boolean left) {
        boolean keyUP, keyDOWN, keyRIGHT, keyLEFT;
        keyUP = up;
        keyDOWN = down;
        keyRIGHT = right;
        keyLEFT = left;
        int angle = tank.getBodyAngel();
        double diameter = Math.sqrt(2) * GameFrame.TANK_SIZE;
        int centerX = tank.getLocX() + GameFrame.TANK_SIZE / 2;
        int centerY = tank.getLocY() + GameFrame.TANK_SIZE / 2;
        if (keyUP && !keyDOWN) {
            if (keyRIGHT && !keyLEFT) {
                if (angle > 50) {
                    // if (mapHandler.canRotate(diameter, centerX, centerY, -10, tank))
                    tank.setBodyAngel(angle - 10);
                } else if (angle < 50) {
                    //if (mapHandler.canRotate(diameter, centerX, centerY, 10, tank))
                    tank.setBodyAngel(angle + 10);
                }
            } else if (keyLEFT && !keyRIGHT) {
                if (angle > -50) {
                    //if (mapHandler.canRotate(diameter, centerX, centerY, -10, tank))
                    tank.setBodyAngel(angle - 10);
                } else if (angle < -50) {
                    //if (mapHandler.canRotate(diameter, centerX, centerY, 10, tank))
                    tank.setBodyAngel(angle + 10);
                }
            } else {
                if (angle > 0) {
                    // if (mapHandler.canRotate(diameter, centerX, centerY, -10, tank))
                    tank.setBodyAngel(angle - 10);
                } else if (angle < 0) {
                    //if (mapHandler.canRotate(diameter, centerX, centerY, 10, tank))
                    tank.setBodyAngel(angle + 10);
                }
            }
        }
        if (keyDOWN && !keyUP) {
            if (keyRIGHT && !keyLEFT) {
                if (angle > 140) {
                    //  if (mapHandler.canRotate(diameter, centerX, centerY, -10, tank))
                    tank.setBodyAngel(angle - 10);
                } else if (angle < 140) {
                    //if (mapHandler.canRotate(diameter, centerX, centerY, 10, tank))
                    tank.setBodyAngel(angle + 10);
                }
            } else if (keyLEFT && !keyRIGHT) {
                if (angle > -140) {
                    //if (mapHandler.canRotate(diameter, centerX, centerY, -10, tank))
                    tank.setBodyAngel(angle - 10);
                } else if (angle < -140) {
                    //if (mapHandler.canRotate(diameter, centerX, centerY, 10, tank))
                    tank.setBodyAngel(angle + 10);
                }
            } else {
                if (angle > 180) {
                    //if (mapHandler.canRotate(diameter, centerX, centerY, -10, tank))
                    tank.setBodyAngel(angle - 10);
                } else if (angle < 180) {
                    //if (mapHandler.canRotate(diameter, centerX, centerY, 10, tank))
                    tank.setBodyAngel(angle + 10);
                }
            }
        }
        if (keyLEFT && !keyRIGHT) {
            if (!keyUP && !keyDOWN) {
                if (angle > -90) {
                    // if (mapHandler.canRotate(diameter, centerX, centerY, -10, tank))
                    tank.setBodyAngel(angle - 10);
                } else if (angle < -90) {
                    //if (mapHandler.canRotate(diameter, centerX, centerY, 10, tank))
                    tank.setBodyAngel(angle + 10);
                }
            }
        }
        if (keyRIGHT && !keyLEFT) {
            if (!keyUP && !keyDOWN) {
                if (angle > 90) {
                    //if (mapHandler.canRotate(diameter, centerX, centerY, -10, tank))
                    tank.setBodyAngel(angle - 10);
                } else if (angle < 90) {
                    //if (mapHandler.canRotate(diameter, centerX, centerY, 10, tank))
                    tank.setBodyAngel(angle + 10);
                }
            }
        }
    }

    /**
     * @param tank
     */
    public void attack(Tank tank) {
        boolean openView = true;
        int locX = tank.getLocX();
        int locY = tank.getLocY();
        int targetLocX = GameFrame.myTank.getLocX();
        int targetLocY = GameFrame.myTank.getLocY();


        int tanksCentreX = (int) (tank.getBarrelX() + (GameFrame.TANK_SIZE / 6) / 2 * Math.sin(tank.getBodyAngel()));
        int tanksCentreY = (int) (tank.getBarrelY() - (GameFrame.TANK_SIZE / 4) / 2 * Math.cos(tank.getBodyAngel()));
        int myTanksCentreX = (int) (GameFrame.myTank.getBarrelX() + (GameFrame.TANK_SIZE / 6) / 2 * Math.sin(GameFrame.myTank.getBodyAngel()));
        int myTanksCentreY = (int) (GameFrame.myTank.getLocY() - (GameFrame.TANK_SIZE / 4) / 2 * Math.cos(GameFrame.myTank.getBodyAngel()));
        int stepX = tanksCentreX > myTanksCentreX ? -1 : tanksCentreX < myTanksCentreX ? 1 : 0;
        int stepY = tanksCentreY > myTanksCentreY ? -1 : tanksCentreY < myTanksCentreY ? 1 : 0;



//        for (int j = tanksCentreY; j != myTanksCentreY; j += stepX) {
//            for (int i = tanksCentreX; i != myTanksCentreX; i += stepY) {
//                for (int k = 0; k < mapHandler.getMapsBlocks().length; k++) {
//                    for (int f = 0; f < mapHandler.getMapsBlocks()[0].length; f++) {
//
//                        if (mapHandler.getMapsBlocks()[k][f].isInThisBlock(i, j) && mapHandler.getMapsBlocks()[k][f].getBlocksType().equals("strongWall")) {
//                            openView = false;
//                            break;
//                        }
//                    }
//                }
//            }
//        }
//        System.out.println(openView);


        if (Math.pow(targetLocX - locX, 2) + Math.pow(targetLocY - locY, 2) < 160000 && openView) {
            // tank.getTankShell().getWeapons().get(0).fire(tank.getTankShell().getShellAngel(), tank.getTankShell().getShellEndingX(), tank.getTankShell().getShellEndingY());
            tank.fire();
        }
    }

    /**
     * @return
     */
    public ArrayList<Tank> getTanks() {
        return tanks;
    }

    /**
     * @return
     */
    public boolean gameEnded() {
        for (Tank tank : tanks) {
            if (tank.getHealth() > 0) {
                return false;
            }
        }
        return true;
    }
}
