package CapaDomini;

/**
 * Created by Maria on 01/12/2015.
 */
public class EstadisticasUsuari extends EstadisticasHidato{
    int idUsuari;

    public int getIdUsuari() {
        return idUsuari;
    }

    public void setIdUsuari(int idUsuari) {
        this.idUsuari = idUsuari;
    }

    public EstadisticasUsuari()
    {
        super();
        idUsuari = -1;
    }

    public EstadisticasUsuari( int idHidato,int idUsuari) {
        super(idHidato);
        this.idUsuari = idUsuari;
    }
}
