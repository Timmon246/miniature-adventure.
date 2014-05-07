public class Ship {
		public String name;//You should be able to buy and rename ships, so this distinguishes them.
		public int hp;//When this gets to zero, the ship should be destroyed (perhaps by giving it a DestroyedEvent?)
		public int value;//The higher this gets, the bigger trades it can make
		//Perhaps add more here?
		
		public Ship() {name = "Jenny"; hp = 100; value = 100;}
		
		public String toString() {return "[" + name + " HP: " + hp + " Value: " + value + "]";}
	}