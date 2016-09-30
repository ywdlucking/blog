<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>评论审核页面</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/themes/icon.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/base.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">

	var url;
	
	function deleteComment(){
		var selectedRows=$("#dg").datagrid("getSelections");
		if(selectedRows.length==0){
			$.messager.alert("系统提示","请选择要删除的评论");
			return;
		}
		var strIds=[];
		for(var i=0;i<selectedRows.length;i++){
			strIds.push(selectedRows[i].id);
		}
		var ids=strIds.join(",");
		$.messager.confirm("系统提示","您确定要删除这<font color=red>"+selectedRows.length+"</font>条留言吗？",function(r){
			if(r){
				$.post("${pageContext.request.contextPath}/admin/comment/delete.do",{ids:ids},function(result){
					if(result){
						$.messager.alert("系统提示","删除成功");
						$("#dg").datagrid("reload");
					}else{
						$.messager.alert("系统提示","删除失败！");
					}
				},"json");
			}
		});
	}
	
	function openWordsModifyDialog() {
		var selectedRows=$("#dg").datagrid("getSelections");
		if(selectedRows.length!=1){
			$.messager.alert("系统提示","请选择一个要修改的博客！");
			return;
		}
		var row=selectedRows[0];
		$("#dlg").dialog("open").dialog("setTitle","回复留言");
		$("#fm").form("load",row);
		url="${pageContext.request.contextPath}/admin/comment/update.do?id="+row.id;
	}
	
	function saveWordsBack() {
		$("#fm").form("submit",{
			url:url,
			onSubmit:function(){
				return $(this).form("validate");
			},
			success:function(result){
				if(result){
					$.messager.alert("系统提示","保存成功！");
					$("#wordsBack").val("");
					$("#dlg").dialog("close");
					$("#dg").datagrid("reload");
				}else{
					$.messager.alert("系统提示","保存失败！");
					return;
				}
			}
		});
	}
	
</script>
</head>
<body style="margin: 1px;">
<table id="dg" title="评论审核管理" class="easyui-datagrid" 
  fitColumns="true" pagination="true" rownumbers="true"
  url="${pageContext.request.contextPath}/admin/comment/list.do?type=3&state=1" fit="true" toolbar="#tb">
  <thead>
  	<tr>
  		<th field="cb" checkbox="true" align="center"></th>
  		<th field="id" width="20" align="center">编号</th>
  		<th field="commentName" width="50" align="center">留言者</th>
  		<th field="content" width="50" align="content">留言内容</th>
  		<th field="commentDate" width="50" align="center">留言时间</th>
  		<th field="reComment" width="50" align="content">回复内容</th>
  		<th field="reCommentDate" width="50" align="center">回复时间</th>
  	</tr>
  </thead>
</table>
<div id="tb">
	<div>
		<a href="javascript:openWordsModifyDialog()" class="easyui-linkbutton" iconCls="icon-edit" plain="true">回复</a>
		<a href="javascript:deleteComment()" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
	</div>
</div>

<div id="dlg" class="easyui-dialog" style="width: 500px;height: 300px;padding: 10px 20px" closed="true" buttons="#dlg-buttons">
	<form id="fm" method="post">
		<div><label><span style="color: red;">*</span>回复内容</label></div>
		<div class="modal-body-p"><textarea id="wordsBack" name="wordsBack" rows="" cols=""></textarea></div>
	</form>
</div>

<div id="dlg-buttons">
	<a href="javascript:saveWordsBack()" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
	<a href="javascript:closeBlogTypeDialog()" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
</div>
</body>
</html>