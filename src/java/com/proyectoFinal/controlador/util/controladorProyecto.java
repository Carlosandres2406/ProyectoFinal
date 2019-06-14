/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoFinal.controlador.util;

import com.proyectoFinal.controlador.util.util.JsfUtil;
import com.proyectoFinal.modelo.Usuario;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;

/**
 *
 * @author juanmaragra
 */
@Named(value = "controladorProyecto")
@SessionScoped
public class controladorProyecto implements Serializable {

    /**
     * Creates a new instance of controladorProyecto
     */
    public controladorProyecto() {
    }
    private Usuario usuario;
    @EJB
    private UsuarioFacade usuarioFacade;
    

    /**
     * Creates a new instance of ControladorJuegoCuatroenlinea
     */
    
    @PostConstruct
    private void inicializar()
    {
       usuario = new Usuario();
    }
    public String ingresar()        
    {
        Usuario usuarioEncontrado=usuarioFacade.find(usuario.getCorreo());
        
        if(usuarioEncontrado != null)
        {
            if(usuario.getContrasenia().compareTo(usuarioEncontrado.getContrasenia())==0)
            {
                return "ingresar";
            }
            JsfUtil.addErrorMessage("Contraseña incorrecta");
        }
        else
        {
            JsfUtil.addErrorMessage("El correo ingresado no existe");
        }
        return null;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
            
}

