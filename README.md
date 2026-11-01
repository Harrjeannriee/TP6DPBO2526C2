# Penjelasan Program
Program ini merupakan sebuah permainan Flappy Bird di mana program menggunakan bahasa pemrograman Java dan Java Swing sebagai teknologi untuk membangun Graphical User Interface (GUI). Flappy Bird merupakan permainan sederhana yang cara memainkannya yaitu dengan mengendalikan sebuah burung dengan sebuah aturan. Aturan itu meliputi, tidak menabrak pipa atas dan bawah, dan tidak jatuh ke tanah (batas frame bawah). Tujuan permainan ini dibuat yaitu untuk menerapkan konsep pemrograman berorientasi objek (OOP) dalam pengembangan permainan berbasis GUI menggunakan Java Swing. Melalui pengembangan permainan sederhana ini juga saya mencoba mempelajari dan menerapkan interaksi antara pengguna dengan program melalui input keyboard, mengatur pergerakan objek dengan timer, serta memahami bagaimana logika permainan bekerja.

Terkait teknologi yang saya gunakan terdiri dari: Java, sebagai bahasa pemrograman utama; Java Swing, untuk membangun antarmuka grafis seperti JFrame, JPanel, JButton, JLabel; Java Sound API, digunakan untuk memutar background music (BGM) selama permainan berjalan; IntellIj IDEA yang digunakan untuk menulis, mengembangkan, dan menjalankan program.

Dalam permainan Flappy Bird ini, saya membuat beberapa fitur sederhana yaitu:
1. Main menu: menampilkan halaman awal. Terdiri dari judul permainan, tombol start, dan tombol exit.
2. Player Movement: pemain dapat mengendalikan burung dengan menekan tombol SPACE untuk melompat atau terbang.
3. Obstacle: terdapat pipa atas dan bawah yang ditampilkan secara berulang sebagai rintangan selama permainan berlangsung. Aturan, burung tak boleh mengenai obstacle pipa.
4. Score System: bertujuan untuk menampilkan score selama permainan berlangsung. Score dihitung +1 setiap kali burung melewati 1 pipa.
5. Collision Detection: bertujuan untuk mendeteksi tabrakan atau irisan antara burung dengan obstacle atau burung dengan tanah (batas frame bawah).
6. Game Over: tujuannya untuk menghentikan permainan ketika terjadi tabrakan atau irisan antara burung dengan pipa atau dengan tanah.
7. Backgroun Music: sebagai pemanis permainan, diputar ketika permainan dimulai.

class java yang digunakan:
1. App.java: titik awal program, menginisialisasi JFrame, mengatur main menu dan tampilan permainan
2. View.java: mengatur tampilan permainan, mengatur objek burung dan pipa, menampilkan informasi skor dan Game Over
3. Logic.java: mengatur logika utama permainan, pergerakan burung, kemunculan pipa, deteksi tabrakan, perhitungan skor, restart
4. Player.java: merepresentasikan burung yang dikendalikan oleh pemain beserta atributnya.
5. Pipe.java: merepresentasikan objek pipa yang berfungsi sebagai obstacle
6. Sound.java: mengatur pemutaran dan pemberhentian music (BGM)

# Desain Alur Program
1. Program menginisialisasi JFrame sebagai jendela utama, lalu menyiapkan komponen permainan (burung, pipa, tampilan permainan, logika permainan).
2. Selanjutnya, program menampilkan main menu. Terdiri dari judul, tombol start(untuk lanjutkan permainan) dan tombol exit(untuk keluar dari program).
3. Jika tombol start ditekan, program akan memulai permainan dan menampilkan permainan. Timer mulai dijalankan, music mulai diputar.
4. Untuk memainkan permianan, user harus menekan SPACE untuk mengendalikan gerakan burung dan jangan sampai terkena pipa atau tanah.
5. Program akan memeriksa apakah burung tabrakan atau tidak. Selain itu program akan mencatat score yang telah ditentukan setiap kali berhasil melewati pipa. Score +1 untuk setiap melewati pipa.
6. Jika burung tabrakan atau bahkan jatuh ke area bawah frame/tanah, permainan dihentikan. Program akan menampilkan label game over dan informasi restart. Jika ingin melakukan restart, user harus menekan R di keyboard.
7. Ketika R ditekan, itu artinya permainan akan memulai ulang ke awal. Posisi burung, jumlah pipa sebelumnya, skor, semua itu dikembalikan ke posisi semula di mana permainan baru akan dimulai. Setelah itu user baru bisa memainkan permainan kembali.
   
# Dokumentasi
Video demonstrasi Flappy Bird:
[Dokumentasi Video Flappy Bird](Dokumentasi/Dokumentasi_TP6.mp4)

# Sumber Background Music (BGM)
Judul: 8bit Bossa
Author: Joth
Sumber: [OpenGameArt.org – Bossa Nova](https://opengameart.org/content/bossa-nova)
Lisensi: CCO (Public Domain)
