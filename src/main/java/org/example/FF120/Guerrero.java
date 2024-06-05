package org.example.FF120;

public class Guerrero extends Personaje {
	private int fuerza;

	public Guerrero(String nombre) {
		super(nombre, 150, 10, 13);
		this.fuerza = 8;
	}

	@Override
	public void atacar(Personaje otro) {
		int daño = Math.max(0, ataque + fuerza + (arma != null ? arma.getPoder() : 0) - otro.defensa);
		otro.recibirDaño(daño);
	}
}