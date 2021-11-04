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

public class PhoneService {

    private final PhoneDAOImp phoneDAOImp;

    public PhoneService(PhoneDAOImp phoneDAOImp) {
        this.phoneDAOImp = phoneDAOImp;
    }

    public void insertPhone(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int price = Integer.parseInt(request.getParameter("price"));
        String model = request.getParameter("model");
        String vendor_name = request.getParameter("vendor_name");
        String vendor_site = request.getParameter("vendor_site");
        Phone newPhone = new Phone(price, model, vendor_name, vendor_site);
        phoneDAOImp.insert(newPhone);
        response.sendRedirect("list");
    }

    public void listPhone(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Phone> listPhone = phoneDAOImp.selectAll();
        request.setAttribute("listPhone", listPhone);
        RequestDispatcher dispatcher = request.getRequestDispatcher("phone-list.jsp");
        dispatcher.forward(request, response);
    }

    public void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("phone-form.jsp");
        dispatcher.forward(request, response);
    }

    public void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Phone exPhone = phoneDAOImp.select(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("phone-form.jsp");
        request.setAttribute("phone", exPhone);
        dispatcher.forward(request, response);
    }
    public void updatePhone(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        int price = Integer.parseInt(request.getParameter("price"));
        String model = request.getParameter("model");
        String vendor_name = request.getParameter("vendor_name");
        String vendor_site = request.getParameter("vendor_site");

        Phone phone = new Phone(id, price, model, vendor_name, vendor_site);
        phoneDAOImp.update(phone);
        response.sendRedirect("list");
    }
    public void deletePhone(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        phoneDAOImp.delete(id);
        response.sendRedirect("list");

    }
}

