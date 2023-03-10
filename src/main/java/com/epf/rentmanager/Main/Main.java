package com.epf.rentmanager.Main;

import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.ReservationService;
import com.epf.rentmanager.service.VehicleService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {

        System.out.println("requête : ");
        try{
            System.out.println(ClientService.getInstance().findAll());
            System.out.println();
            System.out.println(ClientService.getInstance().count());
            System.out.println(VehicleService.getInstance().count());


        }
        catch(Exception e){
        }

    }

}