	public class Combat extends ShipEvent {
		Ship ship;
		Combat(Ship ship, int time) {this.ship = ship; this.time = time;}
		public void process(ShipGame game) {
			Ship enemy = new Ship();
			enemy.name = "Enemy";
			while(enemy.hp > 0 && ship.hp > 0){
				int enemyAtt = game.random.nextInt(35);
				int playerAtt = game.random.nextInt(30);
				int enemyDef = game.random.nextInt(10);
				int playerDef = game.random.nextInt(15);
				int enemyDamage = enemyAtt - playerDef;
				int playerDamage =  playerAtt - enemyDef;
				if (playerDamage > 0){
					enemy.hp = enemy.hp - playerDamage;
					System.out.println("Ship " + enemy.name + " takes " + playerDamage + " Damage!");
				}
				if (enemyDamage > 0){
					ship.hp = ship.hp - enemyDamage;
					System.out.println("Ship " + ship.name + " takes " + enemyDamage + " Damage!");
				}
			}
			if (ship.hp > 0){
				int booty = game.random.nextInt(enemy.value);
				int profit = game.random.nextInt(ship.value + booty);
				ship.value += profit / 5;
				game.money += profit;
				
				System.out.println("Ship " + ship.name + " has returned with " + ship.hp + " health!");
				System.out.println("Profit: " + profit);
				game.dock.add(ship);
			}
			else{game.queue.add(new DestroyedEvent(ship, 3));}
		}
	}