/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladora;

import Recurso.Emparejamiento;
import UML.*;
import BD.*;
import Excepciones.Excepcion;
import Views.*;
import Parsers.*;
import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.UIManager;

/**
 * Clase controladora.
 * Fecha de creación de la clase: 24/04/2018
 * @author eqdaw04
 */

public class Main {

    private static BDEquipo bdEquipo;
    private static BDJornada bdJornada;
    private static BDJugador bdJugador;
    private static BDPartido bdPartido;
    private static BDPerfil bdPerfil;
    private static BDPersona bdPersona;
    private static Persona persona;
    private static Equipo equipo;
    private static Jugador jugador;
    private static DOM_JornadaEnCurso domResultadosUltimaJornada;
    private static SAX_JornadaEnCurso saxJornadaEnCurso;
    //No se precisa objetos de jornada ni partido, ya que casi no se usa
    //El objeto perfil no es necesario, ya que sólo precisamos el nivel de la persona
    //Con estas omisiones de objetos, se ahorrá recursos del sistema 
    private static int perfil;
    private static float salarioMin, salarioMax;
    private static VLogin login;
    
    /**
     *
     * @param args nada...
     * @throws Exception lanza excepcion
     */
    public static void main(String[] args) throws Exception {

        //construccion de la ruta completa.
        aplicarEstilo();
        inicializarValores();       
        login = new VLogin(0);
    }
    
    /**
     * Metodo para aplicar el estilo de la interfaz gráfica
     * @throws Exception  lanza excepcion
     */
    
    public static void aplicarEstilo() throws Exception {
        
        // http://codejavu.blogspot.com.es/2014/05/ejemplo-look-and-feel-en-java.html
        
        JFrame.setDefaultLookAndFeelDecorated(true);
        JDialog.setDefaultLookAndFeelDecorated(true);
        
        // Nimbus
        UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        
        // Metal
        // UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        
        // Windows
        // UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        
        // Motif
        // UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
        
    }
    
    /**
     * Metodo para crear objetos de la base de datos.
     * @throws Exception lanza excepcion
     */
    
    public static void inicializarValores() throws Exception{
        bdEquipo = new BDEquipo();
        bdJornada = new BDJornada();
        bdJugador = new BDJugador();
        bdPartido = new BDPartido();
        bdPerfil = new BDPerfil();
        bdPersona = new BDPersona();
        domResultadosUltimaJornada = new DOM_JornadaEnCurso();
        saxJornadaEnCurso = new SAX_JornadaEnCurso();
        salarioMin = 735.90f;
        salarioMax = 196320.00f;
    }
    
    /**
     * Metodo para introducir los datos del usuario y servidor.
     * @param usuario String
     * @param contrasenna char
     * @throws Exception lanza excepcion
     */
    
    //---------- JON XU JIN ----------

    public static void accederPrincipal(String usuario, char[] contrasenna) throws Exception{
        
        persona = null;
        
        persona = bdPersona.buscarPersonaPorUsuario(usuario);

        int cont = login.getCont()+1;
        login.setCont(cont);
        if(persona == null){
            throw new Excepcion(14);
        }
        else if(Arrays.equals(persona.getContrasenna().toCharArray(), contrasenna)){
            perfil = persona.getPerfil().getIdPerfil();
            login.dispose();
            if(perfil == 3){
                new VUPrincipal(persona.getUsuario());
            }
            else{
                new VPrincipal(perfil,persona.getUsuario());
            }
            
        }
        else if(cont <3){
            throw new Excepcion(12);
        }else if(cont == 3){
            login.setError(13);
            throw new Excepcion(13);
        }
    }
        /**
         * Metodo para seleccionar la ventana que se desea abrir.
         * @param n int
         * @param tipo String
         * @throws Exception lanza excepcion
         */
    //---------- JON XU JIN ----------
    
    public static void abrirVentana(int n, String tipo) throws Exception{
        switch(n){
            case 1:
                new VJugador(tipo, n);
                break;
                
            case 2:
                new VEquipo(tipo, n);
                break;
                
            case 3:
                new VPersona(tipo, n);
                break;

            case 5:
                new VModificarEquipo(tipo);
                break;
                
            case 6:
                new VIntroducirResultado(n);
                break;     
                
            case 7:
                new VGenerarLiga(n);
                break;
                
            case 8:
                new VCalendario(tipo);
                break; 
                
            case 9:
                new VDResultados(tipo);
                break;
        }
    }

