import machine.*;

public class VendingMachineMain {

	public static void main(String[] args) {
		Machine vendingMachine = new VendingMachine();
		
		Product coke = new Coke();
		Product water = new Water();
		
		vendingMachine.add(coke, 7);
		vendingMachine.add(water, 12);
		
		vendingMachine.print();
	}
}