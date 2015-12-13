package CapaDomini;

import CapaPersistencia.GestorHidato;
import CapaPersistencia.GestorTauler;
import CapaVista.CrearTaulerAutomatic;
import CapaVista.CrearTaulerManual;

/**
 * Aquesta classe es dedica a poder gestionar les accions que es poden fer amb l'Hidato
 * @author Dani Armengod
 */
public class ControladorHidato
{
    private static ControladorHidatoCallbackImplementor callbackImplementor;

    static
    {
        callbackImplementor = ControladorHidatoCallbackImplementor.getInstance();
    }

    public static class ControladorHidatoCallbackImplementor implements TaulerDisplayerCallbacks
    {
        private static ControladorHidatoCallbackImplementor instance;
        private ControladorHidatoCallbackImplementor()
        {
            //do nothing?
        }
        public static ControladorHidatoCallbackImplementor getInstance()
        {

            if (instance == null) instance = new ControladorHidatoCallbackImplementor();
            return instance;
        }


        /**
         *
         * @param c
         * @return Verifica que l'operacio de modificar Casella es correcte
         */
        public boolean casellaModificada(Casella c)
        {
            return modificaCasella(c);
        }
    }

    private static Tauler taulerCreacio;


    /**
     *
     * @param c
     * @return Verifica que l'operacio de modificar Casella es correcte
     */
    public static boolean modificaCasella(Casella c)
    {
        if (c.getElem() > 0 || c.getElem() == Casella.BUIT || c.getElem() == Casella.FORAT)
        {
            taulerCreacio.setCasella(c);
        }
        else
        {
            throw new RuntimeException("No se com vols que modifiqui una casella");
        }
        return true; //en principi totes les modificacions son valides?
        /** todo: fer que les verificacions tinguin lloc a mesura que l'usuari va ficant numeros, e.g.
         *      que si fiques una casella et miri si no et passes del maxim
         *      que tambe et miri que la poses adjacent a les que hauria de ser
         *      etc
         *  potser aixo es massa user unfriendly tho.
         *
         */
    }


    /**
     * Crida la funcio de crearHidatoManual
     * @param tamany
     */
    public static void creaHidatoManualment(int tamany)
    {

        taulerCreacio = new Tauler(tamany);
        CrearTaulerManual ct = new CrearTaulerManual(taulerCreacio,callbackImplementor);
        ct.pack();
        ct.setVisible(true);
    }

    /**
     * Crida la funcio de crearHidatoAutomaticament
     */
    public static void creaHidatoAutomaticament()
    {

        CrearTaulerAutomatic ct = new CrearTaulerAutomatic();
        ct.pack();
        ct.setVisible(true);
    }


    /**
     * Crida la funcio de crear un Hidato automaticament
     * @param tamanyHidato
     * @param numForats
     * @param numPrecolocats
     * @param dificultat
     */
    public static void fesCreacioAutomatica(int tamanyHidato, int numForats, int numPrecolocats, String dificultat)
    {

        Tauler t;
        TaulerComplert tc = new TaulerComplert();

        t = CapaDomini.creacioTaulerPredeterminat(tamanyHidato, numForats, numPrecolocats, dificultat, tc);

        Hidato nouHidato = new Hidato();
        nouHidato.setTauler(t);
        nouHidato.setTaulerComplert(tc);
        nouHidato.setDificultat(dificultat);

        GestorHidato.creaHidato(nouHidato); //todo prendre nota del creador i de la id

    }

    /**
     *  @return Verifica que el tauler sigui legal i el guarda
     */
    public static int validaIGuarda() //todo fer aixo com una interface
    {

        TaulerComplert hopefully = new TaulerComplert();
        int result = ValidadorTauler.validarTauler(taulerCreacio,hopefully);
        if (result == ValidadorTauler.OK)
        {
            Hidato aGuardar = new Hidato();
            aGuardar.setTauler(taulerCreacio);
            aGuardar.setTaulerComplert(hopefully);
            aGuardar.setDificultat("Facil"); //todo aixo esta hardcoded hard.
            GestorHidato.creaHidato(aGuardar);
        }
        System.out.print("S'ha aconseguit un resultat de: ");
        System.out.println(result);
        return result;
    }
}
