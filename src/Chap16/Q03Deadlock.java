package Chap16;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.junit.Test;

public class Q03Deadlock {
	@Test
	public void tc1() {
		ChopstickDeadlock cs1 = new ChopstickDeadlock();
		ChopstickDeadlock cs2 = new ChopstickDeadlock();
		ChopstickDeadlock cs3 = new ChopstickDeadlock();
		ChopstickDeadlock cs4 = new ChopstickDeadlock();

		final PhilosopherDeadlock p1 = new PhilosopherDeadlock(cs1, cs2);
		final PhilosopherDeadlock p2 = new PhilosopherDeadlock(cs2, cs3);
		final PhilosopherDeadlock p3 = new PhilosopherDeadlock(cs3, cs4);
		final PhilosopherDeadlock p4 = new PhilosopherDeadlock(cs4, cs1);

		Runnable task1 = new Runnable() {
			public void run() {
				p1.eat();
			}
		};
		
		Runnable task2 = new Runnable() {
			public void run() {
				p2.eat();
			}
		};
		
		Runnable task3 = new Runnable() {
			public void run() {
				p3.eat();
			}
		};
		
		Runnable task4 = new Runnable() {
			public void run() {
				p4.eat();
			}
		};
		
		new Thread(task1).start();
		new Thread(task2).start();
		new Thread(task3).start();
		new Thread(task4).start();
		try {
			Thread.sleep(100000);
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}

class PhilosopherDeadlock {
	private static int count;
	private int id;
	ChopstickDeadlock left, right;
	public PhilosopherDeadlock(ChopstickDeadlock left, ChopstickDeadlock right) {
		this.left = left;
		this.right = right;
		this.id = count++;
	}
	public void eat() {
		try {
			System.out.println("philosopher " + id + " is trying to pick up left chopstick");
			this.left.pick();
			System.out.println("philosopher " + id + " picked up left chopstick");
			
			Thread.sleep(1000);
			
			System.out.println("philosopher " + id + " is trying to pick up right chopstick");
			this.right.pick();
			System.out.println("philosopher " + id + " picked up right chopstick");
			
			System.out.println("philosopher " + id + " is eating");
			
			Thread.sleep(1000);
			
			System.out.println("philosopher " + id + " finished eating");

			this.left.drop();
			System.out.println("philosopher " + id + " droped left chopstick");
			
			Thread.sleep(1000);
			
			this.right.drop();
			System.out.println("philosopher " + id + " droped right chopstick");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}

class ChopstickDeadlock {
	Lock lock = new ReentrantLock();
	public void pick() {
		lock.lock();
	}
	public void drop() {
		lock.unlock();
	}
}