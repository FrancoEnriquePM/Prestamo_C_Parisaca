<%-- 
    Document   : buscarLibroAutor
    Created on : 30-06-2021, 12:24:36 AM
    Author     : hp
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Buscar Libro - Autor</title>
        <%! String salidaTabla = "";%>
    </head>
    <body>
        <h1>Buscar Libro - Autor</h1>
        <jsp:useBean id="autorBean" scope="session" class="com.mycompany.bean.AutorBean"/>
        <jsp:useBean id="libroBean" scope="session" class="com.mycompany.bean.LibroBean"/>
        <%
            if(request.getParameter("buscar")!=null){
                String codAutor = request.getParameter("codAutor");
                //llamando al metodo de busqueda de productos de una determinada categoria
                salidaTabla=libroBean.listarLibroAutor(codAutor);
            }
        %>
        <form method="POST">
            <table border="1">
                <thead>
                    <tr>
                        <th colspan="2">AUTOR</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>
                            <select name="codAutor">
                                <%=autorBean.listarAutorSelect()%>
                            </select>
                        </td>
                        <td>
                            <input type="submit" value="BUSCAR" name="buscar" />
                        </td>
                    </tr>
                </tbody>
            </table>
            <br>
            <table border="1">
                <thead>
                    <tr>
                        <th>TITULO</th>
                        <th>EDICION</th>
                        <th>NRO DE EJEMPLAR</th>
                        <th>AUTOR</th>
                    </tr>
                </thead>
                <tbody>
                    <%=salidaTabla%>
                </tbody>
            </table>
        </form>
        <a href="index.jsp">Menu Inicio</a>
    </body>
</html>
