$(function () {

    $.UserManageController = function (elm, config) {};

    $.UserManageController.titles = {};

    $.UserManageController.initTitle = function(admin_name_title,
                                                            admin_type_title,
                                                            created_title,
                                                            operation_title) {

        var titles = $.UserManageController.titles;
        titles.admin_name_title = admin_name_title,
        titles.admin_type_title = admin_type_title,
        titles.created_title = created_title,
        titles.operation_title = operation_title;
    }

    $.extend($.UserManageController, {

        list: function(sSearch) {
            var aoColumns = [
                {"mData": "username", "sTitle": $.UserManageController.titles.admin_name_title},
                {"mData": "userType", "sTitle": $.UserManageController.titles.admin_type_title,
                    mRender: function(data, type, row) {
                        if (data == undefined || data == null) return '';
                        var userTypeList = ["管理员","客户","销售经理","销售代表"]
                        return userTypeList[data];
                }},
                {"mData": "created", "sTitle": $.UserManageController.titles.created_title,
                    mRender: function(data, type, row) {
                    if (data == undefined || data == null) return '';
                    var created = new Date(data);
                    return created.format("dd/MM/yyyy");
                }},
                /*{"mData": "id", "sTitle": $.UserManageController.titles.operation_title},*/
                {
                    "mData": "id", "sTitle": $.UserManageController.titles.operation_title, "bSearchable": false, "bSortable": false,
                    mRender: function (data, type, row) {
                        return $.UserManageController.showOperations(row);
                    }
                }
            ];
            var tableInstance = $('#user_manage_table').dataTable({
                "sDom": '<"top from-input">rt<"bottom"ip><"clear">',
                "bJQueryUI": false,
                "bAutoWidth": true,
                "bProcessing": false,
                "bDestroy": true,
                "bPaginate": true,
                "bServerSide": true,
                "bFilter": true,
                "bScrollAutoCss": true,
                "bSort": true,
                "bInfo": true,
                "bStateSave": true,
                "iDisplayLength": 20,
                "aaSorting": [],
                "sAjaxSource": '/user/list',
                "aoColumns": aoColumns,
                "sAjaxDataProp": "list",
                "oLanguage": {},
                "fnServerData": function (sUrl, aoData, fnCallback, oSettings) {

                    var sEcho = $.CommonFn.DataTable.fnGetKey(aoData, "sEcho");
                    var iRequestStart = $.CommonFn.DataTable.fnGetKey(aoData, "iDisplayStart");
                    var iRequestLength = $.CommonFn.DataTable.fnGetKey(aoData, "iDisplayLength");
                    console.log("page_size:" + iRequestLength);
                    var sortIndex = $.CommonFn.DataTable.fnGetKey(aoData, "iSortCol_0");
                    var sortType = $.CommonFn.DataTable.fnGetKey(aoData, "sSortDir_0");
                    var sortColumn = $.CommonFn.DataTable.fnGetKey(aoData, "mDataProp_" + sortIndex);
                    $.CommonFn.DataTable.fnSetKey(aoData, "p_number", iRequestStart / iRequestLength + 1);
                    $.CommonFn.DataTable.fnSetKey(aoData, "p_size", 20);
                    $.CommonFn.DataTable.fnSetKey(aoData, "p_field", sortColumn);
                    $.CommonFn.DataTable.fnSetKey(aoData, "p_type", sortType);
                    oSettings.jqXHR = $.ajax({
                        "url": sUrl,
                        "data": aoData,
                        cache: false,
                        "success": function (json) {
                            if (json.sError) {
                                oSettings.oApi._fnLog(oSettings, 0, json.sError);
                            }
                            $(oSettings.oInstance).trigger('xhr', [oSettings, json]);

                            json.sEcho = sEcho;
                            json.iTotalRecords = json.totalCount;
                            json.iTotalDisplayRecords = json.totalCount;

                            fnCallback(json);
                        },
                        "dataType": "json",
                        "cache": false,
                        "type": oSettings.sServerMethod,
                        "error": function (xhr, error, thrown) {
                            if (error == "parsererror") {
                                oSettings.oApi._fnLog(oSettings, 0, "DataTables warning: JSON data from " +
                                    "server could not be parsed. This is caused by a JSON formatting error.");
                            }
                        }
                    });
                },
                "fnServerParams": function (aoData) {
                    var iRequestStart = $.CommonFn.DataTable.fnGetKey(aoData, "iDisplayStart");
                    var iRequestLength = $.CommonFn.DataTable.fnGetKey(aoData, "iDisplayLength");
                    aoData.push(
                        {"name": "p_field", "value": "created"},
                        {"name": "p_type", "value": "asc"},
                        {"name": "p_number", "value": 1},
                        {"name": "p_size", "value": 20}
                    );
                    if(sSearch!=null&&sSearch!=""&&sSearch!=undefined){
                        aoData.push({"name": "sSearch", "value": sSearch});
                    }
                },
                "sServerMethod": 'GET'
            });
            $('#user_manage_table').data("user_manage_table", tableInstance);
        },

        showOperations: function (row) {
            var tmpl = doT.template($("#user_operations_template").html());
            var html = tmpl(row);
            return html;
        },

        add: function() {
            var username = $("#username").val();
            var user_type = $("#user_type").val();
            var password = $("#password").val();
            var cfm_password = $("#cfm_password").val();
            if(username==""||user_type==""||password==""||password!=cfm_password){
                return;
            }
            if(password.length>12||password.length<6){
                alert("密码必须为6-12位");
                return;
            }
            var data = {"username":username,"user_type":user_type,"password":password};
            $.ajax({
                type: 'POST',
                url: '/user/add',
                headers:{},
                data: data,
                async: false,
                cache: false,
                success: function(data) {
                    $.UserManageController.list();
                }
            });
            $("#NewUser").modal("hide");
        },

        editPage: function(user_id, username, user_type){
            $("#editUserId").val(user_id);
            $("#editUsername").val(username);
            var user_type = $("#editUserType").val(user_type);
            $("#EditUser").modal("show");
        },

        edit: function(){
            var user_id = $("#editUserId").val();
            var user_type = $("#editUserType").val();
            var old_password = $("#oldPassword").val();
            var new_password = $("#editPassword").val();
            var cfm_password = $("#editPassword2").val();
            if(new_password!=cfm_password){
                alert("新密码和确认密码不一致");
                return;
            }
            if(new_password!=0||old_password!=0){
                if(old_password.length>12||old_password.length<6){
                    alert("密码必须为6-12位");
                    return;
                }
                if(new_password.length>12||new_password.length<6){
                    alert("密码必须为6-12位");
                    return;
                }
            }
            var data = {"user_id":user_id,"user_type":user_type,
                "old_password":old_password,"new_password":new_password};
            $.ajax({
                type: 'POST',
                url: '/user/edit',
                headers:{},
                data: data,
                async: false,
                cache: false,
                success: function(data) {
                    alert("编辑成功！");
                    $.UserManageController.list();
                },
                error: function (xhr, error, thrown) {
                    var response = jQuery.parseJSON(xhr.responseText);
                    var message = response.message;
                    var messageStr = {"admin.password.invalidate":"原密码错误"};
                    alert(messageStr[message]);
                }
            });
            $("#EditUser").modal("hide");
        },

        deletePage: function(user_id,username){
            $("#deleteUserId").val(user_id);
            $("#deleteUsername").html(username);
            $("#DeleteUser").modal("show");
        },

        delete: function(){
            var user_id=$("#deleteUserId").val();
            if(user_id==null||user_id==undefined){
                return;
            }
            var data = {"user_id":user_id};
            $.ajax({
                type: 'POST',
                url: '/user/delete',
                headers:{},
                data: data,
                async: false,
                cache: false,
                success: function(data) {
                    $.UserManageController.list();
                }
            });
            $("#DeleteUser").modal("hide");
        }
  });
});