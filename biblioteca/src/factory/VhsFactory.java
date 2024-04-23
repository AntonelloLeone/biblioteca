package factory;

import model.Item;
import model.Vhs;

public class VhsFactory extends ItemFactory{

	private static VhsFactory instance = null;

	@Override
	protected Item createItem() {
		
		return Vhs.BuildVhs();
	}

	//Singleton
	private VhsFactory() {};
	
	public static VhsFactory getInstance() {
		if(instance == null) {
			instance = new VhsFactory();
		}
		
		return instance;
	}
}
