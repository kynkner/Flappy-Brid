import extendss.Objects;
import sound.SoundPlayer;

import java.awt.*;
import java.io.File;

public class Bird extends Objects {
    private float vt = 0;
    private boolean isFlying = false;
    private final Rectangle rectangle;
    private boolean islive = true;
    public SoundPlayer flapSound, bupSound, getMoneySound;
    public Bird(int x ,int y, int w, int h){
        super(x, y, w, h);
        rectangle = new Rectangle(x, y ,w, h);

        flapSound = new SoundPlayer(new File("Assest/sound/fap.wav"));
        bupSound = new SoundPlayer(new File("Assest//sound/preview_4.wav"));
        getMoneySound = new SoundPlayer(new File("Assest/sound/getpoint.wav"));

    }

    public void setIslive(boolean islive) {
        this.islive = islive;
    }

    public boolean getIslive() {
        return islive;
    }

    public void setVt(float vt){
        this.vt = vt;
    }
    public  void update(long deltaTime){
        vt += FlappyBride.g;
        this.setPosY(this.getPosY() + vt);
        this.rectangle.setLocation((int) this.getPosX(), (int)this.getPosY());
        if (vt < 0) isFlying = true;
        else isFlying = false;
    }
    public  void fly(){
        vt = -3;
        flapSound.play();
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public boolean getIsFlying(){
        return isFlying;
    }
}
