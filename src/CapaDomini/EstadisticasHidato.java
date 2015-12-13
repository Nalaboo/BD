package CapaDomini;

import java.util.List;

/**
 * Created by Maria on 01/12/2015.
 */
public class EstadisticasHidato {
    int idHidato;
    int intents;
    int cops_res;
    int temps_mig;
    int ajudes;

    public EstadisticasHidato( int idHidato) {
        this.idHidato = idHidato;
        this.intents = 0;
        this.cops_res = 0;
        this.temps_mig = 0;
        this.ajudes = 0;
    }
    public EstadisticasHidato( ) {
    }

    public int getIdHidato() {
        return idHidato;
    }

    public void setIdHidato(int idHidato) {
        this.idHidato = idHidato;
    }

    public int getIntents() {
        return intents;
    }

    public void setIntents(int intents) {
        this.intents = intents;
    }

    public int getCops_res() {
        return cops_res;
    }

    public void setCops_res(int cops_res) {
        this.cops_res = cops_res;
    }

    public int getTemps_mig() {
        return temps_mig;
    }

    public void setTemps_mig(int temps_mig) {
        this.temps_mig = temps_mig;
    }

    public int getAjudes() {
        return ajudes;
    }

    public void setAjudes(int ajudes) {
        this.ajudes = ajudes;
    }

    int ger_ratio(){
        return (cops_res/intents)*100;
    }
}
