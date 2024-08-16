package javatesting.CompleteableFuture;

import java.util.concurrent.CompletableFuture;

public class MultipleCompletableFuture {

    public static void main(String[] args) {
        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> {
            return 10;
        });
        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(() -> {
            return 10;
        });
        CompletableFuture<Integer> future3 = CompletableFuture.supplyAsync(() -> {
            return 10;
        });

        CompletableFuture.allOf(future1,future2,future3).exceptionally(ex -> {
           return null;
        }).thenRun(() -> {
            Integer result1 = future1.join();
            Integer result2 = future1.join();
            Integer result3 = future1.join();
            System.out.println(result1 + result2 + result3);
        });

    }
}
