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
	function formatBlogTitle(val,row){
		if(val == null){
			return "<font color='red'>该博客已经被删除</font>";
		}else{
			return "<a target='_blank' href='${pageContext.request.contextPath}/blog/articles/"+val.id+".html'>"+val.title+"</a>";
		}
	}
	
	function formatBlogState(val,row){
		if(val == null){
			return "<font color='red'>状态出错，请排查原因</font>";
		}else if(val == 0){
			return "未审核";
		}else if(val == 1){
			return "审核通过";
		}else if(val == 2){
			return "未通过";
		}
	}
	
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
		$.messager.confirm("系统提示","您确定要删除这<font color=red>"+selectedRows.length+"</font>条评论吗？",function(r){
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
	
	function doSearch(value,name){
		$("#dg").datagrid('load',{
			"blogId":value,
			"state":name
		});
	}
	
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
  url="${pageContext.request.contextPath}/admin/comment/list.do" fit="true" toolbar="#tb">
  <thead>
  	<tr>
  		<th field="cb" checkbox="true" align="center"></th>
  		<th field="id" width="20" align="center">编号</th>
  		<th field="blog" width="100" align="center" formatter="formatBlogTitle">博客标题</th>
  		<th field="commentName" width="50" align="center">评论者</th>
  		<th field="commentDate" width="50" align="center">评论时间</th>
  		<th field="state" width="50" align="center" formatter="formatBlogState">审核状态</th>
  	</tr>
  </thead>
</table>
<div id="tb">
	<div>
		<a href="javascript:deleteComment()" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
		<a href="javascript:pass(2)" class="easyui-linkbutton" iconCls="icon-cancel" plain="true">不通过</a>
	</div>
	<div style="margin: 5px 5px;">
		<input class="easyui-searchbox" data-options="prompt:'请输入博客的ID...',menu:'#mm',searcher:doSearch" style="width:250px"></input>
	</div>
</div>

<div id="mm" style="width:120px">
    <div data-options="name:'all',iconCls:'icon-ok'">所有</div>
    <div data-options="name:'0'">未审核</div>
    <div data-options="name:'1'">审核</div>
    <div data-options="name:'2'">未通过</div>
</div>


<div id="dlg" class="easyui-dialog" style="width: 500px;height: 180px;padding: 10px 20px" closed="true" buttons="#dlg-buttons">
	<form id="fm" method="post">
		<table cellspacing="8px">
			<tr>
				<td>博客类别名称：</td>
				<td>
					<input type="text" id="typeName" name="typeName" class="easyui-validatebox" required="true"/>
				</td>
			</tr>
			<tr>
				<td>博客类别排序：</td>
				<td>
					<input type="text" id="orderNo" name="orderNo" class="easyui-numberbox" required="true" style="width: 60px"/>&nbsp;(类别根据排序序号从小到大排序)
				</td>
			</tr>
		</table>
	</form>
</div>

<div id="dlg-buttons">
	<a href="javascript:saveBlogType()" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
	<a href="javascript:closeBlogTypeDialog()" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
</div>
</body>
</html>