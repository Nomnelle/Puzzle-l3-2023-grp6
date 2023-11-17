package projet.modele.game;
import java.util.Scanner;

public class Main {

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
                g = Grille.getInstance(longueur);
            }
        }catch(java.util.InputMismatchException e){
            System.out.println("Veuillez rentrer un nombre entier.\n");
        }

        return g;

    }

    public static void main(String[] args) throws InterruptedException {

        System.out.println("Bonjour !");
        boolean correcte = false;
        Grille g = null;
        while(!(correcte)){
           g = entrerLongueur(g);
           if(g!=null){
               correcte = true;
           }
        }

        System.out.println("Génération de la grille...\n");

        System.out.println(g.getGrille());
        correcte = false;

        while(!correcte){
            g.remplirGrille();
            correcte = g.testerSiGrilleSoluble();
        }

        boolean game = true;

        Chrono t = new Chrono();

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


        System.out.print("Entrez votre nom");
        Scanner sc = new Scanner(System.in);

        String nom_Joueur = sc.nextLine();

        BDD bdd = new BDD();

        bdd.addData(nom_Joueur, g.getNombreCoups(), t.toString(), g.getLongueur());


    }

}
