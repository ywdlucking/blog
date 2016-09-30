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
			<a href="/">留言板</a>
		</div>
	    <div class="datas">
	      	<div class="modal-body-p">
	      	  	<div style="margin-top: 20px;"><label><span style="color: red;">*</span>留言箱（必填）</label></div>
	      	  	<div><textarea id="message_textarea" oninput="limitTextAreaM()" rows="" cols="" placeholder="感谢你留言，审核后显示(400字以内)，谢谢您的支持!"></textarea></div>
	      	  	<div style="float: right;">还可以输入<span id="message_counter">400</span>个字符</div>
	      	  	<div style="margin-top: 20px;"><label><span style="color: red;">*</span>你的昵称</label></div>
	      	  	<div><input id="message_name" type="text" placeholder="请告诉我，该怎么称呼你"/></div>
	      	  	<div style="margin-top: 20px;"><label>你的邮箱</label></div>
	      	  	<div><input id="message_email" type="text" placeholder="请留下您的联系邮箱，以便我们及时回复您（邮箱保密，拒绝骚扰）"/></div>
	      	  	<div style="margin-bottom: 15px;margin-top: 5px;"><button type="button" class="btn btn-primary" onclick="submitM()">提交</button></div>
	      	</div>
		</div>
	    
	    <hr style="height:2px;border:none;border-top:2px dotted #185598;">
	    <h3 style="font-weight: bold;font-size: 18px;text-align: center;">留言墙</h3>
	    <hr style="height:2px;border:none;border-top:2px dotted #185598;">
	    <div>
	    	<c:forEach var="message" items="${messages }">
				<div class="leave-message lem">
					<figure><img src="${pageContext.request.contextPath}/static/tx/${message.picture }"></figure>
					<ul>
						<p>${message.content }</p>
						<p class="autor1">
							<span class="ps f_l">${message.commentName }</span>
							<span class="dtime f_l"><fmt:formatDate value="${message.commentDate }" type="date" pattern="yyyy年MM月dd日  HH:mm:ss"/></span>
						</p>
					</ul>
					<c:if test="${message.reComment != '' }">
						<div class="leave-message mg">
							<figure><img src="${pageContext.request.contextPath}/static/tx/bz.gif"></figure>
							<ul>
								<p>${message.reComment }</p>
								<p class="autor1">
									<span class="ps1 f_l">土豆</span>
									<span class="dtime f_l"><fmt:formatDate value="${message.reCommentDate }" type="date" pattern="yyyy年MM月dd日  HH:mm:ss"/></span>
								</p>
							</ul>
						</div>
					</c:if>
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
<script type="text/javascript">
	function limitTextAreaM() {
		var max = 400;
		var field = $("#message_textarea").val();
		if(field.length>max){
			field = field.substring(0,max);
		}else{
			$("#message_counter").text(max-field.length);
		}
		$("#message_textarea").val(field)
	}
	
	function submitM() {
		var content = $("#message_textarea").val();
		var commentName = $("#message_name").val();
		var email = $("#message_email").val().replace(/(^\s*)|(\s*$)/g, "");
		if(content == "" || commentName == ""){
			window.wxc.xcConfirm("请填写留言以及你的昵称", window.wxc.xcConfirm.typeEnum.info);
			return;
		}
		$.ajax({
			type : 'POST',
			url : '${pageContext.request.contextPath}/comment/saveLeaveWord.do',
			data : {
				content:content,
				commentName:commentName,
				email:email
			},
			dataType:'json',
			success : function(result){
				if(result.success){
					window.wxc.xcConfirm("反馈信息提交成功", window.wxc.xcConfirm.typeEnum.success);
					$("#message_textarea").val("");
					$("#message_name").val("");
					$("#message_email").val("");
				}else{
					window.wxc.xcConfirm("反馈信息提交失败", window.wxc.xcConfirm.typeEnum.error);
				}
			}
		});
	}
</script>
</div>