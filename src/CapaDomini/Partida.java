package CapaDomini;


/**
 * Aquesta classe conté Partida i les seves funcionalitats
 * @author Eduard J. Seoane
 */
public class Partida
{
    private int uniqID;
    private Hidato hidato;
    private Usuari usuari;
    private Tauler taulerProgres;
    private int nCelesResoltes;
    private int numAjudesUtilitzades;
    private boolean esAcabada;
    private int temps;



    /**
     * Afegeix un valor n del Tauler del Hidato i imprimeix el tauler amb la nova modificacio.
     * @param x
     * @param y
     * @param elem
     * @return Si ha pogut afegir
     */

    public int afegir(int x, int y, int elem)
    { //todo arreglar aixo

        System.out.print("TaulerProgres.getTamany(): "); System.out.println(taulerProgres.getTamany());
        System.out.print("X: "); System.out.println(x);
        System.out.print("Y: "); System.out.println(y);
        if (x < 0 | x > taulerProgres.getTamany()-1)
        {
            System.out.println("X no valida, introdueix una altra");
            return 1;
        }

        if (y < 0 | y > taulerProgres.getTamany()-1)
        {
            System.out.println("Y no valida, introdueix una altra");
            return 2;
        }

        if (taulerProgres.buscaCasella(elem) != null)
        {
            System.out.println("Valor ja assignat");
            return 3;
        }
        if (taulerProgres.maximElementPossible() < elem)
        {
            System.out.println("Valor massa gran");
            return 3;
        }
        if (elem < 1)
        {
            System.out.println("Valor massa petit");
            return 3;
        }
        if (hidato.casellaEsOriginal(x,y))
        {
            System.out.print("Aquesta casella no es pot modificar");
            return 0;
        }
        else
        {
            taulerProgres.setCasella(x, y, elem);
            taulerProgres.pintar_tauler();
            return 0;
        }
    }

    public Tauler getTaulerOriginal()
    {
        return hidato.getTauler();
    }

    public TaulerComplert getTaulerComplert()
    {
        return hidato.getTaulerComplert();
    }

    public Usuari getUsuari()
    {
        return new Usuari(usuari);
    }

    public void setUsuari(Usuari u)
    {
        usuari = u;
    }

    public Hidato getHidato()
    {
        return new Hidato(hidato);
    }

    public void setHidato(Hidato h)
    {
        hidato = h;
    }

    public void setTaulerProgres(Tauler t)
    {
        taulerProgres = t;
    }

    public Tauler getTaulerProgres()
    {
        return new Tauler(taulerProgres);
    }

    public int getIDUsuari()
    {
        return usuari.getUniqID();
    }

    public int getIDTaulerProgres()
    {
        return taulerProgres.getUniqID();
    }

    public int getIDHidato()
    {
        return hidato.getUniqID();
    }

    public void setIDHidato(int id)
    {
        hidato.setUniqID(id);
    }

    public void setIDTaulerProgres(int id)
    {
        taulerProgres.setUniqID(id);
    }

    /**
     * Treu un valor n del Tauler del Hidato i imprimeix el tauler amb la nova modificacio.
     * @param x
     * @param y
     * @return si a eliminat be o no
     */
    public int remove(int x, int y)
    {
        if (x < 0 | x > taulerProgres.getTamany()-1)
        {
            System.out.println("X no valida, introdueix una altra");
            return 1;
        }

        if (y < 0 | y > taulerProgres.getTamany()-1)
        {
            System.out.println("Y no valida, introdueix una altra");
            return 2;
        }
        if (hidato.casellaEsOriginal(x,y))
        {
            System.out.print("Aquesta casella no es pot eliminar");
            return 3;
        }
        else
        {
            taulerProgres.setCasella(x, y, 0);
            taulerProgres.pintar_tauler();
            return 0;
        }
    }

    /**
     *
     * @return Retorna UniqID
     */
    public int getUniqID()
    {
        return uniqID;
    }

    /**
     * Modifica UniqID per el passat per paràmetre
     * @param uniqID
     */
    public void setUniqID(int uniqID)
    {
        this.uniqID = uniqID;
    }

    /**
     *
     * @return Retorna nCelesResoltes
     */
    public int getnCelesResoltes()
    {
        return nCelesResoltes;
    }

    /**
     * Modifica nCelesResoltes per el passat per paràmetre
     * @param nCelesResoltes
     */
    public void setnCelesResoltes(int nCelesResoltes)
    {
        this.nCelesResoltes = nCelesResoltes;
    }

    /**
     *
     * @return Retorna NumAjudesUtilitzades
     */
    public int getNumAjudesUtilitzades()
    {
        return numAjudesUtilitzades;
    }

    /**
     * Modifica NumAjudesUtilitzades per el passat per paràmetre
     * @param numAjudesUtilitzades
     */
    public void setNumAjudesUtilitzades(int numAjudesUtilitzades)
    {
        this.numAjudesUtilitzades = numAjudesUtilitzades;
    }

    /**
     *
     * @return Retorna EsAcabada
     */
    public Boolean esAcabada()
    {
        return esAcabada;
    }

    /**
     * Modifica esAcabada per el passat per paràmetre
     * @param esAcabada
     */
    public void setEsAcabada(Boolean esAcabada)
    {
        this.esAcabada = esAcabada;
    }

    public int getTemps() {
        return temps;
    }

    public void setTemps(int temps) {
        this.temps = temps;
    }
    /**
     * Constructora buida
     */
    public Partida()
    {
    }
}
