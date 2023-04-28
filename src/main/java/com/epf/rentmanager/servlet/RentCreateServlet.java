package com.epf.rentmanager.servlet;

import com.epf.rentmanager.Configuration.AppConfiguration;
import com.epf.rentmanager.Exception.ServiceException;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.ReservationService;
import com.epf.rentmanager.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@WebServlet("/rents/create")
public class RentCreateServlet extends HttpServlet {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	ApplicationContext context = new AnnotationConfigApplicationContext(AppConfiguration.class);
	@Autowired
	VehicleService vehicleService;
	@Autowired
	ClientService clientService;
	@Autowired
	ReservationService reservationService;


	@Override
	public void init() throws ServletException {
		super.init();
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try{
			request.setAttribute("vehicles", vehicleService.findAll());
			request.setAttribute("clients", clientService.findAll());
		}catch(Exception e){
			throw new RuntimeException();
		}
		finally {
			this.getServletContext().getRequestDispatcher("/WEB-INF/views/rents/create.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-d");
		Reservation resa = new Reservation(Long.parseLong(request.getParameter("client")), Long.parseLong(request.getParameter("car")), LocalDate.parse(request.getParameter("begin"),formatter), LocalDate.parse(request.getParameter("end"),formatter));
		try{
			reservationService.create(resa);
		}catch (ServiceException e) {
			e.printStackTrace();
		}
		response.sendRedirect("/rentmanager/rents");
	}



}
