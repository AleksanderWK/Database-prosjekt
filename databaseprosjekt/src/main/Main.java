package main;

import java.util.Collection;
import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) {
		/*
		MovieController movieCtrl = new MovieController();
		movieCtrl.connect();
		movieCtrl.movieQueryTest();
		System.out.println(movieCtrl.roleNamesOfActor(1));
		System.out.println(movieCtrl.roleNamesOfActorByName("Harrison Ford"));
		*/
		
		MovieController movieCtrl = new MovieController();
		movieCtrl.connect();
		while (true) {
			System.out.println("1. Get all roles of a given actor.");
			System.out.println("2. Get all movies a given actor have played in.");
			System.out.println("3. Get the companies that makes most movies in each genre.");
			System.out.println("4. Add movie to the database.");
			System.out.println("5. Add a new review of an episode of a series to the database.");
			System.out.println("What action do you want to make? (0 for quit)");
			
			Scanner scanner = new Scanner(System.in);
			int choice = scanner.nextInt();
			
			if (choice == 0) break;
			switch(choice) {
			case 1:
				printTable(movieCtrl.getAllActors());
				System.out.println("Select ID of actor you want to get all roles of: ");
				int actorID = scanner.nextInt();
				System.out.println("All actor roles: " + movieCtrl.roleNamesOfActor(actorID));
				break;
			case 2:
				Scanner scanner2 = new Scanner(System.in); //fungerte kun med nextLine() hvis jeg lagde en egen scanner her. 
				System.out.println("Write the name of the actor: ");
				String actor = scanner2.nextLine();
				scanner2.close();
				printTable(movieCtrl.getMoviesFromActor(actor));
				break;
			case 3:
				
				break;
				
			case 4:
				
				break;
			case 5:
				
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
