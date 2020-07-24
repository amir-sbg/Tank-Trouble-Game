package game.troubleTankSample;

import javax.swing.*;
import java.awt.*;

public class temporary_forRenderMap extends JPanel {
JFrame jFrame;
MapHandler goo;
    public temporary_forRenderMap(){
         goo = new MapHandler();
        goo.setBlocksLocations(800, 800);
         jFrame =new JFrame();
        jFrame.setVisible(true);
jFrame.setSize(800,800);
        jFrame.add(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        goo.renderer((Graphics2D) g);
    }

    public static void main(String[] args) {
        temporary_forRenderMap blockSizesManager=new temporary_forRenderMap();
    }
}
