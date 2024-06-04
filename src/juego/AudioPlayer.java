package juego;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import java.io.BufferedInputStream;
import java.io.InputStream;

public class AudioPlayer {
    private Player player;
    private String filePath;

    public AudioPlayer(String filePath) {
        this.filePath = filePath;
    }

    public void play() {
        new Thread(() -> {
            while (true) { // Bucle infinito para la música en bucle
                try {
                    // Utilizar ClassLoader para cargar el archivo desde el directorio src
                    InputStream inputStream = getClass().getClassLoader().getResourceAsStream(filePath);
                    if (inputStream == null) {
                        throw new IllegalArgumentException("File not found: " + filePath);
                    }
                    BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
                    player = new Player(bufferedInputStream);
                    player.play();
                } catch (JavaLayerException | IllegalArgumentException e) {
                    e.printStackTrace();
                    break; // Salir del bucle en caso de error
                }
            }
        }).start();
    }

    public void stop() {
        if (player != null) {
            player.close();
        }
    }
}
