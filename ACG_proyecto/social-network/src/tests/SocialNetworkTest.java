package tests;
import opinion.ISocialNetwork;
import opinion.SocialNetwork;
import exceptions.BadEntryException;
import exceptions.MemberAlreadyExistsException;
import exceptions.ItemFilmAlreadyExistsException;
import exceptions.ItemBookAlreadyExistsException;
import exceptions.NotMemberException;
import exceptions.NotItemException;
import java.util.LinkedList;

public class SocialNetworkTest {

    public static void initialisationTest() {
	System.out.println("Testing  initialisation  of a brand new ISocialNetwork  ");
	try {

	    // a brand new ISocialNetwork should not contain any member nor any item
	    ISocialNetwork sn = new SocialNetwork();
	    if (sn.nbMembers()!= 0) {
		System.out.println("Err 1.1 :  non-zero number of Member in a newly created ISocialNetwork");
		System.exit(1);
	    }
	    if (sn.nbBooks() != 0) {
		System.out.println("Err 1.2 : non-zero number of Book in a newly created ISocialNetwork");
		System.exit(1);
	    }
	    if (sn.nbFilms() != 0) {
		System.out.println("Err 1.3 : non-zero number of Film in a newly created ISocialNetwork");
		System.exit(1);
	    }
	    
	}
	catch (Exception e) {
	    System.out.println("Unexpected Exception : " + e);
	    e.printStackTrace();
	}
    }
    public static void addMemberTest() {
	int nbMiembros = 0;
	int nbLibros = 0;
	int nbFilms = 0;

	System.out.println("Testing adding members to the social network ");


	ISocialNetwork sn = new SocialNetwork();

	// testing addMember
	nbFilms = sn.nbFilms();
	nbLibros = sn.nbBooks();
	
	// We try to add a member with correct entries
	
	nbMiembros = sn.nbMembers();
	try {
		System.out.println("Error 2.0 : Testing if the adding of a member with every entries correct is refused");
		sn.addMember("Kamil", "password", "description");
		if (sn.nbMembers() == nbMiembros) {
			System.out.println("Error 2.0 : The member didn't get add correctly");
		}
	}
	catch (Exception e) {
		System.out.println("Error 2.0, Unexpected Exception : " + e);
		e.printStackTrace();
	}

	// We try to add a member with incorrect entries

	nbMiembros = sn.nbMembers();
	try {
		System.out.println("Error 2.1 :  Testing if the adding of a member whose pseudo isn't instantiated is accepted");
	    sn.addMember("", "qsdfgh", "something");		    
	}
	catch (BadEntryException e) {
	    if (sn.nbMembers() != nbMiembros)
		System.out.println("Error 2.1 :  The number of members after an adding has been refused has been modified");
	}
	catch (Exception e) {
	    System.out.println("Error 2.1, Unexpected Exception : " + e);
	    e.printStackTrace();
	}

	nbMiembros = sn.nbMembers();
	try {
		System.out.println("Error 2.2 :  Testing if the adding of a member whose pseudo doesn't contain a character other than spaces is accepted");
	    sn.addMember("  ", "qsdfgh", "");		    
	}
	catch (BadEntryException e) {
	    if (sn.nbMembers() != nbMiembros)
		System.out.println("Error 2.2 :  The number of members after an adding has been refused has been modified");
	}			
	catch (Exception e) {
	    System.out.println("Error 2.2, Exception not planned : " + e);
	    e.printStackTrace();
	}

	nbMiembros = sn.nbMembers();
	try {
		System.out.println("Error 2.3 :  Testing if the adding of a member whose password isn't instantiated is accepted");
	    sn.addMember("B", "", "description");	    
	}
	catch (BadEntryException e) {
	    if (sn.nbMembers() != nbMiembros)
		System.out.println("Error 2.3 :  The number of members after an adding has been refused has been modified");
	}
	catch (Exception e) {
	    System.out.println("Error 2.3, Unexpected Exception : " + e);
	    e.printStackTrace();
	}

	nbMiembros = sn.nbMembers();
	try {
		System.out.println("Error 2.4 :  Testing if the adding of a member whose password doesn't contain at least 4 characters other than spaces from the beginning until the end has been accepted");
	    sn.addMember("B", "  qwd  ", "");	   
	}
	catch (BadEntryException e) {
	    if (sn.nbMembers() != nbMiembros)
		System.out.println("Error 2.4 :  The number of members after an adding has been refused has been modified");
	}
	catch (Exception e) {
	    System.out.println("Error 2.4, Unexpected Exception : " + e);
	    e.printStackTrace();
	}

	nbMiembros = sn.nbMembers();
	try {
		System.out.println("Error 2.5 :  Testing if the adding of a member whose profile isn't instantiated has been accepted");
	    sn.addMember("BBBB", "bbbb", "");		    
	}
	catch (BadEntryException e) {
	    if (sn.nbMembers() != nbMiembros)
		System.out.println("Error 2.5 :  The number of members after an adding has been refused has been modified");
	}
	catch (Exception e) {
	    System.out.println("Error 2.5, Unexpected Exception : " + e);
	    e.printStackTrace();
	}


	// We add 3 Members with correct entries
	nbMiembros = sn.nbMembers();
	try {
	    sn.addMember("Paul", "paul", "impulsive reader");
	    sn.addMember("Antoine", "antoine", "great lover of litterature");
	    sn.addMember("Alice", "alice", "cartoons passionate");
	    if (sn.nbMembers()!= (nbMiembros + 3))
	    	System.out.println("Error 2.6 :  The number of members after we add 3 members didn't increase by 3");
	}
	catch (Exception e) {
	    System.out.println("Error 2.6, Unexpected Exception : " + e);
	    e.printStackTrace();
	}


	// We try to add an existing member
	nbMiembros = sn.nbMembers();
	try {
	    sn.addMember("Paul", "abcdefghij", "impulsive reader");	
	    System.out.println("Error 2.7 :  Testing if the adding of a member whose pseudo is the same as the first existing member has been accepted");
	}
	catch (MemberAlreadyExistsException e) {
	    if (sn.nbMembers() != nbMiembros)
		System.out.println("Error 2.7 :  The number of members after an adding has been made has been modified");
	}
	catch (Exception e) {
	    System.out.println("Error 2.7, Unexpected Exception : " + e);
	    e.printStackTrace();
	}

	nbMiembros = sn.nbMembers();
	try {
	    sn.addMember("Alice", "abcdefghij", "love cartoons");	
	    System.out.println("Error 2.8 :  Testing if the adding of a member whose pseudo is the same as the last member added has been accepted");
	}
	catch (MemberAlreadyExistsException e) {
	    if (sn.nbMembers() != nbMiembros)
		System.out.println("Error 2.8 :  The number of member after an adding has been refused has been modified");
	}
	catch (Exception e) {
	    System.out.println("Error 2.8, Unexpected Exception : " + e);
	    e.printStackTrace();
	}

	nbMiembros = sn.nbMembers();
	try {
	    sn.addMember("anToine", "abcdefghij", "description");	
	    System.out.println("Error 2.9 :  Testing if the adding of a member whose pseudo already exists (with a different case) has been accepted");
	}
	catch (MemberAlreadyExistsException e) {
	    if (sn.nbMembers() != nbMiembros)
		System.out.println("Error 2.9 :  The number of member after an adding has been refused has been modified");
	}
	catch (Exception e) {
	    System.out.println("Error 2.9, Unexpected Exception : " + e);
	    e.printStackTrace();
	}


	nbMiembros = sn.nbMembers();
	try {
	    sn.addMember("  Antoine  ", "abcdefghij", "description");	
	    System.out.println("Error 2.10 :  Testing if the adding of a member whose pseudo already exists (with leading and trailing blanks has been accepted");
	}
	catch (MemberAlreadyExistsException e) {
	    if (sn.nbMembers() != nbMiembros)
		System.out.println("Error 2.10 :  The number of member after an adding has been refused has been modified");
	}
	catch (Exception e) {
	    System.out.println("Error 2.10, Unexpected Exception : " + e);
	    e.printStackTrace();
	}

	nbMiembros = sn.nbMembers();
	String nomConstruit = "An";
	String nomConstruit2 = "ne";
	nomConstruit = nomConstruit + "toi" + nomConstruit2 ;	// "Antoine"
	try {
	    sn.addMember(nomConstruit, "abcdefghij", "description");	
	    System.out.println("Error 2.11 :  Testing if the adding of a member whose pseudo already exists (obtained by concatenation of String) has been accepted");
	}
	catch (MemberAlreadyExistsException e) {
	    if (sn.nbMembers() != nbMiembros)
		System.out.println("Error 2.11 :  The number of member after an adding has been refused has been modified");
	}
	catch (Exception e) {
	    System.out.println("Error 2.11, Unexpected Exception : " + e);
	    e.printStackTrace();
	}

	if (nbFilms != sn.nbFilms()) {
	    System.out.println("Error 2.12 :  The number of films after the use of addMember has been modified");
	}
	if (nbLibros != sn.nbBooks()) {
	    System.out.println("Error 2.13 :  The number of books after the use of addMember has been modified");				
	}

    }

