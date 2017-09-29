<!doctype html>
<html>
<%@ include file="header.jsp" %>
<body>
<div class="main-container clearfix">
        <div class="m20">
            <div class="row">
                <div class="col-md-2">
                    <div class="btn-group">
                        <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown">
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;选择项目名称&nbsp;&nbsp;<span class="caret"></span>&nbsp;&nbsp;&nbsp;&nbsp;
                        </button>
                        <ul id="ul_project_name" class="dropdown-menu js-type-list" role="menu">

                        </ul>
                    </div>
                </div>
                <div class="col-md-10">
                    <div id="search" class="search-area">
                        <form role="form" class="form-inline">
                            <div class="form-group first">
                                <div id="div_search_select">

                                </div>
                                <div id="div_search_input">

                                </div>

                            </div>

                        </form>
                    </div>
                </div>
            </div>

        </div><!--end search-area-->

        <section class="m20">
            <%--<div class="blue"><strong>门店总数 ：<span id="total">请稍等......</span></strong></div>--%>
            <div class="panel panel-info m-t-xs">

                <!-- Default panel contents -->
                <div id="project_current_name" class="panel-heading">
                </div>
                <!-- Table -->
                <table cellspacing="0" cellpadding="0" border="0" id="project_content_table" class="table table-hover table-bordered ">

                </table>
            </div>
        </section>




    </div><!--end right-side-->
</div><!--end main-container-->

<!-- Modal -->
<div class="modal fade" id="Edit" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">编辑部门</h4>
            </div>
            <div class="modal-body">
                <form role="form" class="form-horizontal">
                    <div class="form-group">
                        <label class="col-sm-2 control-label" for="inputEmail3">部门名称:</label>
                        <div class="col-sm-9">
                            <input type="text" placeholder="部门名称" id="inputEmail3" class="form-control">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-2 control-label" >部门类型:</label>
                        <div class="col-sm-9">
                            <input type="text" placeholder="部门类型"  class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label" >部门状态:</label>
                        <div class="col-sm-9">
                            <select type="text" class="form-control">
                                <option>禁用</option>
                                <option>激活</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label" >权限设置:</label>
                        <div class="col-sm-9">
                            <select type="text" class="form-control">
                                <option>一般权限</option>
                                <option>管理员权限</option>
                            </select>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-2 control-label" >部门说明:</label>
                        <div class="col-sm-9">
                            <textarea class="form-control" rows="3">部门说明</textarea>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary">确定</button>
            </div>
            <hr>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->