    /**
     *
     * @return boolean
     * @throws Exception lanza excepcion
     */
    public static boolean borrarLiga() throws Exception{
        return bdJornada.borrarTodo();
    }
    /**
     * Metodo para cerrar la ventana abierta
     * @param v JDialog
     */
    
    //---------- JON XU JIN ----------
    
    public static void cerrar(JDialog v) {
        // Cierra la ventana abierta
        v.dispose();
    }
    
    /**
     * Metodo para cerrar la ventana abierta y volver a abrir según la ventana cerrada.
     * @param v JDialog
     * @param tipo String
     * @param n int
     * @throws Exception  lanza excepcion
     */
    
    //---------- JON XU JIN ----------
    
    public static void reabrir(JDialog v, String tipo, int n) throws Exception {
        v.dispose();
        switch(n){
            case 1:
                new VJugador(tipo, n);
                break;
                
            case 2:
                new VEquipo(tipo, n);
                break;
                
            case 3:
                new VPersona(tipo, n);
                break;
                
            case 5:
                new VModificarEquipo(tipo);
                break;
                
            case 6:
                new VIntroducirResultado(n);
                break;
                
            case 7:
                new VGenerarLiga(n);
                break;
                
            case 8:
                new VCalendario(tipo);
                break;
                
            case 9:
                new VDResultados(tipo);
                break;
            
        }
    }
    
    /**
     *
     * @param v jframe
     * @param tipo tipo alta baja lista..
     * @param estado boolean
     * @throws Exception lanza excepcion
     */
    public static void reabrirFrame(JFrame v, String tipo, boolean estado) throws Exception {
        v.dispose();
        if(estado){
            new VUPrincipal(tipo);
        }
        else{
            new VUPrincipal(tipo, estado);
        }
        
    }
    
    /**
     * Metodo para abrir ventana jugador como consulta única.
     * @param j Jugador
     */
      //-------Mikel
 
    public static void abrirVJugador (Jugador j){
 
        new VJugador("consulta",1,j);
 
    }
    
    /**
     * Metodo para salir del programa
     * @param v  JFrame
     */

    
    public static void salir(JFrame v){
        //salir del programa
        v.dispose();
    }
    
    /**
     *
     * @param v jframe
     */
    public static void salirDelPrograma(JFrame v){
        v.dispose();
        System.exit(0);
    }
    /**
     * Metodo para cerrar sesión y volver al login
     * @param v JFrame
     */
    
    //---------- JON XU JIN ----------
    
    public static void cerrarSesion(JFrame v){
        v.dispose();
        login = new VLogin(1);
    }
    
    /**
     * Metodo que devuelve una lista de todos los perfiles insertados en la base de datos.
     * @return lista de perfiles
     * @throws Exception  lanza excepcion
     */
    
    //---------- JON XU JIN ----------
    
    public static ArrayList <Perfil> consultarTodosLosPerfiles() throws Exception{
        return bdPerfil.buscarPerfiles();
    }
    
    /**
     * Metodo para consultar un perfil de la base de datos.
     * @param cod int
     * @return un perfil
     * @throws Exception  lanza excepcion
     */
    
    //---------- JON XU JIN ----------
    
    public static Perfil consultarPerfil(int cod) throws Exception{
        return bdPerfil.buscarPorCodigo(cod);
    }
    
    /**
     * Metodo que inserta un usuario en la base de datos.
     * @param usuario String
     * @param contrasenna String
     * @param nombre String
     * @param ape1 String
     * @param ape2 String
     * @param email String
     * @param fecha Date
     * @param perfil String
     * @throws Exception  lanza excepcion
     */
    
    //---------- JON XU JIN ----------
    
    public static void altaPersona( String usuario, String contrasenna, String nombre, String ape1, String ape2, String email, Calendar fecha, String perfil) throws Exception{
        persona = new Persona(nombre, ape1, ape2, fecha, usuario, contrasenna, email);
        persona.setPerfil(bdPerfil.buscarPorNombre(perfil));
        bdPersona.altaPersona(persona);
    }
    
    /**
     * Metodo para eliminar un usuario de la base de datos.
     * @param usuario String
     * @throws Exception  lanza excepcion
     */
    
    //---------- JON XU JIN ----------

    public static void bajaPersona(String usuario) throws Exception {
        bdPersona.bajaPersona(usuario);
    }
    
