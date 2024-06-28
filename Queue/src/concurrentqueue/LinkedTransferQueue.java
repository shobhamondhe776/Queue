package concurrentqueue;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TransferQueue;

public class LinkedTransferQueue {
	public static void main(String[] args)throws InterruptedException {
		LinkedTransferQueue transferQueue = new LinkedTransferQueue();

					// Adding elements to the transfer queue
					transferQueue.put("First element");
					transferQueue.put("Second element");
					transferQueue.put("Third element");

					// Transferring elements between threads
					new Thread(() -> {
						String element = transferQueue.take();
						System.out.println("Consumed element in Thread 1: " + element);
					}).start();

					transferQueue.transfer("Fourth element");

					new Thread(() -> {
						String element = transferQueue.take();
						System.out.println("Consumed element in Thread 2: " + element);
					}).start();
				}

	private void transfer(String string) {
		// TODO Auto-generated method stub
		
	}

	private String take() {
		// TODO Auto-generated method stub
		return null;
	}

	private void put(String string) {
		// TODO Auto-generated method stub
		
	}
			}
		