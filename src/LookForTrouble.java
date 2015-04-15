public class LookForTrouble extends ShipEvent {
		Ship ship;
		LookForTrouble(Ship ship, int time) {this.ship = ship; this.time = time;}
		public void process(ShipGame game) {
			int chance = game.random.nextInt(100);
			if (chance <= 25){
				game.queue.add(new Combat(ship, (time + 3)));
			}
			else if (chance > 25 && chance <= 50){
				//TreasureCove(); // idea of finding treasure instead of combat
			}
			else{
				System.out.println("You did not find any trouble.");
				game.dock.add(ship);
			}
		}
}