package main.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import main.UsersDBManager;

public class UsersTest {

    @Test
    void testRegisterDuplicateUsername() {
        boolean result = UsersDBManager.registerPlayer("testuser1", "testuser1");
        boolean resultAgain = UsersDBManager.registerPlayer("testuser1", "testuser1");

        assertFalse(resultAgain);
    }

    @Test
    void testLoginInvalidCredentials() {
        String role = UsersDBManager.login("userWrong", "passWrong");
        assertNull(role);
        
        String newRole = UsersDBManager.login("testuser1", "testuser1");
        assertNotNull(newRole);
    }
}
