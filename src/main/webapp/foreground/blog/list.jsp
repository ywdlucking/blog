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
			<a href="/">博客</a>
		</div>
	    <div class="datas">
	        <c:forEach var="blog" items="${listBlog }">
				<div class="blogs">
					<figure><img src="${pageContext.request.contextPath}/static/images/${blog.picture }"></figure>
					<ul>
						<h3><a href="${pageContext.request.contextPath}/blog/articles/${blog.id }.html">${blog.title }</a></h3>
						<p>摘要: ${blog.summary }...</p>
						<p class="autor">
							<span class="lm f_l"><a href="/">${blog.blogType.typeName }</a></span><span class="dtime f_l"><fmt:formatDate value="${blog.releaseDate }" type="date" pattern="yyyy年MM月dd日"/></span>
							<span class="viewnum f_r">浏览（<a href="/">${blog.clickHit }</a>）</span>
							<span class="pingl f_r">评论（<a href="/">${blog.replyHit }</a>）</span>
						</p>
					</ul>
				</div> 		
			</c:forEach>
	    </div>
	    
	    <div>
	    	<nav>
		    	<ul class="pagination pagination-sm">
		    		${pageCode }
		    	</ul>
	    	</nav>
	    </div>
	    
	</div>
</div>