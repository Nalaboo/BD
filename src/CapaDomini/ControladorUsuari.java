package CapaDomini;

import CapaPersistencia.GestorUsuari;

/**
 * Aquesta classe es dedica a gestionar les accions que pot fer Usuari
 * @author Oriol Giralt
 */
public class ControladorUsuari
{

    /**
     * Crea un Usuari amb els valors passat per paràmetre.
     * @param nomUsuari
     * @param contrasenya
     * @param nomReal
     * @return retorna si l'ha creat o no
     */
    public static boolean crearUsuari(String nomUsuari, String contrasenya, String nomReal)
    {

        Usuari u = new Usuari();
        u.setNomUsuari(nomUsuari);
        u.setContrasenya(contrasenya);
        u.setNomReal(nomReal);
        return GestorUsuari.creaUsuari(u) != -1;
        /*TODO:
            Controlar les excepcions i el resultat de la operacio.
              ^en principi ja es controla tot lo necessari (basicament si es crea o no l'user) i es deixa morir els errors de base de dades.
         */
    }

    /**
     * lEimina el Usuari que tingui el nomUsuari passat per paràmetre
     * @param nomUsuari
     * @return Retorna si la elimanat o no
     */
    public static boolean eliminarUsuari(String nomUsuari)
    {

        //Retorna true si s'ha borrat l'usuari. False si no s'ha borrat cap usuari. Tira excepcio si s'han borrat multiples.
        return GestorUsuari.eliminaUsuari(nomUsuari);
        /*TODO:
            Si la capa de persistencia tira una excepcio normal en lloc d'una runtime (o controlem la runtime aqui), llavors podem morir "more gracefully" si mai borrem mes d'un usuari... PERO COM TAMPOC PASSARA MAI (GOD BLESS UNIQUE CONSTRAINTS), FUCK IT.
         */
    }
}
