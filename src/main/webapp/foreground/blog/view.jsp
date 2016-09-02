<script type="text/javascript" src="${pageContext.request.contextPath}/static/ueditor/third-party/SyntaxHighlighter/shCore.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/ueditor/third-party/SyntaxHighlighter/shCoreDefault.css">
<script type="text/javascript">
    SyntaxHighlighter.all();
    
    function showOtherComment(){
    	$(".otherComment").show();
    	$(".showall").hide();
    }
    
    function loadimage(){
		document.getElementById("randImage").src="${pageContext.request.contextPath}/image.jsp?"+Math.random();
	}
    
    function submitData(){
    	var content=$("#content").val();
    	var imageCode=$("#imageCode").val();
    	var commentName=$("#commentName").val();
    	var email=$("#email").val();
    	if(content==null || content==""){
    		alert("请输入评论内容！");
    	}else if(imageCode==null || imageCode==""){
    		alert("请填写验证码！");
    	}else{
    		$.post("${pageContext.request.contextPath}/comment/save.do",
    				{
		    			"content":content,
		    			'commentName':commentName,
		    			'email':email,
		    			'imageCode':imageCode,
		    			'blog.id':'${blog.id}'
    				},function(result){
		    			if(result.success){
		    				window.location.reload();
		    				alert("评论已提成成功，审核通过后显示！");
		    			}else{
		    				alert(result.errorInfo);
		    			}
		    		},"json");
    	}
    }
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
			<div class="blog_title"><h3><strong>${blog.title }</strong></h3></div>
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
				发布时间：『 <fmt:formatDate value="${blog.releaseDate }" type="date" pattern="yyyy-MM-dd HH:mm"/>』&nbsp;&nbsp;博客类别：${blog.blogType.typeName }&nbsp;&nbsp;阅读(${blog.clickHit }) 评论(${blog.replyHit })
			</div>
			<div class="blog_content">
			${blog.content }
			</div>
			<div class="blog_keyWord">
				<font><strong>关键字：</strong></font>
				<c:choose>
					<c:when test="${keyWords==null }">
						&nbsp;&nbsp;无
					</c:when>
					<c:otherwise>
						<c:forEach var="keyWord" items="${keyWords }">
							&nbsp;&nbsp;&nbsp;&nbsp;<a href="${pageContext.request.contextPath}/blog/q.html?q=${keyWord }" target="_blank">${keyWord }</a>&nbsp;&nbsp;
						</c:forEach>
					</c:otherwise>
				</c:choose>
			</div>
			<div class="blog_lastAndNextPage">
				${pageCode }
			</div>
		</div>
	</div>
</div>
<!--  <div class="data_list">
	<div class="data_list_title">
		<img src="/static/images/comment_icon.png"/>
		评论信息
	</div>
	<div class="commentDatas">
		<c:choose>
			<c:when test="${comments.size() == 0 }">
				暂无评论
			</c:when>
			<c:otherwise>
				<c:forEach var="comment" items="${comments }" varStatus="status">
					<c:choose>
						<c:when test="${status.index<10 }" >
							<div class="comment">
								<span><font>${status.index+1 }楼&nbsp;&nbsp;&nbsp;[&nbsp;<fmt:formatDate value="${comment.commentDate }" type="date" pattern="yyyy-MM-dd HH:mm"/>&nbsp;]</font></span>	
								<p>${comment.commentName }&nbsp;<font>&raquo;说：</font>${comment.content }</p>
								<c:if test="${not empty comment.reComment}">
								    <p>${blogger.nickName }&nbsp;<font>&raquo;答复：</font>${comment.reComment.reComment }</p>
								</c:if>
							</div>
						</c:when>
						
						<c:otherwise>
							<div class="otherComment">
								<span><font>${status.index+1 }楼&nbsp;&nbsp;&nbsp;[&nbsp;<fmt:formatDate value="${comment.commentDate }" type="date" pattern="yyyy-MM-dd HH:mm"/>&nbsp;]</font></span>	
								<p>${comment.commentName }&nbsp;<font>&raquo;说：</font>${comment.content }</p>		
								<c:if test="${not empty comment.reComment}">
								    <p>${blogger.nickName }&nbsp;<font>&raquo;答复：</font>${comment.reComment.reComment }</p>
								</c:if>
							</div>
						</c:otherwise>
					</c:choose>
				</c:forEach>
				<c:if test="${comments.size()>10 }">
					<div class="showall">
						<a href="javascript:showOtherComment()" >显示所有评论</a>
					</div>
				</c:if>
			</c:otherwise>
		</c:choose>
	</div>
</div>  -->

<!-- <div class="data_list" >
	<div class="data_list_title">
		<img src="/static/images/publish_comment_icon.png"/>
		发表评论
	</div>
	<div class="publish_comment">
		<form>
			<div>
				<textarea style="width: 100%" rows="3" id="content" name="content" placeholder="欢迎吐槽..."></textarea>
			</div>
			<div class="verCode">
				<div class="input-group cm_info">
				  <span class="input-group-addon" id="basic-addon1">昵称</span>
				  <input type="text" class="form-control" placeholder="这个必须填写哦" aria-describedby="basic-addon1" name="commentName" id="commentName" size="10">
				</div>
				<div class="input-group cm_info">
				  <span class="input-group-addon" id="basic-addon2">邮箱</span>
				  <input type="text" class="form-control" placeholder="博主回复将直接@你，是保密哦" aria-describedby="basic-addon1" name="email" id="email" size="10">
				</div>
				<div class="input-group cm_info">
				  <span class="input-group-addon" id="basic-addon3">验证</span>
				  <input type="text" class="form-control" placeholder="验证码就在下面哦" aria-describedby="basic-addon1" name="imageCode" id="imageCode" size="10">
				</div>
				<img onclick="javascript:loadimage();"  title="换一张试试" name="randImage" id="randImage" src="/image.jsp" width="120" height="34" border="1" align="absmiddle">
			</div>
			<div class="publishButton">
				<button class="btn btn-primary" type="button" onclick="submitData()">发表评论</button>&nbsp;&nbsp;&nbsp;<span>(评论需要被审核才能显示哦，请耐心等待)</span>
			</div>
		</form>
	</div>
</div> -->