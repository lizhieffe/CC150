package Chap16;

import org.junit.Test;

public class Q05 {
	private boolean firstFinished = false;
	private boolean secondFinished = false;
	
	public synchronized void first() {
		System.out.println("first()");
		firstFinished = true;
		notifyAll();
	}
	
	public synchronized void second() {
		try {
			while (!firstFinished) {
				wait();
			}
			System.out.println("second()");
			secondFinished = true;
			notifyAll();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public synchronized void third() {
		try {
			while (!secondFinished) {
				wait();
			}
			System.out.println("third()");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@Test
	public void tc1() {
		final Q05 tc = new Q05();
		Runnable task1 = new Runnable() {
			public void run() {
				tc.first();
			}
		};
		Runnable task2 = new Runnable() {
			public void run() {
				tc.second();
			}
		};
		Runnable task3 = new Runnable() {
			public void run() {
				tc.third();
			}
		};
		new Thread(task3).start();
		new Thread(task2).start();
		new Thread(task1).start();
	}
}