    public static void addItemTest() {
	int nbMiembros = 0;
	int nbLibros = 0;
	int nbFilms = 0;
		
	System.out.println("Testing adding items to the social network ");
		
		
	try {

	    ISocialNetwork sn = new SocialNetwork();
	    // Adding 3 Members with 3 correct entries
	    sn.addMember("Paul", "paul", "impulsive reader");
	    sn.addMember("Antoine", "antoine", "great lover of litterature");
	    sn.addMember("Alice", "alice", "cartoons passionate");
			
	    // Testing addItemLivre and addItemFilm
	    nbMiembros = sn.nbMembers();
	    
	    // We try to add a book with correct entries
	    nbLibros = sn.nbBooks();
	    
	    try {
	    	System.out.println("Error 3.1.0 : Testing if adding a book with correct entries has been refused");
	    	sn.addItemBook("Paul", "paul", "Les furtifs", "roman", "Alain Damasio", 687);
	    	if (sn.nbBooks() == nbLibros) {
	    		System.out.println("Error 3.1.0 : Adding a book doesn't work correctly");
	    	}
	    }
	    catch (Exception e) {
	    	System.out.println("Error 3.1.0, Unexpected Exception : " + e);
	    	e.printStackTrace();
	    }
	    
	    // We add 3 books and 4 films with correct entries
	    nbLibros = sn.nbBooks();
	    nbFilms = sn.nbFilms();
	    
	    sn.addItemBook("Alice", "alice", "Lignes de faille", "roman", "Nancy Huston", 220);
	    sn.addItemFilm("Alice", "alice", "Le train sifflera trois fois", "western 1952", "Fred Zinnemann", "Carl Foreman", 85);
	    sn.addItemBook("Paul", "paul", "La peste", "roman", " Albert Camus", 336);
	    sn.addItemFilm("Paul", "paul", "Avant l'aube", "thriller 2011", "Raphael Jacoulot", "Lise Macheboeuf et Raphael Jacoulot", 104);
	    sn.addItemBook("Antoine", "antoine", "Guerre et Paix", "roman", "Leon Tosltoi", 1247);
	    sn.addItemFilm("Antoine", "antoine", "Le discours d'un roi", "drame historique 2010", "Tom Hooper", "David Seidler", 118);
	    sn.addItemFilm("Alice", "alice", "Black Swan", "drame 2010", "Darren Aronofsky", "John McLaughlin et Mark Heyman et Andres Heinz", 103);
			
	    if (sn.nbBooks()!= (nbLibros + 3)) 
	    	System.out.println("Error 3.1 :  The number of books after we added 3 books hasn't increased by 3 ");
	    if (sn.nbFilms()!= (nbFilms + 4)) 
	    	System.out.println("Error 3.2 :  The number of films after we added 4 films hasn't increased by 4");
	    
	    // We try to add a book with incorrect entries
	    nbLibros = sn.nbBooks();
	    
	    try {
	    	System.out.println("Error 3.1.1 : Testing if adding a book with another member works");
	    	sn.addItemBook("Alice", "alice", "Les furtifs", "roman", "Alain Damasio", 687);
	    }
	    catch (ItemBookAlreadyExistsException e) {
	    	if (sn.nbBooks() != nbLibros) {
	    		System.out.println("Error 3.1.1 : The number of books changed even after a refuse");
	    	}
	    }
	    catch (Exception e) {
	    	System.out.println("Error 3.1.1, Unexpected Exception : " + e);
	    	e.printStackTrace();
	    }
			
	    try {
	    	System.out.println("Error 3.1.2 : Testing if adding a book with an uninstantiated title has been accepted ");
	    	sn.addItemBook("Antoine", "antoine", "", "qsdfgh", "aaaaa", 123);		
	    }
	    catch (BadEntryException e) {
	    	if (sn.nbBooks() != nbLibros)
	    		System.out.println("Error 3.1.2 : The number of books after a refused adding has been modified ");
	    }
	    catch (Exception e) {
	    	System.out.println("Error 3.1.2, Unexpected Exception : " + e);
	    	e.printStackTrace();
	    }
	    
	    nbLibros = sn.nbBooks();
	    try {
	    	System.out.println("Error 3.1.3 : Testing if adding a book with a title that doesn't contain any character, other than spaces, has been accepted ");
	    	sn.addItemBook("Antoine", "antoine", "  ", "qsdfgh", "aaaaa", 123);		
	    }
	    catch (BadEntryException e) {
	    	if (sn.nbBooks() != nbLibros)
	    		System.out.println("Error 3.1.3 : The number of books after a refused adding has been modified ");
	    }
	    catch (Exception e) {
	    	System.out.println("Error 3.1.3, Unexpected Exception : " + e);
	    	e.printStackTrace();
	    }
	    
	    nbLibros = sn.nbBooks();
	    try {
	    	System.out.println("Error 3.1.4 : Testing if adding a book whose genre is not instantiated has been accepted ");
	    	sn.addItemBook("Antoine", "antoine", "titre", "", "aaaaa", 123);		
	    }
	    catch (BadEntryException e) {
	    	if (sn.nbBooks() != nbLibros)
	    		System.out.println("Error 3.1.4 : The number of books after a refused adding has been modified ");
	    }
	    catch (Exception e) {
	    	System.out.println("Error 3.1.4, Unexpected Exception : " + e);
	    	e.printStackTrace();
	    }
	    
	    
	    nbLibros = sn.nbBooks();
	    try {
	    	System.out.println("Error 3.1.5 : Testing if adding a book whose author isn't instantiated has been accepted ");
	    	sn.addItemBook("Antoine", "antoine", "titre", "genre", "", 123);		
	    }
	    catch (BadEntryException e) {
	    	if (sn.nbBooks() != nbLibros)
	    		System.out.println("Error 3.1.5 :  The number of books after a refused adding has been modified ");
	    }
	    catch (Exception e) {
	    	System.out.println("Error 3.1.5, Unexpected Exception : " + e);
	    	e.printStackTrace();
	    }
	    
	    nbLibros = sn.nbBooks();
	    try {
	    	System.out.println("Error 3.1.6 : Testing if adding a book whose number of pages is negative has been accepted ");
	    	sn.addItemBook("Antoine", "antoine", "titre", "genre", "auteur", -12);		
	    }
	    catch (BadEntryException e) {
	    	if (sn.nbBooks() != nbLibros)
	    		System.out.println("Error 3.1.6 : The number of books after a refused adding has been modified ");
	    }
	    catch (Exception e) {
	    	System.out.println("Error 3.1.6, Unexpected Exception : " + e);
	    	e.printStackTrace();
	    }
	    
	    nbLibros = sn.nbBooks();
	    try {
	    	System.out.println("Error 3.1.7 : Testing if adding a book whose number of pages is 0 has been accepted ");
	    	sn.addItemBook("Antoine", "antoine", "titre", "genre", "auteur", 0);		
	    }
	    catch (BadEntryException e) {
	    	if (sn.nbBooks() != nbLibros)
	    		System.out.println("Error 3.1.7 : The number of books after a refused adding has been modified ");
	    }
	    catch (Exception e) {
	    	System.out.println("Error 3.1.7, Unexpected Exception : " + e);
	    	e.printStackTrace();
	    }
	    
	    nbLibros = sn.nbBooks();
	    try {
	    	System.out.println("Error 3.1.8 : Testing if adding a book with an already existing title (with different case) has been accepted ");
	    	sn.addItemBook("Alice", "alice", "la Peste", "roman", " Albert Camus", 336);	
	    }
	    catch (ItemBookAlreadyExistsException e) {
	    	if (sn.nbBooks() != nbLibros)
	    		System.out.println("Error 3.1.8 :  The number of books after a refused adding has been modified ");
	    }
	    catch (Exception e) {
	    	System.out.println("Error 3.1.8, Unexpected Exception : " + e);
	    	e.printStackTrace();
	    }

			
	    // We try to add a film with correct entries
	    nbFilms = sn.nbFilms();
	    
	    try {
	    	System.out.println("Error 3.2.0 : Testing if adding a film with correct entries works");
	    	sn.addItemFilm("Paul", "paul", "xxxx", "yyyy", "zzzz", "aaaa", 123);
	    	if (sn.nbFilms() == nbFilms) {
	    		System.out.println("Error 3.2.0 : Adding a film doesn't work correctly");
	    	}
	    }
	    catch (Exception e) {
	    	System.out.println("Error 3.2.0, Unexpected Exception : " + e);
	    	e.printStackTrace();
	    }
	    
	    // We try to add a film with incorrect entries
	    nbFilms = sn.nbFilms();	
	    
	    try {
	    	System.out.println("Error 3.2.1 : Testing if adding the same film with another member is accepted");
	    	sn.addItemFilm("Antoine", "antoine", "xxxx", "yyyy", "zzzz", "aaaa", 123);
	    }
	    catch (ItemFilmAlreadyExistsException e) {
	    	if (sn.nbFilms() != nbFilms) {
	    		System.out.println("Error 3.2.1 : The number of films changed when we added the same movie with a different member");
	    	}
	    }
	    catch (Exception e) {
	    	System.out.println("Error 3.2.1, Unexpected Exception : " + e);
	    	e.printStackTrace();
	    }
	    
	    try {
	    	System.out.println("Error 3.2.2 : Testing if adding a film whose title isn't instantiated has been accepted ");
	    	sn.addItemFilm("Paul", "paul", "", "qsdfgh", "realisateur", "scenariste", 123);	
	    }
	    catch (BadEntryException e) {
	    	if (sn.nbFilms() != nbFilms)
	    		System.out.println("Error 3.2.2 : The number of films after a refused adding has been modified ");
	    }
	    catch (Exception e) {
	    	System.out.println("Error 3.2.2, Unexpected Exception : " + e);
	    	e.printStackTrace();
	    }
	    
	    nbFilms = sn.nbFilms();
	    try {
	    	System.out.println("Error 3.2.3 : Testing if adding a film whose title doesn't contain any character, other than spaces has been accepted ");
	    	sn.addItemFilm("Paul", "paul", "  ", "qsdfgh", "realisateur", "scenariste", 123);	
	    }
	    catch (BadEntryException e) {
	    	if (sn.nbFilms() != nbFilms)
	    		System.out.println("Error 3.2.3 :  The number of films after a refused adding has been modified ");
	    }
	    catch (Exception e) {
	    	System.out.println("Error 3.2.3, Unexpected Exception : " + e);
	    	e.printStackTrace();
	    }
	    
	    nbFilms = sn.nbFilms();
	    try {
	    	System.out.println("Error 3.2.4 : Testing if adding a film whose genre isn't instantiated has been accepted ");
	    	sn.addItemFilm("Paul", "paul", "titre", "", "realisateur", "scenariste", 123);	
	    }
	    catch (BadEntryException e) {
	    	if (sn.nbFilms() != nbFilms)
	    		System.out.println("Error 3.2.4 :  The number of films after a refused adding has been modified ");
	    }
	    catch (Exception e) {
	    	System.out.println("Error 3.2.4, Unexpected Exception : " + e);
	    	e.printStackTrace();
	    }
	    
	    nbFilms = sn.nbFilms();
	    try {
	    	System.out.println("Error 3.2.5 : Testing if adding a film whose director isn't instantiated has been accepted ");
	    	sn.addItemFilm("Paul", "paul", "titre", "genre", "", "scenariste", 123);	
	    }
	    catch (BadEntryException e) {
	    	if (sn.nbFilms() != nbFilms)
	    		System.out.println("Error 3.2.5 :  The number of films after a refused adding has been modified ");
	    }
	    catch (Exception e) {
	    	System.out.println("Error 3.2.5, Unexpected Exception : " + e);
	    	e.printStackTrace();
	    }
	    
	    nbFilms = sn.nbFilms();
	    try {
	    	System.out.println("Error 3.2.6 : Testing if adding a film whose scenarist isn't instantiated has been accepted ");
	    	sn.addItemFilm("Paul", "paul", "titre", "genre", "realisateur", "", 123);	
	    }
	    catch (BadEntryException e) {
	    	if (sn.nbFilms() != nbFilms)
	    		System.out.println("Error 3.2.6 :  The number of films after a refused adding has been modified ");
	    }
	    catch (Exception e) {
	    	System.out.println("Error 3.2.6, Unexpected Exception : " + e);
	    	e.printStackTrace();
	    }
	    
	    nbFilms = sn.nbFilms();
	    try {
	    	System.out.println("Error 3.2.7 : Testing if adding a film whose duration is negative has been accepted ");
	    	sn.addItemFilm("Paul", "paul", "titre", "genre", "realisateur", "scenariste", -12);	
	    }
	    catch (BadEntryException e) {
	    	if (sn.nbFilms() != nbFilms)
	    		System.out.println("Error 3.2.7 :  The number of films after a refused adding has been modified ");
	    }
	    catch (Exception e) {
	    	System.out.println("Error 3.2.7, Unexpected Exception : " + e);
	    	e.printStackTrace();
	    }
	    
	    nbFilms = sn.nbFilms();
	    try {
	    	System.out.println("Error 3.2.8 : Testing if adding a film whose duration is 0 has been accepted ");
	    	sn.addItemFilm("Paul", "paul", "titre", "genre", "realisateur", "scenariste", 0);	
	    }
	    catch (BadEntryException e) {
	    	if (sn.nbFilms() != nbFilms)
	    		System.out.println("Error 3.2.8 :  The number of films after a refused adding has been modified ");
	    }
	    catch (Exception e) {
	    	System.out.println("Error 3.2.8, Unexpected Exception : " + e);
	    	e.printStackTrace();
	    }

		// We try to add a film with the same features as an already existing books and vice versa
	    nbLibros = sn.nbBooks();
	    nbFilms = sn.nbFilms();
	    
	    sn.addItemFilm("Alice", "alice", "Lignes de faille", "scifi", "Spielberg", "Nancy Huston", 220);
	    
	    
	    if (nbLibros != sn.nbBooks()) {
	    	System.out.println("Error 3.3 :  The number of books after we used addItemFilm has been modified ");				
	    }
	    
	    nbLibros = sn.nbBooks();
	    nbFilms = sn.nbFilms();
	    sn.addItemBook("Alice", "alice", "Le train sifflera trois fois", "western", "Carl Foreman", 85);
	    
	    if (nbFilms != sn.nbFilms()) {
	    	System.out.println("Error 3.4 : The number of films after we used addItemBook has been modified");
	    }

	    if (nbMiembros != sn.nbMembers()) {
	    	System.out.println("Error 3.5 :  The number of members after we used addItemFilm and addItemLivre has been modified ");
	    }

	}
	catch (Exception e) {
	    System.out.println("Unexpected Exception : " + e);
	    e.printStackTrace();
	}
    }


