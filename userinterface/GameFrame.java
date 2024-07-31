package userinterface;

import javax.swing.*;
import java.awt.*;

// khung ban dau cua ung dung
public class GameFrame  extends JFrame {
    public static final int SCREEN_WIDTH = 1000;
    public static final int SCREEN_HEIGHT = 600;
    public GameFrame(){

        Toolkit toolkit = this.getToolkit(); // lay size cua man hinh may tinh
        Dimension dimension = toolkit.getScreenSize(); // dat size cua man hinh may tinh vao trong bien dimension
        this.setBounds((dimension.width - SCREEN_WIDTH) / 2, (dimension.height - SCREEN_HEIGHT) / 2, SCREEN_WIDTH, SCREEN_HEIGHT); // xet toa do cho ung dung sao cho o giua man hinh bang cach lay trung binh cong

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // dat default bang nut tat frame

    }

    public static void main(String args[])
    {
        GameFrame gameFrame = new GameFrame();
        gameFrame.setVisible(true);// show ra cua frame game
    }
}