    /**
     * Metodo para modificar una persona de la base de datos.
     * @param id integer de id
     * @param usuario String
     * @param contrasenna String
     * @param nombre String
     * @param ape1 String
     * @param ape2 String
     * @param email String
     * @param perfil String
     * @param fecha fecha 
     * @throws Exception  lanza excepcion
     */
    
    //---------- JON XU JIN ----------
    
    public static void modificarPersona(int id, String usuario, String contrasenna, String nombre, String ape1, String ape2, String email, String perfil, Calendar fecha) throws Exception {
        // en ved de modificar cada atributo, crear uno nuevo con su constructor se ahorra código

        persona = new Persona(nombre, ape1, ape2, fecha, usuario, contrasenna, email);
        persona.setIdPersona(id);
        persona.setPerfil(bdPerfil.buscarPorNombre(perfil));
        bdPersona.modificarPersona(persona);
    }
    
    /**
     * Metodo para localizar a una persona por su usuario.
     * @param usuario String
     * @return una persona ya insertada en la base de datos
     * @throws Exception  lanza excepcion
     */
    
    //---------- JON XU JIN ----------
    
    public static Persona consultarPersona(String usuario) throws Exception{
        persona = bdPersona.buscarPersonaPorUsuario(usuario);
        return persona;
    }
    
    /**
     * Metodo que devuelve todas las personas guardadas en la base de datos.
     * @return lista de personas
     * @throws Exception  lanza excepcion
     */
    
    //---------- JON XU JIN ----------
    
    public static ArrayList <Persona> consultarTodasLasPersonas() throws Exception{
        return bdPersona.buscarTodasLasPersona();
    }
    
    /**
     * Metodo que devuelve todos los partidos 
     * @param fecha fecha
     * @return fecha
     * @throws Exception  lanza excepcion
     */
    
    //---------- JON XU JIN ----------
    
    public static ArrayList <Partido> consultarLosPartidosPorFecha(Calendar fecha) throws Exception{
        return bdPartido.consultarPartidoPorFecha(fecha);
    }
    
    /**
     * Metodo para consultar los partidos de la jornada.
     * @param n int
     * @return una lista de partidos
     * @throws Exception  lanza excepcion
     */
    
    //---------- JON XU JIN ----------
    
    public static ArrayList <Partido> consultarPartidosPorJornada(int n) throws Exception{
        return bdPartido.consultarPartidosPorJornada(n);
    }
    
    /**
     * Metodo para consultar el marcador por partido. 
     * @param p Partido
     * @return objeto partido
     * @throws Exception  lanza excepcion
     */
    
    //---------- JON XU JIN ----------
    
    public static Partido consultarMarcadorPorPartido(Partido p) throws Exception{
        return bdPartido.consultarMarcadores(p);
    }
    
    /**
     * Metodo para consultar todas las jornadas.
     * @return lista de jornadas
     * @throws Exception  lanza excepcion
     */
    
    
    //---------- JON XU JIN ----------
    
    public static ArrayList<Jornada> consultarTodasLasJornadas() throws Exception{
        return bdJornada.consultarTodasLasJornadas();
    }
    
    /**
     * Metodo para generar calendario de la liga.
     * @param fecha Calendar
     * @param horaF int
     * @return el dato obtenido de un objeto emparejamiento
     * @throws Exception  lanza excepcion
     */
    
    //---------- JON XU JIN ----------
    
    public static String generarCalendario(Calendar fecha, int horaF) throws Exception{
        // extraer de la bbdd los equipos disponibles e instanciar el algoritmo de emparejamiento
        Emparejamiento emp = new Emparejamiento(bdEquipo.BuscarEquipo6());
        // ejecutar el algoritmo para los equipos aleatorios
        // Abrir conexion y mantenerlo abierto hasta que acabe que introducir las partidas para no tener que abrir y cerrar constantemente hasta introducir los X partidos
        emp.calcularPartido(fecha, horaF);
        return emp.getDato();
    }
    
    /**
     * Metodo para insertar una jornada.
     * @param nJornada int
     * @param fecha Calendar
     * @param con BDConexion
     * @return objeto jornada
     * @throws Exception  lanza excepcion
     */
    
    //---------- JON XU JIN ----------
    
    public static Jornada insertarJornada(int nJornada, Calendar fecha, BDConexion con) throws Exception{
        return bdJornada.insertarJornada(nJornada, fecha, con);
    }
    
