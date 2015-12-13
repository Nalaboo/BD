package CapaDomini;

import CapaPersistencia.GestorEstadistica;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Maria on 01/12/2015.
 */
public class ControladorEstadisticas {

    //Per a fer proves amb el driver
/*
    public static void registrar_abandonament( ) {
        if(GestorEstadistica.existeixEstadistica(2,1)) {    //Va bien este
            EstadisticasUsuari estUser = GestorEstadistica.donaEstadistica(1, 2);
            int intents = estUser.getIntents();
            ++intents;
            estUser.setIntents(intents);
            GestorEstadistica.modificaEstadistica(estUser);
        }
        else{
            //El segundo 0 corresponde al tiempo y el 3 a las ayudas para hacer pruebas
            //Va bien este
            EstadisticasUsuari est = new EstadisticasUsuari(2,1);
            est.setTemps_mig(0);
            est.setIntents(1);
            est.setCops_res(0);
            est.setAjudes(0);
            GestorEstadistica.guardaEstadistica(est);
        }
    }
*/
    //Per a fer proves amb el driver
 /*   public static void registrar_guanyat() {
        if(GestorEstadistica.existeixEstadistica(1, 1)) {
            EstadisticasUsuari estUser = GestorEstadistica.donaEstadistica(1, 1);
            int intents = estUser.getIntents();
            int temps = estUser.getTemps_mig();
            int cops_resolt = estUser.getCops_res();
            int nAjudes = 0;
            ++intents;
            ++cops_resolt;
            nAjudes = estUser.getAjudes() + 3;
            temps = (temps + 2) / 2;
            estUser.setIntents(intents);
            estUser.setAjudes(nAjudes);
            estUser.setCops_res(cops_resolt);
            estUser.setTemps_mig(temps);
            GestorEstadistica.modificaEstadistica(estUser);
        }
        else{
            //El temps (0)  y ajudes (3) no esta hecho en las otras clases todavia
            //Comprobar si va bien, que se han hecho cambios
           // EstadisticasUsuari est = new EstadisticasUsuari(p.getIDHidato(),p.getIDUsuari());
            est.setAjudes(p.getnAjudes());
            est.setCops_res(1);
            est.setIntents(1);
            est.setTemps_mig(p.getTemps());
            GestorEstadistica.guardaEstadistica(est);
        }
    }
        */

    /**
     * Registra un abandonament, nomes es modifica el numero d'intents
     * Crea una Estadistica nova en cas que el hidato jugat no hagi estat jugat abans per l'usuari
     * que ha jugat la partida. En cas que ja hagues jugat aquest hidato, actualitza les estadistiques
     * @param p
     */
     public static void registrar_abandonament(Partida p) {
        EstadisticasUsuari e = new EstadisticasUsuari();
        if(GestorEstadistica.existeixEstadistica(p.getIDUsuari(),p.getIDHidato())) {
            e = GestorEstadistica.donaEstadistica(p.getIDUsuari(), p.getIDHidato());
            int intents = e.getIntents();
            ++intents;
            e.setIntents(intents);
            GestorEstadistica.modificaEstadistica(e);

        }
        else{
            e = new EstadisticasUsuari(p.getIDHidato(),p.getIDUsuari());
            e.setTemps_mig(0);
            e.setIntents(1);
            e.setCops_res(0);
            e.setAjudes(0);
            GestorEstadistica.guardaEstadistica(e);
        }
    }


    /**
     * Registra una partida guanyada. S'actualitzen tots els parametres d'estadistica en cas que l'usuari
     * hagi jugat aquell hidato abans. En cas que no existeix l'estadistica per aquell hidato i usuari,
     * en crea una estadistica nova.
     * @param p
     * */
    public static void registrar_guanyat(Partida p) {
        EstadisticasUsuari e;
        if (GestorEstadistica.existeixEstadistica(p.getIDUsuari(), p.getIDHidato())) {
            e = GestorEstadistica.donaEstadistica(p.getIDUsuari(), p.getIDHidato());
            int intents = e.getIntents();
            int temps = e.getTemps_mig();
            int cops_resolt = e.getCops_res();
            int nAjudes = e.getAjudes();
            ++intents;
            ++cops_resolt;
            nAjudes += p.getNumAjudesUtilitzades();
            temps = (temps + p.getTemps()) / 2;
            e.setIntents(intents);
            e.setAjudes(nAjudes);
            e.setCops_res(cops_resolt);
            e.setTemps_mig(temps);
            GestorEstadistica.modificaEstadistica(e);
        } else {
            e = new EstadisticasUsuari(p.getIDHidato(), p.getIDUsuari());
            e.setAjudes(p.getNumAjudesUtilitzades());
            e.setCops_res(1);
            e.setIntents(1);
            e.setTemps_mig(p.getTemps());
            GestorEstadistica.guardaEstadistica(e);
        }
    }

