<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link
	href="https://fonts.googleapis.com/css?family=Noto+Sans+TC&display=swap"
	rel="stylesheet">
<link rel=stylesheet type="text/css" href="text1.css">
<title>期中專題Demo</title>
</head>
<style>
* {
	font-family: 'Noto Sans TC', sans-serif;
	color: rgba(245, 245, 245, 0.945);
	*/
}

body {
	background-color: rgba(38, 38, 39, 0.88);
}

form {
	margin: 25px;
}

.choose {
	color: red;
}

.MemberFormB table tr:hover {
	background-color: #2471FF;
}

input {
	background-color: rgb(38, 38, 39);
}

select {
	background-color: rgb(38, 38, 39);
}
</style>

<script>
	function highlightBG(element, color) {
		element.style.backgroundColor = color;
	}

	function comPanyIdCheck() {
		console.log(document.getElementById("comPanyId").value);
		var NewStringValue = document.getElementById("comPanyId").value;
		var re = /^[0-9]+$/;
		if (NewStringValue == "") {
			document.getElementById("msg1").innerHTML = "<td>公司代碼</td><td>不能為空</td>"

		} else if (!re.test(NewStringValue)) {
			document.getElementById("msg1").innerHTML = "<td>公司代碼</td><td>必須為數字</td>";

		} else if (document.getElementById("comPanyId").value.length < 2) {
			document.getElementById("msg1").innerHTML = "<td>公司代碼</td><td>至少兩個數字</td>";

		} else {
			document.getElementById("msg1").innerHTML = ""
			return true;
		}
	}
	function dateCheck() {
		var NewDateValue = document.getElementById("esdate").value;
		var Datepattern = new RegExp(
				"^\([0-9]{4})([0-9]{1,2})([0-3]{1}[0-9]{1})$");
		if (!Datepattern.test(NewDateValue)) {
			document.getElementById("msg2").innerHTML = "<td>創立日期</td><td>須符合格式yyyyMMdd</td>";
		} else {
			document.getElementById("msg2").innerHTML = "";
			return true;
		}
	}
	function dateCheck1() {
		var NewDateValue = document.getElementById("setdate").value;
		var Datepattern = new RegExp(
				"^\([0-9]{4})([0-9]{1,2})([0-3]{1}[0-9]{1})$");
		if (!Datepattern.test(NewDateValue)) {
			document.getElementById("msg3").innerHTML = "<td>上市日期</td><td>須符合yyyyMMdd</td>";
		} else {
			document.getElementById("msg3").innerHTML = "";
			return true;
		}
	}
	function comPanyNameCheck() {
		var NewStringValue = document.getElementById("comPanyName").value;
		var reg = /[^\x00-\x80]/;
		if (NewStringValue == "") {
			document.getElementById("msg4").innerHTML = "<td>公司名稱</td><td>不能為空</td>"

		} else if (!reg.test(NewStringValue)) {
			document.getElementById("msg4").innerHTML = "<td>公司名稱</td><td>必須為漢字</td>";

		} else if (document.getElementById("comPanyName").value.length < 2) {
			document.getElementById("msg4").innerHTML = "<td>公司名稱</td><td>必須為漢字</td>";

		} else {
			document.getElementById("msg4").innerHTML = "";
			return true;
		}
	}
</script>

