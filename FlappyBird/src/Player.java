import java.awt.*;

public class Player {
    private int posX;   //posisi horizontal urung
    private int posY;   //posisi vertikal burung
    private int width;  //lebar burung
    private int height; //panjang burung
    private Image image;    //gambar burung yang akan ditampilkan
    private int velocity = -0;

    public Player(int posX, int posY, int width, int height, Image image){
        this.posX = posX;
        this.posY = posY;
        this.width = width;
        this.height = height;
        this.image = image;

        this.velocity = -0;
    }

    //Getter
    public int getPosX(){
        return posX;
    }
    public int getPosY(){
        return posY;
    }
    public int getWidth(){
        return width;
    }
    public int getHeight(){
        return height;
    }
    public Image getImage(){
        return image;
    }
    public int getVelocity(){
        return velocity;
    }

    //Setter
    public void setPosX(int posX){
        this.posX = posX;
    }
    public void setPosY(int posY){
        this.posY = posY;
    }
    public void setWidth(int width){
        this.width = width;
    }
    public void setHeight(int height) {
        this.height = height;
    }

    public void setImage(Image image) {
        this.image = image;
    }
    public void setVelocity(int velocity){
        this.velocity = velocity;
    }
}
