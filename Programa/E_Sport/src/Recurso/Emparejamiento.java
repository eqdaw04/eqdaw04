/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Recurso;

import BD.BDConexion;
import Controladora.Main;
import Excepciones.Excepcion;
import UML.Equipo;
import UML.Jornada;
import UML.Partido;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

/**
 * Clase en la que definimos los emparejamientos de los equipos por partido.
 * Fecha de creación de la clase: 25/04/2018
 * @author eqdaw04
 */

/**
 * Metodo para emparejar equipos.
 * Obtenemos el día en el que se jugará el partido y los arrays de equipos locales y visitantes.
 */
public class Emparejamiento {
    ArrayList <Equipo> lEquipo;
    Equipo[][] lJPEquipoL;
    Equipo[][] lJPEquipoV;
    Calendar fecha;
    String dato;
    int horaI, horaF;
    
    public Emparejamiento() {
    }
    
    /**
     * Constructor para emparejar los equipos.
     * @param lEquipo ArrayList de equipo
     */

    public Emparejamiento(ArrayList<Equipo> lEquipo) {
        this.lEquipo = lEquipo;
    }
    
    /**
     * Metodo para calcular el partido.
     * Obtenemos el número de jornadas y partidos.
     * @param fecha
     * @param horaF
     * @throws java.lang.Exception
     */
    
    public void calcularPartido(Calendar fecha, int horaF) throws Exception{
        int e = lEquipo.size();
        this.fecha = fecha;
        horaI = fecha.get(Calendar.HOUR_OF_DAY);
        this.horaF = horaF;
        // Si es impar añadir 1 numero equipo fantasma y crear Array
        if(e%2!=0){
            // si el equipo es impar, se añade un equipo fantasma
            Equipo f = new Equipo();
            f.setNombre("DESCANSO");
            lEquipo.add(f);
            e = lEquipo.size();
        }
        int j = (e-1);
        int p = e/2;
        lJPEquipoL = new Equipo[j][p];
        lJPEquipoV = new Equipo[j][p];
        // rellenar array con los equipos
        llenarArray(j, p, e);
    }
    
    /**
     * Metodo en el que llenamos los arrays de los equipos locales y visitantes.
     * @param j int
     * @param p int
     * @param e int
     * @throws java.lang.Exception
     */
    
    public void llenarArray(int j, int p, int e) throws Exception{
        //Algoritmo de construccion de equipos
        for(int x = 0; x<j; x++){
            if(x%2==0){
                lJPEquipoV[x][0] = lEquipo.get(e-1);
            }
            else{
                lJPEquipoL[x][0] = lEquipo.get(e-1);
            }
        }
        // Completar asignar equipos locales omitiendo array puesto 0, array[x][0 omitido]
        int horizontal;
        horizontal = lJPEquipoL[0].length;
        int c = 0;
        for(int x = 0; x<e-1; x++){
            for(int y = 1; y< horizontal; y++){
                lJPEquipoL[x][y] = lEquipo.get(c);
                if(c == e-2){
                    c = 0;
                }
                else{
                    c ++;
                }
            }
        }
        //completar array visitante
        c = e-2;
        for(int x = 0; x<e-1; x++){
            for(int y = 0; y< horizontal; y++){
                //comprobar si visitante está libre, afirmativo llenar equipo, negativo saltar a rellenar local
                if(lJPEquipoV[x][y] == null){
                    lJPEquipoV[x][y] = lEquipo.get(c);
                }
                else{
                    lJPEquipoL[x][y] = lEquipo.get(c);
                }
                if(c == 0){
                    c = e-2;
                }
                else{
                    c --;
                }
            }
        }
        insertarBBDD(e, horizontal);
    }
    
    /**
     * Metodo para emplearlo en la combinación de equipos en la base de datos.
     * @param e int
     * @param horizontal int
     * @throws Exception 
     */
    
