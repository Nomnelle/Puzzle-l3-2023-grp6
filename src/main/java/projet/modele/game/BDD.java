package projet.modele.game;

import java.sql.*;
import java.time.LocalTime;
import java.util.ArrayList;

/**
 * @author npnoi
 */
public class BDD {

    private Connection con;
    private String host;
    private String dbName;
    private String port;
    private String username;
    private String password;

    public BDD() {
        // avant de se connecter sur la BDD, allez sur ce lien: https://phpmyadmin.alwaysdata.com/
        con = null;
        host = "mysql-puzzle-mick-purson-palich-watrin-mysql.alwaysdata.net";
        dbName = "puzzle-mick-purson-palich-watrin-mysql_Puzzle";
        port = "3306";
        username = "329728";
        password = "Puzzle.2023";
    }

    private void openConnexion() {

        if (this.con != null) {
            this.closeConnexion();
        }

        try {
            String connectUrl = "jdbc:mysql://" + host + ":" + port + "/" + dbName;
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            con = DriverManager.getConnection(connectUrl, username, password);
            System.out.println("Database connection established.");
        } catch (ClassNotFoundException cnfe) {
            System.out.println("Cannot load db driver: com.mysql.jdbc.Driver");
            cnfe.printStackTrace();
        } catch (Exception e) {
            System.out.println("Erreur inattendue");
            e.printStackTrace();
        }

    }

    private void closeConnexion() {
        if (this.con != null) {
            try {
                this.con.close();
                System.out.println("Database connection terminated.");
            } catch (Exception e) {
                /* ignore close errors */
            }
        }
    }

    public ArrayList<String> getTuples() {
        ArrayList<String> res = null;
        String query = "SELECT * FROM Joueur";
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

    /*
     * Insère un ou plusieurs tuples dans la base à partir de la requête passée en paramètre
     * Pour cela, il faut utiliser la méthode executeUpdate dans la classe Statement
     */
    //créer fonction qui permet d'ajouter les données du jeu dans la BDD

    public Boolean addData(String nom_Joueur, int nombre_Coups, String temps_Partie, int taille_Puzzle) {

        boolean resultAddData = false;

        String updateQuery = "INSERT INTO Partie (nombre_Coups, temps_Partie, taille_Puzzle) VALUES('" + nombre_Coups + "', '" + temps_Partie + "', '" + taille_Puzzle + "')";
        String updateQuery2 = "INSERT INTO Joueur (nom_Joueur) VALUES('" + nom_Joueur + "')";
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


