package juego;

import java.awt.Image;
import java.util.Random;

import entorno.Entorno;
import entorno.Herramientas;

public class Portal {
	private double x;
	private double y;
	private double xf;
	private double yf;
	private long startTime;
	private static final int APPEAR_TIME = 2000; // 2 segundos
	private Image imagen;
	private Image imagenF;

	public Portal(double x, double y) {
		this.x = x;
		this.y = y;
		this.xf = x;
		this.yf = y;
		this.startTime = System.currentTimeMillis();
//        this.imagen = Herramientas.cargarImagen("styles/portal.jpeg");
		this.imagen = Herramientas.cargarImagen("styles/spawner.png");
		this.imagenF = Herramientas.cargarImagen("styles/flame.png");
	}

	public void dibujar(Entorno entorno) {
//        entorno.dibujarImagen(imagen, x, y-14, 0,0.5);
		entorno.dibujarImagen(imagen, x, y, 0, 1);
		this.yf -= 1 * 0.25;
		entorno.dibujarImagen(imagenF, xf - 20, yf, 0.5,1);
		entorno.dibujarImagen(imagenF, xf + 15, yf - 2, 0.5,1.2);
		entorno.dibujarImagen(imagenF, xf + 1, yf + 12, 0.5,1.5);
		entorno.dibujarImagen(imagenF, xf - 10, yf - 8, 0.5,1.3);

	}

	public boolean haPasadoTiempo() {
		return System.currentTimeMillis() - startTime >= APPEAR_TIME;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}
}
