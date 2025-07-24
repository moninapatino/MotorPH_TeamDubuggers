
package com.mmdc.motor_ph_portal.DAO;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.Vector;


public interface EmployeeAttendanceDAO {
    boolean logTimeIn(String employeeId, String date, String time);
    boolean logTimeOut(String employeeId, String date, String time);
    void showAttendanceTable(DefaultTableModel attendanceTable);
    ArrayList<Vector<String>> getAttendanceRecordsByEmployeeId(String employeeId);
}
