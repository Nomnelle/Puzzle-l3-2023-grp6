package projet.logicUI;

import projet.modele.game.Grille;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Serial {
    private final String PATH = System.getProperty("user.home") + File.separator + "Puzzle" + File.separator;
    private final String GRIDSAVE = "gridsave.ser";
    Grille grille = null;
    public Serial(Grille grille){
        this.grille = grille;
    }
    public Serial(){

    }

    public void saveGrille(){
        //Save Path
        Path path = Paths.get(PATH);
        if (!Files.exists(path)){
            try {Files.createDirectories(path);
            } catch (IOException e) {System.err.println("Unable to save Directory");}
        }

        ObjectOutputStream oos = null;

        try (FileOutputStream gridStream = new FileOutputStream(PATH+GRIDSAVE)){
            oos = new ObjectOutputStream(gridStream);
            oos.writeObject(grille);

        } catch (IOException e) {
            System.err.println("Unable to save grid");
        }
        finally {
            try {
                if(oos != null) {
                    oos.flush();
                    oos.close();
                }
            } catch (final IOException ex) {
                ex.printStackTrace();
            }
        }
    }
    public Grille deserial(){
        ObjectInputStream ois = null;

        try (final FileInputStream fichierIn = new FileInputStream(PATH+GRIDSAVE)){

            ois = new ObjectInputStream(fichierIn);

            return (Grille) ois.readObject();

        } catch (final java.io.IOException e) {
            e.printStackTrace();
            return null;

        } catch (final ClassNotFoundException e) {
            e.printStackTrace();
            return null;

        } finally {
            try {
                if (ois != null) {
                    ois.close();
                }
            } catch (final IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
