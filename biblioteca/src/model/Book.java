package model;

public class Book extends Item{
	
	private Book() {

	}
	
	
	public static Book BuildBook() {
		return new Book();
	}

}
