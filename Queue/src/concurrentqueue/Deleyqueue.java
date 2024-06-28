package concurrentqueue;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class DelayedTask implements Delayed {
    private final long delay;
    private final long startTime;
    private final String taskName;

    public DelayedTask(long delay, String taskName) {
        this.delay = delay;
        this.startTime = System.currentTimeMillis() + delay;
        this.taskName = taskName;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        long remaining = startTime - System.currentTimeMillis();
        return unit.convert(remaining, TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(Delayed o) {
        if (this.startTime < ((DelayedTask) o).startTime) {
            return -1;
        } else if (this.startTime > ((DelayedTask) o).startTime) {
            return 1;
        } else {
            return 0;
        }
    }

    public void run() {
        System.out.println("Executing task: " + taskName);
    }
}

 class DelayQueueExample {
    public static void main(String[] args) {
        // Create a DelayQueue
        DelayQueue<DelayedTask> delayQueue = new DelayQueue<>();

        // Create a thread pool with a single thread
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        // Add delayed tasks to the queue
        delayQueue.offer(new DelayedTask(2000, "Task 1"));
        delayQueue.offer(new DelayedTask(1000, "Task 2"));
        delayQueue.offer(new DelayedTask(3000, "Task 3"));

        // Execute the delayed tasks
        executorService.submit(() -> {
            while (true) {
                try {
                    DelayedTask task = delayQueue.take();
                    task.run();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        // Shut down the thread pool
        executorService.shutdown();
    }
}

