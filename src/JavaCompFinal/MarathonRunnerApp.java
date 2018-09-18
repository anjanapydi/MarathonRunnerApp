package JavaCompFinal;

import java.util.*;
/**
 * MarathonRunnerApp.java - A class with main method to create the required number of runner threads provided by data source.
 * @author Anjana
 * @version 1.0	
 */
public class MarathonRunnerApp {
	//Declare variables
	static List<RunnersThread> runnersList = null;
	static Scanner sc = new Scanner(System.in);
	static String keyChoice = "y";
	/**
	 * Main method 
	 * @param args - command line argument
	 */
	public static void main(String[] args) {		
		//Display welcome message and menu options
		while(keyChoice.equalsIgnoreCase("y")) {
			System.out.println("Welcome to the Marathon Race Runner Program");
			System.out.println("\nSelect your data source:");
			System.out.println("\n1. Derby Database\n" 		+
						   "2. XML file\n" 		   		+
						   "3. Text file\n" 	   		+
						   "4. Default two runners\n"   +
						   "5. Exit");
			//Take the input option entered by user and validate it 
			int inputOption = Validator.getIntWithInRange(sc, "\nEnter your choice: ", 1, 5);
			runnersList = new ArrayList<RunnersThread>();
			switch(inputOption) {
			//Derby database data source
			case 1 :
				System.out.println("Get set...Go!");
				RunnersDB rdb = new RunnersDB();
				//Get runners information from derby DB and start the threads
		        runnersList = rdb.getAllRunners();
				for (RunnersThread r: runnersList) {
					r.start();
				}	        
				break;
			//XML file data source	
			case 2 :
				System.out.println("Get set...Go!");
				FinalXMLFile fX = new FinalXMLFile();
				//Get runners information from XML file data and start the threads
				runnersList = fX.getFileData();
				for (RunnersThread r: runnersList) {
					r.start();
				}
				break;
			//Txt file data source	
			case 3:
				System.out.println("Get set...Go!");
				FinalTextFile fT = new FinalTextFile();
				//Get runners information from txt file data and start the threads
				runnersList = fT.getFileData();
				for (RunnersThread r: runnersList) {
					r.start();
				}
				break;
			//Default 2 runners	
			case 4:
				System.out.println("Get set...Go!");
				//default runners to 2 and start the threads
				runnersList.add(new RunnersThread("Tortoise",10,0));
				runnersList.add(new RunnersThread("Hare",100,90));
				for (RunnersThread r: runnersList) {
					r.start();
				}
				break;
			//Exit 	
			case 5:
				System.out.println("Thank you for using my Marathon Race Program");
//				sc.close();
				System.exit(0);
			} //end switch
			//This is to make main method wait until all threads concede.
			for (RunnersThread rt: runnersList) {
				try {
					rt.join();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			System.out.println("\nPress any key and enter to continue . . .");
			String inputKey = sc.nextLine();
			//To accept any 'key & enter' or only 'enter key'
			if(!(inputKey.equalsIgnoreCase("")) || inputKey.isEmpty()) {
				keyChoice = "y";
			} else {
				keyChoice = "n";
			} // end if
		}//end while loop
	} //end main
		
	/**
	 * This method is to finish the race when one thread reaches 1000 and stop all other active threads
	 * @param r - RunnersThread object
	 */
	public synchronized void finishedRace(RunnersThread r) {
		//Stop other threads when winning thread calls finish method
		for (RunnersThread rt: runnersList) {
			//Skip the thread name that has finished the race.
			if (!(rt.getRunnerName().equals(r.getRunnerName()))) {
				stopThread(rt);
			} //end if	
		} // end for
		// To wait for all threads to complete
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} // end try-catch block
		//Display messages at the end of race
		System.out.println(r.getRunnerName() + " : I finished!");
		System.out.println("\nThe race is over! The " +  r.getRunnerName() + " is the winner.\n");
		for (RunnersThread rt: runnersList) { 
			//Display messages from all threads after one thread has finished the race.
			if (!(rt.getRunnerName().equals(r.getRunnerName()))) {
				System.out.println(rt.getRunnerName() + " : You beat me fair and square!");
			} //end if
		} // end for loop
	} // end finishedRace method	
	/**
	 * This method is to stop all non-winner threads
	 * @param rt - RunnerThread object
	 */
	public void stopThread(RunnersThread rt) {
		rt.interrupt();
	} // end stopThread method
} //end Class MaratahonRunnerApp
