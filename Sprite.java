import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class Sprite {
        private Position position;
        private String IMGstring;
        private Boolean inStorage;

        public Sprite(Position position, String IMGstring) {
                this.position = position;
                this.IMGstring = IMGstring;
                this.inStorage = false;
        }

        public Position getPosition() {
                return position;
        }

        //SetPosition()?
        public void setPosition() {

        }

        public String getIMGstring() {
                return IMGstring;
        }

        public void setIMGstring(String IMGstring) {
                this.IMGstring = IMGstring;
        }
        //Get och Set med Boolean?


        //Skapa metoden drawImage() som använder ImageIO.read och g.drawImage som i drawPanel
        void drawImage(){

                }
        }
