package com.epf.rentmanager.servlet;

import com.epf.rentmanager.Exception.ServiceException;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.VehicleService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/users")
public class UserServlet extends HttpServlet {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private ClientService clientService;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			request.setAttribute("clients", clientService.findAll());
		} catch (ServiceException e) {
			throw new RuntimeException();
		}
		finally {
			this.getServletContext().getRequestDispatcher("/WEB-INF/views/users/list.jsp").forward(request, response);
		}

	}
}


