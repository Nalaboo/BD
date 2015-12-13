package Testers;

import CapaDomini.ControladorLogin;
import CapaDomini.Usuari;
import CapaPersistencia.GestorUsuari;

import java.util.Scanner;

/**
 * Aquesta classe testeja les funcionalitats d'un Perfil
 * @author Maria Vives
 */
public class DriverGestioPerfil
{
    public static void main()
    {
        while (true)
        {
            System.out.println("Introdueix una comana:");
            System.out.println("\t- 1: Login Usuari");
            System.out.println("\t- 2: Crear Usuari");
            System.out.println("\t- 3: Entrar a al creador de Hidatos");
            System.out.println("\t- x: Sortir de Gestio de Perfil");
            Scanner capt = new Scanner(System.in);
            String s = capt.nextLine();
            if (s.equals("x"))
            {
                break;
            }
            else if (s.equals("1"))
            {
                System.out.println("Siusplau, introdueix el teu nom de usuari i contrassenya:");
                while (true)
                {
                    System.out.println("Nom Usuari:");
                    s = capt.nextLine();
                    System.out.println("Contrassenya:");
                    String s2 = capt.nextLine();
                    boolean b = ControladorLogin.fesLogin(s,s2);
                    if(b) break;
                    else System.out.print("Nom de usuari o contrassenya incorrectes");

                }
                System.out.print("Loguin Realitzat correctament");
            }
            else if (s.equals("2"))
            {
                while (true)
                {
                    System.out.println("Siusplau, introdueix nom usuari a crear:");
                    capt = new Scanner(System.in);
                    s = capt.nextLine();
                    if (GestorUsuari.existeixUsuari(s))
                    {
                        System.out.println("Nom de usuari ja repetit, introdueix un altre nom.");
                    }
                    else break;
                }
                System.out.println("Siusplau, introdueix nom Real:");
                String s2 = capt.nextLine();
                System.out.println("Siusplau, introdueix Contrassenya:");
                String s3 = capt.nextLine();
                Usuari u = new Usuari(-1, s, s3, s2);
                int i = GestorUsuari.creaUsuari(u);
                System.out.println("Usuari creat correctament, la id assignada a la BD es: " + i );
            }
            else if (s.equals("3"))
            {
                DriverCreadorHidatos.main();
            }
            else s.equals("Comana no valida");
        }
    }
}
