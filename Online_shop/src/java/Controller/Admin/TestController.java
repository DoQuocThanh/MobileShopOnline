/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller.Admin;

import jakarta.servlet.ServletContext;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.RequestContext;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

@MultipartConfig

public class TestController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private static final String UPLOAD_DIR = "uploads";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.getRequestDispatcher("Test.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse responds) throws ServletException, IOException {
        // Create a factory for disk-based file items

        processRequest(request, responds);
        processRequest2(request, responds);

    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            //fetch form data

            Part filePart = request.getPart("file");

            String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();

            // Set the destination directory for the uploaded file
            String uploadPath = "E:\\ShopOnline\\Project-OnlineShop\\Online_shop\\web\\img";
            
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            // Copy the file to the destination directory
            InputStream inputStream = filePart.getInputStream();
            Files.copy(inputStream, Paths.get(uploadPath + File.separator + fileName), StandardCopyOption.REPLACE_EXISTING);

            System.err.println(uploadPath + File.separator + fileName);
            // Show a message to the user
            response.getWriter().println(fileName);

        }
    }
    protected void processRequest2(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            //fetch form data

            Part filePart = request.getPart("file");

            String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();

            // Set the destination directory for the uploaded file
           
            String uploadPath2 = "E:\\ShopOnline\\Project-OnlineShop\\Online_shop\\build\\web\\img";
            File uploadDir = new File(uploadPath2);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            // Copy the file to the destination directory
            InputStream inputStream = filePart.getInputStream();
            Files.copy(inputStream, Paths.get(uploadPath2 + File.separator + fileName), StandardCopyOption.REPLACE_EXISTING);

            System.err.println(uploadPath2 + File.separator + fileName);
            // Show a message to the user
            response.getWriter().println(fileName);

        }
    }

    public boolean uploadFile(InputStream is, String path) {
        boolean test = false;
        try {
            byte[] byt = new byte[is.available()];
            is.read();

            FileOutputStream fops = new FileOutputStream(path);
            fops.write(byt);
            fops.flush();
            fops.close();

            test = true;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return test;
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    // </editor-fold>
}
