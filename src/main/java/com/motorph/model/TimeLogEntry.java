package com.motorph.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

public class TimeLogEntry {
    private final String firstName;
    private final String lastName;
    private final LocalDate date;
    private final LocalTime timeIn;
    private final LocalTime timeOut;

    public TimeLogEntry(String firstName, String lastName, LocalDate date, LocalTime timeIn, LocalTime timeOut) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.date = date;
        this.timeIn = timeIn;
        this.timeOut = timeOut;
    }

    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public LocalDate getDate() { return date; }
    public LocalTime getTimeIn() { return timeIn; }
    public LocalTime getTimeOut() { return timeOut; }

    @Override
    public String toString() {
        return firstName + " " + lastName + " | " + date + " | " + timeIn + " - " + timeOut;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TimeLogEntry that = (TimeLogEntry) o;
        return Objects.equals(firstName, that.firstName) &&
               Objects.equals(lastName, that.lastName) &&
               Objects.equals(date, that.date) &&
               Objects.equals(timeIn, that.timeIn) &&
               Objects.equals(timeOut, that.timeOut);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, date, timeIn, timeOut);
    }
}