    /**
     * Metodo para modificar una jornada.
     * @param jornada Jornada
     * @param con BDConexion
     * @throws Exception  lanza excepcion
     */
    
    //---------- JON XU JIN ----------
    
    public static void modificarJornada(Jornada jornada, BDConexion con) throws Exception{
        // modificar la fecha final de la jornada        
        bdJornada.modificarJornada(jornada, con);
        
    }
    
    /**
     * Metodo para insertar un partido.
     * @param partido Partido
     * @param jornada Jornada
     * @param con BDConexion
     * @return el estado "correcto" si se ha insertado sin problemas
     * @throws Exception  lanza excepcion
     */
    
    //---------- JON XU JIN ----------
    
    public static boolean insertarPartido(Partido partido, Jornada jornada, BDConexion con) throws Exception{
        return bdPartido.insertarPartido(partido, jornada, con);
    }
    
    /**
     *
     * @return Jornada
     * @throws Exception lanza excepcion
     */
    public static Jornada consultarInicioJornada() throws Exception{
        return bdJornada.consultarInicio();
    }
    
    /**
     * Metodo para modificar un partido.
     * @param partido Partido
     * @return el estado "correcto" si se ha insertado sin problemas
     * @throws Exception  lanza excepcion
     */
  
    //---------- JON XU JIN ----------
    
    public static boolean modificarPartido(Partido partido) throws Exception{
        return bdPartido.modificarPartido(partido);
    }
    
    /**
     * Metodo para consultar un equipo por su id.
     * @param n int
     * @return objeto equipo
     * @throws Exception  lanza excepcion
     */
    
    //---------- JON XU JIN ----------
    
    public static Equipo consultarEquipoPorNumero(int n) throws Exception{
        equipo = bdEquipo.consultarEquipoPorNumero(n);
        return equipo;
    }
    
    //---------- JON XU JIN ----------

    /**
     *
     * @param p partido
     * @return boolean
     * @throws Exception lanza excepcion
     */
    
    public static boolean modificarMarcador(Partido p) throws Exception{
        return bdPartido.modificarMarcador(p);
    }
    
    /**
     * Metodo que valida los datos correctos por su patrón introducido.
     * @param cod int
     * @param campo campo de texto
     * @throws Exception  lanza excepcion
     */
    
    //----------JON XU JIN ----------
    public static void validar(int cod, JTextField campo) throws Exception{        
        Pattern p=Pattern.compile(datoPatron(cod));
        Matcher m=p.matcher(campo.getText());
        if(!m.matches())
        {
            campo.setBackground(Color.red);
            campo.grabFocus();
            throw new Excepcion(cod);
        }
        else{
            campo.setBackground(Color.white);
        }
    }
    
    /**
     * Metodo que devuelve los resultados de los equipos.
     * @return resultado
     * @throws Exception  lanza excepcion
     */
    
    
    public static ArrayList<Object> resultados() throws Exception{
        return bdEquipo.resultadoFinal();
    }
    
    /**
     *
     * @return array object
     * @throws Exception lanza excepcion
     */
    public static ArrayList<Object> resultadosTodosLosPartidos() throws Exception{
        return bdEquipo.resultadoTodasLasJornadas();
    }
    
    /**
     * Metodo que devuelve los resultados de la última jornada jugada, guardando los partidos, equipos y puntuación del partido.
     * @return una lista de resultados
     * @throws Exception  lanza excepcion
     */
    
    public static ArrayList<Object> resultadosUltimaJornada() throws Exception{
        ArrayList <Object> lista;
                
        lista = bdEquipo.resultadoUltimaJornada();
        if(lista.isEmpty()){
            lista = bdEquipo.resultadoUltimaJornadaDeLaLiga();
        }
        if(lista.size() > 1){
            ArrayList <Object> listaFinal = new ArrayList();
            String p2 = "";
            // partido, equipo, puntos, visitante
            for(int x = 0; x<lista.size(); x++){
                String p = "",
                       el = "",
                       ev = "",
                       pl = "",
                       pv = "";
                Object[] partido = (Object[]) lista.get(x);
                p = partido[0].toString();
           
                if(!p2.equals(p)){
                    for(int y = 0; y<lista.size(); y++){
                    Object[] equipo = (Object[]) lista.get(y);
                    if(p.equals(equipo[0])){
                        // local
                        if(equipo[3].equals("1")){
                            el = equipo[1].toString();
                            pl = equipo[2].toString();
                        }
                        // visitante
                        else{
                            ev = equipo[1].toString();
                            pv = equipo[2].toString();
                            }
                        }
                    }
                    if(el.equals("")){
                        String fila[] = {p, ev + "   Se encuentra en Descando", pv};
                        listaFinal.add(fila);
                    }
                    else if(ev.equals("")){
                        String fila[] = {p, el + "   Se encuentra en Descando", pl};
                        listaFinal.add(fila);
                    }
                    else{
                        String fila[] = {p, el + "   VS   " + ev, pl + "   |   " + pv};
                        listaFinal.add(fila);
                    }
                }
                
                
                p2 = p;
            }
            lista = listaFinal;
        }
        else{
            throw new Excepcion(38);
        }
        
        return lista;
    }
    
