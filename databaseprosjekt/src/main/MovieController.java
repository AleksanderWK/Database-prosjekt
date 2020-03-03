package main;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

public class MovieController extends DBConn {
	
	private PreparedStatement currentStatement;	
	
	public MovieController() {
		
	}
	
	public void movieQueryTest() {
		try {
			currentStatement = conn.prepareStatement("select * from verk");
			ResultSet result = currentStatement.executeQuery();
			int currentRow = 1;
			while (result.next()) {
				System.out.println(result.getString("tittel"));
				currentRow++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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
					"group by sjanger, selskap");
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
	
	
	
	
}
