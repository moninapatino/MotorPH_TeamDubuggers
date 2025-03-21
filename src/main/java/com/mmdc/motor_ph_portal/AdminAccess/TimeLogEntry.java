
package com.mmdc.motor_ph_portal.AdminAccess;

 
public class TimeLogEntry {
     String firstName;
     String lastName;
     String startDate;
     String endDate;
     String timeIn;
     String timeOut;

    public TimeLogEntry(String firstName, String lastName, String startDate, String endDate, String timeIn, String timeOut) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.timeIn = timeIn;
        this.timeOut = timeOut;
    }
        // Getters
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getStartDate() {
        return startDate;
    }
    
    public String getEndDate() {
        return endDate;
    }

    public String getTimeIn() {
        return timeIn;
    }

    public String getTimeOut() {
        return timeOut;
    }
    
    
}
