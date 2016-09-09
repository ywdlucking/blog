<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div class="l_box f_l">
	<div class="data_list">
	   <div class="data_list_title">
			<img src="/static/images/blog_show_icon.png"/>
			<a href="${pageContext.request.contextPath}/index.html">首页</a>
			<span>></span>
			<a href="/">时间轮</a>
		</div>
	    <div class="datas">
	        <c:forEach var="time" items="${listTime }">
				<div class="times">
					<ul class="arrow_box">
						<div class="sy">
							<c:if test="${time.picture !=null && time.picture !=''}">
								<img src="${pageContext.request.contextPath}/static/picture/${time.picture}">
							</c:if>
							<p>${time.context}</p>
						</div>
						<span class="dateview"><fmt:formatDate value="${time.saydate }" type="date" pattern="yyyy-MM-dd"/></span>
						<div class="drawline"></div>
					</ul>
				</div> 		
			</c:forEach>
		</div>
	    
	    <div class="page">
	    	${pageCode }
	    </div>
	    
	</div>
</div>