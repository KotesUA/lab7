package com.company;

import java.util.concurrent.Callable;

public class MonteCarlo implements Callable<Integer> {

    //number of dots to "throw"
    private int N;

    //this is class constructor
    public MonteCarlo(int n) {
        this.N = n;
    }

    //"throw" N dots and count those are inside circle
    @Override
    public Integer call() {
        int counter = 0;
        for (int i = 0; i < this.N; ++ i) {
            double x = Math.random();
            double y = Math.random();
            if (x * x + y * y <= 1) {
                counter++;
            }
        }
        return counter;
    }
}