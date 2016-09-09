<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div class="r_box f_r">
	 <div class="tit01">
      <h3>关注我</h3>
      <div class="gzwm">
        <ul>
          <li><a class="xlwb" href="#" target="_blank">新浪微博</a></li>
          <li><a class="txwb" href="#" target="_blank">腾讯微博</a></li>
          <li><a class="rss" href="portal.php?mod=rss" target="_blank">RSS</a></li>
          <li><a class="wx" href="mailto:2011ywd@sina.com">邮箱</a></li>
        </ul>
      </div>
    </div>
    <!--tit01 end-->
    <div class="ad"> <img src="${pageContext.request.contextPath}/static/picture/ad300x100.jpg"> </div>
    
    <div class="moreSelect" id="lp_right_select">
     <script>
		window.onload = function ()
		{
			var oLi = document.getElementById("tab").getElementsByTagName("li");
			var oUl = document.getElementById("ms-main").getElementsByTagName("div");
			
			for(var i = 0; i < oLi.length; i++)
			{
				oLi[i].index = i;
				oLi[i].onmouseover = function ()
				{
					for(var n = 0; n < oLi.length; n++) oLi[n].className="";
					this.className = "cur";
					for(var n = 0; n < oUl.length; n++) oUl[n].style.display = "none";
					oUl[this.index].style.display = "block"
				}	
			}
		}
	</script>
	<div class="ms-top">
        <ul class="hd" id="tab">
          <li class="cur"><a href="/">点击排行</a></li>
          <li><a href="/">最新文章</a></li>
          <li><a href="/">站长推荐</a></li>
        </ul>
     </div>
     <div class="ms-main" id="ms-main">
        <div style="display: block;" class="bd bd-news" >
          <ul>
            <li><a href="/" target="_blank">住在手机里的朋友</a></li>
            <li><a href="/" target="_blank">教你怎样用欠费手机拨打电话</a></li>
            <li><a href="/" target="_blank">原来以为，一个人的勇敢是，删掉他的手机号码...</a></li>
            <li><a href="/" target="_blank">手机的16个惊人小秘密，据说99.999%的人都不知</a></li>
            <li><a href="/" target="_blank">你面对的是生活而不是手机</a></li>
            <li><a href="/" target="_blank">豪雅手机正式发布! 在法国全手工打造的奢侈品</a></li>
          </ul>
        </div>
        <div  class="bd bd-news">
          <ul>
            <li><a href="/" target="_blank">原来以为，一个人的勇敢是，删掉他的手机号码...</a></li>
            <li><a href="/" target="_blank">手机的16个惊人小秘密，据说99.999%的人都不知</a></li>
            <li><a href="/" target="_blank">住在手机里的朋友</a></li>
            <li><a href="/" target="_blank">教你怎样用欠费手机拨打电话</a></li>
            <li><a href="/" target="_blank">你面对的是生活而不是手机</a></li>
            <li><a href="/" target="_blank">豪雅手机正式发布! 在法国全手工打造的奢侈品</a></li>
          </ul>
        </div>
        <div class="bd bd-news">
          <ul>
            <li><a href="/" target="_blank">手机的16个惊人小秘密，据说99.999%的人都不知</a></li>
            <li><a href="/" target="_blank">你面对的是生活而不是手机</a></li>
            <li><a href="/" target="_blank">住在手机里的朋友</a></li>
            <li><a href="/" target="_blank">豪雅手机正式发布! 在法国全手工打造的奢侈品</a></li>
            <li><a href="/" target="_blank">教你怎样用欠费手机拨打电话</a></li>
            <li><a href="/" target="_blank">原来以为，一个人的勇敢是，删掉他的手机号码...</a></li>
          </ul>
        </div>
      </div>      
    </div>
    
    <div class="cloud">
      <h3>日志类别</h3>
      <ul>
        <c:forEach var="blogType" items="${blogTypes }">
			<li><a href="${pageContext.request.contextPath}/index.html?typeId=${blogType.id }">${blogType.typeName }(${blogType.blogCount })</a></li>
		 </c:forEach>
      </ul>
    </div>
    
    <div class="cloud">
      <h3>时间列表</h3>
      <ul>
		 <c:forEach var="blog" items="${blogs }">
			<li><span><a href="${pageContext.request.contextPath}/index.html?releaseDateStr=${blog.releaseDateStr }">${blog.releaseDateStr }(${blog.blogCount })</a></span></li>
		</c:forEach>
	  </ul>
    </div>
    
    <div class="ad"> <img src="${pageContext.request.contextPath}/static/picture/01.jpg"> </div>
    
    <div class="links">
      <h3><span>[<a href="/">申请友情链接</a>]</span>友情链接</h3>
      <ul>
        <c:forEach var="link" items="${links }">
			<li><a href="${link.linkUrl }" target="_blank">${link.linkName }</a></li>							
		</c:forEach>
      </ul>
    </div>
</div>