package CapaDomini;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * Aquesta classe s'encarrega de dir si un Tauler té solució unica o no
 * @author Dani Armengod
 */
public class ValidadorTauler
{
    private static Tauler taulerEsbos;
    private static TaulerComplert taulerFinal;
    private static boolean solucioTrobada;
    private static boolean[] presentsInicialment;
    private static List<Interval> intervalsInicials;
    private static int maximaIncognita;
    private static int indexSeguentInterval; //L'inicialitzarem a 1 (based from 0) perque el primer interval (0) sempre sera 1..primeraIncognita-1
    private static boolean multiplesSolucions;

    public static final int OK = 0;
    public static final int NOMINMAX = 1;
    public static final int NOBENPOSADES = 2;
    public static final int JARESOLT = 3;
    public static final int NOTESOL = 4;
    public static final int MULTIPLES = 5;


    /**
     * Aquesta funcio accepta un Hidato on el seu tauler d'usuari conte un Hidato que s'ha de validar.
     * @param aValidar
     * @param deixaAqui
     * @return Retorna OK si l'Hidato te unicament una solucio valida, qualsevol altra cosa si el Hidato no tenia solucio, o aquesta no era unica, etc.
     */
    public static int validarTauler(Tauler aValidar, Tauler deixaAqui)
    {
        /**
         * Aquesta funcio accepta un Hidato on el seu tauler d'usuari conte un Hidato que s'ha de validar.
         * Retorna OK si el tauler d'usuari tenia un Hidato vàlid (solucio i és unica) i deixa el tauler mestre amb totes les dades.
         * Retorna qualsevol altra cosa si el Hidato no tenia solucio, o aquesta no era unica, etc.
         *
         * Els passos que s'han de fer son:
         *   Mirar que hi hagi minim i maxim
         *   Mirar que tots els numeros contigus ja donats estiguin colocats adjacents
         *   Explorar les solucions (amb backtracking) i mirar si hi ha una i nomes una que sigui valida
         *   //todo: si el hidato m'ho passa el generador de l'Edu no fa falta comprovar els items 1, 2,
         */

        //S'ha de reinicialitzar tots els parametres
        solucioTrobada = false;
        multiplesSolucions = false;

        taulerEsbos = new Tauler(aValidar);
        if (!taulerEsbos.teMaximIMinim()) return NOMINMAX;
        if (!taulerEsbos.casellesBenPosades()) return NOBENPOSADES;

        int primeraIncognita = taulerEsbos.trobaPrimeraIncognitaAPartirDe(1);
        //Un petit sanity-check: si no hi ha cap incognita a partir de l'1 vol dir que ja esta resolt...
        if (primeraIncognita == 0) return JARESOLT;

        presentsInicialment = taulerEsbos.presentsAlTauler();
        intervalsInicials = taulerEsbos.donaIntervals(presentsInicialment);
        maximaIncognita = (intervalsInicials.get(intervalsInicials.size() - 1).inici) - 1; //El num anterior a l'inici de l'ultim interval
        indexSeguentInterval = 1; //veure declaracio de la variabe

        try
        {
            bt(taulerEsbos, primeraIncognita);
        }
        catch (RuntimeException e)
        {
            //no fes res, nomes tira la excepcio com a manera "guarra" de aturar l'execucio.
        }

        if (solucioTrobada)
        {
            if (!multiplesSolucions)
            {
                deixaAqui.move(taulerFinal);
                return OK;
            }
            else
            {
                return MULTIPLES;
            }
        }
        else return NOTESOL;
    }

    /**
     *
     * @param t
     * @param numeroSeguent
     * @return La ultima casella de la "llista" de permutacions ha de ser invalida perque la funcio next es dongui compte de que ja no li queda res mes que fer...
     */
    static List<Casella> donaPermutacions(Tauler t, int numeroSeguent)
    {
        /**
         * La ultima casella de la "llista" de permutacions ha de ser invalida perque la funcio next es dongui compte de que ja no li queda res mes que fer...
         */
        Casella base = t.buscaCasella(numeroSeguent - 1);
        //        Casella seguent = t.buscaCasella(numeroSeguent);
        List<Casella> aRetornar;
        //        if (seguent == null || !base.esAdjacent(seguent)) //todo: si un numero no es adjacent al seu seguent, malament anem... joder
        //        {
        Queue<Casella> adjacents = new ArrayDeque<>();
        t.getAdjacents(base, adjacents);
        aRetornar = new ArrayList<>(adjacents);
        for (Casella c : aRetornar)
            c.elem = numeroSeguent;
        //        }
        //        else
        //        {
        //            aRetornar = new ArrayList<>();
        //            aRetornar.add(seguent);
        //        }
        aRetornar.add(new Casella()); //La ultima casella de la "llista" de permutacions ha de ser invalida perque la funcio next es dongui compte de que ja no li queda res mes que fer...
        return aRetornar;
    }