    /**
     * Metodo que guarda los patterns que utilizaremos en el programa.
     * @param cod int
     * @return devuelve el patrón a utilizar.
     */
    
    public static String datoPatron(int cod){
        String dato = "";
        switch(cod){

            case 46: // marcador
                dato = "^[0-9]{1,}$";
                break;
                
            case 3: // NIF
                dato = "^[A-Z0-9][0-9]{7}[A-Z]$";
                break;
                
            case 4: // Nombre
                dato = "^[A-ZÑ 0-9][a-zñ 0-9]{2,}$";
                break;
                
            case 5: // Apellidos
                dato = "^[A-ZÑ ][a-zñ ]{2,}$";
                break;
                
            case 6: // e-mail
                dato = "^[_a-z0-9-]+(.[_a-z0-9-]+)*@[a-z0-9-]+(.[a-z0-9-]+)*(.[a-z]{2,})$";
                break;
                
            case 7: // Usuario
                dato = "^[A-ZÑña-z0-9]{3,}$";
                break;
                
            case 8: // Contraseña
                dato = "^[A-ZñÑa-z0-9]{3,}$";
                break;
                
            case 29: // lugar
                dato = "^[A-Z][a-z ]{3,}([ --][A-Z][a-z]{2,})*$";
                break;
        }
        return dato;
    }
    
    /**
     * Metodo para obtener un perfil.
     * @return perfil
     */

    public static int getPerfil() {
        return perfil;
    }

    /**
     * Metodo para establecer el perfil.
     * @param perfil  int
     */
    
    public static void setPerfil(int perfil) {
        Main.perfil = perfil;
    }
    
    /**
     * Metodo para obtener el login.
     * @return objeto login
     */

    public static VLogin getLogin() {
        return login;
    }
    
    /**
     *  Metodo para establecer el login.
     * @param login VLogin
     */

    public static void setLogin(VLogin login) {
        Main.login = login;
    }
 
      
    /**
     * Metodo para obtener una persona.
     * @return objeto persona
     */

    public static Persona getPersona() {
        return persona;
    }
    
    /**
     * Metodo para establecer una persona.
     * @param persona Persona
     */

    public static void setPersona(Persona persona) {
        Main.persona = persona;
    }

    /**
     *
     * @return float
     */
    public static float getSalarioMin() {
        return salarioMin;
    }

    /**
     *
     * @return float
     */
    public static float getSalarioMax() {
        return salarioMax;
    }

    // se mantiene los set de salario max y min, por si un futuro se plantea integrar los salarios en la bbdd
    // aunque actualmente no tiene uso alguna

    /**
     *
     * @param salarioMax float
     */
    public static void setSalarioMax(float salarioMax) {
        Main.salarioMax = salarioMax;
    }

    /**
     *
     * @param salarioMin float
     */
    public static void setSalarioMin(float salarioMin) {
        Main.salarioMin = salarioMin;
    }
    
    /**
     * Metodo para dar de alta a un jugador en la base de datos.
     * @param dni String
     * @param nombre String
     * @param apellido1 String
     * @param apellido2 String
     * @param nickname String
     * @param sueldo String
     * @param fechaAlta Date
     * @param comentario String
     * @throws Exception  lanza excepcion
     */
    
    // Imanol Luis
    public static void altaJugador(String dni, String nombre, String apellido1, String apellido2, String nickname, float sueldo, Date fechaAlta, String comentario) throws Exception {
        jugador = new Jugador(dni, nombre, apellido1, apellido2, nickname, sueldo, fechaAlta, comentario);
        bdJugador.insertarJugador(jugador);
    }
    
    /**
     * Metodo para dar de baja a un jugador de la base de datos.
     * @throws Exception  lanza excepcion
     */

