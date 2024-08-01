package src.userinterface;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GamePanel extends JPanel implements Runnable, KeyListener {

    private Thread thread;
    private boolean isRunning;

    private inputManager inputManager;

//    BufferedImage image;
//    BufferedImage subImage;

    public GamePanel() {
        inputManager = new inputManager();
//        try {
//            image = ImageIO.read(new File("data/megasprite.png"));
//            subImage = image.getSubimage(2, 5, 30, 100);
//        } catch (IOException ex) {
//            ex.printStackTrace(); // in ra loi tren console
//        }
    }

    @Override
    public  void paint(Graphics g)
    {
        g.setColor(Color.white);
        g.fillRect(0, 0, GameFrame.SCREEN_WIDTH, GameFrame.SCREEN_HEIGHT); // tao ra mot vung contain trong frame

//        g.drawImage(subImage, 10, 10, this); // ve ra image
    }

    public void startGame()
    {
        if(thread == null)
        {
            isRunning = true;
            thread = new Thread(this);
            thread.start();
        }
    }

    @Override
    public void run()
    {
        long FPS = 80;
        long period = 1000 * 1000000 / FPS; // chu ki lam tuoi cua game
        long beginTime;
        long sleepTime;

        beginTime = System.nanoTime();

//        int a = 1;
//        System.out.println("GamePanel thread start");
        while(isRunning)
        {
//            System.out.println("a = " + (a++));
            // update game
            // render game

            long deltaTime = System.nanoTime() - beginTime; // khoang thoi gian thuc thi game
            sleepTime = period - deltaTime;

            try{
                if(sleepTime > 0)
                    Thread.sleep(sleepTime / 1000000);
                else Thread.sleep(period / 2000000);
            } catch (InterruptedException ex)
            {
            }
            beginTime = System.nanoTime();

        }
    }

    @Override
    public void keyTyped(KeyEvent e) { // callback method: cac phuong thuc duoc mot lop khac goi

    }

    @Override
    public void keyPressed(KeyEvent e) {
        inputManager.processKeyPressed(e.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent e) {
        inputManager.processKeyRelease(e.getKeyCode());
    }
}
