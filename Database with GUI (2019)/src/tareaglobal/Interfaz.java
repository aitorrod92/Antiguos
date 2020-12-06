package tareaglobal;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

// Esta clase implementa los comportamientos de los diferentes componentes de la GUI
public class Interfaz extends javax.swing.JFrame {
    static Operaciones operaciones;
    
    // Variable centinela que sirve para determinar si se ha pulsado "Modificar" previamente.
    // Pulsar cualquier botón que no sea "Modificar" o "Modificar" por segunda vez, la vuelve "true".
    // Pulsar modificar por primera vez la vuelve "False"
    Boolean PrimerClick = true;
    
    // El programa se inicia instanciando la clase "Operaciones" y la propia ventana
     public static void main(String args[]) throws ClassNotFoundException, SQLException {
         operaciones = new Operaciones();
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Interfaz().setVisible(true);
              }
        });
    }
    
    // Constructor de la ventana
    public Interfaz() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        label7 = new java.awt.Label();
        label8 = new java.awt.Label();
        jScrollPane2 = new javax.swing.JScrollPane();
        ÁreaTexto = new javax.swing.JTextArea();
        SentenciaUtilizada = new java.awt.Label();
        jInternalFrame1 = new javax.swing.JInternalFrame();
        Campo_Código = new javax.swing.JTextField();
        Campo_IDMánager = new javax.swing.JTextField();
        Campo_IDLocalización = new javax.swing.JTextField();
        Campo_Nombre = new javax.swing.JTextField();
        Botón_Mostrar = new javax.swing.JButton();
        label1 = new java.awt.Label();
        label2 = new java.awt.Label();
        label3 = new java.awt.Label();
        label4 = new java.awt.Label();
        label6 = new java.awt.Label();
        Botón_Insertar = new javax.swing.JButton();
        BotónModificar = new javax.swing.JButton();
        BotónBorrar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(0, 0, 0));
        setForeground(new java.awt.Color(153, 0, 153));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        label7.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        label7.setText("Sentencia utilizada");
        getContentPane().add(label7, new org.netbeans.lib.awtextra.AbsoluteConstraints(297, 19, -1, -1));

        label8.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        label8.setText("Salida");
        getContentPane().add(label8, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 70, 120, -1));

        ÁreaTexto.setColumns(20);
        ÁreaTexto.setRows(5);
        jScrollPane2.setViewportView(ÁreaTexto);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(305, 100, 600, 210));

        SentenciaUtilizada.setText("Sentencia");
        getContentPane().add(SentenciaUtilizada, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 40, 640, -1));

        jInternalFrame1.setVisible(true);

        Botón_Mostrar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        Botón_Mostrar.setText("Mostrar");
        Botón_Mostrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Botón_MostrarActionPerformed(evt);
            }
        });

        label1.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        label1.setText("Datos del Departamento:");

        label2.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        label2.setText("Código:");

        label3.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        label3.setText("Nombre:");

        label4.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        label4.setText("ID_Localización:");

        label6.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        label6.setText("ID_Mánager:");

        Botón_Insertar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        Botón_Insertar.setText("Insertar");
        Botón_Insertar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Botón_InsertarActionPerformed(evt);
            }
        });

        BotónModificar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        BotónModificar.setText("Modificar");
        BotónModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotónModificarActionPerformed(evt);
            }
        });

        BotónBorrar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        BotónBorrar.setText("Borrar");
        BotónBorrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotónBorrarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jInternalFrame1Layout = new javax.swing.GroupLayout(jInternalFrame1.getContentPane());
        jInternalFrame1.getContentPane().setLayout(jInternalFrame1Layout);
        jInternalFrame1Layout.setHorizontalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jInternalFrame1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jInternalFrame1Layout.createSequentialGroup()
                        .addComponent(Botón_Insertar)
                        .addGap(2, 2, 2)
                        .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(Botón_Mostrar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(BotónModificar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BotónBorrar))
                    .addGroup(jInternalFrame1Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jInternalFrame1Layout.createSequentialGroup()
                                    .addComponent(label6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(Campo_IDMánager, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(Campo_IDLocalización, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jInternalFrame1Layout.createSequentialGroup()
                                        .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(label3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(label2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(54, 54, 54)
                                        .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(Campo_Código, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(Campo_Nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))))))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jInternalFrame1Layout.setVerticalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jInternalFrame1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Campo_Código, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Campo_Nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(label4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Campo_IDLocalización, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(label6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Campo_IDMánager, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Botón_Insertar)
                    .addComponent(BotónModificar)
                    .addComponent(BotónBorrar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Botón_Mostrar)
                .addContainerGap(69, Short.MAX_VALUE))
        );

        label1.getAccessibleContext().setAccessibleName("Datos del departamento");

        getContentPane().add(jInternalFrame1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Método que se ejecuta al pulsar el botón "Borrar"
    private void BotónBorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotónBorrarActionPerformed
        PrimerClick=true;
        
        // Se ejecuta la operación "Eliminar" y, en función de la cadena devuelta, se escriben distintos mensajes en "ÁreaTexto"
        String ConsultaTrasEliminación ="";
        try {
          ConsultaTrasEliminación = operaciones.Eliminar(Campo_Código.getText());
            if (ConsultaTrasEliminación.contains("Eliminación no realizada")){
                ÁreaTexto.setText(ConsultaTrasEliminación); }
            else {
                ÁreaTexto.setText("Registro eliminado. Código: " + Campo_Código.getText() + "\n\n" + ConsultaTrasEliminación);
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(Interfaz.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // Se cambia la etiqueta "SentenciaUtilizada" para mostrar la sentencia DML empleada
        SentenciaUtilizada.setText(operaciones.getSentenciaDML());
    }//GEN-LAST:event_BotónBorrarActionPerformed

    // Método que se ejecuta al pulsar el botón "Modificar"
    private void BotónModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotónModificarActionPerformed
        // Si se trata de la primera vez que se pulsa, se ejecuta el método "Modificar"
        String ConsultaTrasModificación ="";
        if (PrimerClick){
            try {
                    ConsultaTrasModificación = operaciones.Modificar(Campo_Código.getText());
            } catch (SQLException | ClassNotFoundException ex) { 
                Logger.getLogger(Interfaz.class.getName()).log(Level.SEVERE, null, ex);
            }
            /* En función de la cadena devuelta, se escribe un mensaje u otro en "ÁreaTexto". Si, además, se encuentra un registro que
            coincida con el código introducido, el resto de campos se rellenan con sus datos y "PrimerClick" cambia a falso, permitiendo
            acceder a la segunda parte del proceso de modificación*/
            if (ConsultaTrasModificación.contains("Modificación no realizada")){
                ÁreaTexto.setText(ConsultaTrasModificación);
            } else {
                Campo_Nombre.setText(operaciones.getNombre());
                Campo_IDLocalización.setText(Integer.toString(operaciones.getLocalización()));
                Campo_IDMánager.setText(Integer.toString(operaciones.getMánager()));
                ÁreaTexto.setText(ConsultaTrasModificación);
                PrimerClick = false;
            }
            
            // Se cambia la etiqueta "SentenciaUtilizada" para mostrar la sentencia DQL empleada
            SentenciaUtilizada.setText(operaciones.getSentenciaDQL());
            
        // Si se ha pulsado "Modificar" antes, se ejecuta el método "ModificarSegundoClick"    
        } else {
            try {
                /* Se muestra un mensaje diferente en función de si alguno de los campos (o el campo "Nombre") está vacío o no.
                En caso de no estarlo, además se ejecuta el método "ModificarSegundoClick" antes*/
                if (!Campo_Nombre.getText().equals("")){
                    ConsultaTrasModificación = operaciones.ModificarSegundoClick(Campo_Código.getText(), Campo_Nombre.getText(), 
                    Campo_IDLocalización.getText(), Campo_IDMánager.getText()); 
                    ÁreaTexto.setText("Modificación realizada en el departamento con código " + 
                    Campo_Código.getText() + " Nombre:" + Campo_Nombre.getText() + " Localización: " + Campo_IDLocalización.getText() + 
                    " Mánager: " + Campo_IDMánager.getText() + "\n\n" + ConsultaTrasModificación);
                } else ÁreaTexto.setText("Modificación abortada. Es necesario introducir un nombre.");
            } catch (SQLException ex) {
                ÁreaTexto.setText("Modificación abortada. Asegúrate de poner todos los datos.");
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Interfaz.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            // Se reestablece la variable centinela a su estado inicial
            PrimerClick = true;
            
            // Se cambia la etiqueta "SentenciaUtilizada" para mostrar la sentencia DML empleada
            SentenciaUtilizada.setText(operaciones.getSentenciaDML());  
        }        
    }//GEN-LAST:event_BotónModificarActionPerformed
    
    // Método que se ejecuta al pulsar el botón "Mostrar"
    private void Botón_MostrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Botón_MostrarActionPerformed
        PrimerClick=true;
        // Ejecuta el método "Mostrar e incorpora la tabla completa en "ÁreaTexto" y la sentencia empleada en "SentenciaUtilizada"
        try {
            ÁreaTexto.setText(operaciones.Mostrar(Campo_Código.getText(), Campo_Nombre.getText(), Campo_IDLocalización.getText(), 
                    Campo_IDMánager.getText()));
            SentenciaUtilizada.setText(operaciones.getSentenciaDQL());
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(Interfaz.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_Botón_MostrarActionPerformed

    // Método que se ejecuta al pulsar el botón "Insertar"
    private void Botón_InsertarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Botón_InsertarActionPerformed
        PrimerClick=true;
        // Se ejecuta la operación "Insertar" y, en función de la cadena devuelta, se escriben distintos mensajes en "ÁreaTexto"
        if (!Campo_Nombre.getText().equals("")){      
        try {
            String ConsultaTrasInserción = operaciones.Insertar(Campo_Código.getText(), Campo_Nombre.getText(), 
                    Campo_IDLocalización.getText(), Campo_IDMánager.getText());
            if (ConsultaTrasInserción.contains("Inserción no realizada")){
                ÁreaTexto.setText(ConsultaTrasInserción);
            } else {
                ÁreaTexto.setText("Registro insertado. Código: " + Campo_Código.getText() + " Nombre: " +
                    Campo_Nombre.getText() + " Localización: " + Campo_IDLocalización.getText() + " Mánager: "
                    + Campo_IDMánager.getText() + "\n\n" + ConsultaTrasInserción);
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(Interfaz.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // Se cambia la etiqueta "SentenciaUtilizada" para mostrar la sentencia DML empleada
        SentenciaUtilizada.setText(operaciones.getSentenciaDML());
        
        }else { ÁreaTexto.setText("Inserción no realizada. Introduce un nombre.");   
        }
    }//GEN-LAST:event_Botón_InsertarActionPerformed

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BotónBorrar;
    private javax.swing.JButton BotónModificar;
    private javax.swing.JButton Botón_Insertar;
    private javax.swing.JButton Botón_Mostrar;
    private javax.swing.JTextField Campo_Código;
    private javax.swing.JTextField Campo_IDLocalización;
    private javax.swing.JTextField Campo_IDMánager;
    private javax.swing.JTextField Campo_Nombre;
    private java.awt.Label SentenciaUtilizada;
    private javax.swing.JInternalFrame jInternalFrame1;
    private javax.swing.JScrollPane jScrollPane2;
    private java.awt.Label label1;
    private java.awt.Label label2;
    private java.awt.Label label3;
    private java.awt.Label label4;
    private java.awt.Label label6;
    private java.awt.Label label7;
    private java.awt.Label label8;
    private javax.swing.JTextArea ÁreaTexto;
    // End of variables declaration//GEN-END:variables
}
