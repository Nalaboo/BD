package CapaPersistencia;

import CapaDomini.Hidato;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Aquesta classe s'encarrega de les Funcionalitats amb la BD del Hidato
 * @author Maria Vives
 */
public class GestorHidato
{
    private static final Connection conn = CapaPersistencia.conn;
    private static final String COUNT_HIDATO = "SELECT COUNT(*) FROM hidatos WHERE id = ?";
    private static final String INSERT_HIDATO = "INSERT INTO hidatos (" +
            "idTauler," +
            "idTaulerComplert," +
            "dificultat" +
            ") VALUES (?,?,?)";
    private final static String DELETE_HIDATO = "DELETE FROM hidatos WHERE id = ?";
    private final static String SELECT_HIDATO = "SELECT * FROM hidatos WHERE id = ?";
    private final static String SELECT_ALL_ID_HIDATO = "SELECT id FROM hidatos";
    private final static String SELECT_HIDATOS_AMB_DIFICULTAT = "SELECT id FROM hidatos WHERE dificultat = ?";


    /**
     * Aquesta funcio mira si ja existeix un Hidato amb la id pasada per parametre a la BD
     * @param id
     * @return Retorna si existeix un Hidato amb aquella id a la BD true
     */
    public static boolean existeixHidato(int id)
    {
        try (PreparedStatement s = conn.prepareStatement(COUNT_HIDATO))
        {
            s.setInt(1, id);
            ResultSet resSet = s.executeQuery();
            resSet.next();
            if (resSet.getInt(1) >= 2) throw new RuntimeException("Dos Hidatos a la Base de Dades amb la mateixa ID!!");
            return resSet.getInt(1) == 1;
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }

    /**
     * Aquesta funcio retorna l'Hidato amb id que es passa per parametre
     * @param id
     * @return Retorna l'Hidato que te la id pasa per parametre
     */
    public static Hidato donaHidato(int id)
    {
        /**
         * Retorna un hidato amb id ‘id’
         */
        Hidato aRetornar = null;
        try (PreparedStatement p = conn.prepareStatement(SELECT_HIDATO))
        {
            p.setInt(1, id);
            ResultSet resSet = p.executeQuery();
            if (resSet.next())
            {
                aRetornar = new Hidato();
                aRetornar.setUniqID(id);
                int idTauler = resSet.getInt("idTauler");
                aRetornar.setTauler(GestorTauler.donaTauler(idTauler));
                int idTaulerComplert = resSet.getInt("idTaulerComplert");
                aRetornar.setTaulerComplert(GestorTauler.donaTaulerComplert(idTaulerComplert));
                aRetornar.setDificultat(resSet.getString("dificultat"));
            }
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
        return aRetornar;
    }

    /**
     * Aquesta funcio guarda l'Hidato passat per parametre a la BD
     * @param h
     * @return Retorna la ultima clau inserida, per saber la id de l'Hidato
     */
    public static int creaHidato(Hidato h)
    {
        /**
         * Guarda a la BD a la taula hidato l’hidato h
         */
        if (!GestorTauler.existeixTauler(h.getIDTauler()))
        {
            int nouIDTauler = GestorTauler.creaTauler(h.getTauler());
            h.setIDTauler(nouIDTauler);
        }
        else throw new RuntimeException("Ja existia el tauler?");
        if (!GestorTauler.existeixTaulerComplert(h.getIDTaulerComplert()))
        {
            int nouIDTaulerComplert = GestorTauler.creaTaulerComplert(h.getTaulerComplert());
            h.setIDTaulerComplert(nouIDTaulerComplert);
        }
        else throw new RuntimeException("Ja existia el tauler complert?");

        try (PreparedStatement p = CapaPersistencia.conn.prepareStatement(INSERT_HIDATO))
        {
            p.setInt(1, h.getIDTauler());
            p.setInt(2, h.getIDTaulerComplert());
            p.setString(3, h.getDificultat());
            p.executeUpdate();
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
        return CapaPersistencia.retornaUltimaClauInserida();
    }

    /**
     * Aquesta funcio rep la id de l'Hidato que es vol eliminar
     * @param id
     * @return Retorna false si no s'ha esborrat cap Hidato, diu quants hidatos s'han esborrat, en cas que hagi sigut mes de un, i en cas que s'hagi esborrat nomes un, retorna true
     */
    public static boolean eliminaHidato(int id)
    {
        /**
         * Elimina de la BD l’hidato amb id ‘idhidato’
         */
        Hidato h = donaHidato(id);
        GestorTauler.eliminaTauler(h.getIDTauler());
        GestorTauler.eliminaTaulerComplert(h.getIDTaulerComplert());

        try (PreparedStatement s = conn.prepareStatement(DELETE_HIDATO))
        {
            s.setInt(1, id);
            int hidatosBorrats = s.executeUpdate();
            if (hidatosBorrats != 1)
            {
                String problema;
                if (hidatosBorrats == 0) return false;
                else problema = String.format("S'han borrat %d hidatos!", hidatosBorrats);
                throw new RuntimeException(problema);
            }
            return true;
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }

    public static boolean modificaHidato(Hidato h)
    {
        return false; //todo what
    }

    /**
     * Aquesta funcio dona totes les id dels Hidatos que hi ha a la BD
     * @return Retorna una llista amb totes les id
     */
    public static List<Integer> donaTotesID()
    {
        List<Integer> aRetornar = new ArrayList<>(30);
        try (Statement s = conn.createStatement())
        {
            ResultSet resSet = s.executeQuery(SELECT_ALL_ID_HIDATO);
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

    public static ArrayList<Integer> donaPerDificultat (String dificultat){
        ArrayList<Integer> array = new ArrayList<Integer>();
        try (PreparedStatement s = conn.prepareStatement(SELECT_HIDATOS_AMB_DIFICULTAT))
        {
            s.setString(1, dificultat);
            ResultSet resSet = s.executeQuery();
            while (resSet.next()) {
                int i = resSet.getInt("idHidato");
                array.add(i);
            }

        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
        return array;
    }

    //    public static List<Hidato> getHidatos() {
    //        List<Hidato> lista = new ArrayList<>();
    //        String sDriverName = "org.sqlite.JDBC";
    //        try {
    //            Class.forName(sDriverName);
    //        } catch (ClassNotFoundException e) {
    //            e.printStackTrace();
    //        }
    //        try (Connection c = DriverManager.getConnection("jdbc:sqlite:C:/Users/Maria/ProgramaJava/projecte_Skrubs/basedades.db")) {
    //            PreparedStatement consulta = c.prepareStatement("SELECT * FROM hidato ORDER BY idhidato");
    //            try (ResultSet rs = consulta.executeQuery()) {
    //                while (rs.next()) {
    //                    Integer id = rs.getInt("idhidato");
    //                    Integer idtaulerinia = rs.getInt("id_taulerini");
    //                     Integer idtauleromplerta = rs.getInt("id_tauleromplert");
    //                    String dif = rs.getString("dificultad");
    //                    Hidato h = new Hidato();
    //                    h.setUniqID(id);
    //                    h.setDificultat(dif);
    //                   // h.setTauler(null);
    //                   // h.setTaulerComplert(null);
    //                    lista.add(h);
    //                }
    //                return lista;
    //            } catch (SQLException e) {
    //                throw new RuntimeException(e);
    //            }
    //        } catch (SQLException e) {
    //            e.printStackTrace();
    //        }
    //        return lista;
    //    }

    //      public static Hidato getHidato(Integer idhidato) {
    //        Hidato h = new Hidato();
    //        String sDriverName = "org.sqlite.JDBC";
    //        try {
    //            Class.forName(sDriverName);
    //        } catch (ClassNotFoundException e) {
    //            e.printStackTrace();
    //        }
    //        try (Connection c = DriverManager.getConnection("jdbc:sqlite:C:/Users/Maria/ProgramaJava/projecte_Skrubs/basedades.db")) {
    //            PreparedStatement consulta = c.prepareStatement("SELECT * FROM hidato WHERE idhidato  = '" + idhidato + "'");
    //            try (ResultSet rs = consulta.executeQuery()) {
    //                while (rs.next()) {
    //                    Integer idh = rs.getInt("idhidato");
    //                    Integer idtiini = rs.getInt("id_taulerini");
    //                    Integer idtomp = rs.getInt("id_tauleromplert");
    //                    String dificultad  = rs.getString("dificultad");
    //                    h.setUniqID(idh);
    //                    h.setDificultat(dificultad);
    //                    TauleriniCP.getTaulerini(idtiini);
    //                    TaulerOmplertCP.getTaulerOmp(idtomp);
    //                    h.setTauler(TauleriniCP.getTaulerini(idtiini));
    //                    h.setTaulerComplert(TaulerOmplertCP.getTaulerOmp(idtomp));
    //                }
    //                return h;
    //            } catch (SQLException e) {
    //                throw new RuntimeException(e);
    //            }
    //        } catch (SQLException e) {
    //            e.printStackTrace();
    //        }
    //        return h;
    //    }
}