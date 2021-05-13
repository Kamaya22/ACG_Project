package opinion;

public class Book extends Item {
	
	public String author;
	public int nbPages;
	
	public Book(String title, String kind, String author, int nbPages) {
		super(title, kind);
		this.author = author;
		this.nbPages = nbPages;
	}
	

}
