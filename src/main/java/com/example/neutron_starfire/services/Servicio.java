package com.example.neutron_starfire.services;

import com.example.neutron_starfire.dto.InfosateliteDTO;
import com.example.neutron_starfire.dto.PositionDTO;
import com.example.neutron_starfire.dto.SateliteDTO;
import com.lemmingapex.trilateration.NonLinearLeastSquaresSolver;
import com.lemmingapex.trilateration.TrilaterationFunction;
import org.apache.commons.math3.fitting.leastsquares.LeastSquaresOptimizer;
import org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Servicio {
    private SateliteDTO kenobi = new SateliteDTO("kenobi", -500, -200);
    private SateliteDTO skywalker = new SateliteDTO("skywalker", 100, -100);
    private SateliteDTO solo = new SateliteDTO("solo", 500, 100);
    private List<SateliteDTO> satelites = Arrays.asList(kenobi, skywalker, solo);
    private List<SateliteDTO> satelites2 = Arrays.asList(kenobi, skywalker, solo);

    public String obtenerMensaje (List<SateliteDTO> info){
        this.setSatelites(info);
        return this.getMessage(this.satelites);
    }

    public void editarSatelite(String nombre, InfosateliteDTO info){
        int index = 0;
        boolean encontrado = false;
        for (int i = 0; i < satelites2.size(); i++){
            if(satelites2.get(i).getName().equals(nombre)){
                encontrado = true;
                index = i;
            }
        }

        if (encontrado){
            satelites2.get(index).setDistance( info.getDistance() );
            satelites2.get(index).setMessage( info.getMessage() );
        }
    }

    public void setSatelites(List<SateliteDTO> satel) {
        for (int i = 0; i < satel.size(); i++) {
            for (int j = 0; j < this.satelites.size(); j++){
                if( this.satelites.get(j).getName().equals( satel.get(i).getName() ) ){
                    this.satelites.get(j).setDistance( satel.get(i).getDistance() );
                    this.satelites.get(j).setMessage( satel.get(i).getMessage() );
                }
            }
        }
    }

    public String getMessage(List<SateliteDTO> satelites){
        ArrayList<String> mensajeTotal = new ArrayList<String>();
        for (int i = 0; i < satelites.size(); i++) {
            String[] mensajeParcial = satelites.get(i).getMessage();
            for (int j = 0; j < mensajeParcial.length; j++){
                if(mensajeTotal.isEmpty() || j >= mensajeTotal.size()){
                    mensajeTotal.add(j, mensajeParcial[j]);
                }else if (mensajeTotal.get(j) == ""){
                    mensajeTotal.set(j, mensajeParcial[j]);
                }
            }
        }

        boolean mensajeObtenido = true;
        StringBuilder mensaje = new StringBuilder();
        for (int k = 0; k < mensajeTotal.size(); k++){
            if (mensajeTotal.get(k).equals("")){
                mensajeObtenido = false;
            }
            mensaje.append(mensajeTotal.get(k) + " ");
        }

        if (mensajeObtenido) {
            mensaje.setLength(mensaje.length() - 1);
            return mensaje.toString();
        }else{
            return "404";
        }
    }

    public PositionDTO getPosition (List<SateliteDTO> satelites) {
        PositionDTO posicion = new PositionDTO();
        double [][] coordenadas = new double [ satelites.size() ] [ 2 ];
        double [] distancias = new double [satelites.size()];
        for (int i = 0; i < satelites.size(); i++){
            coordenadas[i][0] = satelites.get(i).getPos_x();
            coordenadas[i][1] = satelites.get(i).getPos_y();
            distancias[i] = satelites.get(i).getDistance();
        }

        NonLinearLeastSquaresSolver solver = new NonLinearLeastSquaresSolver(new TrilaterationFunction(coordenadas, distancias), new LevenbergMarquardtOptimizer());
        LeastSquaresOptimizer.Optimum optimum = solver.solve();
        double[] punto = optimum.getPoint().toArray();
        posicion.setX( punto[0] );
        posicion.setY( punto[1] );
        return posicion;
    }

    public boolean validarInfoCompleta(){
        boolean completo = true;
        for (int i = 0; i < satelites2.size(); i++) {
            if ( satelites2.get(i).getMessage() == null ){
                completo = false;
            }
        }
        return completo;
    }

    public String obtenerInformacion(){
        String resp;
        if(validarInfoCompleta()){
            resp = getMessage(satelites2);
        }else{
            resp = "404";
        }

        return resp;
    }

    public List<SateliteDTO> getSatelites2() {
        return satelites2;
    }
    public List<SateliteDTO> getSatelites() {
        return satelites;
    }
}
