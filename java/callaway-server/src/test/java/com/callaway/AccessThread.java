package com.callaway;

import org.apache.commons.lang.math.RandomUtils;

public class AccessThread extends Thread {

    private int num;
    private boolean running = true;

    public AccessThread() {
        this.num = RandomUtils.nextInt(890);
    }

    @Override

    public void run() {
        while (running) {
            System.err.println("printing a number: " + num);
            try {
                sleep(250);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.err.println(num + "out of the running");
    }

    public void terminate() {
        running = false;
    }
}