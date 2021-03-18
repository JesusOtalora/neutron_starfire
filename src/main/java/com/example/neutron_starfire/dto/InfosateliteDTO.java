package com.example.neutron_starfire.dto;

public class InfosateliteDTO {
    private double distance;
    private String message[];

    public double getDistance() {
        return distance;
    }

    public String[] getMessage() {
        return message;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public void setMessage(String[] message) {
        this.message = message;
    }
}
