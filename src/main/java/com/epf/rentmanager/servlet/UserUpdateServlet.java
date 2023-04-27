package com.epf.rentmanager.servlet;

import com.epf.rentmanager.Configuration.AppConfiguration;
import com.epf.rentmanager.Exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.service.ClientService;
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

@WebServlet("/users/update")
public class UserUpdateServlet extends HttpServlet {

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

        try {
            long id = Long.parseLong(request.getParameter("id"));
            request.setAttribute("client", clientService.findById(id));
        } catch (ServiceException e) {
            throw new RuntimeException();
        }
        finally {
            this.getServletContext().getRequestDispatcher("/WEB-INF/views/users/update.jsp").forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-d");
        Client client = new Client(Long.parseLong(request.getParameter("id")), request.getParameter("last_name"), (request.getParameter("first_name")), request.getParameter("email"), LocalDate.parse(request.getParameter("Date"), formatter));
        try{
            clientService.update(client);
        }catch (ServiceException e) {
            e.printStackTrace();
        }
        response.sendRedirect("/rentmanager/users");
    }



}