    // Imanol Luis
    public static void bajaJugador() throws Exception {
        bdJugador.eliminarJugador(jugador);
    }
    
    /**
     * Metodo para modificar a un jugador de la base de datos.
     * @param id integer
     * @param dni String
     * @param nombre String
     * @param apellido1 String
     * @param apellido2 String
     * @param nickname String
     * @param fecha date
     * @param sueldo String
     * @param comentario String
     * @throws Exception  lanza excepcion
     */
    
    // Imanol Luis
    public static void modificarJugador(int id , String dni, String nombre, String apellido1, String apellido2, String nickname, float sueldo,Date fecha, String comentario) throws Exception {
        jugador = new Jugador(dni, nombre, apellido1, apellido2, nickname, sueldo, fecha, comentario);
        jugador.setIdJugador(id);
        bdJugador.modificarJugador(jugador);
    }
    
    /**
     * Metodo para buscar a un jugador a traves del DNI.
     * @param dni String
     * @return objeto jugador
     * @throws Exception  lanza excepcion
     */
    
    // Imanol Luis   
    public static Jugador buscarJugador(String dni) throws Exception {  
        jugador = bdJugador.BuscarJugador(dni);
       return jugador;
    }
    
    /**
     * Metodo para buscar todos los jugadores de la base de datos.
     * @return devuelve los jugadores introducidos en la clase BDJugador
     * @throws Exception  lanza excepcion
     */
    
    // Imanol Luis   
    public static ArrayList<Jugador> buscarJugador() throws Exception {        
       return bdJugador.BuscarJugador();
    }
    
    /**
     * Metodo para buscar jugadores pertenecientes a un equipo.
     * @param id String
     * @return devuelve los ids introducidos en la clase BDJugador
     * @throws Exception  lanza excepcion
     */
    
    //------------Mikel
    public static ArrayList<Jugador> obtenerJugEqui(String id) throws Exception{
        return bdJugador.BuscarEqui(id);
    }
    
    /**
     * Metodo para consultar el equipo de un dueño mediante su usuario
     * @param usu String
     * @return devuelve el equipo que dirige el dueño
     * @throws Exception  lanza excepcion
     */

    //------------Mikel
    public static Equipo ConsultarEquipoPorUsuario(String usu) throws Exception{
        equipo = bdEquipo.BuscarEquipoPorUsuario(usu);
        return equipo;
    }
    
    /**
     * Metodo que consulta los jugadores que no pertenecen a ningún equipo.
     * @return devuelve jugadores sin equipo
     * @throws Exception  lanza excepcion
     */
    
    //------------Mikel
    public static ArrayList <Jugador> consultarJugadoresDisponibles () throws Exception{
        return bdJugador.BuscarJugadoresDisponibles();
    }
    
    /**
     * Metodo para eliminar de un equipo a un jugador.
     * @param nickname String
     * @return devuelve el nickname del jugador y al eliminarlo pone el id del equipo al que pertenecía a null
     * @throws java.lang.Exception lanza excepcion
     */
    
    //------------Mikel
    public static boolean EliminarJugadorEquipo (String nickname) throws Exception{
       
        return bdJugador.QuitarJugadorEquipo(nickname);
    }
    
    /**
     * Metodo para añadir un jugador a un equipo.
     * @param nickname String
     * @param id String
     * @return devuelve el nickname del jugador y le añade el id del equipo al que se le va a añadir.
     * @throws Exception  lanza excepcion
     */
    
    //------------Mikel
    public static boolean AnnadirJugadorEquipo(String nickname, String id) throws Exception {
        return bdJugador.PonerJugadorEquipo(nickname,id);
    }
    //------------Mikel
 
    // Busca a un jugador por su nickname yle añade el id_equipo del equipo al que se le quiere añadir

    /**
     *
     * @param nickname string
     * @return jugador
     * @throws Exception lanza excepcion
     */
 
    public static Jugador consultarJugadorNickname(String nickname) throws Exception {
 
        return bdJugador.buscarJugadorNickname(nickname);
 
    }
 
    /**
     * Metodo para dar de alta un equipo en la base de datos.
     * @param nombre String
     * @param lugar string
     * @param usuario String
     * @param fechaCreacion Date
     * @param comentario String
     * @throws Exception  lanza excepcion
     */
    
