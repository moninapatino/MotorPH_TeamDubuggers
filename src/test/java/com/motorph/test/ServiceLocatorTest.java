package com.motorph.test;

import com.motorph.util.ServiceLocator;
import com.motorph.service.EmployeeService;
import com.motorph.service.EmployeeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ServiceLocatorTest {

    @BeforeEach
    void setUp() {
        ServiceLocator.clearServices();
    }

    @Test
    void testRegisterAndGetService() {
        EmployeeService service = new EmployeeServiceImpl();
        ServiceLocator.registerService(EmployeeService.class, service);
        
        EmployeeService retrieved = ServiceLocator.getService(EmployeeService.class);
        assertNotNull(retrieved);
        assertEquals(service, retrieved);
    }

    @Test
    void testGetNonexistentService() {
        assertThrows(IllegalStateException.class, () -> {
            ServiceLocator.getService(EmployeeService.class);
        });
    }
}
