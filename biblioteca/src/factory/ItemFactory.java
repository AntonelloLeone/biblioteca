package factory;

import model.Item;

public abstract class ItemFactory {
	
	public Item create() {
		return createItem();
		
	}
	
	protected abstract Item createItem();

}
