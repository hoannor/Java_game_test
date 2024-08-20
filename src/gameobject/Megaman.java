package src.gameobject;

import java.awt.*;

public class Megaman {

    private float posX;
    private float posY;

    private float width;
    private float height;
    private float mass;

    private float speedX;
    private float speedY;

    public static int DIR_LEFT;
    public static int DIR_RIGHT;
    private int direction;



    public Megaman(float posX, float posY, float width, float height, float mass) {
        this.posX = posX;
        this.posY = posY;
        this.width = width;
        this.height = height;
        this.mass = mass;
    }

    public void update()
    {
        setPosX(getPosX() + speedX); // thay doi vi tri bang cach cong 1 luong cua toc do

    }

    public void draw(Graphics2D g2)
    {
        g2.setColor(Color.red);
        g2.fillRect((int) posX, (int) posY, (int) width, (int) height);
    }

    public float getPosX() {
        return posX;
    }

    public void setPosX(float posX) {
        this.posX = posX;
    }

    public float getPosY() {
        return posY;
    }

    public void setPosY(float posY) {
        this.posY = posY;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getMass() {
        return mass;
    }

    public void setMass(float mass) {
        this.mass = mass;
    }

    public float getSpeedX() {
        return speedX;
    }

    public void setSpeedX(float speedX) {
        this.speedX = speedX;
    }

    public float getSpeedY() {
        return speedY;
    }

    public void setSpeedY(float speedY) {
        this.speedY = speedY;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }
}
