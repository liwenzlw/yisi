<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
String basepath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
+ request.getContextPath() + "/";
%>
<style>
    .iconImg {
     width: 100px;
 }

 #jqGrid tbody {
     cursor: pointer;
 }
</style>

<script>
	var onIFrameLoaded = function (iframe) {
       var doc = iframe.contentWindow.document;
       var html = doc.body.innerHTML;

       if (html != '') {
          if(html == 'true') {
    			//成功后刷新数据
              jQuery("#jqGrid").trigger("reloadGrid");
              alert("操作成功");
          } else {
             alert("操作失败");
         }
     }
     $('#showSubmitDiv').hide();
 };
 $(document).ajaxStart(function(){
  $("#modalShade").show();
  $("#modalShade").data("count",parseInt($("#modalShade").data("count"))+1);
});
 $(document).ajaxStop(function(){
  var count = parseInt($("#modalShade").data("count"))-1;
  count=count>=0?count:0;
  $("#modalShade").data("count",count);
  if(count == 0) {
     $("#modalShade").hide();
 }
});
</script>
<div style="display: inline-block;">
	<table id="jqGrid"></table>
	<div id="jqGridPager"></div>
</div>
<iframe id='backinfocontainer' name="backinfocontainer"
style="display: none" onload='onIFrameLoaded(this)'></iframe>


<script type="text/ecmascript"
src="<%=basepath%>vender/jqGrid/js/i18n/grid.locale-cn.js"></script>
<script type="text/ecmascript"
src="<%=basepath%>vender/jqGrid/js/jquery.jqGrid.min.js"></script>
<script>
	window.UEDITOR_HOME_URL = "<%=basepath%>vender/ueditor/";
</script>
<script type="text/javascript"
src="<%=basepath%>vender/ueditor/ueditor.config.js"></script>
<script type="text/javascript"
src="<%=basepath%>vender/ueditor/ueditor.all.js"></script>

<script>
    $.jgrid.defaults.width = 1100;
    $.jgrid.defaults.responsive = false;
    $.jgrid.defaults.styleUI = 'Bootstrap';
</script>

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
     var subTypeFormat = function (cellvalue, options, rowdata){
      var subTypeValueSpan = $('<div/>').append($('<span/>').attr('id','subTypeValue').attr('data-oldvalue',cellvalue).html(rowdata.subTypeName));
      return subTypeValueSpan.html();
  };
  var subTypeUnFormat = function( cellvalue, options, cell){
     cellvalue = $('#subTypeValue',cell).data('oldvalue');
     return cellvalue+"";
 };
 var topTypeFormat = function (cellvalue, options, rowdata){
  var topTypeValueSpan = $('<div/>').append($('<span/>').attr('id','topTypeValue').attr('data-oldvalue',cellvalue).html(rowdata.topTypeName));
  return topTypeValueSpan.html();
};
var topTypeUnFormat = function( cellvalue, options, cell){
 cellvalue = $('#topTypeValue',cell).data('oldvalue');
 return cellvalue+"";
};

