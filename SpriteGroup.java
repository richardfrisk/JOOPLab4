import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class SpriteGroup implements drawableSprite {
    ArrayList<Sprite> sprites = new ArrayList<>();

    public void addSprite(Sprite sprite) {
        sprites.add(sprite);
    }

    public void removeSprite(Sprite sprite) {
        sprites.remove(sprite);
    }

    public ArrayList<Sprite> getSprites() {
        return sprites;
    }

    public Sprite getSprite(Sprite sprite) {
        return sprites.get(getSprites().indexOf(sprite));
    }

    @Override
    public void drawImage(Graphics g) {
        for (Sprite sprite : sprites) {
         //   sprite.drawImage(g);
        }
    }
}


