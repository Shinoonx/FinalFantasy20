package org.example.FF120;

public abstract class Personaje {
	protected String nombre;
	protected int vida;
	protected int ataque;
	protected int defensa;
	protected Arma arma;
	protected Pocion pocion;
	protected boolean defendiendo = false;

	public Personaje(String nombre, int vida, int ataque, int defensa) {
		this.nombre = nombre;
		this.vida = vida;
		this.ataque = ataque;
		this.defensa = defensa;
	}

	public void equiparArma(Arma arma) {
		this.arma = arma;
	}

	public void equiparPocion(Pocion pocion) {
		this.pocion = pocion;
	}

	public abstract void atacar(Personaje otro);

	public void recibirDaño(int daño) {
		if (defendiendo) {
			daño /= 2;
			defendiendo = false; // Dejar de defender después de recibir el daño
		}
		vida -= daño;
		if (vida < 10 && pocion != null) {
			usarPocion();
		}
	}

	protected boolean usarPocion() {
		if (pocion != null) {
			vida += pocion.getCura();
			pocion = null;
			return true;
		} else {
			return false;
		}
	}

	public void defender() {
		defendiendo = true;
	}

	public int getVida() {
		return vida;
	}

	public String getNombre() {
		return nombre;
	}
}