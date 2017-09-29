<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<script id="appeal_operations_template" type="text/x-dot-template">
    <div aria-label="Small button group" role="group" class="btn-group btn-group-xs">
        <button class="btn btn-default" type="button" data-toggle="modal"
                onclick="$.AppealManageController.editPage({{=it.id}},'{{=it.store_code}}','{{=it.status}}',$(this));">
            <span class="glyphicon glyphicon-pencil"></span> 审核</button>
        <button class="btn btn-default" type="button" data-toggle="modal"
                onclick="$.AppealManageController.deletePage({{=it.id}});">
            <span class="glyphicon glyphicon-trash"></span>删除</button>
    </div>
</script>