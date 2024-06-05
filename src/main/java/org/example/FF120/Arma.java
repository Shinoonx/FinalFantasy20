package org.example.FF120;

public class Arma {
	private String nombre;
	private String tipo;
	private int poder;

	public Arma(String nombre, String tipo, int poder) {
		this.nombre = nombre;
		this.tipo = tipo;
		this.poder = poder;
	}

	public int getPoder() {
		return poder;
	}
}