package game.troubleTankSample;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class MapHandler {

    private static int numberOf_Vertical_Blocks;
    private static int numberOf_Horizontal_Blocks;
    private int frameXsize ;
    private int frameYsize ;
    private Block[][] mapsBlocks;
    private final static String STRONG_WALL = "strongWall";
    private final static String DESTROYABLE_WALL = "destroyableWall";
    private final static String PASSABLE_FIELD = "passableField";
    private final static String AWARD_BOX_GUARD = "guardAward";
    private final static String AWARD_BOX_LASER = "laserAward";
    private final static String AWARD_BOX_REPAIR_10per = "repairAward";
    private final static String AWARD_BOX_POWER_2X = "powerAward2X";
    private final static String AWARD_BOX_POWER_3X = "powerAward3X";
    private static BufferedImage strongWall_Icon;
    private static BufferedImage destroyableWall_Icon;
    private static BufferedImage guardAward_Icon;
    private static BufferedImage laserAward_Icon;
    private static BufferedImage repairAward_Icon;
    private static BufferedImage powerAward2X_Icon;
    private static BufferedImage powerAward3X_Icon;
    private static String mapAddress;

    public MapHandler() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("./r.txt"));
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
            System.err.println("map not exist!");
        } catch (IOException e) {
        }

    }

    public void setBlocksLocations(int frames_width, int frames_height) {
        int standardX = frames_width / (4 * frameXsize - 3);
        int standardY = frames_height / (4 * frameYsize - 3);

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
                 System.out.print(mapsBlocks[i][j].getX1()+"__"+mapsBlocks[i][j].getY1()+"->"+mapsBlocks[i][j].getX3()+"__"+mapsBlocks[i][j].getY3()+"  ");
            }
            System.out.println("\n");
        }
    }

    public void renderer (Graphics2D graphics2D) {
        for (int i = 0; i < frameYsize; i++) {
            for (int j = 0; j < frameXsize; j++) {
                ImageIcon imageIcon;
                if (mapsBlocks[i][j].getBlocksType().equals(PASSABLE_FIELD))
                    imageIcon = new ImageIcon("./t.png");
                else if(mapsBlocks[i][j].getBlocksType().equals(STRONG_WALL))
                    imageIcon = new ImageIcon("./r.png");
                else //if(mapsBlocks[i][j].getBlocksType().equals(DESTROYABLE_WALL))
                    imageIcon = new ImageIcon("./r.png");

                graphics2D.drawImage(imageIcon.getImage(), (mapsBlocks[i][j].getX1()), (mapsBlocks[i][j].getY1()),(mapsBlocks[i][j].getBlocksHeight()),  (mapsBlocks[i][j].getBlocksWidth()), null);
            }
        }


    }




}