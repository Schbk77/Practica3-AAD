<%@page import="hibernate.Inmuebles"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% 
    Inmuebles i = (Inmuebles)request.getAttribute("inmueble");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Inmobiliaria IZV - Subir Imagen</title>
        <link rel=stylesheet href="style.css" type="text/css">
    </head>
    <body>
        <div id="container">
            <header class="cabecera">
                <a href="index.html"><img class="logo" src="img/logo_inmoizv_blanco.png"/></a>
            </header>
            <div class="contenido">
                <h2 class="seccion">Subir Fotografía Inmueble</h2>
                <form action="controlsubir" method="post" enctype="multipart/form-data">
                    <input type="file" name="archivo" />
                    <input type="hidden" name="id" value="<%= i.getId()%>" />
                    <input type="submit" value="Subir" />
                </form>
            </div>
        </div>
    </body>
</html>
