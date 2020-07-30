package game.troubleTankSample;

import game.sample.ball.GameFrame;

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
    private Integer barrelX;
    private Integer barrelY;
    private Integer damage;
    private int fireRate;
    private Integer bulletSpeed;
    private Integer lastTimeBulletShot;
    private BufferedImage bulletImage;
    private Integer bulletWidth;
    private Integer bulletHeight;


    public Tank(Integer health, Integer locX, Integer locY, String bodyImageAddress) {
        lastTimeBulletShot = (int) System.currentTimeMillis();
        damage = 10;
        fireRate = 2;
        bulletSpeed = 20;
        shield = false;
        laser = false;
        bodyAngel = 0;
        this.health = health;
        this.locX = locX;
        this.locY = locY;
        try{
            tankBodyImage = ImageIO.read(new File(bodyImageAddress));
            bulletImage = ImageIO.read(new File("resources/bulletBlue3.png"));
        }
        catch(IOException e){
            System.out.println("Tank Image Does Not Exist!");
        }
    }

    public void fire(Integer tankSize) {
        barrelX = locX + GameFrame.TANK_SIZE / 2;// * (int) (Math.cos(bodyAngel));
        barrelY = locY + GameFrame.TANK_SIZE / 2;// * (int) (Math.sin(bodyAngel));
        bulletWidth = tankSize / 6;
        bulletHeight = tankSize / 4;
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

    public Integer getBarrelX() {
        return barrelX;
    }

    public void setBarrelX(Integer barrelX) {
        this.barrelX = barrelX;
    }

    public Integer getBarrelY() {
        return barrelY;
    }

    public void setBarrelY(Integer barrelY) {
        this.barrelY = barrelY;
    }
}
