package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import factory.AudioBookFactory;
import factory.BookFactory;
import factory.VhsFactory;

public class Biblioteca implements Mantainable {

	private static Biblioteca instance = null;
	// need to manage here the id - UNIQUE
	private HashMap<Integer, Item> archive = new HashMap<Integer, Item>();
	private BookFactory bookFactory = BookFactory.getInstance();
	private AudioBookFactory audiobookFactory = AudioBookFactory.getInstance();
	private VhsFactory vhsFactory = VhsFactory.getInstance();

	private Biblioteca() {
		super();

	}

	public static Biblioteca getInstance() {
		if (instance == null) {
			instance = new Biblioteca();
		}
		// populate from ancient data

		return instance;
	}

	public void addBook(Scanner scan) {
		// correction with FILE - auto_path
		String fileName = "C:\\Users\\Antonello\\allworkspace\\accademiainformatica\\biblioteca\\src\\biblioteca\\resources\\db.txt";

		try (BufferedWriter fileWriter = new BufferedWriter(new FileWriter(fileName, true));
				BufferedWriter bw = new BufferedWriter(fileWriter)) {

			// object creation
			Item book = bookFactory.create();
			// ask for data and build members

			System.out.println("Inserisci il titolo del libro: ");
			String titolo = scan.nextLine();
			System.out.println("Inserisci l'autore del libro: ");
			String autore = scan.nextLine();

			book.id(archiveSize() + 1).title(titolo).author(autore);

			// business
			archive.put(book.getId(), book);
			bw.newLine(); // Add a new line IF FILE IS NOT EMPTY
			bw.write(book.getId() + "," + "B" + "," + book.getTitle() + "," + book.getAuthor());
			bw.newLine();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void addAudioBook(Scanner scan) {

		String fileName = "C:\\Users\\Antonello\\allworkspace\\accademiainformatica\\biblioteca\\src\\biblioteca\\resources\\db.txt";

		try (BufferedWriter fileWriter = new BufferedWriter(new FileWriter(fileName, true));
				BufferedWriter br = new BufferedWriter(fileWriter)) {

			// object creation
			AudioBook audioBook = (AudioBook) audiobookFactory.create();
			// ask for data and build members

			System.out.println("Inserisci il titolo dell' audioLibro: ");
			String titolo = scan.nextLine();
			System.out.println("Inserisci l'autore dell' audioLibro: ");
			String autore = scan.nextLine();
			System.out.println("Inserisci la durata dell' audioLibro: ");
			int durata = Integer.parseInt(scan.nextLine());

			audioBook.id(archiveSize() + 1).title(titolo).author(autore).durata(durata);

			// business
			archive.put(audioBook.getId(), audioBook);
			br.newLine(); // Add a new line IF FILE IS NOT EMPTY
			br.write(audioBook.getId() + "," + "AB" + "," + audioBook.getTitle() + "," + audioBook.getAuthor() + ","
					+ durata);
			br.newLine();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void addAudioVHS(Scanner scan) {
		String fileName = "C:\\Users\\Antonello\\allworkspace\\accademiainformatica\\biblioteca\\src\\biblioteca\\resources\\db.txt";

		try (BufferedWriter fileWriter = new BufferedWriter(new FileWriter(fileName, true));
				BufferedWriter br = new BufferedWriter(fileWriter)) {

			// object creation
			Vhs vhs = (Vhs) vhsFactory.create();
			// ask for data and build members

			System.out.println("Inserisci il titolo del vhs: ");
			String titolo = scan.nextLine();
			System.out.println("Inserisci l'autore del vhs: ");
			String autore = scan.nextLine();
			System.out.println("Inserisci la durata del vhs: ");
			int durata = Integer.parseInt(scan.nextLine());

			vhs.id(archiveSize() + 1).title(titolo).author(autore).durata(durata);

			// business
			archive.put(vhs.getId(), vhs);
			br.newLine(); // Add a new line IF FILE IS NOT EMPTY
			br.write(vhs.getId() + "," + "VHS" + "," + vhs.getTitle() + "," + vhs.getAuthor() + "," + durata);
			br.newLine();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// capire perche non si propaga in modo utile nell'ide??
	public int esaminaBiblioteca(String filepath) throws IOException, FileNotFoundException {
		int linesCount = 0;

		BufferedReader br = new BufferedReader(new FileReader(filepath));
		String line;
		while ((line = br.readLine()) != null) {
			System.out.println(line);
			linesCount++;
		}

		br.close();
		return linesCount;
	}

	public void remove(Integer idx, String filepath) throws Exception {
		clear(filepath);

		Item tmp = archive.remove(idx);

		if (tmp == null) {
			throw new Exception();
		} else {
			HashMap<Integer, Item> mapCopied = new HashMap<Integer, Item>();

			for (Map.Entry<Integer, Item> mapEntry : archive.entrySet()) {
				mapCopied.put(mapEntry.getKey(), mapEntry.getValue());
			}

			archive.clear();
			int count_id = 1;
			for (Item item : mapCopied.values()) {
				item.setId(count_id);
				archive.put(count_id, item);
				count_id++;

			}
			// qui devi riscrivere il db

			for (Map.Entry<Integer, Item> mapEntry : archive.entrySet()) {
				try (BufferedWriter br = new BufferedWriter(new FileWriter(filepath, true))) {
					br.write(mapEntry.getValue().toString());
					
					br.newLine();

				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	// utility methods
	public Integer archiveSize() {
		return archive.size();
	}

	// svuota un file se esiste
	public static void clear(String dbFilePath) {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(
				"C:\\Users\\Antonello\\allworkspace\\accademiainformatica\\biblioteca\\src\\biblioteca\\resources\\db.txt"))) {
			// Writing nothing to the file effectively clears it
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void createDb(String FilePath) {
		try {
			File myObj = new File(FilePath);
			if (myObj.createNewFile()) {
				System.out.println("File created: " + myObj.getName());
			} else {
				System.out.println("File already exists.");
			}
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}

	// qui ci vanno dei controlli, potrebbero non esserci tutti gli elementi degli
	// array
	// LO LANCIO QUANDO INSTANZIO LA BIBLIO, QUINDI AL MASSIMO L'UTENTE DEVE ESSERE
	// AVVERTITO CHE:
	// SI E' TROVATO UN DB CON TOT ELEMENTI - E MI DEVO PORTARE ALLA LINEA SOTTO
	// NON SI E' TROVATO IL FILE
	// IL FILE E' MANOMESSO IN ALCUNE PARTI..PER ORA BASTA INDICARE QUESTO
	public void retrieveDb(String dbFilePath) throws IOException {
		String fileName = "C:\\Users\\Antonello\\allworkspace\\accademiainformatica\\biblioteca\\src\\biblioteca\\resources\\db.txt";

		try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
			String line;
			while ((line = br.readLine()) != null) {
				String type = line.split(",")[1];
				switch (type) {
				case "B":
					Book book = (Book) bookFactory.create().id(Integer.parseInt(line.split(",")[0].trim()))
							.title(line.split(",")[2].trim()).author(line.split(",")[3].trim());
					book.type(type);
					archive.put(Integer.parseInt(line.split(",")[0].trim()), book);
					break;
				case "AB":
					AudioBook audioBook = (AudioBook) audiobookFactory.create()
							.id(Integer.parseInt(line.split(",")[0].trim())).title(line.split(",")[2].trim())
							.author(line.split(",")[3].trim());
					audioBook.durata(Integer.parseInt(line.split(",")[4].trim()));
					audioBook.type(type);
					archive.put(Integer.parseInt(line.split(",")[0].trim()), audioBook);
					break;
				case "VHS":
					Vhs vhs = (Vhs) vhsFactory.create().id(Integer.parseInt(line.split(",")[0].trim()))
							.title(line.split(",")[2].trim()).author(line.split(",")[3].trim());
					vhs.durata(Integer.parseInt(line.split(",")[4].trim()));
					vhs.type(type);
					archive.put(Integer.parseInt(line.split(",")[0].trim()), vhs);
					break;
				}
				// add line if needed
				//BufferedWriter bw = new BufferedWriter(new BufferedWriter(new FileWriter(fileName, true)));
			}

		}

	}

	public HashMap<Integer, Item> getArchive() {
		return archive;
	}

//	public static void clear() {
//		try (BufferedWriter bw = new BufferedWriter(new FileWriter(
//				"C:\\Users\\Studente4.15\\eclipse-workspace\\biblioteca\\src\\biblioteca\\resources\\db.txt"))) {
//			// Writing nothing to the file effectively clears it
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}

}
