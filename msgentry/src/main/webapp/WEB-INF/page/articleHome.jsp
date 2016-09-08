<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	String basepath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/";
%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="UTF-8">
<title>后台数据录入</title>
<style>
body {
	margin: 0;
	padding: 0;
	min-width: 1200px;
}
</style>
</head>
<body class="container">
	<div class="row">
		<div class="panel panel-default" style="margin-top: 10px">
			<div class="panel-heading">
				<!-- Nav tabs -->
				<ul class="nav nav-tabs" id="maintab">
					<li><a href="#editArticle"
						onclick='showPage("editArticle","<%=basepath%>getEidtPage")'>编辑数据</a></li>
					<li><a href="#settings" onclick='showPage("settings","")'>用户中心</a></li>
				</ul>
			</div>
			<div class="panel-body">
				<!-- Tab panes -->
				<div class="tab-content">
					<div class="tab-pane" id="editArticle" style="text-align: center;"></div>
					<div class="tab-pane" id="settings"></div>
				</div>
			</div>
		</div>
	</div>

</body>
<!-- 新 Bootstrap 核心 CSS 文件 -->
<link rel="stylesheet"
	href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css">

<!-- 可选的Bootstrap主题文件（一般不用引入） -->
<link rel="stylesheet"
	href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap-theme.min.css">
<link rel="stylesheet" type="text/css" media="screen"
	href="<%=basepath%>vender/jqGrid/css/ui.jqgrid-bootstrap.css" />	
<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>

<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="//cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script language="javascript">
	//var loadimg = "./img/load2.gif"; // 加载时的loading图片

	function showPage(tabId, url) {
		$('#maintab a[href="#' + tabId + '"]').tab('show'); // 显示点击的tab页面
		if ($('#' + tabId).html().length < 20 && url != "") { // 当tab页面内容小于20个字节时ajax加载新页面
			$('#' + tabId).html('<br>' + ' 页面加载中，请稍后...'); // 设置页面加载时的loading图片
			$('#' + tabId).load(url); // ajax加载页面
		}
	}
</script>


</html>