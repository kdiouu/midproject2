<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel=stylesheet type="text/css" href="text1.css">
<link href="https://fonts.googleapis.com/css?family=Noto+Sans+TC&display=swap" rel="stylesheet">
<title>Lab05_03</title>
</head>
<style>
*{   
   font-family: 'Noto Sans TC', sans-serif;
   color:rgba(245, 245, 245, 0.945);
   background-color:rgba(38, 38, 39, 0.88);
     */
}

</style>
<body>
<form  class="MemberFormA">
<input type="button" value="back to Index"  style="width: 97%;
    height: 70px;"
    onclick="window.location='signUpIndex.jsp';" />   
</form>
<hr>
<h1>公司名稱 ${ EmpBeanCompany.companyname } 的資料新增成功</h1>
流水編號: ${EmpBeanCompany.companyno} <br>
公司代號: ${ EmpBeanCompany.companyID }<br>
公司種類: ${ EmpBeanCompany.companytype }<br>

創立日期: ${ EmpBeanCompany.establish_date }<br>
上市時間: ${ EmpBeanCompany.launch_date }<br>
資本額: ${ EmpBeanCompany.capital_amount }<br>
公司地址: ${ EmpBeanCompany.company_addrs }<br>
是否上市: ${ EmpBeanCompany.isestablish }<br>

<hr>
<h1> ${OldEmpBeanCompany.companyname} 的更新資訊</h1>
<table style="width: 1000px;">
<tr><th></th><th>更新前</th><th>更新後</th></tr>
<tr><td>公司名稱</td><td> ${OldEmpBeanCompany.companyname}</td><td>${NewEmpBeanCompany.companyname}</td></tr>
<tr><td>公司代號</td><td> ${OldEmpBeanCompany.companyID}</td><td>${NewEmpBeanCompany.companyID}</td></tr>
<tr><td>公司種類</td><td> ${OldEmpBeanCompany.companytype}</td><td>${NewEmpBeanCompany.companytype}</td></tr>
<tr><td>創立日期</td><td> ${OldEmpBeanCompany.establish_date}</td><td>${NewEmpBeanCompany.establish_date}</td></tr>
<tr><td>上市時間</td><td> ${OldEmpBeanCompany.launch_date}</td><td>${NewEmpBeanCompany.launch_date}</td></tr>
<tr><td>資本額</td><td> 	${OldEmpBeanCompany.capital_amount}</td><td>${NewEmpBeanCompany.capital_amount}</td></tr>
<tr><td>公司地址</td><td> ${OldEmpBeanCompany.company_addrs}</td><td>${NewEmpBeanCompany.company_addrs}</td></tr>
<tr><td>是否上市</td><td> ${OldEmpBeanCompany.isestablish}</td><td>${NewEmpBeanCompany.isestablish}</td></tr>
</table>
<hr>
<div>

<h1>已刪除<b style="color:red;">${count}</b>筆資料</h1>
</div>
</body>
</html>