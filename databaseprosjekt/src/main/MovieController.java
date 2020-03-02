package main;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

public class MovieController extends DBConn {
	
	private PreparedStatement currentStatement;	
	
	public MovieController() {
		
	}
	
	public void addPerson(int personID, String name, int birthyear, String country) {
		try {
			currentStatement = conn.prepareStatement("insert into Person values(?, ?, ?, ?)");
			currentStatement.setInt(1, personID);
			currentStatement.setString(2, name);
			currentStatement.setInt(3, birthyear);
			currentStatement.setString(4, country);
			currentStatement.execute();
		} catch (SQLException e) {
			System.out.println("Couldn't add Person");
		}
	}
	
	public void addMovie(int movieID, String title, int length, int releaseYear, Date releaseDate, String description) {
		try {
			currentStatement = conn.prepareStatement("insert into Verk values(?, ?, ?, ?, ?, ?)");
			currentStatement.setInt(1, movieID);
			currentStatement.setString(2, title);
			currentStatement.setInt(3, length);
			currentStatement.setInt(4, releaseYear);
			currentStatement.setDate(5, releaseDate);
			currentStatement.setString(6, description);
			currentStatement.execute();
			
			currentStatement = conn.prepareStatement("insert into Film values(?)");
			currentStatement.setInt(1, movieID);
			currentStatement.execute();
		} catch (SQLException e) {
			System.out.println("Couldn't add Movie");
		}
	}
	
	public void addActorToVerk(int personID, int verkID, String role) {
		
	}
	
	public void addPersonWorkingOnMovie(int personID, int verkID, String type) {
		
	}
	
	public Collection<String> roleNamesOfActorByName(String actorName) {
		try {
			currentStatement = conn.prepareStatement(
					"select distinct personID "
					+ "from skuespilleriverk natural join person "
					+ "where navn = ?"
				);
			currentStatement.setString(1, actorName);
			ResultSet result = currentStatement.executeQuery();
			result.next();
			int actorId = result.getInt("personID");
			return roleNamesOfActor(actorId);
		} catch (SQLException e) {
			e.printStackTrace();
			return new ArrayList<String>();
		}		
	}
	
	public Collection<String> roleNamesOfActor(int actorID) {
		Collection<String> roleNames = new ArrayList<String>();
		try {
			currentStatement = conn.prepareStatement(
					"select distinct rolle "
					+ "from skuespilleriverk as spiv "
					+ "where spiv.personID = ?");
			currentStatement.setInt(1, actorID);
			ResultSet result = currentStatement.executeQuery();
			while(result.next()) {
				roleNames.add(result.getString("rolle"));
			}
			
		} catch (SQLException e) {
			return new ArrayList<String>();
		}
		
		return roleNames;
	}
	
	public Collection<String[]> getAllPersons() {
		Collection<String[]> actorRows = new ArrayList<String[]>();
		try {
			currentStatement = conn.prepareStatement(
					"select personID, navn, foedselsaar, foedselsland "
					+ "from person"
					);
			ResultSet result = currentStatement.executeQuery();
			while (result.next()) {
				String[] row = {((Integer) result.getInt("personID")).toString(), result.getString("navn"), result.getString("foedselsaar"), result.getString("foedselsland")};
				actorRows.add(row);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return actorRows;
	}
	
	public Collection<String[]> getAllActors() {
		Collection<String[]> actorRows = new ArrayList<String[]>();
		try {
			currentStatement = conn.prepareStatement(
					"select distinct personID, navn, foedselsaar, foedselsland "
					+ "from skuespillerIVerk natural join person"
					);
			ResultSet result = currentStatement.executeQuery();
			while (result.next()) {
				String[] row = {((Integer) result.getInt("personID")).toString(), result.getString("navn"), result.getString("foedselsaar"), result.getString("foedselsland")};
				actorRows.add(row);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return actorRows;
	}
	
	public Collection<String[]> getAllVerk() {
		Collection<String[]> actorRows = new ArrayList<String[]>();
		try {
			currentStatement = conn.prepareStatement(
					"select * "
					+ "from verk"
					);
			ResultSet result = currentStatement.executeQuery();
			while (result.next()) {
				String[] row = {((Integer) result.getInt("verkID")).toString(),
							result.getString("tittel"), 
							((Integer) result.getInt("lengde")).toString(),
							((Integer) result.getInt("utgivelsesaar")).toString(),
							result.getString("lanseringsdato"),
							result.getString("innhold")};
				actorRows.add(row);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return actorRows;
	}
	
	public Collection<String[]> getMoviesFromActor(String ActorName){
		Collection<String[]> moviesFromActor = new ArrayList<>();
		try {
			currentStatement = conn.prepareStatement(
					"select tittel " 
					 + "from (skuespilleriverk natural join verk) natural join person " 
					 + "where navn = " + "'" + ActorName + "'");
			ResultSet result = currentStatement.executeQuery();
			while(result.next()) {
				String[] record = {result.getString("tittel")};
				moviesFromActor.add(record);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return moviesFromActor;
	}
	
	public Collection<String[]> mostMoviesInGeneres(){
		Collection<String[]> r = new ArrayList<>();
		try {
			currentStatement = conn.prepareStatement("");
			ResultSet result = currentStatement.executeQuery();
			while(result.next()) {
				String[] record = {result.getString("navn"), result.getString("adresse")}; //adresse er navn på selskap (?)
				r.add(record);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return r;
	}
	
	
	
	
}
