package controlador;

import hibernate.Inmuebles;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.ModeloInmuebles;
import modelo.ModeloFotosInmueble;

@WebServlet(name = "Controlador", urlPatterns = {"/control"})
public class Controlador extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String destino = "index.html";          // A donde transfiero el control
            boolean forward = false;                // De que manera transfiero el control
            String op, target, action;
            boolean noredirect = false;

            // Decidir destino
            target = request.getParameter("target");
            op = request.getParameter("op");
            action = request.getParameter("action");

            if (target.equals("inmueble") && op.equals("select") && action.equals("view")) {
                // VER TABLA INMUEBLES
                forward = true;
                destino = "WEB-INF/inmueble/index.jsp";
                request.setAttribute("datos", ModeloInmuebles.get());
                request.setAttribute("fotos", ModeloFotosInmueble.get());
            } else if (target.equals("inmueble") && op.equals("insert") && action.equals("view")) {
                // FORMULARIO INSERTAR
                forward = true;
                destino = "WEB-INF/inmueble/insert.jsp";
            } else if (target.equals("inmueble") && op.equals("insert") && action.equals("op")) {
                // INSERTAR INMUEBLE
                forward = false;
                destino = "control?target=inmueble&op=select&action=view";
                Inmuebles inmueble = new Inmuebles();
                inmueble.setLocalidad(request.getParameter("localidad"));
                inmueble.setDireccion(request.getParameter("direccion"));
                inmueble.setTipo(request.getParameter("tipo"));
                inmueble.setPrecio(Integer.valueOf(request.getParameter("precio")));
                if(!request.getParameter("usuario").equals("null")){
                    inmueble.setUsuario(request.getParameter("usuario"));
                }
                ModeloInmuebles.insert(inmueble);
            } else if (target.equals("inmueble") && op.equals("insert") && action.equals("opa")) {
                // INSERTAR INMUEBLE DESDE ANDROID
                noredirect=true;
                Inmuebles inmueble = new Inmuebles();
                inmueble.setLocalidad(request.getParameter("localidad"));
                inmueble.setDireccion(request.getParameter("direccion"));
                inmueble.setTipo(request.getParameter("tipo"));
                inmueble.setPrecio(Integer.valueOf(request.getParameter("precio")));
                if(!request.getParameter("usuario").equals("null")){
                    inmueble.setUsuario(request.getParameter("usuario"));
                }
                long id = ModeloInmuebles.insert(inmueble);
                try (PrintWriter respuesta = response.getWriter()) {
                    out.println(id);
                }   
            }else if (target.equals("inmueble") && op.equals("delete") && action.equals("op")) {
                // BORRAR DE TABLA
                forward = false;
                destino = "control?target=inmueble&op=select&action=view";
                ModeloInmuebles.delete(request.getParameter("id"));
                ModeloFotosInmueble.deleteAll(request.getParameter("id"));
            } else if (target.equals("inmueble") && op.equals("edit") && action.equals("view")) {
                // MOSTRAR DATOS EDITAR
                forward = true;
                destino = "WEB-INF/inmueble/edit.jsp";
                request.setAttribute("inmueble", ModeloInmuebles.get(request.getParameter("id")));
            } else if (target.equals("inmueble") && op.equals("edit") && action.equals("op")) {
                // EDITAR
                forward = false;
                destino = "control?target=inmueble&op=select&action=view";
                Inmuebles inmueble = new Inmuebles();
                inmueble.setId(Integer.parseInt(request.getParameter("id")));
                inmueble.setLocalidad(request.getParameter("localidad"));
                inmueble.setDireccion(request.getParameter("direccion"));
                inmueble.setTipo(request.getParameter("tipo"));
                inmueble.setPrecio(Integer.parseInt(request.getParameter("precio")));
                ModeloInmuebles.edit(inmueble);
            } else if (target.equals("inmueble") && op.equals("upload") && action.equals("view")) {
                // VER FORMULARIO SUBIDA
                forward = true;
                destino = "WEB-INF/inmueble/upload.jsp";
                request.setAttribute("inmueble", ModeloInmuebles.get(request.getParameter("id")));
            }

            if(!noredirect){
                // Transferir el control
                if (forward) {
                    request.getRequestDispatcher(destino).forward(request, response);
                } else {
                    response.sendRedirect(destino);
                }  
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
