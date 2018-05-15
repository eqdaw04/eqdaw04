package UML;

import java.util.Calendar;
import java.util.Date;

/**
 * Clase en la que definiremos los datos personales, la fecha de alta y el usuario, contraseña y email de la persona.
 * Fecha de la creación de la clase: 23/04/2018
 * @author eqdaw04
 */

public class Persona {
    private int idPersona;
    private String nombre, apellido1, apellido2;
    private Calendar fechaAlta;
    private String usuario, contrasenna, email;
    
    private Perfil perfil;

    public Persona() {
    }

    public Persona(String nombre, String apellido1, String apellido2, Calendar fechaAlta, String usuario, String contrasenna, String email) {
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.fechaAlta = fechaAlta;
        this.usuario = usuario;
        this.contrasenna = contrasenna;
        this.email = email;
    }

    
    public int getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(int idPersona) {
        this.idPersona = idPersona;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }
 
    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public Calendar getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(Calendar fechaAlta) {
        this.fechaAlta = fechaAlta;
    }


    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasenna() {
        return contrasenna;
    }

    public void setContrasenna(String contrasenna) {
        this.contrasenna = contrasenna;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }
}
