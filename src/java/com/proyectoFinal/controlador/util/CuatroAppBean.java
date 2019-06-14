/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoFinal.controlador.util;

import com.proyectoFinal.controlador.util.util.JsfUtil;
import com.proyectoFinal.modelo.Usuario;
import com.proyectoFinal.modelo.grafo.Arista;
import com.proyectoFinal.modelo.grafo.Ficha;
import com.proyectoFinal.modelo.grafo.Grafo;
import com.proyectoFinal.modelo.grafo.Vertice;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import org.primefaces.model.diagram.Connection;
import org.primefaces.model.diagram.DefaultDiagramModel;
import org.primefaces.model.diagram.Element;
import org.primefaces.model.diagram.connector.StraightConnector;
import org.primefaces.model.diagram.endpoint.BlankEndPoint;
import org.primefaces.model.diagram.endpoint.DotEndPoint;
import org.primefaces.model.diagram.endpoint.EndPoint;
import org.primefaces.model.diagram.endpoint.EndPointAnchor;

/**
 *
 * @author juanmaragra
 */
@Named(value = "cuatroAppBean")
@SessionScoped
public class CuatroAppBean implements Serializable {

    private int altoFichas = 6;
    private int ancho = 7;
    private boolean estadoJuego=false;
    private byte distancia = 6;
    private DefaultDiagramModel model;
    private Grafo tablero = new Grafo();
    private List teclas;
    private Date fechaSistema;
    
    

    public CuatroAppBean() {
        teclas=new ArrayList<>();
    }

    @PostConstruct
    private void pintarTablero() {
        model = new DefaultDiagramModel();
        model.setMaxConnections(-1);
        model.setConnectionsDetachable(false);
        
        disparar();

        int x = 18;
        int y = 5;
        String color = "Blanco";
        String styleColor = "ui-diagram-element-grafo-blanco";

        for (int k = 1; k <= 6; k++) {

            for (int i = 1; i <= altoFichas; i++) {
                for (int j = 1; j <= ancho; j++) {
                    tablero.adicionarVertice(new Ficha(color, i, k));
                    Element ceo = new Element(tablero.getVertices().size(), x + "em", y + "em");
                    ceo.setDraggable(false);
                    ceo.setStyleClass(styleColor);
                    ceo.addEndPoint(new BlankEndPoint(EndPointAnchor.CENTER));
                   
                    model.addElement(ceo);
                    x = x + distancia;
                }
                y = y + distancia ;
                x = 18;
            }
        }
        
        llenarAristas();

        StraightConnector connector = new StraightConnector();
        connector.setPaintStyle("{strokeStyle:'#404a4e', lineWidth:3}");
        connector.setHoverPaintStyle("{strokeStyle:'#20282b'}");
        model.setDefaultConnector(connector);
        //recorrer aristas
        for (Arista arista : tablero.getAristas()) {
            Element origen = model.getElements().get(arista.getOrigen() - 1);
            Element destino = model.getElements().get(arista.getDestino() - 1);
           
                    model.connect(new Connection(origen.getEndPoints().get(0), destino.getEndPoints().get(0)));
                    
                    
                    
            

        }
    }
    
    public void llenarAristas() {
         
        for (Vertice vertice : tablero.getVertices()) {
            if (vertice.getId() % ancho !=0 ) {
                
                tablero.adicionarArista(vertice.getId(), vertice.getId() + 1, 2);
            }
            if (vertice.getFicha().getNivel()< (altoFichas)) {
                
                tablero.adicionarArista(vertice.getId(), vertice.getId() + ancho, 1);
                
            }
              if (vertice.getFicha().getNivel()< (altoFichas) && 
                      vertice.getId() < ( ancho*altoFichas*vertice.getFicha().getTablero())+(vertice.getFicha().getNivel()
                 * ancho) && vertice.getId() % ancho !=0 ) {
                
                tablero.adicionarArista(vertice.getId(), vertice.getId() + ancho + 1, 1);
              }
            if (vertice.getFicha().getNivel()< (altoFichas) && 
                    vertice.getId() > ((vertice.getFicha().getNivel() 
                 * ancho) - ancho) + 1) {
                
                tablero.adicionarArista(vertice.getId(), vertice.getId() + ancho - 1, 1);
            }
            
    
        }
    }

//        Element ceo = new Element("CEO", "25em", "6em");
//        ceo.addEndPoint(createEndPoint(EndPointAnchor.BOTTOM));
//        model.addElement(ceo);
//         
//        StraightConnector connector = new StraightConnector();
//        connector.setPaintStyle("{strokeStyle:'#404a4e', lineWidth:3}");
//        connector.setHoverPaintStyle("{strokeStyle:'#20282b'}");
        //connections
        // model.connect(new Connection(ceo.getEndPoints().get(0), cfo.getEndPoints().get(0), connector));        
    

    private EndPoint createEndPoint(EndPointAnchor anchor) {
        DotEndPoint endPoint = new DotEndPoint(anchor);
        endPoint.setStyle("{fillStyle:'#404a4e'}");
        endPoint.setHoverStyle("{fillStyle:'#20282b'}");

        return endPoint;
    }

    public int getAltoFichas() {
        return altoFichas;
    }

    public void setAltoFichas(int altoFichas) {
        this.altoFichas = altoFichas;
    }

    public List getTeclas() {
        return teclas;
    }

    
    public void setTeclas(List teclas) {
        this.teclas = teclas;
    }

    public boolean isEstadoJuego() {
        return estadoJuego;
    }

    public void setEstadoJuego(boolean estadoJuego) {
        this.estadoJuego = estadoJuego;
    }

    public Date getFechaSistema() {
        return new Date();
    }

    public void setFechaSistema(Date fechaSistema) {
        this.fechaSistema = fechaSistema;
    }

  

    public byte getDistancia() {
        return distancia;
    }

    public void setDistancia(byte distancia) {
        this.distancia = distancia;
    }

    public DefaultDiagramModel getModel() {
        return model;
    }

    public void setModel(DefaultDiagramModel model) {
        this.model = model;
    }
    
    public void activarJuego()
    {
        estadoJuego=true;
        JsfUtil.addSuccessMessage("Se ha habilitado el juego");
    }
    
  private void disparar(){
  
      for (int i = 1; i <=ancho; i++) {
          
          teclas.add(i);
      }
  
  }

  public void accion(int numeroColumna, Usuario idColor){
  
      while (numeroColumna+ancho<=tablero.getVertices().size()){
          
          if(model.getElements().get(numeroColumna+ancho-1).getStyleClass().toString().compareTo("ui-diagram-element-grafo-blanco")==0){
          
          
          numeroColumna=numeroColumna+ancho;
          }else{
          
          break;
          }
          
      }
  String nombre = idColor.getNombre();
  model.getElements().get(numeroColumna-1).setStyleClass("ui-diagram-element-grafo-" + idColor.getColor());
  
  }
}
