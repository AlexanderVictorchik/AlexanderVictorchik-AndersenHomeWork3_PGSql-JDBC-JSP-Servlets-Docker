package dao;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PsqlConnectionTest {

    PsqlConnection psqlConnection = new PsqlConnection();

    @Test
    void getConnection() {
        assertNotNull(psqlConnection);
    }
}