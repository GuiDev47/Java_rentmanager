package com.epf.rentmanager.service;

import com.epf.rentmanager.Exception.DaoException;
import com.epf.rentmanager.Exception.ServiceException;
import com.epf.rentmanager.dao.ReservationDao;
import com.epf.rentmanager.model.Reservation;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationService {

	private ReservationDao reservationDao;

	private ReservationService(ReservationDao reservationDao) {
		this.reservationDao = reservationDao;
	}

	
	
	public long create(Reservation reservation) throws ServiceException {
		try{
			return reservationDao.create(reservation);
		}catch(DaoException e){
			e.printStackTrace();
			throw new ServiceException();
		}
	}

	public long delete(Reservation reservation) throws ServiceException {
		try{
			return reservationDao.delete(reservation);
		}catch(DaoException e){
			e.printStackTrace();
			throw new ServiceException();
		}
	}
	public Reservation findById(long id) throws ServiceException {
		try{
			return reservationDao.findById(id);
		}catch(DaoException e){
			e.printStackTrace();
			throw new ServiceException();
		}
	}

	public List<Reservation> findAll() throws ServiceException {

		try{
			return reservationDao.findAll();
		}catch(DaoException e){
			e.printStackTrace();
			throw new ServiceException();
		}
	}

	public List<Reservation> findResaByClientId(long clientId) throws ServiceException{
		try{
			return reservationDao.findResaByClientId(clientId);
		}catch(DaoException e){
			e.printStackTrace();
			throw new ServiceException();
		}
	}

	public List<Reservation> findResaByVehicleId(long vehicleId) throws ServiceException{
		try{
			return reservationDao.findResaByVehicleId(vehicleId);
		}catch(DaoException e){
			e.printStackTrace();
			throw new ServiceException();
		}
	}

	public int count() throws ServiceException{
		try{
			return reservationDao.count();
		}catch(DaoException e){
			e.printStackTrace();
			throw new ServiceException();
		}
	}



	
}
