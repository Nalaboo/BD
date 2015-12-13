package CapaDomini;

import CapaPersistencia.GestorHidato;
import CapaPersistencia.GestorPartida;
import CapaPersistencia.GestorUsuari;
import CapaVista.JugarPartida;

/**
 * Aquesta Casella s'encarrega de gestionar les accions d'una Partida
 * @author Eduard J. Seoane
 */
public class ControladorPartida
{
    private static Partida partida;
    private static ControladorPartidaCallbackImplementor callbackImplementor;

    static
    {
        callbackImplementor = ControladorPartidaCallbackImplementor.getInstance();
    }

    public static class ControladorPartidaCallbackImplementor implements TaulerDisplayerCallbacks
    {
        private static ControladorPartidaCallbackImplementor instance;
        private ControladorPartidaCallbackImplementor()
        {
            //do nothing?
        }
        public static ControladorPartidaCallbackImplementor getInstance()
        {
            if (instance == null) instance = new ControladorPartidaCallbackImplementor();
            return instance;
        }


        /**
         *
         * @param c
         * @return Verifica que l'operacio de modificar Casella es correcte
         */
        public boolean casellaModificada(Casella c)
        {

            boolean aRetornar = false;
            if (c.getElem() > 0)
            {
                int resultatAfegir = afegirvaloraCaselladePartida(c.getX(),c.getY(),c.getElem());
                aRetornar = resultatAfegir == 0; //todo: canviar aixo per un enum o algo plox
            }
            else if (c.getElem() == Casella.BUIT)
            {
                int resultatEliminar = eliminarvalorCaselladePartida(c.getX(),c.getY());
                aRetornar = resultatEliminar == 0;
            }
            else
            {
//                throw new RuntimeException("No se com vols que modifiqui una casella");
            }
            return aRetornar;
        }
    }


    /***
     * Afegeix al Tauler de la partida el ELEM a la posicio X,Y passat tot per paràmetres
     * @param x
     * @param y
     * @param elem
     * @return et retorna si ho ha afegit o no
     */
    public static int afegirvaloraCaselladePartida(int x, int y, int elem)
    {

        int ret = partida.afegir(x, y, elem);
        return ret;
    }

    /**
     * Elimina el valor de la Casella de partida de la posicio X,Y passat per paràmetres
     * @param x
     * @param y
     * @return Et retorna si ho ha eliminat o no
     */
    public static int eliminarvalorCaselladePartida(int x, int y)
    {

        int ret = partida.remove(x, y);
        return ret;
    }

    /**
     * Comença a jugar la partida actual
     */
    public static void jugaPartida()
    {

        JugarPartida j = new JugarPartida(partida.getTaulerProgres(),callbackImplementor);
    }

    /**
     * Crea una nova partida a partir de paràmetres
     * @param u
     * @param h
     */
    public static void novaPartida(Usuari u, Hidato h)
    {

        partida = new Partida();
        partida.setUsuari(u);
        partida.setHidato(h);
        partida.setTaulerProgres(new Tauler(h.getTauler()));
        partida.setnCelesResoltes(0);
        partida.setNumAjudesUtilitzades(0);
        partida.setEsAcabada(false);
        partida.setTemps(0);
    }


    /**
     * Crea una nova partida a partir de paràmetres
     * @param idUsuari
     * @param idHidato
     */
    public static void novaPartida(int idUsuari, int idHidato)
    {

        Usuari u = GestorUsuari.donaUsuari(idUsuari);
        Hidato h = GestorHidato.donaHidato(idHidato);
        novaPartida(u,h);
    }


    /**
     * Carrega la Partida a partir de la idPartida passada per paràmetres
     * @param idPartida
     * @return et retorna si l'ha carregat o no
     */
    public static boolean carregarPartida(int idPartida)
    {

        partida = GestorPartida.donaPartida(idPartida);
        return partida != null;
    }

    /**
     * Guarda la Partida actual
     */
    public static void guardarPartida()
    {

        //todo aixo hauria d'associar el usuari amb la ID de la partida (cas creacio)
        if (GestorPartida.existeixPartida(partida.getUniqID()))
            GestorPartida.modificaPartida(partida);
        else
            GestorPartida.creaPartida(partida);
    }

    public static void descartaPartida()
    {
        //todo aixo pot tenir en compte que s'ha fet un intent i incrementar les estadistiques apropiadament (en el futur)
    }




}
