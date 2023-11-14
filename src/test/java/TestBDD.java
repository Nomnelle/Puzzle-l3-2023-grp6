
import org.junit.Test;
import projet.modele.BDD;

import java.util.ArrayList;

public class TestBDD {

    @Test
    private void testOpenConnexion() {
        BDD bdd = new BDD();
    }


    @Test
    private void openConnexion() {

        if (this.con != nul) {
            try {
                this.con.close();
                System.out.println("Database connection terminated.");
            } catch (Exception e) {
                /* ignore close errors */
            }
        }
    }


    public static void main(String[] args) {
        BDD bdd = new BDD();

        // Tester la connexion à la base de données et récupérer tous les tuples
        testerAjoutDonnees(bdd);

        // Tester l'ajout de données dans la table Joueur
        testerAjoutDonnees(bdd);
    }

    private static void testerAjoutDonnees(BDD bdd) {
        ArrayList<String> tuples = bdd.getTuples();

        if (tuples != null) {
            System.out.println("Tuples bien récupérés, avec succès:");
            for (String tuple : tuples) {
                System.out.println(tuple);
            }
        } else {
            System.out.println("La récupération des tuples ne s'est pas réalisée.");
        }
    }

    private static void tester_Ajout_Donnees(BDD bdd) {
        // Ajouter des données de test
        String nom_Joueur = "Joueur1";
        String temps_Partie = "10 minutes";
        int taille_Puzzle = 5;
        int nbCoup = 20;

        // Appeler la méthode addData pour ajouter les données
        boolean ajoutReussi = bdd.ajoutDonnees(nom_Joueur, temps_Partie, taille_Puzzle, nbCoup);

        if (ajoutReussi) {
            System.out.println("Données bien ajoutées avec succès.");
        } else {
            System.out.println("L'ajout des données a échoué.");
        }

        // Tester à nouveau la récupération des tuples après l'ajout
        tester_Connection_Recolter_Donnees_Tuples(bdd);
    }
}