package Chap16;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.junit.Test;

public class Q03 {
	@Test
	public void tc1() {
		Chopstick cs1 = new Chopstick();
		Chopstick cs2 = new Chopstick();
		Chopstick cs3 = new Chopstick();
		Chopstick cs4 = new Chopstick();

		final Philosopher p1 = new Philosopher(cs1, cs2);
		final Philosopher p2 = new Philosopher(cs2, cs3);
		final Philosopher p3 = new Philosopher(cs3, cs4);
		final Philosopher p4 = new Philosopher(cs4, cs1);

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

class Philosopher {
	private static int count;
	private int id;
	Chopstick left, right;
	private static Random rand = new Random(47);
	public Philosopher(Chopstick left, Chopstick right) {
		this.left = left;
		this.right = right;
		this.id = count++;
	}
	public void eat() {
		try {
			while (true) {
				System.out.println("philosopher " + id + " is trying to pick up left chopstick");
				if (!this.left.pick()) {
					System.out.println("philosopher " + id 
							+ " was not able to pick up left chopstick");
					Thread.sleep(rand.nextInt(1000));
					continue;
				}
				System.out.println("philosopher " + id + " picked up left chopstick");
				
				Thread.sleep(1000);
				
				System.out.println("philosopher " + id + " is trying to pick up right chopstick");
				if (!this.right.pick()) {
					System.out.println("philosopher " + id 
							+ " was not able to pick up right chopstick");
					this.left.drop();
					System.out.println("philosopher " + id + " droped left chopstick");
					Thread.sleep(rand.nextInt(1000));
					continue;
				}
				System.out.println("philosopher " + id + " picked up right chopstick");
				break;
			}
			
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

class Chopstick {
	Lock lock = new ReentrantLock();
	public boolean pick() {
		return lock.tryLock();
	}
	public void drop() {
		lock.unlock();
	}
}