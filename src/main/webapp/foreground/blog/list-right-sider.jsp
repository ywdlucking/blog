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
            <c:forEach var="blog" items="${listBlogTop }">
	          	<li><a href="${pageContext.request.contextPath}/blog/articles/${blog.id }.html" target="_blank">${blog.title }</a></li>
	        </c:forEach>
          </ul>
        </div>
        <div  class="bd bd-news">
          <ul>
            <c:forEach var="blog" items="${listBlogNew }">
	          	<li><a href="${pageContext.request.contextPath}/blog/articles/${blog.id }.html" target="_blank">${blog.title }</a></li>
	          </c:forEach>
          </ul>
        </div>
        <div class="bd bd-news">
          <ul>
	          <c:forEach var="blog" items="${listBlogRecommend }">
	          	<li><a href="${pageContext.request.contextPath}/blog/articles/${blog.id }.html" target="_blank">${blog.title }</a></li>
	          </c:forEach>
          </ul>
        </div>
      </div>      
    </div>
    
    <div class="cloud">
      <h3>日志类别</h3>
      <ul>
        <c:forEach var="blogType" items="${blogTypes }">
			<li><a href="${pageContext.request.contextPath}/article.html?typeId=${blogType.id }">${blogType.typeName }(${blogType.blogCount })</a></li>
		 </c:forEach>
      </ul>
    </div>
    
    <div class="cloud">
      <h3>时间列表</h3>
      <ul>
		 <c:forEach var="blog" items="${blogs }">
			<li><span><a href="${pageContext.request.contextPath}/article.html?releaseDateStr=${blog.releaseDateStr }">${blog.releaseDateStr }(${blog.blogCount })</a></span></li>
		</c:forEach>
	  </ul>
    </div>
    
    <div class="ad"> <img src="${pageContext.request.contextPath}/static/picture/01.jpg"> </div>
    
    <div class="links">
      <h3><span>[<a href="javascript:$('#askfor_link').modal('show')">申请友情链接</a>]</span>友情链接</h3>
      <ul>
        <c:forEach var="link" items="${links }">
			<li><a href="${link.linkUrl }" target="_blank">${link.linkName }</a></li>							
		</c:forEach>
      </ul>
    </div>
</div>

<script>

	function limitTextArea() {
		var max = 400;
		var field = $("#askfor_textarea").val();
		if(field.length>max){
			field = field.substring(0,max);
		}else{
			$("#askfor_counter").text(max-field.length);
		}
		$("#askfor_textarea").val(field)
	}
	function askforLink() {
		var content = $("#askfor_textarea").val();
		var link = $("#askfor_linkUrl").val();
		if(content == "" || link == ""){
			$('#askfor_link').modal('hide');
			window.wxc.xcConfirm("请填写反馈信息", window.wxc.xcConfirm.typeEnum.info);
		}else{
			$.ajax({
				type : 'POST',
				url : '${pageContext.request.contextPath}/link/save.do',
				data : {
					linkDesc:content,
					linkUrl:link
				},
				dataType:'json',
				success : function(result){
					if(result.success){
						window.wxc.xcConfirm("反馈信息提交成功", window.wxc.xcConfirm.typeEnum.success);
						$('#askfor_link').modal('hide');
						$("#askfor_textarea").val("");
						$("#askfor_link").val("");
					}else{
						window.wxc.xcConfirm("反馈信息提交失败", window.wxc.xcConfirm.typeEnum.error);
					}
				}
			});
		}
	}
</script>

<div class="modal fade" id="askfor_link" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
        <h4 class="modal-title" id="myModalLabel"><span style="font-weight: bold;">友情连接申请</span></h4>
      </div>
      <div class="modal-body">
      	<div class="modal-body-p">
      	  	<div><label><span style="color: red;">*</span>推荐理由</label></div>
      	  	<div><textarea id="askfor_textarea" oninput="limitTextArea()" rows="" cols="" placeholder="请对网站简单说明"></textarea></div>
      	  	<div style="float: right;">还可以输入<span id="askfor_counter">400</span>个字符</div>
      	  	<div style="margin-top: 20px;"><label><span style="color: red;">*</span>地址链接（必填）</label></div>
      	  	<div><input id="askfor_linkUrl" type="text" placeholder="如：http://www.100java.com/" /></div>
      	</div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
        <button type="button" class="btn btn-success" onclick="askforLink()">提交</button>
      </div>
    </div>
  </div>
</div>