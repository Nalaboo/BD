package CapaPersistencia;

import CapaDomini.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Aquesta classe s'encarrega de les Funcionalitats amb la BD de la Partida
 * @author Oriol Giralt
 */
public class GestorPartida
{
    private static final Connection conn = CapaPersistencia.conn;

    private static final String INSERT_PARTIDA = "INSERT INTO partides (" +
            "idUsuari," +
            "idHidato," +
            "idTaulerProgres," +
            "nCelesResoltes," +
            "numAjudesUtilitzades," +
            "esAcabada," +
            "temps," +
            ") VALUES (?, ?, ?, ?, ?, ?,?,?)";
    private static final String COUNT_PARTIDA = "SELECT COUNT(*) FROM partides WHERE id=?";
    private static final String DELETE_PARTIDA = "DELETE FROM partides WHERE id=?";
    private static final String SELECT_PARTIDA = "SELECT * FROM partides WHERE id=?";
    private static final String UPDATE_PARTIDA = "UPDATE partides SET " +
            "idTaulerProgres=?," +
            "nCelesResoltes=?," +
            "numAjudesUtilitzades=?," +
            "esAcabada=?," +
            "temps=? WHERE id=?";
    private static final String SELECT_ALL_ID_PARTIDA = "SELECT id FROM partides";

    public static boolean existeixPartida(int id)
    {
        /**
         * Retorna si existeix una Partida amb l'id passat per paràmetres o no
         */
        if (id == -1)
            return false;
        try (PreparedStatement s = conn.prepareStatement(COUNT_PARTIDA))
        {
            s.setInt(1, id);
            ResultSet resSet = s.executeQuery();
            resSet.next();
            if (resSet.getInt(1) >= 2)
                throw new RuntimeException("Dos Partides a la Base de Dades amb la mateixa ID!!");
            return resSet.getInt(1) == 1;
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }

    public static Partida donaPartida(int id)
    {
        /**
         * Retorna una Partida amb id igual a la id donada per paràmetre
         */
        Partida aRetornar = null;

        try (PreparedStatement p = conn.prepareStatement(SELECT_PARTIDA))
        {
            p.setInt(1, id);
            ResultSet resSet = p.executeQuery();
            if (resSet.next())
            {
                aRetornar = new Partida();
                aRetornar.setUniqID(resSet.getInt("id"));
                Usuari u = GestorUsuari.donaUsuari(resSet.getInt("idUsuari"));
                aRetornar.setUsuari(u);
                Hidato h = GestorHidato.donaHidato(resSet.getInt("idHidato"));
                aRetornar.setHidato(h);
                Tauler t = GestorTauler.donaTauler(resSet.getInt("idTaulerProgres"));
                aRetornar.setTaulerProgres(t);
                aRetornar.setnCelesResoltes(resSet.getInt("nCelesResoltes"));
                aRetornar.setNumAjudesUtilitzades(resSet.getInt("numAjudesUtilitzades"));
                aRetornar.setEsAcabada(resSet.getBoolean("esAcabada"));
                aRetornar.setTemps(resSet.getInt("temps"));
            }
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
        return aRetornar;
    }

    public static int creaPartida(Partida p)
    {
        if (!GestorUsuari.existeixUsuari(p.getIDUsuari()))
            throw new RuntimeException("Intentes crear una partida i el usuari que la juga ni existeix?");

        if (!GestorHidato.existeixHidato(p.getIDHidato())) //pot existir a priori (cas jugar existent) o no (cas crear nou + jugar) todo no m'ho crec
        {
            int nouIDHidato = GestorHidato.creaHidato(p.getHidato());
            p.setIDHidato(nouIDHidato);
        }

        if (!GestorTauler.existeixTauler(p.getIDTaulerProgres())) //mai hauria d'existir a priori
        {
            int nouIDTaulerProgres = GestorTauler.creaTauler(p.getTaulerProgres());
            p.setIDTaulerProgres(nouIDTaulerProgres);
        }
        else throw new RuntimeException("Ja existia el tauler del progres? weeird");

        try (PreparedStatement ps = conn.prepareStatement(INSERT_PARTIDA))
        {
            ps.setInt(1, p.getIDUsuari());
            ps.setInt(2, p.getIDHidato());
            ps.setInt(3, p.getIDTaulerProgres());
            ps.setInt(4, p.getnCelesResoltes());
            ps.setInt(5, p.getNumAjudesUtilitzades());
            ps.setBoolean(6, p.esAcabada());
            ps.setInt(7, p.getTemps());
            ps.executeUpdate();
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
        return CapaPersistencia.retornaUltimaClauInserida();
    }

    public static boolean eliminaPartida(int id)
    {
        return false; //todo acabar
    }

    public static void modificaPartida(Partida p)
    {
        //todo: aixo esborra les dades (taulerProgres) velles i en crea de noves. Hauria de ser capaç de modificar l'estat.
        if (!existeixPartida(p.getUniqID()))
        {
            throw new RuntimeException("Vols que modifiqui una partida que no es present a la BD?");
        }
        GestorTauler.eliminaTauler(p.getIDTaulerProgres());

        try (PreparedStatement ps = conn.prepareStatement(UPDATE_PARTIDA))
        {
            int novaIDTaulerProgres = GestorTauler.creaTauler(p.getTaulerProgres());
            ps.setInt(1,novaIDTaulerProgres);
            ps.setInt(2,p.getnCelesResoltes());
            ps.setInt(3,p.getNumAjudesUtilitzades());
            ps.setBoolean(4, p.esAcabada());
            ps.setInt(5,p.getTemps());
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }

    public static List<Integer> donaTotesID()
    {
        List<Integer> aRetornar = new ArrayList<>(30);
        try (Statement s = conn.createStatement())
        {
            ResultSet resSet = s.executeQuery(SELECT_ALL_ID_PARTIDA);
            while (resSet.next())
            {
                aRetornar.add(resSet.getInt("id"));
            }
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
        return aRetornar;
    }

}
