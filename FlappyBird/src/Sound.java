import javax.sound.sampled.*;
import java.io.IOException;

public class Sound {
    //clip itu objek yang dipake buat memuat dan memutar audio
    private Clip clip;

    //constructor sound
    public Sound(String filePath) {
        try {
            //membuat variabel bernama audioStream dengan tipe data AudioInputStream.
            // Fungsi: buat menampung aliran data audio
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(
                    getClass().getResource(filePath)
            );

            //variabel clip diisi dengan objek yang akan digunakan untuk memuat dan memutar audio
            clip = AudioSystem.getClip();
            //memuat data audio dari audioStream ke dalam clip
            clip.open(audioStream);

        }
        //menangani exception dalam satu blok catch
        catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            //menampilkan informasi kesalahan, supaya bisa mengetahui penyebab audio gagal diputar
            e.printStackTrace();
        }
    }
    
    public void play() {
        if (clip != null) {
            clip.setFramePosition(0);
            clip.loop(Clip.LOOP_CONTINUOUSLY);  //putar audio secara berulang tanpa batas sampai dihentikan
        }
    }

    public void stop() {
        if (clip != null) {
            clip.stop();
        }
    }
}