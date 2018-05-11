/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import Controladora.Main;
import Excepciones.Excepcion;
import java.awt.Color;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 * Vista para la carga del login.
 * Fecha de creación de la vista: 23/04/2018
 * @author eqdaw04
 */
public class VLogin extends javax.swing.JFrame {

    int error, cont = 0;
    
    public VLogin() {
        initComponents();
        setLocationRelativeTo(null);
        this.getContentPane().setBackground(new Color(70, 130, 180));
        setVisible(true);

        jTextField1.grabFocus();
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
        jLabel2 = new javax.swing.JLabel();
        tfUsuario = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        bAcceder = new javax.swing.JButton();
        bSalir = new javax.swing.JButton();
        pfContrasenna = new javax.swing.JPasswordField();
        jTextField1 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(0, 66, 255));
        setMinimumSize(new java.awt.Dimension(562, 496));
        setPreferredSize(new java.awt.Dimension(562, 496));
        getContentPane().setLayout(null);

        jLabel1.setFont(new java.awt.Font("Tahoma", 3, 36)); // NOI18N
        jLabel1.setText("¡Bienvenidos a E-Sport!");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(50, 40, 450, 70);

        jLabel2.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel2.setText("Usuario:");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(230, 120, 70, 24);

        tfUsuario.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        tfUsuario.setText("usu");
        getContentPane().add(tfUsuario);
        tfUsuario.setBounds(190, 150, 150, 30);

        jLabel3.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel3.setText("Contraseña:");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(220, 200, 98, 24);

        bAcceder.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        bAcceder.setText("Acceder");
        bAcceder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bAccederActionPerformed(evt);
            }
        });
        getContentPane().add(bAcceder);
        bAcceder.setBounds(120, 300, 120, 40);

        bSalir.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        bSalir.setText("Salir");
        bSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bSalirActionPerformed(evt);
            }
        });
        getContentPane().add(bSalir);
        bSalir.setBounds(290, 300, 120, 40);

        pfContrasenna.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        pfContrasenna.setText("sus");
        getContentPane().add(pfContrasenna);
        pfContrasenna.setBounds(190, 230, 150, 30);

        jTextField1.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                jTextField1CaretUpdate(evt);
            }
        });
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField1KeyPressed(evt);
            }
        });
        getContentPane().add(jTextField1);
        jTextField1.setBounds(20, 410, 69, 22);

        jLabel4.setText("1. Clase; 2. Jon; 3. Mikel");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(20, 380, 160, 16);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bAccederActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bAccederActionPerformed
        try
        {
            if (tfUsuario.getText().isEmpty())
            {
                throw new Excepcion(1);
            }
            else if (String.copyValueOf(pfContrasenna.getPassword()).isEmpty())
            {
                throw new Excepcion(2);
            }
            else{
                
                Main.accederPrincipal(tfUsuario.getText(), pfContrasenna.getPassword(), Integer.parseInt(jTextField1.getText()) );
            }
        }
        catch (Excepcion e)
        {
            pfContrasenna.setText("");
            pfContrasenna.grabFocus();
            javax.swing.JOptionPane.showMessageDialog(this, e.getMessage() + "(" + cont + "/3)", "Error", 0);
        }
        catch (Exception e)
        {
            javax.swing.JOptionPane.showMessageDialog(this, e.getClass());
        }
        finally{
            if(error == 13){
                Main.salir(this);
            }
        }
    }//GEN-LAST:event_bAccederActionPerformed

    /**
     * Metodo para obtener la contraseña.
     * @return devuelve la contraseña 
     */
    
    public JPasswordField getPfContrasenna() {
        return pfContrasenna;
    }
    
    /**
     * Metodo para obtener el usuario.
     * @return devuelve el usuario
     */

    public JTextField getTfUsuario() {
        return tfUsuario;
    }
    
    /**
     * Metodo para obtener errores.
     * @return devuelve un error
     */

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    public int getCont() {
        return cont;
    }

    public void setCont(int cont) {
        this.cont = cont;
    }
    
    private void bSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bSalirActionPerformed
        Main.salir(this);
    }//GEN-LAST:event_bSalirActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        try
        {
            if (tfUsuario.getText().isEmpty())
            {
                throw new Excepcion(1);
            }
            else if (String.copyValueOf(pfContrasenna.getPassword()).isEmpty())
            {
                throw new Excepcion(2);
            }
            else{
                
                Main.accederPrincipal(tfUsuario.getText(), pfContrasenna.getPassword(), Integer.parseInt(jTextField1.getText()) );
            }
        }
        catch (Excepcion e)
        {
            pfContrasenna.setText("");
            pfContrasenna.grabFocus();
            javax.swing.JOptionPane.showMessageDialog(this, e.getMessage() + "(" + cont + "/3)", "Error", 0);
        }
        catch (Exception e)
        {
            javax.swing.JOptionPane.showMessageDialog(this, e.getClass());
        }
        finally{
            if(error == 13){
                Main.salir(this);
            }
        }
        
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jTextField1CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_jTextField1CaretUpdate
        
    }//GEN-LAST:event_jTextField1CaretUpdate

    private void jTextField1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1KeyPressed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bAcceder;
    private javax.swing.JButton bSalir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JPasswordField pfContrasenna;
    private javax.swing.JTextField tfUsuario;
    // End of variables declaration//GEN-END:variables
}
