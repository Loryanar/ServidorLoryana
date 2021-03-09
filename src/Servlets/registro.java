package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import Helpers.Condb;
import Helpers.Encriptamiento;
import Helpers.Queries;

/**
 * Servlet implementation class registro
 */
@WebServlet("/registro")
public class registro extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public registro() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Cookie[] cookies= request.getCookies();
		String p;
		PrintWriter out = response.getWriter();
		
		JSONObject data = new JSONObject(request.getReader().lines().collect(Collectors.joining(System.lineSeparator())));
		Queries db = new Queries();
		JSONObject mensaje = new JSONObject();
		String per =data.getString("us");
		for (Cookie c: cookies) {
			if(c.getName().equals("per")) {
				p=c.getValue();
				System.out.println(p);
			}
		}
		try {
			System.out.println("comenzamos");
				if(!db.VerificarUser(data.getString("email"))) {
					System.out.println("Usuario correcto");
					System.out.println(data.get("email"));
					boolean status = db.Registrar(data);
					if (status) {
						mensaje.put("status", 200).put("response", "El usuario fue creado");
					}else {
						mensaje.put("status", 409).put("response", "El usuario no fue creado");
						System.out.println("El usuario no fue creado");
					}
				}else {
					mensaje.put("status", 405).put("response", "este usuario ya existe");
				}
		}catch(SQLException e) {
			e.printStackTrace();
		} finally {
			db.closeResources();
		}
		out.println(mensaje.toString());
	}
}
