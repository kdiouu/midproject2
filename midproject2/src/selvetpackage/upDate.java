package selvetpackage;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
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
 * Servlet implementation class upDate
 */
@WebServlet("/signUp/upDate")
public class upDate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processrequest(request,response);
	}
	private void processrequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, String> errorMessage = new HashMap<>();
		try {
			request.setCharacterEncoding("UTF-8");
			int idbox = Integer.valueOf(request.getParameter("companyid2"));
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
			
			EmpDAO EmpDAOObject = new EmpDAO();
			EmpBean EmpBeanResultObject = EmpDAOObject.selectone(idbox);
			request.setAttribute("OldEmpBeanCompany", EmpBeanResultObject);
			
			EmpBean EmpBeanUpDateObject = new EmpBean(idbox,smid,sformName,stype,sesDate,sSetupDate,scapital,saddrs,sisSet);
			EmpBean EmpBeanUpDateResultObject = EmpDAOObject.upDate(EmpBeanUpDateObject);
			request.setAttribute("NewEmpBeanCompany", EmpBeanUpDateResultObject);

			RequestDispatcher rd = request.getRequestDispatcher("/signUp/signUpSuccess.jsp");
			rd.forward(request, response);
			return;
			
		} catch (UnsupportedEncodingException e) {
			errorMessage.put("encode", "編碼錯誤 : " +e.getMessage());
			e.printStackTrace();
			RequestDispatcher rd = request.getRequestDispatcher("/signUp/signUpIndex.jsp");
			rd.forward(request, response);
		} catch (ParseException e) {
			errorMessage.put("date", "轉型錯誤 ，請檢查日期是否合法: " +e.getMessage());
			e.printStackTrace();
			RequestDispatcher rd = request.getRequestDispatcher("/signUp/signUpIndex.jsp");
			rd.forward(request, response);
		} catch (ServletException e) {
			errorMessage.put("system", "系統錯誤 : " +e.getMessage());
			e.printStackTrace();
			RequestDispatcher rd = request.getRequestDispatcher("/signUp/signUpIndex.jsp");
			rd.forward(request, response);
		} catch (IOException e) {
			errorMessage.put("system", "系統錯誤 : " +e.getMessage());
			e.printStackTrace();
			RequestDispatcher rd = request.getRequestDispatcher("/signUp/signUpIndex.jsp");
			rd.forward(request, response);
		} catch (NumberFormatException e) {
			errorMessage.put("system", "數值錯誤，請檢查數字 : " +e.getMessage());
			e.printStackTrace();
			RequestDispatcher rd = request.getRequestDispatcher("/signUp/signUpIndex.jsp");
			rd.forward(request, response);
		}
	}

}
