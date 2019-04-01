package com.addicted2u.commandFabrics;

import com.addicted2u.commands.*;
import com.addicted2u.entitys.ClientEntity;
import com.addicted2u.entitys.ProcedureEntity;
import org.hibernate.Session;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CommandFabric {
    public static Command getCommand(HttpServletRequest request, Session session) {
        switch (request.getParameter("code")) {
            case ("med-serv-search"): {
                return (new GetServicesByName(request.getParameter("info"), session));
            }
            case ("get-order-info"): {
                return (new GetOrderInfo(request.getParameter("service"), session));
            }
            case ("authorization"): {
                return (new Authorization(request.getParameter("login"), request.getParameter("password"), session));
            }
            case ("confirm-order"): {
                try {
                    return (new ConfirmOrder((new ProcedureEntity(
                            Integer.parseInt(request.getParameter("client_id")),
                            Integer.parseInt(request.getParameter("order_id")),
                            Integer.parseInt(request.getParameter("doctor_id")),
                            (new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("date")))
                    )), session));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            case ("user-registration"): {
                return (new RegisterUser((new ClientEntity(
                        request.getParameter("login"),
                        request.getParameter("password"),
                        request.getParameter("name"),
                        request.getParameter("surname"),
                        request.getParameter("patronum"),
                        request.getParameter("mobile"),
                        request.getParameter("email")
                )), session));
            }
            default: {
                return null;
            }
        }
    }
}
