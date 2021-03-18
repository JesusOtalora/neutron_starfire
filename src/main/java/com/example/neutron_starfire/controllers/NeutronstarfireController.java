package com.example.neutron_starfire.controllers;

import com.example.neutron_starfire.dto.InfosateliteDTO;
import com.example.neutron_starfire.dto.PositionDTO;
import com.example.neutron_starfire.dto.RespuestaDTO;
import com.example.neutron_starfire.dto.SateliteDTO;
import com.example.neutron_starfire.services.Servicio;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;

@RestController
public class NeutronstarfireController {
    private Servicio servicio = new Servicio();

    @PostMapping(path = "/topsecret")
    public RespuestaDTO topSecret(@RequestBody Map<String, List<SateliteDTO>> data){
        try{
            RespuestaDTO respuesta = new RespuestaDTO();
            String mensaje = servicio.obtenerMensaje(data.get("satellites"));
            if(!mensaje.equals("404")){
                respuesta.setMessage(mensaje);
                PositionDTO posicion = servicio.getPosition(servicio.getSatelites());
                respuesta.setPosition(posicion);
            }else{
                throw new ResponseStatusException( HttpStatus.NOT_FOUND, "Error.", new Exception() );
            }
            return respuesta;
        }catch (Exception e){
            throw new ResponseStatusException( HttpStatus.NOT_FOUND, "Error.", e );
        }
    }

    @PostMapping(path = "/topsecret_split/{satellite_name}")
    public void topSecretSplit(@RequestBody InfosateliteDTO data, @PathVariable(value = "satellite_name") String nombre){
        servicio.editarSatelite(nombre, data);
    }

    @GetMapping(path = "/topsecret_split")
    public RespuestaDTO topsecret_split(){
        String info = servicio.obtenerInformacion();
        RespuestaDTO respuesta = new RespuestaDTO();
        PositionDTO posicion = new PositionDTO();
        if (info.equals("404")){
            respuesta.setMessage("No hay suficiente información");
            posicion = null;
            respuesta.setPosition(posicion);
        }else{
            respuesta.setMessage(info);
            posicion = servicio.getPosition(servicio.getSatelites2());
            respuesta.setPosition(posicion);
        }
        return respuesta;
    }
}
