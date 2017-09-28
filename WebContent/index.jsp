<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html >
<html>
<head>
<HEAD>
<TITLE>客户关系管理系统</TITLE>
<META http-equiv=Content-Type content="text/html; charset=utf-8">
<META content="MSHTML 6.00.2900.3492" name=GENERATOR>
</HEAD>
<c:if test="${empty sessionScope.user}">
	<script type="text/javascript">
		window.location.href = "<c:url value="/login.jsp"></c:url>"
	</script>
</c:if>
<FRAMESET frameSpacing=0 rows=80,* frameBorder=0>
	<FRAME name=top src="top.jsp" frameBorder=0 noResize scrolling=no>
	<FRAMESET frameSpacing=0 frameBorder=0 cols=220,*>
		<FRAME name=menu src="menu.jsp" frameBorder=0 noResize>
		<FRAME name=main src="welcome.htm" frameBorder=0>
</FRAMESET>
	<NOFRAMES>
		<p>This page requires frames, but your browser does not support
			them.</p>
</NOFRAMES>
</FRAMESET>

</html>