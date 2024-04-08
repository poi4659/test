<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> 부서 수정 </title>
</head>
<body>
<script type="text/javascript">
//경고창으로 확인 내용을 설정한다. 
alert("입력하신 ${deptDTO.deptno}번의 부서 번호를 수정하였습니다.");
//전체 URL 주소를 반환하여 이동할 웹 페이지를 설정한다. 
location.href="./DeptSelect.do";
</script>
</body>
</html>