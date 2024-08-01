package src.effect;

import java.awt.*;
import java.awt.image.BufferedImage;

public class FrameImage {
    private String name;
    private BufferedImage image;

    public FrameImage(String name, BufferedImage image)
    {
        this.name = name;
        this.image = image;
    }

    // note JAVA khong co con tro

    public FrameImage(FrameImage frameImage) // copy structor la tao ra mot ham khoi tao voi 1 tham so khac nhung van khoi tao ra FrameImage do
    {
        image = new BufferedImage(frameImage.getImageWidth(), frameImage.getImageHeight(), frameImage.getImage().getType());

        Graphics g = image.getGraphics(); // khi get 1 graphic tu bat ki mot doi tuong nao ve duoc thi graphic chinh la but de ve len cai image do
        g.drawImage(frameImage.getImage(), 0, 0 , null);
    }

    public void draw(Graphics2D g2, int x, int y)
    {
        g2.drawImage(image, x - image.getWidth() / 2, y - image.getHeight() / 2, null);
    }

    public int getImageWidth()
    {
        return image.getWidth();
    }

    public int getImageHeight()
    {
        return image.getHeight();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public BufferedImage getImage() {
        return image;
    }
}
