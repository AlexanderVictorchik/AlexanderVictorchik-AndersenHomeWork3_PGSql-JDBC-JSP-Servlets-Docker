package dao;

import entity.Phone;
import lombok.SneakyThrows;
import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@Testcontainers
class PhoneDAOImpTest {

    private static PostgreSQLContainer postgreSQLContainer;
    private static Connection conn = null;
    private static PhoneDAOImp phoneDAOImp = new PhoneDAOImp();

    @BeforeAll
    public static void init() {
        postgreSQLContainer = (PostgreSQLContainer) new PostgreSQLContainer
                ("postgres:13-alpine")
                .withExposedPorts(5432);
        postgreSQLContainer.withDatabaseName("test").withUsername("test")
                .withPassword("test").withInitScript("tables-creation.sql");

        postgreSQLContainer.start();
        assertTrue(postgreSQLContainer.isCreated());
        System.out.println(postgreSQLContainer.getJdbcUrl());
        System.out.println(postgreSQLContainer.getUsername());
        System.out.println(postgreSQLContainer.getPassword());

        try {
            conn = DriverManager.getConnection(postgreSQLContainer.getJdbcUrl(),
                    postgreSQLContainer.getUsername(),
                    postgreSQLContainer.getPassword());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        phoneDAOImp.startConnection(conn);
    }

    @SneakyThrows
    @Test
    void insertNewPhone() {
        int price = 300;
        String model = "SomeModel1";
        String vendor_name = "SomeVendor1";
        String vendor_site = "somevendor1.com";
        Phone phone1 = new Phone(price, model, vendor_name, vendor_site);
        assertTrue(postgreSQLContainer.isCreated());
        try {
            assertTrue(phoneDAOImp.insert(phone1));
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @SneakyThrows
    @Test
    void selectPhoneId() {
        int price = 500;
        String model = "SomeModel2";
        String vendor_name = "SomeVendor2";
        String vendor_site = "somevendor2.com";
        Phone phone2 = new Phone(price, model, vendor_name, vendor_site);
        try {
            assertTrue(phoneDAOImp.insert(phone2));
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        assertEquals(500, phone2.getPrice());
    }

    @SneakyThrows
    @Test
    void selectAllPhones() {
        List<Phone> phoneList = phoneDAOImp.selectAll();
        assertFalse(phoneList.isEmpty());
        int price = 700;
        String model = "SomeModel3";
        String vendor_name = "SomeVendor3";
        String vendor_site = "somevendor3.com";
        Phone phone3 = new Phone(price, model, vendor_name, vendor_site);
        try {
            assertTrue(phoneDAOImp.insert(phone3));
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        assertEquals(700, phone3.getPrice());
    }

    @SneakyThrows
    @Test
    void deletePhoneInfo() {
        int phone_id = 1;
        int price = 900;
        String model = "SomeModel4";
        String vendor_name = "SomeVendor4";
        String vendor_site = "somevendor4.com";
        Phone phone4 = new Phone(phone_id, price, model, vendor_name, vendor_site);
        try {
            assertFalse(phoneDAOImp.delete(phone4));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @SneakyThrows
    @Test
    void updatePhoneInfo() {
        int phone_id = 1;
        int price = 1100;
        String model = "SomeModel5";
        String vendor_name = "SomeVendor5";
        String vendor_site = "somevendor5.com";
        Phone phone5 = new Phone(phone_id, price, model, vendor_name, vendor_site);
        try {
            assertFalse(phoneDAOImp.update(phone5));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}