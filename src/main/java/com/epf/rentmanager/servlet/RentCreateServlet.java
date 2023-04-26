package com.epf.rentmanager.servlet;

import com.epf.rentmanager.Configuration.AppConfiguration;
import com.epf.rentmanager.Exception.ServiceException;
import com.epf.rentmanager.model.Vehicle;
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

@WebServlet("/cars/create")
public class RentCreateServlet extends HttpServlet {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	ApplicationContext context = new AnnotationConfigApplicationContext(AppConfiguration.class);
	@Autowired
	VehicleService vehicleService;


	@Override
	public void init() throws ServletException {
		super.init();
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

			this.getServletContext().getRequestDispatcher("/WEB-INF/views/vehicles/create.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		Vehicle vehicle = new Vehicle(request.getParameter("manufacturer"), Integer.parseInt(request.getParameter("seats")));
		System.out.println("premier ok");
		try{
			vehicleService.create(vehicle);
		}catch (ServiceException e) {
			System.out.println("okkkkokokokok");
			e.printStackTrace();
		}
		response.sendRedirect("/rentmanager/cars");
	}



}
