//This is an abstract class. It's sort of like an interface, but you can put code in it.
public abstract class ShipEvent {
		int time;
		abstract public void process(ShipGame game);//Abstract methods don't provide any code.
	}