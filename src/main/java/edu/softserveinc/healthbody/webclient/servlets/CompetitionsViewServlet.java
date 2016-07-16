package edu.softserveinc.healthbody.webclient.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.softserveinc.healthbody.webclient.utils.RequestParamUtils;

/**
 * Servlet implementation class CompetitionsViewServlet
 */
@WebServlet("/competitions")
public class CompetitionsViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CompetitionsViewServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		int partNumber = getPartNumber(request);
		int partSize = getPartSize(request);
}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private void writeResponse(Object object, HttpServletResponse response)
			throws IOException {
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		out.print(object);
		out.flush();

	}


	private int getPartNumber(HttpServletRequest request) {
		String partNumberString = request.getParameter("partNumber");
		if (RequestParamUtils.isEmptyParam(partNumberString)) {
			return 1;
		}
		return RequestParamUtils.getIntegerParam(partNumberString);
	}

	private int getPartSize(HttpServletRequest request) {
		String partSizeString = request.getParameter("partSize");
		if (RequestParamUtils.isEmptyParam(partSizeString)) {
			return 5;
		}
		return RequestParamUtils.getIntegerParam(partSizeString);
	}

}
