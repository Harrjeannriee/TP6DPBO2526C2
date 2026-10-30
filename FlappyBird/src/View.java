import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class View extends JPanel {
    int width = 360;
    int height = 640;

    //deklarasi label gameOver
    private JLabel gameOverLabel;
    //deklarasi label untuk skor
    private JLabel scoreLabel;
    //deklarasi label untuk informasi tekan R
    private JLabel restartLabel;

    private Logic logic;

    public View(Logic logic){
        this.logic = logic;
        setPreferredSize(new Dimension(width, height));
        setBackground(Color.cyan);

        setFocusable(true);
        addKeyListener(logic);

        //untuk label Game Over
        setLayout(null);

        //MEMBUAT LABEL GAMEOVER
        gameOverLabel = new JLabel("GAME OVER!");
        gameOverLabel.setFont(new Font("Arial", Font.BOLD, 30));
        gameOverLabel.setForeground(Color.RED);
        gameOverLabel.setVisible(false);

        //masukkan JPanel
        add(gameOverLabel);

        int labelWidth = gameOverLabel.getPreferredSize().width;
        int labelHeight = gameOverLabel.getPreferredSize().height;

        gameOverLabel.setBounds(0, (height - labelHeight) / 2, width, labelHeight);

        gameOverLabel.setHorizontalAlignment(JLabel.CENTER);

        //MEMBUAT LABEL SCORE
        scoreLabel = new JLabel("Score: 0");
        //aturan tampilan labelnya
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 18));
        scoreLabel.setForeground(Color.WHITE);
        scoreLabel.setVisible(true);

        add(scoreLabel);

        //mengatur posisi label score
        int scoreWidth = scoreLabel.getPreferredSize().width;
        int scoreHeight = scoreLabel.getPreferredSize().height;
        scoreLabel.setBounds((width - scoreWidth) / 2, 10, scoreWidth, scoreHeight);

        //MEMBUAT LABEL RESTART
        restartLabel = new JLabel("Press R to Restart");
        //aturan tampilan
        restartLabel.setFont(new Font("Arial", Font.PLAIN, 15));
        restartLabel.setForeground(Color.WHITE);
        restartLabel.setVisible(false);

        add(restartLabel);

        //posisi label restart
        int restartWidth = restartLabel.getPreferredSize().width;
        int restartHeight = restartLabel.getPreferredSize().height;

        restartLabel.setBounds(0, (height - restartHeight) / 2 + 40, width, restartHeight);

        restartLabel.setHorizontalAlignment(JLabel.CENTER);
    };

    //method untuk memperbaharui score game
    public void updateScore(int score){
        scoreLabel.setText("Score: " + score);

        int scoreWidth = scoreLabel.getPreferredSize().width;
        int scoreHeight = scoreLabel.getPreferredSize().height;

        scoreLabel.setBounds((width - scoreWidth) / 2, 10, scoreWidth, scoreHeight);

        repaint();
    }

    //method untuk mengatur visibilitas label Game Over
    public void showGameOver(boolean visible){
        gameOverLabel.setVisible(visible);
        restartLabel.setVisible(visible);
        repaint();
    }


    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g){
        Player player = logic.getPlayer();
        if(player != null){
            g.drawImage(player.getImage(), player.getPosX(), player.getPosY(), player.getWidth(), player.getHeight(), null);
        }

        ArrayList<Pipe> pipes = logic.getPipes();
        if(pipes != null){
            for(int i = 0; i < pipes.size(); i++){
                Pipe pipe = pipes.get(i);

                g.drawImage(pipe.getImage(), pipe.getPosX(), pipe.getPosY(), pipe.getWidth(), pipe.getHeight(), null);
            }
        }
    }
}
