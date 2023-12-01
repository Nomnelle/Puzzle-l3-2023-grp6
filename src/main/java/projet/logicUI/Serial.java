package projet.logicUI;

import projet.modele.game.Grille;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Serialisation
 */
public class Serial {
    private static final String PATH = System.getProperty("user.home") + File.separator + "Puzzle" + File.separator;
    private static final String GRIDSAVE = "gridsave.ser";
    private final Grille grid;

    /**
     * Saving controller
     * @param grille the grid
     */
    public Serial(Grille grille){
        this.grid = grille;
    }

    /**
     * Loading controller
     */
    public Serial(){this.grid = null;}

    /**
     * @return true if the save exist
     */
    public static boolean verifySave(){
        Path path = Paths.get(PATH+GRIDSAVE);
        return Files.exists(path);
    }

    /**
     * This method allow to save the current grid on a .ser file
     */
    public void saveGrid(){
        //Save Path
        Path path = Paths.get(PATH);
        if (!Files.exists(path)){
            try {Files.createDirectories(path);
            } catch (IOException e) {System.err.println("Unable to save Directory");}
        }

        //Save file
        ObjectOutputStream oos;
        try (FileOutputStream gridStream = new FileOutputStream(PATH+GRIDSAVE)){ //don't need manual flush and close
            oos = new ObjectOutputStream(gridStream);
            oos.writeObject(grid);

        } catch (IOException e) {
            System.err.println("Unable to save grid");
        }
    }

    /**
     * This method load the saved grid by a .ser file
     *
     * @return deserialized grid
     */
    public Grille deserialize(){
        try (final FileInputStream fichierIn = new FileInputStream(PATH+GRIDSAVE)){
            ObjectInputStream ois = new ObjectInputStream(fichierIn);
            return (Grille) ois.readObject();

        } catch (final IOException | ClassNotFoundException e) {
            return null;
        }
    }
}