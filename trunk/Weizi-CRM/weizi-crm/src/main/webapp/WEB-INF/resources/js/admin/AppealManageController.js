$(function () {

    $.AppealManageController = function (elm, config) {};

    $.AppealManageController.titles = {};

    $.AppealManageController.initTitle = function(operation_title, project_name_title, store_code_title,content_title,
                                                  image_title, author_title,operator_title,status_title,
                                                  created_title,updated_title) {

        var titles = $.AppealManageController.titles;
        titles.project_name_title = project_name_title,
        titles.store_code_title = store_code_title,
        titles.content_title = content_title,
        titles.image_title = image_title,
        titles.author_title = author_title,
        titles.operator_title = operator_title;
        titles.status_title = status_title;
        titles.created_title = created_title;
        titles.updated_title = updated_title;
        titles.operation_title = operation_title;
    }

    $.extend($.AppealManageController, {

            list: function() {
            var aoColumns = [
                {
                    "mData": "id", "sTitle": $.AppealManageController.titles.operation_title, "bSearchable": false, "bSortable": false,
                    mRender: function (data, type, row) {
                        return $.AppealManageController.showOperations(row);}
                },
                {"mData": "project", "sTitle": $.AppealManageController.titles.project_name_title,
                    mRender: function(data, type, row) {
                        if(data==null){
                            return "";
                        }
                        return data.name;}
                },
                {"mData": "store_code", "sTitle": $.AppealManageController.titles.store_code_title},
                {"mData": "authorUser.username", "sTitle": $.AppealManageController.titles.author_title},
                {"mData": "operatorUser.username", "sTitle": $.AppealManageController.titles.operator_title,
                    mRender: function(data, type, row) {
                        if (data == undefined || data == null) return '';
                        return data}
                },
                {"mData": "content", "sTitle": $.AppealManageController.titles.content_title},
                {"mData": "status", "sTitle": $.AppealManageController.titles.status_title,
                    mRender: function(data, type, row) {
                    if (data == undefined || data == null) return '';
                    var userTypeList = ["","等待处理","已经处理","申诉资料不全，不予处理","数据无误，无需更改"]
                    return userTypeList[data]}
                },
                {"mData": "id", "sTitle": $.AppealManageController.titles.image_title, "bSearchable": false, "bSortable": false,
                    mRender: function (data, type, row) {
                        return $.AppealManageController.showImageList(row);}
                },
                {"mData": "created", "sTitle": $.AppealManageController.titles.created_title,
                    mRender: function(data, type, row) {
                    if (data == undefined || data == null) return '';
                    var created = new Date(data);
                    return created.format("dd/MM/yyyy hh:mm:ss");}
                },
                {"mData": "updated", "sTitle": $.AppealManageController.titles.updated_title,
                    mRender: function(data, type, row) {
                        if (data == undefined || data == null) return '';
                        var created = new Date(data);
                        return created.format("dd/MM/yyyy hh:mm:ss");}
                }
                /*{"mData": "id", "sTitle": $.AppealManageController.titles.operation_title},*/
            ];
            var tableInstance = $('#appeal_manage_table').dataTable({
                "sDom": '<"top from-input">rt<"bottom"ip><"clear">',
                "bJQueryUI": false,
                "bAutoWidth": false,
                "bScrollX": true,
                "bProcessing": false,
                "bDestroy": true,
                "bPaginate": true,
                "bServerSide": true,
                "bFilter": true,
                "bScrollAutoCss": true,
                "sScrollX":true,
                "bSort": false,
                "bInfo": true,
                "bStateSave": true,
                "iDisplayLength": 20,
                "aaSorting": [],
                "sAjaxSource": '/appeal/list',
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
                    var sSearch = $('#sSearch').val();
                    var projectFilter = $('#appeal_project_list').val();
                    aoData.push(
                        {"name": "p_field", "value": "created"},
                        {"name": "p_type", "value": "asc"},
                        {"name": "p_number", "value": 1},
                        {"name": "p_size", "value": 20},
                        {"name": "sSearch", "value": sSearch},
                        {"name": "projectFilter", "value": projectFilter}
                    );
                },
                "sServerMethod": 'GET'
            });
            $('#appeal_manage_table').data("appeal_manage_table", tableInstance);
        },

        listProject: function(){
            $.ajax({
                type: 'POST',
                url: '/metadata/user',
                headers:{},
                data: {"user_id":0},
                async: false,
                cache: false,
                success: function(data) {
                    $.each(data, function (i, item) {
                        $("#appeal_project_list").append("<option value='"+item["id"]+"'>"+item["name"]+"</option>");
                    });
                },
                error: function (xhr, error, thrown) {
                    alert(error);
                    alert(xhr);
                }
            });
        },

        filter: function(id, name){
            alert(name);
            $.ajax({
                type: 'POST',
                url: '/appeal/filter',
                headers:{},
                data: {"user_id":0},
                async: false,
                cache: false,
                success: function(data) {
                    $.each(data, function (i, item) {
                        $("#ul_project_name").append("<li><a href='javascript:void(0);' " +
                            "onclick='$.AppealManageController.filter("+item["id"]+"," +
                            '"'+item["name"]+'"'+");'>" + item["name"]+"</a></li>");
                    });
                },
                error: function (xhr, error, thrown) {
                    alert(error);
                    alert(xhr);
                }
            });
        },

        showOperations: function (row) {
            var tmpl = doT.template($("#appeal_operations_template").html());
            var html = tmpl(row);
            return html;
        },

        showImageList: function (row) {
            var tmpl = doT.template($("#appeal_image_list_template").html());
            var html = tmpl(row);
            return html;
        },

        editPage: function(appeal_id, store_code, status, obj){
            $("#editAppealId").val(appeal_id);
            $("#editStoreCode").val(store_code);
            $("#editAppealStatus").val(status);
            $("#editAppealContent").val(obj.parents("tr").find("td").eq(4).html());
            $("#EditAppeal").modal("show");
        },

        deletePage: function(appeal_id){
            $("#deleteAppealId").val(appeal_id);
            $("#DeleteAppeal").modal("show");
        },

        edit: function(){
            var appeal_id = $("#editAppealId").val();
            var status = $("#editAppealStatus").val();
            var data = {"appeal_id":appeal_id,"status":status};
            $.ajax({
                type: 'POST',
                url: '/appeal/edit',
                headers:{},
                data: data,
                async: false,
                cache: false,
                success: function(data) {
                    $.AppealManageController.list();
                },
                error: function (xhr, error, thrown) {
                    alert(error);
                    alert(xhr);
                }
            });
            $("#EditAppeal").modal("hide");
        },

        delete: function(){
            var appeal_id = $("#deleteAppealId").val();
            var data = {"appeal_id":appeal_id,"status":0};
            $.ajax({
                type: 'POST',
                url: '/appeal/edit',
                headers:{},
                data: data,
                async: false,
                cache: false,
                success: function(data) {
                    $.AppealManageController.list();
                },
                error: function (xhr, error, thrown) {
                    alert(error);
                    alert(xhr);
                }
            });
            $("#DeleteAppeal").modal("hide");
        }
  });
});