    /**
     * Dona l'estadistica de l'idUser i idHidato passats per parametre
     * @param idUser
     * @param idHidato
     * @return retorna l'estadistica d'idUser i idHidato
     */
    public static EstadisticasUsuari dona_estadistiques_Usuari(int idUser,int idHidato) {
        EstadisticasUsuari aux = new EstadisticasUsuari();
        if(GestorEstadistica.existeixEstadistica(idHidato, idUser)){
            aux = GestorEstadistica.donaEstadistica(idUser,idHidato);
         //   System.out.println(aux.getIdHidato() + ": " + aux.getIntents() + ": " + aux.getCops_res() + ": " + aux.getTemps_mig() + ": " + aux.getAjudes());
        }
        else {
            System.out.println("No existeix");
        }
        return aux;
    }

    /**
     * Dona l'estadistica de l'idHidato passat per parametre
     * @param idHidato
     * @return retorna l'estadistica d'idHidato
     */    public static EstadisticasHidato dona_estadistiques_Hidato(int idHidato) {
            EstadisticasHidato aux = fer_mitja_variables(idHidato);
            System.out.println(aux.getIdHidato() + ": " + aux.getIntents() + ": " + aux.getCops_res() + ": " + aux.getTemps_mig() + ": " + aux.getAjudes());
            return aux;
    }

    /**
     * Dona el valor total de l'estadistica del hidato amb idHidato
     * @param i
     * @return retorna el valor total d'estadistica idHidato
     */
    public static EstadisticasHidato fer_mitja_variables(int i) {
        int cops_resolt = 0;
        int nIntents = 0;
        int temps = 0;
        int ajudes = 0;
        EstadisticasHidato estHid = new EstadisticasHidato();
        //Calculem les variables de cada Hidato
        List<Integer> l_cops_res = GestorEstadistica.get_copsResolt(i);
        for (int elem : l_cops_res) {
            cops_resolt += elem;
        }
        List<Integer> l_nintents = GestorEstadistica.get_nintents(i);
        for (int elem : l_nintents) {
            nIntents += elem;
        }
        List<Integer> l_temps = GestorEstadistica.get_temps(i);
        for (int elem : l_temps) {
            temps += elem;
        }
        temps = temps / (l_temps.size());
        List<Integer> l_ajudes = GestorEstadistica.get_nAjudes(i);
        for (int elem : l_ajudes) {
            ajudes += elem;
        }
        estHid.setIdHidato(i);
        estHid.setIntents(nIntents);
        estHid.setCops_res(cops_resolt);
        estHid.setTemps_mig(temps);
        estHid.setAjudes(ajudes);

        return estHid;
    }

    /**
     * Dona l'estadistica de l'agregat. Fa la mitjana dels hidatos de la dificultat passada per parametre
     * @param dificultad
     * @return retorna l'estadistica de la dificultat pasada per parametre
     */
    public static EstadisticasHidato dona_estadistiques_agregat(String dificultad) {
        List<Integer> l_idhidato = GestorEstadistica.get_idHidatos();
        List<Integer> l1 = new ArrayList<>();
        for(int i : l_idhidato){
            String res = GestorEstadistica.get_dificultat(i);
            if(res.equals(dificultad)) {
                l1.add(i);
            }
        }
        EstadisticasHidato easy = aux(l1);
        //imprimir
       // System.out.println(dificultad);
       // System.out.println(easy.getIntents() + ": " + easy.getCops_res() + ": " + easy.getTemps_mig() + ": " + easy.getAjudes());
        return easy;
    }

    /**
     * Dona l'estadistica global d'hidato dels hidatos amb idHidato que estan dins la llista
     * @param l
     * @return retorna una EstadisticaHidato amb la mitjana dels hidatos amb els id's que hi ha a la llista
     */
    public static EstadisticasHidato aux(List<Integer> l) {
        int ajudes = 0;
        int temps = 0;
        int nintents = 0;
        int copresolt = 0;
        for(int i : l) {
            EstadisticasHidato est = fer_mitja_variables(i);
            nintents += est.getIntents();
            temps += est.getTemps_mig();
            copresolt += est.getCops_res();
            ajudes += est.getAjudes();
        }
        if(l.size() >0) temps = temps/l.size();
        EstadisticasHidato nombre = new EstadisticasHidato(0);
        nombre.setAjudes(ajudes);
        nombre.setTemps_mig(temps);
        nombre.setIntents(nintents);
        nombre.setCops_res(copresolt);
        return nombre;
    }

}
