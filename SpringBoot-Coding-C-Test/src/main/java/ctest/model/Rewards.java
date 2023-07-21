package ctest.model;

import java.time.LocalDate;

public class Rewards {

    private Long customerId;
    private int points;
    private int month;
    private int year;

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Rewards(Long customerId, int points, LocalDate transactionDate) {
        this.customerId = customerId;
        this.points = points;
        this.month = transactionDate.getMonthValue();
        this.year = transactionDate.getYear();
    }

}
