package concurrentqueue;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Concurrentlinkedqueue {
    public static void main(String[] args) {
        // Create a ConcurrentLinkedQueue
        ConcurrentLinkedQueue<Integer> queue = new ConcurrentLinkedQueue<>();

        // Create a thread pool with two threads
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        // Submit the producer and consumer tasks to the thread pool
        executorService.submit(() -> produceItems(queue));
        executorService.submit(() -> consumeItems(queue));

        // Shut down the thread pool
        executorService.shutdown();
    }

    private static void produceItems(ConcurrentLinkedQueue<Integer> queue) {
        for (int i = 0; i < 10; i++) {
            queue.offer(i);
            System.out.println("Produced: " + i);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static void consumeItems(ConcurrentLinkedQueue<Integer> queue) {
        while (true) {
            Integer item = queue.poll();
            if (item != null) {
                System.out.println("Consumed: " + item);
            } else {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}


