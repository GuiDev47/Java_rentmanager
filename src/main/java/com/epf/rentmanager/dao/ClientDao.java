package com.epf.rentmanager.dao;

import com.epf.rentmanager.Exception.DaoException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.persistence.ConnectionManager;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ClientDao {

	private ClientDao() {}
	
	private static final String CREATE_CLIENT_QUERY = "INSERT INTO Client(nom, prenom, email, naissance) VALUES(?, ?, ?, ?);";
	private static final String DELETE_CLIENT_QUERY = "DELETE FROM Client WHERE id=?;";
	private static final String FIND_CLIENT_QUERY = "SELECT nom, prenom, email, naissance FROM Client WHERE id=?;";
	private static final String FIND_CLIENTS_QUERY = "SELECT id, nom, prenom, email, naissance FROM Client;";
	
	public long create(Client client) throws DaoException {
		try{
			Connection connection = ConnectionManager.getConnection();

			PreparedStatement ps = connection.prepareStatement(CREATE_CLIENT_QUERY, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, client.getNom());
			ps.setString(2, client.getPrenom());
			ps.setString(3, client.getEmail());
			ps.setDate(4, Date.valueOf(client.getDateNaissance()));
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
	
	public long delete(Client client) throws DaoException {
		try{
			Connection connection = ConnectionManager.getConnection();

			PreparedStatement ps = connection.prepareStatement(DELETE_CLIENT_QUERY, Statement.RETURN_GENERATED_KEYS);
			ps.setLong(1, client.getID());
			long id=client.getID();

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

	public Client findById(long id) throws DaoException {
		try{
			Connection connection= ConnectionManager.getConnection();

			PreparedStatement stat = connection.prepareStatement(FIND_CLIENT_QUERY);
			stat.setLong(1, id);

			ResultSet rs = stat.executeQuery();

			rs.next();

			String nom =rs.getString("nom");
			String prenom = rs.getString("prenom");
			String email = rs.getString("email");
			LocalDate date = rs.getDate("naissance").toLocalDate();

			connection.close();



			return (new Client(id, nom, prenom,email,date));
		}
		catch(Exception e){
			e.printStackTrace();
			throw new DaoException();
		}


	}

	public List<Client> findAll() throws DaoException {

		List<Client> clients = new ArrayList<Client>();
		try{
			Connection connection= ConnectionManager.getConnection();

			Statement statement = connection.createStatement();

			ResultSet rs = statement.executeQuery(FIND_CLIENTS_QUERY);

			while (rs.next()){
				long id = rs.getLong("id");
				String nom =rs.getString("nom");
				String prenom = rs.getString("prenom");
				String email = rs.getString("email");
				LocalDate date = rs.getDate("naissance").toLocalDate();

				clients.add(new Client(id, nom, prenom,email,date));

			}

			connection.close();

		}catch(SQLException e){
			e.printStackTrace();
			throw new DaoException();
		}
		return clients;
	}

	public int count() throws DaoException{

		int cpt = findAll().size();

		return cpt;
	}



}
