package CapaDomini;

/**
 * Aquesta classe conté a Usuari i totes les seves funcionalitats
 * @author Dani Armengod
 */
public class Usuari
{
    private int uniqID;
    private String nomUsuari;
    private String contrasenya;
    private String nomReal;

    /**
     * Crea un Usuario vacio
     */
    public Usuari()
    {

        uniqID = -1;
    }

    /**
     * Crea un Usuari amb els valors passats per paràmetres
     * @param id
     * @param nomU
     * @param contra
     * @param nomR
     */
    public Usuari(int id, String nomU, String contra, String nomR)
    {
        uniqID = id;
        nomUsuari = nomU;
        contrasenya = contra;
        nomReal = nomR;
    }

    /**
     * Crea un Usuari igual al passat per paràmetres
     * @param u
     */
    public Usuari(Usuari u)
    {
        uniqID = u.getUniqID();
        nomUsuari = u.getNomUsuari();
        contrasenya = u.getContrasenya();
        nomReal = u.getNomReal();
    }

    /**
     * @return Retorna el UniqID
     */
    public int getUniqID()
    {

        return uniqID;
    }

    /**
     * Modifica el uniqID ficant un enter pasat per paràmetres
     * @param uniqID
     */
    public void setUniqID(int uniqID)
    {

        this.uniqID = uniqID;
    }

    /**
     * @return Retorna la contrasenya
     */
    public String getContrasenya()
    {

        return contrasenya;
    }
    /**
     * Modifica el uniqID ficant la contrasenya pasada per paràmetres
     * @param contrasenya
     */
    public void setContrasenya(String contrasenya)
    {

        this.contrasenya = contrasenya;
    }

    /**
     * @return Retorna el nomUsuari
     */
    public String getNomUsuari()
    {

        return nomUsuari;
    }

    /**
     * Modifica el nomUsuari ficant el nomUsuair passat per paràmetres
     * @param nomUsuari
     */
    public void setNomUsuari(String nomUsuari)
    {

        this.nomUsuari = nomUsuari;
    }

    /**
     * Et Retorna si la contrasenya pasada per parametres es iguala a la Contrasenya
     * @param altraContrasenya
     */
    public boolean validaContrasenya(String altraContrasenya)
    {

        return altraContrasenya.compareTo(contrasenya) == 0;
    }

    /**
     * @return Retorna el NomReal
     */
    public String getNomReal()
    {

        return nomReal;
    }

    /**
     * Modifica el nomReal ficant el nomReal passat per paràmetres
     * @param nomReal
     */
    public void setNomReal(String nomReal)
    {

        this.nomReal = nomReal;
    }
}
