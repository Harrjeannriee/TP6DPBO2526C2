import javax.swing.*;

public class App{
    public static void main(String[] args){
        JFrame frame = new JFrame("FlappyBird");    //Membuat jendela game
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   //menutup program ketika jendela ditutup
        frame.setSize(360, 640);    //mengatur ukuran jendela
        frame.setLocationRelativeTo(null);  //menenpatkan jendela di tengah layar
        frame.setResizable(false);  //mencegah ukuran jendela diubah

        Logic logika = new Logic();

        //buat objek view
        View tampilan = new View(logika);
        logika.setView(tampilan);

        tampilan.requestFocus();

        frame.add(tampilan);
        frame.pack();
        frame.setVisible(true);     //menampulkan jendela ke layar

        SwingUtilities.invokeLater(() -> tampilan.requestFocusInWindow() );
    }
}