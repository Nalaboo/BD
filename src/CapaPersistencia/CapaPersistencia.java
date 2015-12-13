package CapaPersistencia;

import java.sql.*;

/**
 * Aquesta classe s'encarrega de verificar i crear les taules de la BD
 * @author Dani Armengod
 */

public class CapaPersistencia
{
    private static final String CREATE_TABLE_USUARIS = "CREATE TABLE IF NOT EXISTS usuaris (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "nomUsuari VARCHAR NOT NULL UNIQUE," +
            "contrasenya VARCHAR NOT NULL," +
            "nomReal VARCHAR" +
            ")";
    private static final String CREATE_TABLE_TAULERS = "CREATE TABLE IF NOT EXISTS taulers (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "tamany INTEGER NOT NULL," +
            "stringCreacio VARCHAR" +
            ")";
    private static final String CREATE_TABLE_HIDATOS = "CREATE TABLE IF NOT EXISTS hidatos (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "idTauler INTEGER," +
            "idTaulerComplert INTEGER," +
            "dificultat VARCHAR NOT NULL," +
            "FOREIGN KEY (idTauler) REFERENCES taulers (id)," +
            "FOREIGN KEY (idTaulerComplert) REFERENCES taulers (id)" +
            ")";
    private static final String CREATE_TABLE_PARTIDES = "CREATE TABLE IF NOT EXISTS partides (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "idUsuari INTEGER NOT NULL," +
            "idHidato INTEGER NOT NULL," +
            "idTaulerProgres INTEGER NOT NULL," +
            "nCelesResoltes INTEGER NOT NULL," +
            "numAjudesUtilitzades INTEGER NOT NULL," +
            "esAcabada BOOLEAN NOT NULL," +
            "temps INTEGER NOT NULL," +
            "FOREIGN KEY (idUsuari) REFERENCES usuaris(id)," +
            "FOREIGN KEY (idHidato) REFERENCES hidato(id)," +
            "FOREIGN KEY (idTaulerProgres) REFERENCES taulers(id)" +
            ")";

    private static final String CREATE_TABLE_ESTADISTICA = "CREATE TABLE IF NOT EXISTS estadistica (" +
            "idUsuari INTEGER NOT NULL," +
            "idHidato INTEGER NOT NULL," +
            "nIntents INTEGER NOT NULL," +
            "copsResolt INTEGER NOT NULL," +
            "temps INTEGER NOT NULL," +
            "nAjudes INTEGER NOT NULL," +
            "FOREIGN KEY (idUsuari) REFERENCES usuaris(id)," +
            "FOREIGN KEY (idHidato) REFERENCES hidato(id)," +
            ")";

    static Connection conn;

    static
    {
        try
        {
            //jbdc:sqlite:<pathAlFitxer.db>
            conn = DriverManager.getConnection("jdbc:sqlite:C:/Users/Maria/ProgramaJava/untitled1/basedades.db");
        }
        catch (SQLException e)
        {
            throw new RuntimeException("No s'ha pogut connectar a la Base de Dades!", e);
        }
        validarBaseDeDades();
    }

    public static int retornaUltimaClauInserida()
    {
        /**
         * Retorna l'ultima clau inserida
         */
        int aRetornar = -1;
        try (Statement s = conn.createStatement())
        {
            ResultSet resSet = s.executeQuery("SELECT last_insert_rowid()");
            if (resSet.next())
                aRetornar = resSet.getInt(1);
            else
                throw new RuntimeException("No hi ha ultima clau inserida?");
        }
        catch (SQLException e)
        {
            throw new RuntimeException("No es pot coneixer l'última clau inserida.", e);
        }
        return aRetornar;
    }

    public static void validarBaseDeDades()
    {
        /**
         * En cas que no estiguin les taules creades a la BD, les crea
         */
        //Executem tots els CREATE TABLE statements per assegurar-nos que la BD esta en condicions d'operar. Exemple:

        //Falta afegir el codi de la resta de taules
        try (Statement statement = conn.createStatement())
        {
            statement.execute(CREATE_TABLE_USUARIS);
            statement.execute(CREATE_TABLE_TAULERS);
            statement.execute(CREATE_TABLE_HIDATOS);
            statement.execute(CREATE_TABLE_PARTIDES);
            statement.execute(CREATE_TABLE_ESTADISTICA);
            //Falta afegir el codi de la resta de taules (e.g. estadistiques)
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }
}
