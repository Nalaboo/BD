package Testers;


import CapaDomini.CapaDomini;
import CapaDomini.Tauler;
import CapaDomini.TaulerComplert;
import CapaDomini.ValidadorTauler;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Aquesta Classe testeja la funcionalitat de Generador d'Hidatos
 * @author Oriol Giralt
 */
public class DriverCreadorHidatos
{
    public static void main()
    {
        System.out.println("Generador de Hidatos:");
        while (true)
        {
            System.out.println("Introdueix una comana:");
            System.out.println("\t- 1: Generar Hidato aleatori");
            System.out.println("\t- 2: Generar Hidato manualment");
            System.out.println("\t- x: Sortir generador Hidatos");
            Scanner capt = new Scanner(System.in);
            String s = capt.nextLine();
            if (s.equals("1"))
            {
                capt = new Scanner(System.in);
                System.out.println("Introdueix tamany hidato:");
                int n = capt.nextInt();
                while (n < 3 | n > 10)
                {
                    System.out.println("Tamany no valid, introdueix una altra");
                    n = capt.nextInt();
                }
                System.out.println("Introdueix numero forats:");
                int m = capt.nextInt();
                while ((m < 0 || m > (0.3 * n * n)))
                {
                    System.out.println("Forats no valida, introdueix una altra");
                    m = capt.nextInt();
                }
                System.out.println("Introdueix numero valors predeterminats:");
                int x = capt.nextInt();
                while (x < 3 || x > (n * n - 1))
                {
                    System.out.println("Numero de valors no valid, introdueix una altra");
                    x = capt.nextInt();
                }
                System.out.println("Dificultat:");
                while (true)
                {
                    s = capt.nextLine();
                    if (s.equals("facil") || s.equals("normal") || s.equals("dificil") || s.equals("UltraViolence"))
                    {
                        CapaDomini cd = new CapaDomini();
                        Tauler t = new Tauler();
                        TaulerComplert tc = new TaulerComplert();
                        t = cd.creacioTaulerPredeterminat(n, m, x, s, tc);
                        t.pintar_tauler();
                        System.out.print("\n");
                        tc.pintar_tauler();
                        break;
                    }
                    else System.out.println("Dificultat no valida [facil/normal/dificil/UltraViolence]");
                }

            }
            else if (s.equals("2"))
            {
                capt = new Scanner(System.in);
                System.out.println("S'utilitzara arxius predeterminats per aquesta fase de testeig");
                Scanner scanner;
                String ar;
                while (true)
                {
                    System.out.println("Selecciona arxiu:[1/2/3/4/5]");
                    s = capt.nextLine();
                    if (s.equals("1"))
                    {
                        ar = "./DATA/hidato1";
                        break;
                    }
                    else if (s.equals("2"))
                    {
                        ar = "./DATA/hidato2";
                        break;
                    }
                    else if (s.equals("3"))
                    {
                        ar = "./DATA/hidato3";
                        break;
                    }
                    else if (s.equals("4"))
                    {
                        ar = "./DATA/hidato4";
                        break;
                    }
                    else if (s.equals("5"))
                    {
                        ar = "./DATA/hidato5";
                        break;
                    }
                    else if (s.equals("6"))
                    {
                        ar = "./DATA/hidato6";
                        break;
                    }
                    else System.out.println("Comana no valida");
                }
                try
                {
                    scanner = new Scanner(new File(ar));
                }
                catch (FileNotFoundException e)
                {
                    System.out.println(System.getProperty("user.dir"));
                    throw new RuntimeException("Arxiu no trobat", e);

                }
                Tauler t = new Tauler(scanner);
                TaulerComplert tc = new TaulerComplert();
                int i = ValidadorTauler.validarTauler(t, tc);
                t.pintar_tauler();
                System.out.print("\n");
                if (i == 0)
                {
                    System.out.println("Valid amb sol. unica");
                    tc.pintar_tauler();
                }
                else if (i == 1) System.out.println("Error, no conte el valor minim o el maxim");
                else if (i == 2) System.out.println("Error, els valor no estan ben posats");
                else if (i == 3) System.out.println("Error, el hidato ja esta resolt");
                else if (i == 4) System.out.println("Error, no te solucio possible");
                else if (i == 5) System.out.println("Error de solucio multiple, tauler no valid");


            }
            else if (s.equals("x")) break;
            else System.out.println("Comana no valida");
        }
    }
}
