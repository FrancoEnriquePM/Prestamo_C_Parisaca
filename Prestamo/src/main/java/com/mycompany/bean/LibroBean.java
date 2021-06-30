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
import java.sql.SQLDataException;
import java.sql.SQLException;
import javax.annotation.PreDestroy;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author hp
 */
public class LibroBean {
    //Atributos
    private VariablesConexion variable;
    private PreparedStatement insertLibro;
    private Connection connection;
    //constructores
    public LibroBean() throws SQLDataException, SQLException{
        variable = new VariablesConexion();
        variable.inicioConexion();
        connection=variable.getConnection();
        System.out.println("Iniciando la Conexion");            
    }
    @PreDestroy
    public void cerrarConexion(){
        variable.cerrarConexion();
    }
    //Metodos
    public String registrarLibro(HttpServletRequest request){
        String mensaje="";
        if (request == null) {
            return  "";
        }
        if(connection != null){
            try {
                //Defiendo la consulta
                StringBuilder query=new StringBuilder();
                query.append(" insert into libro ");
                query.append(" values (nextval('sec_libro'),?,?,?,?) ");
                //enviando la consulta
                if(insertLibro == null){
                    insertLibro = connection.prepareStatement(query.toString());
                }
                
                //Rescatando los parametros del formulario jsp registrar Categoria
                
                int autor = Integer.parseInt(request.getParameter("codAutor"));
                String titulo=request.getParameter("titulo");
                int edicion = Integer.parseInt(request.getParameter("edicion"));
                int nroejemplar = Integer.parseInt(request.getParameter("nroejemplar")) ;
                
                //pasando los datos a los parametros de la consulta
                
                insertLibro.setInt(1, autor);
                insertLibro.setString(2, titulo);
                insertLibro.setInt(3, edicion);
                insertLibro.setInt(4, nroejemplar);
                
                //ejecutando la consulta
                
                int registro = insertLibro.executeUpdate();
                if(registro == 1){
                    mensaje = "Registro realizado con exito..!";
                }else{
                    mensaje = "Error al insertar el registro";
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return mensaje;
    }

//Buscar todos los productos de una determinada categoria
    
    public String listarLibroAutor(String codAutor){
        StringBuilder salidaTabla = new StringBuilder();
        StringBuilder query = new StringBuilder();
        query.append(" SELECT l.titulo, l.edicion, l.nroejemplar, CONCAT(a.paterno,' ',a.materno,' ',a.nombre) ");
        query.append(" FROM libro l ");
        query.append(" INNER JOIN autor a ON l.cod_autor=a.cod_autor ");
        query.append(" WHERE l.cod_autor=? ");
        try {
            PreparedStatement pst=connection.prepareStatement(query.toString());
            //pasando a la consulta el parametro del codigo de categoria
            pst.setInt(1, Integer.parseInt(codAutor));
            ResultSet resultado=pst.executeQuery();
            while(resultado.next()){
                salidaTabla.append("<tr>");
                salidaTabla.append("<td>");
                salidaTabla.append(resultado.getString(1));
                salidaTabla.append("</td>");
                salidaTabla.append("<td>");
                salidaTabla.append(resultado.getString(2));
                salidaTabla.append("</td>");
                salidaTabla.append("<td>");
                salidaTabla.append(resultado.getInt(3));
                salidaTabla.append("</td>");
                salidaTabla.append("<td>");
                salidaTabla.append(resultado.getString(4));
                salidaTabla.append("</td>");
                salidaTabla.append("</tr>");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return salidaTabla.toString();
    }
    //Getter y setter
}
