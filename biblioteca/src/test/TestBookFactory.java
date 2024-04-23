package test;

import factory.BookFactory;
import model.Item;

public class TestBookFactory {

	public static void main(String[] args) {
		
		BookFactory gen = BookFactory.getInstance();
		
		Item obj = gen.create().title("L'Orso").author("Wu Xing");
System.out.println(obj.getClass().getName());
		System.out.println(obj.getId()+" "+ obj.getTitle()+" "+obj.getAuthor());
	}

}
