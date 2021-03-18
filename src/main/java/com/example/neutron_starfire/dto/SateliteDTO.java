package com.example.neutron_starfire.dto;

public class SateliteDTO {
    public SateliteDTO (){
    }

    public SateliteDTO (String name, double pos_x, double pos_y){
        this.name = name;
        this.pos_x = pos_x;
        this.pos_y = pos_y;
    }

    private String name;
    private double distance;
    private String message[];
    private double pos_x;
    private double pos_y;

    //Getters y Setters
    public String getName() { return name; }
    public Double getDistance() { return distance; }
    public String[] getMessage() { return message; }
    public double getPos_x() { return pos_x; }
    public double getPos_y() { return pos_y; }

    public void setName(String name) { this.name = name; }
    public void setMessage(String[] message) { this.message = message; }
    public void setDistance(double distance) { this.distance = distance; }
    public void setPos_x(double pos_x) { this.pos_x = pos_x; }
    public void setPos_y(double pos_y) { this.pos_y = pos_y; }
}
