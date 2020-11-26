package com.ognjengaric.demo.dto;

public class CourseProgressDTO {
    private String total;
    private String drivingRange;
    private String low;
    private String medium;
    private String high;
    private String night = "0/0";   
    private String rural;
    private String load;

    public CourseProgressDTO() {
    }

    public CourseProgressDTO(String total, String drivingRange, String low, String medium, String high, String rural, String load, String night) {
        this.total = total;
        this.drivingRange = drivingRange;
        this.low = low;
        this.medium = medium;
        this.high = high;
        this.rural = rural;
        this.load = load;
        this.night = night;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getDrivingRange() {
        return drivingRange;
    }

    public void setDrivingRange(String drivingRange) {
        this.drivingRange = drivingRange;
    }

    public String getLow() {
        return low;
    }

    public void setLow(String low) {
        this.low = low;
    }

    public String getMedium() {
        return medium;
    }

    public void setMedium(String medium) {
        this.medium = medium;
    }

    public String getHigh() {
        return high;
    }

    public void setHigh(String high) {
        this.high = high;
    }

    public String getNight() {
        return night;
    }

    public void setNight(String night) {
        this.night = night;
    }

    public String getRural() {
        return rural;
    }

    public void setRural(String rural) {
        this.rural = rural;
    }

    public String getLoad() {
        return load;
    }

    public void setLoad(String load) {
        this.load = load;
    }
}
