package selvetpackage;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import midproject2.EmpDAO;

/**
 * Servlet implementation class queryall
 * /signUp
 */
@WebServlet("/signUp/queryall")
public class queryall extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String type = request.getParameter("companyid3");
		String value = request.getParameter("query").trim();
		EmpDAO EmpDAOObject = new EmpDAO();
		StringBuffer a = EmpDAOObject.selectLike(type, value);
		String[] s = a.toString().split("@");
		request.setAttribute("result", s[0]);
		request.setAttribute("querycount", s[1]);
		request.setAttribute("type", type);
		request.setAttribute("value", value);
		request.getRequestDispatcher("/signUp/signUpIndex.jsp").forward(request, response);
		return;
	}

}
