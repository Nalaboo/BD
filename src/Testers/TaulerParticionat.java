package Testers;

import CapaDomini.Tauler;

import java.util.Scanner;

/**
 * Aquest tester mira si la funcionalitat de comprovar si un tauler és partit funciona bé.
 * @author Dani Armengod
 */

public class TaulerParticionat
{
    public static void run()
    {
        System.out.println("De quant vols que sigui el tauler? ");
        Tauler prova = new Tauler(new Scanner(System.in));
        if (!prova.esPartit()) System.out.println("No és partit!! Tot bé.");
        else System.out.println("Malament, és partit.");

    }
}