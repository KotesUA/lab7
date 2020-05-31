package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Main {

    public static void main(String[] args) {
        //generate threads and iterations numbers
        int threads = 8;
        int iterations = 1000000;
        int threadIterations = iterations/threads;
        int counter = 0;

        //write execution start time
        long startTime = System.nanoTime();

        //create ExecutorService with fixed number of threads
        ExecutorService executor = Executors.newFixedThreadPool(threads);

        //create a futures list
        List<Future<Integer>> list = new ArrayList<>();

        //create callable, which is a MonteCarlo object
        Callable<Integer> callable = new MonteCarlo(threadIterations);

        //add futures in number of threads
        for (int i = 0; i < threads; i++) {
            Future<Integer> future = executor.submit(callable);
            list.add(future);
        }

        //execute all futures and get our counters
        for (Future<Integer> f : list) {
            try {
                counter += f.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

        //shut down the executor, count pi and end time
        executor.shutdown();
        double pi = (4.0 * counter) / iterations;
        long endTime = System.nanoTime();

        //print results
        System.out.println("Pi: " + pi);
        System.out.println("Threads: " + threads);
        System.out.println("Iterations: " + iterations);
        System.out.println("Time consumed: " + ((endTime - startTime)/1000000) + " ms");
    }
}