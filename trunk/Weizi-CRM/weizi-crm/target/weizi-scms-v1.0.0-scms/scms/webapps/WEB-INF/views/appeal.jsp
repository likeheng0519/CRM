<!doctype html>
<html>
<%@ include file="header.jsp" %>
<body>
<%@ include file="appeal-manage-operation.jsp" %>
<%@ include file="appeal-image-list.jsp" %>
<div class="main-container clearfix">
    <div class="row m20">
        <div class="col-md-6"></div>
        <label class="col-md-1 pt7 text-right">项目名称</label>
        <div class="col-md-2">
            <select id="appeal_project_list" class="form-control"  >
                <option value="0" selected="selected">ALL</option>
            </select>
        </div>

        <div class="col-md-3">
            <label class="pull-left pt7 text-right">商户编码&nbsp;&nbsp;&nbsp;&nbsp;</label>
            <div class="input-group">
                <input id="sSearch" type="text" class="form-control" placeholder="商户编码">
                <span class="input-group-btn">
                    <button class="btn btn-primary" type="button" onclick="$.AppealManageController.list();">搜索</button>
                </span>
            </div>
            <!-- /input-group -->
        </div>
    </div>
    <!--end search-area-->
    <div class="bg-white">
        <table id="appeal_manage_table" cellspacing="0" cellpadding="0" border="0" id="table"
               class="table table-hover table-bordered ">
        </table>
    </div>
</div>
<!--end main-container-->

<!-- Modal -->
<div class="modal fade" id="EditAppeal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">审核申诉</h4>
            </div>
            <div class="modal-body">
                <form role="form" class="form-horizontal">
                    <div class="form-group">
                        <label class="col-sm-2 control-label">商户编码:</label>
                        <input id="editAppealId" type="hidden">

                        <div class="col-sm-9">
                            <input type="text" disabled="true" placeholder="商户编码" id="editStoreCode"
                                   class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">申诉状态:</label>

                        <div class="col-sm-9">
                            <select type="text" class="form-control" id="editAppealStatus">
                                <option value="1">等待处理</option>
                                <option value="2">已经处理</option>
                                <option value="3">申诉资料不全，不予处理</option>
                                <option value="4">数据无误，无需更改</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">申诉内容:</label>

                        <div class="col-sm-9">
                            <textarea disabled="true" class="form-control" rows="3"
                                      id="editAppealContent">申请单说明</textarea>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary" onclick="$.AppealManageController.edit();">确定</button>
            </div>
            <hr>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>

<div class="modal fade" id="DeleteAppeal" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">删除申诉</h4>
            </div>
            <div class="modal-body">
                <form role="form" class="form-horizontal">
                    <div class="clearfix text-center">
                        <input type="hidden" id="deleteAppealId">
                        <span class="glyphicon glyphicon-info-sign text-danger font-40"></span>
                        <h4>你群定删除此申诉吗？</h4>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <input type="hidden" id="deleteUserId">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary" onclick="$.AppealManageController.delete();">确定</button>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>
<!-- /.modal -->
<script type="text/javascript">
    $(document).ready(function () {
        $.AppealManageController.initTitle("操作", "项目名称","商户编码", "申诉内容", "申诉图片", "申诉者",
                "审核人", "申诉状态", "申诉时间", "审核时间");
        $.AppealManageController.listProject();
        $.AppealManageController.list();
    });

</script>
</body>
</html>