    // Imanol Luis
    public static void altaEquipo(String nombre, String lugar, String usuario, Date fechaCreacion, String comentario) throws Exception {
        equipo = new Equipo();
        equipo.setNombre(nombre);     
        equipo.setLugar(lugar);
        equipo.setPersona(bdPersona.buscarPersonaPorUsuario(usuario));
        equipo.setFechaCreacion(fechaCreacion);
        equipo.setComentario(comentario);
        bdEquipo.insertarEquipo(equipo);
    }
    
    /**
     * Metodo para dar de baja un equipo de la base de datos.
     * @throws Exception  lanza excepcion
     */

    // Imanol Luis
    public static void bajaEquipo() throws Exception {
        bdEquipo.eliminarEquipo(equipo);
    }
    
    /**
     * Metodo para modificar un equipo de la base de datos.
     * @param nombre String
     * @param lugar string
     * @param fecha date
     * @param comentario String
     * @throws Exception  lanza excepcion
     */

    // Imanol Luis
    public static void modificarEquipo(String nombre, String lugar, Date fecha, String comentario) throws Exception {
        equipo.setNombre(nombre);
        equipo.setLugar(lugar);
        equipo.setFechaCreacion(fecha);
        equipo.setComentario(comentario);
        bdEquipo.modificarEquipo(equipo);
    }
    
    /**
     * Metodo para buscar un equipo de la base de datos a traves del nombre
     * @param nombre String
     * @return objeto equipo
     * @throws Exception  lanza excepcion
     */

    // Imanol Luis
    public static Equipo buscarEquipo(String nombre) throws Exception {
        equipo = bdEquipo.BuscarEquipo(nombre);
       return equipo;
    }
    
    /**
     * Metodo para buscar todos los equipos de la base de datos
     * @return todos los equipos de la base de datos
     * @throws Exception  lanza excepcion
     */

    // Imanol Luis
    public static ArrayList<Equipo> buscarEquipo() throws Exception {
        return bdEquipo.BuscarEquipo();
    }
    
    /**
     * Metodo para obtener todos los datos de todos los jugadores de un equipo
     * @param equipo Equipo
     * @return String
     * @throws Exception  lanza excepcion
     */

    public static String buscarPlantilla(Equipo equipo) throws Exception {
        String plantilla="";
        String dni, nombre, apellido1, apellido2, nickname;
        
        ArrayList<Jugador> listaJugadores=bdJugador.BuscarEqui(String.valueOf(equipo.getIdEquipo()));
        
        for(int x=0;x<listaJugadores.size();x++){
            if(x>0)
            {
                plantilla = plantilla + "\n";
            }
            
            dni=listaJugadores.get(x).getDni();
            nombre=listaJugadores.get(x).getNombre();
            apellido1=listaJugadores.get(x).getApellido1();
            apellido2=listaJugadores.get(x).getApellido2();
            nickname=listaJugadores.get(x).getNickname();
            
            plantilla=plantilla + dni + " – " + nombre + " " + apellido1 + " " + apellido2 + " – " + nickname;
        }
        return plantilla;
    }
    
    /**
     * Metodo para buscar los dueños que no tengan equipo asignado.
     * @return devuelve dueños.
     * @throws Exception  lanza excepcion
     */
    
    public static ArrayList<Persona> buscarDuennosSinEquipo() throws Exception {
         return bdPersona.buscarDuennosSinEquipo();
    }
    
    /**
     * Metodo para buscar los usuarios que sean dueños.
     * @return Devuelve todos los usuarios que sean dueños.
     * @throws Exception  lanza excepcion
     */

    // Imanol Luis
    public static ArrayList<Persona> buscarUsuariosDuennos() throws Exception {
         return bdPersona.buscarUsuariosDuennos();
    }
    
    /**
     * Metodo para comprobar que el dueño pertenece a un equipo.
     * @param usuario String
     * @return objeto equipo cuando todo está correcto.
     * @throws Exception  lanza excepcion
     */

    // Imanol Luis
    public static boolean duennoTieneEquipo(String usuario) throws Exception {
        equipo = bdEquipo.BuscarEquipoPorUsuario(usuario);
        if(equipo == null)
        {
            return false;
        }
        else
        {
            return true;
        }
    }    
    
    /**
     * Metodo para obtener persona por su id.
     * @param idPersona int
     * @return objeto persona
     * @throws Exception  lanza excepcion
     */

    public static Persona obtenerPersona(int idPersona) throws Exception {
        persona = bdPersona.buscarPersona(idPersona);
        return persona;
    }
    //----------------MIKEL

