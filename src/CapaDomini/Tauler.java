package CapaDomini;


import java.util.*;

/**
 * Aquesta classe conté Tauler i totes les seves funcionalitats
 * @author Oriol Giralt
 */
public class Tauler
{
    private Casella[][] tauler;
    private int uniqID;
    private static Casella[] offsets = {
            new Casella(1, -1), new Casella(1, 0), new Casella(1, 1),
            new Casella(0, -1), /*casella central*/ new Casella(0, 1),
            new Casella(-1, -1), new Casella(-1, 0), new Casella(-1, 1)
    };

    /**
     * Contructora Buida
     */
    public Tauler()
    {
        // Aixo abans tiraba execpio perque se suposa que no s'havia de fer servir mai.
        uniqID = -1;
        tauler = null;
    }

    /**
     * Contructora a partir d'un tamany passat per paràmetre
     * @param tamany
     */
    public Tauler(int tamany)
    {
        uniqID = -1;
        tauler = new Casella[tamany][tamany];
        for (int i = 0; i < tamany; ++i)
        {
            for (int j = 0; j < tamany; ++j)
            {
                if ((i == 0 && j == 0) || (i == 0 && j == tamany - 1) || (i == tamany - 1 && j == 0) || (i == tamany - 1 && j == tamany - 1))
                    tauler[i][j] = new Casella(i, j, Casella.BUIT, 3);
                else if ((i == 0 && j != 0 && j != tamany) || (i == tamany - 1 && j != 0 && j != tamany - 1) || (j == 0 && i != 0 && i != tamany - 1) || (j == tamany - 1 && i != 0 && i != tamany - 1))
                    tauler[i][j] = new Casella(i, j, Casella.BUIT, 5);
                else tauler[i][j] = new Casella(i, j, Casella.BUIT, 8);

            }
        }
        for (int i = 0; i < tamany; ++i) //por si las moscas
        {
            for (int j = 0; j < tamany; ++j)
            {
                tauler[i][j].elem=Casella.BUIT;
            }
        }
    }

    /**
     * Crea un tauler igual al Tauler passat per paràmetre
     * @param t
     */
    public Tauler(Tauler t)
    {
        uniqID = -1;
        int tamany = t.getTamany();
        tauler = new Casella[tamany][tamany];
        for (int i = 0; i < tamany; ++i)
        {
            for (int j = 0; j < tamany; ++j)
            {
                tauler[i][j] = new Casella(t.tauler[i][j]);
            }
        }
    }

    /**
     * Crea un tauler igual a lo que es llegeix per terminal
     * @param in
     */
    public Tauler(Scanner in)
    {
        uniqID = -1;
        llegeixRepresentacioTextual(in);
    }

    /**
     * Assigna la Matriu a la representacio textual que passes per paràmetre
     * @param in
     */
    public void llegeixRepresentacioTextual(Scanner in)
    {
        int tamany = in.nextInt();
        tauler = new Casella[tamany][tamany];
        for (int i = 0; i < tamany; ++i)
        {
            for (int j = 0; j < tamany; ++j)
            {
                tauler[i][j] = new Casella(i, j, in.nextInt());
            }
        }
    }

    /***
     *
     * @return Retorna un String que serà la Matriu
     */
    public String donaRepresentacioTextual()
    {
        StringBuilder aRetornar = new StringBuilder(400);
        aRetornar.append(getTamany()).append(' ');
        for (Casella[] fila : tauler)
        {
            for (Casella c : fila)
            {
                aRetornar.append(c.elem).append(' ');
            }
            aRetornar.append('\n');
        }
        return aRetornar.toString();
    }

    /**
     *
     * @param num
     * @return Retorna una Casella on el valor sigui igual al enter passat per paràmetre
     */
    public Casella buscaCasella(int num)
    {
        for (Casella[] fila : tauler)
        {
            for (Casella candidat : fila)
            {
                if (candidat.elem == num)
                    return new Casella(candidat);
            }
        }
        return null;
    }

