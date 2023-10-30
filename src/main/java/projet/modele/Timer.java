package projet.modele;


public class Timer extends Thread {

    private int heure;
    private int minute;
    private int seconde;
    private boolean decompte;

    public Timer() {

        this.heure = 0;
        this.minute = 0;
        this.seconde = 0;
        this.decompte = true;

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

    public void setDecompte(boolean decompte) {
        this.decompte = decompte;
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

        return "Le décompte est : " + stringHeure + " : " + stringMinute + " : " + stringSeconde;

    }

    @Override
    public void run() {

        while(true) {
            if(decompte) {
                try {
                    Thread.sleep(1000);
                    seconde ++;
                    if(seconde >= 60) {
                        seconde = 0;
                        minute ++;
                    }
                    if(minute >= 60) {
                        minute = 0;
                        heure ++;
                    }

                } catch (InterruptedException e) {
                    throw new RuntimeException(e);

                }

            }



        }

    }

}
