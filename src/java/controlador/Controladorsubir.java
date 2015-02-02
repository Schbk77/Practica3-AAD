package controlador;

import hibernate.FotosInmueble;
import hibernate.Inmuebles;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import modelo.ModeloFotosInmueble;
import modelo.ModeloInmuebles;

@WebServlet(name = "Controladorsubir", urlPatterns = {"/controlsubir"})
@MultipartConfig
public class Controladorsubir extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        boolean error = false;
        String id = request.getParameter("id");
        Part filePart = request.getPart("archivo");
        String fileName = filePart.getSubmittedFileName();
        response.setContentType("text/html;charset=UTF-8");
        //response.setContentType("application/json;charset=UTF-8");
        InputStream fileContent = filePart.getInputStream();
        String destino = getServletContext().getRealPath("/") + "fotosinmuebles/" + fileName;
        String destinoBd = "fotosinmuebles/" + fileName;
        FotosInmueble foto = new FotosInmueble();
        foto.setInmuebles(ModeloInmuebles.get(id));
        foto.setFoto(destinoBd);
        ModeloFotosInmueble.insert(foto);
        try {
            OutputStream os = new FileOutputStream(destino);
            byte[] b = new byte[2048];
            int length;
            try {
                while ((length = fileContent.read(b)) != -1) {
                    os.write(b, 0, length);
                }
                fileContent.close();
            } catch (Exception e) {
                error = true;
            } finally {
                os.close();
            }
        } catch (Exception e) {
            error = true;
        }

        /*try (PrintWriter out = response.getWriter()) {
         if (error) {
         out.println("{\"r\":0)}");
         } else {
         out.println("{\"r\":1)}");
         }
         }*/
        response.sendRedirect("control?target=inmueble&op=select&action=view");
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
