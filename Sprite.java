import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.awt.image.BufferedImage;


public class Sprite implements drawableSprite {
        protected Position position;
        private String IMGpath;
        private BufferedImage sprite;
        private Boolean inStorage;


        public Sprite(Position position, String IMGpath) {
                this.position = position;
                this.IMGpath = IMGpath;
                this.inStorage = false;
                setImage(IMGpath);
        }


        public String getIMGpath() {
                return IMGpath;
        }
        public void setIMGpath(String IMGpath) {
                this.IMGpath = IMGpath;
        }

        public boolean getStorageState() { return inStorage; }
        public void setStorageState(boolean state) { inStorage = state; }

        public BufferedImage getImage() { return sprite; }


        public void setImage(String path) {
            try {
                sprite = ImageIO.read(DrawPanel.class.getResourceAsStream(path));
            } catch (IOException ex)
            {
                    ex.printStackTrace();
            }
        }

        public void drawImage(Graphics g) {
                if (!inStorage) {
                        int x = (int) Math.round(position.getPosition().getX());
                        int y = (int) Math.round(position.getPosition().getY());
                        g.drawImage(sprite, x, y, null);
                }
        }
}
