package midproject2;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.*;

public interface IEmpDAO {
	public int writeToSQL(EmpBean emp) throws SQLException, IOException, ParseException;
	public StringBuffer showId();
	public EmpBean upDate(EmpBean upDateObject);
	public EmpBean selectone(int id);
	public int delete(int id);
	public StringBuffer showType();
	public StringBuffer selectLike(String type, String text);
	public void closeConn() throws SQLException;
} // end of class IEmpDAO