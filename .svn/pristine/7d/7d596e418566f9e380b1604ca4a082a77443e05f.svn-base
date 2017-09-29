<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page isELIgnored="false" %>
<%
    /*获得根URL*/
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>${ctx.substring(0,1).toUpperCase()}${ctx.substring(1)}</title>
<link rel="stylesheet" href="${path}/resources/css/bootstrap.min.css">
<link rel="stylesheet" href="${path}/resources/css/style.css">
<link rel="stylesheet" href="${path}/resources/css/login.css">
<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="${path}/resources/js/html5shiv.min3.7.0.js"></script>
        <script src="${path}/resources/js/respond.min1.3.0.js"></script>
    <![endif]-->

</head>

<body class="loginbg">
<div class="loginpanelinner">
    <div class="logo animate0 bounceIn">${ctx.substring(0,1).toUpperCase()}${ctx.substring(1)} Log in</div>
    <%--<form method="get" action="<%=basePath%>/project/home" id="login" class="form-group">--%>
        <input id="ctx" type="hidden" value="${ctx}">
        <div class="inputwrapper animate1 bounceIn">
            <input type="text" placeholder="请输入您用户名" id="username" name="username">
        </div>
        <div class="inputwrapper animate2 bounceIn">
            <input type="password" placeholder="请输入您的密码" id="password"  name="password">
            <div id="hide" class="hide">密码错误</div>
        </div>
      
        <div class="inputwrapper animate3 bounceIn">
            <button name="submit" id="loginButton">登录</button>
        </div>
        <div class="inputwrapper animate4 bounceIn">
            <label class="pull-left">
                <input id="remember" type="checkbox" name="signin" class="remember" checked>
                记住密码
            </label>
        </div>
        
    <%--</form>--%>
</div><!--loginpanelinner-->

<script src="${path}/resources/js/jquery.min1.10.2.js"></script>
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="${path}/resources/js/bootstrap.js"></script>
<script src="${path}/resources/js/common.js"></script>
<script src="${path}/resources/js/jquery.placeholder.js"></script>
<script src="${path}/resources/js/admin/LoginController.js"></script>
<script>
$('input, textarea').placeholder();
jQuery(function ($) {
    var ERROR_MSGS = {"login.error.account.required":"用户名不能为空",
        "login.error.password.required":"密码必须为6-12位",
        "admin.account.not.exist":"用户名不存在",
        "admin.password.invalidate":"登陆密码错误"};

    $(document).ready(function () {
        $("#loginButton").click(function () {
            return $.LoginController.login({
                userNameID: "#username",
                passwordID: "#password",
                errorID: "#hide",
                ERROR_MSGS: ERROR_MSGS
            })
        });

        $("#password").keydown(function (event) {
            if (event.keyCode == "13") {//keyCode=13是回车键
                return $.LoginController.login({
                    userNameID: "#username",
                    passwordID: "#password",
                    errorID: "#hide",
                    ERROR_MSGS: ERROR_MSGS
                })
            }
        })

    });
});
</script>
</body>
</html>
