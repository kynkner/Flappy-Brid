import extendss.Objects;

import java.awt.*;

public class Chimney extends Objects {
    private final Rectangle rectangle;
    private boolean isBehindBird = false;
    public Chimney(int x, int y,int w, int h){
        super(x, y, w, h);
        rectangle = new Rectangle(x, y ,w, h);
    }
    public  void update(){
        setPosX(getPosX() - 2 );
        rectangle.setLocation((int) this.getPosX(), (int) this.getPosY());
    }
    public  void update2(){
        setPosX(getPosX() - 0.4f );
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public void setBehindBird(boolean behindBird) {
        isBehindBird = behindBird;
    }

    public boolean getIsBehindBird() {
        return isBehindBird;
    }
}