var showSubmitDiv = function(event){
 $('#showSubmitDiv').show();
};
		//编辑Form框显示后
		var initSubTypeAndRichContent = function (formid) {
          var rowId = $("#id",formid).val();
          var topType = $("#jqGrid").jqGrid('getCell', rowId,'topType');
          var subType = $("#jqGrid").jqGrid('getCell', rowId,'subType');
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
	    	//初始化富文本编辑框
	    	$.ajax({
                url:"<%=basepath%>article/getContentById",
                type: "POST",
                data:{
                   id:$("#id",formid).val()
               },
               dataType: "html",
               success : function(data){
                   /* 加载编辑器的容器 */
					//初始化富文本框
                    $("#content",formid).after("<input id='hideContent' type='textarea' name='content' style='display:none'/>");
                    $("#content",formid).replaceWith('<div><script id="ueditorContainer" name="content" type="text/plain"><\/script></div>');
                    var editor = UE.getEditor('ueditorContainer', {
                      enableAutoSave : false,
                      enableAutoSave : false,
                      autoSyncData : false,
                      enableContextMenu : false,
                      isShow : true,
                      elementPathEnabled :false,
                      wordCount :false,
                      initialFrameWidth:'500',
                      initialFrameHeight: '300',
                      autoHeightEnabled:false
                  });
                    editor.ready(function() {
                      editor.setContent(data);
                  });
                }
            });
      };

      var template = "<table style='width:900px'>";
      template += "<tr style='display:none'><td>编号:</td><td> {id} </td></tr>";
      template += "<tr ><td style='width:100px'> 文章标题:</td><td> {title} </td><td style='width:100px'> 文章内容:</td><td rowspan='10' style='width:500px;height:405px;'><div> {content} </div></td></tr>";
      template += "<tr ><td> 一级类型:</td><td> {topType} </td></tr>";
      template += "<tr ><td> 二级类型:</td><td> {subType} </td></tr>";
      template += "<tr ><td> 图标:</td><td style='width:200px'> {iconAddress} </td></tr>";
      template += '${canAudit==true? "<tr ><td> 审核:</td><td> {audit} </td></tr>": ""}';
      template += "<tr ><td> 开始营业时间:</td><td> {startTime} </td></tr>";
      template += "<tr ><td> 结束营业时间:</td><td> {endTime} </td></tr>";
      template += "<tr ><td> 文章显示时间:</td><td> {showTime} </td></tr>";
      template += "<tr ><td> 地址:</td><td> {address} </td></tr>";
      template += "<tr ><td> 电话:</td><td> {phone} </td></tr>";
      template += "</table>";
      template += "<hr style='width:100%;'/>";
      template += "<div> {pData} {nData} {sData} {cData}  </div>";
      $("#jqGrid").jqGrid({
        url: '<%=basepath%>article/queryArticleByPage',
        mtype: "POST",
        datatype: "json",
        colModel: [
        {
            label: '编号', name: 'id', key: true, width: 75,editable: true,hidden:true,editrules: {required: true},editoptions:{defaultValue:"-1",readonly:true}
        },
        {label: '文章标题', name: 'title', width: 150, editable: true,editrules:{required:true}
    },
    {label: '一级类型', name: 'topTypeName',width: 150,hidden:true},
    {label: '一级类型', name: 'topType', width: 150, editable: true,edittype:'select', editrules:{required:true},formatter:topTypeFormat, unformat:topTypeUnFormat,
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
                		dataEvents: [ 
                        { type: 'change', fn: function(e) { 
                           debugger;
                           var rowId = $(this).parents('tbody').find('#id').val();
                           var topType = $(this).val();
                           var subType = $("#jqGrid").jqGrid('getCell', rowId,'subType');

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
                            {label: '二级类型', name: 'subType', width: 150, editable: true,edittype:'select',editrules:{required:true},editoptions:{ value:"0:NULL" },formatter:subTypeFormat, unformat:subTypeUnFormat},
                            {label: '二级类型', name: 'subTypeName', width: 150,hidden:true},
                            {label: '审核', name: 'audit', width: 80,  edittype:"checkbox",editoptions:{ value:"1:0" }
                            /* 使用empty运算符检查对象是否为null(空) */
                            ${canAudit==true? ",editable: true" : ",editable: false"}
                        },

                        {
                            label: '图标', name: 'iconAddress', width: 150, editable: true, edittype: 'file',formatter:imageFormat, unformat:imageUnFormat,
                            editoptions: { 

                               dataInit : function (elem) { 
                                  $("<img id='editImg' class='iconImg' src='" + jQuery("#jqGrid").jqGrid('getCell',$(elem).attr('rowid'), $(elem).attr('name')) + "'/>").insertBefore($(elem));
                                  $(elem).attr('accept','.jpg,.png,.jpeg').attr('name','imgFile').css({'width':'200px'});
                              },
                              dataEvents: [ 
                              { type: 'change', data: {}, fn: function(e) { 
                                                     var file=this.files[0];
                                                    //只允许传送image/jpeg和image/png和image/jpg格式的图片
                                                    if(!(file.type == 'image/jpeg' || file.type == 'image/png' || file.type == 'image/jpg')){
                                                      alert('请选择图片');
                                                      $(this).replaceWith($(this).clone(true,true).val(''));
                                                      document.getElementById('editImg').src = '';
                                                      return;
                                                  } else if (file.size > 60000) {//如果文件的大于60KB禁止用户上传
                                                    alert('图片过大，请重新选择');
                                                    $(this).replaceWith($(this).clone(true,true).val(''));
                                                    document.getElementById('editImg').src = '';
                                                    return;
                                                }
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
                                              language:'zh-CN',
                                              format: 'hh:ii',
                                              startView:1,
                                              maxView:1,
                                              autoclose:true,
                                              showMeridian:"hour",
                                              startDate: new Date().setHours(0,0,0),
                                              keyboardNavigation:false
                                          });
                                       }
                                   }
                               },
                               {label: '结束营业时间', name: 'endTime', width: 150,editable: true,edittype: "text",editrules:{required:true},
                               editoptions: {
                                dataInit: function (element) {
                                   $(element).addClass('form-control').prop('readonly',true);
                                   $(element).datetimepicker({
                                      language:'zh-CN',
                                      format:'hh:ii',
                                      startView:1,
                                      maxView:1,
                                      autoclose:true,
                                      showMeridian:"hour",
                                      startDate: new Date().setHours(0,0,0),
                                      keyboardNavigation:false
                                  });
                               }
                           }
                       },
                       {label: '文章显示时间', name: 'showTime',width: 150,editable: true,edittype: "text",editrules:{required:true},
                       editoptions: {
                        dataInit: function (element) {
                        	$(element).addClass('form-control').prop('readonly',true);
                        	$(element).datetimepicker({
                        		language:'zh-CN',
                        		format: 'yyyy-mm-dd',
                        		startView:3,
                        		todayBtn:"linked",
                        		maxView:3,
                        		minView:2,
                        		autoclose:true,
                        		showMeridian:"day"
                        		
                        	});
                        }
                    }
                },
                {label: '地址', name: 'address', width: 150,editable: true,editrules:{required:true}},
                {label: '电话', name: 'phone', width: 150,editable: true,editrules:{required:true}},
                {label: '审核人', name: 'auditName', width: 150},
                {label: '审核时间', name: 'auditTime', width: 150},
                {label: '上传人', name: 'inserterName', width: 150},
                {label: '上传时间', name: 'uploadTime', width: 150},
                {label: '最近修改时间', name: 'updateTime', width: 150},
                {label: '审核时间', name: 'auditTime', width: 150},
                {label: '文章内容', name: 'content', width: 150, hidden:true,editable: true,editrules:{edithidden:true}}  
                ],
                sortname: 'id',
                sortorder: 'asc',
                viewrecords: true,
                width:1100,
                height: 'auto',
                rowNum: 10,
                pager: "#jqGridPager"
            });

$('#jqGrid').navGrid('#jqGridPager',
                // the buttons to appear on the toolbar of the grid
                {
                    edit: true,
                    add: true,
                    del: true,
                    search: false,
                    refresh: true,
                    view: false,
                    position: "left",
                    cloneToTop: false
                },
                // options for the Edit Dialog
                {
                    template:template,
                    width:925,
                    jqModal:false,
                    drag:true,
                    recreateForm:true,
                    reloadAfterSubmit:false,
                    resize:false,
                    url:"<%=basepath%>#",
                    beforeSubmit : function(postdata, formid) { 
                    	debugger;
                    	//阻止默认提交方式，使用表单提交
                    	postdata = {};
                    	$('#hideContent').val(UE.getEditor('ueditorContainer').getContent());
                    	$(formid).on('submit',showSubmitDiv).prop('enctype','multipart/form-data').prop('method','post').prop('target','backinfocontainer').removeAttr('onSubmit').prop('action','<%=basepath%>article/edit').submit();
                    	debugger;
                    	return[true,""]; 
                    },
                    afterclickPgButtons : function (whichbutton, formid, rowid) {
                    	
                     var topType = $("#topType",formid).val();
                     var subType = jQuery("#jqGrid").jqGrid('getCell', rowid,'subType');
                     debugger;
	  		    		//编辑模块加载二级类型下拉框
                     $.ajax({
                       url:"<%=basepath%>type/getSubTypeByTopType",
                       type: "POST",
                       data:{
                          id:topType
                      },
                      dataType: "json",
                      success : function(data){
                          debugger;
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
	  		    		//初始化富文本内容区
                     $.ajax({
                       url:"<%=basepath%>article/getContentById",
                       type: "POST",
                       data:{
                          id:$("#id",formid).val()
                      },
                      dataType: "html",
                      success : function(data){
	  							//初始化富文本框
                              var editor = UE.getEditor('ueditorContainer');
                              editor.ready(function() {
                                 editor.setContent(data);
                             });
                          }
                      });
	  		    		//重置图片链接
	  		    		var editImgSrc = jQuery("#jqGrid").jqGrid('getCell', rowid,'iconAddress');
	  		    		$('#editImg').attr('src',editImgSrc);
                    },
                    afterShowForm:initSubTypeAndRichContent,
                    onClose : function(){
                    	window.onscroll = function(){};
                    	UE.getEditor('ueditorContainer').destroy();
                    	return true;
                    },
                    errorTextFormat: function (data) {
                        return 'Error: ' + data.responseText
                    },
                },
                // options for the Add Dialog
                {
                    template:template,
                    width:925,
                    jqModal:false,
                    drag:true,
                    recreateForm:true,
                    reloadAfterSubmit:false,
                    clearAfterAdd:false,
                    resize:false,
                    url:"<%=basepath%>#",
                    beforeSubmit : function(postdata, formid) { 
                    	//阻止默认提交方式，使用表单提交
                    	postdata = {};
                    	$('#hideContent').val(UE.getEditor('ueditorContainer').getContent());
                    	$(formid).on('submit',showSubmitDiv).prop('enctype','multipart/form-data').prop('method','post').prop('target','backinfocontainer').removeAttr('onSubmit').prop('action','<%=basepath%>article/add').submit();
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
        		    	//初始化富文本框
                       $("#content",formid).after("<input id='hideContent' type='textarea' name='content' style='display:none'/>");
                       $("#content",formid).replaceWith('<div><script id="ueditorContainer" name="content" type="text/plain"><\/script></div>');
                       var editor = UE.getEditor('ueditorContainer', {
                          enableAutoSave : false,
                          enableAutoSave : false,
                          autoSyncData : false,
                          enableContextMenu : false,
                          isShow : true,
                          elementPathEnabled :false,
                          wordCount :false,
                          initialFrameWidth:'500',
                          initialFrameHeight: '300',
                          autoHeightEnabled:false
                      });
                   },
                   onClose : function(){
                       window.onscroll = function(){};
                       UE.getEditor('ueditorContainer').destroy();
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
                      return 'Error: '+ data.responseText
                  }
              });
});
</script>
<!-- jqgrid end -->

