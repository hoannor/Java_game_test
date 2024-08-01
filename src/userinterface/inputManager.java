package src.userinterface;


import java.awt.event.KeyEvent;

// xu li cac tinh huong nhan nut cho game
public class inputManager {
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
                System.out.println("You press LEFT");
                break;
            case KeyEvent.VK_RIGHT:
                System.out.println("You press RIGHT");
                break;
            case KeyEvent.VK_ENTER: // enter de co the vao game va chon lua trong menugame
                System.out.println("You press ENTER");
                break;
            case KeyEvent.VK_SPACE: // space de co the nhay
                System.out.println("You press SPACE");
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
                System.out.println("You release LEFT");
                break;
            case KeyEvent.VK_RIGHT:
                System.out.println("You release RIGHT");
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
