package com.epf.rentmanager.servlet;

import com.epf.rentmanager.Configuration.AppConfiguration;
import com.epf.rentmanager.Exception.ServiceException;
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

@WebServlet("/cars/create")
public class CreateCarServlet extends HttpServlet {

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

			this.getServletContext().getRequestDispatcher("/WEB-INF/views/vehicles/create.jsp").forward(request, response);
	}



}
