package CapaDomini;

import java.util.ArrayList;
import java.util.List;

/**
 * Aquesta classe s'encarrega de gestionar la funcionalitats de les ajudes a l'hora de jugar
 * @author Maria Vives
 */

public class ControladorAjudes {

    /**
     *
     * @param h
     * @return Donat un Hidato per paràmetre et retorna la Casella següent que s’hauria completar al Hidato en una partida,
     * en ordre creixent de ficar Caselles.
     */
    public Casella help1(Hidato h){
        int x = h.getTauler().trobaPrimeraIncognitaAPartirDe(1);
        return h.getTaulerComplert().buscaCasella(x);
    }


    /**
     *
     * @param i
     * @param j
     * @param h
     * @return Et retorna una llista de enters que seran els valors que pot obtenir el valor de la Casella a la
     * posicio I i J del Hidaot H que pasen per paràmetre.
     */
    public List<Integer> help2(Integer i, Integer j, Hidato h) {


        List<Integer> candidatos_todos = new ArrayList<>();
        List<Integer> resultado = new ArrayList<>();
        List<Integer> res1 = new ArrayList<>();
        if (h.getTauler().getCasella(i, j).elem == 0)
        {   //Si la casilla no tiene numero colocado
            candidatos_todos = omplir_vex(h);
            resultado = quitar_puestos(candidatos_todos, h);
            res1 = quitar_resto(resultado, i, j, h);
        }
        return res1;
    }


    /**
     *
     * @param vector
     * @param i
     * @param j
     * @param h
     * @return Retorna una llista amb les cadidats de la Casella a la posició I i J del Hidato H. Candidats s’enten com
     * els valors que pot pendre el valor.
     */
    public List<Integer> quitar_resto(List<Integer> vector, Integer i, Integer j, Hidato h) {

        Integer indice = 0;
        while (indice < vector.size())
        {
            List<Boolean> se_borra = new ArrayList<>();
            Integer auxi = vector.get(indice);
            Boolean b = false;
            if (i - 1 >= 0)
            {    //Arriba //Si las variables i,j estan dentro del rango
                if (h.getTauler().getCasella(i - 1, j).elem != 0)
                {   //Si hay un numero colocado, miramos para cada candidato el rango
                    if (Math.abs(auxi - h.getTauler().getCasella(i - 1, j).elem) == 1)
                    {
                        b = true;
                        se_borra.add(b);
                    }
                }
            }
            if (i + 1 < h.getTauler().getTamany())
            {    //Abajo
                if (h.getTauler().getCasella(i + 1, j).elem != 0)
                {
                    if (Math.abs(auxi - h.getTauler().getCasella(i + 1, j).elem) == 1)
                    {
                        b = true;
                        se_borra.add(b);
                    }
                }
            }
            if (j - 1 >= 0)
            {    //Izquierda
                if (h.getTauler().getCasella(i, j - 1).elem != 0)
                {
                    if ((Math.abs(auxi - h.getTauler().getCasella(i, j - 1).elem) == 1))
                    {
                        b = true;
                        se_borra.add(b);
                    }
                }
            }
            if (j + 1 < h.getTauler().getTamany())
            {    //Derecha
                if (h.getTauler().getCasella(i, j + 1).elem != 0)
                {
                    if (Math.abs(auxi - h.getTauler().getCasella(i, j + 1).elem) == 1)
                    {
                        b = true;
                        se_borra.add(b);
                    }
                }
            }
            if (((i - 1) >= 0) && ((j - 1) >= 0))
            {    //Diagonal izq arriba
                if (h.getTauler().getCasella(i - 1, j - 1).elem != 0)
                {
                    if (Math.abs(auxi - h.getTauler().getCasella(i - 1, j - 1).elem) == 1)
                    {
                        b = true;
                        se_borra.add(b);
                    }
                }
            }
            if (((i + 1) < h.getTauler().getTamany()) && ((j - 1) >= 0))
            {    //Diagonal izq abajo
                if (h.getTauler().getCasella(i + 1, j - 1).elem != 0)
                {
                    if (Math.abs(auxi - h.getTauler().getCasella(i + 1, j - 1).elem) == 1)
                    {
                        b = true;
                        se_borra.add(b);
                    }
                }
            }
            if (((i - 1) >= 0) && ((j + 1) < h.getTauler().getTamany()))
            {    //Diagonal derecha arriba
                if (h.getTauler().getCasella(i - 1, j + 1).elem != 0)
                {
                    if (Math.abs(auxi - h.getTauler().getCasella(i - 1, j + 1).elem) == 1)
                    {
                        b = true;
                        se_borra.add(b);
                    }
                }
            }
            if (((i + 1) < h.getTauler().getTamany()) && ((j + 1) < h.getTauler().getTamany()))
            {    //Diagonal derecha abajo
                if (h.getTauler().getCasella(i + 1, j + 1).elem != 0)
                {
                    if (Math.abs(auxi - h.getTauler().getCasella(i + 1, j + 1).elem) == 1)
                    {
                        b = true;
                        se_borra.add(b);
                    }
                }
            }
            if (se_borra.size() == 0)
            {
                vector.remove(auxi);
            }
            else ++indice;

        }
        return vector;
    }


    //Primer paso: Quitar de la lista los numeros que ya estan colocados en el tablero

    /**
     *
     * @param vector
     * @param h
     * @return Retorna una llista sense els valors de la llista que estiguin ja predeterminats al Hidato
     */
    public List<Integer> quitar_puestos(List<Integer> vector, Hidato h) {

        List<Integer> listaaux = new ArrayList<>();
        for (int k = 0; k < h.getTauler().getTamany(); ++k)
        {
            for (int l = 0; l < h.getTauler().getTamany(); ++l)
            {
                if (h.getTauler().getCasella(k, l).elem != 0 && h.getTauler().getCasella(k, l).elem != -1)
                {
                    vector.remove((Integer) h.getTauler().getCasella(k, l).elem); //quito de la lista los numeros que ya estan colocados
                }
            }
        }
        return vector;
    }

    //Pongo en un vector todos los numeros posibles del tablero del 1 al n

    /**
     *
     * @param h
     * @return Retorna una llista omplerta amb els valors de 1 fins a tamany del Tauler menys forats
     */
    public List<Integer> omplir_vex(Hidato h){
        Integer n = h.getTauler().getTamany() * h.getTauler().getTamany();
        List<Integer> l = new ArrayList<>();
        for (int i = 0; i < n; i++)
        {
            l.add(i + 1);
        }
        return l;
    }

    /**
     * Surt per pantalla els valors de la llista pasada per paràmetres
     * @param vector
     */
    public void print(List<Integer> vector){

        for (Integer temp : vector) {
            System.out.println(temp);
        }
    }

}
