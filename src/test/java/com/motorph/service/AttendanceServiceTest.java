package com.motorph.test.service;

import com.motorph.dao.AttendanceDAO;
import com.motorph.model.AttendanceRecord;
import com.motorph.service.AttendanceService;
import com.motorph.service.AttendanceServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AttendanceServiceTest {
    @Mock
    private AttendanceDAO attendanceDAO;
    private AttendanceService attendanceService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        attendanceService = new AttendanceServiceImpl(attendanceDAO);
    }

    @Test
    void testRecordTimeIn_success() {
        when(attendanceDAO.findCurrentDayRecord(anyString())).thenReturn(null);
        when(attendanceDAO.saveTimeIn(anyString())).thenReturn(true);

        boolean result = attendanceService.recordTimeIn("EMP001");
        assertTrue(result);
    }

    @Test
    void testRecordTimeIn_alreadyTimedIn() {
        AttendanceRecord mockRecord = mock(AttendanceRecord.class);
        when(mockRecord.getTimeIn()).thenReturn(java.time.LocalDateTime.now());
        when(attendanceDAO.findCurrentDayRecord(anyString())).thenReturn(mockRecord);

        boolean result = attendanceService.recordTimeIn("EMP001");
        assertFalse(result);
    }

    @Test
    void testRecordTimeOut_success() {
        AttendanceRecord mockRecord = mock(AttendanceRecord.class);
        when(mockRecord.getTimeOut()).thenReturn(null);
        when(attendanceDAO.findCurrentDayRecord(anyString())).thenReturn(mockRecord);
        when(attendanceDAO.saveTimeOut(anyString())).thenReturn(true);

        boolean result = attendanceService.recordTimeOut("EMP001");
        assertTrue(result);
    }
}
