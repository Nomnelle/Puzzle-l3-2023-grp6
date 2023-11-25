package projet.modele.game;

import java.util.ArrayList;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;


/**
 * The BDD class handles database operations for the puzzle game.
 */
public class BDD {

    private Connection con;  // Database connection
    private String host;  // Database host address
    private String dbName;  // Database name
    private String port;  // Database port
    private String username;  // Database username
    private String password;  // Database password

    /**
     * Constructs a BDD object with default database connection details.
     * Note: Before connecting to the database, visit the provided link.
     */
    public BDD() {
        // avant de se connecter sur la BDD, allez sur ce lien: https://phpmyadmin.alwaysdata.com/
        con = null;
        host = "mysql-puzzle-mick-purson-palich-watrin-mysql.alwaysdata.net";
        dbName = "puzzle-mick-purson-palich-watrin-mysql_puzzle";
        port = "3306";
        username = "329728";
        password = "Puzzle.2023";
    }

    /**
     * Opens a new database connection. If a connection already exists, it is closed first.
     */
    private void openConnexion() {

        if (this.con != null) {
            this.closeConnexion();
        }

        try {
            String connectUrl = "jdbc:mysql://" + host + ":" + port + "/" + dbName;
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            this.con = DriverManager.getConnection(connectUrl, username, password);
            System.out.println("Database connection established.");
        } catch (ClassNotFoundException cnfe) {
            System.out.println("Cannot load db driver: com.mysql.jdbc.Driver");
            cnfe.printStackTrace();
        } catch (Exception e) {
            System.out.println("Erreur inattendue");
            e.printStackTrace();
        }

    }

    /**
     * Closes the current database connection if it exists.
     */
    private void closeConnexion() {
        if (this.con != null) {
            try {
                this.con.close();
                System.out.println("Database connection terminated.");
            } catch (Exception e) {
                //ignore close errors
            }
        }
    }

    /**
     * Retrieves tuples from the database based on the provided query.
     *
     * @param query The SQL query to retrieve tuples.
     * @return An ArrayList containing strings representing tuples.
     */
    public ArrayList<String> getTuples(String query) {
        ArrayList<String> res = null;
        try {
            this.openConnexion();
            Statement stmt = this.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            ResultSetMetaData metadata = rs.getMetaData(); // permet de récupérer les noms des colonnes des tuples en sortie de la requête
            String tuple;
            res = new ArrayList<>();
            while (rs.next()) {
                tuple = "";
                for (int i = 1; i <= metadata.getColumnCount(); i++) {
                    tuple += rs.getString(i);
                    if (i < metadata.getColumnCount()) {
                        tuple += ":";
                    }
                }
                res.add(tuple);
            }
            stmt.close();
        } catch (SQLException e) {
            System.out.println("Probleme avec la requete");
        } finally {
            this.closeConnexion();
        }
        return res;
    }

    /**
     * Inserts game data into the database.
     *
     * @param nom_Joueur The player's name.
     * @param nombre_Coups The number of moves in the game.
     * @param temps_Partie The duration of the game.
     * @param taille_Puzzle The size of the puzzle.
     * @return True if the data insertion is successful, false otherwise.
     */
    public Boolean addData(String nom_Joueur, int nombre_Coups, String temps_Partie, int taille_Puzzle) {

        boolean resultAddData = false;

        String updateQuery = "INSERT INTO Partie (nombre_Coups, temps_Partie, taille_Puzzle) VALUES('" + nombre_Coups + "', '" + temps_Partie + "', '" + taille_Puzzle+ "');";
        String updateQuery2 = "INSERT INTO Joueur (nom_Joueur, idPartie) VALUES('" + nom_Joueur + "', LAST_INSERT_ID());";
        try {
            this.openConnexion();
            Statement stmt = this.con.createStatement();
            int n = stmt.executeUpdate(updateQuery);
            System.out.println(n + " tuples inserés dans la table Partie.");
            n = stmt.executeUpdate(updateQuery2);
            System.out.println(n + " tuples inseré dans la table Joueur.");
            stmt.close();
            resultAddData = true;
        } catch (SQLException e) {
            System.out.println("Probleme avec la requete d'insertion");

        } finally {
            this.closeConnexion();
        }
        return resultAddData;
    }
}


