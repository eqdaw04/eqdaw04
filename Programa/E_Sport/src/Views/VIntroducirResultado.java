/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import Controladora.Main;
import Excepciones.Excepcion;
import UML.Partido;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author v6222
 */
public class VIntroducirResultado extends javax.swing.JDialog {

    int n;
    ArrayList<Partido> listaPartido;
    Partido p;
    /**
     * Creates new form VIntroducirResultado
     * @param n
     */
    public VIntroducirResultado(int n) {
        initComponents();
        // cargar los datos secundarios necesarios para operar
        cargarDatos(n);
    }
    
    private void cargarDatos(int n){
        this.n = n;
        setModal(true);
        setLocationRelativeTo(null);
        // Cargar los partidos del día de hoy, si existe
        cargarPartido();
        ccFecha.setEnabled(false);
        setVisible(true);
    }

    private void cargarPartido(){
        try{
            listaPartido = new ArrayList();
            listaPartido = Main.consultarLosPartidosPorFecha(ccFecha.getCalendar());
            cbPartido.removeAllItems();
            for(Partido pa:listaPartido) {
                int fd = pa.getIdPartido();
                cbPartido.addItem(String.valueOf(fd));

            }
            if(cbPartido.getItemCount() !=0){
                cbPartido.setSelectedIndex(0);
            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(this, e.getClass() + " \n " + e.getMessage(), "Error", 0);
        }
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
        jScrollPane1 = new javax.swing.JScrollPane();
        taLocal = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        taVisitante = new javax.swing.JTextArea();
        tfPuntosLocal = new javax.swing.JTextField();
        tfPuntosVisitante = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        tfHora = new javax.swing.JTextField();
        tfLugar = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        ccFecha = new org.freixas.jcalendar.JCalendarCombo();
        bAceptar = new javax.swing.JButton();
        bCancelar = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        cbPartido = new javax.swing.JComboBox<String>();
        bModificar = new javax.swing.JButton();
        img = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(796, 646));
        setMinimumSize(new java.awt.Dimension(796, 646));
        getContentPane().setLayout(null);

        jLabel1.setFont(new java.awt.Font("Berlin Sans FB Demi", 0, 36)); // NOI18N
        jLabel1.setText("Inserción de los resultados del partido");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(12, 13, 700, 42);

        taLocal.setEditable(false);
        taLocal.setColumns(20);
        taLocal.setRows(5);
        jScrollPane1.setViewportView(taLocal);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(40, 290, 205, 231);

        taVisitante.setEditable(false);
        taVisitante.setColumns(20);
        taVisitante.setRows(5);
        jScrollPane2.setViewportView(taVisitante);

        getContentPane().add(jScrollPane2);
        jScrollPane2.setBounds(430, 290, 204, 231);

        tfPuntosLocal.setEditable(false);
        getContentPane().add(tfPuntosLocal);
        tfPuntosLocal.setBounds(180, 260, 42, 22);

        tfPuntosVisitante.setEditable(false);
        getContentPane().add(tfPuntosVisitante);
        tfPuntosVisitante.setBounds(600, 260, 42, 22);

        jLabel3.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel3.setText("Equipo Local:");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(40, 270, 130, 18);

        jLabel4.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel4.setText("Equipo Visitante:");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(430, 270, 150, 18);

        jLabel5.setText("Puntuaciones");
        getContentPane().add(jLabel5);
        jLabel5.setBounds(330, 230, 75, 16);

        jLabel6.setFont(new java.awt.Font("Verdana", 1, 13)); // NOI18N
        jLabel6.setText("Modificar resultado anterior: Haga click modificar partido y seleccione la fecha");
        getContentPane().add(jLabel6);
        jLabel6.setBounds(30, 70, 680, 17);

        jLabel7.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel7.setText("Hora del Partido:");
        getContentPane().add(jLabel7);
        jLabel7.setBounds(460, 150, 150, 18);

        tfHora.setEditable(false);
        tfHora.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tfHora.setText("10:00");
        getContentPane().add(tfHora);
        tfHora.setBounds(630, 150, 80, 22);

        tfLugar.setEditable(false);
        getContentPane().add(tfLugar);
        tfLugar.setBounds(210, 190, 230, 22);

        jLabel8.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel8.setText("Lugar del Partido:");
        getContentPane().add(jLabel8);
        jLabel8.setBounds(30, 190, 150, 18);

        ccFecha.setEditable(true);
        ccFecha.setEnabled(false);
        ccFecha.addDateListener(new org.freixas.jcalendar.DateListener() {
            public void dateChanged(org.freixas.jcalendar.DateEvent evt) {
                ccFechaDateChanged(evt);
            }
        });
        getContentPane().add(ccFecha);
        ccFecha.setBounds(270, 110, 260, 22);

        bAceptar.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        bAceptar.setText("Aceptar");
        bAceptar.setEnabled(false);
        bAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bAceptarActionPerformed(evt);
            }
        });
        getContentPane().add(bAceptar);
        bAceptar.setBounds(20, 560, 140, 27);

        bCancelar.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        bCancelar.setText("Cancelar");
        bCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bCancelarActionPerformed(evt);
            }
        });
        getContentPane().add(bCancelar);
        bCancelar.setBounds(550, 560, 140, 27);

        jLabel11.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel11.setText("Partido número: ");
        getContentPane().add(jLabel11);
        jLabel11.setBounds(30, 150, 150, 18);

        cbPartido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbPartidoActionPerformed(evt);
            }
        });
        getContentPane().add(cbPartido);
        cbPartido.setBounds(220, 150, 73, 22);

        bModificar.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        bModificar.setText("Modificar Partido");
        bModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bModificarActionPerformed(evt);
            }
        });
        getContentPane().add(bModificar);
        bModificar.setBounds(40, 110, 165, 27);

        img.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/fondo2.jpg"))); // NOI18N
        getContentPane().add(img);
        img.setBounds(0, 0, 800, 650);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bCancelarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bCancelarActionPerformed

    private void bModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bModificarActionPerformed
        ccFecha.setEnabled(true);
    }//GEN-LAST:event_bModificarActionPerformed

    private void cbPartidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbPartidoActionPerformed
        p = null;
        for(int x = 0 ; x < listaPartido.size() ; x++){
            p = listaPartido.get(x);
            if(cbPartido.getSelectedIndex()>=0){
                if(p.getIdPartido() == Integer.valueOf(cbPartido.getSelectedItem().toString())){
                    SimpleDateFormat f = new SimpleDateFormat("hh:mm");
                    tfHora.setText(f.format(p.getFecha().getTime()));
                    
                    tfPuntosLocal.setText(String.valueOf(p.getmLocal()));
                    tfPuntosVisitante.setText(String.valueOf(p.getmLocal()));
                    tfPuntosLocal.setEditable(true);
                    tfPuntosVisitante.setEditable(true);
                    bAceptar.setEnabled(true);
                    String dato;
                    dato = "";
                    if(p.geteLocal() != null){
                        tfLugar.setText(p.geteLocal().getLugar());
                        if(!p.geteLocal().getComentario().equals("")){
                            dato = p.geteLocal().getComentario();
                        }
                        taLocal.setText(p.geteLocal().getNombre() + "\n" + dato);
                    }
                    else{
                        tfLugar.setText("EL EQUIPO SE ENCUENTRA EN DESCANSO");
                        taLocal.setText("EN DESCANSO");
                    }

                    if(p.geteVisitante() != null){
                        if(p.geteVisitante().getComentario().equals("")){
                            dato = p.geteVisitante().getComentario();
                        }
                        taVisitante.setText(p.geteVisitante().getNombre() + "\n" + dato);
                    }
                    else{
                        tfLugar.setText("EL EQUIPO SE ENCUENTRA EN DESCANSO");
                        taVisitante.setText("EN DESCANSO");
                    }

                    if(p.geteLocal() == null || p.geteVisitante() == null){
                        tfPuntosLocal.setEditable(false);
                        tfPuntosVisitante.setEditable(false);
                        bAceptar.setEnabled(false);
                    }

                    x = listaPartido.size();
                }
            }
        }
    }//GEN-LAST:event_cbPartidoActionPerformed

    private void bAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bAceptarActionPerformed
            
        try{
            p.setmLocal(Integer.valueOf(tfPuntosLocal.getText()));
            p.setmVisitante(Integer.parseInt(tfPuntosVisitante.getText()));
            Main.validar(46, tfPuntosLocal);
            Main.validar(46, tfPuntosVisitante);
            if(Main.modificarMarcador(p)){
                JOptionPane.showMessageDialog(this, "Marcador modificado correctamente.");
                Main.reabrir(this, "", 6);
            }
            else{
                throw new Excepcion(47);
            }
            
        }
        catch(Excepcion e){
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", 0);
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(this, e.getClass() + " \n " + e.getMessage(), "Error", 0);
        }
            
    }//GEN-LAST:event_bAceptarActionPerformed

    private void ccFechaDateChanged(org.freixas.jcalendar.DateEvent evt) {//GEN-FIRST:event_ccFechaDateChanged
        cargarPartido();
    }//GEN-LAST:event_ccFechaDateChanged

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bAceptar;
    private javax.swing.JButton bCancelar;
    private javax.swing.JButton bModificar;
    private javax.swing.JComboBox<String> cbPartido;
    private org.freixas.jcalendar.JCalendarCombo ccFecha;
    private javax.swing.JLabel img;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea taLocal;
    private javax.swing.JTextArea taVisitante;
    private javax.swing.JTextField tfHora;
    private javax.swing.JTextField tfLugar;
    private javax.swing.JTextField tfPuntosLocal;
    private javax.swing.JTextField tfPuntosVisitante;
    // End of variables declaration//GEN-END:variables
}
