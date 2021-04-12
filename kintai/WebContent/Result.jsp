<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    <%@ page import="model.Time"
    import = "java.util.ArrayList"
	import ="java.util.List" %>

    <%


    List<Time> times = new ArrayList<Time>();
    times = (List<Time>) application.getAttribute("times");

    request.setCharacterEncoding("UTF8");
	String message = (String) request.getAttribute("message");
	String errmessage = (String) request.getAttribute("errmessage");

    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<label>勤務時間入力</label>

	<form  name ="form" action = "/kintai/check" method="post">
		<p>
		<input type = text id = "year" name = "year" value ="<%= times.get(1).getYear()%>">年

			<select name="month" id = "month">
				<option value="<%= times.get(0).getMonth()%>"><%= times.get(0).getMonth()%></option>
				<option value= "01">01</option>
				<option value= "02">02</option>
				<option value= "03">03</option>
				<option value= "04">04</option>
				<option value= "05">05</option>
				<option value= "06">06</option>
				<option value= "07">07</option>
				<option value= "08">08</option>
				<option value= "09">09</option>
				<option value= "10">10</option>
				<option value= "11">11</option>
				<option value= "12">12</option>
			</select>
			<button type="button" onclick="btnClick()">検索</button>
			<input type="submit"  name = "stamp" id = "btn1" value = "search" style="display:none;">

		</p><br><br>
		<p>
			<button type="button" onclick="btnClick2()">登録</button>
			<input type="submit"  name = "stamp" id = "btn2" value = "update" style="display:none;">
			<button onclick="return confirm('削除してもよろしいですか？')" type="submit" name = "stamp" value = "delete">削除</button>
			<label><%= message %></label>
		</p>
		<table id = "table" border="1">
			<tr>
				<th>年月日</th><th>曜日</th><th>出勤時刻</th><th>退勤時刻</th><th>休憩時間</th><th>勤務時間</th>
			</tr>
			<%  double sum = 0;
				for(int i = 0; i < times.size(); i++) {

					double sumtime = times.get(i).SumTime();
					sum = sum + sumtime;
					%>
			<tr>
					<td><input type = text style="border:solid 0px;" name = "ymd<%=i %>" value ="<%=times.get(i).show()%>" readonly></td>
					<td><label><%=times.get(i).showyoubi()%></label></td>
					<td><input type = text style="border:solid 0px;" name = "st<%=i %>" id = "st<%=i %>" value ="<%=times.get(i).getWorkSt()%>"></td>
					<td><input type = text style="border:solid 0px;" name = "ed<%=i %>" id = "ed<%=i %>" value ="<%=times.get(i).getWorkEd()%>"></td>
					<td><input type = text style="border:solid 0px;" name = "rt<%=i %>" id = "rt<%=i %>" value =<%=times.get(i).getWorkRt()%>></td>
					<td><label><%=times.get(i).getSumTime()%></label></td>

			<%} %>
			<tr>
			<td colSpan=4 style="border:solid 0px;"> </td>
			<td style="border:solid 0px;" align="right">合計時間</td>
			<td><label><%= sum%></label></td>
			</tr>

		</table>
	</form>
	<script>
	function btnClick(){

		var year = document.getElementById("year").value;
		var month = document.form.month;
		var num = month.selectedIndex;
		var str = month.options[num].value;

		if(str == "") {
			alert("年月を入力してください");
		}
		else {
			if (year.match(/^\d{4}$/)) {
				document.getElementById("btn1").click();
			}
			else {
				alert("年はyyyyで入力してください");
			}
		}
	}





	function timecheck(time) {
		var check = 0
		if (time.match(/^\d{4}$/)) {
			check = 1;
		}
		else {

		}
		return check;
	}

	function rtcheck(time) {
		var check = 0
		if (time.match(/^\d{1,3}$/)) {
			check = 1;
		}
		else {

		}
		return check;
	}

	function btnClick2(){

		for(var i = 0; i < <%= times.size()%>; i++) {

			var check = false;

			if(document.getElementById("st" + i).value == ""&&document.getElementById("ed" + i).value == ""&&document.getElementById("rt" + i).value == "") {
				check = true;
			}else {
				if(timecheck(document.getElementById("st" + i).value) == 1) {
					check = true;
				}else {
					check = false;
					break;
				}

				if(timecheck(document.getElementById("ed" + i).value) == 1) {
					check = true;
				}else {
					check = false;
					break;
				}

				 if(rtcheck(document.getElementById("rt" + i).value) == 1) {
					check = true;
				}else {
					check = false;
					break;
				}
			}



		}

		if(check) {
			document.getElementById("btn2").click();
		} else {
			alert("勤怠表の入力に誤りがあります。");
		}
	}
	</script>
	</body>
</html>