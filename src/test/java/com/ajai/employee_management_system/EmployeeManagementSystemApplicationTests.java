package com.ajai.employee_management_system;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EmployeeManagementSystemApplicationTests {

    @Test
    void testAddition() {

        int result = 2 + 3;

        assertEquals(5, result);
    }

    @Test
    void testTrue() {

        boolean isJavaAwesome = true;

        assertTrue(isJavaAwesome);
    }

    @Test
    void testException() {

        Exception exception = assertThrows(
                ArithmeticException.class,
                () -> {
                    int value = 10 / 0;
                });

        assertEquals("/ by zero", exception.getMessage());
    }
}