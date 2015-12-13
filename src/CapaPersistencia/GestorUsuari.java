package CapaPersistencia;

import CapaDomini.Usuari;

import java.sql.*;

/**
 * Aquesta classe s'encarrega de les Funcionalitats amb la BD del Usuari
 * @author Dani Armengod
 */
public class GestorUsuari
{

    private final static Connection conn = CapaPersistencia.conn;
    private final static String INSERT_USUARI = "INSERT INTO usuaris (nomUsuari, contrasenya, nomReal) VALUES (?,?,?)";
    private final static String COUNT_USUARI = "SELECT COUNT(*) FROM usuaris WHERE nomUsuari = ?";
    private final static String COUNT_USUARI_ID = "SELECT COUNT(*) FROM usuaris WHERE id=?";
    private final static String DELETE_USUARI = "DELETE FROM usuaris WHERE nomUsuari = ?";
    private final static String SELECT_USUARI_NOMUSUARI = "SELECT * FROM usuaris WHERE nomUsuari = ?";
    private final static String SELECT_USUARI_ID = "SELECT * FROM usuaris WHERE id=?";
    private final static String UPDATE_USUARI = "UPDATE usuaris SET" +
            "    nomUsuari=?," +
            "    contrasenya=?," +
            "    nomReal=?" +
            "WHERE id=?";

    //Inserta un nou usuari a la BD

    public static boolean existeixUsuari(String nomUsuari)
    {
        try (PreparedStatement s = conn.prepareStatement(COUNT_USUARI))
        {
            s.setString(1, nomUsuari);
            ResultSet resSet = s.executeQuery();
            resSet.next();
            if (resSet.getInt(1) >= 2) throw new RuntimeException("Dos usuaris a la Base de Dades amb el mateix nom!!");
            return resSet.getInt(1) == 1;
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }

    public static boolean existeixUsuari(int id)
    {
        if (id == -1)
            return false;
        try (PreparedStatement s = conn.prepareStatement(COUNT_USUARI_ID))
        {
            s.setInt(1, id);
            ResultSet resSet = s.executeQuery();
            resSet.next();
            if (resSet.getInt(1) >= 2) throw new RuntimeException("Dos Usuaris a la Base de Dades amb la mateixa ID!!");
            return resSet.getInt(1) == 1;
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }

    public static Usuari donaUsuari(String nomUsuari)
    {
        Usuari u = null;
        try (PreparedStatement s = conn.prepareStatement(SELECT_USUARI_NOMUSUARI))
        {
            s.setString(1, nomUsuari);
            ResultSet resSet = s.executeQuery();
            if (resSet.next())
            {
                u = new Usuari();
                u.setUniqID(resSet.getInt("id"));
                u.setNomUsuari(resSet.getString("nomUsuari"));
                u.setContrasenya(resSet.getString("contrasenya"));
                u.setNomReal(resSet.getString("nomReal"));
            }
            if (resSet.next()) //Hi ha mes d'un usuari que concorda amb el nom que li ha donat! Error!!!
            {
                throw new RuntimeException("Hi ha mes d'un usuari amb el mateix nom!");
                //Aixo realment no te que ser RuntimeException, sino que hauria de ser normal i handlejada mes amunt...
                // pero *tampoc passara mai* (la taula te UNIQUE constraint), aixi que fuck it.
            }
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
        return u;
    }

    public static Usuari donaUsuari(int id)
    {
        Usuari u = null;
        try (PreparedStatement s = conn.prepareStatement(SELECT_USUARI_ID))
        {
            s.setInt(1, id);
            ResultSet resSet = s.executeQuery();
            if (resSet.next())
            {
                u = new Usuari();
                u.setUniqID(resSet.getInt("id"));
                u.setNomUsuari(resSet.getString("nomUsuari"));
                u.setContrasenya(resSet.getString("contrasenya"));
                u.setNomReal(resSet.getString("nomReal"));
            }
            if (resSet.next())
            {
                throw new RuntimeException("Hi ha mes d'un usuari amb la mateixa ID!");
            }
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
        return u;
    }

    public static int creaUsuari(Usuari u)
    {
        if (existeixUsuari(u.getNomUsuari()))
            return -1;
        //Ja hem comprovat que no hi ha un usuari amb el mateix nom... per tant podem inserir be.
        try (PreparedStatement p = conn.prepareStatement(INSERT_USUARI))
        {
            p.setString(1, u.getNomUsuari());
            p.setString(2, u.getContrasenya());
            p.setString(3, u.getNomReal());
            p.executeUpdate();
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
        return CapaPersistencia.retornaUltimaClauInserida();
    }

    public static boolean eliminaUsuari(String nomUsuari)
    {
        //Per anar be, aquesta funcio nomes l'hauria de poder arribar a cridar l'administrador!!!
        // o potser tambe el usuari per a si mateix (?)
        try (PreparedStatement s = conn.prepareStatement(DELETE_USUARI))
        {
            s.setString(1, nomUsuari);
            int usuarisBorrats = s.executeUpdate();
            if (usuarisBorrats != 1)
            {
                String problema;
                if (usuarisBorrats == 0) return false;
                else problema = String.format("S'han borrat %d usuaris!", usuarisBorrats);
                throw new RuntimeException(problema);
            }
            return true;
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }

    public static boolean modificaUsuari(Usuari u)
    {
        if (!existeixUsuari(u.getUniqID()))
            return false;
        try (PreparedStatement s = conn.prepareStatement(UPDATE_USUARI))
        {
            s.setString(1, u.getNomUsuari());
            s.setString(2, u.getContrasenya());
            s.setString(3, u.getNomReal());
            s.setInt(4, u.getUniqID());
            int modificats = s.executeUpdate();
            if (modificats != 1)
            {
                String problema;
                if (modificats == 0)
                    throw new RuntimeException("No s'ha modificat l'usuari, pero ha passat el check d'existencia.");
                else problema = String.format("S'han modificat %d usuaris!", modificats);
                throw new RuntimeException(problema);
            }
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
        return true;
    }
}
