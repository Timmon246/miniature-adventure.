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
	int shipCost = 100;
	
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
	
	private void buildShip() { // Build a new Ship
		System.out.println("Build menu:");
		System.out.println("0. Generic Ship (" + shipCost + "G)");
		System.out.println("1. Trade Ship (" + (shipCost + 150) + "G)");
		System.out.print("Enter a choice: ");
		System.out.println(" ");
		int shipClass = scanner.nextInt();
		switch (shipClass) {
		case 0:
			if (money >= shipCost){
				money -= 100;
				System.out.println("The ship will be ready in 6 days.");
				queue.add(new BuildShip(shipClass, 6));
				shipCost += 25;
				break;
			}
			else{
				System.out.println("Not enough money.");
				break;
				}
		case 1:
			if (money >= (shipCost + 150)){
				money -= 250;
				System.out.println("The ship will be ready in 10 days.");
				queue.add(new BuildShip(shipClass, 10));
				shipCost += 50;
				break;
			}
			else{
				System.out.println("Not enough money.");
				break;
				}
		default:
			System.out.println("Invalid choice");
			
		}
	}

//	private void repairMenu() { // Repair a Ship
//		int shipRepair = chooseShip();
//		if (shipRepair < 0) return;
//		System.out.println("It will cost 1G per day for 1Hp plus a day for each 10Hp.");
//		System.out.println("How much damage would you like to repair?");
//		int damageRepair = scanner.nextInt();
//		System.out.println(dock.get(shipRepair).name + " will be gone for " + ((damageRepair / 10) + 1) + " day(s) and cost " + damageRepair + "G for " + damageRepair + " health.");
//		if (money >= damageRepair){
//			money -= damageRepair;
//			int repair = damageRepair;
//			queue.add(new Repair(dock.remove(shipRepair), (clock + ((damageRepair / 10) + 1))));
//		}
//	}
	
	private void combatMenu(){
		int shipCombat = chooseShip();
		if (shipCombat < 0) return;
		System.out.println(dock.get(shipCombat).name + " will be gone for 6 days looking for trouble.");
		queue.add(new LookForTrouble(dock.remove(shipCombat), 6));
	}
	
	private void mainMenu() {
		System.out.println("Main menu:");
		System.out.println("0. Wait a few days");
		System.out.println("1. Send ship to barter");
		System.out.println("2. Build a new ship");
		System.out.println("3. Send ship out for repair");
		System.out.println("4. Send ship out for combat");
		System.out.print("Enter a choice: ");
		
		switch (scanner.nextInt()) {
		case 0:
			waitMenu();//Wait # of days
			break;
		case 1:
			sendShip();//Send ship out to trade
			break;
		case 2:
			buildShip();//Build ship
			break;
		case 3:
			//repairMenu();//send ship out for repair
			break;
		case 4:
			combatMenu();//send ship out for combat perhaps a fleet
			break;
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
		System.out.print("Select a ship");
		int choice = scanner.nextInt();
		if (choice >= dock.size()) choice = -1;
		return choice;
	}
	
	private void waitMenu() {
		System.out.println("How many days?");
		int days = scanner.nextInt();
		if (days >= 0) clock += days;
	}
	
	private void sendShip() { // To go on a Bartering Mission
		int shipno = chooseShip();
		if (shipno < 0) return;
		int goneFor = 1 + random.nextInt(7);
		System.out.println(dock.get(shipno).name + " will be gone for " + goneFor + " day(s).");
		queue.add(new BarterEvent(dock.remove(shipno), (clock + goneFor)));
	}
	
	public void start() {
		//start StormyNight?
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