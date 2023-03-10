package com.epf.rentmanager.servlet;

import com.epf.rentmanager.Exception.ServiceException;
import com.epf.rentmanager.service.ClientService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/users/details")
public class UserDetailsServlet extends HttpServlet {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private ClientService clientService = ClientService.getInstance();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			long id = Long.parseLong(request.getParameter("id"));
			request.setAttribute("client", clientService.findById(id));
		} catch (ServiceException e) {
			throw new RuntimeException();
		}
		finally {
			this.getServletContext().getRequestDispatcher("/WEB-INF/views/users/details.jsp").forward(request, response);
		}

	}
}


