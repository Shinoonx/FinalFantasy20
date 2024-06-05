package org.example.FF120;

public class Santuario {
	public static void bendecir(Personaje personaje) {
		personaje.defensa += 5;
		System.out.println(personaje.getNombre() + " ha recibido una bendición del santuario. Defensa aumentada en 5 puntos.");
	}
}