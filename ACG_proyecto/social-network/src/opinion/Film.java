package opinion;

public class Film extends Item {

	public String director;
	public String scenarist;
	public int duration;
	
	public Film(String title, String kind, String director, String scenarist,int duration) {
		super(title, kind);
		this.director = director;
		this.scenarist = scenarist;
		this.duration = duration;
	}
	
}
