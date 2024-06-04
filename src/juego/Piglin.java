package juego;

import java.awt.Image;
import java.util.Random;

import entorno.Entorno;
import entorno.Herramientas;

public class Piglin {
	private double x;
	private double y;
	private double ancho;
	private double alto;
	private int direccion;
	private Image imag;

	private double velocidadY;
	private double gravedad;
	private Random random;
	private Hueso hueso;

	public Piglin(double x, double y) {
		this.x = x;
		this.y = y;
		this.ancho = 30;
		this.alto = 50;
		this.direccion = 1;
		this.velocidadY = 0;
		this.gravedad = 0.5;
		this.random = new Random();
		this.imag = Herramientas.cargarImagen("styles/piglin.png");

	}

	public void dibujar(Entorno e) {
		e.dibujarImagen(imag, x, y - 24, 0, 1.8);
		if (direccion == 1) {
			this.imag = Herramientas.cargarImagen("styles/piglin der.png");
		} else {
			this.imag = Herramientas.cargarImagen("styles/piglin.png");
		}
	}

	public void mover(Ladrillo[][] pisos, Entorno e, Piglin[][] otroPiglins) {
	    boolean ensuelo = false; // verifica si el Trex está en el suelo

	    // verifica colisiones con bloques en todos los pisos
	    for (Ladrillo[] piso : pisos) {
	        for (Ladrillo ladrillo : piso) {
	            if (ladrillo != null && colision(ladrillo)) {
	                // rebote para abajo
	                if (velocidadY > 0) { // si está cayendo
	                    y = ladrillo.getY() - 25 - this.alto / 2;
	                    velocidadY = 0;
	                    ensuelo = true; // El Trex está en el suelo
	                }
	            }
	        }
	    }

	    // que no se superpongan...
	    for (Piglin[] pisoPiglin : otroPiglins) {
	        for (Piglin otroPiglin : pisoPiglin) {
	        	 if (otroPiglin != null && otroPiglin != this && colisionConPiglin(otroPiglin)) {
	                 if (this.y < otroPiglin.y) { // Si este Trex está encima del otro
	                     if (velocidadY > 0) { // Si está cayendo
	                         this.y = otroPiglin.y - this.alto; // Ajusta la posición para que no se superponga
	                         velocidadY = 0; // Detén la caída
	                         ensuelo = true; // El Trex está en el suelo (sobre otro Trex)
	                     }
	                 } else {
	                     if (this.direccion == otroPiglin.direccion) {
	                         // Si ambos Trex se mueven en la misma dirección y colisionan, cambiar dirección de uno de ellos
	                         this.direccion = -this.direccion;
	                     }
	                 }
	            }
	        }
	    }

	    // si no está en el suelo, cae
	    if (!ensuelo) {
	        velocidadY += gravedad;
	        y += velocidadY;
	    }

	    // cuando llega a los bordes cambia de direccion
	    if (this.x + this.ancho / 2 >= e.ancho()) {
	        this.direccion = -1;
	    } else if (this.x - this.ancho / 2 <= 0) {
	        this.direccion = 1;
	    }
	    if (this.direccion == 1) {
	        moverderecha();
	    } else {
	        moverizquierda();
	    }
	}

	public void moverderecha() {
		this.x += 0.9;
		this.direccion = 1;
	}

	public void moverizquierda() {
		this.x -= 0.9;
		this.direccion = -1;
	}

	public Hueso getHueso() {
		return this.hueso;
	}

	public void setHueso(Hueso hueso) {
		this.hueso = hueso;
	}

	public void dispararHueso() {
		if (hueso == null && random.nextInt(100) == 2) { // random para que no disparen SIEMPRE
			hueso = new Hueso(this.x, this.y-10, this.direccion);
		}
	}

	private boolean colision(Ladrillo ladrillo) {
		return this.x + this.ancho / 2 > ladrillo.getX() - ladrillo.getAncho() / 2
				&& this.x - this.ancho / 2 < ladrillo.getX() + ladrillo.getAncho() / 2
				&& this.y + this.alto / 2 > ladrillo.getY() - ladrillo.getAlto() / 2
				&& this.y - this.alto / 2 < ladrillo.getY() + ladrillo.getAlto() / 2;
	}

	public boolean colisionConFlecha(Flecha flecha) {
		return flecha != null && flecha.getX() + flecha.getDiametro() / 2 > this.x - this.ancho / 2
				&& flecha.getX() - flecha.getDiametro() / 2 < this.x + this.ancho / 2
				&& flecha.getY() + flecha.getDiametro() / 2 > this.y - this.alto / 2
				&& flecha.getY() - flecha.getDiametro() / 2 < this.y + this.alto / 2;
	}
	
	private boolean colisionConPiglin(Piglin otroPiglin) {
	    return this.x < otroPiglin.x + otroPiglin.ancho &&
	           this.x + this.ancho > otroPiglin.x &&
	           this.y < otroPiglin.y + otroPiglin.alto &&
	           this.y + this.alto > otroPiglin.y;
	}
	
	public boolean colisionConSteve(Steve steve) {
	    return this.x + this.ancho / 2 > steve.getX() - steve.getAncho() / 2 &&
	           this.x - this.ancho / 2 < steve.getX() + steve.getAncho() / 2 &&
	           this.y + this.alto / 2 > steve.getY() - steve.getAlto() / 2 &&
	           this.y - this.alto / 2 < steve.getY() + steve.getAlto() / 2;
	}
	
	
	public double getX() {
		return x;
	}
	public double getY() {
		return y;
	}
	public double getAncho() {
		return ancho;
	}
	public double getAlto() {
		return alto;
	}

}