    /**
     * Agafa lo que hi ha a l'altre tauler i t'ho posa a la Matriu
     * @param altre
     */
    public void move(Tauler altre)
    {
        //agafa lo que hi ha a l'altre tauler i t'ho posa a tu
        tauler = altre.tauler;
        altre.tauler = null;
    }

    /**
     * Iguala el Tauler a el Tauler que passa per paràmetres
     * @param t
     */
    public void clone(Tauler t)
    {
        for (int i = 0; i < t.getTamany(); ++i)
        {
            for (int j = 0; j < t.getTamany(); ++j)
            {
                this.setCasella(i, j, t.getCasella(i, j).elem, t.getCasella(i, j).numadjlliures);
            }
        }
    }

    /**
     * Modifica tauler igual al Tauler passat per paràmetre
     * @param tauler
     */
    public void setTauler(Casella[][] tauler) {
        this.tauler = tauler;
    }

    /**
     *
     * @return Retorna el Tauler
     */
    public Casella[][] getTauler() {

        return tauler;
    }

    /**
     *
     * @return Retorna UniqID
     */
    public int getUniqID() {

        return uniqID;
    }

    /**
     * Modifica tauler igual al idTauler passat per paràmetre
     * @param idtauler
     */
    public void setUniqID(int idtauler) {

        this.uniqID = idtauler;
    }

    /**
     *
     * @param posX
     * @param posY
     * @return Retorna la Casella del Tauler amb la posicio pasada per paràmetre
     */
    public Casella getCasella(int posX, int posY)
    {
        return new Casella(tauler[posX][posY]);
    }

    /**
     *
     * @param candidat
     * @return Retorna la Casella del Tauler amb la posicio de la Casella pasada per paràmetre
     */
    public Casella getCasella(Casella candidat)
    {
        return new Casella(tauler[candidat.x][candidat.y]);
    }

    /**
     * Modifica el Tauler ficant una Casella amb el valor y la posició pasada per paràmetres
     * @param posX
     * @param posY
     * @param valor
     */
    public void setCasella(int posX, int posY, int valor)
    {
        tauler[posX][posY].elem = valor;
    }

    /**
     * Modifica el Tauler ficant una Casella amb el valor, el numadjlliure y la posició pasada per paràmetres
     * @param posX
     * @param posY
     * @param valor
     * @param numadjlliure
     */
    public void setCasella(int posX, int posY, int valor, int numadjlliure)
    {

        int[] X = {0, 1, 0, -1, 1, 1, -1, -1};
        int[] Y = {1, 0, -1, 0, 1, -1, 1, -1};
        tauler[posX][posY] = new Casella(posX, posY, valor, numadjlliure);

        for (int i = 0; i < 8; ++i)
        {
            if (esValid(posX + X[i], posY + Y[i]) && tauler[posX + X[i]][posY + Y[i]].elem != -1)
                --tauler[posX + X[i]][posY + Y[i]].numadjlliures;
        }

    }

    /**
     * Modifica el Tauler ficant una Casella amb el valor y la posició pasada per paràmetre
     * @param c
     */
    public void setCasella(Casella c)
    {
        tauler[c.x][c.y] = c;
    }

    /**
     *
     * @return Retorna el tamany del Tauler
     */
    public int getTamany()
    {
        return tauler.length;
    }

    /**
     *
     * @param Posx
     * @param Posy
     * @return Et retorna si la posició pasada per paràmetres esta dins dels limits del tauler
     */
    public boolean esValid(int Posx, int Posy)
    {
        //todo: el nom no es gens indicatiu
        return (Posx >= 0 && Posx < tauler.length && Posy >= 0 && Posy < tauler.length);
    }

    /**
     *
     * @return Et diu si te el numero 1 i el número maxim del tauler
     */
    public boolean teMaximIMinim()
    {
        return buscaCasella(1) != null && buscaCasella(maximElementPossible()) != null;
    }

