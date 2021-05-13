package opinion;

public class Review {
	
	public float mark;
	public String comment;
	public Member author;
	public Item item;
	
	public Review(float mark, String comment, Member author, Item item) {
		this.mark = mark;
		this.comment = comment;
		this.author = author;
		this.item = item;
	}
	
	public float getMark() { return this.mark;}

}
