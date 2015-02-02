<%@page import="hibernate.Inmuebles"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% 
    Inmuebles i = (Inmuebles)request.getAttribute("inmueble");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Inmobiliaria IZV - Editar Inmueble</title>
        <link rel=stylesheet href="style.css" type="text/css">
    </head>
    <body>
        <div id="container">
            <header class="cabecera">
                <a href="index.html"><img class="logo" src="img/logo_inmoizv_blanco.png"/></a>
            </header>
            <div class="contenido">
                <h2 class="seccion">Editar Inmueble</h2>
                <form action="control" method="POST">
                    Localidad <input type="text" name="localidad" value="<%= i.getLocalidad()%>" /><br/>
                    Dirección <input type="text" name="direccion" value="<%= i.getDireccion()%>" /><br/>
                    Tipo <input type="text" name="tipo" value="<%= i.getTipo()%>" /><br/>
                    Precio <input type="number" name="precio" value="<%= i.getPrecio()%>" /><br/>
                    <input type="hidden" name="target" value="inmueble" />
                    <input type="hidden" name="op" value="edit" />
                    <input type="hidden" name="action" value="op" />
                    <input type="hidden" name="id" value="<%= i.getId()%>" />
                    <input type="submit" value="Modificar" />
                </form>
            </div>
        </div>
    </body>
</html>