<div class="modal fade" id="Deleteuser" tabindex="-1" role="dialog"  aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">删除部门<small class="red" id="delete_content_message" style="display: none"></small></h4>
            </div>
            <div class="modal-body">
                <form id="form-content-delete" role="form" class="form-horizontal">
                    <div class="clearfix">
                        <div class="col-md-2 text-center"><span class="glyphicon glyphicon-info-sign text-danger font-40"></span></div>
                        </br></br></br>
                        <div class="col-md-10"><h5>你群定删除门店<span class="text-danger"></span>吗？</h5></div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary" onclick="deleteProjectContent();">确定</button>
            </div>
            <hr>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<script>

    var table_project;
    var columns = [];
    var columsName=null;
    var current_metadata_id=0;
    var current_content_id=0;
    var searchCondition={};
    var current_store=""

    $(function () {

        function ObjColumn(id, name) {
            this.data = id;
            this.title = name;
            this.bSortable = false;
        }

        function getAllColumnsName(metadata_id){
            var columnsNameList={};
            $.ajax({
                "url": "/metadata/"+metadata_id,
                async : false,
                "success": function (jsondata) {
                    var count=jsondata["length"];
                    columnsNameList["length"]=count;
                    for(var i=1;i<=count;i++){
                        columnsNameList["f"+i]=jsondata["f"+i+"_title"]
                    }
                },
                error: function(){
//                    window.location.href="/";
                }
            });
            return columnsNameList;
        }

        function setSearchCondition(metadata_id){
            $.ajax({
                url: "/search_condition/"+metadata_id,
                async : true,
                "success": function (jsondata) {
                    if(jsondata==""){
                        $("#search").addClass("hidden");
                    }else{
                        $("#search").removeClass("hidden");
                        $("#div_search_select").empty();
                        $("#div_search_input").empty();
                        for(var j=1;j<=5;j++){
                            if(jsondata["field"+j]!=null&&jsondata["field"+j+"_title"]!=null&&jsondata["field"+j+"_type"]==1){
                                $("#div_search_select").append("<select class='form-control' id='search_condition_"+j+"' name='"+jsondata["field"+j]+"'></select>")
                                var distinct=jsondata["field"+j+"_distinct"];
                                $("#search_condition_"+(j)).append("<option value=''>"+"请选择"+jsondata["field"+j+"_title"]+"</option>");
                                $.each(distinct, function(i, item){
                                    $("#search_condition_"+(j)).append("<option value='"+item+"'>"+item+"</option>");
                                });
                            }
                            if(jsondata["field"+j]!=null&&jsondata["field"+j+"_title"]!=null&&jsondata["field"+j+"_type"]==2){
                                $("#div_search_input").append("<label for='search_condition_"+j+"'>"+"请输入"+jsondata["field"+j+"_title"]+":</label>")
                                $("#div_search_input").append("<input type='text' id='search_condition_"+j+"' name='"+jsondata["field"+j]+"' class='form-control'>")
                            }
                        }
                        $("#div_search_input").append("&nbsp;&nbsp;<button id='action_search' class='btn btn-primary' type='button'><span class='glyphicon glyphicon-search'></span> 搜索</button>");
                    }
                },
                error: function(){
                    $("#search").addClass("hidden");
                }
            });
        }


        function createTable(columns) {
            return $("#project_content_table").DataTable({
                "sort":false,
                ajax: {
                    url: "/content/metadata/"+current_metadata_id,
                    data: searchCondition,
                    async : true,
                    type: "GET",
                    dataType:"json"

                },
                "autoWidth":true,
                "scrollX": true,
                //允许销毁实例
                "destroy": true,
                //列的配置信息
                "columns": columns,
                "columnDefs":[
                    {"targets":[0], "data":"id", "render":function(data, type, full){
                        return "<a class='btn btn-default btn-xs'  onclick='getRowNo(this)' target='_blank' href='/${ctx}/project/content/detail/"+data+"?metadata_id="+current_metadata_id+"'>详细信息</a>"
                                +"<a class='btn btn-default hidden' target='_blank'  data-toggle='modal'   disabled='true' data-target='#Deleteuser' onclick='getRowNo(this)' name='"+data+"'>删除门店</a>"
                     }}
                    ],
                "language": {
                    "processing":"查询中......",
                    "loadingRecords":"正在加载记录中,请稍等......",
                    "lengthMenu": "每页_MENU_ 条记录",
                    "zeroRecords": "没有符合条件记录",
                    "info": "第 _PAGE_ 页 ( 总共 _PAGES_ 页 )&nbsp;&nbsp;&nbsp;&nbsp;门店总数：_TOTAL_ ",
                    "infoEmpty": "目前项目中没有任何记录",
                    "search": "搜索：",
                    "infoFiltered": "(从 _MAX_ 条记录过滤)",
                    "paginate": {
                        "previous": "上一页",
                        "next": "下一页"
                    }
                },

                "dom": "<'row'<'col-xs-3'i><'col-xs-2'l><'col-xs-6'f>>" + "t" + "<'row'<'col-xs-6'i><'col-xs-6'p>>"

            });
        }


        $(document).on("click","#action_search",function(e){

            if($("#search_condition_1").length>0){
                searchCondition.field1=$("#search_condition_1").prop("name");
                if($("#search_condition_1")[0].tagName=='SELECT'){
                    searchCondition.field1_type='1';
                }else{
                    searchCondition.field1_type='2';
                }
                searchCondition.field1_content=$("#search_condition_1").prop("value");
            }
            if($("#search_condition_2").length>0){
                searchCondition.field2=$("#search_condition_2").prop("name");
                if($("#search_condition_2")[0].tagName=='SELECT'){
                    searchCondition.field2_type='1';
                }else{
                    searchCondition.field2_type='2';
                }
                searchCondition.field2_content=$("#search_condition_2").prop("value");
            }
            if($("#search_condition_3").length>0){
                searchCondition.field3=$("#search_condition_3").prop("name");
                if($("#search_condition_3")[0].tagName=='SELECT'){
                    searchCondition.field3_type='1';
                }else{
                    searchCondition.field3_type='2';
                }
                searchCondition.field3_content=$("#search_condition_3").prop("value");
            }
            if($("#search_condition_4").length>0){
                searchCondition.field4=$("#search_condition_4").prop("name");
                if($("#search_condition_4")[0].tagName=='SELECT'){
                    searchCondition.field4_type='1';
                }else{
                    searchCondition.field4_type='2';
                }
                searchCondition.field4_content=$("#search_condition_4").prop("value");
            }
            if($("#search_condition_5").length>0){
                searchCondition.field5=$("#search_condition_5").prop("name");
                if($("#search_condition_5")[0].tagName=='SELECT'){
                    searchCondition.field5_type='1';
                }else{
                    searchCondition.field5_type='2';
                }
                searchCondition.field5_content=$("#search_condition_5").prop("value");
            }
            console.log("searchCondition:"+searchCondition);
            table_project = createTable(columns);

        });


        $.ajax({
            url: "/metadata/user",
            data:{user_id:${user_id}},
            async : false,
            type: "POST",
            dataType:"json",
            "success": function (jsondata) {
                $.each(jsondata, function (i, item) {
                    //console.log("project name:" + ':' + item["name"]);
                    if(i==0){
                        $("#ul_project_name").append("<li class='active'>"+"<a href='javascript:void(0);' name='"+item["id"]+"'>"+item["name"]+"</a></li>");
                        $("#project_current_name").empty()
                        $("#project_current_name").append("<span>"+"当前项目号:["+item["id"]+"]&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;项目名称:["+item["name"]+"]</span>")
                        current_metadata_id=item["id"];
                    }else{
                        $("#ul_project_name").append("<li><a href='javascript:void(0);' name='"+item["id"]+"'>"+item["name"]+"</a></li>");
                    }

                });

            },

            error: function(){
//                window.location.href="/";
            }
        });

        onLoadPageProject();

        function onLoadPageProject() {
            columsName = getAllColumnsName(current_metadata_id);
//            console.log(columsName);

            var number = columsName["length"];
            columns=[];
            columns.push(new ObjColumn("id","信息"));
            for (var i = 1; i <= number; i++) {
                var item = new ObjColumn("f" + i, columsName["f" + i]);
                columns.push(item);
            }

            table_project = createTable(columns);
            setSearchCondition(current_metadata_id);
            $("#user-name").html("${user_name}");
        }



        $('.js-type-list li').click(function(){
            $(this).addClass('active').siblings().removeClass('active');
            var item=$($(this)[0].children[0]).prop("name");
            var text=$(this)[0].innerText;

            current_metadata_id=$($(this)[0].children[0]).prop("name");
            $("#project_current_name").empty()
            $("#project_current_name").append("<span>当前项目号:["+current_metadata_id+"]&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;项目名称:["+text+"]</span>")
            //改变列的结构，先销毁前面的实例
            //"+item["id"]+
            table_project.destroy();
            // 列改变了，需要清空table
            $("#project_content_table").empty();
            onLoadPageProject();
        })




    });




    function deleteProjectContent() {

        $("#form-content-delete").ajaxSubmit({
            url: '/content/delete/'+current_content_id,
            type: 'get',
            async: true,
            dataType: 'json',
            success: function (jsondata) {
                console.log(jsondata)
                if(jsondata["status"]=="success"){
                    $(".modal").modal("hide")
                    delRow();
                }else {
                    console.log("delete_content_message error.")
                    $('#delete_content_message').html("")
                    $('#delete_content_message').append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;删除部门失败")
                    $('#delete_content_message').fadeIn().fadeOut(30000);
                }
            },
            error: function (XmlHttpRequest, textStatus, errorThrown) {
                console.log("delete_content_message error.")
                $('#delete_content_message').html("")
                $('#delete_content_message').append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;删除部门失败")
                $('#delete_content_message').fadeIn().fadeOut(30000);
                throw new Error("delete_content_message error.");
            }
        });

    }


    //得到行对象
    function getRowObj(obj)
    {   var i = 0;
        while(obj.tagName.toLowerCase() != "tr")
        {    obj = obj.parentNode;
            if(obj.tagName.toLowerCase() == "table")
                return null;
        }
        return obj;
    }
    //根据得到的行对象得到所在的行数
    function getRowNo(obj)
    {   current_row_object = getRowObj(obj);
        current_content_id = obj.getAttribute('name');
        console.log("current_content_id:"+current_content_id);
        var trArr = current_row_object.parentNode.children;
        for(var trNo= 0; trNo < trArr.length; trNo++){
            if(current_row_object == current_row_object.parentNode.children[trNo]){
                current_store=document .getElementById ("project_content_table").rows[trNo+1].cells[3].innerText
                $(".text-danger").html("");$(".text-danger").append(current_store)
                console.log("current_store:"+current_store);
                console.log("current_metadata_id:"+current_metadata_id);
            }
        }
    }
    //删除行
    function delRow(){
        if(current_row_object != null){
            current_row_object.parentNode.removeChild(current_row_object);
        }else{
            throw new Error("the given object is not contained by the table");
        }
        console.log("current_metadata_id:"+current_metadata_id)
    }




</script>
</body>
</html>
