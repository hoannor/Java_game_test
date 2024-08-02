package src.effect;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Animation {
    private String name;

    private boolean isRepeated;

    private ArrayList<FrameImage> frameImages; // mot list chua cac phan tu la frameImage
    private  int currentFrame; // luu Image hien tai dang duoc ve tren man hinh

    private ArrayList<Boolean> ignoreFrames; // trong 1 animation co mot so anh duoc luoc bo va bang nay de luu gia tri cac anh nao duoc luoc bo trong mot qua trinh lap

    private ArrayList<Double> delayFrames; // chua cac thoi gian delay(thoi gian ton tai cua frame do) giua cac frames
    private long beginTime;

    private boolean drawRectFrame; // bien test de co the phan biet hinh anh(ve ra mot cai khung bao quanh subImage cua minh de de nhan biet)

    public Animation()
    {
        // khoi tao cac bien va list cua Animation
        delayFrames = new ArrayList<Double>();
        beginTime = 0;
        currentFrame = 0;

        ignoreFrames = new ArrayList<Boolean>();

        frameImages = new ArrayList<FrameImage>();

        drawRectFrame = false;

        isRepeated = true;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getRepeated() {
        return isRepeated;
    }

    public void setRepeated(boolean repeated) {
        isRepeated = repeated;
    }

    public ArrayList<FrameImage> getFrameImages() {
        return frameImages;
    }

    public void setFrameImages(ArrayList<FrameImage> frameImages) {
        this.frameImages = frameImages;
    }

    public int getCurrentFrame() {
        return currentFrame;
    }

    public void setCurrentFrame(int currentFrame) {
        if(currentFrame >= 0 && currentFrame < frameImages.size())
            this.currentFrame = currentFrame;
        else this.currentFrame = 0;
    }

    public ArrayList<Boolean> getIgnoreFrames() {
        return ignoreFrames;
    }

    public void setIgnoreFrames(ArrayList<Boolean> ignoreFrames) {
        this.ignoreFrames = ignoreFrames;
    }

    public ArrayList<Double> getDelayFrames() {
        return delayFrames;
    }

    public void setDelayFrames(ArrayList<Double> delayFrames) {
        this.delayFrames = delayFrames;
    }

    public long getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(long beginTime) {
        this.beginTime = beginTime;
    }

    public boolean getDrawRectFrame() {
        return drawRectFrame;
    }

    public void setDrawRectFrame(boolean drawRectFrame) {
        this.drawRectFrame = drawRectFrame;
    }

    public boolean isIgnoreFrame(int id)
    {
        return ignoreFrames.get(id);
    }

    public void setIgnoreFrame(int id)
    {
        if(id >= 0 && id < ignoreFrames.size())
        {
            ignoreFrames.set(id, true);
        }
    }

    public void unIgnoreFrame(int id)
    {
        if(id >= 0 && id < ignoreFrames.size())
        {
            ignoreFrames.set(id, false);
        }
    }

    public void reset()  // de cho animation co the quay lai tu dau giua cac lan chuyen animation
    {
        currentFrame = 0;
        beginTime = 0;

        for(int i = 0; i < ignoreFrames.size(); i++)
        {
            ignoreFrames.set(i, false);
        }
    }

    public Animation(Animation animation) // copy constructor de co the copy tu mot animation nay sang animation khac
    {
        beginTime = animation.beginTime;
        currentFrame = animation.currentFrame;
        drawRectFrame = animation.drawRectFrame;
        isRepeated = animation.isRepeated;

        delayFrames = new ArrayList<Double>();
        for(Double d : animation.delayFrames)
        {
            delayFrames.add(d);
        }

        ignoreFrames = new ArrayList<Boolean>();
        for(boolean b : animation.ignoreFrames)
        {
            ignoreFrames.add(b);
        }

        frameImages = new ArrayList<FrameImage>();
        for(FrameImage f : animation.frameImages)
        {
            frameImages.add(new FrameImage(f));
        }

    }

    public void add(FrameImage frameImage, double timeToNextFrame)
    {
        // 3 mang se co cung size va luu lai thuoc tinh cua cac frame
        ignoreFrames.add(false);
        frameImages.add(frameImage);
        delayFrames.add(new Double(timeToNextFrame));
    }

    public BufferedImage getCurrentImage()
    {
        return frameImages.get(currentFrame).getImage(); // tra ve 1 cai image lay theo currentFrame tu mang frameImags
    }

    public void Update(long currentTime)
    {
        if(beginTime == 0) beginTime = currentTime; // chua duoc xet de hien thi
        else {
            if(currentTime - beginTime > delayFrames.get(currentFrame)) // kiem tra xem frame hien tai da hien thi du thoi gian chua
            {
                nextFrame();
                beginTime = currentTime;
            }
        }
    }

    private void nextFrame()
    {
        if(currentFrame >= frameImages.size() - 1)
        {
            if(isRepeated) currentFrame = 0;
        }
        else
        {
            currentFrame++;
        }
        if(ignoreFrames.get(currentFrame)) nextFrame();
    }

    public boolean isLastFrame() // kiem tra xem animation nay da chay xong chua tuy trong 1 so animation khong duoc lap lai
    {
        if(currentFrame == frameImages.size() - 1)
        {
            return true;
        }
        else return false;
    }

    public void flipAllImage() // cac hinh hien tai deu la tu trai qua phai nen can dao nguoc lai hinh de nhan vat co the chay tu phai qua trai
    {
        for(int i = 0; i < frameImages.size(); i++) // duyet tat cac cac anh co trong frameImage
        {
            BufferedImage image = frameImages.get(i).getImage(); // lay hinh can lat

            // 4  cau lenh duoi dung de co the lat duoc hinh anh bang cach su dung thu vien co san cua Java
            AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
            tx.translate(-image.getWidth(), 0);

            AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
            image = op.filter(image, null);

            frameImages.get(i).setImage(image); // set lai hinh anh sau khi da lat
        }
    }

    public void draw(int x, int y, Graphics2D g2)
    {
        BufferedImage image = getCurrentImage();

        g2.drawImage(image, x - image.getWidth() / 2, y - image.getHeight() / 2, null);
        if(drawRectFrame)
        {
            g2.drawRect(x - image.getWidth() / 2, y - image.getHeight() / 2, image.getWidth(), image.getHeight());
        }
    }

}
