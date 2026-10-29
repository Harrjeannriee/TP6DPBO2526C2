import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class Logic implements ActionListener, KeyListener {
    int frameWidth = 360;
    int frameHeight = 640;

    int playerStartPosX = frameWidth / 2;
    int playerStartPosY = frameHeight / 2;
    int playerWidth = 34;
    int playerHeight = 24;

    //tambah atribut posisi dan ukuran pipa
    int pipeStartPosX = frameWidth;
    int pipeStartPosY = 0;

    int pipeWidth = 64;
    int pipeHeight = 512;

    //atribut timer
    Timer gameLoop;
    Timer pipesCooldown;
    int gravity = 1;

    int pipeVelocityX = -2;

    View view;
    Image birdImage;
    Player player;

    //menambahkan list pipa dan gambarnya
    Image lowerPipeImage;
    Image upperPipeImage;
    ArrayList<Pipe> pipes;

    public Logic(){
        birdImage = new ImageIcon(getClass().getResource("assets/bird.png")).getImage();
        player = new Player(playerStartPosX, playerStartPosY, playerWidth, playerHeight, birdImage);

        lowerPipeImage = new ImageIcon(getClass().getResource("assets/lowerPipe.png")).getImage();
        upperPipeImage = new ImageIcon(getClass().getResource("assets/upperPipe.png")).getImage();

        System.out.println("Lower pipe: " + lowerPipeImage);
        System.out.println("Upper pipe: " + upperPipeImage);

        System.out.println("Lower width: " + lowerPipeImage.getWidth(null));
        System.out.println("Lower height: " + lowerPipeImage.getHeight(null));
        System.out.println("Upper width: " + upperPipeImage.getWidth(null));
        System.out.println("Upper height: " + upperPipeImage.getHeight(null));

        pipes = new ArrayList<Pipe>();

        pipesCooldown = new Timer(1500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.out.println("Pipa");
                placePipes();
            }
        });
        pipesCooldown.start();

        gameLoop = new Timer(1000/60, this);
        gameLoop.start();
    }

    public void setView(View view){
        this.view = view;
    }

    public Player getPlayer(){
        return player;
    }

    public ArrayList<Pipe> getPipes(){
        return pipes;
    }

    public void placePipes(){
        int openingSpace = frameHeight / 4;
        int randomPosY = -100 - (int)(Math.random() * 150);

        Pipe upperPipe = new Pipe(
                pipeStartPosX, randomPosY,
                pipeWidth, pipeHeight, upperPipeImage
        );
        pipes.add(upperPipe);

        Pipe lowerPipe = new Pipe(
                pipeStartPosX, randomPosY + pipeHeight + openingSpace,
                pipeWidth, pipeHeight, lowerPipeImage
        );
        pipes.add(lowerPipe);

        System.out.println("Jumlah pipa: " + pipes.size());
    }

    public void move(){
        player.setVelocity(player.getVelocity() + gravity);
        player.setPosY(player.getPosY() + player.getVelocity());
        player.setPosY(Math.max(player.getPosY(), 0));

        for(int i = 0; i < pipes.size(); i++){
            Pipe pipe = pipes.get(i);
            pipe.setPosX(pipe.getPosX() + pipeVelocityX);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e){
        move();
        if(view != null){
            view.repaint();
        }
    }

    @Override
    public void keyTyped(KeyEvent e){}

    @Override
    public void keyPressed(KeyEvent e){
        if(e.getKeyCode() == KeyEvent.VK_SPACE){
            player.setVelocity(-10);
        }
    }

    @Override
    public void keyReleased(KeyEvent e){}
}
