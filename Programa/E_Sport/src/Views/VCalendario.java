/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import Controladora.Main;
import UML.*;
import java.awt.Color;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author usuario
 */
public class VCalendario extends javax.swing.JDialog {

    /**
     * Creates new form VCalendario
     */
    private ArrayList<Jornada> jornadas;
    private int pos=0;
    public VCalendario() {
        initComponents();
        setVisible(true);
        setLocationRelativeTo(null);
        obtenerDatos();
        rellenar(pos);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        Njornada = new javax.swing.JLabel();
        lbFechaIni = new javax.swing.JLabel();
        lbFechaFin = new javax.swing.JLabel();
        Label2 = new javax.swing.JLabel();
        spPartidos = new javax.swing.JScrollPane();
        bPrimero = new javax.swing.JButton();
        bAnterior = new javax.swing.JButton();
        bSiguiente = new javax.swing.JButton();
        bUltimo = new javax.swing.JButton();
        bSalir = new javax.swing.JButton();
        imagen = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(581, 596));
        setMinimumSize(new java.awt.Dimension(581, 596));
        getContentPane().setLayout(null);

        jLabel1.setFont(new java.awt.Font("Berlin Sans FB Demi", 0, 36)); // NOI18N
        jLabel1.setText("Jornada");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(160, 30, 196, 42);

        Njornada.setFont(new java.awt.Font("Berlin Sans FB Demi", 0, 36)); // NOI18N
        Njornada.setText("X");
        getContentPane().add(Njornada);
        Njornada.setBounds(363, 30, 52, 42);

        lbFechaIni.setFont(new java.awt.Font("Verdana", 1, 16)); // NOI18N
        lbFechaIni.setText("Fecha inicio");
        getContentPane().add(lbFechaIni);
        lbFechaIni.setBounds(115, 90, 144, 21);

        lbFechaFin.setFont(new java.awt.Font("Verdana", 1, 16)); // NOI18N
        lbFechaFin.setText("Fecha fin");
        getContentPane().add(lbFechaFin);
        lbFechaFin.setBounds(337, 90, 136, 21);

        Label2.setFont(new java.awt.Font("Verdana", 1, 16)); // NOI18N
        Label2.setText("-");
        getContentPane().add(Label2);
        Label2.setBounds(277, 90, 8, 21);

        spPartidos.setBorder(null);
        getContentPane().add(spPartidos);
        spPartidos.setBounds(57, 161, 446, 269);

