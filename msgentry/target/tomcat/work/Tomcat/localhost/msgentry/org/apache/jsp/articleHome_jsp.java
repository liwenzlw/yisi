/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.37
 * Generated at: 2016-09-02 16:17:13 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class articleHome_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("\r\n");

	String basepath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/";

      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html lang=\"zh-CN\">\r\n");
      out.write("<head>\r\n");
      out.write("<meta charset=\"UTF-8\">\r\n");
      out.write("<title>Title</title>\r\n");
      out.write("<style>\r\n");
      out.write("body {\r\n");
      out.write("\tmargin: 0;\r\n");
      out.write("\tpadding: 0;\r\n");
      out.write("}\r\n");
      out.write("</style>\r\n");
      out.write("</head>\r\n");
      out.write("<body class=\"container\">\r\n");
      out.write("\t<div class=\"row\">\r\n");
      out.write("\t\t<div class=\"panel panel-default\" style=\"margin-top: 10px\">\r\n");
      out.write("\t\t\t<div class=\"panel-heading\">\r\n");
      out.write("\t\t\t\t<!-- Nav tabs -->\r\n");
      out.write("\t\t\t\t<ul class=\"nav nav-tabs\" id=\"maintab\">\r\n");
      out.write("\t\t\t\t\t<li class=\"active\"><a href=\"#home\"\r\n");
      out.write("\t\t\t\t\t\tonclick='showPage(\"home\",\"\")'>Home</a></li>\r\n");
      out.write("\t\t\t\t\t<li><a href=\"#uploadArticle\"\r\n");
      out.write("\t\t\t\t\t\tonclick='showPage(\"uploadArticle\",\"");
      out.print(basepath);
      out.write("uploadArticle.jsp\")'>录入数据</a></li>\r\n");
      out.write("\t\t\t\t\t<li><a href=\"#editArticle\"\r\n");
      out.write("\t\t\t\t\t\tonclick='showPage(\"editArticle\",\"");
      out.print(basepath);
      out.write("editArticle.jsp\")'>编辑数据</a></li>\r\n");
      out.write("\t\t\t\t\t<li><a href=\"#settings\" onclick='showPage(\"settings\",\"\")'>修改密码</a></li>\r\n");
      out.write("\t\t\t\t</ul>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t\t<div class=\"panel-body\">\r\n");
      out.write("\t\t\t\t<!-- Tab panes -->\r\n");
      out.write("\t\t\t\t<div class=\"tab-content\">\r\n");
      out.write("\t\t\t\t\t<div class=\"tab-pane active\" id=\"home\">12111111111</div>\r\n");
      out.write("\t\t\t\t\t<div class=\"tab-pane\" id=\"uploadArticle\"></div>\r\n");
      out.write("\t\t\t\t\t<div class=\"tab-pane\" id=\"editArticle\" style=\"text-align: center;\"></div>\r\n");
      out.write("\t\t\t\t\t<div class=\"tab-pane\" id=\"settings\">4444444444444</div>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t</div>\r\n");
      out.write("\r\n");
      out.write("</body>\r\n");
      out.write("<!-- 新 Bootstrap 核心 CSS 文件 -->\r\n");
      out.write("<link rel=\"stylesheet\"\r\n");
      out.write("\thref=\"//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css\">\r\n");
      out.write("\r\n");
      out.write("<!-- 可选的Bootstrap主题文件（一般不用引入） -->\r\n");
      out.write("<link rel=\"stylesheet\"\r\n");
      out.write("\thref=\"//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap-theme.min.css\">\r\n");
      out.write("\r\n");
      out.write("<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->\r\n");
      out.write("<script src=\"//cdn.bootcss.com/jquery/1.11.3/jquery.min.js\"></script>\r\n");
      out.write("\r\n");
      out.write("<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->\r\n");
      out.write("<script src=\"//cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js\"></script>\r\n");
      out.write("<script language=\"javascript\">\r\n");
      out.write("\t//var loadimg = \"./img/load2.gif\"; // 加载时的loading图片\r\n");
      out.write("\r\n");
      out.write("\tfunction showPage(tabId, url) {\r\n");
      out.write("\t\t$('#maintab a[href=\"#' + tabId + '\"]').tab('show'); // 显示点击的tab页面\r\n");
      out.write("\t\tif ($('#' + tabId).html().length < 20 && url != \"\") { // 当tab页面内容小于20个字节时ajax加载新页面\r\n");
      out.write("\t\t\t$('#' + tabId).html('<br>' + ' 页面加载中，请稍后...'); // 设置页面加载时的loading图片\r\n");
      out.write("\t\t\t$('#' + tabId).load(url); // ajax加载页面\r\n");
      out.write("\t\t}\r\n");
      out.write("\t}\r\n");
      out.write("</script>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("</html>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}