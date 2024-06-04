package juego;

import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Hueso {
	private double x;
	private double y;
	private double diametro;
	private double velocidad;
	private Image imag;
	private int direccion; // 1 para derecha, -1 para izquierda

	public Hueso(double x, double y, int direccion) {
		this.x = x;
		this.y = y;
		this.diametro = 25;
		this.velocidad = 2;
//		this.imag = Herramientas.cargarImagen("styles/hueso.png");
		this.imag = Herramientas.cargarImagen("styles/golden axe.png");
		this.direccion = direccion;
	}

	public void dibujar(Entorno e) {
//		e.dibujarCirculo(this.x, this.y, this.diametro, this.color);
		e.dibujarImagen(imag, x, y, x * 0.08, 1.9);
	}

	public void mover() {
		this.x += this.direccion * this.velocidad; // Mover en la dirección correspondiente
	}

	public boolean chocaConEntorno(Entorno e) {
		if (this.x - this.diametro / 2 < 0 || this.x + this.diametro / 2 > e.ancho()) {
			return true;
		}
		return false;
	}

	public boolean colisionConFlecha(Flecha flecha) {
		return flecha != null && this.x + this.diametro / 2 > flecha.getX() - flecha.getDiametro() / 2
				&& this.x - this.diametro / 2 < flecha.getX() + flecha.getDiametro() / 2
				&& this.y + this.diametro / 2 > flecha.getY() - flecha.getDiametro() / 2
				&& this.y - this.diametro / 2 < flecha.getY() + flecha.getDiametro() / 2;
	}

	public boolean colisionConSteve(Steve steve) {
		return steve != null && this.x + this.diametro / 2 > steve.getX() - steve.getAncho() / 2
				&& this.x - this.diametro / 2 < steve.getX() + steve.getAncho() / 2
				&& this.y + this.diametro / 2 > steve.getY() - steve.getAlto() / 2
				&& this.y - this.diametro / 2 < steve.getY() + steve.getAlto() / 2;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public double getDiametro() {
		return diametro;
	}

	public void setX(double x) {
		this.x = x;
	}

	public void setY(double y) {
		this.y = y;
	}
}
