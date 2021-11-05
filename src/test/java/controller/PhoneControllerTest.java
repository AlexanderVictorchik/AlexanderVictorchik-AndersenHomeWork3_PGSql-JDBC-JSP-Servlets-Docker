package controller;

import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import service.PhoneService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PhoneControllerTest {

    static private HttpServletRequest request;
    static private HttpServletResponse response;
    static private PhoneController phoneController;
    static private PhoneService phoneService;

    @BeforeEach
    public void startUp() {
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        phoneController = mock(PhoneController.class);
        phoneService = mock(PhoneService.class);
        phoneController.putPhones(phoneService);
    }

    @SneakyThrows
    @Test
    void doPostCall() {
        Mockito.doCallRealMethod().when(phoneController).doPost(request, response);
        phoneController.doGet(request, response);
        Mockito.verify(phoneController).doGet(request, response);
    }

    @Test
    void doGetCall() {
        Mockito.when(request.getRequestDispatcher("phone-list.jsp"))
                .thenReturn(new RequestDispatcher() {
                    @Override
                    public void forward(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {

                    }

                    @Override
                    public void include(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {

                    }
                });
        Mockito.when(request.getServletPath()).thenReturn("new");
        Mockito.doCallRealMethod().when(phoneController).doGet(request, response);
        phoneController.doGet(request, response);
        Mockito.verify(phoneService).listPhone(request, response);
    }
}