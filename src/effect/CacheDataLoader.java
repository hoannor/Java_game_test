package src.effect;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.security.PublicKey;
import java.util.Hashtable;

public class CacheDataLoader {

    private static CacheDataLoader instance;

    private String framefile = "data/frame.txt";
    private String animationfile = "data/animation.txt";

    // dung bang hashtable de luu 1 bang theo gia tri cap <key, value>
    private Hashtable<String, FrameImage> frameImages;
    private Hashtable<String, Animation> animations;
    private CacheDataLoader() { /// de private de vo hieu hoa ham constructor de khong the tao 1 lop co thuc the bang cau lenh ne
    }

    public static CacheDataLoader getInstance() /// co the goi truc tiep qua class luon khong phai khoi tao mot bien new
    {
        if(instance == null)
        {
            instance = new CacheDataLoader();
        }
        return instance;
        // thuat ngu Single Door: chi co 1 cach khai bao duy nhat cho Class CacheDataLoader chi dung duy nhat mot minh instance
    }

    public void LoadData() throws IOException
    {
        LoadFrame();
        LoadAnimation();
    }

    public void LoadFrame() throws IOException
    {
        frameImages = new Hashtable<String, FrameImage>();

        FileReader fr = new FileReader(framefile);
        BufferedReader br = new BufferedReader(fr);

        String line = null;

        if(br.readLine() == null) // ham readline tra ve string va cho con tro nhay sang dong tiep theo
        {   // =null de kiem tra xem da ket thuc file chua con ="" de kiem tra xem dong do co phai dong trong hay khong
            System.out.println("No data");
            throw new IOException();
        }
        else
        {
            fr = new FileReader(framefile);
            br = new BufferedReader(fr);
            // su dung 2 dong khoi tao tren de dua con tro doc file ve lai dau file sau khi biet file da co du lieu

            while((line = br.readLine()).equals("")); // ham equals de so sanh xau

            int n = Integer.parseInt(line); // chuyen 1 xau sang 1 so nguyen bang ham cua Integer

            for(int i = 0; i < n; i++)
            {
                FrameImage frame = new FrameImage();
                while((line = br.readLine()).equals(""));
                frame.setName(line);

                while((line = br.readLine()).equals(""));
                String[] str = line.split(" ");
                String path = str[1];

                while((line = br.readLine()).equals(""));
                str = line.split(" ");
                int x = Integer.parseInt(str[1]);

                while((line = br.readLine()).equals(""));
                str = line.split(" ");
                int y = Integer.parseInt(str[1]);

                while((line = br.readLine()).equals(""));
                str = line.split(" ");
                int w = Integer.parseInt(str[1]);

                while((line = br.readLine()).equals(""));
                str = line.split(" ");
                int h = Integer.parseInt(str[1]);

                BufferedImage imageData = ImageIO.read(new File(path));
                BufferedImage image = imageData.getSubimage(x, y, w, h); /// lay image theo subImage da lay toa do tu truoc
                frame.setImage(image);

                instance.frameImages.put(frame.getName(), frame);// put frame len tren man hinh bang instance
            }
        }

        br.close(); // dong file lai
    }

    public FrameImage getFrameImage (String name)
    {
        FrameImage frameImage = new FrameImage(instance.frameImages.get(name)); // tach ra mot frameImage moi de lam moi vung tham chieu vi neu nhu co nhieu frameImage thi co the dan den viec bi trung vung du lieu nen lam nhu vay de co the tao ra mot vung du lieu moi de co the xu li thang frameImage hien tai, tranh viec trung vung du lieu va co the su dung lai vung du lieu nay de co the xu li cac frameImage khac tranh viec ton tong bo nho su dung
        return frameImage;
    }

    public void LoadAnimation() throws IOException
    {
        // tuong tu voi loadFrame cac chu thich
        animations = new Hashtable<String, Animation>();

        FileReader fr = new FileReader(animationfile);
        BufferedReader br = new BufferedReader(fr);

        String line = null;

        if(br.readLine() == null)
        {
            System.out.println("No data");
            throw new IOException();
        }
        else
        {
            fr = new FileReader(animationfile);
            br = new BufferedReader(fr);

            while((line = br.readLine()).equals(""));
            int n = Integer.parseInt(line);

            for(int i = 0; i < n; i++)
            {
                Animation animation = new Animation();

                while((line = br.readLine()).equals(""));
                animation.setName(line);

                while((line = br.readLine()).equals(""));
                String[] str = line.split(" ");

                for(int j = 0; j < str.length; j += 2)
                {
                    animation.add(getFrameImage(str[j]), Double.parseDouble(str[j + 1]));
                }

                instance.animations.put(animation.getName(), animation);
            }
        }
        br.close();
    }

    public Animation getAnimation(String name)
    {
        Animation animation = new Animation(instance.animations.get(name));
        return animation;
    }

}
