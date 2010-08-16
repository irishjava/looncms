package com.callaway;


public class Terminator extends Thread {

    private static final int TIME_DIVISIONS = 5;
    private AccessThread[] threads;
    private final int milliseconds;
    private boolean finished = false;

    public Terminator(AccessThread[] threads, int milliseconds) {
        this.threads = threads;
        this.milliseconds = milliseconds;
    }

    public boolean isFinished() {
        return finished;
    }


    int counter = 0;

    @Override
    public void run() {
        while (true) {
            counter++;
            if (counter > TIME_DIVISIONS) {
                System.err.println("Terminator kills everything");
                for (AccessThread at : threads) {
                    at.terminate();
                }
                finished = true;
                return;
            }
            try {
                sleep(milliseconds / TIME_DIVISIONS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
 