import extendss.QueueList;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class ChimneyList {
    private  QueueList<Chimney> chimneyQueueList;
    private BufferedImage chimneyImage, chimneyImage2;
    public static int Size = 6;
    private final int topChimneyY = -350;
    private final int bottomChimneyY = 200;

    public Chimney getChimney(int i) {
        return chimneyQueueList.get(i);
    }
    public int getRandomY(){
        Random random = new Random();
        int a;
        a = random.nextInt(10);

        return a * 30;
    }

    public ChimneyList() {
        try {
            chimneyImage = ImageIO.read(new File("Assest/chimney.png"));

            chimneyImage2 = ImageIO.read(new File("Assest/chimney2.png"));

        }catch (IOException e){}

        chimneyQueueList = new QueueList<Chimney>();
        
        Chimney cn;
        for (int i = 0; i < Size / 2; i++) {

            int deltaY = getRandomY();
            cn = new Chimney(830 + i * 300, bottomChimneyY + deltaY, 74,400);
            chimneyQueueList.push(cn);

            cn = new Chimney(830 + i * 300,topChimneyY + deltaY,74,400);
            chimneyQueueList.push(cn);
        }
    }
    public void resetChimney(){
        chimneyQueueList = new QueueList<Chimney>();

        Chimney cn;
        for (int i = 0; i < Size / 2; i++) {

            int deltaY = getRandomY();

            cn = new Chimney(830 + i * 300, bottomChimneyY + deltaY, 74,400);
            chimneyQueueList.push(cn);

            cn = new Chimney(830 + i * 300,topChimneyY + deltaY,74,400);
            chimneyQueueList.push(cn);
        }
    }
    public  void update2(){
        for (int i = 0; i< Size; i++) {
            chimneyQueueList.get(i).update2();
        }
        queue();
    }
    public void update(){
        for (int i = 0; i< Size; i++) {
            chimneyQueueList.get(i).update();
        }
           queue();
    }
    public void queue(){
        if (chimneyQueueList.get(0).getPosX() < -74) {
            int deltaY = getRandomY();
            Chimney cn;
            cn = chimneyQueueList.pop();
            cn.setPosX(chimneyQueueList.get(4).getPosX()+ 300);
            cn.setPosY(bottomChimneyY + deltaY);
            cn.setBehindBird(false);
            chimneyQueueList.push(cn);

            cn = chimneyQueueList.pop();
            cn.setPosX(chimneyQueueList.get(4).getPosX());
            cn.setPosY(topChimneyY + deltaY);
            cn.setBehindBird(false);
            chimneyQueueList.push(cn);
        }
    }
    public void paint(Graphics2D g2){
        for (int i = 0; i < Size; i++) {
            if (i % 2 == 0) {
                g2.drawImage(chimneyImage, (int) chimneyQueueList.get(i).getPosX(), (int) chimneyQueueList.get(i).getPosY(), null);
            }else {
                g2.drawImage(chimneyImage2, (int) chimneyQueueList.get(i).getPosX(), (int) chimneyQueueList.get(i).getPosY(), null);

            }
        }
    }
}
