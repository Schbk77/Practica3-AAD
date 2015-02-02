<%@page import="java.io.PrintWriter"%>
<%@page import="hibernate.FotosInmueble"%>
<%@page import="hibernate.Inmuebles"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Inmobiliaria IZV - Ver Inmuebles</title>
        <link rel=stylesheet href="style.css" type="text/css">
    </head>
    <body>
        <div id="container">
            <header class="cabecera">
                <a href="index.html"><img class="logo" src="img/logo_inmoizv_blanco.png"/></a>
            </header>
            <div class="contenido">
                <h2 class="seccion">Lista de inmuebles</h2>
                <div class="datagrid">
                    <table border="1">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Localidad</th>
                                <th>Direccion</th>
                                <th>Tipo</th>
                                <th>Precio</th>
                                <th>Fotos</th>
                            </tr>
                        </thead>
                        <tbody>
                            <%
                                List<Inmuebles> lista = (List<Inmuebles>) request.getAttribute("datos");
                                List<FotosInmueble> listaFotos = (List<FotosInmueble>) request.getAttribute("fotos");
                                for (Inmuebles i : lista) {
                            %>
                            <tr>
                                <td><%= i.getId()%></td>
                                <td><%= i.getLocalidad()%></td>
                                <td><%= i.getDireccion()%></td>
                                <td><%= i.getTipo()%></td>
                                <td><%= i.getPrecio()%> &euro;</td>
                                <td>
                                    <%
                                        if (!listaFotos.isEmpty()) {
                                            for (int v = 0; v < listaFotos.size(); v++) {
                                                String ruta = listaFotos.get(v).getFoto(i.getId());
                                                %><a href="<%= ruta%>"><img src="<%= ruta%>" class="fotoinmueble"/><br/></a><%
                                                        }
                                                    }
                                        %>
                                </td>
                                <td><a href="control?target=inmueble&op=delete&action=op&id=<%= i.getId()%>"><img src="img/delete.png" width="30px"/></a></td>
                                <td><a href="control?target=inmueble&op=edit&action=view&id=<%= i.getId()%>"><img src="img/edit.png" width="30px"/></a></td>
                                <td><a href="control?target=inmueble&op=upload&action=view&id=<%= i.getId()%>"><img src="img/upload.png" width="30px"/></a></td>
                            </tr>
                            <%
                                }
                            %>

                        </tbody>
                    </table>
                    <a class="insertar" href="control?target=inmueble&amp;op=insert&amp;action=view">Insertar Inmuebles</a>
                </div>
            </div>
        </div>
    </body>
</html>
