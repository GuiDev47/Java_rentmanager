package com.epf.rentmanager.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.epf.rentmanager.Exception.DaoException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.persistence.ConnectionManager;
import com.epf.rentmanager.service.VehicleService;
import org.springframework.stereotype.Repository;

@Repository
public class ReservationDao {

	private ReservationDao() {}
	
	private static final String CREATE_RESERVATION_QUERY = "INSERT INTO Reservation(client_id, vehicle_id, debut, fin) VALUES(?, ?, ?, ?);";
	private static final String DELETE_RESERVATION_QUERY = "DELETE FROM Reservation WHERE id=?;";
	private static final String FIND_RESERVATIONS_BY_CLIENT_QUERY = "SELECT id, vehicle_id, debut, fin FROM Reservation WHERE client_id=?;";
	private static final String FIND_RESERVATIONS_BY_VEHICLE_QUERY = "SELECT id, client_id, debut, fin FROM Reservation WHERE vehicle_id=?;";
	private static final String FIND_RESERVATIONS_QUERY = "SELECT id, client_id, vehicle_id, debut, fin FROM Reservation;";
	private static final String FIND_RESERVATION_QUERY = "SELECT id, client_id, vehicle_id, debut, fin FROM Reservation WHERE id=?;";
	public long create(Reservation reservation) throws DaoException {
		try{
			Connection connection = ConnectionManager.getConnection();

			PreparedStatement ps = connection.prepareStatement(CREATE_RESERVATION_QUERY, Statement.RETURN_GENERATED_KEYS);
			ps.setLong(1, reservation.getClient_id());
			ps.setLong(2, reservation.getVehicle_id());
			ps.setDate(3, Date.valueOf(reservation.getDebut()));
			ps.setDate(4, Date.valueOf(reservation.getFin()));
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
	
	public long delete(Reservation reservation) throws DaoException {
		try{
			Connection connection = ConnectionManager.getConnection();

			PreparedStatement ps = connection.prepareStatement(DELETE_RESERVATION_QUERY, Statement.RETURN_GENERATED_KEYS);
			ps.setLong(1, reservation.getID());
			long id=reservation.getID();

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

	public Reservation findById(long id) throws DaoException {
		try{
			Connection connection= ConnectionManager.getConnection();

			PreparedStatement stat = connection.prepareStatement(FIND_RESERVATION_QUERY);
			stat.setLong(1, id);

			ResultSet rs = stat.executeQuery();

			rs.next();

			int client_id = rs.getInt("client_id");
			int vehicle_id = rs.getInt("vehicle_id");

			connection.close();

			return (new Reservation(id, client_id, vehicle_id));

		}
		catch(Exception e){
			e.printStackTrace();
			throw new DaoException();
		}


	}

	
	public List<Reservation> findResaByClientId(long clientId) throws DaoException {
		List<Reservation> reservationsByClientId = new ArrayList<Reservation>();

		try {
			Connection connection = ConnectionManager.getConnection();

			PreparedStatement stat = connection.prepareStatement(FIND_RESERVATIONS_BY_CLIENT_QUERY);
			stat.setLong(1, clientId);
			ResultSet rs = stat.executeQuery();

			while (rs.next()) {

				long id = rs.getLong("id");
				//String modele = rs.getString("prenom");
				long vehicle_id = rs.getLong("vehicle_id");

				reservationsByClientId.add(new Reservation(id, clientId, vehicle_id));
			}


		} catch (Exception e) {
			e.printStackTrace();
			throw new DaoException();
		}

		return reservationsByClientId;
	}
	
	public List<Reservation> findResaByVehicleId(long vehicleId) throws DaoException {

		List<Reservation> reservationsByVehicleId = new ArrayList<Reservation>();

		try {
			Connection connection = ConnectionManager.getConnection();

			PreparedStatement stat = connection.prepareStatement(FIND_RESERVATIONS_BY_VEHICLE_QUERY);
			stat.setLong(1, vehicleId);
			ResultSet rs = stat.executeQuery();

			while (rs.next()) {

				long id = rs.getLong("id");
				long client_id = rs.getLong("client_id");
				//String modele = rs.getString("prenom");


				reservationsByVehicleId.add(new Reservation(id, client_id, vehicleId));
			}


		} catch (Exception e) {
			e.printStackTrace();
			throw new DaoException();
		}

		return reservationsByVehicleId;
	}

	public List<Reservation> findAll() throws DaoException {
		List<Reservation> reservations = new ArrayList<Reservation>();
		try{
			Connection connection= ConnectionManager.getConnection();

			Statement statement = connection.createStatement();

			ResultSet rs = statement.executeQuery(FIND_RESERVATIONS_QUERY);

			while (rs.next()){
				long id = rs.getLong("id");
				int client_id = rs.getInt("client_id");
				int vehicle_id = rs.getInt("vehicle_id");

				reservations.add(new Reservation(id, client_id, vehicle_id));

			}

			connection.close();

		}catch(SQLException e){
			e.printStackTrace();
			throw new DaoException();
		}
		return reservations;
	}

	public int count() throws DaoException{

		int cpt = findAll().size();

		return cpt;
	}
}
