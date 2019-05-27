package selvetpackage;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
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
 * Servlet implementation class signUp
 */
@WebServlet("/signUp/signUp")
public class signUp extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
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
		processrequest(request, response);
	}

	private void processrequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Map<String, String> errorMessage = new HashMap<>();
		try {
			request.setAttribute("ErrorMsg", errorMessage);
			request.setCharacterEncoding("UTF-8");
			int smid = Integer.valueOf(request.getParameter("mId"));
			String sformName = request.getParameter("formName");
			String stype = request.getParameter("formType");

			SimpleDateFormat DateFormate = new SimpleDateFormat("yyyyMMdd");
			Date date1 = DateFormate.parse(request.getParameter("esDate"));
			Date date2 = DateFormate.parse(request.getParameter("setupDate"));

			Timestamp sesDate = new Timestamp(date1.getTime());
			Timestamp sSetupDate = new Timestamp(date2.getTime());

			Double scapital = Double.valueOf(request.getParameter("capital"));
			String saddrs = request.getParameter("addrs");
			Boolean sisSet = "yes".equals(request.getParameter("isSet"));

			EmpBean EmpBeanSelectObject = new EmpBean(smid, sformName, stype, sesDate, sSetupDate, scapital, saddrs,
					sisSet);
			EmpDAO EmpDAOObject = new EmpDAO();
			int usernumber = EmpDAOObject.writeToSQL(EmpBeanSelectObject);

			EmpBeanSelectObject.setCompanyno(usernumber);
			request.setAttribute("EmpBeanCompany", EmpBeanSelectObject);

			RequestDispatcher rd = request.getRequestDispatcher("/signUp/signUpSuccess.jsp");
			rd.forward(request, response);
			return;
		} catch (SQLException e) {
			if (e.getMessage().indexOf("重複的索引鍵") != -1) {
				errorMessage.put("id", "帳號重複，請重新輸入帳號");
			} else {
				errorMessage.put("exception", "資料庫存取錯誤:" + e.getMessage());
			}
			RequestDispatcher rd = request.getRequestDispatcher("/signUp/signUpIndex.jsp");
			rd.forward(request, response);
		} catch (NumberFormatException e) {
			errorMessage.put("exception", "請輸入數字 : " + e.getMessage());
			RequestDispatcher rd = request.getRequestDispatcher("/signUp/signUpIndex.jsp");
			rd.forward(request, response);

		} catch (UnsupportedEncodingException e) {
			errorMessage.put("encode", "編碼錯誤 : " + e.getMessage());
			e.printStackTrace();
			RequestDispatcher rd = request.getRequestDispatcher("/signUp/signUpIndex.jsp");
			rd.forward(request, response);
		} catch (ParseException e) {
			errorMessage.put("date", "轉型錯誤 ，請檢查日期是否合法: " + e.getMessage());
			e.printStackTrace();
			RequestDispatcher rd = request.getRequestDispatcher("/signUp/signUpIndex.jsp");
			rd.forward(request, response);
		} catch (IOException e) {
			errorMessage.put("system", "系統錯誤 : " + e.getMessage());
			e.printStackTrace();
			RequestDispatcher rd = request.getRequestDispatcher("/signUp/signUpIndex.jsp");
			rd.forward(request, response);
		} catch (ServletException e) {
			errorMessage.put("system", "系統錯誤 : " + e.getMessage());
			e.printStackTrace();
			RequestDispatcher rd = request.getRequestDispatcher("/signUp/signUpIndex.jsp");
			rd.forward(request, response);
		}

	}

}
