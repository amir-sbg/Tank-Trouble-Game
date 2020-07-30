package game.troubleTankSample;

import java.awt.image.BufferedImage;

public class Bullet {

    private double x1;
    private double x2;
    private double x3;
    private double x4;
    private double y1;
    private double y2;
    private double y3;
    private double y4;
    private double x5;
    private double y5;
    private Integer bulletWidth;
    private Integer bulletHeight;
    private BufferedImage bulletImage;
    private final Integer damage;
    private final Integer bulletSpeed;
    private double barrelAngel;
    private Integer firstTimeBulletWasShot;
    private Integer presentTimeOfTheBullet;

    public Bullet(BufferedImage bulletImage, Integer damage, Integer bulletSpeed, double barrelAngel, double barrelX, Integer barrelY, Integer bulletWidth, Integer bulletHeight, Integer firstTimeBulletWasShot) {
        this.damage = damage;
        this.bulletSpeed = bulletSpeed;
        this.bulletWidth = bulletWidth;
        this.bulletHeight = bulletHeight;
        this.barrelAngel = barrelAngel;
        this.bulletImage = bulletImage;
        this.firstTimeBulletWasShot = firstTimeBulletWasShot;

        x1 = (barrelX + bulletWidth/2 * Math.sin(barrelAngel));
        y1 = (barrelY - bulletWidth/2 * Math.cos(barrelAngel));
        x2 = (barrelX - bulletWidth/2 * Math.sin(barrelAngel));
        y2 = (barrelY + bulletWidth/2 * Math.cos(barrelAngel));
        x3 = x2 + (Math.cos(barrelAngel) * bulletHeight);
        y3 = y2 + (Math.sin(barrelAngel) * bulletHeight);
        x4 = x1 + (Math.cos(barrelAngel) * bulletHeight);
        y4 = y1 + (Math.sin(barrelAngel) * bulletHeight);
        x5 = (x3 + x4)/2;
        y5 = (y3 + y4)/2;
    }

    public void update() {
        x1 += (bulletSpeed * Math.cos(barrelAngel));
        x2 += (bulletSpeed * Math.cos(barrelAngel));
        x3 += (bulletSpeed * Math.cos(barrelAngel));
        x4 += (bulletSpeed * Math.cos(barrelAngel));
        y1 += (bulletSpeed * Math.sin(barrelAngel));
        y2 += (bulletSpeed * Math.sin(barrelAngel));
        y3 += (bulletSpeed * Math.sin(barrelAngel));
        y4 += (bulletSpeed * Math.sin(barrelAngel));
        x5 = (x3 + x4)/2;
        y5 = (y3 + y4)/2;
        presentTimeOfTheBullet = (int) System.currentTimeMillis() - firstTimeBulletWasShot;
    }

    public double getX1() {
        return x1;
    }

    public void setX1(double x1) {
        this.x1 = x1;
    }

    public double getX2() {
        return x2;
    }

    public void setX2(double x2) {
        this.x2 = x2;
    }

    public double getX3() {
        return x3;
    }

    public void setX3(double x3) {
        this.x3 = x3;
    }

    public double getX4() {
        return x4;
    }

    public void setX4(double x4) {
        this.x4 = x4;
    }

    public double getY1() {
        return y1;
    }

    public void setY1(double y1) {
        this.y1 = y1;
    }

    public double getY2() {
        return y2;
    }

    public void setY2(double y2) {
        this.y2 = y2;
    }

    public double getY3() {
        return y3;
    }

    public void setY3(double y3) {
        this.y3 = y3;
    }

    public double getY4() {
        return y4;
    }

    public void setY4(double y4) {
        this.y4 = y4;
    }

    public double getX5() {
        return x5;
    }

    public void setX5(double x5) {
        this.x5 = x5;
    }

    public double getY5() {
        return y5;
    }

    public void setY5(double y5) {
        this.y5 = y5;
    }

    public Integer getBulletWidth() {
        return bulletWidth;
    }

    public void setBulletWidth(Integer bulletWidth) {
        this.bulletWidth = bulletWidth;
    }

    public Integer getBulletHeight() {
        return bulletHeight;
    }

    public void setBulletHeight(Integer bulletHeight) {
        this.bulletHeight = bulletHeight;
    }

    public Integer getDamage() {
        return damage;
    }

    public Integer getBulletSpeed() {
        return bulletSpeed;
    }

    public double getBarrelAngel() {
        return barrelAngel;
    }

    public Integer getPresentTimeOfTheBullet() {
        return presentTimeOfTheBullet;
    }

    public void setPresentTimeOfTheBullet(Integer presentTimeOfTheBullet) {
        this.presentTimeOfTheBullet = presentTimeOfTheBullet;
    }

    public BufferedImage getBulletImage() {
        return bulletImage;
    }

    public void setBarrelAngel(double barrelAngel) {
        this.barrelAngel = barrelAngel;
    }
}
