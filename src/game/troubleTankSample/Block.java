package game.troubleTankSample;

public class Block {
    private int x1, x2, x3, x4, y1, y2, y3, y4, blocksHeight, blocksWidth;
    private String blocksType;

    public Block( String blocksType) {

        this.blocksType=blocksType;
    }
    public void setLocation(int x, int y, int blocksHeight, int blocksWidth){
        this.x1 = x;                                //     1_______2
        this.y1 = y;                                //     | block |
        this.x2 = x + blocksWidth;                  //     4_______3
        this.y2 = y;
        this.x3 = x + blocksWidth;
        this.y3 = y + blocksHeight;
        this.x4 = x;
        this.y4 = y + blocksHeight;
        this.blocksHeight = blocksHeight;
        this.blocksWidth = blocksWidth;
    }
    public int getX1() { return x1; }
    public int getX2() { return x2; }
    public int getX3() { return x3; }
    public int getX4() { return x4; }
    public int getY1() { return y1; }
    public int getY2() { return y2; }
    public int getY3() { return y3; }
    public int getY4() { return y4; }
    public int getBlocksHeight() { return blocksHeight; }
    public int getBlocksWidth() { return blocksWidth; }
    public String getBlocksType() { return blocksType; }
    public void setBlocksType(String blocksType) { this.blocksType = blocksType; }
}
