package com.juegosimson.aa;

import java.util.Scanner;

public class Tablero {

    private int filas;
    private int columnas;
    public String personaje;
    private String enemigo;


    String[][] celdasTablero; //Array celdas tablero
    int posicionFilajugador; // Variable control fila personaje
    int posicionColumjugador; // Variable control columna personaje

    private int vidas;
    private int vidasExtra = 2;

    Scanner seleccion = new Scanner(System.in);


    //CONSTRUCTOR
    public Tablero(String personaje, String enemigo) {
        /* Definimos el tamaño del tablero y cantidad de vidas.
         *Modificandolas podemos variar condiciones
         *Asignamos personaje y enemigo */

        this.personaje = personaje;
        this.enemigo = enemigo;
        filas = 6;
        columnas = 6;
        vidas = 3;


    }

    public int getVidas() {

        return vidas;
    }

    //Tableros

    public void posicionVidasExtra() {

        int n = 1;
        while (n <= vidasExtra) {  //Bucle según el número de pocimas restantes
            int x = (int) (Math.random() * columnas); //Numero aleatorio para columna inicio
            int y = (int) (Math.random() * filas); // Numero aleatorio para fila inicio
            if (celdasTablero[y][x].equals(" L ")) { //Si casilla esta libre la ocupa pocima
                celdasTablero[y][x] = " V ";
                n++;
            }
        }


    }

