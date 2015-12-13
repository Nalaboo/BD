package Testers;

import CapaDomini.ControladorEstadisticas;
import CapaDomini.Partida;

import java.util.Scanner;

/**
 * Created by Maria on 07/12/2015.
 */
public class PruebaEstadisticasAdd {

    public static void main(String[] args) {
        System.out.println("Vamos a probar guardar info en BD estadisticas");
        Scanner capt = new Scanner(System.in);
        String s = capt.nextLine();

        while (true) {

            System.out.println("Introdueixi el numero de la consulta que vulgui realitzar:");
            System.out.println("\t- 1: Guardar abandonament");
            System.out.println("\t- 2: Guardar guanyat");
            System.out.println("\t- x: Sortir prova");
            s = capt.nextLine();
            if (s.equals("x")) {
                break;
            }
            else if (s.equals("1")) {
                System.out.println("Introdueixi la partida (canviar valors al tester)");
                //ControladorEstadisticas.registrar_abandonament();
            }
            else if (s.equals("2")) {
                System.out.println("Introdueixi el idHidato");
                //ControladorEstadisticas.registrar_guanyat();
            }
            else System.out.println("Comana no valida");
        }
    }
}