    public void insertarBBDD(int e, int horizontal) throws Exception{
        // guardo los datos en un String para mostrarlo al final, para comprobar que sí que se ha hecho seún algoritmo
        dato = "Los Partidos se quedarán de la siguiente manera:\nJornadas 1-" + (e-1) + " Equipos:\n";
        
        boolean sumar;
        int dia = 0,
            jornada = 1,
            partido = 1;
        // comienza a asignar la primera mitad de la liga
        for(int x = 0; x<e-1; x++){
            // Se abre conexion con la BBDD para insertar una jornada con sus partidos y marcadores
            // para evitar sobrecarga de datos, se ha decidido dividir la inserción de datos por jornada en vez de toda la liga
            BDConexion con = new BDConexion();
            dato += "Jornada " + jornada + " compiten: ";
            // insertar la jornada a la BBDD
            Jornada j = Main.insertarJornada(jornada, fecha, con);
            sumar = true;
            int d = 0;
            for(int y = 0; y< horizontal; y++){
                dato += nombreSemana(fecha.get(Calendar.DAY_OF_WEEK)) + " día " + formatearFecha(fecha) + ", " ;
                dato += lJPEquipoL[x][y].getNombre() + " vs " + lJPEquipoV[x][y].getNombre() + "   ";
                Partido p = new Partido();
                p.setIdPartido(partido);
                cambiarHora();
                p.setFecha(fecha);
                p.setmLocal(0);
                p.setmVisitante(0);
                p.seteLocal(lJPEquipoL[x][y]);
                p.seteVisitante(lJPEquipoV[x][y]);
                
                if(!Main.insertarPartido(p, j, con)){
                    throw new Excepcion(42);
                }
                fecha.add(Calendar.DAY_OF_YEAR, 1);
                d++;
                // si supera la semana antes de acabar la jornada, se volverá al primer día del inicio de la jornada y así sucesivamente
                if(d==7){
                    fecha.add(Calendar.DAY_OF_YEAR, -7);
                    d=0;
                    sumar=false;
                }
                if(sumar){
                    dia++;
                } 
                
                partido++;
            }
            fecha.add(Calendar.DAY_OF_YEAR, 7-d);
            Calendar ff = Calendar.getInstance();
            ff.setTime(fecha.getTime());
            ff.add(Calendar.DAY_OF_YEAR, -1);
            j.setFechaFinal(ff.getTime());
            Main.modificarJornada(j, con);
            dato += "\n";
            dia = dia + 7 - d;
            jornada++;
            con.desconectar();
        }    
        
        // comienza a asignar la segunda mitad de la liga
        dato += "\nJornadas " + e + "-" + ((e-1)*2) + " Equipos:\n";
        for(int x = 0; x<e-1; x++){
            
            // Se abre conexion con la BBDD para insertar una jornada con sus partidos y marcadores
            // para evitar sobrecarga de datos, se ha decidido dividir la inserción de datos por jornada en vez de toda la liga
            BDConexion con = new BDConexion();
            dato += "Jornada " + jornada + " compiten: ";
            // insertar la jornada a la BBDD
            Jornada j = Main.insertarJornada(jornada, fecha, con);
            sumar = true;
            int d = 0;
            for(int y = 0; y< horizontal; y++){
                dato += nombreSemana(fecha.get(Calendar.DAY_OF_WEEK)) + " día " + formatearFecha(fecha) + ", " ;
                dato += lJPEquipoV[x][y].getNombre() + " vs " + lJPEquipoL[x][y].getNombre() + "   ";
                Partido p = new Partido();
                p.setIdPartido(partido);
                cambiarHora();
                p.setFecha(fecha);
                p.setmLocal(0);
                p.setmVisitante(0);
                p.seteLocal(lJPEquipoV[x][y]);
                p.seteVisitante(lJPEquipoL[x][y]);
                
                if(!Main.insertarPartido(p, j, con)){
                    throw new Excepcion(42);
                }
                fecha.add(Calendar.DAY_OF_YEAR, 1);
                d++;
                if(d==7){
                    fecha.add(Calendar.DAY_OF_YEAR, -7);
                    d=0;
                    sumar=false;
                }
                if(sumar){
                    dia++;
                } 
                
                partido++;
            }
            fecha.add(Calendar.DAY_OF_YEAR, 7-d);
            Calendar ff = Calendar.getInstance();
            ff.setTime(fecha.getTime());
            ff.add(Calendar.DAY_OF_YEAR, -1);
            j.setFechaFinal(ff.getTime());
            Main.modificarJornada(j, con);
            dato += "\n";
            dia = dia + 7 - d;
            
            jornada++;
            con.desconectar();
        }        
    }
    
    /**
     * Metodo para cambiar la hora.
     */
    
    private void cambiarHora(){
        if(horaI != horaF){
            Random aleatorio = new Random();
            int ale, 
                rango = horaF - horaI;
            ale = horaI + 1+aleatorio.nextInt(rango);
            fecha.set(Calendar.HOUR_OF_DAY, ale);
        }
        
    }
    
    /**
     * Metodo para formatear la fecha.
     * @param fecha Calendar
     * @return 
     */
    
    private String formatearFecha(Calendar fecha){
        SimpleDateFormat f = new SimpleDateFormat("dd-MM-yyyy");
        String valor;
        valor = f.format(fecha.getTime());
        return valor;
    }
    
    /**
     * Metodo que guarda los nombres de los días de la semana.
     * @param n int
     * @return 
     */
    
    private String nombreSemana(int n){
        String valor; 
        valor = "";
        switch(n){
            case 1:
                valor = "Domingo";
                break;
                
            case 2:
                valor = "Lunes";
                break;
                
            case 3:
                valor = "Martes";
                break;
                
            case 4:
                valor = "Miércoles";
                break;
                      
            case 5:
                valor = "Jueves";
                break;
                
            case 6:
                valor = "Viernes";
                break;
                
            case 7:
                valor = "Sábado";
                break;
                        
        }
        return valor;
    }

    public Equipo[][] getlJPEquipoL() {
        return lJPEquipoL;
    }

    public void setlJPEquipoL(Equipo[][] lJPEquipoL) {
        this.lJPEquipoL = lJPEquipoL;
    }

    public Equipo[][] getlJPEquipoV() {
        return lJPEquipoV;
    }

    public void setlJPEquipoV(Equipo[][] lJPEquipoV) {
        this.lJPEquipoV = lJPEquipoV;
    }

    public String getDato() {
        return dato;
    }

    public void setDato(String dato) {
        this.dato = dato;
    }
    
    
}
