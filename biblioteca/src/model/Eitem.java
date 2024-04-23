package model;

public class Eitem extends Item {

	private int durata;

	protected Eitem() {

	}

	public static Eitem BuildEitem() {
		return new Eitem();
	}

	public Eitem durata(int durata) {
		this.durata = durata;
		return this;
	}

	public int getDurata() {
		return durata;
	}

	public void setDurata(int durata) {
		this.durata = durata;
	}
	
	@Override
	public Eitem id(Integer id) {
		this.id=id;
		return this;
	}
	@Override
	public Eitem type(String type) {
		this.type=type;
		return this;
	}
	@Override
	public Eitem title(String title) {
		this.title=title;
		return this;
	}
	@Override
	public Eitem author(String author) {
		this.author=author;
		return this;
	}
	
	
	

	@Override
	public String toString() {
		return super.toString() + "," + durata;
	}

}
