import pkg2dgamesframework.AFrameOnImage;
import pkg2dgamesframework.Animation;
import pkg2dgamesframework.GameScreen;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class FlappyBride extends GameScreen {
    private BufferedImage birds;
    private final Animation bird_anim;
    public static float g = 0.15f;
    private final Bird bird;
    private final Ground ground;
    private final ChimneyList chimneyList;
    private int Point = 0;

    private final int BEGIN_SCREEN = 0;
    private final int GAMEPLAY_SCREEN = 1;
    private final int GAMEOVER_SCREEN = 2;
    private int CurrentScreen = BEGIN_SCREEN;
    public FlappyBride() {
        super(800, 600);
        try {
            birds = ImageIO.read(new File("Assest/bird_sprite.png"));

        }catch (IOException e){}

        bird_anim = new Animation(70);
        AFrameOnImage f;
        f = new AFrameOnImage(0, 0, 60, 60);
        bird_anim.AddFrame(f);
        f = new AFrameOnImage(60, 0, 60, 60);
        bird_anim.AddFrame(f);
        f = new AFrameOnImage(120, 0, 60, 60);
        bird_anim.AddFrame(f);
        f = new AFrameOnImage(60, 0, 60, 60);
        bird_anim.AddFrame(f);

        bird = new Bird(350, 250, 50, 50);

        ground = new Ground();

        chimneyList = new ChimneyList();
        BeginGame();

    }
    private void resetGame(){
        bird.setPos(350, 250);
        bird.setVt(0);
        bird.setIslive(true);
        Point = 0;
        chimneyList.resetChimney();
    }
    public static void main(String[] args){
        new FlappyBride();
    }

    @Override
    public void GAME_UPDATE(long deltaTime) {
        if(CurrentScreen == BEGIN_SCREEN){
            resetGame();
            bird.setVt(0);

        }else if(CurrentScreen == GAMEPLAY_SCREEN){
            if (!bird.getIsFlying()) bird_anim.Update_Me(deltaTime);
            bird.update(deltaTime);
            ground.Update();

            chimneyList.update();

            for (int i = 0; i < ChimneyList.Size; i++) {
                if (bird.getRectangle().intersects(chimneyList.getChimney(i).getRectangle())){
                    if (bird.getIslive()) bird.bupSound.play();
                    bird.setIslive(false);
                }
            }

            for (int i = 0; i < ChimneyList.Size; i++) {
                if( bird.getPosX() > chimneyList.getChimney(i).getPosX() && !chimneyList.getChimney(i).getIsBehindBird() && i % 2 == 0) {
                    Point ++;
                    bird.getMoneySound.play();
                    chimneyList.getChimney(i).setBehindBird(true);
                }
            }

            if (bird.getPosY() + bird.getH() > ground.getYGround()) CurrentScreen = GAMEOVER_SCREEN;
        }else {

        }
    }

    @Override
    public void GAME_PAINT(Graphics2D g2) {
        g2.setColor(Color.decode("#b8daef"));
        g2.fillRect(0,0,MASTER_WIDTH,MASTER_HEIGHT);
        chimneyList.paint(g2);
        ground.Paint(g2);

        if (bird.getIsFlying())
        bird_anim.PaintAnims((int) bird.getPosX(), (int) bird.getPosY(), birds, g2, 0, -1);
        else
            bird_anim.PaintAnims((int) bird.getPosX(), (int) bird.getPosY(), birds, g2, 0, 0);

        if (CurrentScreen == BEGIN_SCREEN){
            g2.setColor(Color.white);
            g2.drawString("Press space to play game", 200, 300);
        }
        if (CurrentScreen == GAMEOVER_SCREEN){
            g2.setColor(Color.white);
            g2.drawString("Press space turn back begin screen", 200, 300);
        }
        g2.setColor(Color.white);
        g2.drawString("Point: " + Point, 20, 50);


    }

    @Override
    public void KEY_ACTION(KeyEvent e, int Event) {
        if (Event == KEY_PRESSED){

            if (CurrentScreen == BEGIN_SCREEN){
                CurrentScreen = GAMEPLAY_SCREEN;
            }else if (CurrentScreen == GAMEPLAY_SCREEN){
            if (bird.getIslive())bird.fly();
            }else if (CurrentScreen == GAMEOVER_SCREEN){
                CurrentScreen = BEGIN_SCREEN;
            }

        }
    }
}
