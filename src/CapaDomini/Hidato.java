package CapaDomini;

import java.util.Scanner;

/**
 * Aquesta classe conte Hidato i totoes les seves funcionalitats
 * @author Oriol Giralt
 */


public class Hidato
{
    private int uniqID;
    private Tauler tauler;
    private TaulerComplert taulerComplert;
    public String dificultat;

    /**Crea un Hidato buit
     */
    public Hidato()
    {

        uniqID = -1;
        tauler = null;
        taulerComplert = null;
        dificultat = null;
    }



    /**
     * Crea un Hidato amb els valors iguals als paràmetres
     * @param tauler
     * @param taulerComplert
     * @param dificultat
     */
    public Hidato(Tauler tauler, TaulerComplert taulerComplert, String dificultat)
    {

        this.uniqID = -1;
        this.tauler.move(tauler);
        this.taulerComplert.move(taulerComplert);
        this.dificultat = dificultat;
    }

    /**
     * Crea Hidato a partir del Hidato passat per paràmetre
     * @param h
     */
    public Hidato(Hidato h)
    {

        uniqID = h.getUniqID();
        tauler = h.getTauler();
        taulerComplert = h.getTaulerComplert();
        dificultat = h.getDificultat();
    }

    /**
     *
     * @return Retorna Tauler
     */
    public Tauler getTauler()
    {

        return new Tauler(tauler);
    }

    /**
     * @return Retorna TaulerComplert
     */
    public TaulerComplert getTaulerComplert()
    {

        return new TaulerComplert(taulerComplert);
    }

    /**
     * @return Retorna idTauler
     */
    public int getIDTauler()
    {

        return tauler.getUniqID();
    }

    /**
     * Modifica el id per el passat per paràmetre
     * @param id
     */
    public void setIDTauler(int id) {

        tauler.setUniqID(id);
    }

    /**
     * @return Retorna el idTaulerComplert
     */
    public int getIDTaulerComplert()
    {

        return taulerComplert.getUniqID();
    }

    /**
     * Modifica el idTaulerComplert per el passat per paràmetre
     * @param id
     */
    public void setIDTaulerComplert(int id)
    {

        taulerComplert.setUniqID(id);
    }

    /**
     * Modifica el Tauler per el passat per paràmetre
     * @param nou
     */
    public void setTauler(Tauler nou)
    {

        tauler = nou;
    }

    /**
     * Modifica el TaulerComplert per el passat per paràmetre
     * @param nou
     */
    public void setTaulerComplert(TaulerComplert nou)
    {

        taulerComplert = nou;
    }

    /**
     * @return Retorna el UniqID
     */
    public int getUniqID() {

        return uniqID;
    }

    /**
     * Modifica el UniqID per el passat per paràmetre
     * @param uniqID
     */
    public void setUniqID(int uniqID) {

        this.uniqID = uniqID;
    }

    /**
     * @return Retorna dificultat
     */
    public String getDificultat() {

        return dificultat;
    }

    /**
     * Modifica dificualtat per el passat per paràmetre
     * @param dificultat
     */
    public void setDificultat(String dificultat) {

        this.dificultat = dificultat;
    }

    /**
     * @return Retorna el Tamany del Tauler
     */
    public int getTamany()
    {


        return tauler.getTamany();
    }

    /**
     *
     * @param x
     * @param y
     * @return Si la casella esta es predeterminada o s'ha de completar
     */
    public boolean casellaEsOriginal(int x, int y)
    {
        return tauler.getCasella(x,y).elem >0;
    }

}
