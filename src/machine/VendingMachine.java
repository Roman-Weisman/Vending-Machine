package machine;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class VendingMachine extends Machine {
	private final Inventory[][] inventory;		// Stack of items - FILO
	private Map<String, LinkedList<ItemLookup>> productLocation; // holds the location/locations of each product
	
	private final int rows = 4;
	private final int columns = 3;
	private final int depth = 5;
	
	
	public VendingMachine() {
		inventory = new Inventory[rows][columns];
		
		// each spot in the matrix is a stack
		for(int i = 0; i < rows; i++) {
			for(int j = 0; j < columns ; j++) {
				inventory[i][j] = new Inventory(depth);
			}
		}
	}
	
	// Since each item has a dedicated spot, below dedicated locations must be implemented for each machine
	// If item location is changed, then below needs to be altered to match the design ( same as in a warehouse where each item has it's own location )
	@Override
	protected void initProductLocation() {
		ItemLookup coke1 = new ItemLookup(0, 0);
		ItemLookup coke2 = new ItemLookup(1, 0);
		
		LinkedList<ItemLookup> coke = new LinkedList<>();
		coke.add(coke1);
		coke.add(coke2);
		
		ItemLookup water1 = new ItemLookup(0, 1);
		ItemLookup water2 = new ItemLookup(1, 1);
		LinkedList<ItemLookup> water = new LinkedList<>();
		water.add(water1);
		water.add(water2);
		
		productLocation = new HashMap<>();
		
		productLocation.put(ProductConstants.COKE, coke);
		productLocation.put("Water", water);
	}
	

	@Override
	public void add(Product product, int quantity) {
		// lookup for product location - for example, Coke is at [0][0] , [0][1]
		// Try to push to any available spot - if none, display error message
		
		LinkedList<ItemLookup> itemLocation = productLocation.get(product.getName());
		
		int counter = 0 ;
		
		
		for(ItemLookup item: itemLocation) {
			if(counter == quantity)
				break;
			
			//int maxDepth = inventory[item.getX()][item.getY()].getMaxDepth();
			for(int i = counter; i < quantity; i++) {
				if(inventory[item.getX()][item.getY()].push(product)) { 
					counter++;
				} else { // if quantity is 80 but maxDepth is 5 - no need to iterate 80 times; instead, push max and continue to next spot
					break;
				}
			}
		}
		
		String item = product.getName();
		
		if(counter != quantity) {
			System.out.println(counter + " " + item + " were inserted");
			int remainder = quantity-counter;
			
			System.out.println("There was no room for " + remainder + " " + item + "");
		} else
			System.out.println(counter + " " + item + " were inserted");
	}
	
	@Override
	public void print() {
		System.out.println("\n***      The Vending Machine      ***");
		System.out.println("*** With Predefined Item Location ***\n");
		
		for(int i = 0 ; i < rows; i++) {
			for(int j = 0; j < columns; j++) {
				System.out.print(inventory[i][j].getCurrentDepth() + "/" + inventory[i][j].getMaxDepth() +
								 "(" + inventory[i][j].peekForProductName() + ")");
				if(j + 1 < columns)
					System.out.print(" | ");
			}
			System.out.println();
		}
	}
}
