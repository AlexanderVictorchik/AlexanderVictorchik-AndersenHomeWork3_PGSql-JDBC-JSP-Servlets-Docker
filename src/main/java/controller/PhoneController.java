package controller;

import dao.PhoneDAOImp;
import service.PhoneService;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/")
public class PhoneController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static PhoneService IMPL_S = new PhoneService(new PhoneDAOImp());
    public static void putPhones(PhoneService phoneService) {
        IMPL_S = phoneService;
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response){
        String action = request.getServletPath();
        switch (action) {
            case "/new":
                IMPL_S.showNewForm(request, response);
                break;
            case "/insert":
                IMPL_S.insertPhone(request, response);
                break;
            case "/delete":
                IMPL_S.deletePhone(request, response);
                break;
            case "/edit":
                IMPL_S.showEditForm(request, response);
                break;
            case "/update":
                IMPL_S.updatePhone(request, response);
                break;
            default:
                IMPL_S.listPhone(request, response);
                break;
        }
    }
}