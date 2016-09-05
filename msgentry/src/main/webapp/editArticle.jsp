<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	String basepath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/";
%>

<!-- We support more than 40 localizations -->
<script type="text/ecmascript"
	src="<%=basepath%>vender/jqGrid/js/i18n/grid.locale-en.js"></script>
<!-- This is the Javascript file of jqGrid -->
<script type="text/ecmascript"
	src="<%=basepath%>vender/jqGrid/js/jquery.jqGrid.min.js"></script>
<!-- The link to the CSS that the grid needs -->


<script>
    $.jgrid.defaults.width = 900;
    $.jgrid.defaults.responsive = true;
    $.jgrid.defaults.styleUI = 'Bootstrap';
</script>

<div style="display: inline-block;">
	<table id="jqGrid"></table>
	<div id="jqGridPager"></div>
</div>
<!-- jqgrid start -->

<div id="ueditorContainer" style="display: none">
	<!-- 加载编辑器的容器 -->
	<script id="container" name="content" type="text/plain">
这里写你的初始化内容
			</script>
</div>

<script>
function onIFrameLoaded(iframe) {
    var doc = iframe.contentWindow.document;
    var html = doc.body.innerHTML;
    if (html != '') {
        alert(html);
    }
}
</script>
<iframe name="backinfocontainer" style="display:none" onload='onIFrameLoaded(this)'></iframe>

<style>
.iconImg {
	width: 100px;
}
</style>


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
		isShow : true,
		initialFrameWidth:'100%'
	});
	console.log(editor);
</script>
<!--富文本编辑器 end -->


