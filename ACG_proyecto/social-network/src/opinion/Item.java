package opinion;

import java.util.LinkedList;

public class Item {
	
	public String title;
	public String kind;
	public LinkedList<Review> reviews;
	
	public Item(String title, String kind) {
		this.title = title;
		this.kind = kind;
		this.reviews = new LinkedList<Review>();
	}
	
	public String getTitle() {return this.title;}
	
	public String getKind() {return this.kind;}
	
	public void addReview(float mark, String comment, Member member, Item i) {
		Review r = new Review(mark, comment, member, i);
		this.reviews.add(r);
	}
	
	public float mean() {
		int n = reviews.size();
		float s = 0;
		for (int i=0 ; i<n ; i ++) {
			s = s + reviews.get(i).getMark();
		}
		return s/n;
	}
}