    /**
     *
     * @param presents
     * @return Et retorna una llista de tots els intervals
     */
    public List<Interval> donaIntervals(boolean[] presents)
    {
        List<Interval> aRetornar = new ArrayList<>();
        boolean enInterval = false;
        int iniciInterval = -400;
        for (int i = 1; i < presents.length; ++i)
        {
            if (enInterval && presents[i]) continue;
            if (!enInterval && !presents[i]) continue;
            if (!enInterval && presents[i])
            {
                iniciInterval = i;
                enInterval = true;
            }
            if (enInterval && !presents[i])
            {
                aRetornar.add(new Interval(iniciInterval, i - 1));
                enInterval = false;
            }
        }
        if (enInterval) //afegeix el ultim interval que arriba fins al final!!
            aRetornar.add(new Interval(iniciInterval, presents.length - 1));
        return aRetornar;
    }

    /**
     * Aixo comprova que tots els numeros que estiguin posats al tauler hi siguin de manera adjacent.
     * @return Si la Casella esta ben posada o no
     */
    public boolean casellesBenPosades()
    {
        /**
         * Aixo comprova que tots els numeros que estiguin posats al tauler hi siguin de manera adjacent.
         * La idea es agafar els intervals de caselles que es troben posades al tauler i mirar si recorrent el tauler des de l'inici de cada interval es pot arribar al final del mateix. Si això és així, significa que les caselles de l'interval estaven posades adjacentment.
         */
        boolean[] presents = presentsAlTauler();
        List<Interval> intervals = donaIntervals(presents);
        for (Interval interval : intervals)
        {
            Casella maxima = recorreTauler(interval.inici);
            if (!(maxima != null && maxima.elem == interval.fi))
            {
                return false;
            }
        }
        return true;
    }

    /**
     * Aquesta funció afegeix a la lista "aAfegir" tots els adjacents que no tinguin o un valor o un forat, es a dir, només els que siguin buits
     * @param inici
     * @param aAfegir
     */
    public void getAdjacentslist(Casella inici, List<Casella> aAfegir)
    {
        int DEBUG_AFEGIT = 0;
        for (int i = -1; i <= 1; ++i)
        {
            int consideraX = inici.x + i;
            if (consideraX >= 0 && consideraX < tauler.length)
            {
                for (int j = -1; j <= 1; ++j)
                {
                    int consideraY = inici.y + j;
                    if (consideraY >= 0 && consideraY < tauler.length)
                    {
                        if (tauler[consideraX][consideraY].elem == Casella.BUIT)
                        {
                            aAfegir.add(new Casella(consideraX, consideraY, 0, tauler[consideraX][consideraY].numadjlliures));
                            ++DEBUG_AFEGIT;
                        }
                    }
                }
            }
        }
    }


    /**
     * Aquesta funció afegeix a la cua "aAfegir" tots els adjacents que no tinguin o un valor o un forat, es a dir, només els que siguin buits
     * @param inici
     * @param aAfegir
     */
    public void getAdjacents(Casella inici, Queue<Casella> aAfegir)
    {
        int DEBUG_AFEGIT = 0;

        for (Casella offset : offsets)
        {
            Casella candidat = inici.suma(offset);
            if (candidat.esDinsLimits(tauler.length) && getCasella(candidat).elem == Casella.BUIT)
            {
                aAfegir.add(getCasella(candidat)); //Aixi la casella que retornarem tindra el camp elem inicialitzat tambe...
                ++DEBUG_AFEGIT;
            }
        }
        //              System.err.println("S'han trobat " + DEBUG_AFEGIT + " adjacents\tCasella inicial: " + inici.x + "," + inici.y);
    }

    /**
     *
     * @return Retorna el numero mes elevat que pot tenir aquest tauler
     */
    public int maximElementPossible() //todo computar aixo un cop
    {
        int potencialMax = getTamany() * getTamany();
        for (int i = 0; i < getTamany(); ++i)
            for (int j = 0; j < getTamany(); ++j)
                if (getCasella(i, j).elem == Casella.FORAT)
                    --potencialMax;
        return potencialMax;
    }

