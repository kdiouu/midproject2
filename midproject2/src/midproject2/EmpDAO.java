package midproject2;

import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class EmpDAO implements IEmpDAO {
	Connection conn = null;
	DataSource ds = null;
	public EmpDAO() {
		try {
			Context context = new InitialContext();
			ds = (DataSource) context.lookup("java:comp/env/jdbc/midproject");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	public void closeConn() throws SQLException {
		if (conn != null)
			conn.close();
	}
	private static final String INSERT_STMT = "insert into stock_info_database values ((select max(companyno)+1 from stock_info_database),?,?,?,?,?,?,?,?)";
	private static final String QUERY_INSERT_ID = "select max(companyno) from stock_info_database";
	@Override
	public int writeToSQL(EmpBean emp) throws SQLException, IOException, ParseException {
		conn = ds.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(INSERT_STMT);
		int resultnumber;
		pstmt.setInt(1, emp.getCompanyID());
		pstmt.setString(2, emp.getCompanyname());
		pstmt.setString(3, emp.getCompanytype());
		pstmt.setTimestamp(4, emp.getEstablish_date());
		pstmt.setTimestamp(5, emp.getLaunch_date());
		pstmt.setDouble(6, emp.getCapital_amount());
		pstmt.setString(7, emp.getCompany_addrs());
		pstmt.setBoolean(8, emp.getIsestablish());
		pstmt.executeUpdate();
		pstmt.close();

		PreparedStatement pstmt1 = conn.prepareStatement(QUERY_INSERT_ID);
		pstmt1.executeQuery();
		ResultSet rs = pstmt1.getResultSet();
		rs.next();
		resultnumber = rs.getInt(1);
		conn.close();
		return resultnumber;
	}

	private static final String QRYSTMT = "select companyno from stock_info_database";
	@Override
	public StringBuffer showId() {
		StringBuffer str = new StringBuffer();
		try {
			conn = ds.getConnection();
			PreparedStatement stmt = conn.prepareStatement(QRYSTMT);
			ResultSet rs = stmt.executeQuery();

			str.append("<select id = 'companyid2' name='companyid2'><option id='optionID' value=' '>");
			String companyid2;
			while (rs.next()) {
				companyid2 = rs.getString("companyno");
				str.append("<option value='" + companyid2 + "'>" + companyid2);
			}
			str.append("</select>" + "@" + " ");
			rs.close();
			conn.close();
		} catch (SQLException e) {
			str.append("@" + e.getMessage());
		}
		return str;
	}

	private static final String SELECT_STMT = "select companyno,companyID,companyname,companytype,establish_date,launch_date,"
			+ "FORMAT(capital_amount, 'C', 'zh-tw') as capital_amount,company_addrs,isestablish from stock_info_database";
	
	public StringBuffer showType() {
		StringBuffer str = new StringBuffer();
		try {
			conn = ds.getConnection();
			PreparedStatement stmt = conn.prepareStatement(SELECT_STMT);
			ResultSet rs = stmt.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			str.append(
					"<select id = 'companyid3' name='companyid3' style='width:200px;'><option id='optionIDtype' value=' '>");
			String companyid3;
			rs.next();
			for (int j = 1; j < rsmd.getColumnCount(); j++) {
				companyid3 = rsmd.getColumnName(j);
				str.append("<option value='" + companyid3 + "'>" + companyid3);
			}
			str.append("</select>");
			rs.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return str;
	}

	private static final String QUERY_BY_ID = "select * from stock_info_database where companyno=?";

	@Override
	public EmpBean selectone(int id) {
		EmpBean EmpBeanObject = new EmpBean();
		try {
			conn = ds.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(QUERY_BY_ID);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			rs.next();
			EmpBeanObject.setCompanyno(id);
			EmpBeanObject.setCompanyID(rs.getInt(rsmd.getColumnName(2)));
			EmpBeanObject.setCompanyname(rs.getString(rsmd.getColumnName(3)).trim());
			EmpBeanObject.setCompanytype(rs.getString(rsmd.getColumnName(4)).trim());
			EmpBeanObject.setEstablish_date(rs.getTimestamp(rsmd.getColumnName(5)));
			EmpBeanObject.setLaunch_date(rs.getTimestamp(rsmd.getColumnName(6)));
			EmpBeanObject.setCapital_amount(rs.getDouble(rsmd.getColumnName(7)));
			EmpBeanObject.setCompany_addrs(rs.getString(rsmd.getColumnName(8)).trim());
			EmpBeanObject.setIsestablish(rs.getBoolean(rsmd.getColumnName(9)));
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return EmpBeanObject;
	}

	private static final String UPDATE_STMT = " update stock_info_database set companyID = ?,companyname=?,companytype=?,establish_date=?,launch_date=?,capital_amount=?,company_addrs=?,isestablish=? where companyno = ?";

	@Override
	public EmpBean upDate(EmpBean upDateObject) {
		EmpBean EmpBeanObject = new EmpBean();
		try {
			conn = ds.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(UPDATE_STMT);
			pstmt.setInt(1, upDateObject.getCompanyID());
			pstmt.setString(2, upDateObject.getCompanyname());
			pstmt.setString(3, upDateObject.getCompanytype());
			pstmt.setTimestamp(4, upDateObject.getEstablish_date());
			pstmt.setTimestamp(5, upDateObject.getLaunch_date());
			pstmt.setDouble(6, upDateObject.getCapital_amount());
			pstmt.setString(7, upDateObject.getCompany_addrs());
			pstmt.setBoolean(8, upDateObject.getIsestablish());
			pstmt.setInt(9, upDateObject.getCompanyno());
			pstmt.executeUpdate();
			pstmt.close();
			EmpBeanObject.setCompanyno(upDateObject.getCompanyno());
			EmpBeanObject.setCompanyID(upDateObject.getCompanyID());
			EmpBeanObject.setCompanyname(upDateObject.getCompanyname());
			EmpBeanObject.setCompanytype(upDateObject.getCompanytype());
			EmpBeanObject.setEstablish_date(upDateObject.getEstablish_date());
			EmpBeanObject.setLaunch_date(upDateObject.getLaunch_date());
			EmpBeanObject.setCapital_amount(upDateObject.getCapital_amount());
			EmpBeanObject.setCompany_addrs(upDateObject.getCompany_addrs());
			EmpBeanObject.setIsestablish(upDateObject.getIsestablish());
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return EmpBeanObject;
	}
	private static final String DELETE_STMT = "delete from stock_info_database where companyno=?";
	@Override
	public int delete(int id) {
		int deletecount = 0;
		try {
			conn = ds.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(DELETE_STMT);
			pstmt.setInt(1, id);
			deletecount = pstmt.executeUpdate();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return deletecount;
	}
	@Override
	public StringBuffer selectLike(String type, String text) {
		StringBuffer result = new StringBuffer();
		try {
			conn = ds.getConnection();
			String select = "select companyno,companyID,companyname,companytype,establish_date,launch_date,"
					+ "FORMAT(capital_amount, 'C', 'zh-tw') as capital_amount,company_addrs,isestablish from stock_info_database where ";
			if (type.equals("companytype")) {
				select += "companytype like ?";
				System.out.println("tes");
			} else if (type.equals("companyno")) {
				select += "companyno like ?";
			} else if (type.equals("companyID")) {
				select += "companyID like ?";
			} else if (type.equals("establish_date")) {
				select += "establish_date like ?";
			} else if (type.equals("capital_amount")) {
				select += "capital_amount like ?";
			} else if (type.equals("company_addrs")) {
				select += "company_addrs like ?";
			} else if (type.equals("launch_date")) {
				select += "launch_date like ?";
			} else if (type.equals("isestablish")) {
				select += "isestablish like ?";
			} else if (type.equals("companyname")) {
				select += "companyname like ?";
			}
			PreparedStatement pstmt = conn.prepareStatement(select);
			pstmt.setString(1, "%" + text.trim() + "%");
			System.out.println(type.trim());
			ResultSet rs = pstmt.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			int record = 0;
			result.append("<table id='tableB' style=' border:1;'>");
			result.append(
					"<tr style='border: 1px solid white;'><td>companyno</td><td>companyID</td><td>companyname</td><td>companytype</td><td>establish_date</td><td>launch_date</td><td>capital_amount</td><td>company_addrs</td><td>isestablish</td></tr>");
			while (rs.next()) {
				result.append("<tr class='select' id=" + rs.getString(rsmd.getColumnName(1)).trim() + ">");
				result.append("<td nowrap='nowrap'>" + rs.getString(rsmd.getColumnName(1)).trim() + "</td>");
				result.append("<td nowrap='nowrap'>" + rs.getString(rsmd.getColumnName(2)).trim() + "</td>");
				result.append("<td nowrap='nowrap'>" + rs.getString(rsmd.getColumnName(3)).trim() + "</td>");
				result.append("<td nowrap='nowrap'>" + rs.getString(rsmd.getColumnName(4)).trim() + "</td>");
				result.append(
						"<td nowrap='nowrap'>" + rs.getString(rsmd.getColumnName(5)).trim().substring(0, 10) + "</td>");
				result.append(
						"<td nowrap='nowrap'>" + rs.getString(rsmd.getColumnName(6)).trim().substring(0, 10) + "</td>");
				result.append("<td nowrap='nowrap'>" + rs.getString(rsmd.getColumnName(7)).trim() + "</td>");
				result.append("<td nowrap='nowrap'>" + rs.getString(rsmd.getColumnName(8)).trim() + "</td>");
				result.append("<td nowrap='nowrap'>" + rs.getString(rsmd.getColumnName(9)).trim() + "</td>");
				result.append("</tr>");
				record++;
			}
			result.append("</table>" + "@" + record);
			rs.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
} // end of class EmpDAO