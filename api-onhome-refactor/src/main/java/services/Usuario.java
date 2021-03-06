/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import config.Connection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import utils.Log;

/**
 *
 * @author Matheus Mattos
 */
public class Usuario {

    private Integer idUsuario;
    private String nomeUsuario;
    private String emailUser;
    private String senhaUser;
    private String webhook;
    private Integer fkEmpresa;
    private Integer fkPermissao;
    private Integer fkEspecialidade;

    Connection config = new Connection();
    JdbcTemplate connect = new JdbcTemplate(config.getDataSource());

    public Boolean logar(String email, String senha) {
        Log log = new Log();
        System.setOut(log);
        System.setErr(log);
        Boolean valida = false;

        try {

            List<Usuario> usuario = connect.query("SELECT * FROM Usuario WHERE emailUser = ? AND senhaUser = ?",
                    new BeanPropertyRowMapper<>(Usuario.class), email, senha);
            
            for (Usuario user : usuario) {
                idUsuario = user.getIdUsuario();
                nomeUsuario = user.getNomeUsuario();
                emailUser = user.getEmailUser();
                fkEmpresa = user.getFkEmpresa();
                fkPermissao = user.getFkPermissao();
                fkEspecialidade = user.getFkEspecialidade();
            }
                       
            if (!usuario.isEmpty()) {
                valida = true;
            }

        } catch (Exception e) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, e);
        }

        return valida;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getEmailUser() {
        return emailUser;
    }

    public void setEmailUser(String emailUser) {
        this.emailUser = emailUser;
    }

    public String getSenhaUser() {
        return senhaUser;
    }

    public void setSenhaUser(String senhaUser) {
        this.senhaUser = senhaUser;
    }

    public String getWebhook() {
        return webhook;
    }

    public void setWebhook(String webhook) {
        this.webhook = webhook;
    }
    
    public Integer getFkEmpresa() {
        return fkEmpresa;
    }

    public void setFkEmpresa(Integer fkEmpresa) {
        this.fkEmpresa = fkEmpresa;
    }

    public Integer getFkPermissao() {
        return fkPermissao;
    }

    public void setFkPermissao(Integer fkPermissao) {
        this.fkPermissao = fkPermissao;
    }

    public Integer getFkEspecialidade() {
        return fkEspecialidade;
    }

    public void setFkEspecialidade(Integer fkEspecialidade) {
        this.fkEspecialidade = fkEspecialidade;
    }

    @Override
    public String toString() {
        return "\nidUsuario=" + idUsuario + ", \nnomeUsuario=" + nomeUsuario + ", \nemailUser=" + emailUser + ", \nsenhaUser=" + senhaUser + ", \nfkEmpresa=" + fkEmpresa + ", \nfkPermissao=" + fkPermissao + ", \nfkEspecialidade=" + fkEspecialidade + '}';
    }

}
