package model;

import java.util.Calendar;

public class Time {

	private String workSt;
	private String workEd;
	private String workRt;
	private String year;
	private String month;
	private String day;
	private String SumTime;

	Calendar cal = Calendar.getInstance();

	public Time() {

	}

	public Time(String year, String month , String day) {

		this.workSt = "";
		this.workEd = "";
		this.workRt = "";

		this.year = year;
		this.month = month;
		this.day = day;
	}

	public String getWorkSt() {
		return workSt;
	}

	public void setWorkSt(String workSt) {

		this.workSt = workSt;
	}

	public void setWorkEd(String workEd) {

		this.workEd = workEd;
	}

	public void setWorkRt(String workRt) {

		this.workRt = workRt;
	}

	public String getWorkEd() {
		return workEd;
	}

	public String getWorkRt() {
		return workRt;
	}

	public String getSumTime() {
		return SumTime;
	}

	public String getYear() {
		return year;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getYmd() {

		String ymd = year + month + day;

		return ymd;
	}

	public String show() {

		String ymd = year + "/" + month + "/" + day;

		return ymd;
	}

	public String showyoubi() {
		String youbi;

		String[] week = {"","日","月","火","水","木","金","土"};

		Calendar cal = Calendar.getInstance();
		int iYear = Integer.parseInt(year);
		int iMonth = Integer.parseInt(month) - 1;
		int iDay = Integer.parseInt(day);

		cal.set(iYear,iMonth,iDay);

		int i = cal.get(Calendar.DAY_OF_WEEK);

		youbi = week[i];

		return youbi;
	}



	public double SumTime() {

		double iWorkRt;
		double result = 0;

		if(workSt.equals("")||workEd.equals("")||workRt.equals("")) {

			String SResult = "";
			this.SumTime = SResult;
		}
		else {

//			System.out.println(workSt);
			String sth = workSt.substring(0, 2);
			String stm = workSt.substring(2);

			String edh = workEd.substring(0, 2);
			String edm = workEd.substring(2);

			int iSth = Integer.parseInt(sth);
			int iStm = Integer.parseInt(stm);
			int iEdh = Integer.parseInt(edh);
			int iEdm = Integer.parseInt(edm);
			iWorkRt = Integer.parseInt(workRt);

			double iWorkMin = (iEdh - iSth) * 60.0 + (iEdm - iStm);

			double resultmin =iWorkMin - iWorkRt;
			result = resultmin / 60; // 元の形式に戻す



			String SResult = String.valueOf(result);

			this.SumTime = SResult;
		}

		return result;
	}
}
