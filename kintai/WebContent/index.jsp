<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- dev2 -->

<!-- featureブランチから追加 -->
	<label>勤務時間入力</label>
	<form  name ="form" action = "/kintai/check" method="post">
		<p>
			<input type = text name = "year" id = "year" value ="">年

			<select name="month" id = "month">
				<option value=""></option>
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

		</p>
	</form>
<!-- dev2 -->
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



</script>
</body>
</html>