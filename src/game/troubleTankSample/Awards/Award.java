package game.troubleTankSample.Awards;

abstract public class Award {
    protected String awardsType;
    protected float awardsQuantity;
    protected int x1, x2, x3, x4, y1, y2, y3, y4, widthOfAward, heightOfAward;

    public Award(String awardsType) {
        this.awardsType = awardsType;
    }


    public abstract void doAction(/*Tank tank*/);

    public void setLocation(int x, int y, int widthOfAward, int heightOfAward) {
        this.y1 = y;
        this.x1 = x;
        x2 = x + widthOfAward;
        y2 = y;
        x3 = x + widthOfAward;
        y3 = y + heightOfAward;
        x4 = x;
        y4 = y + heightOfAward;
    }

    public float getAwardsQuantity() {
        return awardsQuantity;
    }

    public String getAwardsType() {
        return awardsType;
    }

    public int getX1() {
        return x1;
    }

    public int getY1() {
        return y1;
    }

    public int getX2() {
        return x2;
    }

    public int getY2() {
        return y2;
    }

    public int getX3() {
        return x3;
    }

    public int getY4() {
        return y4;
    }

    public int getY3() {
        return y3;
    }

    public int getX4() {
        return x4;
    }



}
