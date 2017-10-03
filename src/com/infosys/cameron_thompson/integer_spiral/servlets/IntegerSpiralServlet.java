package com.infosys.cameron_thompson.integer_spiral.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.infosys.cameron_thompson.integer_spiral.SpiralService;

/**
 * Servlet implementation class IntegerSpiralServlet
 */
@WebServlet("/DoSpiral")
public class IntegerSpiralServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static SpiralService spiralWorker = new SpiralService();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IntegerSpiralServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		response.sendRedirect("/index.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String targetVal = request.getParameter("targetVal").trim();
		boolean isSpiralRight = Boolean.valueOf(request.getParameter("spiralDir"));
		//doGet(request, response);
		
		try {
			String spiral = spiralWorker.createSpiral(targetVal, isSpiralRight);
			request.setAttribute("spiral", spiral);
		} catch(IllegalArgumentException ex) {
			request.setAttribute("inputError", ex.getMessage());
			ex.printStackTrace();
		} catch(Exception ex) {
			request.setAttribute("error", String.format("%s: %s", ex.getClass().getName(), ex.getMessage()));
			ex.printStackTrace();
		}
		
		request.getRequestDispatcher("/").forward(request, response);
	}

}
