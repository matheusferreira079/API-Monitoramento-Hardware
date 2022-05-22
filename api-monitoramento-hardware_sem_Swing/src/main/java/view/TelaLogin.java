package view;

import controller.ControllerComputadores;
import controller.ControllerMonitoramento;
import controller.ControllerProcesso;
import controller.ControllerSlack;
import controller.ControllerUsuario;
import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import logger.Log;
import model.ModelUsuario;

/**
 *
 * @author Matheus Mattos
 */
public class TelaLogin extends javax.swing.JFrame {

    /**
     * Creates new form TelaLogin
     */
    public TelaLogin() {
        initComponents();
        //Colocando cor no fundo da página;
        getContentPane().setBackground(Color.decode("#080425"));

        // Variaveis para adicionar "favicon" na barra superior da página;
        URL caminhoImagem = this.getClass().getClassLoader().getResource("logo-casa.png");
        Image iconeTitulo = Toolkit.getDefaultToolkit().getImage(caminhoImagem);
        setIconImage(iconeTitulo);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jButtonAcessar = new javax.swing.JButton();
        jButtonSair = new javax.swing.JButton();
        jTextFieldUsuario = new javax.swing.JTextField();
        jPasswordFieldSenha = new javax.swing.JPasswordField();
        jLabelUsuario = new javax.swing.JLabel();
        jLabelSenha = new javax.swing.JLabel();
        jLabelLogo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(8, 4, 37));
        getContentPane().setLayout(null);

        jButtonAcessar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButtonAcessar.setText("Sign in");
        jButtonAcessar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAcessarActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonAcessar);
        jButtonAcessar.setBounds(340, 210, 120, 30);

        jButtonSair.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButtonSair.setText("Exit");
        jButtonSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSairActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonSair);
        jButtonSair.setBounds(470, 210, 110, 30);

        jTextFieldUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldUsuarioActionPerformed(evt);
            }
        });
        getContentPane().add(jTextFieldUsuario);
        jTextFieldUsuario.setBounds(340, 70, 240, 30);
        getContentPane().add(jPasswordFieldSenha);
        jPasswordFieldSenha.setBounds(340, 130, 240, 30);

        jLabelUsuario.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabelUsuario.setForeground(new java.awt.Color(255, 255, 255));
        jLabelUsuario.setText("E-mail Address");
        getContentPane().add(jLabelUsuario);
        jLabelUsuario.setBounds(340, 50, 110, 20);

        jLabelSenha.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabelSenha.setForeground(new java.awt.Color(255, 255, 255));
        jLabelSenha.setText("Password");
        getContentPane().add(jLabelSenha);
        jLabelSenha.setBounds(340, 110, 90, 20);

        jLabelLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/logo-onhome-branco.png"))); // NOI18N
        jLabelLogo.setRequestFocusEnabled(false);
        getContentPane().add(jLabelLogo);
        jLabelLogo.setBounds(20, 30, 450, 190);

        setSize(new java.awt.Dimension(634, 312));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonAcessarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAcessarActionPerformed
        Log log = new Log();
        log.createDirectory();
        log.createFile();
        TelaPrincipal telaPrincipal = new TelaPrincipal();
        ControllerUsuario validar = new ControllerUsuario();
        ModelUsuario usuario = new ModelUsuario();
        ControllerComputadores controllerComputadores = new ControllerComputadores();
        ControllerMonitoramento controllerMonitoramento = new ControllerMonitoramento();
        ControllerProcesso controllerProcessos = new ControllerProcesso();
        ControllerSlack controllerNotificacoes = new ControllerSlack();
        
        System.out.println("-------------------------------[ OnHome ]-------------------------------");
        log.append("-------------------------------[ OnHome ]-------------------------------");
        System.out.println("\nOlá ao terminal da aplicação da OnHome \\(^-^)/\n");
        log.append("\nOlá ao terminal da aplicação da OnHome \\(^-^)/\n");
        
        String email = jTextFieldUsuario.getText();
        String senha = new String(jPasswordFieldSenha.getPassword());

        if (validar.logar(email, senha)) {
            try {
                usuario.recuperar(email, senha);
                System.out.println(usuario.getNomeUsuario());
                System.out.println("---------------------------[ Banco de Dados ]---------------------------\n");
                log.append("---------------------------[ Banco de Dados ]---------------------------\n");
                System.out.println("Conectando ao Banco de Dados...\n");
                log.append("Conectando ao Banco de Dados...");
                System.out.println("Banco de Dados Conectado!\n");
                log.append("Banco de Dados Conectado!\n");
                controllerNotificacoes.enviarNotificacao(email, senha);
                controllerComputadores.insertComputador(email, senha);
                System.out.println("-----------------------------[ Descrição ]------------------------------\n");
                log.append("-----------------------------[ Descrição ]------------------------------\n");
                controllerMonitoramento.insertMonitoramento();
                controllerProcessos.insertProcesso();
                telaPrincipal.setVisible(true);
                dispose();
            } catch (UnknownHostException ex) {
                Logger.getLogger(TelaLogin.class.getName()).log(Level.SEVERE, null, ex);
                log.append(ex.getMessage());
            }

        } else {
            System.out.println("Senha e/ou usuário inválidos! (X_X)");
            log.append("Senha e/ou usuário inválidos! (X_X)");
            JOptionPane.showMessageDialog(rootPane, "Senha e/ou usuário inválidos! (X_X)");
        }
    }//GEN-LAST:event_jButtonAcessarActionPerformed

    private void jButtonSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSairActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_jButtonSairActionPerformed

    private void jTextFieldUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldUsuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldUsuarioActionPerformed

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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TelaLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaLogin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButtonAcessar;
    private javax.swing.JButton jButtonSair;
    private javax.swing.JLabel jLabelLogo;
    private javax.swing.JLabel jLabelSenha;
    private javax.swing.JLabel jLabelUsuario;
    private javax.swing.JPasswordField jPasswordFieldSenha;
    private javax.swing.JTextField jTextFieldUsuario;
    // End of variables declaration//GEN-END:variables
}
