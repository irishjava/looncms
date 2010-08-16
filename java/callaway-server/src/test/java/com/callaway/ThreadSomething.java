package com.callaway;

public class ThreadSomething {

    /**
     * @param args
     */
    public static void main(String[] args) {
        AccessThread[] threads = {new AccessThread(), new AccessThread(),
                new AccessThread()};
        for (AccessThread at : threads) {
            at.start();
        }
        Terminator t = new Terminator(threads, 10000);
        t.start();

    }

}
