package machine;

public abstract class Machine {
	
	public Machine() {
		initProductLocation();
	}

	abstract protected void initProductLocation();
	
	abstract public void add(Product product, int quantity);
	
	abstract public void print();
}
