package service;

import dao.PhoneDAOImp;
import entity.Phone;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class PhoneService {

    private final PhoneDAOImp phoneDAOImp;

    public PhoneService(PhoneDAOImp phoneDAOImp) {
        this.phoneDAOImp = phoneDAOImp;
    }

    public void insertPhone(HttpServletRequest request, HttpServletResponse response){
        int price = Integer.parseInt(request.getParameter("price"));
        String model = request.getParameter("model");
        String vendor_name = request.getParameter("vendor_name");
        String vendor_site = request.getParameter("vendor_site");
        Phone newPhone = new Phone(price, model, vendor_name, vendor_site);
        try {
            phoneDAOImp.insert(newPhone);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            response.sendRedirect("list");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showNewForm(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher dispatcher = request.getRequestDispatcher("phone-form.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    public void showEditForm(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        Optional<Phone> exPhone = phoneDAOImp.select(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("phone-form.jsp");
        request.setAttribute("phone", exPhone);
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    public void listPhone(HttpServletRequest request, HttpServletResponse response) {
        List<Phone> listPhone = phoneDAOImp.selectAll();
        request.setAttribute("listPhone", listPhone);
        RequestDispatcher dispatcher = request.getRequestDispatcher("phone-list.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    public void updatePhone(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        int price = Integer.parseInt(request.getParameter("price"));
        String model = request.getParameter("model");
        String vendor_name = request.getParameter("vendor_name");
        String vendor_site = request.getParameter("vendor_site");
        Phone upphone = new Phone(id, price, model, vendor_name, vendor_site);
        try {
            phoneDAOImp.update(upphone);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            response.sendRedirect("list");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deletePhone(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        Phone dephone = new Phone();
        try {
            phoneDAOImp.delete(dephone);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            response.sendRedirect("list");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

