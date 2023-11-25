package projet.modele.game;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.util.Duration;

/**
 * A class representing a timer (Chrono) that extends Thread. It can be used to measure elapsed time
 * or as a countdown timer with a graphical user interface.
 */
public class Chrono extends Thread {

    private int heure;  //hours
    private int minute;  //minutes
    private int seconde;  //seconds
    private long startingTime;  //time of reference
    private volatile boolean decompte;  //// Flag indicating whether the timer is counting down
    private Label timeUI;  // Graphical user interface element for displaying the time

    /**
     * Constructs a Chrono object for measuring elapsed time.
     * The timer starts automatically upon instantiation.
     */
    public Chrono() {
        this.heure = 0;
        this.minute = 0;
        this.seconde = 0;
        this.decompte = true;
        this.setDaemon(true);
        startingTime = System.currentTimeMillis();
        this.start();
    }

    /**
     * Constructs a Chrono object for displaying time on a graphical user interface.
     * The timer starts automatically upon instantiation.
     *
     * @param label The Label element used for displaying the time.
     */
    public Chrono(Label label){ //Controller UI
        this.timeUI = label;
        this.heure = 0;
        this.minute = 0;
        this.seconde = 0;
        this.decompte = false;
        this.setDaemon(true);
        startingTime = System.currentTimeMillis();
        this.start();
    }

    /**
     * Gets the current hour value of the timer.
     *
     * @return The current hour value.
     */
    public int getHeure() {
        return heure;
    }

    /**
     * Gets the current minute value of the timer.
     *
     * @return The current minute value.
     */
    public int getMinute() {
        return minute;
    }

    /**
     * Gets the current second value of the timer.
     *
     * @return The current second value.
     */
    public int getSeconde() {
        return seconde;
    }

    /**
     * Gets the status of the countdown flag.
     *
     * @return True if the timer is counting down, false otherwise.
     */
    public boolean getDecompte() {
        return decompte;
    }

    /**
     * Pauses the timer.
     */
    public void pauseTime(){
        this.decompte = false;
    }

    /**
     * Resumes the timer.
     */
    public void goTime(){
        this.decompte = true;
    }

    /**
     * Overrides the toString method to provide a formatted string representation of the timer.
     *
     * @return A formatted string displaying the current time.
     */
    @Override
    public String toString() {

        String stringHeure;
        String stringMinute;
        String stringSeconde;

        if(heure <= 9) {
            stringHeure = "0" + heure;
        }else{
            stringHeure = String.valueOf(heure);
        }

        if(minute <= 9) {
            stringMinute = "0" + minute;
        }else{
            stringMinute = String.valueOf(minute);
        }

        if(seconde <= 9) {
            stringSeconde = "0" + seconde;
        }else{
            stringSeconde = String.valueOf(seconde);
        }
        return stringHeure + ":" + stringMinute + ":" + stringSeconde;

    }

    /**
     * Overrides the run method to continuously update the timer.
     */
    @Override
    public void run() {
        if(this.timeUI!=null){
            labelUI();
        }
        while(true) {
            if(decompte){
                if ((System.currentTimeMillis()- startingTime) >= 1000) { // Update time in decompte mode
                    startingTime = System.currentTimeMillis();
                    seconde++;
                    if (seconde >= 60) {
                        seconde = 0;
                        minute++;
                    }
                    if (minute >= 60) {
                        minute = 0;
                        heure++;
                    }
                }
            }else{
                startingTime = System.currentTimeMillis();
            }
        }

    }

    /**
     * Resets the timer to zero.
     */
    public void reset(){
        this.heure = 0;
        this.minute = 0;
        this.seconde = 0;
    }

    /**
     * Updates the graphical user interface Label with the current time.
     */
    private void labelUI(){
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> Platform.runLater(() -> timeUI.setText(toString()))));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }
}
