public class BarterEvent extends ShipEvent {
		Ship ship;
		BarterEvent(Ship ship, int time) {this.ship = ship; this.time = time;}
		public void process(ShipGame game) {
			int damage = game.random.nextInt(10);
			if (damage >= ship.hp)
				game.queue.add(new DestroyedEvent(ship, 3));
			else {
				ship.hp -= damage;
				int profit = game.random.nextInt(ship.value);
				ship.value += profit / 5;
				game.money += profit;
				
				System.out.println("Ship " + ship.name + " has returned!");
				System.out.println("Profit: " + profit);
				game.dock.add(ship);
			}
		}
	}