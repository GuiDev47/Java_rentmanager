package com.epf.rentmanager.servlet;

import com.epf.rentmanager.Configuration.AppConfiguration;
import com.epf.rentmanager.Exception.ServiceException;
import com.epf.rentmanager.model.Client;
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

@WebServlet("/users/create")
public class UserCreateServlet extends HttpServlet {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    ApplicationContext context = new AnnotationConfigApplicationContext(AppConfiguration.class);

    @Autowired
    ClientService clientService;


    @Override
    public void init() throws ServletException {
        super.init();
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

            this.getServletContext().getRequestDispatcher("/WEB-INF/views/users/create.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        System.out.println("premier ok");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-d");
        Client client = new Client(request.getParameter("last_name"), (request.getParameter("first_name")), request.getParameter("email"), LocalDate.parse(request.getParameter("Date"), formatter));
        System.out.println("premier ok");
        try{
            clientService.create(client);
        }catch (ServiceException e) {
            System.out.println("okkkkokokokok");
            e.printStackTrace();
        }
        response.sendRedirect("/rentmanager/users");
    }



}
