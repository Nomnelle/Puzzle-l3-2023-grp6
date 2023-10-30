package modele;

import java.sql.*;
import java.time.LocalTime;
import java.util.ArrayList;

/**
 * @author npnoi
 */
public class BDD {

    Connection con;
    String host;
    String dbName;
    String port;
    String username;
    String password;

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
        String query = "";
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

    public Boolean addData(String nom_Joueur,String temps_Partie, int taille_Puzzle) {
        return false;
    }
}

