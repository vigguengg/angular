package javatesting;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

public class ThreadusingCompletableFuture {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        IntStream.rangeClosed(1,10).forEach(i -> {
            CompletableFuture<Integer> oddcompletableFuture = CompletableFuture.completedFuture(i).thenApplyAsync(integer -> {
                if (integer % 2 != 0) {

                    System.out.println("Thread current " + Thread.currentThread().getName() + " value " + integer);
                }
                return integer;
            },executorService);
            oddcompletableFuture.join();

            CompletableFuture<Integer> evencompletableFuture = CompletableFuture.completedFuture(i).thenApplyAsync(integer -> {
                if (integer % 2 == 0) {

                    System.out.println("Thread current" + Thread.currentThread().getName() + " value " + integer);
                }
                return integer;
            },executorService);
            evencompletableFuture.join();

        });
        executorService.shutdown();
    }

}
