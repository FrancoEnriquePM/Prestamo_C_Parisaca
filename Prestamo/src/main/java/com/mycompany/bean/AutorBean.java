/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bean;

import com.mycompany.conexion.VariablesConexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.annotation.PreDestroy;

/**
 *
 * @author hp
 */
public class AutorBean {
    //Atributos
    private Connection connection;
    private PreparedStatement insertRol;
    private VariablesConexion variable;
    //Constructores
    public AutorBean() throws SQLException{
        //Instanciando
        variable = new VariablesConexion();
        variable.inicioConexion();
        //Obteniendo la conexion
        connection = variable.getConnection();
        System.out.println("Iniciando la conexion");
    }    
    //Metodos
    @PreDestroy
    public void cerrarConexion(){
        variable.cerrarConexion();
    }  
    public String listarAutorSelect(){
        StringBuilder salidaTabla = new StringBuilder();
        StringBuilder query = new StringBuilder();
        query.append(" SELECT a.cod_autor, a.paterno, a.materno, a.nombre ");
        query.append(" FROM autor a ");
        try {
            PreparedStatement pst = connection.prepareCall(query.toString());
            ResultSet resultado = pst.executeQuery();
            while (resultado.next()) {                
                salidaTabla.append("<option value='");
                salidaTabla.append(resultado.getInt(1));
                salidaTabla.append("'>");
                salidaTabla.append(resultado.getString(2)+ " ");
                salidaTabla.append(resultado.getString(3)+ " ");
                salidaTabla.append(resultado.getString(4));
                salidaTabla.append("</option>");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return salidaTabla.toString();
    }
    //getter y setter
}
