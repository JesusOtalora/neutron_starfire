package com.example.neutron_starfire.dto;

public class RespuestaDTO {

    private String message;
    private PositionDTO position;

    //Getters y Setters
    public String getMessage() { return message; }
    public PositionDTO getPosition() { return position; }

    public void setMessage(String message) { this.message = message; }
    public void setPosition(PositionDTO position) { this.position = position; }
}
