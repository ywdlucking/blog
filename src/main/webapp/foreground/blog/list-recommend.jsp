<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
	<div class="l_box f_l">
		<div class="banner">
			<div id="slide-holder">
				<div id="slide-runner">
					<c:forEach var="scoll" items="${scolls }">
						<a href="${pageContext.request.contextPath}/blog/scoll/${scoll.id }.html" target="_blank">
							<img id="slide-img-${scoll.titleId }" src="${pageContext.request.contextPath}/static/picture/${scoll.picture }" alt="" />
						</a>
					</c:forEach>
					<div id="slide-controls">
						<p id="slide-client" class="text">
							<strong></strong><span></span>
						</p>
						<p id="slide-desc" class="text"></p>
						<p id="slide-nav"></p>
					</div>
				</div>
			</div>
			<script>
				if (!window.slider) {
					var slider = {};
				}

				slider.data = ${scollVO};
			</script>
		</div>
	    <div class="topnews">
	      	<h2><b>文章</b>推荐</h2>
			<c:forEach var="blog" items="${listBlog }">
				<div class="blogs">
					<figure><img src="${pageContext.request.contextPath}/static/picture/${blog.picture }"></figure>
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
    </div>	