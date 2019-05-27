package midproject2;

import java.sql.Timestamp;
import java.text.DecimalFormat;

public class EmpBean {
	private int companyno;
	private int companyID;

	private String companyname;
	private String companytype;
	private Timestamp establish_date;
	private Timestamp launch_date;
	private Double capital_amount;
	private String company_addrs;
	private Boolean isestablish;
	public EmpBean() {};
	@Override
	public String toString() {
		return "EmpBean [companyno=" + companyno + ", companyID=" + companyID + ", companyname=" + companyname
				+ ", companytype=" + companytype + ", establish_date=" + establish_date + ", launch_date=" + launch_date
				+ ", capital_amount=" + capital_amount + ", company_addrs=" + company_addrs + ", isestablish="
				+ isestablish + "]";
	}
	public EmpBean(int companyno, int companyID, String companyname, String companytype, Timestamp establish_date,
			Timestamp launch_date, Double capital_amount, String company_addrs, Boolean isestablish) {
		super();
		this.companyno = companyno;
		this.companyID = companyID;
		this.companyname = companyname;
		this.companytype = companytype;
		this.establish_date = establish_date;
		this.launch_date = launch_date;
		this.capital_amount = capital_amount;
		this.company_addrs = company_addrs;
		this.isestablish = isestablish;
	}
	public EmpBean(int companyID, String companyname, String companytype, Timestamp establish_date,
			Timestamp launch_date, Double capital_amount, String company_addrs, Boolean isestablish) {
		super();
		this.companyID = companyID;
		this.companyname = companyname;
		this.companytype = companytype;
		this.establish_date = establish_date;
		this.launch_date = launch_date;
		this.capital_amount = capital_amount;
		this.company_addrs = company_addrs;
		this.isestablish = isestablish;
	}
	public int getCompanyno() {
		return companyno;
	}
	public void setCompanyno(int companyno) {
		this.companyno = companyno;
	}
	public String getCompanyname() {
		return companyname;
	}
	public void setCompanyname(String companyname) {
		this.companyname = companyname;
	}
	public String getCompanytype() {
		return companytype;
	}
	public void setCompanytype(String companytype) {
		this.companytype = companytype;
	}
	public Timestamp getEstablish_date() {
		return establish_date;
	}
	public void setEstablish_date(Timestamp establish_date) {
		this.establish_date = establish_date;
	}
	public Timestamp getLaunch_date() {
		return launch_date;
	}
	public void setLaunch_date(Timestamp launch_date) {
		this.launch_date = launch_date;
	}
	public Double getCapital_amount() {
		return capital_amount;
	}
	public void setCapital_amount(Double capital_amount) {
		this.capital_amount = capital_amount;
	}
	public String getCompany_addrs() {
		return company_addrs;
	}
	public void setCompany_addrs(String company_addrs) {
		this.company_addrs = company_addrs;
	}
	public Boolean getIsestablish() {
		return isestablish;
	}
	public void setIsestablish(Boolean isestablish) {
		this.isestablish = isestablish;
	}
	public int getCompanyID() {
		return companyID;
	}
	public void setCompanyID(int companyID) {
		this.companyID = companyID;
	}
} // end of class EmpVO