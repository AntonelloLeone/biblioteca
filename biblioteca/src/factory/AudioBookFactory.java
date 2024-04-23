package factory;

import model.AudioBook;
import model.Item;

public class AudioBookFactory extends ItemFactory{

	private static AudioBookFactory instance = null;

	@Override
	protected Item createItem() {
		
		return AudioBook.BuildAudioBook();
	}

	//Singleton
	private AudioBookFactory() {};
	
	public static AudioBookFactory getInstance() {
		if(instance == null) {
			instance = new AudioBookFactory();
		}
		
		return instance;
	}
}