    /**
     * Aquesta funcio intenta travessar tot el hidato, començant per la casella que te el numero i acabant fins on es pugui arribar seguint les adjacencies.
     * @param inicial
     * @return Torna l'ultima on hi ha pogut arribar.
     */
    public Casella recorreTauler(int inicial)
    {
        /**
         * Aquesta funcio intenta travessar tot el hidato, començant per la casella que te el numero i acabant fins on es pugui arribar seguint les adjacencies.
         * Torna l'ultima on hi ha pogut arribar.
         */
        Casella cActual = buscaCasella(inicial);
        boolean heAvancat = true;
        while (heAvancat)
        {
            heAvancat = false;
            //Get adjacents i mira si algun te el seguent numero
            for (Casella offset : offsets)
            {
                Casella candidat = cActual.suma(offset);
                if (candidat.esDinsLimits(tauler.length))
                {
                    candidat = getCasella(candidat);
                    if (candidat.elem == cActual.elem + 1)
                    {
                        heAvancat = true;
                        cActual = candidat;
                    }
                }
            }
        }
        return getCasella(cActual);
    }

    /**
     *
     * @return Retorna un array de booleans que indica que si cada casella en ordre ascendent sent primer la posicio (0,0)
     * si hi ha un valor no BUIT i no FORAT.
     */
    public boolean[] presentsAlTauler()
    {
        // no usarem presentsAlTauler[0]
        boolean[] aRetornar = new boolean[maximElementPossible() + 1];
        for (Casella[] fila : tauler)
        {
            for (Casella candidat : fila)
            {
                if (candidat.elem > 0) //comprovacio que no sigui ni un BUIT (0) ni un forat (-1!!!!)
                    aRetornar[candidat.elem] = true;
            }
        }
        return aRetornar;
    }

    /**
     *
     * @param minim
     * @return Retorna 0 si no hi ha cap incognita al tauler a partir del minim (inclusiu)
     */
    public int trobaPrimeraIncognitaAPartirDe(int minim)
    {
        /**
         * Aixo NO comprova que les caselles estiguin ficades en un ordre valid per les regles del hidato
         * Retorna 0 si no hi ha cap incognita al tauler a partir del minim (inclusiu)
         */
        /* todo: funcionalitat duplicada, canviar per
         *   donaIntervals(presentsAlTauler)[0].final+1
         */
        boolean[] presentsAlTauler = presentsAlTauler();
        int i = minim;
        while (i < presentsAlTauler.length)
        {
            if (!presentsAlTauler[i]) break;
            ++i;
        }

        if (i == presentsAlTauler.length) return 0;
        return i;
    }

