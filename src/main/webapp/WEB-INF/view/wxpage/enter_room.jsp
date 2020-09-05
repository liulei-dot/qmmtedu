<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.*,com.qmmt.edu.util.*,com.qmmt.edu.pojo.*" %>
<%

String jsdata = (String)request.getAttribute("jsdata");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
</head>
<body onload="open()">
<script type="text/javascript">

			function open(){
				<%if(jsdata != null){ %>
				window.location.href='http://api.baijiacloud.com/web/room/enter?<%=jsdata%>';
				<%} %>
			}
</script>

<%if(jsdata == null){ %>
无法进入直播课堂，可能您的输入码有误！
<%} %>
</body>
</html>
