package game.troubleTankSample;

public class Block {
    private int x1, x2, x3, x4, y1, y2, y3, y4, blocksHeight, blocksWidth,health;
    private String blocksType;

    public Block( String blocksType) {

        this.blocksType=blocksType;
        if(blocksType.equals("strongWall"))
            health=9999;
        else if(blocksType.equals("passableField"))
            health=0;

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
    public int getBlocksCentreX(){return (x1+x3)/2;}
    public int getBlocksCentreY(){return (y1+y3)/2;}
    public boolean isInThisBlock(int x,int y){
        if(x>x1 && x<x2 && y<y4 && y>y1){
          //  if(x-size/2>x1 && x+size/2<x2 && y+size/2<y3 && y-size/2>y1){
                return true;
          //  }
        }
        return false;
    }
    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }
}
