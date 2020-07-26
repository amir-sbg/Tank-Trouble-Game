package game.troubleTankSample;

public class OverlapDetector {

    public boolean overlap(int O1_x1,
                           int O1_y1,
                           int O1_x3,
                           int O1_y3,
                           int O2_x1,
                           int O2_y1,
                           int O2_x3,
                           int O2_y3) {
        if (O1_x1 >= O2_x3 || O2_x1 >= O1_x3)
            return false;


        if (O1_y1 <= O2_y3 || O2_y1 <= O1_y3)
            return false;

        return true;
    }
}