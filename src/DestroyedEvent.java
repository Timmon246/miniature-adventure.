public class DestroyedEvent extends ShipEvent {
		Ship ship;
		DestroyedEvent(Ship ship, int time) {this.ship = ship; this.time = time;}
		public void process(ShipGame game) {
			System.out.println("Another ship brings you news that your Ship, " + ship.name + ", was destroyed!");
			// More later?
		}
	
}