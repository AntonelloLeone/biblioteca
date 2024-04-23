package test;

import menu.Menu;

public class Main {


	public static void main(String[] args) {

		Menu menu = Menu.getInstance(); // instance menu with his library!
		menu.findDb();
		menu.viewMenu();
		
		
		// test create or retrieve
//		menu.findDb();
//		menu.printCollection();


	}

}
