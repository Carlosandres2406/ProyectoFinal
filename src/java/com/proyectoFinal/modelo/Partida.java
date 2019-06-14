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
public class Partida implements Serializable{
    private String idPartida;
    private int numeroJugadores=4;
    private Tablero idTablero;
    private Double tiempo_jugador1;
    private Double tiempo_jugador2;

    public Partida(String idPartida, Tablero idTablero, Double tiempo_jugador1, Double tiempo_jugador2) {
        this.idPartida = idPartida;
        this.idTablero = idTablero;
        this.tiempo_jugador1 = tiempo_jugador1;
        this.tiempo_jugador2 = tiempo_jugador2;
    }

    public String getIdPartida() {
        return idPartida;
    }

    public void setIdPartida(String idPartida) {
        this.idPartida = idPartida;
    }

    public int getNumeroJugadores() {
        return numeroJugadores;
    }

    public void setNumeroJugadores(int numeroJugadores) {
        this.numeroJugadores = numeroJugadores;
    }

    public Tablero getIdTablero() {
        return idTablero;
    }

    public void setIdTablero(Tablero idTablero) {
        this.idTablero = idTablero;
    }

    public Double getTiempo_jugador1() {
        return tiempo_jugador1;
    }

    public void setTiempo_jugador1(Double tiempo_jugador1) {
        this.tiempo_jugador1 = tiempo_jugador1;
    }

    public Double getTiempo_jugador2() {
        return tiempo_jugador2;
    }

    public void setTiempo_jugador2(Double tiempo_jugador2) {
        this.tiempo_jugador2 = tiempo_jugador2;
    }

    @Override
    public String toString() {
        return "Partida{" + "idPartida=" + idPartida + ", numeroJugadores=" + numeroJugadores + ", idTablero=" + idTablero + ", tiempo_jugador1=" + tiempo_jugador1 + ", tiempo_jugador2=" + tiempo_jugador2 + '}';
    }
    
    
    
}

