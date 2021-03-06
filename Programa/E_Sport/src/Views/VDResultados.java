/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import Controladora.Main;
import Excepciones.Excepcion;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

/**
 * Vista resultados. Veremos la clasificación actual, y el resultado de la última jornada.
 * Fecha de creación de la vista: 15/05/2018
 * @author eqdaw04
 * Se ha usado la librería para el gráfico en
 * http://www.jfree.org/jfreechart/download.html
 */
public class VDResultados extends javax.swing.JDialog {
    // Para cargar datos a las tablas
    DefaultTableModel mJornada, mClasificacion;
    
    /**
     * Creates new form VDResultados
     * @param nombre string nombre
     */
    public VDResultados(String nombre) {
        initComponents();
        cargarDatos(nombre);
    }

    private void cargarDatos(String nombre){
        setModal(true);
        lNombre.setText(lNombre.getText() + nombre);
        modelarTabla();
        this.setLocationRelativeTo(null);
        try {
            graficoClasificacion(Main.resultados());
            // se ha programado para el la última jornada y no la actual, ya que así lo pide el enunciado
            tablaUltimaJornada(Main.resultadosUltimaJornada());
        } 
        catch (Excepcion e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Atencón", 1);
        }
        catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getClass() + " \n " + ex.getMessage(), "Error", 0);
        }
	pGrafico.setVisible(false);
        setVisible(true);
    }
    
    private void tablaUltimaJornada(ArrayList<Object> lista){
        // partido, equipo, puntos, visitante
        for(int x =0; x< lista.size(); x++){
            Object[] dato = (Object[]) lista.get(x);
            Object[] fila = new Object[3];
            fila[0] = dato[0];
            fila[1] = dato[1];
            fila[2] = dato[2];
            mJornada.addRow(fila);
        }
    }
    
    /**
     * Metodo para crear el gráfico.
     * @param lista Object
     */
    
    private void graficoClasificacion(ArrayList<Object> lista){
        DefaultPieDataset pieDataset = new DefaultPieDataset();
        for(int x =0; x< lista.size(); x++){
            Object[] fila = (Object[]) lista.get(x);
            pieDataset.setValue(String.valueOf(fila[1]), new Integer(Integer.parseInt(fila[2].toString())));
            mClasificacion.addRow(fila);
        }
        JFreeChart chart = ChartFactory.createPieChart("",pieDataset, true, true, false);
        
        //Mostramos la grafica en pantalla
        ChartPanel panel = new ChartPanel(chart);
        pGrafico.setLayout(new java.awt.BorderLayout());
        pGrafico.add(panel);
        pGrafico.validate();
        
    }
    
    /**
     * Metodo para darle forma a la tabla en la que aparecen los resultados.
     */
    
    private void modelarTabla(){
        
	mJornada = new DefaultTableModel();
	Object[] vec1 = {"Partido","Local VS Visitante","Marcador"};
	mJornada.setColumnIdentifiers(vec1);
	tJornada.setModel(mJornada);
	tJornada.getColumnModel().getColumn(0).setPreferredWidth(75);
	tJornada.getColumnModel().getColumn(1).setPreferredWidth(360);
	tJornada.getColumnModel().getColumn(2).setPreferredWidth(75);
        tJornada.setRowHeight(40);
        tJornada.setShowVerticalLines(false);
        tJornada.setGridColor(Color.BLUE);
        mClasificacion = new DefaultTableModel();
        
	Object[] vec2 = {"Posición","Equipo","Marcador"};
	mClasificacion.setColumnIdentifiers(vec2);
	tClasificacion.setModel(mClasificacion);
	tClasificacion.getColumnModel().getColumn(0).setPreferredWidth(65);
	tClasificacion.getColumnModel().getColumn(1).setPreferredWidth(150);
	tClasificacion.getColumnModel().getColumn(2).setPreferredWidth(75);
        tClasificacion.setRowHeight(40);
        tClasificacion.setShowVerticalLines(false);
        tClasificacion.setGridColor(Color.BLUE);
        DefaultTableCellRenderer centrar = new DefaultTableCellRenderer();
        centrar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tJornada.getColumnModel().getColumn(0).setCellRenderer(centrar);
        tJornada.getColumnModel().getColumn(1).setCellRenderer(centrar);
        tJornada.getColumnModel().getColumn(2).setCellRenderer(centrar);
        tJornada.getColumnModel().getColumn(0).setHeaderRenderer(centrar);
        tJornada.getColumnModel().getColumn(1).setHeaderRenderer(centrar);
        tJornada.getColumnModel().getColumn(2).setHeaderRenderer(centrar);
        tClasificacion.getColumnModel().getColumn(0).setCellRenderer(centrar);
        tClasificacion.getColumnModel().getColumn(1).setCellRenderer(centrar);
        tClasificacion.getColumnModel().getColumn(2).setCellRenderer(centrar);
        tClasificacion.getColumnModel().getColumn(0).setHeaderRenderer(centrar);
        tClasificacion.getColumnModel().getColumn(1).setHeaderRenderer(centrar);
        tClasificacion.getColumnModel().getColumn(2).setHeaderRenderer(centrar);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pGrafico = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tJornada = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tClasificacion = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        lNombre = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        img = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(976, 538));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });
        getContentPane().setLayout(null);

        javax.swing.GroupLayout pGraficoLayout = new javax.swing.GroupLayout(pGrafico);
        pGrafico.setLayout(pGraficoLayout);
        pGraficoLayout.setHorizontalGroup(
            pGraficoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 540, Short.MAX_VALUE)
        );
        pGraficoLayout.setVerticalGroup(
            pGraficoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 310, Short.MAX_VALUE)
        );

        getContentPane().add(pGrafico);
        pGrafico.setBounds(390, 110, 540, 310);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("Última Jornada: ");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(390, 120, 540, 22);

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
        if (tJornada.getColumnModel().getColumnCount() > 0) {
            tJornada.getColumnModel().getColumn(0).setPreferredWidth(30);
            tJornada.getColumnModel().getColumn(1).setPreferredWidth(300);
            tJornada.getColumnModel().getColumn(2).setPreferredWidth(50);
        }

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(390, 150, 540, 269);

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
        jScrollPane2.setBounds(50, 150, 319, 269);

        jLabel3.setFont(new java.awt.Font("Verdana", 1, 16)); // NOI18N
        jLabel3.setText("Clasificación Actual:");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(50, 111, 260, 30);

        jButton1.setFont(new java.awt.Font("Verdana", 1, 16)); // NOI18N
        jButton1.setText("Salir");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(796, 40, 130, 40);

        lNombre.setFont(new java.awt.Font("Berlin Sans FB Demi", 0, 36)); // NOI18N
        lNombre.setText("Bienvenido SR./SRA. ");
        getContentPane().add(lNombre);
        lNombre.setBounds(50, 40, 540, 50);

        jButton3.setFont(new java.awt.Font("Verdana", 1, 16)); // NOI18N
        jButton3.setText("Ver en Gráfico");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3);
        jButton3.setBounds(50, 440, 190, 50);

        img.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/fondo2.jpg"))); // NOI18N
        getContentPane().add(img);
        img.setBounds(0, 0, 980, 610);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        try {
            if(jButton3.getText().equals("Ver en Gráfico")){
                pGrafico.setVisible(true);
                jButton3.setText("Ocultar Gráfico");
            }
            else{
                jButton3.setText("Ver en Gráfico");
                pGrafico.setVisible(false);
            }
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getClass() + " \n " + ex.getMessage(), "Error", 0);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Main.cerrar(this);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
    
    }//GEN-LAST:event_formWindowClosing


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel img;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lNombre;
    private javax.swing.JPanel pGrafico;
    private javax.swing.JTable tClasificacion;
    private javax.swing.JTable tJornada;
    // End of variables declaration//GEN-END:variables
}
