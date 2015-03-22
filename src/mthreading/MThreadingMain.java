package mthreading;

public class MThreadingMain {
	
	static boolean firstThreadStopped = false;
	static boolean secondThreadStopped = false;
	static String string;

	public static void main(String[] args) {

		Thread firstThread = new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				for (int i = 0; i < 1000; i++)
					string = string + "a";
				firstThreadStopped = true;
			}
		});

		Thread secondThread = new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				for (int i = 0; i < 1000; i++)
					string = string + "b";
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
		
		System.out.println(string.length());
	}
}