    /**
     *
     * @param t
     * @param permIndex
     * @param permutacions
     * @return Genera la siguiente permutacion del Tablero
     */
    static boolean next(Tauler t, int permIndex, List<Casella> permutacions)
    {
        /**
         * Genera la siguiente permutacion del Tablero
         */
        if (permIndex != 0) //this will undo the changes made on the previous permutation
        {
            Casella c = permutacions.get(permIndex - 1);
            c.elem = 0;
            t.setCasella(c);
        }

        if (permIndex != permutacions.size() - 1) //we do not consider the last "permutation" on the list since it's a sentinel
        {
            t.setCasella(permutacions.get(permIndex));

        }
        return permIndex != permutacions.size() - 1; //will return false only on last element, which is what we want
    }


    /**
     * Comproba si el candidat es invalid i el descarta si ho es
     * @param t
     * @param casellaPosada
     */
    static boolean reject(Tauler t, Casella casellaPosada)
    {
        /**
         * Comproba si el candidat es invalid i el descarta si ho es
         */
        //todo: amb nomes una comprovacio sembla que ja rompe... mola
        boolean returnVal = false;
        //1a comprovacio: mirar que la distancia entre la casella que hem posat i la inicial del seguent interval es troben a distancia assolible
        // es a dir: casellaPosada.distancia(t.getCasella(seguentInterval.inici)) <= seguentInterval.inici - casellaPosada.elem
        //2a comprovacio: si estem a punt d'entrar a un interval, mirar que la casella que hem ficat te com a adjacent la inicial de l'interval, no?
        Interval seguentInterval = intervalsInicials.get(indexSeguentInterval);
        //todo: precomputar les caselles inicials del seguent interval?
        if (casellaPosada.distancia(t.buscaCasella(seguentInterval.inici)) > seguentInterval.inici - casellaPosada.elem)
            returnVal = true;
        //
        return returnVal;
    }

    /**
     *  Comproba si el candidat es valid i ho accepta si ho es
     * @param t
     * @return
     */
    static boolean accept(Tauler t)
    {
        /**
         * Comproba si el candidat es valid i ho accepta si ho es
         */
        int recorre = t.recorreTauler(1).elem;
        int maxIncog = t.maximElementPossible();
        if (recorre != maxIncog) throw new RuntimeException("No m'acceptes el tauler?? sadface");
        return recorre == maxIncog;
    }

    /**
     * Es la funció Core del Backtracking
     * @param t
     * @param numeroActual
     */
    static void bt(Tauler t, int numeroActual)
    {
        /**
         * Es la funció Core del Backtracking
         */

        //        t.print();
        //        System.out.println();
        //        System.out.println("------------------------------------------------------------");
        //        System.out.println();

        if (numeroActual >= t.maximElementPossible() && accept(t))
        {
            if (solucioTrobada)
            {
                //                System.out.println("La ultima solucio era");
                //                t.print();
                //                System.out.println("------------------------------------------------------------");
                //                taulerFinal.print();
                multiplesSolucions = true;
                throw new RuntimeException("Tauler amb multiples solucions.");
            }
            else
            {
                solucioTrobada = true;
                taulerFinal = new TaulerComplert(t);
                return;
            }
        }

        Interval i = intervalsInicials.get(indexSeguentInterval); //No fa falta fer bounds-checking perque el seguent interval mai estara fora de limits
        if (numeroActual == i.inici) //Cas que estem entrant a una tirada de "ja colocats"... el famos fast-forward
        {
            ++indexSeguentInterval; //todo: repensar aixo com variables locals a la funcio?
            bt(t, i.fi + 1);
            --indexSeguentInterval;
            return;
        }

        //Cas que estem encara en una zona on no hi ha el numero
        int permIndex = 0;
        List<Casella> permutacions = donaPermutacions(t, numeroActual);
        while (next(t, permIndex, permutacions))
        {
            if (!reject(t, permutacions.get(permIndex)))
                bt(t, numeroActual + 1);
            ++permIndex;
        }
    }
}