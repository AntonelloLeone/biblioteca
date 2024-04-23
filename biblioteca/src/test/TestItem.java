package test;

import model.Item;

public class TestItem {

	public static void main(String[] args) {


		Item obj = Item.BuildItem().title("xi").author("ping");
		System.out.println(obj.getId()+" "+ obj.getTitle()+" "+obj.getAuthor());
		
		Item obj_2 = Item.BuildItem().title("zan").author("sun");
		System.out.println(obj_2.getId()+" "+ obj_2.getTitle()+" "+obj_2.getAuthor());

	}

}
