package CapaDomini;

/**
 * Aquesta Classe conte TaulerComplert i les seves funcionalitats
 * @author Eduard J. Seoane
 */
public class TaulerComplert extends Tauler
{
    public TaulerComplert()
    {
        /**
         * Crea un TaulerComplert BUIT
         */
        //Igual que amb el constructor de la classe pare, s'ha d'anar molt amb compte amb aixo ja que inicialitza el seu tauler a null...
        super();
    }

    public TaulerComplert(Tauler orig)
    {
        /**
         * Crea un TaulerComplert igual al passat per paràmetres
         */
        super(orig);
    }

    public int comparaDiferencies(Tauler altre)
    {
        /**
         * Retorna la compte de Caselles que no coincideixen de el TaulerComplert amb el Tauler passat per paràmetre
         */
        //TODO: Que torni també la llista de diferencies que troba... pero java es una puta i no deixa parametres per referencia, aixi que esta una mica fucked
        //S'ha de fer sanity-check que comprovi que els dos taulers tenen dimensions iguals
        if (this.getTamany() != altre.getTamany())
        {
            throw new RuntimeException("ERROR TAMANYS DE TAULER NO SON IGUALS");
            // throw excepcio
        }
        int diferenciesTrobades = 0;
        for (int x = 0; x < this.getTamany(); ++x)
        {
            for (int y = 0; y < this.getTamany(); ++y)
            {
                if (this.getCasella(x, y) != altre.getCasella(x, y))
                {
                    ++diferenciesTrobades;
                }
            }
        }
        return diferenciesTrobades;
    }
}
