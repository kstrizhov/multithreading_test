package mthreading;

import java.util.ArrayList;

public class MThreadingMain {
	
	static boolean firstThreadStopped = false;
	static boolean secondThreadStopped = false;
	static ArrayList<Integer> list = new ArrayList<Integer>();

	public static void main(String[] args) {

		Thread firstThread = new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				for (int i = 0; i < 1000; i++)
					list.add(i);
				firstThreadStopped = true;
			}
		});

		Thread secondThread = new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				for (int i = 0; i < 1000; i++)
					list.add(100 * i);
				secondThreadStopped = true;
			}
		});
		
		firstThread.start();
		secondThread.start();
		
		while(!firstThreadStopped || !secondThreadStopped) {
			System.out.println("working...");
		}
		
		firstThread.interrupt();
		secondThread.interrupt();
		
		System.out.println(list.size());
	}
}
