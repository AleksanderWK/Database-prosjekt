package main;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

public class MovieDBController extends DBConn {
	
	private PreparedStatement currentStatement;	
	
	public MovieDBController() {
		
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
	
	public void addCompany(int companyID, String adress, String URL, String country) {
		try {
			currentStatement = conn.prepareStatement("insert into Selskap values(?, ?, ?, ?)");
			currentStatement.setInt(1, companyID);
			currentStatement.setString(2, adress);
			currentStatement.setString(3, URL);
			currentStatement.setString(4, country);
			currentStatement.execute();
		} catch (SQLException e) {
			System.out.println("Couldn't add Company");
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
		try {
			currentStatement = conn.prepareStatement("insert into SkuespillerIVerk values (?, ?, ?)");
			currentStatement.setInt(1, personID);
			currentStatement.setInt(2, verkID);
			currentStatement.setString(3, role);
			currentStatement.execute();
		} catch (SQLException e) {
			System.out.println("Couldn't Add Actor to Verk");
		}
		
	}
	
	public void addPersonWorkingOnMovie(int personID, int verkID, String type) {
		try {
			currentStatement = conn.prepareStatement("insert into AnsattIVerk values (?, ?, ?)");
			currentStatement.setInt(1, personID);
			currentStatement.setInt(2, verkID);
			currentStatement.setString(3, type);
			currentStatement.execute();
		} catch (SQLException e) {
			System.out.println("Couldn't Add Person to Verk");
		}
	}
	
	public void addGenreToVerk(int verkID, int sjangerID) {
		try {
			currentStatement = conn.prepareStatement("insert into VerkSjanger values (?, ?)");
			currentStatement.setInt(1, verkID);
			currentStatement.setInt(2, sjangerID);
			currentStatement.execute();
		} catch (SQLException e) {
			System.out.println("Couldn't Add Genre to Verk");
		}
	}
	
	public void addCompanyToVerk(int companyID, int verkID) {
		try {
			currentStatement = conn.prepareStatement("insert into SelskapVerk values (?, ?)");
			currentStatement.setInt(1, companyID);
			currentStatement.setInt(2, verkID);
			currentStatement.execute();
		} catch (SQLException e) {
			System.out.println("Couldn't Add Company to Verk");
		}
	}
	
	public void addReview(int brukerID, int verkID, int rating, String kommentar) {
		try {
			currentStatement = conn.prepareStatement("insert into Anmeldelse values(?, ?, ?, ?)");
			currentStatement.setInt(1, brukerID);
			currentStatement.setInt(2, verkID);
			currentStatement.setInt(3, rating);
			currentStatement.setString(4, kommentar);
			currentStatement.execute();
		} catch (SQLException e) {
			System.out.println("Couldn't add Review");
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
			System.out.println("Couldn't get All Verks");
		}
		return actorRows;
	}
	
	public Collection<String[]> getAllCompanies() {
		Collection<String[]> companyRows = new ArrayList<String[]>();
		try {
			currentStatement = conn.prepareStatement(
					"select * "
					+ "from Selskap"
					);
			ResultSet result = currentStatement.executeQuery();
			while (result.next()) {
				String[] row = {((Integer) result.getInt("selskapID")).toString(),
							result.getString("adresse"), 
							result.getString("url"),
							result.getString("land")};
				companyRows.add(row);
			}
		} catch (SQLException e) {
			System.out.println("Couldn't get all companies");
		}
		return companyRows;
	}
	
	public Collection<String[]> getAllGenres() {
		Collection<String[]> genreRows = new ArrayList<String[]>();
		try {
			currentStatement = conn.prepareStatement(
					"select * "
					+ "from Sjanger"
					);
			ResultSet result = currentStatement.executeQuery();
			while (result.next()) {
				String[] row = {((Integer) result.getInt("sjangerID")).toString(), result.getString("navn")};
				genreRows.add(row);
			}
		} catch (SQLException e) {
			System.out.println("Couldn't get all Genres");
		}
		return genreRows;
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
			currentStatement = conn.prepareStatement(
					"select sjanger, selskap, max(SSF.antallFilmer) as 'Max antall' " + 
					"from (select adresse as Selskap, navn as Sjanger, count(navn) as antallFilmer " + 
					"		from ((((verk natural join verksjanger) natural join selskapverk) natural join sjanger) natural join selskap) " + 
					"		group by adresse, navn) as SSF " + 
					"group by sjanger");
			ResultSet result = currentStatement.executeQuery();
			while(result.next()) {
				String[] record = {result.getString("sjanger"), result.getString("selskap"), result.getString("Max antall")}; //adresse er navn på selskap (?)
				r.add(record);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return r;
	}
	
	public Collection<String[]> getAllUsers() {
		Collection<String[]> userRows = new ArrayList<String[]>();
		try {
			currentStatement = conn.prepareStatement(
					"select * "
					+ "from Bruker"
					);
			ResultSet result = currentStatement.executeQuery();
			while (result.next()) {
				String[] row = {((Integer) result.getInt("brukerID")).toString(),
								result.getString("brukernavn"),
								result.getString("epost"),
								};
				userRows.add(row);
			}
		} catch (SQLException e) {
			System.out.println("Couldn't get users.");
		}
		return userRows;
	}
	
	public Collection<String[]> getAllReviews() {
		Collection<String[]> reviewRows = new ArrayList<String[]>();
		try {
			currentStatement = conn.prepareStatement(
					"select * "
					+ "from Anmeldelse"
					);
			ResultSet result = currentStatement.executeQuery();
			while (result.next()) {
				String[] row = {((Integer) result.getInt("brukerID")).toString(),
								((Integer) result.getInt("verkID")).toString(),
								((Integer) result.getInt("raiting")).toString(),
								result.getString("kommentar")
								};
				reviewRows.add(row);
			}
		} catch (SQLException e) {
			System.out.println("Couldn't get reviews.");
		}
		return reviewRows;
	}
	
}
