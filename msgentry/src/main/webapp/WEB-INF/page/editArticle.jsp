<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

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
    $.jgrid.defaults.width = 1100;
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
    		if(html == 'true') {
    			console.group('onIFrameLoaded');
    			$('#cData').trigger('click');
        		jQuery("#jqGrid").trigger("reloadGrid"); 
        		console.groupEnd();
    		} else {
    			alert(html);
    		}
    }
}
</script>
<iframe id='backinfocontainer' name="backinfocontainer"
	style="display: none" onload='onIFrameLoaded(this)'></iframe>



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

<style>
.iconImg {
	width: 100px;
}
</style>

<!-- 日历控件 start -->
<link rel="stylesheet" type="text/css" media="screen"
	href="<%=basepath%>vender/bootstrap-datetimepicker/css/bootstrap-datetimepicker.min.css" />
<script type="text/ecmascript"
	src="<%=basepath%>vender/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
<script type="text/javascript"
	src="<%=basepath%>vender/bootstrap-datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"
	charset="UTF-8"></script>
<!-- 日历控件 end -->
<script type="text/javascript">

    $(document).ready(function () {
    	
    	var imageFormat = function (cellvalue, options, rowdata){
			return '<img class="iconImg" src=" ' + cellvalue + '"/>';
		};
		var imageUnFormat = function( cellvalue, options, cell){
			return $('img', cell).attr('src');
		};
		
		    //编辑Form框显示后
		 var initSubType = function (formid) {
		    	
		    	var rowId = $("#id",formid).val();
		    	var topType = jQuery("#jqGrid").jqGrid('getCell', rowId,'topType');
		    	var subType = jQuery("#jqGrid").jqGrid('getCell', rowId,'subType');
		    	
		    	//编辑模块加载二级类型下拉框
		    	$.ajax({
					url:"<%=basepath%>type/getSubTypeByTopType",
					type: "POST",
					data:{
						id:topType
					},
					dataType: "json",
					success : function(data){
						var subSelectorOptions = "";
						if($.isEmptyObject(data)) {
							subSelectorOptions = "<option value='0'>NULL</option>";
						} else {
							$.each(data,function(i,n) {
		        				subSelectorOptions += "<option value='"+n.id+"'>"+n.name+"</option>";
		        			});
						}
		    			$("#subType").html(subSelectorOptions).val(subType);
					},
				});
		    };
		  
		    
        $("#jqGrid").jqGrid({
            url: '<%=basepath%>article/queryArticleByPage',
            mtype: "POST",
            datatype: "json",
            colModel: [
                {
                    label: '编号', name: 'id', key: true, width: 75,editable: true,hidden:true,editrules: {required: true},editoptions:{defaultValue:"-1"}
                },
                {label: '文章标题', name: 'title', width: 150, editable: true,editrules:{required:true}
                },
                {label: '一级类型', name: 'topTypeName',width: 150},
                {label: '一级类型', name: 'topType', width: 150, editable: true,edittype:'select', hidden:true,editrules:{edithidden:true,required:true},
                	editoptions:{
                		dataUrl:"<%=basepath%>type/getTopType",
                		buildSelect : function (response){
                			//构造一级类型下拉框
                			var data = JSON.parse(response);
                			var selector = "<select id='topType'>";
                			$.each(data,function(i,n) {
                					selector += "<option value='"+n.id+"'>"+n.name+"</option>";
                			});
                			selector += "</select>"
                			return selector;
                		},
                		dataInit : function (elem) { 
                        	console.group("dataInit");
                        	console.log(elem);
                        	console.groupEnd();
                        	
                    	},
                		 dataEvents: [ 
	                		              { type: 'change', fn: function(e) { 
	                		            	  	var rowId = $(this).parents('tbody').find('#id').val();
	                		  		    		var topType = $(this).val();
	                		  		    		var subType = jQuery("#jqGrid").jqGrid('getCell', rowId,'subType');
	                		  		    		
	                		  		    		//编辑模块加载二级类型下拉框
	                		  			    	$.ajax({
	                		  						url:"<%=basepath%>type/getSubTypeByTopType",
	                		  						type: "POST",
	                		  						data:{
	                		  							id:topType
	                		  						},
	                		  						dataType: "json",
	                		  						success : function(data){
	                		  							var subSelectorOptions = "";
	                		  							if($.isEmptyObject(data)) {
	                		  								subSelectorOptions = "<option value='0'>NULL</option>";
	                		  							} else {
	                		  								$.each(data,function(i,n) {
	                		  			        				subSelectorOptions += "<option value='"+n.id+"'>"+n.name+"</option>";
	                		  			        			});
	                		  							}
	                		  							$("#subType").html(subSelectorOptions);
	                		  						},
	                		  					});
	                		            	  } 
	                		              }
                		              ] 
                	}
                },
                {label: '二级类型', name: 'subType', width: 150, editable: true,edittype:'select',hidden:true,editrules:{edithidden:true,required:true},editoptions:{ value:"0:NULL" }},
                {label: '二级类型', name: 'subTypeName', width: 150},
                {label: '审核', name: 'audit', width: 80,  edittype:"checkbox",editoptions:{ value:"1:9" }
                /* 使用empty运算符检查对象是否为null(空) */
                ${isAdmin==true? ",editable: true" : ",editable: false"}
          	},
               
          	{
                    label: '图标', name: 'iconAddress', width: 150, editable: true, edittype: 'file',formatter:imageFormat, unformat:imageUnFormat,
                    editoptions: { 
                    	
                    	dataInit : function (elem) { 
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
                {label: '开始营业时间', name: 'startTime', width: 150,editable: true,edittype: "text",editrules:{required:true},
                    editoptions: {
                        dataInit: function (element) {
                        	$(element).addClass('form-control').prop('readonly',true);
                        	$(element).datetimepicker({
                        		language:'zh-CN',format: 'hh:ii',startView:1,maxView:1,autoclose:true,showMeridian:"hour"
                        	});
                        }
                    }
                },
                {label: '结束营业时间', name: 'endTime', width: 150,editable: true,edittype: "text",editrules:{required:true},
                    editoptions: {
                        dataInit: function (element) {
                        	$(element).addClass('form-control').prop('readonly',true);
                        	$(element).datetimepicker({
                        		language:'zh-CN',format:'hh:ii',startView:1,maxView:1,autoclose:true,showMeridian:"hour"
                        	});
                        }
                    }
				},
                {label: '文章显示时间', name: 'showTime',width: 150,editable: true,edittype: "text",editrules:{required:true},
                    editoptions: {
                        dataInit: function (element) {
                        	$(element).addClass('form-control').prop('readonly',true);
                        	$(element).datetimepicker({
                        		language:'zh-CN',format: 'yyyy-mm-dd',startView:3,todayBtn:"linked",maxView:3,minView:2,autoclose:true,showMeridian:"day"
                        	});
                        }
                    }
				},
                {label: '地址', name: 'address', width: 150,editable: true,editrules:{required:true}},
                {label: '电话', name: 'phone', width: 150,editable: true,editrules:{required:true}},
                {label: '审核时间', name: 'auditTime', width: 150},
                {label: '上传时间', name: 'uploadTime', width: 150},
                {label: '最近修改时间', name: 'updateTime', width: 150},
                {label: '文章内容', name: 'content', width: 150, hidden:true,editable: true,editrules:{edithidden:true},editoptions: { 
                	
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
            height: 'auto',
            rowNum: 20,
            pager: "#jqGridPager"
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
                    drag:true,
                    url:"<%=basepath%>#",
                    recreateForm: true,
                    checkOnSubmit: true,
                    closeAfterEdit:true,
                    beforeSubmit : function(postdata, formid) { 
                    	//阻止默认提交方式，使用表单提交
                    	postdata = {};
                    	$('#hideContent').val(editor.getContent());
                    	$(formid).prop('enctype','multipart/form-data').prop('method','post').prop('target','backinfocontainer').removeAttr('onSubmit').prop('action','<%=basepath%>article/edit').submit();
                    	return[true,""]; 
                    },
                    afterclickPgButtons : function (whichbutton, formid, rowid) {
                    	
	  		    		var topType = $("#topType",formid).val();
	  		    		var subType = jQuery("#jqGrid").jqGrid('getCell', rowid,'subType');
	  		    		
	  		    		//编辑模块加载二级类型下拉框
	  			    	$.ajax({
	  						url:"<%=basepath%>type/getSubTypeByTopType",
	  						type: "POST",
	  						data:{
	  							id:topType
	  						},
	  						dataType: "json",
	  						success : function(data){
	  							var subSelectorOptions = "";
	  							if($.isEmptyObject(data)) {
	  								subSelectorOptions = "<option value='0'>NULL</option>";
	  							} else {
	  								$.each(data,function(i,n) {
	  			        				subSelectorOptions += "<option value='"+n.id+"'>"+n.name+"</option>";
	  			        			});
	  							}
	  							$("#subType").html(subSelectorOptions).val(subType);
	  						},
	  					});
                    },
                    afterShowForm:initSubType,
                    onClose : function(){
                    	//情况ueditor数据
                    	//将ueditor保存到body中
                    	//debugger;
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
                    jqModal:false,
                    drag:true,
                    url:"<%=basepath%>#",
                    recreateForm: true,
                    checkOnSubmit: true,
                    closeAfterEdit:true,
                    beforeSubmit : function(postdata, formid) { 
                    	postdata = {};
                    	//阻止默认提交方式，使用表单提交
                    	$('#hideContent').val(editor.getContent());
                    	$(formid).prop('enctype','multipart/form-data').prop('method','post').prop('target','backinfocontainer').removeAttr('onSubmit').prop('action','<%=basepath%>article/add').submit();
                    	return[true,""]; 
                    },
                    afterShowForm:function (formid) {
        		    	var topType = 1;
        		    	//编辑模块加载二级类型下拉框
        		    	$.ajax({
        					url:"<%=basepath%>type/getSubTypeByTopType",
        					type: "POST",
        					data:{
        						id:topType
        					},
        					dataType: "json",
        					success : function(data){
        						var subSelectorOptions = "";
        						if($.isEmptyObject(data)) {
        							subSelectorOptions = "<option value='0'>NULL</option>";
        						} else {
        							$.each(data,function(i,n) {
        		        				subSelectorOptions += "<option value='"+n.id+"'>"+n.name+"</option>";
        		        			});
        						}
        		    			$("#subType").html(subSelectorOptions);
        					},
        				});
                    },
                    onClose : function(){
                    	//将ueditor保存到body中
                    	//debugger;
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
                    url : "<%=basepath%>/article/del",
											mtype : "POST",
											errorTextFormat : function(data) {
												return 'Error: '
														+ data.responseText
											}
										});
					});
</script>
<!-- jqgrid end -->

