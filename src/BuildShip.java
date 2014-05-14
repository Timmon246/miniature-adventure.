	public class BuildShip extends ShipEvent {
		public int shipClass;
		BuildShip(int shipClass, int time){this.time = time;}
		public void process(ShipGame game) {
			int pick = shipClass;
			switch (pick) {
			case 0:
					System.out.println("Your Ship is ready!");
					System.out.print("Enter a name: ");
					String nameGeneric = game.scanner.next();
					Ship generic = new Ship();
					generic.name = nameGeneric;
					game.dock.add(generic);
				break;
			case 1:
					Ship trade = new Ship();
					trade.hp = 75; 
					trade.value = 150;
					System.out.println("Your Tradeship is ready!");
					System.out.print("Enter a name: ");
					String name = game.scanner.next();
					trade.name = name;
					game.dock.add(trade);
				break;
			case 2:
				//Warship?
				break;
			case 3:
				//Scout?
				break;
			default:
				System.out.println("Invalid choice");
			}
	
		}
	}
 	

