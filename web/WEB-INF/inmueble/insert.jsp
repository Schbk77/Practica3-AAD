<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Inmobiliaria IZV - Insertar Inmueble</title>
        <link rel=stylesheet href="style.css" type="text/css">
    </head>
    <body>
        <div id="container">
            <header class="cabecera">
                <a href="index.html"><img class="logo" src="img/logo_inmoizv_blanco.png"/></a>
            </header>
            <div class="contenido">
                <h2 class="seccion">Insertar Inmueble</h2>
                <form action="control" method="POST">
                    <label for=localidad">Localidad</label>
                    <input type="text" name="localidad" value="" /><br/>
                    <label for="direccion">Dirección</label>
                    <input type="text" name="direccion" value="" /><br/>
                    <label for="tipo">Tipo</label>
                    <input type="text" name="tipo" value="" /><br/>
                    <label for="precio">Precio</label>
                    <input type="number" name="precio" value="" /><br/>
                    <input type="hidden" name="target" value="inmueble" />
                    <input type="hidden" name="op" value="insert" />
                    <input type="hidden" name="action" value="op" />
                    <input type="submit" value="Insertar" />
                </form>
            </div>
        </div>
    </body>
</html>
