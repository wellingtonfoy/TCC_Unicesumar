
package Esteganografia;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;

/**
 *
 * @author Wellington Marcolino
 */
public final class Visualizador extends javax.swing.JFrame {
    
    JFileChooser fs = new JFileChooser();
    Image imagenCargada;
    int control;
    
    /**
     * Criando Visual Do Formulario
     */
    public Visualizador() {
        initComponents();
        //setIconImage(new ImageIcon(getClass().getResource("/esteganografiafinal.UI/upload.png")).getImage());
        inicializar();
        
    }
    
    public void inicializar(){
        panelScrol.setVisible(false);        
        panelBarra.setVisible(false);
        botonAceptar.setVisible(false);
        botonCifrar.setVisible(false);
        botonDescifrar.setVisible(false);
        botonCargar.setVisible(false);
        botonGuardar.setVisible(false);
        areaTexto.setText("");
    }
    
    private void muestraBotones(){
        botonCifrar.setVisible(true);
        botonDescifrar.setVisible(true);
        botonCargar.setVisible(true);
        botonGuardar.setVisible(true);
        botonAceptar.setVisible(false);
    }
    
    private BufferedImage buscador(Image imagen, String mensaje) throws IOException{
        String mensajeCompleto = "##/#/#"+mensaje+"//##/#";
        BufferedImage imagenNova = (BufferedImage) imagen, novaddddd = new BufferedImage(imagenNova.getWidth(), imagenNova.getHeight(), BufferedImage.TYPE_INT_RGB);
        char[] charArreglo = mensajeCompleto.toCharArray();
        int charControl = 0, contador=0;
        ArrayList<String> novaImagen = new ArrayList<>(), almacenado = new ArrayList<>();
        barraProgreso.setMaximum(imagenNova.getHeight());
        barraProgreso.setStringPainted(true);
                      
        for(int i=0;i<imagenNova.getHeight();i++){ 
            for(int j=0;j<imagenNova.getWidth();j++){
                if(charControl<charArreglo.length){
                    almacenado.add(String.format("#%06X", (0xFFFFFF & imagenNova.getRGB(j, i))));
                    if(almacenado.size()==8){
                        novaImagen.addAll(convertidor(almacenado, charArreglo[charControl]));
                        charControl++;
                        almacenado.removeAll(almacenado);
                    }                    
                } else novaImagen.add(String.format("#%06X", (0xFFFFFF & imagenNova.getRGB(j, i))));
                
            }
            barraProgreso.setValue(i+1);
            panelBarra.update(panelBarra.getGraphics());
        }
        
        for(int i=0;i<imagenNova.getHeight();i++){ 
            for(int j=0;j<imagenNova.getWidth();j++){
                try {
                    novaddddd.setRGB(j, i, Color.decode(novaImagen.get(contador)).getRGB());
                    contador++; 
                } catch (Exception e) {System.out.println(e);}                
            }
        }
        botonImagen.setIcon(new ImageIcon(novaddddd.getScaledInstance(610, 360, Image.SCALE_DEFAULT)));
        return novaddddd;
    }
    
