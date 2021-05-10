package game.troubleTankSample;

import game.sample.ball.GameFrame;

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
    private boolean shield;
    private boolean powerBoosted;
    private boolean laser;
    private Integer bodyAngel;
    private BufferedImage tankBodyImage;
    private Integer barrelX;
    private Integer barrelY;
    private Integer damage;
    private int fireRate;
    private Integer bulletSpeed;
    private Integer lastTimeBulletShot;
    private BufferedImage bulletImage;
    private Integer bulletWidth;
    private Integer bulletHeight;


    public Tank( Integer locX, Integer locY, String bodyImageAddress) {
        lastTimeBulletShot = (int) System.currentTimeMillis();
        damage = 10;
        fireRate = 2;
        bulletSpeed = 20;
        shield = false;
        laser = false;
        powerBoosted=false;
        bodyAngel = 0;
        this.locX = locX;
        this.locY = locY;
        //TODO
        this.health=100 ;       //READ FROM FILE



        try {
            tankBodyImage = ImageIO.read(new File(bodyImageAddress));
            bulletImage = ImageIO.read(new File("resources/bulletBlue3.png"));
        }
         catch (IOException e) {
            System.out.println("Tank Image Does Not Exist in Tank class!");
        }
    }

    public void fire() {
        bulletWidth =GameFrame.TANK_SIZE / 6;
        bulletHeight = GameFrame.TANK_SIZE / 4;
        barrelX = locX + GameFrame.TANK_SIZE / 2;
        barrelY = locY + GameFrame.TANK_SIZE / 2;
        Integer presentTime = (int) System.currentTimeMillis();
        if (presentTime - lastTimeBulletShot >= (1000 / fireRate)) {
            lastTimeBulletShot = presentTime;
            GameFrame.getBullets().add(new Bullet(bulletImage, damage, bulletSpeed, Math.toRadians(bodyAngel + 90), barrelX, barrelY, bulletWidth, bulletHeight, presentTime));
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

    public Integer getX3() {
        return locX + GameFrame.TANK_SIZE;
    }

    public Integer getY3() {
        return locY + GameFrame.TANK_SIZE;
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
        return barrelX;

    }

    public int getTanksYCentre() {
        return barrelY;

    }

    public boolean isLaser() {
        return laser;
    }

    public boolean isPowerBoosted() {
        return powerBoosted;
    }

    public Integer getTankSize() {
        return GameFrame.TANK_SIZE;
    }

    public void setBulletSpeed(Integer bulletSpeed) {
        this.bulletSpeed = bulletSpeed;
    }

    public Integer getBulletSpeed() {
        return bulletSpeed;
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

    public void setTankBodyImage(String image)  {
        try {
            tankBodyImage=ImageIO.read(new File(image));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public Integer getBarrelX() {
        return locX + GameFrame.TANK_SIZE / 2;
    }

    public void setBarrelX(Integer barrelX) {
        this.barrelX = barrelX;
    }

    public Integer getBarrelY() {
        return locY + GameFrame.TANK_SIZE / 2 ;
    }

    public void setBarrelY(Integer barrelY) {
        this.barrelY = barrelY;
    }


}
