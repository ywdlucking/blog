<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div class="l_box f_l">
	<div class="data_list">
	   <div class="data_list_title">
			<img src="/static/images/search_icon.png"/>
			搜索&nbsp;<font color="red">${q }</font>&nbsp;的结果 &nbsp;(总共搜索到&nbsp;${resultTotal }&nbsp;条记录) 
			</div>
	    <div class="datas">
		    <c:choose> 
				<c:when test="${fn:length(blogList)==0}">
					<p class="no-results">未查到关于<font color="red">${q }</font>的任何结果</p>						
				</c:when> 
				<c:otherwise>
					<c:forEach var="blog" items="${blogList }">
						<div class="results">
							<ul>
								<h3><a href="${pageContext.request.contextPath}/blog/articles/${blog.id }.html">${blog.title }</a></h3>
								<p class="time">
									<span class="dtime ml">${blog.releaseDateStr }</span>
								</p>
								<c:choose> 
									<c:when test="${fn:length(blog.content)>155}">
										<p>摘要: ${fn:substring(blog.content,0,155) }</p>						
									</c:when> 
									<c:otherwise>
										<p>摘要: ${blog.content }</p>	
									</c:otherwise>
								</c:choose> 
							</ul>
						</div> 		
					</c:forEach>
				</c:otherwise>
			</c:choose> 
	        
	    </div>
	    
	    <div>
	    	<nav>
		    	<ul class="pagination pagination-sm">
		    		${pagecode }
		    	</ul>
	    	</nav>
	    </div>
	    
	</div>
</div>