    @SuppressWarnings("empty-statement")
    private ArrayList<String> convertidor(ArrayList<String> hexColor, Character caracter){
        ArrayList<String> convertido = new ArrayList<>();
        char[] arr = Integer.toBinaryString(Integer.parseInt(Integer.toHexString(caracter), 16)).toCharArray();
        int diferencia = 8-arr.length;
        
        int charControl = 0;
        for (String color : hexColor) {
            String b = Integer.toBinaryString(Integer.parseInt(color.substring(5,7), 16));            
            String integrador = Integer.valueOf(Integer.parseInt(color.substring(5,7), 16))<16? "0":"";
            
            if(diferencia==0){
                String codigo = '#'+color.substring(1,5)+integrador+Integer.toHexString(Integer.parseInt(b.substring(0,b.length()-1)+arr[charControl], 2));
                convertido.add(codigo);                
                charControl++;
            } else{
                String codigo = '#'+color.substring(1,5)+integrador+Integer.toHexString(Integer.parseInt(b.substring(0,b.length()-1)+'0', 2));
                convertido.add(codigo);
                diferencia--;
            }            
        }
              
        return convertido;        
    }
     private String descifrador(BufferedImage imagenNova) {
         String mensaje = "";
         barraProgreso.setMaximum(imagenNova.getHeight());
         barraProgreso.setStringPainted(true);        
         
         ArrayList<String> almacenado = new ArrayList<>();
         for(int i=0;i<imagenNova.getHeight();i++){ 
            for(int j=0;j<imagenNova.getWidth();j++){
                almacenado.add(String.format("#%06X", (0xFFFFFF & imagenNova.getRGB(j, i))));
                if(almacenado.size()==8){
                    if(almacenado.size()==8){
                        String letra = "";
                        for (String string : almacenado) {
                            String string2 = Integer.toBinaryString(Integer.parseInt(string.substring(5,7), 16));
                            letra+=string2.substring(string2.length()-1, string2.length());
                        }
                        mensaje+=(char) Integer.parseInt(letra,2);
                        almacenado.removeAll(almacenado);
                    }                    
                }                
            }
            barraProgreso.setValue(i+1);
            panelBarra.update(panelBarra.getGraphics());
            
        }
        int buscador = 0;
        String salida = "//##/#";
        for (int i = 0; i < mensaje.length(); i++) {
            buscador = mensaje.charAt(i)==salida.charAt(buscador)? buscador+=1:0;            
            if (buscador==6) return mensaje.substring(6, i-5);            
        }
        return "No existe mensaje";
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        botonGuardar = new javax.swing.JButton();
        panelBarra = new javax.swing.JPanel();
        barraProgreso = new javax.swing.JProgressBar();
        botonAceptar = new javax.swing.JButton();
        botonDescifrar = new javax.swing.JButton();
        botonCargar = new javax.swing.JButton();
        botonCifrar = new javax.swing.JButton();
        panelScrol = new javax.swing.JScrollPane();
        areaTexto = new javax.swing.JTextArea();
        labelInicio = new javax.swing.JLabel();
        botonImagen = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        labelFondo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setIconImages(null);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        botonGuardar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        botonGuardar.setForeground(new java.awt.Color(255, 255, 255));
        botonGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/Salvar.png"))); // NOI18N
        botonGuardar.setText("Salvar Imagem");
        botonGuardar.setContentAreaFilled(false);
        botonGuardar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botonGuardar.setPreferredSize(new java.awt.Dimension(115, 30));
        botonGuardar.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        botonGuardar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        botonGuardar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                botonGuardarMousePressed(evt);
            }
        });
        botonGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonGuardarActionPerformed(evt);
            }
        });
        getContentPane().add(botonGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 400, 130, 120));

        panelBarra.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        barraProgreso.setBackground(new java.awt.Color(51, 51, 51));
        barraProgreso.setForeground(new java.awt.Color(255, 0, 0));
        barraProgreso.setPreferredSize(new java.awt.Dimension(146, 23));
        panelBarra.add(barraProgreso, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 530, 30));

        getContentPane().add(panelBarra, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 570, 530, 30));

        botonAceptar.setText("APLICAR");
        botonAceptar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                botonAceptarMousePressed(evt);
            }
        });
        getContentPane().add(botonAceptar, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 530, -1, -1));

        botonDescifrar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        botonDescifrar.setForeground(new java.awt.Color(255, 255, 255));
        botonDescifrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/Extrair.png"))); // NOI18N
        botonDescifrar.setText("Extrair Texto");
        botonDescifrar.setContentAreaFilled(false);
        botonDescifrar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botonDescifrar.setPreferredSize(new java.awt.Dimension(115, 30));
        botonDescifrar.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        botonDescifrar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        botonDescifrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                botonDescifrarMousePressed(evt);
            }
        });
        getContentPane().add(botonDescifrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 400, 150, 120));

        botonCargar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        botonCargar.setForeground(new java.awt.Color(255, 255, 255));
        botonCargar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/descifrar.png"))); // NOI18N
        botonCargar.setText("Carregar Imagem");
        botonCargar.setContentAreaFilled(false);
        botonCargar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botonCargar.setPreferredSize(new java.awt.Dimension(115, 30));
        botonCargar.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        botonCargar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        botonCargar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                botonCargarMousePressed(evt);
            }
        });
        getContentPane().add(botonCargar, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 400, 140, 120));

        botonCifrar.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        botonCifrar.setForeground(new java.awt.Color(255, 255, 255));
        botonCifrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/Inserir.png"))); // NOI18N
        botonCifrar.setText("Inserir Texto");
        botonCifrar.setContentAreaFilled(false);
        botonCifrar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botonCifrar.setPreferredSize(new java.awt.Dimension(115, 30));
        botonCifrar.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        botonCifrar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        botonCifrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                botonCifrarMousePressed(evt);
            }
        });
        getContentPane().add(botonCifrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 400, 130, 120));

        areaTexto.setColumns(20);
        areaTexto.setForeground(new java.awt.Color(102, 0, 0));
        areaTexto.setRows(5);
        panelScrol.setViewportView(areaTexto);

        getContentPane().add(panelScrol, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 400, 610, 120));

        labelInicio.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        labelInicio.setForeground(new java.awt.Color(153, 102, 0));
        labelInicio.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        getContentPane().add(labelInicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 320, 610, 100));

        botonImagen.setBackground(new java.awt.Color(153, 0, 0));
        botonImagen.setFont(new java.awt.Font("Sitka Heading", 1, 14)); // NOI18N
        botonImagen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/Entrar.jpg"))); // NOI18N
        botonImagen.setText("Cargar Imágen");
        botonImagen.setFocusPainted(false);
        botonImagen.setFocusable(false);
        botonImagen.setRequestFocusEnabled(false);
        botonImagen.setRolloverEnabled(false);
        botonImagen.setVerifyInputWhenFocusTarget(false);
        botonImagen.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                botonImagenMousePressed(evt);
            }
        });
        botonImagen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonImagenActionPerformed(evt);
            }
        });
        getContentPane().add(botonImagen, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 150, 410, 200));

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("ENGENHARIA DE SOFTWARE");
        jLabel2.setToolTipText("");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 550, 170, 20));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("APLICADA A IMAGENS ");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 60, 210, 70));

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("ESTEGANOGRAFIA ");
        jLabel3.setToolTipText("");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 30, 370, 60));

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("WELLINGTON DOS SANTOS MARCOLINO");
        jLabel4.setToolTipText("");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 530, 250, 30));

        labelFondo.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        labelFondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/fundo.png"))); // NOI18N
        getContentPane().add(labelFondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(-220, -30, 920, 670));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonCifrarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonCifrarMousePressed
        inicializar();
        control=1;
        panelScrol.setVisible(true);
        botonAceptar.setVisible(true);        
    }//GEN-LAST:event_botonCifrarMousePressed

    private void botonDescifrarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonDescifrarMousePressed
        inicializar();
        control=2;
        panelScrol.setVisible(true);
        panelScrol.setVisible(true);
        areaTexto.setEditable(false);
        botonAceptar.setVisible(true);
        areaTexto.setText("Mensaje: "+descifrador((BufferedImage) imagenCargada));
    }//GEN-LAST:event_botonDescifrarMousePressed

    private void botonImagenMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonImagenMousePressed
        fs.showOpenDialog(this);
        if (fs.getSelectedFile()==null) return;
        File file = fs.getSelectedFile();
        System.out.println(file);
        try {
            imagenCargada = ImageIO.read(file);
            botonImagen.setIcon(new ImageIcon(imagenCargada.getScaledInstance(610, 360, Image.SCALE_DEFAULT)));
            labelInicio.setVisible(false);
            botonImagen.setText("");
            muestraBotones();
        } catch (IOException ex) {
            Logger.getLogger(Visualizador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_botonImagenMousePressed

    private void botonAceptarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonAceptarMousePressed
        if (control==1) {
            try {
                imagenCargada = buscador(imagenCargada, areaTexto.getText());
                panelScrol.setVisible(false);
            } catch (IOException ex) {
                Logger.getLogger(Visualizador.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else inicializar();
        
        muestraBotones();
        control=0;        
    }//GEN-LAST:event_botonAceptarMousePressed

    private void botonCargarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonCargarMousePressed
        botonImagenMousePressed(evt);
    }//GEN-LAST:event_botonCargarMousePressed

    private void botonGuardarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonGuardarMousePressed
        fs.showSaveDialog(this);
        if (fs.getSelectedFile()==null) return;
        System.out.println(fs.getSelectedFile());
        try {
            ImageIO.write((RenderedImage) imagenCargada, "png", new File(fs.getSelectedFile()+".png"));
        } catch (IOException ex) {
            Logger.getLogger(Visualizador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_botonGuardarMousePressed

    private void botonImagenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonImagenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_botonImagenActionPerformed

    private void botonGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonGuardarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_botonGuardarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Visualizador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Visualizador().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea areaTexto;
    private javax.swing.JProgressBar barraProgreso;
    private javax.swing.JButton botonAceptar;
    private javax.swing.JButton botonCargar;
    private javax.swing.JButton botonCifrar;
    private javax.swing.JButton botonDescifrar;
    private javax.swing.JButton botonGuardar;
    private javax.swing.JButton botonImagen;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel labelFondo;
    private javax.swing.JLabel labelInicio;
    private javax.swing.JPanel panelBarra;
    private javax.swing.JScrollPane panelScrol;
    // End of variables declaration//GEN-END:variables

}
