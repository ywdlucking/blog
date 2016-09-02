<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="l_box f_l">    
	<div class="data_list">
	 		<div class="data_list_title">
		<img src="${pageContext.request.contextPath}/static/images/about_icon.png"/>
		<a href="${pageContext.request.contextPath}/index.html">首页</a>
		<span>></span>
		<a href="/">关于博主</a>
	</div>
		<div style="padding: 30px">
			${blogger.profile }
		</div>
	</div>
</div>