    /**
     * Aquesta funcio serveix per a comprovar que un tauler no tingui multiples particions no connexes
     * Considera "travessables" nomes les caselles que estiguin a Casella.BUIT (0).
     * Per tant, te en compte tant els forats com els numeros ja posats.
     * Procediment:
     *  Marcar totes les caselles impassables com a "visitades"
     *  Agafar una casella "no visitada" quaslevol
     *  Començar a fer un BFS des d'aquesta casella, fins a exhaurir tots els adjacents.
     *  Si al final del BFS queda alguna casella no visitada, el tauler es partit.
     *  @return  Si es partit o no
     */
    public boolean esPartit()
    {
        /**
         * Aquesta funcio serveix per a comprovar que un tauler no tingui multiples particions no connexes
         * Considera "travessables" nomes les caselles que estiguin a Casella.BUIT (0).
         * Per tant, te en compte tant els forats com els numeros ja posats.
         * Procediment:
         *  Marcar totes les caselles impassables com a "visitades"
         *  Agafar una casella "no visitada" quaslevol
         *  Començar a fer un BFS des d'aquesta casella, fins a exhaurir tots els adjacents.
         *  Si al final del BFS queda alguna casella no visitada, el tauler es partit.
         */

        boolean[][] visitats = new boolean[tauler.length][tauler.length];
        int primeraX = -1, primeraY = -1;
        int casellesVisitades = 0;
        for (int i = 0; i < tauler.length; ++i)
        {
            for (int j = 0; j < tauler.length; ++j)
            {
                visitats[i][j] = (tauler[i][j].elem != Casella.BUIT);
                if (!visitats[i][j])
                {
                    primeraX = i;
                    primeraY = j;
                }
                else
                {
                    ++casellesVisitades;
                }
            }
        }
        if (primeraX == -1 || primeraY == -1)
        {
            if (casellesVisitades == tauler.length * tauler.length)
                return false;
            else
                throw new RuntimeException("No hi ha punt d'inici, pero no hem visitat totes les caselles??");
        }

        //BFS a partir d'aqui
        Queue<Casella> aVisitar = new ArrayDeque<>();
        getAdjacents(new Casella(primeraX, primeraY, 0, 0), aVisitar);
        visitats[primeraX][primeraY] = true;
        ++casellesVisitades;
        int iteracions = 0;
        while (!aVisitar.isEmpty())
        {
            ++iteracions;
            Casella actual = aVisitar.element();
            aVisitar.remove();
            //check if we've been here before. If so, just skip this round
            if (visitats[actual.x][actual.y]) continue;
            ++casellesVisitades;
            visitats[actual.x][actual.y] = true;
            getAdjacents(actual, aVisitar);
        }
        System.err.println("S'han fet " + iteracions + " iteracions");

        //Ara mirem si hem visitat tantes caselles com te el mapa...
        if (casellesVisitades > tauler.length * tauler.length)
            throw new RuntimeException("S'han visitat " + casellesVisitades + " en un tauler de " + tauler.length * tauler.length + " caselles");
        if (casellesVisitades < tauler.length * tauler.length)
        {
            System.err.println("S'han visitat " + casellesVisitades + " caselles...");
            return true;
        }
        return false;
    }

    public void print()
    {
        /**
         * Pintar el Tauler per pantalla
         */
        for (int i = 0; i < getTamany(); ++i)
        {
            for (int j = 0; j < getTamany(); ++j)
            {
                System.out.print(getCasella(i, j).elem);
                System.out.print("\t");
            }
            System.out.println();
        }
    }

    /**
     * Et retorna un boolean que et diu si esta complet el Tauler
     * @return si ha acabat el Tauler o no
     */
    public boolean he_acabat()
    {
        /**
         * Et retorna un boolean que et diu si esta complet el Tauler
         */
        int cont = 0;
        for (int i = 0; i < this.getTamany(); ++i)
        {
            for (int j = 0; j < this.getTamany(); ++j)
            {
                if (this.getCasella(i, j).elem == 0) ++cont;
            }
        }

        if (cont == 0) return true;
        else return false;

    }

    public void pintar_tauler()
    {
        /**
         * Imprimeix per pantalla el tauler donat. El simbol '*' significa una casella en la qual no hi pot anar cap element.
         * Els numeros de color vermell indiquen els valors propis donats per el Tauler mentre que els numeros de color negre indiquen els valors
         * indtroduits per el usuari.
         */
        final String ANSI_RESET = "\u001B[0m";
        final String ANSI_RED = "\u001B[31m";
        final String ANSI_BARRA = "\u2014";

        for (int i = 0; i < getTamany(); ++i)
        {
            System.out.print("|");
            for (int j = 0; j < getTamany(); ++j)
            {
                if (getCasella(i, j).elem - 10 < 0) System.out.print(" ");
                if (getCasella(i, j).elem == -1) System.out.print("*");
                else if (getCasella(i, j).elem != 0)
                    System.out.print(ANSI_RED + getCasella(i, j).elem + ANSI_RESET); //mirar si es un numero core
                else System.out.print(getCasella(i, j).elem);
                System.out.print("|");
            }
            System.out.print("\n");

        }

    }

}
