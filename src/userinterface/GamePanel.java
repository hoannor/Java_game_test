package src.userinterface;

import src.effect.Animation;
import src.effect.CacheDataLoader;
import src.effect.FrameImage;
import src.gameobject.Megaman;

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

    private BufferedImage bufImage;
    private Graphics2D bufG2D;

    Megaman megaman = new Megaman(300, 300, 100, 100, 0.1f);

//    FrameImage frame1;

//    BufferedImage image;
//    BufferedImage subImage;
//    FrameImage frame1, frame2, frame3;
//    Animation anim;

    public GamePanel() {
        inputManager = new inputManager(this);

        bufImage = new BufferedImage(GameFrame.SCREEN_WIDTH, GameFrame.SCREEN_HEIGHT, BufferedImage.TYPE_INT_ARGB);
    }

    public void UpdateGame()
    {
        megaman.update();
    }

    public void RenderGame ()
    {
        if(bufImage == null)
        {
            bufImage = new BufferedImage(GameFrame.SCREEN_WIDTH, GameFrame.SCREEN_HEIGHT, BufferedImage.TYPE_INT_ARGB);
        }

        if(bufImage != null)
        {
            bufG2D = (Graphics2D) bufImage.getGraphics();
        }

        if(bufG2D != null)
        {
            bufG2D.setColor(Color.white);
            bufG2D.fillRect(0, 0, GameFrame.SCREEN_WIDTH, GameFrame.SCREEN_HEIGHT);

            // draw game here
            megaman.draw(bufG2D);
        }
    }


    @Override
    public  void paint(Graphics g)
    {
//        g.setColor(Color.white);
//        g.fillRect(0, 0, GameFrame.SCREEN_WIDTH, GameFrame.SCREEN_HEIGHT); // tao ra mot vung contain trong frame
        g.drawImage(bufImage, 0, 0, this);

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
            UpdateGame();
            RenderGame();
            repaint();

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
