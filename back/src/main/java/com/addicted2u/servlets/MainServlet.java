package com.addicted2u.servlets;

import com.addicted2u.commandFabrics.CommandFabric;
import com.addicted2u.commands.Command;
import com.addicted2u.sessionFactory.HibernateSessionFactory;
import org.hibernate.Session;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MainServlet extends HttpServlet {
    Session session = HibernateSessionFactory.getSessionFactory().openSession();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("get-request-request");

        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        resp.addHeader("Access-Control-Allow-Origin", "*");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("post-request-request");

        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        resp.addHeader("Access-Control-Allow-Origin", "*");

        Command command = CommandFabric.getCommand(req, session);
        resp.getWriter().write(command.execute().toString());
    }
}
