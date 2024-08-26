package src.userinterface;


import src.gameobject.Megaman;

import java.awt.event.KeyEvent;

// xu li cac tinh huong nhan nut cho game
public class inputManager {

    private GamePanel gamePanel;

    public inputManager(GamePanel gamePanel)
    {
        this.gamePanel = gamePanel;
    }
    public void processKeyPressed(int keyCode)
    {
        switch (keyCode)
        {
            // 4 phim mui ten di chuyen
            case KeyEvent.VK_UP:
                System.out.println("You press UP");
                break;
            case KeyEvent.VK_DOWN:
                System.out.println("You press DOWN");
                break;
            case KeyEvent.VK_LEFT:
//                System.out.println("You press LEFT");
                gamePanel.megaman.setSpeedX(-5);
                break;
            case KeyEvent.VK_RIGHT:
//                System.out.println("You press RIGHT");
                gamePanel.megaman.setSpeedX(5);
                break;
            case KeyEvent.VK_ENTER: // enter de co the vao game va chon lua trong menugame
                System.out.println("You press ENTER");
                break;
            case KeyEvent.VK_SPACE: // space de co the nhay
//                System.out.println("You press SPACE");
                gamePanel.megaman.setSpeedY(-3);
                gamePanel.megaman.setPosY(gamePanel.megaman.getPosY() - 3); // moi cho nhan vat cach mat dat de co the nhay duoc (cung cap van toc ban dau cho doi tuong )

                break;
            case KeyEvent.VK_A: // A de su dung sung
                System.out.println("You press A");
                break;

        }
    }

    public void processKeyRelease(int keyCode)
    {
        switch (keyCode)
        {
            // 4 phim mui ten di chuyen
            case KeyEvent.VK_UP:
                System.out.println("You release UP");
                break;
            case KeyEvent.VK_DOWN:
                System.out.println("You release DOWN");
                break;
            case KeyEvent.VK_LEFT:
//                System.out.println("You release LEFT");
                gamePanel.megaman.setDirection(Megaman.DIR_LEFT); // xac dinh huong cua model nhan vat luc dung chay
                gamePanel.megaman.setSpeedX(0);
                break;
            case KeyEvent.VK_RIGHT:
//                System.out.println("You release RIGHT");
                gamePanel.megaman.setDirection(Megaman.DIR_RIGHT); // xac dinh huong cua model nhan vat luc dung chay
                gamePanel.megaman.setSpeedX(0);
                break;
            case KeyEvent.VK_ENTER: // enter de co the vao game va chon lua trong menugame
                System.out.println("You release ENTER");
                break;
            case KeyEvent.VK_SPACE: // space de co the nhay
                System.out.println("You release SPACE");
                break;
            case KeyEvent.VK_A: // A de su dung sung
                System.out.println("You release A");
                break;

        }
    }
}