    public void reiniciarPosicionVidasExtra() {
        //Recorre tablero completo y borra pocimas anteriores
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                if (celdasTablero[i][j].equals(" V ")) {
                    celdasTablero[i][j] = " L ";
                }
            }

        }
    }

    public void crearTablero() {

        celdasTablero = new String[filas][columnas];

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                celdasTablero[i][j] = " L ";
            }
        }

        //Creamos la posicion inicial al azar del personaje

        while (true) {
            int x = (int) (Math.random() * columnas); //Numero aleatorio para columna inicio
            int y = (int) (Math.random() * filas); // Numero aleatorio para fila inicio
            if (celdasTablero[y][x].equals(" L ")) {
                celdasTablero[x][y] = personaje; // Asigna posicion inicial en el tablero al personaje
                posicionFilajugador = x;
                posicionColumjugador = y;
                break;
            }
        }


        //Posiciones aleatorias 8 enemigos

        int k = 0; // Variable usaremos de contador hasta 8 necesarios en tablero
        do {
            int q = (int) (Math.random() * columnas); //Numero aleatorio para columna inicio
            int w = (int) (Math.random() * filas); // Numero aleatorio para fila inicio
            if (celdasTablero[q][w].equals(" L ")) { //Condicion la casilla este libre "L" para asignar enemigo
                celdasTablero[q][w] = enemigo;
                k++;
            } else {
                k = k;
            }
        } while (k != 8);

    }

    public void mostrarTablero() {

        String titulo; // Cadena para indicar en que tablero jugamos  Hommer o Bart

        if (personaje.equals(" B ")) { //Condicional distingue tablero personajes
            titulo = " ***  BART  ***";

        } else {
            titulo = " *** HOMMER ***";
        }
        System.out.println(titulo);

        // Mostramos tablero

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                System.out.print(celdasTablero[i][j]);
            }
            System.out.println();
        }
        System.out.println();


    }


    public void tableroMovimiento(int a, String b) {

        celdasTablero[posicionFilajugador][posicionColumjugador] = " L "; // Asignamos valor L a celda posicion personaje antes de cambiar posicion

        /* Asignamos el movimiento selccionado de casillas a el movimiento WSAD
         * "a" número casillas seleccionado "b" direccion movimiento
         * según la direcion W,S,D,A suma o resta filas (arriba-abajo) o columnas (numero a)
         */

        if (b.equalsIgnoreCase("w")) {
            posicionFilajugador = posicionFilajugador - a; // Resta filas
        } else if
        (b.equalsIgnoreCase("s")) {
            posicionFilajugador = posicionFilajugador + a; // Suma Filas
        } else if (b.equalsIgnoreCase("a")) {
            posicionColumjugador = posicionColumjugador - a; // Resta Columnas
        } else if (b.equalsIgnoreCase("d")) {
            posicionColumjugador = posicionColumjugador + a; // Suma Columnas
        }

        /* Comprobamos posición final si la ocupa enemigo, entonces resta una vida
         * Si la ocupa una pocima suma 1 vida y resta una pocima*/

        switch (celdasTablero[posicionFilajugador][posicionColumjugador]) {
            case " K ":
                System.out.println(personaje + "**** ¡CAZADO! ****");
                vidas--;
                break;
            case " F ":
                System.out.println(personaje + "**** ¡CAZADO! ****");
                vidas--;
                break;
            case " V ":
                System.out.println("**¡¡¡ VIDA EXTRA !!!**");
                vidasExtra--;
                vidas++;
                break;

        }

        //Nueva posicion personaje y mostrar como queda tablero

        celdasTablero[posicionFilajugador][posicionColumjugador] = personaje; //Nueva posicion personaje

        reiniciarPosicionVidasExtra();
        posicionVidasExtra();
        mostrarTablero(); // Tablero con nueva posicion


    }


    public void preguntarMovimiento() {

        // Jugador introduce numero de casillas.
        System.out.print("Numero de casillas desea mover 1,2 o 3: ");
        int numero1 = seleccion.nextInt();

        //Condicional si usuario no introduce 1,2 o 3
        while (numero1 != 1 && numero1 != 2 && numero1 != 3) {
            System.out.println("INTRODUZCA VALOR VALIDO POR FAVOR");
            System.out.print("Numero de casillas desea mover 1,2 o 3: ");
            numero1 = seleccion.nextInt();
        }

        // Jugador introduce direccion de movimiento
        System.out.print("Dirección movimiento (W)Arriba (S)Abajo (A)Izquierda (D) Derecha: ");
        String direccion1 = seleccion.next();

        //Condicional si jugador no introduce caracter movimiento corecto
        while (!direccion1.equalsIgnoreCase("w") && !direccion1.equalsIgnoreCase("s") && !direccion1.equalsIgnoreCase("a") && !direccion1.equalsIgnoreCase("d")) {
            System.out.println("INTRODUZCA VALOR VALIDO POR FAVOR");
            System.out.print("Dirección movimiento (W)Arriba (S)Abajo (A)Izquierda (D) Derecha: ");
            direccion1 = seleccion.next();
        }

        /* TABLERO INFINITO VERTICAL
        Según numero seleccionado y posicion ocupa jugador. Modificamos numero para que en metodo Movimiento asigne fila
        como si tablero fuese infinito (filas van de 0 a 5) (columnas de 0 a 5)
         */
        if (numero1 == 3 && posicionFilajugador == 3 && direccion1.equalsIgnoreCase("s")) { //Fila 3 con 3 movimientos  abajo, aparecera en fila 0
            numero1 = -3;
        } else if (numero1 == 3 && posicionFilajugador == 2 && direccion1.equalsIgnoreCase("w")) { //Fila 2 con 3 movimientos  arriba, aparecera en fila 5
            numero1 = -3;
        } else if (numero1 == 3 && posicionFilajugador == 4 && direccion1.equalsIgnoreCase("s")) { //Fila 4 con 3 movimientos  abajo, aparecera en fila 1
            numero1 = -3;
        } else if (numero1 == 2 && posicionFilajugador == 4 && direccion1.equalsIgnoreCase("s")) { //Fila 4 con 2 movimientos  abajo, aparecera en fila 0
            numero1 = -4;
        } else if (numero1 == 3 && posicionFilajugador == 5 && direccion1.equalsIgnoreCase("s")) { //Fila 5 con 3 movimientos  abajo, aparecera en fila 2
            numero1 = -3;
        } else if (numero1 == 2 && posicionFilajugador == 5 && direccion1.equalsIgnoreCase("s")) { //Fila 5 con 2 movimientos  abajo, aparecera en fila 2
            numero1 = -4;
        } else if (numero1 == 1 && posicionFilajugador == 5 && direccion1.equalsIgnoreCase("s")) { //Fila 5 con 1 movimientos  abajo, aparecera en fila 0
            numero1 = -5;
        } else if (numero1 == 3 && posicionFilajugador == 1 && direccion1.equalsIgnoreCase("w")) { //Fila 1 con 3 movimientos  arriba, aparecera en fila 4
            numero1 = -1;
        } else if (numero1 == 2 && posicionFilajugador == 1 && direccion1.equalsIgnoreCase("w")) { //Fila 1 con 2 movimientos  arriba, aparecera en fila 5
            numero1 = -4;
        } else if (numero1 == 3 && posicionFilajugador == 0 && direccion1.equalsIgnoreCase("w")) { //Fila 0 con 3 movimientos  arriba, aparecera en fila 3
            numero1 = -3;
        } else if (numero1 == 2 && posicionFilajugador == 0 && direccion1.equalsIgnoreCase("w")) { //Fila 0 con 2 movimientos  arriba, aparecera en fila 4
            numero1 = -4;
        } else if (numero1 == 1 && posicionFilajugador == 0 && direccion1.equalsIgnoreCase("w")) { //Fila 0 con 1 movimientos  arriba, aparecera en fila 5
            numero1 = -5;
        }


        // TABLERO INFINITO HORIZONTAL

        if (numero1 == 3 && posicionColumjugador == 2 && direccion1.equalsIgnoreCase("a")) { //Columna 2 con 3 movimientos  izquierda, aparecera en columna 5
            numero1 = -3;
        }

        if (numero1 == 3 && posicionColumjugador == 3 && direccion1.equalsIgnoreCase("d")) { //Columna 2 con 3 movimientos derecha, aparecera en columna 0
            numero1 = -3;
        }

        if (numero1 == 3 && posicionColumjugador == 1 && direccion1.equalsIgnoreCase("a")) { //Columna 1 con 3 movimientos  izquierda, aparecera en columna 4
            numero1 = -3;
        } else if (numero1 == 2 && posicionColumjugador == 1 && direccion1.equalsIgnoreCase("a")) { //Columna 1 con 2 movimientos  izquierda, aparecera en columna 5
            numero1 = -4;
        } else if (numero1 == 3 && posicionColumjugador == 0 && direccion1.equalsIgnoreCase("a")) { //Columna 0 con 3 movimientos  izquierda, aparecera en columna 3
            numero1 = -3;
        } else if (numero1 == 2 && posicionColumjugador == 0 && direccion1.equalsIgnoreCase("a")) { //Columna 0 con 2 movimientos  izquierda, aparecera en columna 4
            numero1 = -4;
        } else if (numero1 == 1 && posicionColumjugador == 0 && direccion1.equalsIgnoreCase("a")) { //Columna 0 con 1 movimientos  izquierda, aparecera en columna 4
            numero1 = -5;

        } else if (numero1 == 3 && posicionColumjugador == 4 && direccion1.equalsIgnoreCase("d")) { //Columna 4 con 3 movimientos derecha, aparecera en columna 1
            numero1 = -3;
        } else if (numero1 == 2 && posicionColumjugador == 4 && direccion1.equalsIgnoreCase("d")) { //Columna 4 con 2 movimientos  derecha, aparecera en columna 0
            numero1 = -4;
        } else if (numero1 == 3 && posicionColumjugador == 5 && direccion1.equalsIgnoreCase("d")) { //Columna 5 con 3 movimientos derecha, aparecera en columna 2
            numero1 = -3;
        } else if (numero1 == 2 && posicionColumjugador == 5 && direccion1.equalsIgnoreCase("d")) { //Columna 5 con 2 movimientos  derecha, aparecera en columna 1
            numero1 = -4;
        } else if (numero1 == 1 && posicionColumjugador == 5 && direccion1.equalsIgnoreCase("d")) { //Columna 5 con 1 movimientos  derecha, aparecera en columna 0
            numero1 = -5;

        }
        // CASILLAS MOVIMIENTO PROHIBIDO COLUMNA (5 FILA 0 MOVIMIENTO ARRIBA) (FILA 5 COLUMNA 0 MOVIMIENTO IZQUIERDA)

        if (posicionFilajugador == 0 && posicionColumjugador == 5 && direccion1.equalsIgnoreCase("w")) { //fila 0 columna 5 movimiento arriba prohibido
            casillaProhibida();
        } else if (posicionFilajugador == 5 && posicionColumjugador == 0 && direccion1.equalsIgnoreCase("a")) { // fila 6 columna 1 movimiento izquierda prohibido
            casillaProhibida();
        } else {
            tableroMovimiento(numero1, direccion1);
        }


    }

    public void casillaProhibida() {
        System.out.println("¡¡¡¡ MOVIMIENTO INFINITO PROHIBIDO EN ESTA CASILLA !!!");
        preguntarMovimiento();
    }

    public void finPartida() {

        if (getVidas() == 0) {
            System.out.println(personaje + " ====== !! HAS PERDIDO !! ====== ");
            System.exit(-1);
        }

    }


}

