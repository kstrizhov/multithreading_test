package mthreading;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class MThreadingMain {

	static ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock(true);

	static boolean firstThreadStopped = false;
	static boolean secondThreadStopped = false;
	
	static String string = "";

	public static void main(String[] args) {

		Thread firstThread = new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				for (int i = 0; i < 1000; i++) {
					rwLock.writeLock().lock();
					try {
						string = string + "a";
					} finally {
						rwLock.writeLock().unlock();
					}
				}
				firstThreadStopped = true;
			}
		});

		Thread secondThread = new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				for (int i = 0; i < 1000; i++) {
					rwLock.writeLock().lock();
					try {
						string = string + "b";
					} finally {
						rwLock.writeLock().unlock();
					}
				}
				secondThreadStopped = true;
			}
		});

		firstThread.start();
		secondThread.start();

		while (!firstThreadStopped || !secondThreadStopped) {
			System.out.println("working...");
		}

		firstThread.interrupt();
		secondThread.interrupt();

		System.out.println(string.length());
	}
}
