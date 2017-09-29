<%@ page language="java" pageEncoding="utf-8" %>
<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/customize_tags" %>
<%@ page isELIgnored="false" %>
<%
    /*获得根URL*/
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
%>
<head>
    <meta charset="utf-8">
    <title>${ctx.substring(0,1).toUpperCase()}${ctx.substring(1)}</title>
    <link rel="stylesheet" href="${path}/resources/css/bootstrap.min.css">
    <link rel="stylesheet" href="${path}/resources/css/style.css">
    <link rel="stylesheet" href="${path}/resources/js/DataTables/media/css/dataTables.bootstrap.css">
    <link href="${path}/resources/js/bootstrap-fileinput/css/fileinput.css" media="all" rel="stylesheet" type="text/css"/>
    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="${path}/resources/js/html5shiv.min3.7.0.js"></script>
    <script src="${path}/resources/js/respond.min1.3.0.js"></script>
    <![endif]-->
    <script src="${path}/resources/js/jquery.min1.10.2.js"></script>
    <script src="${path}/resources/js/nav.js"></script>
    <script src="${path}/resources/js/bootstrap.js"></script>
    <script src="${path}/resources/js/doT.js"></script>

    <script src="${path}/resources/js/DataTables/media/js/jquery.dataTables.min.js"></script>
    <script src="${path}/resources/js/DataTables/media/js/dataTables.bootstrap.js"></script>
    <script src="${path}/resources/js/jQuery-slimScroll/jquery.slimscroll.js"></script>
    <script src="${path}/resources/js/bootstrap-fileinput/js/fileinput.js" type="text/javascript"></script>
    <!--<script src="resources/js/Tables.js"></script>-->
    <script src="${path}/resources/js/common.js"></script>
    <script src="${path}/resources/js/common/CommonFn.js"></script>
    <script src="${path}/resources/js/common/DateFormat.js"></script>
    <script src="${path}/resources/js/admin/LoginController.js"></script>
    <script src="${path}/resources/js/admin/UserManageController.js"></script>
    <script src="${path}/resources/js/admin/AppealManageController.js"></script>
    <script src="${path}/resources/js/jquery-form-3.50/jquery.form.js"></script>
</head>
<div id="header">
    <nav role="navigation" class="navbar navbar-default">
        <div class="navbar-header text-center">
            <input type="hidden" id="ctx" value="${ctx}">
            <a href="${path}/${ctx}/project/home"> <img style="height:40px;" src="${path}/media/${ctx}.png"></a>
        </div>
        <div class="collapse navbar-collapse">
            <ul class="nav navbar-nav nav-bg">
                <shiro:hasRole name="0">
                    <li><a href="${path}/${ctx}/project/manage">项目管理</a></li>
                    <li><a href="${path}/${ctx}/user/manage">用户管理</a></li>
                    <li><a href="${path}/${ctx}/appeal/manage">申诉管理</a></li>
                </shiro:hasRole>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li>
                    <a href="javascript:void(0);">
                        <span class="glyphicon glyphicon-user"></span>您好, <span id="user-name">
                            <shiro:principal property="username"/>
                        </span>
                    </a>
                </li>
                <li><a href="javascript:void(0);">|</a></li>
                <li>
                    <a href="javascript:void(0);" onclick="$.LoginController.logout();">
                        <span class="glyphicon glyphicon-log-out"></span> 退出
                    </a>
                </li>
            </ul>
        </div>
    </nav>
</div>
