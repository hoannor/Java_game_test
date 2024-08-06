package src.userinterface;

import src.effect.Animation;
import src.effect.FrameImage;

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
    FrameImage frame1, frame2, frame3;
    Animation anim;

    public GamePanel() {
        inputManager = new inputManager();

        try {
            BufferedImage image = ImageIO.read(new File("data/megasprite.png"));
            BufferedImage image1 = image.getSubimage(529, 38, 100, 100);
            frame1 = new FrameImage("frame1", image1);
            BufferedImage image2 = image.getSubimage(616, 38, 100, 100);
            frame2 = new FrameImage("frame2", image2);
            BufferedImage image3 = image.getSubimage(700, 38, 100, 100);
            frame3 = new FrameImage("frame3", image3);

            anim = new Animation();
            anim.add(frame1, 200 * 1000000);
            anim.add(frame2, 200 * 1000000);
            anim.add(frame3, 200 * 1000000);

        } catch (IOException ex) {
            ex.printStackTrace(); // in ra loi tren console
        }
    }

    @Override
    public  void paint(Graphics g)
    {
        g.setColor(Color.white);
        g.fillRect(0, 0, GameFrame.SCREEN_WIDTH, GameFrame.SCREEN_HEIGHT); // tao ra mot vung contain trong frame

        Graphics2D g2 = (Graphics2D) g;

        anim.Update(System.nanoTime());
        anim.draw(100, 130, g2);
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
