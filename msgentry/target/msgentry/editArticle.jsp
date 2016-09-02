<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	String basepath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/";
%>

<div style="display: inline-block;">
	<table id="jqGrid"></table>
	<div id="jqGridPager"></div>
</div>
<!-- jqgrid start -->

<!-- We support more than 40 localizations -->
<script type="text/ecmascript"
	src="<%=basepath%>vender/jqGrid/js/i18n/grid.locale-en.js"></script>
<!-- This is the Javascript file of jqGrid -->
<script type="text/ecmascript"
	src="<%=basepath%>vender/jqGrid/js/jquery.jqGrid.min.js"></script>
<!-- The link to the CSS that the grid needs -->
<link rel="stylesheet" type="text/css" media="screen"
	href="<%=basepath%>vender/jqGrid/css/ui.jqgrid-bootstrap.css" />

<script>
    $.jgrid.defaults.width = 900;
    $.jgrid.defaults.responsive = true;
    $.jgrid.defaults.styleUI = 'Bootstrap';
</script>
<script type="text/javascript">

    $(document).ready(function () {
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
                {label: '审核', name: 'audit', width: 150, editable: true},
                {
                    label: '图标', name: 'iconAddress', width: 150, editable: true, edittype: 'image',
                    editoptions: {
                        dataInit: function (elem) {
                            console.log("elem:");
                            console.log(elem);
                        }
                    }
                },
                {label: '上传时间', name: 'uploadTime', width: 150},
                {label: '最近修改时间', name: 'updateTime', width: 150},
                {label: '审核时间', name: 'auditTime', width: 150},
               /* {label: 'Article Content', name: 'articleContent', width: 150, editable: true}*/
            ],
            sortname: 'id',
            sortorder: 'asc',
            viewrecords: true,
            height: 250,
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
                    recreateForm: true,
                    checkOnUpdate: true,
                    checkOnSubmit: true,
                    closeAfterEdit: true,
                    errorTextFormat: function (data) {
                        return 'Error: ' + data.responseText
                    }
                },
                // options for the Add Dialog
                {
                    closeAfterAdd: true,
                    recreateForm: true,
                    errorTextFormat: function (data) {
                        return 'Error: ' + data.responseText
                    }
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
				return 'Error: ' + data.responseText
			}
		});
	});
</script>
<!-- jqgrid end -->
