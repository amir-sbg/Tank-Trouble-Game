package game.troubleTankSample;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Tank {
    private Integer health;
    private Integer speed;
    private Integer locX;
    private Integer locY;
    private boolean shield;
    private boolean laser;
    private Integer bodyAngel;
    private BufferedImage tankBodyImage;


    public Tank(Integer health, Integer locX, Integer locY, String bodyImageAddress) {
        shield = false;
        laser = false;
        bodyAngel = 0;
        this.health = health;
        this.locX = locX;
        this.locY = locY;
        try{
            tankBodyImage = ImageIO.read(new File(bodyImageAddress));
        }
        catch(IOException e){
            System.out.println("Tank Image Does Not Exist!");
        }
    }

    public void setLocX(Integer locX) {
        this.locX = locX;
    }
    public void setLocY(Integer locY) {
        this.locY = locY;
    }
    public Integer getHealth() {
        return health;
    }
    public Integer getLocX() {
        return locX;
    }
    public Integer getLocY() {
        return locY;
    }
    public void changeX(Integer num) {
        this.locX += num;
    }
    public void changeY(Integer num) {
        this.locY += num;
    }
    public void setHealth(Integer health) {
        this.health = health;
    }
    public boolean isShield() {
        return shield;
    }

    public void setShield(boolean shield) {
        this.shield = shield;
    }

    public boolean isLaser() {
        return laser;
    }

    public void setLaser(boolean laser) {
        this.laser = laser;
    }
    public Integer getBodyAngel() {
        return bodyAngel;
    }
    public BufferedImage getTankBodyImage() {
        return tankBodyImage;
    }
    public void setBodyAngel(Integer bodyAngel) {
        this.bodyAngel = bodyAngel;
    }
    public void setTankBodyImage1(BufferedImage image) {
        tankBodyImage = image;
    }
}
