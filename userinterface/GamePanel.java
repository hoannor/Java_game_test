package userinterface;

import javax.swing.*;
import java.awt.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GamePanel extends JPanel implements Runnable{

    private Thread thread;
    private boolean isRunning;
    @Override
    public  void paint(Graphics g)
    {
        g.setColor(Color.red);
        g.fillRect(0, 0, GameFrame.SCREEN_WIDTH, GameFrame.SCREEN_HEIGHT); // tao ra mot vung contain trong frame
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

        int a = 1;
//        System.out.println("GamePanel thread start");
        while(isRunning)
        {
            System.out.println("a = " + (a++));
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
}
