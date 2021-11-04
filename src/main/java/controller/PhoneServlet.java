package controller;

import dao.PhoneDAO;
import entity.Phone;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/")
public class PhoneServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private PhoneDAO phoneDAO;

    public void init() {
        phoneDAO = new PhoneDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();

        try {
            switch (action) {
                case "/new":
                    showNewForm(request, response);
                    break;
                case "/insert":
                    insertPhone(request, response);
                    break;
                case "/delete":
                    deletePhone(request, response);
                    break;
                case "/edit":
                    showEditForm(request, response);
                    break;
                case "/update":
                    updatePhone(request, response);
                    break;
                default:
                    listPhone(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void listPhone(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Phone> listPhone = phoneDAO.selectAllPhones();
        request.setAttribute("listPhone", listPhone);
        RequestDispatcher dispatcher = request.getRequestDispatcher("phone-list.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("phone-form.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Phone exPhone = phoneDAO.selectPhone(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("phone-form.jsp");
        request.setAttribute("phone", exPhone);
        dispatcher.forward(request, response);

    }

    private void insertPhone(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int price = Integer.parseInt(request.getParameter("price"));
        String model = request.getParameter("model");
        String vendor_name = request.getParameter("vendor_name");
        String vendor_site = request.getParameter("vendor_site");
        Phone newPhone = new Phone(price, model, vendor_name, vendor_site);
        phoneDAO.insertPhone(newPhone);
        response.sendRedirect("list");
    }

    private void updatePhone(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        int price = Integer.parseInt(request.getParameter("price"));
        String model = request.getParameter("model");
        String vendor_name = request.getParameter("vendor_name");
        String vendor_site = request.getParameter("vendor_site");

        Phone phone = new Phone(id, price, model, vendor_name, vendor_site);
        phoneDAO.updatePhone(phone);
        response.sendRedirect("list");
    }

    private void deletePhone(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        phoneDAO.deletePhone(id);
        response.sendRedirect("list");

    }

}