/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoFinal.controlador.util;

import com.proyectoFinal.controlador.util.util.FacesUtils;
import com.proyectoFinal.controlador.util.util.JsfUtil;
import com.proyectoFinal.modelo.Usuario;
import javax.inject.Named;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;

/**
 *
 * @author juanmaragra
 */
@Named(value = "loginBean")
@ViewScoped
public class loginBean implements Serializable {
    private Usuario usuario;
    @EJB
    private UsuarioFacade usuarioFacade;

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    /**
     * Creates a new instance of loginBean
     */
    public loginBean() {
    }
    @PostConstruct
    private void inicializar()
    {
       usuario = new Usuario();
    }
    public String ingresar()        
    {
        Usuario usuarioEncontrado=usuarioFacade.obtenerUsuarioxCorreo(usuario.getCorreo());
        if(usuarioEncontrado != null)
        {
            if(usuario.getContrasenia().compareTo(usuarioEncontrado.getContrasenia())==0)
            {
                controladorProyecto contCuatroenlinea= (controladorProyecto) FacesUtils.getManagedBean("controladorProyecto");
                contCuatroenlinea.setUsuario(usuarioEncontrado);
               
                if(usuarioEncontrado.getTipoUsuario().getIdtipoUsuario().equals("2"))
                {
                   return "ingresar";  
                }else {
                    return "jugar";
                }
            }
            JsfUtil.addErrorMessage("Contraseña incorrecta");
        }
        
        else
        {
            JsfUtil.addErrorMessage("El correo ingresado no existe");
        }
        return null;
    }

    
}

    /**
     * Creates a new instance of loginBean
     */
    
    

