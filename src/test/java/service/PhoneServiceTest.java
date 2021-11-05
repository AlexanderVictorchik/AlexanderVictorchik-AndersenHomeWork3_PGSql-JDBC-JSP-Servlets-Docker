package service;

import dao.PhoneDAO;
import dao.PhoneDAOImp;
import entity.Phone;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.internal.stubbing.BaseStubbing;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PhoneServiceTest {

    static private HttpServletRequest request;
    static private HttpServletResponse response;
    static private PhoneDAOImp phoneDAOImp;
    static private PhoneService phoneService;


    @BeforeEach
    public void startUp() {
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        phoneDAOImp = mock(PhoneDAOImp.class);
        phoneService = new PhoneService(phoneDAOImp);
    }

    @Test
    void whenNewPhoneListLinkPushedThenRedirectToPhonesFormList() {
        when(request.getRequestDispatcher("phones-form.jsp")).thenReturn(new RequestDispatcher() {
            @Override
            public void forward(ServletRequest servletRequest, ServletResponse servletResponse) {

            }

            @Override
            public void include(ServletRequest servletRequest, ServletResponse servletResponse) {

            }
        });
    }

    @Test
    void whenPhoneUpdateLinkPushedThenPhoneDAOUpdatePerformed() {
        when(request.getParameter("id")).thenReturn(String.valueOf(2));
        when(request.getParameter("price")).thenReturn(String.valueOf(2000));
        when(request.getParameter("model")).thenReturn("Pixel XX");
        when(request.getParameter("vendor_name")).thenReturn("GoogleBrand");
        when(request.getParameter("vendor_site")).thenReturn("GoogleBrand.com");

        phoneService.updatePhone(request, response);
        try {
            verify(phoneDAOImp).update(any(Phone.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    void whenPhoneListLinkPushedThenPhoneDAOSelectAllPerformed() {
        List<Phone> phones = new ArrayList<>();
        phones.add(new Phone(2, 2222, "XModel", "XBrand", "xbrand.com"));
        when(request.getRequestDispatcher("phone-list.jsp")).thenReturn(new RequestDispatcher() {
            @Override
            public void forward(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {

            }

            @Override
            public void include(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {

            }
        });

        phoneService.listPhone(request, response);
        verify(phoneDAOImp).selectAll();
    }

    @Test
    void whenPhoneEditLinkPushedThenPhoneDAOSelectPerformed() {
        when(phoneDAOImp.select("1")).thenReturn(Optional.of(new Phone()));
        when(request.getRequestDispatcher("phone-form.jsp")).thenReturn(new RequestDispatcher() {
            @Override
            public void forward(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {

            }

            @Override
            public void include(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {

            }
        });
        phoneService.showEditForm(request, response);
        verify(phoneDAOImp).select(null);
    }

    @Test
    void whenPhoneSaveButtonPressedThenPhoneInsertPerformed() {
        when(request.getParameter("price")).thenReturn("333");
        when(request.getParameter("model")).thenReturn("Blade 3");
        when(request.getParameter("vendor_name")).thenReturn("ZTE");
        when(request.getParameter("vendor_phone")).thenReturn("zte.com");

        phoneService.insertPhone(request, response);
        try {
            verify(phoneDAOImp).insert(any(Phone.class));
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    @Test
    void whenPhoneDeleteLinkPushedThenPhoneDAODeletePerformed() {
        when(request.getParameter("id")).thenReturn("1");

        phoneService.deletePhone(request, response);
        try {
            verify(phoneDAOImp).delete(any(Phone.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}