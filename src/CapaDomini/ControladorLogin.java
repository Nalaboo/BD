package CapaDomini;

import CapaPersistencia.GestorUsuari;

/**
 * Aquesta classe es dedica a gestionar les accions que pot fer un Usuari
 * @author Maria Vives
 */

public class ControladorLogin
{
    private static Usuari usuariActual;


    /**
     *
     * @param nomUsuari
     * @param contrasenya
     * @return Veridica si la contrasenya es la que correspon al Usuari amb el seu nom
     */
    public static boolean fesLogin(String nomUsuari, String contrasenya)
    {

        Usuari u = GestorUsuari.donaUsuari(nomUsuari);
        if (u == null) return false;
        if (!u.validaContrasenya(contrasenya)) return false;

        //si passa totes les comprovacions
        usuariActual = u;
        return true;
    }

    /** @return Retorna el UsuariActual
     */
    public static Usuari getUsuariActual()
    {

        return usuariActual;
    }
}