package selvetpackage;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import midproject2.EmpBean;
import midproject2.EmpDAO;

/**
 * Servlet implementation class getData
 */
@WebServlet("/signUp/getData")
public class getData extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int idbox2 = Integer.valueOf(request.getParameter("goToQuery"));
		EmpDAO EmpDAOObject2 = new EmpDAO();
		EmpBean EmpBeanObject2 = EmpDAOObject2.selectone(idbox2);
		EmpBeanObject2.setCompanyno(idbox2);
		request.setAttribute("EmpBeanCompany", EmpBeanObject2);
		request.setAttribute("esTime2String",
				EmpBeanObject2.getEstablish_date().toString().substring(0, 10).replace("-", ""));
		request.setAttribute("laTime2String",
				EmpBeanObject2.getLaunch_date().toString().substring(0, 10).replace("-", ""));
		request.setAttribute("capitalvalue", EmpBeanObject2.getCapital_amount().longValue());
		System.out.println(EmpBeanObject2.toString());
		System.out.println(EmpBeanObject2.getIsestablish());
		request.getRequestDispatcher("/signUp/signUpIndex.jsp").forward(request, response);
		return;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int idbox = Integer.valueOf(request.getParameter("companyid2"));
		EmpDAO EmpDAOObject2 = new EmpDAO();
		EmpBean EmpBeanObject2 = EmpDAOObject2.selectone(idbox);
		EmpBeanObject2.setCompanyno(idbox);
		request.setAttribute("EmpBeanCompany", EmpBeanObject2);
		request.setAttribute("esTime2String",
				EmpBeanObject2.getEstablish_date().toString().substring(0, 10).replace("-", ""));
		request.setAttribute("laTime2String",
				EmpBeanObject2.getLaunch_date().toString().substring(0, 10).replace("-", ""));
		request.setAttribute("capitalvalue", EmpBeanObject2.getCapital_amount().longValue());
		request.getRequestDispatcher("/signUp/signUpIndex.jsp").forward(request, response);
		return;

	}

}