    public static void reviewItemTest() {
    	
    	System.out.println("Testing the item's reviewing of the social network  ");
    	
    	try {
    		ISocialNetwork sn = new SocialNetwork();
    		
    		// We add 3 members with correct entries
    	    sn.addMember("Paul", "paul", "impulsive reader");
    	    sn.addMember("Antoine", "antoine", "great lover of litterature");
    	    sn.addMember("Alice", "alice", "cartoons passionate");
    	    
    	    // We add 3 books and 4 films with correct entries
    	    sn.addItemBook("Alice", "alice", "Lignes de faille", "roman", "Nancy Huston", 220);
    	    sn.addItemFilm("Alice", "alice", "Le train sifflera trois fois", "western 1952", "Fred Zinnemann", "Carl Foreman", 85);
    	    sn.addItemBook("Paul", "paul", "La peste", "roman", " Albert Camus", 336);
    	    sn.addItemFilm("Paul", "paul", "Avant l'aube", "thriller 2011", "Raphael Jacoulot", "Lise Macheboeuf et Raphael Jacoulot", 104);
    	    sn.addItemBook("Antoine", "antoine", "Guerre et Paix", "roman", "Leon Tosltoi", 1247);
    	    sn.addItemFilm("Antoine", "antoine", "Le discours d'un roi", "drame historique 2010", "Tom Hooper", "David Seidler", 118);
    	    sn.addItemFilm("Alice", "alice", "Black Swan", "drame 2010", "Darren Aronofsky", "John McLaughlin et Mark Heyman et Andres Heinz", 103);
    	    sn.addItemBook("Alice", "alice", "Le train sifflera trois fois", "roman", " J. W. Cunningham", 257);
    	    sn.addItemFilm("Paul", "paul", "Guerre et Paix", "aventure historique", "King Vidor", "Bridget Boland, Robert Westbery", 200);
    	    
    	    // We start by testing if a correct review of a book and a film works correctly
    	    
    	    try {
    	    	System.out.println("Error 4.1.0 : Testing if reviewing a book item gives back the mean of the marks");
    	    	if (sn.reviewItemBook("Alice", "alice", "Guerre et Paix", 2.0f, "A bit long") != 2.0f) {
    	    		System.out.println("Error 4.1.0 : The value returned by the method is false");
    	    	}
    	    }
    	    catch (Exception e) {
    	    	System.out.println("Error 4.1.0, Unexpected Exception : " + e);
    	    	e.printStackTrace();
    	    }
    	    
    	    
    	    try {
    	    	System.out.println("Error 4.2.0 : Testing if reviewing a film item gives back the mean of the marks");
    	    	if (sn.reviewItemFilm("Alice", "alice", "Guerre et Paix", 4.5f, "We don't see the time passing") != 4.5f) {
    	    		System.out.println("Error 4.2.0 : The value returned by the method is false");
    	    	}
    	    }
    	    catch (Exception e) {
    	    	System.out.println("Error 4.2.0, Unexpected Exception : " + e);
    	    	e.printStackTrace();
    	    }
    	    
    	    // Then we try to review an item with a non existing member
    	    try {
    	    	System.out.println("Error 4.1.1 : Testing if reviewing a book item by a non-existent member works");
    	    	sn.reviewItemBook("Michel", "alice", "Guerre et Paix", 2.0f, "A bit long");
    	    }
    	    catch (NotMemberException e) {
    	    }
    	    catch (Exception e) {
    	    	System.out.println("Error 4.1.1, Unexpected Exception : " + e);
    	    	e.printStackTrace();
    	    }
    	    
    	    try {
    	    	System.out.println("Error 4.2.1 : Testing if reviewing a film item by a non-existent member works");
    	    	sn.reviewItemFilm("Michel", "alice", "Guerre et Paix", 4.5f, "We don't see the time passing");
    	    }
    	    catch (NotMemberException e) {
    	    }
    	    catch (Exception e) {
    	    	System.out.println("Error 4.2.1, Unexpected Exception : " + e);
    	    	e.printStackTrace();
    	    }
    	    
    	    // Finally we try to review items with incorrect entries
    	    try {
    	    	System.out.println("Error 4.1.2 : Testing if reviewing a book item with a non instantiated title works");
    	    	sn.reviewItemBook("Alice", "alice", "", 2.0f, "A bit long");
    	    }
    	    catch (BadEntryException e) {
    	    }
    	    catch (Exception e) {
    	    	System.out.println("Error 4.1.2, Unexpected Exception : " + e);
    	    	e.printStackTrace();
    	    }
    	    
    	    try {
    	    	System.out.println("Error 4.1.3 : Testing if reviewing a book item with a title containing only spaces works");
    	    	sn.reviewItemBook("Alice", "alice", "   ", 2.0f, "A bit long");
    	    }
    	    catch (BadEntryException e) {
    	    }
    	    catch (NotItemException e) {
    	    }
    	    catch (Exception e) {
    	    	System.out.println("Error 4.1.3, Unexpected Exception : " + e);
    	    	e.printStackTrace();
    	    }
    	    
    	    try {
    	    	System.out.println("Error 4.1.4 : Testing if reviewing a book item with a good title with wrong cass works");
    	    	sn.reviewItemBook("Alice", "alice", "Guerre ET PaIx", 2.0f, "A bit long");
    	    }
    	    catch (NotItemException e) {
    	    }
    	    catch (Exception e) {
    	    	System.out.println("Error 4.1.4, Unexpected Exception : " + e);
    	    	e.printStackTrace();
    	    }
    	    
    	    try {
    	    	System.out.println("Error 4.1.5 : Testing if reviewing a book item with a negative mark works");
    	    	sn.reviewItemBook("Alice", "alice", "Guerre et Paix", -2f, "A bit long");
    	    }
    	    catch (BadEntryException e) {
    	    }
    	    catch (Exception e) {
    	    	System.out.println("Error 4.1.5, Unexpected Exception : " + e);
    	    	e.printStackTrace();
    	    }
    	    
    	    try {
    	    	System.out.println("Error 4.1.6 : Testing if reviewing a book item with a mark bigger than 5 works");
    	    	sn.reviewItemBook("Alice", "alice", "Guerre et Paix", 1039f, "A bit long");
    	    }
    	    catch (BadEntryException e) {
    	    }
    	    catch (Exception e) {
    	    	System.out.println("Error 4.1.6, Unexpected Exception : " + e);
    	    	e.printStackTrace();
    	    }
    	    
    	    try {
    	    	System.out.println("Error 4.1.7 : Testing if reviewing a book item with a non instantiated comment works");
    	    	sn.reviewItemBook("Alice", "alice", "Guerre et Paix", 2.0f, "");
    	    }
    	    catch (BadEntryException e) {
    	    }
    	    catch (Exception e) {
    	    	System.out.println("Error 4.1.7, Unexpected Exception : " + e);
    	    	e.printStackTrace();
    	    }
    	    
    	    try {
    	    	System.out.println("Error 4.1.8 : Testing if reviewing a book item with a comment containing only spaces works");
    	    	sn.reviewItemBook("Alice", "alice", "Guerre et Paix", 2.0f, "   ");
    	    }
    	    catch (BadEntryException e) {
    	    }
    	    catch (Exception e) {
    	    	System.out.println("Error 4.1.8, Unexpected Exception : " + e);
    	    	e.printStackTrace();
    	    }
    	    
    	    try {
    	    	System.out.println("Error 4.2.2 : Testing if reviewing a film item with a non instantiated title works");
    	    	sn.reviewItemFilm("Alice", "alice", "", 2.0f, "A bit long");
    	    }
    	    catch (BadEntryException e) {
    	    }
    	    catch (Exception e) {
    	    	System.out.println("Error 4.2.2, Unexpected Exception : " + e);
    	    	e.printStackTrace();
    	    }
    	    
    	    try {
    	    	System.out.println("Error 4.2.3 : Testing if reviewing a film item with a title containing only spaces works");
    	    	sn.reviewItemFilm("Alice", "alice", "   ", 2.0f, "A bit long");
    	    }
    	    catch (BadEntryException e) {
    	    }
    	    catch (NotItemException e) {
    	    }
    	    catch (Exception e) {
    	    	System.out.println("Error 4.2.3, Unexpected Exception : " + e);
    	    	e.printStackTrace();
    	    }
    	    
    	    try {
    	    	System.out.println("Error 4.2.4 : Testing if reviewing a film item with a good title with wrong cass works");
    	    	sn.reviewItemFilm("Alice", "alice", "Guerre ET PaIx", 2.0f, "A bit long");
    	    }
    	    catch (NotItemException e) {
    	    }
    	    catch (Exception e) {
    	    	System.out.println("Error 4.2.4, Unexpected Exception : " + e);
    	    	e.printStackTrace();
    	    }
    	    
    	    try {
    	    	System.out.println("Error 4.2.5 : Testing if reviewing a film item with a negative mark works");
    	    	sn.reviewItemFilm("Alice", "alice", "Guerre et Paix", -2f, "A bit long");
    	    }
    	    catch (BadEntryException e) {
    	    }
    	    catch (Exception e) {
    	    	System.out.println("Error 4.2.5, Unexpected Exception : " + e);
    	    	e.printStackTrace();
    	    }
    	    
    	    try {
    	    	System.out.println("Error 4.2.6 : Testing if reviewing a film item with a mark bigger than 5 works");
    	    	sn.reviewItemFilm("Alice", "alice", "Guerre et Paix", 1000000f, "A bit long");
    	    }
    	    catch (BadEntryException e) {
    	    }
    	    catch (Exception e) {
    	    	System.out.println("Error 4.2.6, Unexpected Exception : " + e);
    	    	e.printStackTrace();
    	    }
    	    
    	    try {
    	    	System.out.println("Error 4.2.7 : Testing if reviewing a film item with a non instantiated comment works");
    	    	sn.reviewItemFilm("Alice", "alice", "Guerre et Paix", 2.0f, "");
    	    }
    	    catch (BadEntryException e) {
    	    }
    	    catch (Exception e) {
    	    	System.out.println("Error 4.2.7, Unexpected Exception : " + e);
    	    	e.printStackTrace();
    	    }
    	    
    	    try {
    	    	System.out.println("Error 4.2.8 : Testing if reviewing a film item with a comment containing only spaces works");
    	    	sn.reviewItemFilm("Alice", "alice", "Guerre et Paix", 2.0f, "   ");
    	    }
    	    catch (BadEntryException e) {
    	    }
    	    catch (Exception e) {
    	    	System.out.println("Error 4.2.8, Unexpected Exception : " + e);
    	    	e.printStackTrace();
    	    }
    	    
    	    
    	    
    	}
    	catch (Exception e) {
    		System.out.println("Error 4, Unexpected Exception : " + e);
    		e.printStackTrace();
    	}
	

    }

    
    public static void consultItemsTest() {

	int nbMiembros = 0;
	int nbLibros = 0;
	int nbFilms = 0;
		
	System.out.println("Testing the item's consultations of the social network  ");
		
	try {
	    ISocialNetwork sn = new SocialNetwork();
			
	    // We add 3 members with correct entries
	    sn.addMember("Paul", "paul", "impulsive reader");
	    sn.addMember("Antoine", "antoine", "great lover of litterature");
	    sn.addMember("Alice", "alice", "cartoons passionate");
	  
	    // We add 3 books and 4 films with correct entries
	    sn.addItemBook("Alice", "alice", "Lignes de faille", "roman", "Nancy Huston", 220);
	    sn.addItemFilm("Alice", "alice", "Le train sifflera trois fois", "western 1952", "Fred Zinnemann", "Carl Foreman", 85);
	    sn.addItemBook("Paul", "paul", "La peste", "roman", " Albert Camus", 336);
	    sn.addItemFilm("Paul", "paul", "Avant l'aube", "thriller 2011", "Raphael Jacoulot", "Lise Macheboeuf et Raphael Jacoulot", 104);
	    sn.addItemBook("Antoine", "antoine", "Guerre et Paix", "roman", "Leon Tosltoi", 1247);
	    sn.addItemFilm("Antoine", "antoine", "Le discours d'un roi", "drame historique 2010", "Tom Hooper", "David Seidler", 118);
	    sn.addItemFilm("Alice", "alice", "Black Swan", "drame 2010", "Darren Aronofsky", "John McLaughlin et Mark Heyman et Andres Heinz", 103);
	    sn.addItemBook("Alice", "alice", "Le train sifflera trois fois", "roman", " J. W. Cunningham", 257);
	    sn.addItemFilm("Paul", "paul", "Guerre et Paix", "aventure historique", "King Vidor", "Bridget Boland, Robert Westbery", 200);
	    
	    // Review of a film and a book having the same title and by multiple people 
	    sn.reviewItemFilm("Alice", "alice", "Guerre et Paix", 4.5f, "We don't see the time passing");	
	    sn.reviewItemFilm("Antoine", "antoine", "Guerre et Paix", 3.5f, "We don't see the time passing");	
	    sn.reviewItemBook("Alice", "alice", "Guerre et Paix", 2.0f, "A bit long");	
	    sn.reviewItemBook("Paul", "paul", "Guerre et Paix", 3.0f, "A bit long");	
			
	    // testing consultItem
	    nbMiembros = sn.nbMembers();
	    nbFilms = sn.nbFilms();
	    nbLibros = sn.nbBooks();
	    
	    try {
			System.out.println("Error 5.0 : Testing if the consultation of an existing item found corresponding items");
			LinkedList <String> consultingList = sn.consultItems("Guerre et Paix");
			if (consultingList.size() == 0) {
				System.out.println("Error 5.0 : The consultation of an existing item didn't found the corresponding items");
			}			
		}
	    catch (Exception e) {
	    	System.out.println("Error 5.0, Unexpected Exception : " + e);
	    	e.printStackTrace();
	    }
	    
	    try {
	    	System.out.println("Error 5.1 : Testing if the consultation of an item whose name isn't instantiated has been accepted ");
	    	sn.consultItems("");		
	    }
	    catch (BadEntryException e) {
	    }
	    catch (Exception e) {
	    	System.out.println("Error 5.1, Unexpected Exception : " + e);
	    	e.printStackTrace();
	    }
	    
	    try {
	    	System.out.println("Error 5.2 : Testing if the consultation of an item whose name doesn't contain any character other than spaces has been accepted ");
	    	sn.consultItems("  ");		
	    }
	    catch (BadEntryException e) {
	    }
	    catch (NotItemException e) {
	    	
	    }
	    catch (Exception e) {
	    	System.out.println("Error 5.2, Unexpected Exception : " + e);
	    	e.printStackTrace();
	    }
	    
		try {
			System.out.println("Error 5.3 : Testing if the consultation of an inexisting item found corresponding items");
			sn.consultItems("La malaria");	
		}
		catch (NotItemException e) {
		}
		catch (Exception e) {
			System.out.println("Error 5.3, Unexpected Exception : " + e);
			e.printStackTrace();
		}
		
	
	    try {
	    	System.out.println("Error 5.4 : Testing if the consultation of an existing book item wrongly spelled (spaces) finds a matching item ");
	    	sn.consultItems(" La Peste  ");	
	    }
	    catch (BadEntryException e) {
	    }
	    catch (NotItemException e) {
	    }
	    catch (Exception e) {
	    	System.out.println("Error 5.4, Unexpected Exception : " + e);
	    	e.printStackTrace();
	    }
	    
	    try {
	    	System.out.println("Error 5.5 : Testing if the consultation of an existing film spelled wrongly item (cass) finds a matching item");
	    	sn.consultItems(" BlaCk swan  ");	
	    }
	    catch (BadEntryException e) {
	    }
	    catch (NotItemException e) {
	    }
	    catch (Exception e) {
	    	System.out.println("Error 5.4, Unexpected Exception : " + e);
	    	e.printStackTrace();
	    }
	    
	    try {
	    	System.out.println("Error 5.6 : Testing if the consultation of an existing film and book item finds a matching item");
	    	LinkedList <String> liste2 = sn.consultItems("Guerre et Paix");	
	    	String sLivre = liste2.getFirst();
	    	String sFilm = liste2.get(2);
			String st;
			if (sLivre.contains("aventure historique")  && (sFilm.contains("roman"))) {
				st = sFilm;
				sFilm = sLivre;
				sLivre = st;
		    }
			if (sLivre.contains("King Vidor")  || sFilm.contains("Leon Tosltoi")) 					
				System.out.println("Error 5.6 :  The consultation of an existing film and book item doesn't render the chain of items corresponding ");				
			if (!sLivre.contains("Guerre et Paix")  &&  !sLivre.contains("roman"))
				System.out.println("Error 5.6 :  The consultation of an existing film and book item doesn't render the chain of book corresponding ");
			if (!sFilm.contains("Guerre et Paix")  &&  !sFilm.contains("aventure historique"))
				System.out.println("Error 5.6 :  The consultation of an existing film and book item doesn't render the chain of film corresponding ");
			if (!liste2.get(1).contains("2.5") || !liste2.getLast().contains("4.0")) 					
				System.out.println("Error 5.6 :  The consultation of an existing film and book item doesn't render the chain of review corresponding ");				
			
	    }
	    catch (BadEntryException e) {
	    	System.out.println("Error 5.6 :  The consultation of an existing film and book item whose name is correct hasn't been accepted ");
	    }
	    catch (Exception e) {
	    	System.out.println("Error 5.6, Unexpected Exception : " + e);
	    	e.printStackTrace();
	    }
			
	    if (nbMiembros != sn.nbMembers()) {
	    	System.out.println("Error 5.7 :  The number of members after we used consultItem has been modified ");
	    }
	    if (nbFilms != sn.nbFilms()) {
	    	System.out.println("Error 5.8 :  The number of films after we used consultItem has been modified");
	    }
	    if (nbLibros != sn.nbBooks()) {
	    	System.out.println("Error 5.9 :  The number of books after we used consultItem has been modified");				
	    }
	}
	catch (Exception e) {
	    System.out.println("Unexpected Exception : " + e);
	    e.printStackTrace();
	}
    }

    
    public static void main(String[] args) {
	initialisationTest();
	System.out.println("\n\n **********************************************************************************************\n");
	addMemberTest();
	System.out.println("\n\n **********************************************************************************************\n");
	addItemTest();
	System.out.println("\n\n **********************************************************************************************\n");
	reviewItemTest();
	System.out.println("\n\n **********************************************************************************************\n");
	consultItemsTest();
    }

}
