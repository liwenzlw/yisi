<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	String basepath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/";
%>
<form class="form-horizontal" action="foodArticle/upload"
	enctype="multipart/form-data" method="post">
	<div class="form-group">
		<label for="type" class="col-sm-2 control-label">二级分类</label>
		<div class="col-sm-8">
			<select name="type" id="type">
				<option value="1">吃粉</option>
				<option value="2">龙虾</option>
			</select>
		</div>
	</div>
	<div class="form-group">
		<label for="title" class="col-sm-2 control-label">文章标题</label>
		<div class="col-sm-8">
			<input type="text" class="form-control" id="title" name="title"
				placeholder="请输入标题">
		</div>
	</div>
	<div class="form-group">
		<label for="imgFile" class="col-sm-2 control-label">文章图标</label>
		<div class="col-sm-8">
			<input type="file" id="imgFile" name="imgFile">
		</div>
	</div>
	<div class="form-group">
		<div class="col-sm-8 col-sm-offset-2">
			<!-- 加载编辑器的容器 -->
			<script id="container" name="content" type="text/plain">
这里写你的初始化内容
				</script>
		</div>
	</div>

	<div class="form-group">
		<div class="col-sm-offset-2 col-sm-8">
			<button type="submit" class="btn btn-default">保存数据</button>
		</div>
	</div>
</form>

<!--富文本编辑器 start -->
<script>
window.UEDITOR_HOME_URL = "<%=basepath%>vender/ueditor/";
</script>
<!-- 配置文件 -->
<script type="text/javascript"
	src="<%=basepath%>vender/ueditor/ueditor.config.js"></script>
<!-- 编辑器源码文件 -->
<script type="text/javascript"
	src="<%=basepath%>vender/ueditor/ueditor.all.js"></script>
<!-- 实例化编辑器 -->
<script type="text/javascript">
	var editor = UE.getEditor('container', {
		enableAutoSave : false,
		enableAutoSave : false,
		autoSyncData : false,
		enableContextMenu : false,
	});
</script>
<!--富文本编辑器 end -->
