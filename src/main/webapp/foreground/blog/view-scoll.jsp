<script type="text/javascript" src="${pageContext.request.contextPath}/static/ueditor/third-party/SyntaxHighlighter/shCore.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/ueditor/third-party/SyntaxHighlighter/shCoreDefault.css">
<script type="text/javascript">
    SyntaxHighlighter.all();
</script>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<div class="l_box f_l">
	<div class="data_list">
		<div class="data_list_title">
			<img src="/static/images/blog_show_icon.png"/>
			<a href="${pageContext.request.contextPath}/index.html">首页</a>
			<span>></span>
			<a href="${pageContext.request.contextPath}/article.html">博客</a>
			<span>></span>
			<a href="">博客内容</a>
		</div>
		<div>
			<div class="blog_title"><h3><strong>${scoll.client }</strong></h3></div>
			<div class="blog_share">
				<div class="bshare-custom">
					<a title="分享到QQ空间" class="bshare-qzone"></a>
					<a title="分享到新浪微博" class="bshare-sinaminiblog"></a>
					<a title="分享到人人网" class="bshare-renren"></a>
					<a title="分享到腾讯微博" class="bshare-qqmb"></a>
					<a title="分享到网易微博" class="bshare-neteasemb"></a>
					<a title="更多平台" class="bshare-more bshare-more-icon more-style-addthis"></a>
					<span class="BSHARE_COUNT bshare-share-count">0</span>
				</div>
				<script type="text/javascript" charset="utf-8" src="http://static.bshare.cn/b/buttonLite.js#style=-1&amp;uuid=&amp;pophcol=2&amp;lang=zh"></script><script type="text/javascript" charset="utf-8" src="http://static.bshare.cn/b/bshareC0.js"></script>
			</div>
			<div class="blog_info">
				发布时间：『 <fmt:formatDate value="${scoll.releaseDate }" type="date" pattern="yyyy-MM-dd HH:mm"/>』
			</div>
			<div class="blog_content">
			${scoll.content }
			</div>
		</div>
	</div>
</div>