<script type="text/javascript">

    $(document).ready(function () {
    	
    	var imageFormat = function (cellvalue, options, rowdata){
			return '<img class="iconImg" src=" ' + cellvalue + '"/>';
		};
		var imageUnFormat = function( cellvalue, options, cell){
			return $('img', cell).attr('src');
		};
        $("#jqGrid").jqGrid({
            url: '<%=basepath%>foodArticle/queryArticleByPage',
            mtype: "POST",
            datatype: "json",
            colModel: [
                {
                    label: '编号', name: 'id', key: true, width: 75, editable: true, editrules: {required: true}
                },
                {label: '文章标题', name: 'title', width: 150, editable: true},
                {label: '类型', name: 'type', width: 150, editable: true},
                {label: '类型名', name: 'typeName', width: 150, editable: true},
               {label: '审核', name: 'audit', width: 150, editable: true},
               {
                    label: '图标', name: 'iconAddress', width: 150, editable: true, edittype: 'file',formatter:imageFormat, unformat:imageUnFormat,
                    editoptions: { 
                    	
                    	dataInit : function (elem) { 
	                    	console.group("elem");
	                    	console.log(elem);
	                    	console.groupEnd();
	                    	$("<img id='editImg' class='iconImg' src='" + jQuery("#jqGrid").jqGrid('getCell',$(elem).attr('rowid'), $(elem).attr('name')) + "'/>").insertBefore($(elem));
	                    	$(elem).attr('name','imgFile');
                    	},
                    	 dataEvents: [ 
                    	              { type: 'change', data: {}, fn: function(e) { 
                    	            	 		 //$('#editImg').prop('src',$(this).val());
                    	            	 		 console.group("image onchange");
                    	            	 		 console.log($(this).val());
                    	            	 		 console.groupEnd();
                    	            	 		    // 如果浏览器不支持FileReader，则不处理
                    	            	 		    if (!window.FileReader) return;
                    	            	 		    var files = e.target.files;

                    	            	 		    for (var i = 0, f; f = files[i]; i++) {
                    	            	 		        if (!f.type.match('image.*')) {
                    	            	 		            continue;
                    	            	 		        }
                    	            	 		        var reader = new FileReader();
                    	            	 		        reader.onload = (function(theFile) {
                    	            	 		            return function(e) {
                    	            	 		                // img 元素
                    	            	 		                document.getElementById('editImg').src = e.target.result;
                    	            	 		            };
                    	            	 		        })(f);
                    	            	 		        reader.readAsDataURL(f);
                    	            	 		    }

                    	            	 		}
                    	              		}
                    	              ] 
                    }
                },
                {label: '上传时间', name: 'uploadTime', width: 150},
                {label: '最近修改时间', name: 'updateTime', width: 150},
                {label: '审核时间', name: 'auditTime', width: 150},
                {label: '文章内容', name: 'content', width: 150, editable: true,edittype: 'input',editoptions: { 
                	dataInit : function (elem) { 
                    	console.group("elem");
                    	console.log(elem);
                    	$(elem).after("<input id='hideContent' type='textarea' name='content' style='display:none'/>");
                    	$(elem).replaceWith($('#ueditorContainer').css('display','block'));
                    	console.groupEnd();
                    	
                	},
                }}  
            ],
            sortname: 'id',
            sortorder: 'asc',
            viewrecords: true,
            height: 250,
            rowNum: 20,
            pager: "#jqGridPager",
        });
		
        $('#jqGrid').navGrid('#jqGridPager',
                // the buttons to appear on the toolbar of the grid
                {
                    edit: true,
                    add: true,
                    del: true,
                    search: false,
                    refresh: false,
                    view: false,
                    position: "left",
                    cloneToTop: false
                },
                // options for the Edit Dialog
                {
                    editCaption: "The Edit Dialog",
                    width:800,
                    jqModal:false,
                    drag:false,
                    recreateForm: true,
                    checkOnUpdate: true,
                    checkOnSubmit: true,
                    beforeSubmit : function(postdata, formid) { 
                    	//阻止默认提交方式，使用表单提交
                    	console.group('formid');
                    	console.log(formid);
                    	$('#hideContent').val(editor.getContent());
                    	$(formid).append("<input type='hidden' name='_method' value='PUT' />");
                    	$(formid).prop('enctype','multipart/form-data').prop('method','post').prop('target','backinfocontainer').removeAttr('onSubmit').prop('action','<%=basepath%>article').submit();
                    	console.groupEnd();
                    	
                    	return[false,""]; 
                    },
                    onClose : function(){
                    	//将ueditor保存到body中
                    	$('#ueditorContainer').css('display','none').appendTo($(document.body));
                    	return true;
                    },
                    errorTextFormat: function (data) {
                        return 'Error: ' + data.responseText
                    },
                },
                // options for the Add Dialog
                {
                	editCaption: "The Add Dialog",
                    width:800,
                    height:'auto',
                    drag:true,
                    url:"<%=basepath%>article",
                    recreateForm: true,
                    checkOnUpdate: true,
                    checkOnSubmit: true,
                    beforeSubmit : function(postdata, formid) { 
                    	//阻止默认提交方式，使用表单提交
                    	console.group('formid');
                    	console.log(formid);
                    	$('#hideContent').val(editor.getContent());
                    	$(formid).prop('enctype','multipart/form-data').prop('method','post').prop('target','backinfocontainer').removeAttr('onSubmit').prop('action','<%=basepath%>article').submit();
                    	console.groupEnd();
                    	
                    	return[false,""]; 
                    },
                    onClose : function(){
                    	//将ueditor保存到body中
                    	$('#ueditorContainer').css('display','none').appendTo($(document.body));
                    	return true;
                    },
                    errorTextFormat: function (data) {
                        return 'Error: ' + data.responseText
                    },
                },
                // options for the Delete Dailog
                {
                    caption: "确认删除",
                    msg: "是否确认删除文章?",
                    bSubmit: "删除",
                    bCancel: "取消",
                    url : "<%=basepath%>foodArticle/queryArticleByPage",
					mtype : "POST",
					delData : {
						_method : "DELETE"
					},
					errorTextFormat : function(data) {
						return 'Error: '
								+ data.responseText
					}
				});
		});
</script>
<!-- jqgrid end -->
