package Chap16;

import java.util.concurrent.Semaphore;

import org.junit.Test;

public class Q05Semaphore {
	Semaphore s1 = new Semaphore(1);
	Semaphore s2 = new Semaphore(1);
	Semaphore s3 = new Semaphore(1);
	
	public Q05Semaphore() {
		try {
			s1.acquire();
			s2.acquire();
			s3.acquire();
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void first() {
		System.out.println("first()");
		s1.release();
	}
	
	public void second() {
		try {
			s1.acquire();
			s1.release();
			System.out.println("second()");
			s2.release();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void third() {
		try {
			s2.acquire();
			s2.release();
			System.out.println("third()");
			s3.release();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void tc1() {
		final Q05Semaphore tc = new Q05Semaphore();
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
		try {
			Thread.sleep(10000);
		}
		catch(InterruptedException e) {
			e.printStackTrace();
		}
	}
}
