/**
 * @author JUAN SENEN GARCIA SAEZ
 * @date 14-12-2021
 * @version 1.0
 */


import com.juegosimson.aa.Tablero;

import java.util.Scanner;

public class Main {


    public static void main(String[] args) {

        Scanner seleccion = new Scanner(System.in);

        //BART Jugador 1

        Tablero tablero1 = new Tablero(" B ", " K "); //Creamos objeto tablero para el jugador1
        tablero1.crearTablero(); // Crear tablero inicial jugador 1
        tablero1.posicionVidasExtra(); // Posiciona vidas extra ugador 1

        //HOMMER Jugador 2

        Tablero tablero2 = new Tablero(" H ", " F "); //Creamos objeto tablero para jugador 2
        tablero2.crearTablero(); // Crear tablero inicial jugador 2
        tablero2.posicionVidasExtra(); // Posicion vidas extra jugador 2


        do { // Iniciamos un ciclo continuo hasta que pierda un jugador

            // TURNO JUGADOR 1

            tablero1.mostrarTablero();

            System.out.println("Mueve -> " + tablero1.personaje);// Indicamos turno jugador
            System.out.println("Te quedan :" + tablero1.getVidas() + " vidas \n"); // Vidas restantes

            tablero1.preguntarMovimiento();
            tablero1.finPartida(); // Consulta vidas para fin juego

            System.out.println("Asi queda tablero para siguiente movimiento. ( PULSA INTRO )");
            seleccion.nextLine();

            // TURNO JUGADOR 2

            tablero2.mostrarTablero();

            System.out.println("Mueve -> " + tablero2.personaje);//Indica jugador activo para facilitar uso
            System.out.println("Te quedan :" + tablero2.getVidas() + " vidas \n");


            tablero2.preguntarMovimiento();
            tablero2.finPartida(); // Consulta vidas para fin juego

            System.out.println("Asi queda tablero para siguiente movimiento. ( PULSA INTRO )");
            seleccion.nextLine();


        } while (true);
    }
}