        bPrimero.setText("|<");
        bPrimero.setEnabled(false);
        bPrimero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bPrimeroActionPerformed(evt);
            }
        });
        getContentPane().add(bPrimero);
        bPrimero.setBounds(150, 448, 47, 25);

        bAnterior.setText("<");
        bAnterior.setEnabled(false);
        bAnterior.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bAnteriorActionPerformed(evt);
            }
        });
        getContentPane().add(bAnterior);
        bAnterior.setBounds(204, 448, 41, 25);

        bSiguiente.setText(">");
        bSiguiente.setEnabled(false);
        bSiguiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bSiguienteActionPerformed(evt);
            }
        });
        getContentPane().add(bSiguiente);
        bSiguiente.setBounds(339, 448, 41, 25);

        bUltimo.setText(">|");
        bUltimo.setEnabled(false);
        bUltimo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bUltimoActionPerformed(evt);
            }
        });
        getContentPane().add(bUltimo);
        bUltimo.setBounds(387, 448, 47, 25);

        bSalir.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        bSalir.setText("Salir");
        bSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bSalirActionPerformed(evt);
            }
        });
        getContentPane().add(bSalir);
        bSalir.setBounds(443, 491, 72, 35);

        imagen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/fondo2.jpg"))); // NOI18N
        getContentPane().add(imagen);
        imagen.setBounds(0, 0, 580, 600);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bPrimeroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bPrimeroActionPerformed
        pos = 0;
        rellenar(pos);
        bSiguiente.setEnabled(true);
        bUltimo.setEnabled(true);
        bPrimero.setEnabled(false);
        bAnterior.setEnabled(false);

    }//GEN-LAST:event_bPrimeroActionPerformed

    private void bAnteriorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bAnteriorActionPerformed
        pos--;
        rellenar(pos);
        bSiguiente.setEnabled(true);
        bUltimo.setEnabled(true);
        if(pos == 0){
            bPrimero.setEnabled(false);
            bAnterior.setEnabled(false);
        }

    }//GEN-LAST:event_bAnteriorActionPerformed

    private void bSiguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bSiguienteActionPerformed
        pos++;
        rellenar(pos);
        bPrimero.setEnabled(true);
        bAnterior.setEnabled(true);
        if(pos == jornadas.size()-1){
            bSiguiente.setEnabled(false);
            bUltimo.setEnabled(false);
        }
    }//GEN-LAST:event_bSiguienteActionPerformed

    private void bUltimoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bUltimoActionPerformed
        pos = jornadas.size()-1;
        rellenar(pos);
        bSiguiente.setEnabled(false);
        bUltimo.setEnabled(false);
        bPrimero.setEnabled(true);
        bAnterior.setEnabled(true);
    }//GEN-LAST:event_bUltimoActionPerformed

    private void bSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bSalirActionPerformed
       Main.cerrar(this); // TODO add your handling code here:
    }//GEN-LAST:event_bSalirActionPerformed

    /**
     * @param args the command line arguments
     */
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Label2;
    private javax.swing.JLabel Njornada;
    private javax.swing.JButton bAnterior;
    private javax.swing.JButton bPrimero;
    private javax.swing.JButton bSalir;
    private javax.swing.JButton bSiguiente;
    private javax.swing.JButton bUltimo;
    private javax.swing.JLabel imagen;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lbFechaFin;
    private javax.swing.JLabel lbFechaIni;
    private javax.swing.JScrollPane spPartidos;
    // End of variables declaration//GEN-END:variables

    private void obtenerDatos() {
        try {
        jornadas= Main.consultarTodasLasJornadas();
        } catch (Exception ex) {
            Logger.getLogger(VCalendario.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (int x=0;x<jornadas.size();x++){
            try {
                jornadas.get(x).setListaPartidos(Main.BuscarPartidosPorJornada(jornadas.get(x).getIdJornada()));
            } catch (Exception ex) {
                Logger.getLogger(VCalendario.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(jornadas.size()>1){
            bSiguiente.setEnabled(true);
            bUltimo.setEnabled(true);
        }
    }

    private void rellenar(int pos) {
        Njornada.setText(String.valueOf(jornadas.get(pos).getIdJornada()));
        //falla la fecha ayuda joooonXu
        lbFechaIni.setText(String.valueOf(jornadas.get(pos).getFechaInicio()));
        lbFechaFin.setText(String.valueOf(jornadas.get(pos).getFechaFinal()));
        String titulos[] ={"Equipos","Fecha","Lugar"};
        String datos [] []= new String[jornadas.get(pos).getListaPartidos().size()] [3];
        for (int x=0; x < datos.length;x++){
            datos [x][2] = "";
            if(jornadas.get(pos).getListaPartidos().get(x).geteLocal()== null){
                datos [x][0] = jornadas.get(pos).getListaPartidos().get(x).geteVisitante().getNombre() + " Descansa";
            }else{
                if(jornadas.get(pos).getListaPartidos().get(x).geteVisitante() == null){
                    datos [x][0] = jornadas.get(pos).getListaPartidos().get(x).geteLocal().getNombre()+ " Descansa";
                }else{
                    datos [x][0] = jornadas.get(pos).getListaPartidos().get(x).geteLocal().getNombre()+"  VS  "+jornadas.get(pos).getListaPartidos().get(x).geteVisitante().getNombre();
                    datos [x][2] = jornadas.get(pos).getListaPartidos().get(x).geteLocal().getLugar();
                }
            }
            
            datos [x][1] = String.valueOf(jornadas.get(pos).getListaPartidos().get(x).getFecha());
            
        }
        JTable tPartidos = new JTable (datos,titulos);
        tPartidos.setEnabled(false);
        tPartidos.setShowVerticalLines(false);
        tPartidos.setGridColor(Color.BLUE);
        tPartidos.setRowHeight(40);
        spPartidos.setViewportView(tPartidos);
        
        
    }
}
