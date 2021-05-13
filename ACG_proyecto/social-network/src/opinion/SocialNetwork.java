package opinion;

import java.util.LinkedList;

import exceptions.BadEntryException;
import exceptions.ItemBookAlreadyExistsException;
import exceptions.ItemFilmAlreadyExistsException;
import exceptions.MemberAlreadyExistsException;
import exceptions.NotItemException;
import exceptions.NotMemberException;

public class SocialNetwork implements ISocialNetwork {
	
	private int nbBooks;
	private int nbFilms;
	private int nbMembers;
	private LinkedList<Member> members;
	private LinkedList<Book> books;
	private LinkedList<Film> films;
	
	
	public SocialNetwork() {
		this.members = new LinkedList<Member>();
		this.books = new LinkedList<Book>();
		this.films = new LinkedList<Film>();
		this.nbBooks = books.size();
		this.nbFilms = films.size();
		this.nbMembers = members.size();
	}

	
	public int nbMembers() {
		return this.nbMembers;
	}

	
	public int nbFilms() {
		return this.nbFilms;
	}

	
	public int nbBooks() {
		return this.nbBooks;
	}

	
	public void addMember(String login, String password, String description)
			throws BadEntryException, MemberAlreadyExistsException {
		if (login.trim() == "" || password.trim() == "" || description.trim() == "") {
			throw new BadEntryException("Poorly instantiated.");
		}
		if (password.trim().length() < 4) {
			throw new BadEntryException("Password must contain at least 4 characters other than spaces");
		}
		if (checkID(login,password) != null) {
			throw new MemberAlreadyExistsException();
		}
		else {
			Member m = new Member(login, password, description);
			members.add(m);
			this.nbMembers ++;
		}
	}

	private Member checkID(String login, String password) {
		if (members.size() == 0) {
			return null;
		}
		else {
			for (int i = 0; i< members.size(); i++) {
				if (login == members.get(i).getLogin() && password == members.get(i).getPassword()) {
					return members.get(i);
				}	
			}
		}
		return null;
	}


	public void addItemFilm(String login, String password, String title, String kind, String director, String scriptwriter,
			int duration) throws BadEntryException, NotMemberException, ItemFilmAlreadyExistsException {
		if (title.trim() == ""|| kind.trim() == "" || director.trim() == "" || scriptwriter.trim() == "" || duration <= 0) {
			throw new BadEntryException("Poorly instantiated.");
		}
		if (checkID(login,password) == null) {
			throw new NotMemberException("Unrecognized member.");
		}
		if (checkFilm(title) == null) {
			Film f = new Film (title, kind, director, scriptwriter, duration);
			films.add(f);
			this.nbFilms ++;
		}
		else {
			throw new ItemFilmAlreadyExistsException();
		}

	}
	
	private Film checkFilm(String title) {
		if (films.size()==0) {
			return null;
		}
		else {
			for (int i = 0; i<films.size(); i++) {
				if (title == films.get(i).getTitle()) {
					return films.get(i);
				}
			}
			return null;
		}
	}


	public void addItemBook(String login, String password, String title, String kind, String author, int nbPages)
			throws BadEntryException, NotMemberException, ItemBookAlreadyExistsException {
		if (title.trim() == ""|| kind.trim() == "" || author.trim() == "" || nbPages <= 0) {
			throw new BadEntryException("Poorly instantiated.");
		}
		if (checkID(login,password) == null) {
			throw new NotMemberException("Unrecognized member.");
		}
		if (checkBook(title) == null) {
			Book b = new Book(title, kind, author, nbPages);
			books.add(b);
			this.nbBooks ++;
		}
		else {
			throw new ItemBookAlreadyExistsException();
		}
	}

	private Book checkBook(String title) {
		if (books.size()==0) {
			return null;
		}
		else {
			for (int i = 0; i<books.size(); i++) {
				if (title == books.get(i).getTitle()) {
					return books.get(i);
				}
			}
			return null;
		}
	}


	public float reviewItemFilm(String login, String password, String title, float mark, String comment)
			throws BadEntryException, NotMemberException, NotItemException {
		if ( login.trim() == "" || password.trim() == "" || title.trim() == "" || comment.trim() == "") {
			throw new BadEntryException("Poorly instantiated.");
		}
		if (checkID(login,password) == null) {
			throw new NotMemberException("Unrecognized member.");
		}
		if (checkFilm(title) == null) {
			throw new NotItemException("Film inexistant.");
		}
		else {
			Film f = checkFilm(title);
			Member m = checkID(login,password);
			f.addReview(mark, comment, m, f);
			return f.mean();
		}
	}


	public float reviewItemBook(String login, String password, String title, float mark, String comment)
			throws BadEntryException, NotMemberException, NotItemException {
		if (title.trim() == "" || comment.trim() == "" || (mark < 0f && mark > 5f)) {
			throw new BadEntryException("Poorly instantiated.");
		}
		if (checkID(login,password) == null) {
			throw new NotMemberException("Unrecognized member.");
		}
		if (checkBook(title) == null) {
			throw new NotItemException("Book inexistant.");
		}
		else {
			Book b = checkBook(title);
			Member m = checkID(login,password);
			b.addReview(mark, comment, m, b);
			return b.mean();
		}	
	}


	public LinkedList<String> consultItems(String title) throws BadEntryException, NotItemException {
		if (title.trim() == "") {
			throw new BadEntryException("Poorly instantiated.");
		}
		
		if (checkBook(title) == null && checkFilm(title) == null) {
			throw new NotItemException("This item doesn't exist");
		}
		
		if (checkFilm(title) == null && checkBook(title) == null) {
			return null;
		}
		else {
			LinkedList<String> result = new LinkedList<String>();
			int n1 = books.size();
			int n2 = films.size();
			if (n1<n2) {
				for (int i=0; i<n2; i++) {
					if (i<n1) {
						if (books.get(i).getTitle() == title) {
							result.add(books.get(i).getTitle() + " " + books.get(i).getKind());
							
							result.add(Float.toString(books.get(i).mean()));
							
						}
						if (films.get(i).getTitle() == title) {
							result.add(films.get(i).getTitle() + " " + films.get(i).getKind());
							
							result.add(Float.toString(films.get(i).mean()));
							
						}
					}
						else {
							if (films.get(i).getTitle() == title) {
								result.add(films.get(i).getTitle() + " " + films.get(i).getKind());
								
								result.add(Float.toString(films.get(i).mean()));
								
							}
								
						}
				}
			}
					else {
						for (int i=0; i<n1; i++) {
							if (i<n2) {
								if (books.get(i).getTitle() == title) {
									result.add(books.get(i).getTitle() + " " + books.get(i).getKind());
									
									result.add(Float.toString(books.get(i).mean()));
									
								}
								if (films.get(i).getTitle() == title) {
									result.add(films.get(i).getTitle() + " " + films.get(i).getKind());
									
									result.add(Float.toString(films.get(i).mean()));
									
								}
							}
								else {
									if (books.get(i).getTitle() == title) {
										result.add(books.get(i).getTitle() + " " + books.get(i).getKind());
										
										result.add(Float.toString(books.get(i).mean()));
										
									}
								}				
				
						}
				
					}
		return result;
		}
	}

}
