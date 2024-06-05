package org.example.FF120;
import java.util.Random;
import java.util.Scanner;

public class Juego {
	private Personaje jugador;
	private Personaje enemigo;

	public Juego(Personaje jugador, Personaje enemigo) {
		this.jugador = jugador;
		this.enemigo = enemigo;
	}

	public void iniciarCombate() {
		Random rand = new Random();
		Scanner scanner = new Scanner(System.in);

		// Probabilidad de encontrarse con un santuario
		if (rand.nextBoolean()) {
			Santuario.bendecir(jugador);
		} else {
			System.out.println(jugador.getNombre() + " no encontró un santuario.");
		}

		if (rand.nextBoolean()) {
			Santuario.bendecir(enemigo);
		} else {
			System.out.println(enemigo.getNombre() + " no encontró un santuario.");
		}

		int turno = 0;
		while (jugador.getVida() > 0 && enemigo.getVida() > 0) {
			if (turno % 2 == 0) {
				System.out.println("Turno de " + jugador.getNombre());
				boolean accionValida = false;
				while (!accionValida) {
					System.out.println("Elige una acción: 1. Atacar 2. Defenderse 3. Tomar poción");
					int accion = scanner.nextInt();
					scanner.nextLine(); // Limpiar el buffer del scanner

					switch (accion) {
						case 1:
							jugador.atacar(enemigo);
							accionValida = true;
							break;
						case 2:
							jugador.defender();
							accionValida = true;
							break;
						case 3:
							if (jugador.usarPocion()) {
								System.out.println(jugador.getNombre() + " usó una poción y recuperó vida.");
								accionValida = true;
							} else {
								System.out.println("No te quedan pociones.");
							}
							break;
						default:
							System.out.println("Acción no válida, intenta de nuevo.");
							break;
					}
				}
			} else {
				System.out.println("Turno de " + enemigo.getNombre());
				int accionEnemigo = rand.nextInt(3) + 1;

				switch (accionEnemigo) {
					case 1:
						enemigo.atacar(jugador);
						break;
					case 2:
						enemigo.defender();
						break;
					case 3:
						enemigo.usarPocion();
						break;
				}
			}

			// Imprimir la situación de la salud de ambos personajes
			System.out.println(jugador.getNombre() + " tiene " + jugador.getVida() + " puntos de vida.");
			System.out.println(enemigo.getNombre() + " tiene " + enemigo.getVida() + " puntos de vida.");

			turno++;
		}

		declararGanador();
		scanner.close();
	}

	private void declararGanador() {
		if (jugador.getVida() > 0) {
			System.out.println(jugador.getNombre() + " ha ganado el combate");
		} else {
			System.out.println(enemigo.getNombre() + " ha ganado el combate");
		}
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		// Crear enemigo predefinido
		Personaje enemigo = new Guerrero("Enemigo");
		Arma armaEnemigo = new Arma("Espada", "fisico", 5);
		Pocion pocionEnemigo = new Pocion("Poción de Salud", 20);
		enemigo.equiparArma(armaEnemigo);
		enemigo.equiparPocion(pocionEnemigo);

		System.out.println("Bienvenido al juego de combate!");
		System.out.print("Elige el nombre de tu personaje: ");
		String nombre = scanner.nextLine();

		System.out.print("Elige tu clase (1. Guerrero, 2. Mago): ");
		int clase = scanner.nextInt();
		scanner.nextLine(); // Limpiar el buffer del scanner

		Personaje jugador;
		if (clase == 1) {
			jugador = new Guerrero(nombre);
		} else {
			jugador = new Mago(nombre);
		}

		System.out.print("Elige tu arma (1. Espada, 2. Bastón): ");
		int armaEleccion = scanner.nextInt();
		Arma armaJugador;
		if (armaEleccion == 1) {
			armaJugador = new Arma("Espada", "fisico", 5);
		} else {
			armaJugador = new Arma("Bastón", "mágico", 7);
		}
		jugador.equiparArma(armaJugador);

		Pocion pocionJugador = new Pocion("Poción de Salud", 20);
		jugador.equiparPocion(pocionJugador);

		System.out.println("Tu personaje está equipado y listo para el combate!");

		Juego juego = new Juego(jugador, enemigo);
		juego.iniciarCombate();

		scanner.close();
	}
}