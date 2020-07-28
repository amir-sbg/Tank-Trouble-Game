package game.troubleTankSample;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Tank {
    private Integer health;
    private Integer power;
    private Integer speed;
    private Integer locX;
    private Integer locY;
    private Integer width;
    private Integer height;
    private boolean shield;
    private boolean powerBoosted;
    private boolean laser;
    private Integer bodyAngel;
    private BufferedImage tankBodyImage;


    public Tank(Integer health, Integer locX, Integer locY, String bodyImageAddress) {
        shield = false;
        laser = false;
        powerBoosted=false;
        bodyAngel = 0;
        this.health = health;
        this.locX = locX;
        this.locY = locY;


        try {
            tankBodyImage = ImageIO.read(new File(bodyImageAddress));

        } catch (IOException e) {
            System.out.println("Tank Image Does Not Exist!");
        }
    }

    public void setHeightAndWeight(Integer width, Integer height) {
        this.height = height;
        this.width = width;
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

    public Integer getX3() {
        return locX + width;
    }

    public Integer getY3() {
        return locY + height;
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
        if (shield== true)
            health=999999999;
        this.shield = shield;
    }

    public void setPower(Integer power) {
        this.power = power;
    }

    public Integer getPower() {
        return power;
    }

    public Integer getSpeed() {
        return speed;
    }

    public void setSpeed(Integer speed) {
        this.speed = speed;
    }

    public int getTanksXCentre() {
        return (int) (locX + (Math.cos(Math.toRadians(bodyAngel - 45))) * ((height + width) / 2));

    }

    public int getTanksYCentre() {
        return (int) (locY + (Math.sin(Math.toRadians(bodyAngel - 45))) * ((height + width) / 2));

    }

    public boolean isLaser() {
        return laser;
    }

    public boolean isPowerBoosted() {
        return powerBoosted;
    }

    public Integer getHeight() {
        return height;
    }

    public Integer getWidth() {
        return width;
    }

    public void setLaser(boolean laser) {
        this.laser = laser;
    }

    public void setPowerBoosted(boolean powerBoosted) {
        this.powerBoosted = powerBoosted;
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
