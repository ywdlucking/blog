<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${pageTitle }</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/bootstrap3/css/bootstrap.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/bootstrap3/css/bootstrap-theme.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/base.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/index.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/xcConfirm.css">
<script src="${pageContext.request.contextPath}/static/bootstrap3/js/jquery-1.11.2.min.js"></script>
<script src="${pageContext.request.contextPath}/static/bootstrap3/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/static/js/html5.js"></script> 
<script src="${pageContext.request.contextPath}/static/js/sliders.js"></script>
<script src="${pageContext.request.contextPath}/static/js/xcConfirm.js"></script>
</head>
<body>
	<jsp:include page="/foreground/common/head.jsp"/>
	<article>	
		<jsp:include page="${mainPage }"/>
		<jsp:include page="/foreground/blog/list-right-sider.jsp"/>
	</article>	
	<jsp:include page="/foreground/common/foot.jsp" />
</body>
</html>