<body>
	<div style="float: left; padding: 0px 60px 60px 60px;">
		<form class="MemberFormA" name="MemberFormA" action=" " method="POST"
			style="padding: 0px; margin-top: 27px; margin-bottom: 10px; height: 100%;">
			<table border="1" style="height: 650px;">
				<thead>
					<tr bgcolor='rgba(245, 245, 245, 0.945)'>
						<th height="60" colspan="2" align="center">新增公司資料</th>
					</tr>
				</thead>
				<tbody>
					<tr bgcolor='rgba(245, 245, 245, 0.945)'>
						<td width="150" height="40">公司編號(修改專用):</td>
						<td width="600" height="40" align="left"><span id="show"></span>
						</td>
					</tr>

					<tr bgcolor='rgba(245, 245, 245, 0.945)'>
						<td width="120" height="40">公司代碼:</td>
						<td width="600" height="40" align="left"><input
							style="text-align: left" name="mId" onblur="comPanyIdCheck()"
							id="comPanyId" value="${EmpBeanCompany.companyID}" type="text"
							size="14">
							<div style="color: #FF0000; font-size: 60%; display: inline">${ErrorMsg.id}</div>
						</td>
					</tr>
					<tr bgcolor='rgba(245, 245, 245, 0.945)'>
						<td width="120" height="40">公司名稱:</td>
						<td width="600" height="40" align="left"><input
							id='comPanyName' style="text-align: left" name="formName"
							onblur="comPanyNameCheck()" value="${EmpBeanCompany.companyname}"
							type="text" size="14">
							<div style="color: #FF0000; font-size: 60%; display: inline">${ErrorMsg.formName}</div>
						</td>
					</tr>
					<tr bgcolor='rgba(245, 245, 245, 0.945)'>
						<td width="120" height="40">公司類型:</td>
						<td width="600" height="40" align="left"><input
							name="formType" value="${EmpBeanCompany.companytype}" type="text"
							size="20">
							<div style="color: #FF0000; font-size: 60%; display: inline">${ErrorMsg.formType}</div>
						</td>
					</tr>
					<tr bgcolor='rgba(245, 245, 245, 0.945)'>
						<td width="120" height="40">創立日期:</td>
						<td width="600" height="40" align="left"><input name="esDate"
							value="${esTime2String}" type="text" onblur="dateCheck()"
							id="esdate" size="54">
							<div style="color: #FF0000; font-size: x-small; display: inline">${ErrorMsg.esDate}</div>
						</td>
					</tr>
					<tr bgcolor='rgba(245, 245, 245, 0.945)'>
						<td width="120" height="40">上市日期:</td>
						<td width="600" height="40" align="left"><input
							name="setupDate" onblur="dateCheck1()" id="setdate"
							value="${laTime2String}" type="text" size="54"></td>
					</tr>
					<tr bgcolor='rgba(245, 245, 245, 0.945)'>
						<td width="120" height="40">資本額:</td>
						<td width="600" height="40" align="left"><input
							name="capital" value="${capitalvalue}" type="text" size="30">
							<div style="color: #FF0000; font-size: x-small; display: inline">${ErrorMsg.capital}</div>
						</td>
					</tr>
					<tr bgcolor='rgba(245, 245, 245, 0.945)'>
						<td width="120" height="40">公司地址:</td>
						<td width="600" height="40" align="left"><input name="addrs"
							value="${ EmpBeanCompany.company_addrs}" type="text" size="40">
							<div style="color: #FF0000; font-size: x-small; display: inline">${ErrorMsg.addrs}</div>
						</td>
					</tr>
					<tr bgcolor='rgba(245, 245, 245, 0.945)'>
						<td width="120" height="40">是否上市:</td>
						<td width="600" height="40" align="left"><input type="radio"
							id="true" value="yes" name="isSet" size="14"> <label
							for="true">已上市</label> <input type="radio" id="false" value="no"
							name="isSet" size="14"> <label for="false">未上市</label>
							<div style="color: #FF0000; font-size: x-small; display: inline">${ErrorMsg.isSet}</div>
						</td>
					</tr>
					<tr bgcolor='rgba(245, 245, 245, 0.945)'>
						<td height="50" colspan="2" align="center">
						<input type="button" value="新增資料" style="margin: 10px;" onClick="insertbutton()"> 
						<input type="button" value="修改資料" style="margin: 10px;" onClick="updatebutton()">
						<input type="button" value="清空" style="margin: 10px;" onClick="clears()">
					    <input type="button" style="float: right; margin: 10px; background-color: red; border-bottom: 3px solid red" value="刪除資料" onClick="deletebutton()"></td>


					</tr>

				</tbody>
			</table>
			<div style="color: #FF0000; display: inline"></div>
		</form>
		<img id="imgs2" style="width: 24px;"> <span id="status"
			style="font-size: 12px;"></span> <br>

		<div
			style="border: 1px solid black; margin-top: 0px; width: 762px; size: 14">

			<table
				style="border: 1px solid black; border-bottom: none; margin: 0 auto; width: 762px;">
				<tr>
					<td>執行狀態</td>
					<td style="color: red; width: 500px;">${ErrorMsg.exception}${ErrorMsg.encode}${ErrorMsg.system}${ErrorMsg.id}${ErrorMsg.date}</td>
				</tr>
				<tr>
					<td></td>
				</tr>
			</table>

			<table id="question"
				style="border: 1px solid black; border-top: none; margin: 0 auto; width: 762px;">
				<tr>
					<td style="width: 50%">訊息來源</td>
					<td style="width: 50%">訊息說明</td>
				</tr>
				<tr id="msg1"></tr>
				<tr id="msg2"></tr>
				<tr id="msg3"></tr>
				<tr id="msg4"></tr>
				<tr id="msg5"></tr>
			</table>


		</div>
	</div>

	<div
		style="overflow: hidden; margin-left: 0px; margin-right: 60px; height: 100%;">

		<form class="MemberFormB" name="MemberFormB" action=" " method="POST">
			<span id="showquerymessage" style="color: #e24f4f;"></span><br>
			<span id="show1"></span> <input type="text" id="query2" name="query"
				placeholder="please enter text" style="padding: 0px;" size="50">
			<input type="button" value="查詢"
				style="margin-left: 53px; width: 188px; height: 40px"
				onclick="doQuery()">
			<div
				style="overflow: scroll; border: 1px solid black; height: 789px; width: 900px; font-size: 12px; margin-top: 10px;">
				${result}</div>
			<input type="hidden" name="goToQuery" id="tet"><span
				id="showquerymessage2" style="margin-left: 770px;"></span>



		</form>

	</div>
	<script>
		function doQuery() {
			if (document.getElementById("companyid3").value == "") {
				document.getElementById("showquerymessage").innerHTML = "請選擇查詢種類"
			} else {
				document.MemberFormB.action = "queryall";
				document.MemberFormB.submit();

			}
		}

		function clears() {
			document.MemberFormA.reset();
			window.location.href = 'signUpIndex.jsp'
		}
		function insertbutton() {
			if (!comPanyIdCheck() || !dateCheck() || !dateCheck1()
					|| !comPanyNameCheck())
				alert("資料有誤，表單將不送出！");
			else
			document.MemberFormA.action = "signUp";
			document.MemberFormA.submit();
		}
		function updatebutton() {

			if (!comPanyIdCheck() || !dateCheck() || !dateCheck1()
					|| !comPanyNameCheck())
				alert("資料有誤，表單將不送出！");
			else
			document.MemberFormA.action = "upDate";
			document.MemberFormA.submit();
		}
		function deletebutton() {
			if (document.getElementById("companyid2").value == ""){
				alert("沒有輸入資料")
			} else if(confirm("您真的確定刪除"+"  "+document.getElementById("comPanyName").value+"  "+"這筆資料嗎?")){
			document.MemberFormA.action = "delete";
			document.MemberFormA.submit();
			} else{
				
			}
		}
		var i = 0;
		document.addEventListener("DOMContentLoaded", function() {
			document.getElementById("show").addEventListener("change",
					function() {
						document.MemberFormA.action = "getData"
						document.MemberFormA.method = "POST"
						document.MemberFormA.submit();
					})

			var selectcount = document.getElementsByClassName("select");
			for (i = 0; i <= '${querycount}' - 1; i++) {
				selectcount[i].addEventListener("dblclick", function() {
					document.getElementById("tet").value = this.id;
					document.MemberFormB.action = "getData"
					document.MemberFormB.method = "GET"
					document.MemberFormB.submit();
				})
			}
		})

		if ('${EmpBeanCompany.isestablish}' == "true") {
			document.getElementById("true").click();
		} else if ('${EmpBeanCompany.isestablish}' == "false") {
			document.getElementById("false").click();
		}

		var xhttp = new XMLHttpRequest();
		xhttp.onreadystatechange = function() {
			if (this.readyState == 4 && this.status == 200) {
				document.getElementById("show").innerHTML = this.responseText;
				document.getElementById("optionID").value = '${EmpBeanCompany.companyno}'
				document.getElementById("optionID").innerHTML = '${EmpBeanCompany.companyno}'

			}
		}
		xhttp.open("GET", "selectName.jsp", true);
		xhttp.send();
		var xhttp1 = new XMLHttpRequest();
		xhttp1.onreadystatechange = function() {
			if (this.readyState == 4 && this.status == 200) {
				document.getElementById("show1").innerHTML = this.responseText;
				document.getElementById("optionIDtype").value = '${type}'
				document.getElementById("optionIDtype").innerHTML = '${type}'
				document.getElementById("query2").value = '${value}'
				document.getElementById("showquerymessage2").innerHTML = "目前顯示&nbsp&nbsp${querycount}&nbsp&nbsp筆"
			}
		}
		xhttp1.open("GET", "selectName1.jsp", true);
		xhttp1.send();

		var xhttp2 = new XMLHttpRequest();
		xhttp2.onreadystatechange = function() {
			if (this.readyState == 4 && this.status == 200) {
				if (this.responseText.trim() != "") {
					document.getElementById("imgs2").src = "../imgs/wrong.png";
					document.getElementById("status").innerHTML = this.responseText;
					document.getElementById("status").style.color = "red";
				} else {
					document.getElementById("imgs2").src = "../imgs/click.png";
					document.getElementById("status").style.fontSize = "16px";
					document.getElementById("status").innerHTML = "資料庫連結成功";
					document.getElementById("status").style.color = "#30a0f3";
				}
			}
		}
		xhttp2.open("GET", "selectName1error.jsp", true);
		xhttp2.send();
	</script>

</body>
</html>