package org.example.FF120;

public class Pocion {

	private String nombre;
	private int curacion;

	public Pocion(String nombre, int cura) {
		this.nombre = nombre;
		this.curacion = cura;
	}

	public int getCura() {
		return curacion;
	}
}