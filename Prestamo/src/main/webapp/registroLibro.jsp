<%-- 
    Document   : registroLibro
    Created on : 30-06-2021, 01:46:30 AM
    Author     : hp
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registrar Libro</title>
    </head>
    <body>
        <h1>Registrar Libro</h1>
        <jsp:useBean id="autorBean" scope="session" class="com.mycompany.bean.AutorBean"/>
        <jsp:useBean id="libroBean" scope="session" class="com.mycompany.bean.LibroBean"/>        
        <form method="POST">
            <table border="1">
                <thead>
                    <tr>
                        <th colspan="2">REGISTRAR CATEGORIA</th>                        
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>AUTOR: </td>
                        <td>
                            <select name="codAutor">
                                <%=autorBean.listarAutorSelect()%>
                            </select>                            
                        </td>
                    </tr>                    
                    <tr>
                        <td>TITULO: </td>
                        <td><input type="text" name="titulo" value="" /></td>
                    </tr>
                    <tr>
                        <td>EDICION: </td>
                        <td><input type="text" name="edicion" value="" /></td>
                    </tr>
                    <tr>
                        <td>NRO DE EJEMPLAR: </td>
                        <td><input type="text" name="nroejemplar" value="" /></td>
                    </tr>
                    <tr>
                        <td colspan="2" align="center"><input type="submit" value="REGISTRAR" name="guardar" /></td>
                    </tr>
                    <%
                        if(request.getParameter("guardar")!= null){
                            String mensaje=libroBean.registrarLibro(request);
                            out.print(mensaje);
                        }
                    %>
                </tbody>
            </table>
        </form>
        <a href="index.jsp">Menu Inicio</a>
    </body>
</html>
