<!doctype html>
<html>
<%@ include file="header.jsp" %>
<body>
<%@ include file="user-manage-operation.jsp" %>
<div class="main-container clearfix">

    <div class="m20">
        <button class="btn btn-primary" type="button" data-toggle="modal" data-target="#NewUser">创建新用户</button>
    </div>
    <div class="bg-white">
        <table id="user_manage_table" cellspacing="0" cellpadding="0" border="0" class="table table-bordered table-hover">
        </table>
    </div>
</div>
<!--end main-container-->

<div class="modal fade" id="NewUser" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">新建用户</h4>
            </div>
            <div class="modal-body">
                <form role="form" class="form-horizontal">
                    <div class="form-group">
                        <label class="col-sm-2 control-label">用户名:</label>
                        <div class="col-sm-9">
                            <input id="username" type="text" placeholder="Policy Type" class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">用户类型:</label>
                        <div class="col-sm-9">
                            <select class="form-control" id="user_type">
                                <option value="1">客户</option>
                                <option value="2">销售经理</option>
                                <option value="3">销售代表</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">新密码:</label>
                        <div class="col-sm-9">
                            <input id="password" type="password" class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">确认密码:</label>

                        <div class="col-sm-9">
                            <input id="cfm_password" type="password" class="form-control">
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary" onclick="$.UserManageController.add();">确定</button>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>
<!-- /.modal -->
<div class="modal fade" id="EditUser" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">编辑用户</h4>
            </div>
            <div class="modal-body">
                <form role="form" class="form-horizontal">
                    <div class="form-group">
                        <label class="col-sm-2 control-label">用户名:</label>
                        <div class="col-sm-9">
                            <input id="editUserId" type="hidden">
                            <input type="text" placeholder="Policy Type"
                                   id="editUsername" class="form-control" disabled="true">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">用户类型:</label>
                        <div class="col-sm-9">
                            <select class="form-control" id="editUserType">
                                <option value="1">客户</option>
                                <option value="2">销售经理</option>
                                <option value="3">销售代表</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">原始密码:</label>
                        <div class="col-sm-9">
                            <input id="oldPassword" type="password" class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">输入密码:</label>
                        <div class="col-sm-9">
                            <input id="editPassword" type="password" class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">确认密码:</label>
                        <div class="col-sm-9">
                            <input id="editPassword2" type="password" class="form-control">
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary" onclick="$.UserManageController.edit();">确定</button>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>
<!-- /.modal -->


<div class="modal fade" id="DeleteUser" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">删除用户</h4>
            </div>
            <div class="modal-body">
                <form role="form" class="form-horizontal">
                    <div class="clearfix text-center">
                        <span class="glyphicon glyphicon-info-sign text-danger font-40"></span>
                        <h4>你群定删除用户<span id="deleteUsername" class="text-danger"></span>吗？</h4>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <input type="hidden" id="deleteUserId">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary" onclick="$.UserManageController.delete();">确定</button>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>
<!-- /.modal -->
<script type="text/javascript">
    $(document).ready(function () {
        $.UserManageController.initTitle("用户名称", "用户类型", "创建时间", "用户状态");
        $.UserManageController.list();
    });
</script>
</body>
</html>
