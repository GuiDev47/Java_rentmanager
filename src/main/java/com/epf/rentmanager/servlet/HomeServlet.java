package com.epf.rentmanager.servlet;

import com.epf.rentmanager.Exception.ServiceException;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.VehicleService;

@WebServlet("/home")
public class HomeServlet extends HttpServlet {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private ClientService clientService = ClientService.getInstance();
	private VehicleService vehicleService = VehicleService.getInstance();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			request.setAttribute("nbClients", clientService.count());
			request.setAttribute("nbVoitures", vehicleService.count());
		} catch (ServiceException e) {
			throw new RuntimeException();
		}

		this.getServletContext().getRequestDispatcher("/WEB-INF/views/home.jsp").forward(request, response);
	}
}


