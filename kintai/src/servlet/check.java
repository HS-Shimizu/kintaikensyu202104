package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Operation;
import model.Time;

/**
 * Servlet implementation class check
 */

public class check extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public check() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ServletContext application = this.getServletContext();

		request.setCharacterEncoding("UTF-8");
		String stamp =  request.getParameter("stamp");
		String year =  request.getParameter("year");
		String month = request.getParameter("month");
		String ym = year + month;
//----------検索-------------------------

		/**
		 * 検索
		 *
		 *
		 */
		if(stamp.equals("search")) {
			String message;
			// 年月が正しいか確認
//			message = ymcheck(year,month);

			List<Time> times = new ArrayList<Time>();
			Operation select = new Operation();

			//年月の日付取得
			times = LastDayGetter(year, month, times);

			// リストにインスタンスを保存
			times = select.search(ym , times);

			message = select.getSelectMessage();

			application.setAttribute("times", times);
			request.setAttribute("message", message);
			RequestDispatcher disapatcher = request.getRequestDispatcher("Result.jsp");
			disapatcher.forward(request,response);

		}
//----------追加-------------------------
		/**
		 * 追加
		 *
		 *
		 */
		else if(stamp.equals("update")) {
//			boolean bool = false;

			List<Time> times = new ArrayList<Time>();
			Operation add = new Operation();
			//			 Time time = new Time();
			String message = "";
//			String errmessage = null;

			times = (List<Time>) application.getAttribute("times");

			for(int i = 0; i < times.size(); i++) {

				String st =  request.getParameter("st" + i);
				String ed =  request.getParameter("ed" + i);
				String rt =  request.getParameter("rt" + i);
				String ymd = times.get(i).getYmd();
				if(st.equals("")&& ed.equals("") && rt.equals("")) {
					message = add.getAddMessage();
				}else {
					add.add(ymd, st, ed, rt);
					message = add.getAddMessage();
				}
			}
			times = add.search(ym , times);
			application.setAttribute("times", times);

			request.setAttribute("message", message);
			RequestDispatcher disapatcher = request.getRequestDispatcher("Result.jsp");
			disapatcher.forward(request,response);

		}
//----------削除-------------------------
		/**
		 * 削除
		 *
		 *
		 */
		else if(stamp.equals("delete")) {

			List<Time> times = new ArrayList<Time>();
			Operation delete = new Operation();
			times = LastDayGetter(year, month, times);

			delete.delete(ym);

			times = delete.search(ym , times);

			application.setAttribute("times", times);

			String message = delete.getDeleteMessage();
			request.setAttribute("message", message);

			RequestDispatcher disapatcher = request.getRequestDispatcher("Result.jsp");
			disapatcher.forward(request,response);

		}

	}

	/**
	 *入力された年月が正しいか確認
	 *
	 *
	 * @param year
	 * @param month
	 * @return boolean
	 */
//	public String ymcheck(String year, String month) {
//
//		String message = "";
//
//		if(year == "" || month == ""){
//			message = "年月を入力してください";
//		}
//		else if(year.matches("[0-9]{4}")) {
//
//		}
//		else {
//			message = "年はyyyyで入力してください";
//		}
//
//
//		return message;
//	}

	/**
	 * 入力された時間が正しいか判定
	 *
	 * @param time
	 * @return
	 */
//	public boolean timeCheck(String time) {
//
//		boolean bool = false;
//
//		if (time.matches("[0-9]{4}")) {
//
//			bool = true;
//		}
//
//		return bool;
//	}

	/**
	 * 日付を取得
	 *
	 * @param year
	 * @param month
	 * @param times
	 * @return
	 */
	public List<Time> LastDayGetter(String year, String month, List<Time> times) {

		//対象年
		int iyear = Integer.parseInt(year);
		//対象月
		int imonth = Integer.parseInt(month);

		String day;
		//取得処理
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, iyear);
		calendar.set(Calendar.MONTH, imonth - 1);
		int result = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

		for (int i = 1; i <= result; i++) {
			if (i <= 9) {
				day = "0" + i;
			} else {
				day = String.valueOf(i);
			}
			Time time = new Time(year, month, day);
			times.add(time);
			//System.out.println(dd);
		}

		return times;
	}

//	/**
//	 *
//	 *
//	 * @param times
//	 * @param request
//	 * @return
//	 */
//	public boolean Confirmation(List<Time> times , HttpServletRequest request) {
//
//		boolean bool = false;
//
//		for(int i = 0; i < times.size(); i++) {
//			String st =  request.getParameter("st" + i);
//			String ed =  request.getParameter("ed" + i);
//			String rt =  request.getParameter("rt" + i);
//
//			if(st.equals("")&& ed.equals("") && rt.equals("")) {
//				bool = true;
//			}else {
//				boolean sbool = stCheck(st);
//				boolean ebool = edCheck(ed);
//				boolean rbool = rtCheck(rt);
//
////				System.out.println(i + "st" + sbool);
////				System.out.println(i + "ed" + ebool);
////				System.out.println(i + "rt" + rbool);
//
//				if(sbool && ebool && rbool) {
//					bool = true;
//				}else {
//					bool = false;
//					break;
//				}
//			}
//		}
//		return bool;
//	}
//
//	/**
//	 *
//	 *
//	 *
//	 * @param st
//	 * @return
//	 */
//	public boolean stCheck(String st) {
//
//		boolean bool = false;
//		if(timeCheck(st)) {
//			// 退勤時間の確認
//			bool = true;
//
//		}else {
//			// 出勤時間の値が違う
//
//		}
//		return bool;
//	}
//
//	/**
//	 *
//	 *
//	 *
//	 * @param ed
//	 * @return
//	 */
//	public boolean edCheck(String ed) {
//
//		boolean bool = false;
//		if(timeCheck(ed)) {
//			// 退勤時間の確認
//			bool = true;
//
//		}else {
//			// 出勤時間の値が違う
//
//		}
//		return bool;
//	}
//
//	/**
//	 *
//	 *
//	 *
//	 * @param rt
//	 * @return
//	 */
//	public boolean rtCheck(String rt) {
//
//		boolean bool = false;
////		int iRt = Integer.parseInt(rt);
//
//		if(rt.equals("")) {
//
//		}else if (rt.matches("[0-9]{0,3}")) {
//			bool =true;
//		}
//		else {
//		}
//		return bool;
//	}
}


