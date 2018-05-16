/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import Controladora.Main;
import java.awt.Image;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;


/**
 *
 * @author v6222
 */
public class VUPrincipal extends javax.swing.JFrame {

    /**
     * Creates new form VUPrincipal
     */
    public VUPrincipal() {
        initComponents();
        cargarDatos();
    }

    private void cargarDatos(){
        setVisible(true);
        setLocationRelativeTo(null);
        crearImagenes();
        setTitle("Bienvenido a E-Sport");
        
        
    }
    
    private void crearImagenes(){
        
        ImageIcon logo = new ImageIcon("../../imagenes/4fan.png");
        Icon icono3 = new ImageIcon(logo.getImage().getScaledInstance(logotipo.getWidth(), logotipo.getHeight(), Image.SCALE_DEFAULT));
        logotipo.setIcon(icono3);
        this.repaint();
        this.repaint();
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tJornada = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tClasificacion = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane12 = new javax.swing.JScrollPane();
        lJornada8 = new javax.swing.JList<>();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        logotipo = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        img = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(819, 789));
        setType(java.awt.Window.Type.UTILITY);
        getContentPane().setLayout(null);

        tJornada.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Posición", "Local VS Visitante", "Marcador"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tJornada.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tJornada.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jScrollPane1.setViewportView(tJornada);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(230, 120, 540, 269);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("Partidos de la Jornada ");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(230, 90, 540, 22);

        tClasificacion.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tClasificacion.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jScrollPane2.setViewportView(tClasificacion);

        getContentPane().add(jScrollPane2);
        jScrollPane2.setBounds(230, 440, 319, 269);

        jLabel3.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        jLabel3.setText("Clasificación Actual:");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(230, 400, 260, 30);

        lJornada8.setEnabled(false);
        lJornada8.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                lJornada8ValueChanged(evt);
            }
        });
        jScrollPane12.setViewportView(lJornada8);

        getContentPane().add(jScrollPane12);
        jScrollPane12.setBounds(20, 120, 182, 590);

        jLabel7.setFont(new java.awt.Font("Verdana", 1, 24)); // NOI18N
        jLabel7.setText("Hola ");
        getContentPane().add(jLabel7);
        jLabel7.setBounds(20, 40, 590, 30);

        jLabel8.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        jLabel8.setText("Número de Jornada");
        getContentPane().add(jLabel8);
        jLabel8.setBounds(20, 90, 200, 23);

        jButton1.setText("Actualizar datos");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(570, 510, 200, 25);

        jButton2.setText("<html><center>Ver en un gráfico<br></br>los resultados de la Liga</center></html>");
        jButton2.setActionCommand("");
        jButton2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        getContentPane().add(jButton2);
        jButton2.setBounds(570, 550, 200, 56);

        jButton3.setText("Cerrar Sesión");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3);
        jButton3.setBounds(570, 684, 200, 25);

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Última actualización de la Liga");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(567, 440, 203, 16);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("12/12/2018");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(567, 469, 203, 16);
        getContentPane().add(logotipo);
        logotipo.setBounds(690, 30, 80, 70);

        jButton4.setText("Ver El Calendario");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton4);
        jButton4.setBounds(570, 620, 200, 25);

        img.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/fondo2.jpg"))); // NOI18N
        getContentPane().add(img);
        img.setBounds(0, 0, 860, 790);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void lJornada8ValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_lJornada8ValueChanged
     
    }//GEN-LAST:event_lJornada8ValueChanged

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        Main.cerrarSesion(this);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        try {
            Main.abrirVentana(8, "");
        } catch (Exception ex) {
            Logger.getLogger(VUPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton4ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel img;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JList<Integer> lJornada;
    private javax.swing.JList<Integer> lJornada1;
    private javax.swing.JList<Integer> lJornada2;
    private javax.swing.JList<Integer> lJornada3;
    private javax.swing.JList<Integer> lJornada4;
    private javax.swing.JList<Integer> lJornada5;
    private javax.swing.JList<Integer> lJornada6;
    private javax.swing.JList<Integer> lJornada7;
    private javax.swing.JList<Integer> lJornada8;
    private javax.swing.JLabel logotipo;
    private javax.swing.JTable tClasificacion;
    private javax.swing.JTable tJornada;
    // End of variables declaration//GEN-END:variables
}
