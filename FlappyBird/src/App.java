import javax.swing.*;
import java.awt.*;

public class App{
    public static void main(String[] args){
        JFrame frame = new JFrame("FlappyBird");    //Membuat jendela game
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   //menutup program ketika jendela ditutup
        frame.setSize(360, 640);    //mengatur ukuran jendela
        frame.setLocationRelativeTo(null);  //menenpatkan jendela di tengah layar
        frame.setResizable(false);  //mencegah ukuran jendela diubah

        //MEMBUAT CARD LAYOUT
        CardLayout cardlayout = new CardLayout();
        JPanel container = new JPanel(cardlayout);

        //MEMBUAT OBJEK GAME
        Logic logika = new Logic();
        //buat objek view
        View tampilan = new View(logika);
        logika.setView(tampilan);

        //MEMBUAT MAIN MENU
        JPanel mainMenu = new JPanel();
        mainMenu.setPreferredSize(new Dimension(360, 640));
        mainMenu.setLayout(new BoxLayout(mainMenu, BoxLayout.Y_AXIS));
        mainMenu.setBackground(Color.CYAN);

        //Label untuk di main menu
        JLabel title = new JLabel("FLAPPY BIRD");
        title.setFont(new Font("Arial", Font.BOLD, 32));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        //Membuat tombol start
        JButton startButton = new JButton("START");
        startButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        //membuat tombol exit
        JButton exitButton = new JButton("EXIT");
        exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        //Masukkan komponen ke main menu
        mainMenu.add(Box.createVerticalGlue());
        mainMenu.add(title);
        mainMenu.add(Box.createVerticalStrut(40));
        mainMenu.add(startButton);
        mainMenu.add(Box.createVerticalStrut(15));
        mainMenu.add(exitButton);
        mainMenu.add(Box.createVerticalGlue());

        //masukkan kedua panel ke cardlayout
        container.add(mainMenu, "MENU");
        container.add(tampilan, "GAME");

        frame.add(container);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);     //menampulkan jendela ke layar

        //tombol start
        startButton.addActionListener(e -> {
            cardlayout.show(container, "GAME");
            logika.startGame();

            SwingUtilities.invokeLater(() -> tampilan.requestFocusInWindow() );

        });

        //tombol exit
        exitButton.addActionListener(e -> {
            System.exit(0);
        });
    }
}