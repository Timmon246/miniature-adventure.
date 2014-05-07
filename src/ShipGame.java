/*
 * Lab 13: Fleet Commander
 * Skeleton code by Brian Jackson, Wittenberg University
 * COMP 250 Spring 2014
 * Kaleb Mayfield
 * This lab is optional, and seems like fun to me. But what do I know?
 */

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.Scanner;

public class ShipGame {
	//Consider moving public classes to their own files. It'll make reading the code easier
	
	PriorityQueue<ShipEvent> queue;
	ArrayList<Ship> dock;
	int money;
	int clock;
	Scanner scanner;
	Random random;
	
	public ShipGame() {
		queue = new PriorityQueue<ShipEvent>(100, new Comparator<ShipEvent>() {
			public int compare(ShipEvent arg0, ShipEvent arg1) {
				return arg0.time - arg1.time;
			}
			
		});
		dock = new ArrayList<Ship>();
		dock.add(new Ship());
		money = 100;
		clock = 1;
		scanner = new Scanner(System.in);
		random = new Random();
	}
	
	private void pollEvents() {
		//Check the priority queue for events ready to be processed
		if (queue.peek() != null && queue.peek().time <= clock){queue.poll().process(this);}	
	}
	
	//TODO: Consider creating Swing GUI for this game. The console sux.
	
	private void printStatus() {
		System.out.println("---");
		System.out.println("Status:");
		System.out.println("Day " + clock);
		System.out.println("Money: " + money);
		System.out.println("Ships in port: " + dock.size());
		System.out.println("---");
	}
	
	private void mainMenu() {
		System.out.println("Main menu:");
		System.out.println("0. Wait a few days");
		System.out.println("1. Send ship to barter");
		System.out.println("2. Send ship out for combat");
		System.out.println("3.Send ship out for repair");
		System.out.print("Enter a choice: ");
		
		int choice = scanner.nextInt();
		
		switch (choice) {
		case 0:
			waitMenu();
			break;
		case 1:
			sendShip();
			break;
//		case 2:
//			sendShip();
//			break;
//		case 3:
//			sendShip();
//			break;
		default:
			System.out.println("Invalid choice");
		}
	}
	
	private int chooseShip() {
		if (dock.size() == 0) {
			System.out.println("(No ships in dock)");
			return -1;
		}
		
		for (int i = 0; i < dock.size(); i++)
			System.out.println(i + ". " + dock.get(i));
		System.out.println("Select a ship");
		int choice = scanner.nextInt();
		if (choice >= dock.size()) choice = -1;
		return choice;
	}
	
	private void waitMenu() {
		System.out.println("How many days?");
		int days = scanner.nextInt();
		if (days >= 0) clock += days;
	}
	
	private void sendShip() {
		int shipno = chooseShip();
		if (shipno < 0) return;
		int goneFor = 1 + random.nextInt(7);
		System.out.println(dock.get(shipno).name + " will be gone for " + goneFor + " day(s).");
		
		// Schedule a BarterEvent
				//queue.add(new BarterEvent(dock.remove(shipno), (clock + goneFor)));
		// Schedule a Combat Event
				queue.add(new Combat(dock.remove(shipno), (clock + goneFor)));
		// Schedule a Repair Event
				//queue.add(new Repair(dock.remove(shipno), (clock + goneFor)));
		
	}
	
	public void start() {
		//Check it out! The classic event-processing loop
		while (true) {
			pollEvents();
			printStatus();
			mainMenu();//Note: I don't have a way to quit the game in here
		}
	}
	
	public static void main(String[] args) {
		ShipGame g = new ShipGame();
		g.start();
	}

	
}