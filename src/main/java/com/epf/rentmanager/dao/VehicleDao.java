package com.epf.rentmanager.dao;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.epf.rentmanager.Exception.DaoException;
import com.epf.rentmanager.Exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.persistence.ConnectionManager;
import org.springframework.stereotype.Repository;

@Repository
public class VehicleDao {

	private VehicleDao() {}
	
	private static final String CREATE_VEHICLE_QUERY = "INSERT INTO Vehicle(constructeur, modele, nb_places) VALUES(?, ?, ?);";
	private static final String DELETE_VEHICLE_QUERY = "DELETE FROM Vehicle WHERE id=?;";
	private static final String FIND_VEHICLE_QUERY = "SELECT id, constructeur, modele, nb_places FROM Vehicle WHERE id=?;";
	private static final String FIND_VEHICLES_QUERY = "SELECT id, constructeur, modele, nb_places FROM Vehicle;";
	private static final String FIND_RESERVATIONS_VEHICLE_BY_CLIENT_QUERY = "SELECT * FROM Reservation INNER JOIN Vehicle ON Reservation.vehicle_id = Vehicle.id WHERE client_id=?;";
	private static final String UPDATE_VEHICLE_QUERY = "UPDATE Vehicle SET constructeur = ?,modele = ? ,nb_places = ? WHERE id = ?;";
	public long create(Vehicle vehicle) throws DaoException {
		try{
			Connection connection = ConnectionManager.getConnection();

			PreparedStatement ps = connection.prepareStatement(CREATE_VEHICLE_QUERY, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, vehicle.getConstructeur());
			ps.setString(2, vehicle.getModele());
			ps.setInt(3, vehicle.getNb_places());
			ps.execute();
			ResultSet rs = ps.getGeneratedKeys();
			rs.next();
			int id = rs.getInt(1);

			ps.close();;
			connection.close();

			return id;
		}
		catch(Exception e){
			e.printStackTrace();
			throw new DaoException();
		}
	}

	public long delete(Vehicle vehicle) throws DaoException {
		try{
			Connection connection = ConnectionManager.getConnection();

			PreparedStatement ps = connection.prepareStatement(DELETE_VEHICLE_QUERY, Statement.RETURN_GENERATED_KEYS);
			ps.setLong(1, vehicle.getID());
			long id=vehicle.getID();

			ps.execute();
			ResultSet rs = ps.getGeneratedKeys();

			rs.next();

			ps.close();
			connection.close();

			return id;
		}
		catch(Exception e){
			e.printStackTrace();
			throw new DaoException();
		}
	}

	public Vehicle findById(long id) throws DaoException {
		try {
			Connection connection = ConnectionManager.getConnection();

			PreparedStatement stat = connection.prepareStatement(FIND_VEHICLE_QUERY);
			stat.setLong(1, id);

			ResultSet rs = stat.executeQuery();

			rs.next();

			String constructeur = rs.getString("constructeur");
			String modele = rs.getString("modele");
			int nb_places = rs.getInt("nb_places");

			return (new Vehicle(id, constructeur, modele, nb_places));
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
				String modele = rs.getString("modele");

				vehicles.add(new Vehicle(id, constructeur, modele, nb_places));

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

	public List<Vehicle> findVehicleByClientId(long clientId) throws DaoException {
		List<Vehicle> CarsByClientId = new ArrayList<Vehicle>();

		try {
			Connection connection = ConnectionManager.getConnection();
			PreparedStatement stmt = connection.prepareStatement(FIND_RESERVATIONS_VEHICLE_BY_CLIENT_QUERY);
			stmt.setLong(1, clientId);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Vehicle car = new Vehicle(rs.getLong("vehicle_id"),
						rs.getString("constructeur"),
						rs.getString("modele"),
						rs.getInt("nb_places"));

				CarsByClientId.add(car);
			}
			connection.close();
			return CarsByClientId;
		} catch (SQLException e) {
			throw new DaoException();
		}
	}

	public long update(Vehicle vehicle) throws DaoException {
		try {
			Connection connection = ConnectionManager.getConnection();
			PreparedStatement ps = connection.prepareStatement(UPDATE_VEHICLE_QUERY, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, vehicle.getConstructeur());
			ps.setString(2, vehicle.getModele());
			ps.setInt(3, vehicle.getNb_places());
			ps.setLong(4, vehicle.getID());
			long id = ps.executeUpdate();
			connection.close();
			return id;
		} catch (SQLException e) {
			throw new DaoException();
		}
	}
	

}
