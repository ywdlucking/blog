<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>评论审核页面</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/themes/icon.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">
	
	function pass(state){
		var selectedRows=$("#dg").datagrid("getSelections");
		if(selectedRows.length==0){
			$.messager.alert("系统提示","请选择要审核的评论");
			return;
		}
		var strIds=[];
		for(var i=0;i<selectedRows.length;i++){
			strIds.push(selectedRows[i].id);
		}
		var ids=strIds.join(",");
		$.messager.confirm("系统提示","您确定要审核这<font color=red>"+selectedRows.length+"</font>条评论吗？",function(r){
			if(r){
				$.post("${pageContext.request.contextPath}/admin/comment/pass.do",{ids:ids,state:state},function(result){
					if(result){
						$.messager.alert("系统提示","审核成功");
						$("#dg").datagrid("reload");
					}else{
						$.messager.alert("系统提示","审核失败！");
					}
				},"json");
			}
		});
	}
	
</script>
</head>
<body style="margin: 1px;">
<table id="dg" title="评论审核管理" class="easyui-datagrid" 
  fitColumns="true" pagination="true" rownumbers="true"
  url="${pageContext.request.contextPath}/admin/comment/list.do?state=0&type=3" fit="true" toolbar="#tb">
  <thead>
  	<tr>
  		<th field="cb" checkbox="true" align="center"></th>
  		<th field="id" width="20" align="center">编号</th>
  		<th field="commentName" width="50" align="center">留言人</th>
  		<th field="content" width="100" align="center">内容</th>
  		<th field="commentDate" width="50" align="center">时间</th>
  	</tr>
  </thead>
</table>
<div id="tb">
	<div>
		<a href="javascript:pass(1)" class="easyui-linkbutton" iconCls="icon-ok" plain="true">通过审核</a>
		<a href="javascript:pass(2)" class="easyui-linkbutton" iconCls="icon-cancel" plain="true">不通过</a>
	</div>
</div>

</body>
</html>