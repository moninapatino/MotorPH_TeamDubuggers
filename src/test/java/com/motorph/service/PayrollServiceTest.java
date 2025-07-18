package com.motorph.service;

import com.motorph.dao.PayrollDAO;
import com.motorph.model.PayrollEntry;
import com.motorph.service.PayrollService;
import com.motorph.service.PayrollServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

class PayrollServiceTest {
    @Mock
    private PayrollDAO payrollDAO;
    private PayrollService payrollService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        payrollService = new PayrollServiceImpl(payrollDAO);
    }

    @Test
    void testGetCurrentPayrollPeriod_found() {
        PayrollEntry mockEntry = mock(PayrollEntry.class);
        when(payrollDAO.findByEmployeeIdAndPeriod(toString(), any(LocalDate.class), any(LocalDate.class)))
            .thenReturn(mockEntry);

        PayrollEntry result = payrollService.getCurrentPayrollPeriod("EMP001");
        assertNotNull(result);
    }

    @Test
    void testGetCurrentPayrollPeriod_notFound() {
        when(payrollDAO.findByEmployeeIdAndPeriod(toString(), any(LocalDate.class), any(LocalDate.class)))
            .thenReturn(null);

        PayrollEntry result = payrollService.getCurrentPayrollPeriod("EMP001");
        assertNull(result);
    }

    @Test
    void testGeneratePayslip_success() {
        PayrollEntry mockEntry = mock(PayrollEntry.class);
        when(payrollDAO.findByEmployeeIdAndPeriod(toString(), any(LocalDate.class), any(LocalDate.class)))
            .thenReturn(mockEntry);

        byte[] result = payrollService.generatePayslip("EMP001", LocalDate.now(), LocalDate.now());
        assertNotNull(result);
    }
}
