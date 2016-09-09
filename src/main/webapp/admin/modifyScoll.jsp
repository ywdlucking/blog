<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改博客</title>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/themes/icon.css">
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/jquery.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath }/static/ueditor/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath }/static/ueditor/ueditor.all.min.js"> </script>
    <!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
    <!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
    <script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath }/static/ueditor/lang/zh-cn/zh-cn.js"></script>
</head>
<body style="margin: 10px">
	<div id="p" class="easyui-panel" title="修改博客" style="padding: 10px">
		<table cellspacing="20px">
			<tr>
				<td width="80px">滚屏博客标题：</td>
				<td>
					<input type="text" id="client" name="client" style="width: 400px"/>
				</td>
			</tr>
			<tr>
				<td>排列编号：</td>
				<td>
					<input type="text" id="titleId" name="titleId" style="width: 400px"/>
				</td>
			</tr>
			<tr>
				<td valign="top">博客内容：</td>
				<td>
					<script id="editor" name="content" type="text/plain" style="width:900px;height:500px;"></script>
				</td>
			</tr>
			<tr>
				<td>描叙：</td>
				<td>
					<input type="text" id="desc" name="desc" style="width: 400px"/>
				</td>
			</tr>
			<tr>
				<td>博客配图：</td>
				<td>
					<input type="file" id="picture" name="picture"/>
				</td>
			</tr>
			<tr>
				<td></td>
				<td>
					<a href="javascript:submitData()" class="easyui-linkbutton" data-options="iconCls:'icon-submit'">修改博客</a>
				</td>
			</tr>
		</table>
	</div>
	
	
	<script type="text/javascript">
		//实例化编辑器
	    //建议使用工厂方法getEditor创建和引用编辑器实例，如果在某个闭包下引用该编辑器，直接调用UE.getEditor('editor')就能拿到相关的实例
	    var ue = UE.getEditor('editor');
		
	    ue.addListener("ready",function(){
	    	// 通过ajax请求数据
	    	UE.ajax.request("${pageContext.request.contextPath}/admin/scoll/modifyByBlogId.do",
	    			{
	    				method:"post",
	    				async:false,
	    				data:{"id":"${param.id}"},
	    				onsuccess:function(result){
	    					result=eval("("+result.responseText+")");
	    					$("#client").val(result.client);
	    					$("#desc").val(result.desc);
	    					$("#titleId").val(result.titleId);
	    					UE.getEditor('editor').setContent(result.content);
	    				}
	   			});
	    });
	    
		function submitData(){
		    var client=$("#client").val();
			var titleId=$("#titleId").val();
			var content=UE.getEditor('editor').getContent();
			var desc=$("#desc").val();
			var picture=$('#picture').val();
			
			if(client==null || client==''){
				alert("请输入标题！");
			}else if(titleId==null || titleId==''){
				alert("请选择博客类别！");
			}else if(content==null || content==''){
				alert("请填写内容！");
			}else{
				$.post("${pageContext.request.contextPath}/admin/scoll/save.do",
						{
					'id':'${param.id}',
					'client':client,
					'titleId':titleId,
					'content':content,
					'picture':picture,
					'desc':desc},
					function(result){
					if(result){
						alert("博客修改成功！");
					}else{
						alert("博客修改失败！");
					}
				},"json");
				if(picture !== ""){
					var formData = new FormData();
					formData.append('picture', $('#picture')[0].files[0]);
					 $.ajax({
					      type: "POST",
					      url: "${pageContext.request.contextPath}/admin/blog/savepic.do",
					      cache: false,
					      data: formData,
					      processData: false,
					      contentType: false
					    }).done(function(res) {}).fail(function(res) {});					
				}
			}
		}
	
	</script>
</body>
</html>