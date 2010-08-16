package com.callaway.service;

import com.callaway.AccessThread;
import com.callaway.Terminator;
import org.junit.Test;

public class ThreadingIntegrationTest {


    @Test(timeout = 3000)
    public void testThreading() throws InterruptedException {
        AccessThread[] threads = {new AccessThread(), new AccessThread(),
                new AccessThread()};

        for (AccessThread at : threads) {
            at.start();
        }
        Terminator terminator = new Terminator(threads, 1000);
        terminator.start();
        while (true) {
            Thread.sleep(1000);
            System.err.println("checking if we should kill.");
            if (!terminator.isFinished()) {
                continue;
            } else {
                break;
            }
        }
    }
}
