package selvetpackage;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import midproject2.EmpBean;
import midproject2.EmpDAO;

/**
 * Servlet implementation class delete
 */
@WebServlet("/signUp/delete")
public class delete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processrequest(request,response);
		
	}
	private void processrequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, String> errorMessage = new HashMap<>();
		request.setAttribute("ErrorMsg", errorMessage);
		try {
			request.setCharacterEncoding("UTF-8");
			int idbox = Integer.valueOf(request.getParameter("companyid2"));
			EmpDAO EmpDAOObject = new EmpDAO();
			int count = EmpDAOObject.delete(idbox);
			request.setAttribute("count", count);
			RequestDispatcher rd = request.getRequestDispatcher("/signUp/signUpSuccess.jsp");
			rd.forward(request, response);
			return;
		} catch (UnsupportedEncodingException e) {
			errorMessage.put("encode", "編碼錯誤 : " +e.getMessage());
			RequestDispatcher rd = request.getRequestDispatcher("/signUp/signUpIndex.jsp");
			rd.forward(request, response);
		} catch (ServletException e) {
			errorMessage.put("system", "系統錯誤 : " +e.getMessage());
			RequestDispatcher rd = request.getRequestDispatcher("/signUp/signUpIndex.jsp");
			rd.forward(request, response);
		} catch (IOException e) {
			errorMessage.put("system", "系統錯誤 : " +e.getMessage());
			RequestDispatcher rd = request.getRequestDispatcher("/signUp/signUpIndex.jsp");
			rd.forward(request, response);
		}
	}
}