    /**
     *
     * @param j int
     * @return ArrayList Partido
     * @throws Exception lanza excepcion
     */
    public static ArrayList <Partido> BuscarPartidosPorJornada (int j) throws Exception{
        return bdPartido.BuscarPartidosPorJornada (j);
    }

    /**
     *
     * @param j int
     * @return  ArrayList Partido
     * @throws Exception lanza excepcion
     */
    public static ArrayList<Partido> BuscarPartidosPorJornada2 (int j) throws Exception{
        return bdPartido.BuscarPartidosPorJornada2(j);
    }

    /**
     *
     * @param j int
     * @throws Exception lanza excepcion
     */
    public static void domUltimaJornada (int j) throws Exception{
        Date hoy = new Date();
        SimpleDateFormat ff = new SimpleDateFormat("yyyy-MM-dd");
        domResultadosUltimaJornada.xmlUltJor(BuscarPartidosPorJornada2(j),j, ff.format(hoy));
    }

    /**
     *
     * @return  ArrayList Partido
     * @throws Exception lanza excepcion
     */
    public static ArrayList<Partido> saxUltimaJornada() throws Exception{
        return saxJornadaEnCurso.metodoraiz();
    }
    
    /**
     *
     * @return  int
     * @throws Exception lanza excepcion
     */
    public static int consultarUltimaJornadaActual() throws Exception{
        int j = bdJornada.consultaUltimaJornadaActual();
        return j;
    }

    /**
     *
     * @throws Exception lanza excepcion
     */
    public static void domLiga () throws Exception{
        DOM_Liga liga = new DOM_Liga();
        liga.xmlLiga(bdJornada.BuscarJornadas());
    }

    /**
     *
     * @return ArrayList Jornada
     * @throws Exception  lanza excepcion
     */
    public static ArrayList<Jornada> saxLiga() throws Exception{
        SAX_Liga saxLiga=new SAX_Liga();
        ArrayList<Jornada> jornadas=saxLiga.metodoraiz();
        Calendar fecha = Calendar.getInstance();
        SimpleDateFormat ff = new SimpleDateFormat("yyyy-MM-dd");
        String f = saxLiga.getExpiracion().replace(" ", "");
        fecha.setTime(ff.parse(saxLiga.getExpiracion()));
        Calendar hoy = Calendar.getInstance();
        hoy.set(Calendar.HOUR_OF_DAY, 00);
        hoy.set(Calendar.MINUTE, 00);
        hoy.set(Calendar.SECOND, 00);
        hoy.set(Calendar.MILLISECOND, 00);
        if(fecha.equals(hoy)){
            domLiga ();
            jornadas= new ArrayList(saxLiga.metodoraiz());
        }
        return jornadas;
    }
    /**
     * Metodo de creación y ejecución de parser.
     * @throws Exception  lanza excepcion
     */
    
    //------------------Jon
    public static void domClasificacion() throws Exception{
        DOM_Clasificacion clasif = new DOM_Clasificacion();
        Date hoy = new Date();
        SimpleDateFormat ff = new SimpleDateFormat("yyyy-MM-dd");
        clasif.ejecutar(ff.format(hoy));
        
    }

    
    /**
     * Metodo de creación del parser sax.
     * @return devuelve una lista 
     * @throws Exception  lanza excepcion
     */
    
    public static ArrayList<Object> saxClasificacion() throws Exception{
        SAX_Clasificacion clasax = new SAX_Clasificacion();
        ArrayList<Object> lista = new ArrayList();
        lista = clasax.metodoraiz();
        Calendar fecha = Calendar.getInstance();
        SimpleDateFormat ff = new SimpleDateFormat("yyyy-MM-dd");
        fecha.setTime(ff.parse(clasax.getExpiracion()));
        Calendar hoy = Calendar.getInstance();
        hoy.set(Calendar.HOUR_OF_DAY, 00);
        hoy.set(Calendar.MINUTE, 00);
        hoy.set(Calendar.SECOND, 00);
        hoy.set(Calendar.MILLISECOND, 00);
        // si no es fecha actual se vacía la lista y actualiza la lista
        if(!fecha.equals(hoy)){
            lista = new ArrayList();
            domClasificacion();
            lista = clasax.metodoraiz();
            domUltimaJornada(consultarUltimaJornadaActual());
        }
        return lista;
    }
    
}
