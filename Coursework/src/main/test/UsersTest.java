package main.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import main.UsersDBManager;

public class UsersTest {

    @Test
    void testRegisterDuplicateUsername() {
        int result = UsersDBManager.registerPlayer("testuser4", "testuser4");
        int resultAgain = UsersDBManager.registerPlayer("testuser1", "testuser1");

        assertTrue(result > 0, "First registration should succeed");
        assertEquals(-1, resultAgain, "Duplicate username should fail");
    }

    @Test
    void testLoginInvalidCredentials() {
        int invalidLogin = UsersDBManager.login("userWrong", "passWrong");
        assertEquals(-1, invalidLogin, "Invalid login should fail");

        int validLogin = UsersDBManager.login("testuser1", "testuser1");
        assertTrue(validLogin > 0, "Valid login should return userID");
    }
}
