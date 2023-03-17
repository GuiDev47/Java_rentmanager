package com.epf.rentmanager.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.epf.rentmanager.Exception.DaoException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.persistence.ConnectionManager;
import org.springframework.stereotype.Repository;

@Repository
public class VehicleDao {

	private VehicleDao() {}
	
	private static final String CREATE_VEHICLE_QUERY = "INSERT INTO Vehicle(constructeur, nb_places) VALUES(?, ?);";
	private static final String DELETE_VEHICLE_QUERY = "DELETE FROM Vehicle WHERE id=?;";
	private static final String FIND_VEHICLE_QUERY = "SELECT id, constructeur, nb_places FROM Vehicle WHERE id=?;";
	private static final String FIND_VEHICLES_QUERY = "SELECT id, constructeur, nb_places FROM Vehicle;";
	
	public long create(Vehicle vehicle) throws DaoException {
		return 0;
	}

	public long delete(Vehicle vehicle) throws DaoException {
		return 0;
	}

	public Vehicle findById(long id) throws DaoException {
		try {
			Connection connection = ConnectionManager.getConnection();

			PreparedStatement stat = connection.prepareStatement(FIND_VEHICLE_QUERY);
			stat.setLong(1, id);

			ResultSet rs = stat.executeQuery();

			rs.next();

			String constructeur = rs.getString("constructeur");
			//String modele = rs.getString("prenom");
			int nb_places = rs.getInt("nb_places");

			return (new Vehicle(id, constructeur, nb_places));
		} catch (Exception e) {
			e.printStackTrace();
			throw new DaoException();
		}
	}

	public List<Vehicle> findAll() throws DaoException {


		List<Vehicle> vehicles = new ArrayList<Vehicle>();
		try{
			Connection connection= ConnectionManager.getConnection();

			Statement statement = connection.createStatement();

			ResultSet rs = statement.executeQuery(FIND_VEHICLES_QUERY);

			while (rs.next()){
				long id = rs.getLong("id");
				String constructeur = rs.getString("constructeur");
				int nb_places = rs.getInt("nb_places");

				vehicles.add(new Vehicle(id, constructeur, nb_places));

			}

			connection.close();

		}catch(SQLException e){
			e.printStackTrace();
			throw new DaoException();
		}
		return vehicles;
	}

	public int count() throws DaoException{

		int cpt = findAll().size();

		return cpt;
	}
	

}
