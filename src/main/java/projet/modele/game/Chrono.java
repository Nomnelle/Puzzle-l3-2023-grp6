package projet.modele.game;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.util.Duration;

public class Chrono extends Thread {

    private int heure;
    private int minute;
    private int seconde;
    private long startingTime;
    private volatile boolean decompte;
    private Label timeUI;

    public Chrono() {
        this.heure = 0;
        this.minute = 0;
        this.seconde = 0;
        this.decompte = true;
        this.setDaemon(true);
        startingTime = System.currentTimeMillis();
        this.start();
    }
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

    public int getHeure() {
        return heure;
    }

    public int getMinute() {
        return minute;

    }

    public int getSeconde() {
        return seconde;

    }

    public boolean getDecompte() {
        return decompte;
    }

    public void pauseTime(){
        this.decompte = false;
    }

    public void goTime(){
        this.decompte = true;
    }

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

    @Override
    public void run() {
        if(this.timeUI!=null){
            labelUI();
        }
        while(true) {
            if(decompte){
                if ((System.currentTimeMillis()- startingTime) >= 1000) {
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
    public void reset(){
        this.heure = 0;
        this.minute = 0;
        this.seconde = 0;
    }
    private void labelUI(){
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> Platform.runLater(() -> timeUI.setText(toString()))));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }
}
