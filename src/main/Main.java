package main;

public class Main {
	
	public static void main(String[] args) {
		MovieController movieCtrl = new MovieController();
		movieCtrl.connect();
		movieCtrl.movieQueryTest();
		System.out.println(movieCtrl.roleNamesOfActor(1));
		System.out.println(movieCtrl.roleNamesOfActorByName("Harrison Ford"));
	}
	
}
