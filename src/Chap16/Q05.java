package Finished;

public class Q1605 {
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
		Q1605 tc = new Q1605();
		
	}
}
