package menu;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

import model.Biblioteca;
import model.Item;

public class Menu {

	private static Menu instance;
	private String response = "";
	private static Biblioteca biblioteca;
	private String dbFilePath = "C:\\Users\\Studente4.15\\eclipse-workspace\\biblioteca\\src\\resources\\db.txt";

	private String init = """
			^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
			*****************************************************
			  BLOCKBUSTER
			********************************************
			  1 - Read archive
			  2 - Add article/articles
			  3 - Delete Article
			  4 - Find
			  5 - Update

			  9 - Exit
			********************************************
			  Note: """ + response + "\n" + """
			********************************************
			Select a number to act:
			""";

	private String menuAdd = """
			^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
			******************************************************
			BLOCKBUSTER - Add element
			******************************************************
			1 - Add Book
			2 - Add AudioBook
			3 - Add VHS
			4 - Add File

			8 - Back
			9 - Exit
			******************************************************

			""";

	private Menu() {
		super();

	};

	public static Menu getInstance() {
		if (instance == null) {
			instance = new Menu();
		}
		biblioteca = Biblioteca.getInstance();

		return instance;
	}

	// business logic
	public void findDb() {
		try {
			biblioteca.retrieveDb(dbFilePath);
		} catch (FileNotFoundException e) {
			Biblioteca.createDb(dbFilePath);
		} catch (IOException e) {
			Biblioteca.createDb(dbFilePath);
		}
	}

	private String init(String response) {
		return """
				^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
				******************************************************
				  BLOCKBUSTER
				******************************************************
				  1 - View archive
				  2 - Add article/articles
				  3 - Delete Article
				  4 - Find
				  5 - Update
				  6 - Print Data

				  9 - Exit
				******************************************************
				Note: """ + response + "\n" + """
				******************************************************
				Select a number to act:
				""";
	}

	private String menuAdd() {
		return menuAdd;
	}

	public void viewMenu() {
		System.out.println(init(response));

		try (Scanner scan = new Scanner(System.in)) {
			if (scan.hasNextInt()) {
				int scelta = scan.nextInt();

				switch (scelta) {
				case 1:
					try {
						int count = biblioteca.esaminaBiblioteca(dbFilePath);
						response = "il file db.txt esiste! Trovati: " + count + " elementi";
						viewMenu();
					} catch (FileNotFoundException e) {
						response = "il file db.txt non esiste!";
						viewMenu();

					} catch (IOException e) {
						response = "errore IO generico";
						viewMenu();
					}
					break;
				case 2:
					addElement();
					break;
				case 3:
					try {
						System.out.println("Digita l'id dell'elemento che vuoi eliminare");
						int index = scan.nextInt();
						biblioteca.remove(index, dbFilePath);
						response = "hai eliminato l'id: " + index;
						viewMenu();
					} catch (Exception e) {
						response = "il file db.txt non esiste!";
						viewMenu();
					}
					break;
				case 4:
					break;
				case 5:
//					try {
//						biblioteca.retrieveDb(dbFilePath);
//						int count = biblioteca.archiveSize();
//						response = "il file db.txt esiste! Trovati: " + count + " elementi";
//						viewMenu();
//					} catch (FileNotFoundException e) {
//						response = "il file db.txt non esiste!";
//						viewMenu();
//
//					} catch (IOException e) {
//						response = "errore IO generico";
//						viewMenu();
//					} catch (ArrayIndexOutOfBoundsException e) {
//						response = "il db o una sua parte � manomesso";
//						viewMenu();
//					}
					viewMenu();
					break;
				case 6:
					printCollection();
					viewMenu();
					break;
				case 9:
					System.exit(0);
					break;
				default:
					viewMenu();
				}
			} else {
				System.out.println("Warning: Input non valido. Riprova.");
				viewMenu();
			}
		} catch (

		Exception e) {
			viewMenu();
		}

	}

	public void addElement() {

		System.out.println(menuAdd());
		System.out.println("Seleziona il numero dell'azione da compiere:");

		try (Scanner scan = new Scanner(System.in)) {
			if (scan.hasNextInt()) {
				int scelta = scan.nextInt();

				switch (scelta) {
				case 1:
					scan.nextLine();
					biblioteca.addBook(scan);
					addElement();
					break;
				case 2:
					scan.nextLine();
					biblioteca.addAudioBook(scan);
					addElement();
					break;
				case 3:
					scan.nextLine();
					biblioteca.addAudioVHS(scan);
					addElement();
				case 8:
					viewMenu();
					break;
				case 9:
					System.exit(0);
					break;
				default:
					viewMenu();
				}
			} else {
				System.out.println("Warning: Input non valido. Riprova.");
				viewMenu();
			}
		} catch (Exception e) {
			viewMenu();
		}
	}

	public void printCollection() {
		for (Map.Entry<Integer, Item> item : biblioteca.getArchive().entrySet()) {
			System.out.println(item.getKey() + " " + item.getValue());
		}
	}
}
