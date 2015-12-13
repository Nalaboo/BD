package Testers;
import CapaDomini.ControladorEstadisticas;
import CapaDomini.ControladorUsuari;
import CapaDomini.Usuari;
import CapaPersistencia.CapaPersistencia;

import java.util.Scanner;

/**
 * Created by Maria on 18/11/2015.
 */
public class Prueba {
    public static void main(String[] args) {
        System.out.println("Estadistiques");
        Scanner capt = new Scanner(System.in);
        Scanner aux = new Scanner(System.in);
        String a = capt.nextLine();
        String s = capt.nextLine();

        while (true) {

            System.out.println("Introdueixi el numero de la consulta que vulgui realitzar:");
            System.out.println("\t- 1: Lectura Estadistiques Usuari");
            System.out.println("\t- 2: Lectura Estadistiques Hidatos");
            System.out.println("\t- 3: Lectura Estadistiques Hidatos resolts per dificultat");
            System.out.println("\t- x: Sortir estadistiques");
            s = capt.nextLine();
            if (s.equals("x")) {
                break;
            }
            else if (s.equals("1")) {
                System.out.println("Introdueixi el idUser i del hidato");
                a = aux.nextLine();
                s = capt.nextLine();
                ControladorEstadisticas.dona_estadistiques_Usuari(Integer.parseInt(a),Integer.parseInt(s));
            }
            else if (s.equals("2")) {
                System.out.println("Introdueixi el idHidato");
                s = capt.nextLine();
                ControladorEstadisticas.dona_estadistiques_Hidato(Integer.parseInt(s));
            }
            else if (s.equals("3")) {
                System.out.println("Introdueixi la dificultat");
                s = capt.nextLine();
                ControladorEstadisticas.dona_estadistiques_agregat(s);
            }
            else System.out.println("Comana no valida");
        }
    }
}
