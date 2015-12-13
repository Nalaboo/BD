package CapaDomini;

import java.util.Comparator;

/**
 * Esta Classe conté tot Casella amb les seves funcionalitats
 * @author Eduard J. Seoane
 */

public class Casella implements Comparable
{
    public int elem;
    public int x;
    public int y;
    public int numadjlliures;
    public static int FORAT = -1;
    public static int BUIT = 0;

    /** Crea una Casella buida
     */
    public Casella() {
    }


    /**
     * Crea una Casella amb els valors que pases per paràmetre excepte numadjlliures
     * @param x
     * @param y
     * @param elem
     */
    public Casella(int x, int y, int elem) {

        this.x = x;
        this.y = y;
        this.elem = elem;
    }

    /**
     * Crea una Casella amb els valors que pases per paràmetre
     * @param x
     * @param y
     * @param elem
     * @param numadjlliures
     */
    public Casella(int x, int y, int elem, int numadjlliures) {

        this.x = x;
        this.y = y;
        this.elem = elem;
        this.numadjlliures = numadjlliures;
    }


    /**
     * Et modifica la X y la Y de la Casella ficantli xx y yy
     * @param xx
     * @param yy
     */
    public Casella(int xx, int yy) {

        x = xx;
        y = yy;
    }

    /**
     * A la Casella li modifica els valors de la Casella per els que
     * te la Casella que pases per paràmetre excepte el numadjlliures
     * @param c
     */
    public Casella(Casella c) {

        this.x = c.x;
        this.y = c.y;
        this.elem = c.elem;
    }

    /**
     * Als valors X y Y de la Casella se li suma els valors X y Y de
     * la Casella que pases per paràmetre
     * @param o
     * @return
     */
    public Casella suma(Casella o)
    {
        Casella aRetornar = new Casella(this);
        aRetornar.setX(x + o.x);
        aRetornar.setY(y + o.y);
        return aRetornar;
    }


    /**
     * Als valors X i Y de la Casella se li suma els valors X y Y de
     * la Casella que pases per paràmetre dintre de un minim i un màxim
     */
    public Casella sumaAmbCheck(Casella o, int minim, int maxim)
    {
        Casella aRetornar = suma(o);
        aRetornar.x = aRetornar.x>minim?aRetornar.x:minim;
        aRetornar.x = aRetornar.x<maxim?aRetornar.x:maxim;
        aRetornar.y = aRetornar.y>minim?aRetornar.y:minim;
        aRetornar.y = aRetornar.y<maxim?aRetornar.y:maxim;
        return aRetornar;

    }

    /**
     *
     * @param o
     * @return Torna la minima distancia possible entre dues caselles, independentment del tauler en la que estiguin colocades. La distancia minima real entre dues caselles colocades en un tauler pot ser significativament mes alta (o pot ser que no hi hagi cami per on arribar-hi.
     * Una alternativa baratissima a trobar el tauler.camiMesCurt(Casella a, Casella b) que es necessariament un BFS.
     */
    public int distancia(Casella o)
    {
        int distX = x - o.x;
        int distY = y - o.y;
        distX = distX >= 0 ? distX : -distX;
        distY = distY >= 0 ? distY : -distY;
        return distX >= distY ? distX : distY;
    }

    /**
     *
     * @param tam
     * @return Donat el tamany d’un tauler que li pases per paràmetre et diu si la Casella està dins dels límits del Tauler
     */
    public boolean esDinsLimits(int tam) {

        return 0 <= x && x < tam && 0 <= y && y < tam;
    }

    /**@return Donat una Casella que li pases per paràmetre et diu si es Adjacent a la Casella.
     */
    public boolean esAdjacent(Casella o) {

        return distancia(o) <= 1;
    }

    /**@return Et retorna el valor de la Casella
     */
    public int getElem(){

        return elem;
    }

    /**
     * Et modifica el valor de la Casella per el enter que li pases per paràmetre
     * @param elem
     */
    public void setElem(int elem) {

        this.elem = elem;
    }

    /**@return Et retorna el valor de la X
     */
    public int getX() {

        return x;
    }

    /**
     * Et modifica el valor de la X per el enter que li pases per paràmetre
     * @param x
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     *
     * @return Et retorna el valor de la Y
     */
    public int getY() {

        return y;
    }

    /**
     * Et modifica el valor de la X per el enter que li pases per paràmetre
     * @param y
     */
    public void setY(int y) {

        this.y = y;
    }

    @Override
    /**Funcio que serveix per ordenar per l'implentacio Comparable
     */
    public int compareTo(Object o) {
        Casella c = (Casella) o;
        return new Integer(numadjlliures).compareTo(new Integer(c.numadjlliures));
    }
}
