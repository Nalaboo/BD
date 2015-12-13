package CapaPersistencia;

import CapaDomini.Tauler;
import CapaDomini.TaulerComplert;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * Aquesta classe s'encarrega de les Funcionalitats amb la BD del Tauler
 * @author Eduard J. Seoane
 */
public class GestorTauler
{
    private static final Connection conn = CapaPersistencia.conn;
    private static final String INSERT_TAULER = "INSERT INTO taulers (" +
            "tamany," +
            "stringCreacio" +
            ") VALUES (?, ?)";
    private static final String COUNT_TAULER = "SELECT COUNT(*) FROM taulers WHERE id=?";
    private static final String DELETE_TAULER = "DELETE FROM taulers WHERE id=?";
    private static final String SELECT_TAULER = "SELECT * FROM taulers WHERE id=?";
/*
    private static final String UPDATE_TAULER = "UPDATE taulers SET" +
            "tamany=?," +
            "stringCreacio=?" +
            "WHERE id=?";
*/

    public static boolean existeixTauler(int id)
    {
        if (id == -1)
            return false;
        try (PreparedStatement s = conn.prepareStatement(COUNT_TAULER))
        {
            s.setInt(1, id);
            ResultSet resSet = s.executeQuery();
            resSet.next();
            if (resSet.getInt(1) >= 2) throw new RuntimeException("Dos Taulers a la Base de Dades amb la mateixa ID!!");
            return resSet.getInt(1) == 1;
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }

    public static boolean existeixTaulerComplert(int id)
    {
        return existeixTauler(id);
    }

    public static Tauler donaTauler(int id)
    {
        Tauler aRetornar = null;
        try (PreparedStatement p = conn.prepareStatement(SELECT_TAULER))
        {
            p.setInt(1, id);
            ResultSet resSet = p.executeQuery();
            if (resSet.next())
            {
                aRetornar = new Tauler();
                aRetornar.setUniqID(id);
                Scanner textCreacio = new Scanner(resSet.getString("stringCreacio"));
                aRetornar.llegeixRepresentacioTextual(textCreacio);
            }
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
        return aRetornar;

    }

    public static TaulerComplert donaTaulerComplert(int id)
    {
        return new TaulerComplert(donaTauler(id));
    }

    public static int creaTauler(Tauler t)
    {
        try (PreparedStatement p = CapaPersistencia.conn.prepareStatement(INSERT_TAULER))
        {
            p.setInt(1, t.getTamany());
            p.setString(2, t.donaRepresentacioTextual());
            p.executeUpdate();
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
        return CapaPersistencia.retornaUltimaClauInserida();

    }

    public static int creaTaulerComplert(TaulerComplert t)
    {
        return creaTauler(t);
    }

    public static boolean eliminaTauler(int id)
    {
        try (PreparedStatement s = conn.prepareStatement(DELETE_TAULER))
        {
            s.setInt(1, id);
            int taulersBorrats = s.executeUpdate();
            if (taulersBorrats != 1)
            {
                String problema;
                if (taulersBorrats == 0) return false;
                else problema = String.format("S'han borrat %d taulers!", taulersBorrats);
                throw new RuntimeException(problema);
            }
            return true;
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }

    }

    public static boolean eliminaTaulerComplert(int id)
    {
        return eliminaTauler(id);
    }


}
