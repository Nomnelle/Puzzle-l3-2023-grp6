package projet.modele.game;
import projet.logicUI.Player;

import java.util.Scanner;

/**
 * The Main class contains the main method to execute the puzzle game without GUI.
 */
public class Main {

    /**
     * Allows the user to input the desired length of the puzzle grid.
     *
     * @param g The grid to be initialized.
     * @return The initialized grid.
     */
    public static Grille entrerLongueur(Grille g){

        Scanner sc = new Scanner(System.in);
        int longueur = 0;

        try{
            System.out.println(" Entrez la longueur voulue de votre taquin, entre 2 et 5 !\n");
            longueur = sc.nextInt();
            if(longueur<2){
                System.out.println("Veuillez rentrer un chiffre d'une valeur de 2 ou plus.\n");
            }else if(longueur>5){
                System.out.println("Veuillez rentrer un chiffre d'une valeur de 5 ou moins.\n");
            }else{
                System.out.printf("La grille sera de longueur %d.\n", longueur);
                g = new Grille(longueur);
            }
        }catch(java.util.InputMismatchException e){
            System.out.println("Veuillez rentrer un nombre entier.\n");
        }

        return g;

    }

    /**
     * The main method to execute the puzzle game.
     *
     * @param args Command-line arguments (not used).
     * @throws InterruptedException Exception thrown if a thread is interrupted.
     */
    public static void main(String[] args) throws InterruptedException {

        System.out.println("Bonjour !");
        boolean correcte = false;
        Grille g = null;

        // Input the length of the puzzle grid
        while(!(correcte)){
           g = entrerLongueur(g);
           if(g!=null){
               correcte = true;
           }
        }

        System.out.println("Génération de la grille...\n");

        System.out.println(g.getGrille());
        correcte = false;

        // Fill the grid with solvable configurations
        while(!correcte){
            g.remplirGrille();
            correcte = g.testerSiGrilleSoluble()&&!g.verifierVictoire();
        }

        boolean game = true;

        Chrono t = new Chrono();

        // Game loop
        while(game) {
            Scanner sc = new Scanner(System.in);
            System.out.println(g);

            System.out.println("Entrez votre mouvement : haut/bas/droite/gauche.\n");
            String input = sc.nextLine();

            g.deplacerCase(input);

            boolean victoire = g.verifierVictoire();

            if(victoire){
                System.out.println("Puzzle résolu ! Bravo!\n");
                game = false;
            }
        }

        t.pauseTime();

        // Save game data to the database
        Player player = new Player(t, g.getNombreCoups(), g.getLongueur()*g.getLongueur());
        player.setScore();
        player.start();
    }
}
