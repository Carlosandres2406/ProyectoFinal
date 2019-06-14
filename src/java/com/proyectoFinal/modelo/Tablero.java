/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoFinal.modelo;

import java.io.Serializable;

/**
 *
 * @author juanmaragra
 */
public class Tablero implements Serializable{
    private String idTablero;
    private Partida idPartida;
    private Usuario ficha;

    public Tablero(String idTablero, Partida idPartida, Usuario ficha) {
        this.idTablero = idTablero;
        this.idPartida = idPartida;
        this.ficha = ficha;
    }

    public String getIdTablero() {
        return idTablero;
    }

    public void setIdTablero(String idTablero) {
        this.idTablero = idTablero;
    }

    public Partida getIdPartida() {
        return idPartida;
    }

    public void setIdPartida(Partida idPartida) {
        this.idPartida = idPartida;
    }

    public Usuario getFicha() {
        return ficha;
    }

    public void setFicha(Usuario ficha) {
        this.ficha = ficha;
    }

    @Override
    public String toString() {
        return "Tablero{" + "idTablero=" + idTablero + ", idPartida=" + idPartida + ", ficha=" + ficha + '}';
    }
    
    
    
    
}
