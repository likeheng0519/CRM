<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<script id="user_operations_template" type="text/x-dot-template">
<div aria-label="Small button group" role="group" class="btn-group btn-group-xs">
    <button class="btn btn-default" type="button" data-toggle="modal"
            onclick="$.UserManageController.editPage({{=it.id}},'{{=it.username}}','{{=it.userType}}');">
        <span class="glyphicon glyphicon-pencil"></span>编辑</button>
    {{?it.userType != 0}}
    <button class="btn btn-default" type="button" data-toggle="modal"
            onclick="$.UserManageController.deletePage({{=it.id}},'{{=it.username}}');">
        <span class="glyphicon glyphicon-trash"></span>删除</button>
    {{?}}
</div>
</script>