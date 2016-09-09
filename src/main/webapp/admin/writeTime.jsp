<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>写博客</title>
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
	<div id="p" class="easyui-panel" title="编写时间轴" style="padding: 10px">
		<table cellspacing="20px">
			<tr>
				<td valign="top">内容：</td>
				<td>
					<script id="editor" name="content" type="text/plain" style="width:900px;height:500px;"></script>
				</td>
			</tr>
			<tr>
				<td>配图：</td>
				<td>
					<input type="file" id="picture" name="picture"/>
				</td>
			</tr>
			<tr>
				<td></td>
				<td>
					<a href="javascript:submitData()" class="easyui-linkbutton" data-options="iconCls:'icon-submit'">添加时间轴</a>
				</td>
			</tr>
		</table>
	</div>

	<script type="text/javascript">
		//实例化编辑器
	    //建议使用工厂方法getEditor创建和引用编辑器实例，如果在某个闭包下引用该编辑器，直接调用UE.getEditor('editor')就能拿到相关的实例
	    var ue = UE.getEditor('editor');
		function submitData(){
			var content=UE.getEditor('editor').getContent();
			var picture=$("#picture").val()
			if(content==null || content==''){
				alert("请填写内容！");
			}else{
				$.post("${pageContext.request.contextPath}/admin/time/save.do",
						{
					'context':content,
					'picture':picture},
					function(result){
					if(result){
						alert("发布成功！");
						resultValue();
					}else{
						alert("发布失败！");
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
	
	function resultValue(){
		UE.getEditor('editor').setContent('');
	}
	</script>
</body>
</html>