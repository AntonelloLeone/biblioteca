package model;

public  class Item {
	

	
	protected Integer id;
	protected String type; // poi enum
	protected String title;
	protected String author;
	
	
	protected Item() {

	}
	
	public static Item BuildItem() {
		return new Item();
	}
	
	
	
	
	public Item id(Integer id) {
		this.id=id;
		return this;
	}
	
	public Item type(String type) {
		this.type=type;
		return this;
	}
	
	public Item title(String title) {
		this.title=title;
		return this;
	}
	
	public Item author(String author) {
		this.author=author;
		return this;
	}
	
	
	

	public Integer getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	@Override
	public String toString() {
		return id + "," + type + "," + title + "," + author;
	}
	
	
	
	

	
	
	

}
