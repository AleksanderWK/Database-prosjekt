package main;

import java.sql.Date;
import java.util.Collection;
import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) {
		
		MovieController movieCtrl = new MovieController();
		movieCtrl.connect();
		while (true) {
			System.out.println("1. Get all roles of a given actor.");
			System.out.println("2. Get all movies a given actor have played in.");
			System.out.println("3. Get the companies that makes most movies in each genre.");
			System.out.println("4. Add a person to the database.");
			System.out.println("5. Add a company to the database.");
			System.out.println("6. Add a movie to the database.");
			System.out.println("7. Add a review of a movie, episode or series.");
			System.out.println("What action do you want to make? (0 for quit)");
			
			Scanner scanner = new Scanner(System.in);
			int choice = Integer.parseInt(scanner.nextLine());
			
			if (choice == 0) break;
			switch(choice) {
			case 1:
				printTable(movieCtrl.getAllActors());
				System.out.println("Select ID of actor you want to get all roles of: ");
				int actorID = Integer.parseInt(scanner.nextLine());
				System.out.println("All actor roles: " + movieCtrl.roleNamesOfActor(actorID));
				break;
			case 2:
				printTable(movieCtrl.getAllActors());
				System.out.println("Write the name of the actor: ");
				String actor = scanner.nextLine();
				printTable(movieCtrl.getMoviesFromActor(actor));
				break;
			case 3:
				printTable(movieCtrl.mostMoviesInGeneres());
				break;
			case 4:
				printTable(movieCtrl.getAllPersons());
				System.out.println("ID of new Person: ");
				int personID = Integer.parseInt(scanner.nextLine());
				System.out.println("Name of new Person: ");
				String name = scanner.nextLine();
				System.out.println("Birthyear of Person: ");
				int birthyear = Integer.parseInt(scanner.nextLine());
				System.out.println("Birth country of Person: ");
				String country = scanner.nextLine();
				movieCtrl.addPerson(personID, name, birthyear, country);
				break;
			case 5:
				printTable(movieCtrl.getAllCompanies());
				System.out.println("ID of new Company: ");
				int companyID = Integer.parseInt(scanner.nextLine());
				System.out.println("Name/Adresse of new Company: ");
				String adress = scanner.nextLine();
				System.out.println("URL of company: ");
				String URL = scanner.nextLine();
				System.out.println("Country Company: ");
				country = scanner.nextLine();
				movieCtrl.addCompany(companyID, adress, URL, country);
				break;
			case 6:
				printTable(movieCtrl.getAllVerk());
				System.out.println("ID of new Movie: ");
				int movieID = Integer.parseInt(scanner.nextLine());
				System.out.println("Title of new Movie: ");
				String title = scanner.nextLine();
				System.out.println("length of Movie: ");
				int length = Integer.parseInt(scanner.nextLine());
				System.out.println("Release Year of Movie: ");
				int releaseYear = Integer.parseInt(scanner.nextLine());
				System.out.println("Release Date of Movie: (yyyy-mm-dd format)");
				Date releaseDate = Date.valueOf(scanner.nextLine());
				System.out.println("Description of Movie: ");
				String description = scanner.nextLine();
				movieCtrl.addMovie(movieID, title, length, releaseYear, releaseDate, description);
				
				printTable(movieCtrl.getAllPersons());
				System.out.println("Add actors: (press enter on empty line to stop adding)");
				while (true) {
					System.out.println("Actors ID: ");
					String line = scanner.nextLine();
					if (line.isEmpty()) break;
					personID = Integer.parseInt(line);
					System.out.println("Role in movie: ");
					String roleName = scanner.nextLine();
					
					movieCtrl.addActorToVerk(personID, movieID, roleName);
				}
				

				printTable(movieCtrl.getAllPersons());
				System.out.println("Add Director: (press enter on empty line to stop adding)");
				while (true) {
					System.out.println("Directors ID: ");
					String line = scanner.nextLine();
					if (line.isEmpty()) break;
					personID = Integer.parseInt(line);
					
					movieCtrl.addPersonWorkingOnMovie(personID, movieID, "Regissør");
				}
				

				printTable(movieCtrl.getAllPersons());
				System.out.println("Add Writers: (press enter on empty line to stop adding)");
				while (true) {
					System.out.println("Writers ID: ");
					String line = scanner.nextLine();
					if (line.isEmpty()) break;
					personID = Integer.parseInt(line);
					
					movieCtrl.addPersonWorkingOnMovie(personID, movieID, "Manusforfatter");
				}
				
				printTable(movieCtrl.getAllGenres());
				System.out.println("Add Genres: (press enter on empty line to stop adding)");
				while (true) {
					System.out.println("Genres ID: ");
					String line = scanner.nextLine();
					if (line.isEmpty()) break;
					int genreID = Integer.parseInt(line);
					
					movieCtrl.addGenreToVerk(movieID, genreID);
				}
				
				printTable(movieCtrl.getAllCompanies());
				System.out.println("Add Companies: (press enter on empty line to stop adding)");
				while (true) {
					System.out.println("Company ID: ");
					String line = scanner.nextLine();
					if (line.isEmpty()) break;
					companyID = Integer.parseInt(line);
					
					movieCtrl.addCompanyToVerk(companyID, movieID);
				}
				break;
			case 7:
				System.out.println("Recorded reviews:");
				printTable(movieCtrl.getAllReviews());
				System.out.println("Registered users:");
				printTable(movieCtrl.getAllUsers());
				System.out.println("ID of your user: ");
				int brukerID = Integer.parseInt(scanner.nextLine());
				printTable(movieCtrl.getAllVerk());
				System.out.println("ID of title: ");
				int verkID = Integer.parseInt(scanner.nextLine());
				
				int rating = 0;
				while (!(rating >= 1 && rating <= 10)) {
					System.out.println("Rating (1-10): ");
					rating = Integer.parseInt(scanner.nextLine());
				}

				System.out.println("Review text: ");
				String kommentar = scanner.nextLine();
				
				movieCtrl.addReview(brukerID, verkID, rating, kommentar);
				System.out.println("Successfully added the review!");
				break;
			default:
				System.out.println("Not valid input try again:");
				break;
			}

		}
	}
	
	public static void printTable(Collection<String[]> tableData) {
		for (String[] row : tableData) {
			System.out.print("| ");
			for (int i = 0; i < row.length; i++) {
				System.out.print(row[i] + " | ");
			}
			System.out.println();
		}
	}
	
}
