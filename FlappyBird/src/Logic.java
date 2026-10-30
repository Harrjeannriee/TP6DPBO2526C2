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

    //atribut gameOver
    boolean gameOver = false;   //false berarti game masih berjaln, true game berakhir
    //atribut skor
    int score = 0;
    //atribut sound
    Sound bgm;

    int pipeVelocityX = -2;

    View view;
    Image birdImage;
    Player player;

    //menambahkan list pipa dan gambarnya
    Image lowerPipeImage;
    Image upperPipeImage;
    ArrayList<Pipe> pipes;

    public Logic(){
        bgm = new Sound("assets/bgm.wav");

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

        gameLoop = new Timer(1000/60, this);
    }

    public void setView(View view){
        this.view = view;
    }

    public void startGame(){
        bgm.play();
        gameLoop.start();
        pipesCooldown.start();
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

    //method buat MENDETEKSI TAMBRAKAN KE PIPA
    public void checkCollision(){
        //jika burung jatuh ke batas bawah
        if(player.getPosY() + player.getHeight() >= frameHeight){
            gameOver = true;
        }

        //bikin area yang ditempati burung
        Rectangle playerRect = new Rectangle(
                player.getPosX(), player.getPosY(), player.getWidth(), player.getHeight()
        );

        //cek tabrakan dengan setiap pipa. semua pipa harus diperiksa dan dianggap game berakhir kalau burung menyentuh pipa
        for(int i = 0; i < pipes.size(); i++){
            Pipe pipe = pipes.get(i);

            //bikin area yang ditempati pipa
            Rectangle pipeRect = new Rectangle(
                    pipe.getPosX(), pipe.getPosY(), pipe.getWidth(), pipe.getHeight()
            );

            //apakah pipa dengan burung saling beririsan? true jika beririsan, false jika tidak beririsan
            if(playerRect.intersects(pipeRect)){
                gameOver = true;
            }
        }

        //jika game over true, hentikan timernya, hentikan perulangannya
        if(gameOver){
            gameLoop.stop();
            pipesCooldown.stop();

            //tampilkan label
            view.showGameOver(true);
            bgm.stop();
        }
    }

    // method untuk RESTART GAME
    public void restartGame(){
        //reset status game ke awal (false berarti game berjalan)
        gameOver = false;

        //balikin posisi burung ke semula
        player.setPosX(playerStartPosX);
        player.setPosY(playerStartPosY);
        player.setVelocity(0);

        //hapus semua pipa yang sebelumnya, kita kembalikan ke kemunculan pipa pertama
        pipes.clear();

        //restart skor
        score = 0;

        view.updateScore(score);

        //menyembunyikan label ketika permainan dimulai label
        view.showGameOver(false);

        bgm.play();

        //jalankan kembali timer permainan dan perulangan kemunculan pipa
        gameLoop.start();
        pipesCooldown.restart();

        //perbaharui tampilannnya
        view.repaint();

    }

    //UNTUK SCORE SETIAP MELEWATI PIPA
    public void checkScore(){
        for(int i = 0; i < pipes.size(); i+= 2){
            Pipe pipe = pipes.get(i);

            if(!pipe.isPassed() && player.getPosX() > pipe.getPosX() + pipe.getWidth()){
                score++;
                pipe.setPassed(true);
                view.updateScore(score);
            }
        }
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
        //move dan chollision hanya dijalankan selama game belum berakhir
        if(!gameOver){
            move();
            checkCollision();

            if(!gameOver){
                checkScore();
            }
        }

        if(view != null){
            view.repaint();
        }
    }

    @Override
    public void keyTyped(KeyEvent e){}

    @Override
    public void keyPressed(KeyEvent e){
        //jika game over true dan menekan tombol R pada keyboard, maka panggil method restart
        if(gameOver && e.getKeyCode() == KeyEvent.VK_R){
            restartGame();
        }

        //jika game belum game over, dan menekan space
        if(!gameOver && e.getKeyCode() == KeyEvent.VK_SPACE){
            player.setVelocity(-10);
        }
    }

    @Override
    public void keyReleased(KeyEvent e){}


}
