package JavaCompFinal;
/**
 * RunnersThread.java - This class is to determine which runner thread wins the race.
 * @author Anjana
 *
 */
public class RunnersThread extends Thread {
	//Declare variables
	private int distance = 0;
	private int rest;
	private int speed;
	private String name;
	private int totalDistance = 1000;
	private int sleepTime = 100;
	/**
	 * Constructor that accepts below parameters and assign name to thread.
	 * @param name - Runner name
	 * @param speed - Runner speed
	 * @param rest - Rest Percentage
	 */
	public RunnersThread(String name, int speed, int rest) {
		this.name = name;
		this.speed = speed;
		this.rest = rest;
		Thread.currentThread().setName(name);
	} // end constructor
	/**
	 * Run method to make the runner run based on random number generated and finish the race when one wins. 
	 */
	public void run() {
		try {
			//runner continue to run until it reaches 1000
			while (distance < totalDistance) {
				//Generates random number between 1 and 100
				int randomNumber = (int)(Math.random() * 100);
				if (randomNumber > rest) {
					distance += speed;
				} // end if
				System.out.println(this.getRunnerName() + ":" + distance);
				//Every runner thread should sleep for provided time
				Thread.sleep(sleepTime);
				//finish race if any of the thread reaches 1000
				if (distance >= totalDistance) {
					MarathonRunnerApp m = new MarathonRunnerApp();
					m.finishedRace(this);
				} //end if
			} // end while loop
		} catch (InterruptedException e) {
		} //end try-catch block
	} //end run method
	/**
	 * Gets the name of the Runner.
	 * @return the runner's name
	 */
	public String getRunnerName() {
		return name;
	}
} //end RunnersThread class
