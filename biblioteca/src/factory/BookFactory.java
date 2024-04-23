package factory;

import model.Book;
import model.Item;

public class BookFactory extends ItemFactory {
	
	private static BookFactory instance = null;

	@Override
	protected Item createItem() {
		
		return Book.BuildBook();
	}

	//Singleton
	private BookFactory() {};
	
	public static BookFactory getInstance() {
		if(instance == null) {
			instance = new BookFactory();
		}
		
		return instance;
	}
	
	
}
