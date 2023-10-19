import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Ground {
    private BufferedImage groudImage;
    private  float x1, y1, x2, y2;
    public Ground(){
        try {
            groudImage = ImageIO.read(new File("Assest/ground.png"));
        }catch (IOException e){}

        x1 = 0;
        y1 = 500;
        x2 = x1 + 830;
        y2 = 500;
    }
    public void Update(){
        x1 -= 2;
        x2 -= 2;
        if (x2 < 0) x1 = x2 + 830;
        if (x1 < 0) x2 = x1 + 830;
    }
    public void Update2(){
        x1 -= 0.4f;
        x2 -= 0.4f;
        if (x2 < 0) x1 = x2 + 830;
        if (x1 < 0) x2 = x1 + 830;
    }
    public void Paint(Graphics2D g2){
        g2.drawImage(groudImage, (int) x1, (int) y1, null);
        g2.drawImage(groudImage, (int) x2, (int) y2, null);
    }
    public  float getYGround(){
        return y1;
    }
}
