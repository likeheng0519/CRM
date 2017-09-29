<!doctype html>
<html>
<%@ include file="header.jsp" %>
<body>
<div class="main-container clearfix">

        <div class="m20">
            <div class="btn-group">
                <button type="button" data-toggle="modal" data-target="#createProjectModal" class="btn btn-primary">
                    &nbsp;新建项目<small>&nbsp;(从CSV导入)&nbsp;</small>
                </button>
            </div>
        </div><!--end search-area-->

        <section class="m20 bg-white">
            <table  cellspacing="0" cellpadding="0" border="0" id="table_projects" class="table table-hover table-bordered ">
                <thead>
                <tr role="row">
                    <th>项目ID</th>
                    <th>项目名称</th>
                    <th>创建时间</th>
                    <th>修改时间</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <c:if test="${projectMetadataList!=null && projectMetadataList.size() > 0}">
                    <c:forEach items="${projectMetadataList}" var="item">
                    <tr >
                        <td>${item.id}</td>
                        <%--<td><a href="/project/content/detail/${item.id}" target="_blank"> ${item.name}</a></td>--%>
                        <td> ${item.name}</td>
                        <td>${item.created}</td>
                        <td>${item.updated}</td>
                        <td>

                            <div aria-label="Small button group" role="group" class="btn-group btn-group-xs">
                                <button class="btn btn-default" type="button" data-toggle="modal" data-target="#editModal" onclick="getRowNo(this)"><span class="glyphicon glyphicon-pencil"></span> 修改名称</button>
                                <button class="btn btn-default" type="button" data-toggle="modal" data-target="#searchModal" onclick="getRowNo(this)"><span class="glyphicon glyphicon-search"></span> 定义搜索项</button>
                                <button class="btn btn-default" type="button" data-toggle="modal" data-target="#authorizationModal" onclick="getRowNo(this);getCustomeres();"><span class="glyphicon glyphicon-wrench"></span> 项目授权</button>
                                <button class="btn btn-default" type="button" data-toggle="modal" data-target="#importCSVModal" onclick="getRowNo(this)"><span class="glyphicon glyphicon-share-alt"></span> 导入项目数据(CSV)</button>
                                <button class="btn btn-default" type="button" data-toggle="modal" data-target="#deleteModal" onclick="getRowNo(this)"><span class="glyphicon glyphicon-trash"></span> 删除</button>
                            </div>

                        </td>
                    </tr>
                    </c:forEach>
                </c:if>

                </tbody>
            </table>

        </section>

    </div><!--end right-side-->
</div><!--end main-container-->

<!-- Modal -->
<div class="modal fade" id="createProjectModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">新建项目 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp<small class="red" ><i id="upload_project_message">项目名称和文件一定要填写</i></small></h4>
            </div>
            <div class="modal-body">
                <form id="form-project-import" role="form" class="form-horizontal" accept-charset="UTF-8" >
                    <div class="form-group">
                        <label class="col-sm-3 control-label">部门名称:</label>
                        <div class="col-sm-9">
                            <input type="text" id="new_project_name" name="metadata_name" placeholder="项目名称"  class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label" >导入项目数据CSV:</label>
                        <div class="col-sm-9">
                            <input id="new_project_file" type="file" size="22" name="file_upload" class="form-file" accept=".csv">
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary" onclick="uploadProjectMetadata();">确定</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
<div class="modal fade" id="importCSVModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">导入CSV&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<small  class="red" ><i id="upload_content_message">文件一定要填写</i></small></h4>
            </div>
            <div class="modal-body">
                <form id="form-content-import" role="form" class="form-horizontal">
                    <div class="form-group">
                        <label class="col-sm-2 control-label" >导入CSV:</label>
                        <div class="col-sm-9">
                            <input id="new_content_file" type="file" size="22" name="file_upload" class="form-file" accept=".csv">
                            <input id="new_metadata_id" name="metadata_id" type="text" hidden/>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <span class="pull-left">注：清除此项目所有数据</span>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary" onclick="uploadProjectContent();">确定</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<div class="modal fade" id="editModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">修改名称&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<small  class="red"><span id="update_project_name_message">名称必须填写</span></small></h4>
            </div>
            <div class="modal-body">
                <form id="form-project-update-name" role="form" class="form-horizontal">
                    <div class="form-group">
                        <label class="col-sm-2 control-label">部门名称:</label>
                        <div class="col-sm-9">
                            <input id="project_new_name" type="text" placeholder="部门名称"  class="form-control">
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary" onclick="updateProjectMetadataName();">确定</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->


<div class="modal fade" id="searchModal" tabindex="-1" role="dialog"   aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">定义搜索项 <small class="red" id="maxSearchContent" style="display: none">最多选5个</small></h4>
            </div>
            <div class="modal-body">
                <form role="form" class="form-horizontal">
                    <div class="row">
                        <div class="col-xs-5">
                            <div id="div_search_condition" class="clearfix search-content-list">

                            </div>
                        </div>
                        <div class="col-xs-2 v-middle">
                            <button type="button" class="btn btn-sm btn-block btn-info" id="addToSelect"> 添加到下拉选项 <i class="fa fa-angle-double-right"></i>
                            </button>
                            <button type="button" class="btn btn-sm btn-block btn-info" id="addToText"> 添加到文本选项 <i class="fa fa-angle-double-right"></i></button>
                            <button type="button" class="btn btn-sm btn-block btn-default" id="removeFromSelect"><i class="fa fa-angle-double-left"></i> 移除下拉选项</button>
                            <button type="button" class="btn btn-sm btn-block btn-default" id="removeFromText"><i class="fa fa-angle-double-left"></i> 移除文本选项</button>
                        </div>
                        <div class="col-xs-5">
                            <div id="select_condition" class="select-wrap">
                                <p> 下拉选项</p>

                            </div>
                            <div id="input_condition"  class="text-wrap mt10">
                                <p> 文本选项</p>
                            </div>
                        </div>
                    </div>

                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary" onclick="updateSearchCondition();">确定</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->


<div class="modal fade" id="authorizationModal" tabindex="-1" role="dialog"   aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">项目授权<small class="red" id="customerPermission" style="display: none"></small></h4>
            </div>
            <div class="modal-body">
                <form role="form" class="form-horizontal">
                    <div class="form-group">
                        <label class="col-sm-2 control-label">客户列表:</label>
                        <div class="col-sm-9 authorization-list">

                        </div>
                    </div>

                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary" onclick="updateProjectPermissionForCustomer();">确定</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->



<div class="modal fade" id="deleteModal" tabindex="-1" role="dialog"  aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">删除项目<small class="red" id="delete_project_message" style="display: none"></small></h4>
            </div>
            <div class="modal-body">
                <form id="form-project-delete" role="form" class="form-horizontal">
                    <div class="clearfix text-center">
                        <div class="glyphicon glyphicon-info-sign text-danger font-40"></div>
                        <h4>你群定删除部门<span id="delete_bumen" class="text-danger"></span>吗？</h4>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary" onclick="deleteProjectMetadata();">确定</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<script>
    var current_row_object=null;
    var current_metadata_id=0
    var current_metadata_name=""
    var columsName=null;

    function updateProjectPermissionForCustomer(){
        var customer_permissions=[];
        $(".authorization-list input:checkbox").each(function(index, domEle){
            console.log($(domEle).prop("id"))
            console.log($(domEle).prop("checked"))
            var item={};
            item["user_id"]=$(domEle).prop("id")
            if($(domEle).prop("checked")==true){
                item["hasPermission"]="1"
            }else{
                item["hasPermission"]="0"
            }
            item["metadata_id"]=current_metadata_id;
            item["user_type"]="1";
            item["user_name"]=$(domEle).parents("label")[0].innerText;
            item["user_permission_id"]=$(domEle).prop("name")
            customer_permissions.push(item);

        })

        var permissions=JSON.stringify(customer_permissions)
        console.log(permissions)
        $.ajax({
            "url": "/userpermission/update",
            data: {"permissions": permissions},
            type: 'post',
            dataType: "json",
            async : false,
            "success": function (jsondata) {
                console.log("customer_permissions:"+jsondata)
                if(jsondata["status"]=="success"){

                    window.location.reload();
                }else {
                    $('#customerPermission').html("")
                    $('#customerPermission').append("授权客户失败.")
                    $('#customerPermission').fadeIn().fadeOut(300000);
                }
            },
            error: function(){
                console.log("customerPermission error.")
                $('#customerPermission').html("")
                $('#customerPermission').append("授权客户失败.")
                $('#customerPermission').fadeIn().fadeOut(300000);
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
        var trArr = current_row_object.parentNode.children;
        for(var trNo= 0; trNo < trArr.length; trNo++){
            if(current_row_object == current_row_object.parentNode.children[trNo]){
                current_metadata_id=document .getElementById ("table_projects").rows[trNo+1].cells[0].innerText
                current_metadata_name=document .getElementById ("table_projects").rows[trNo+1].cells[1].innerText
                $("#delete_bumen").html("");$("#delete_bumen").append(current_metadata_name)
                console.log(document .getElementById ("table_projects").rows[trNo+1].cells[0].innerText);
                console.log(document .getElementById ("table_projects").rows[trNo+1].cells[1].innerText);
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

    function getCustomeres(){
        $.ajax({
            "url": "/userpermission/all",
            data:{"metadata_id":current_metadata_id},
            type: 'post',
            async : false,
            "success": function (jsondata) {
                console.log("getCustomeres:"+jsondata)
                if(jsondata.length != 0){
                    var count=jsondata.length;
                    $(".authorization-list").html("")
                    for(var i=0;i<count;i++){

                        $(".authorization-list").append("<label><input type='checkbox'"+" id='"+ jsondata[i]["user_id"]+"' name='"+jsondata[i]["user_permission_id"]+"'>"+ jsondata[i]["user_name"]+"</label>")

                        if(jsondata[i]["hasPermission"]==1){
                            $(".authorization-list input:checkbox:last").attr("checked", "checked");
                        }
                        console.log(jsondata[i]["user_name"]);
                    }

                }else {
                    $('#customerPermission').html("")
                    $('#customerPermission').append("未添加客户")
                    $('#customerPermission').fadeIn().fadeOut(30000);
                }
            },
            error: function(){
                console.log("customerPermission error.")
                $('#customerPermission').html("")
                $('#customerPermission').append("获取客户列表失败.")
                $('#customerPermission').fadeIn().fadeOut(30000);
            }
        });
    }

    function updateSearchCondition(){
        var i=1
        var searchConditionObj = {};
        searchConditionObj["metadata_id"]=current_metadata_id
        $("#select_condition>label").each(function(index){
            console.log($(this).prop("id"));
            console.log($(this)[0].innerText);
            searchConditionObj["field"+i]=$(this).prop("id")
            searchConditionObj["field"+i+"_title"]=$.trim($(this)[0].innerText)
            searchConditionObj["field"+i+"_type"]=1
            i++
        });

        $("#input_condition>label").each(function(index){
            console.log($(this).prop("id"));
            console.log($(this)[0].innerText);
            searchConditionObj["field"+i]=$(this).prop("id")
            searchConditionObj["field"+i+"_title"]=$.trim($(this)[0].innerText)
            searchConditionObj["field"+i+"_type"]=2
            i++
        });

        $.ajax({
            "url": "/search_condition/updateinsert",
            data:searchConditionObj,
            async : true,
            "success": function (jsondata) {
                console.log(jsondata)
                if(jsondata["status"]=="success"){
                    window.location.reload();
                }else {
                    $('#maxSearchContent').html("")
                    $('#maxSearchContent').append("添加搜索项失败.")
                    $('#maxSearchContent').fadeIn().fadeOut(3000);
                }
            },
            error: function(){
                console.log("updateSearchCondition error.")
                $('#maxSearchContent').html("")
                $('#maxSearchContent').append("添加搜索项失败.")
                $('#maxSearchContent').fadeIn().fadeOut(3000);
            }
        });

    }
    function updateProjectMetadataName() {

        $("#form-project-update-name").ajaxSubmit({
            url: '/metadata/update/name',
            type: 'post',
            async: true,
            data:{"metadata_id":current_metadata_id,"new_name":$("#project_new_name").prop("value")},
            dataType: 'json',
            success: function (jsondata) {
                console.log(jsondata)
                if(jsondata["status"]=="success"){
                    window.location.reload();
                }else {
                    $("#update_project_name_message").html("")
                    $("#update_project_name_message").append("修改名称失败.")
                }
            },
            error: function (XmlHttpRequest, textStatus, errorThrown) {
                console.log("updateProjectMetadataName error.")
                $("#update_project_name_message").html("")
                $("#update_project_name_message").append("修改名称失败.")
            }
        });

    }


    function deleteProjectMetadata() {

        $("#form-project-delete").ajaxSubmit({
            url: '/metadata/delete/'+current_metadata_id,
            type: 'post',
            async: true,
            data:{"metadata_id":current_metadata_id},
            dataType: 'json',
            success: function (jsondata) {
                console.log(jsondata)
                if(jsondata["status"]=="success"){
                    delRow();
                    window.location.reload()
                }else {
                    console.log("deleteProjectMetadata error.")
                    $('#delete_project_message').html("")
                    $('#delete_project_message').append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;删除项目失败")
                    $('#delete_project_message').fadeIn().fadeOut(30000);
                }
            },
            error: function (XmlHttpRequest, textStatus, errorThrown) {
                console.log("deleteProjectMetadata error.")
                $('#delete_project_message').html("")
                $('#delete_project_message').append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;删除项目失败")
                $('#delete_project_message').fadeIn().fadeOut(30000);
                throw new Error("deleteProjectMetadata error.");
            }
        });

    }


    function uploadProjectMetadata() {
//            var item1=$("new_project_name").prop("value");
//            var item2=$("new_project_file").prop("value");
//            if(item1==undefined||item2==undefined||item1==""||item2=="") return false;

            $("#form-project-import").ajaxSubmit({
                url: '/metadata/import',
                type: 'post',
                async: true,
                dataType: 'json',
                success: function (jsondata) {
                    console.log(jsondata)
                    if(jsondata["status"]=="success"){
                        window.location.reload();
                    }else {
                        $("#upload_project_message").html("")
                        $("#upload_project_message").append("上传文件失败.")
                    }
                },
                error: function (XmlHttpRequest, textStatus, errorThrown) {
                    console.log("uploadProjectMetadata error.")
                    $("#upload_project_message").html("")
                    $("#upload_project_message").append("上传文件失败.")
                }
            });

        }


        function uploadProjectContent() {
            $("#new_metadata_id").prop("value",current_metadata_id)
            $("#upload_content_message").html("")
            $("#upload_content_message").append("正在导入 请稍等...")
            $("#form-content-import").ajaxSubmit({
                url: '/content/import',
                type: 'post',
                async: true,
                dataType: 'json',
                success: function (jsondata) {
                    console.log(jsondata)
                    if(jsondata["status"]=="success"){
                        window.location.reload();
                    }else {
                        $("#upload_content_message").html("")
                        $("#upload_content_message").append("CSV文件字段不匹配.")
                    }
                },
                error: function (XmlHttpRequest, textStatus, errorThrown) {
                    console.log("uploadProjectContent error.")
                    $("#upload_content_message").html("")
                    $("#upload_content_message").append("CSV文件字段不匹配.")
                }
            });

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
                    columnsNameList["f"+i+"_title"]=jsondata["f"+i+"_title"]
                }
            },
            error: function(){
                window.location.reload();
            }
        });
        return columnsNameList;
    }

    function setAllSearchName(metadata_id){
        var searchNameList={};
        $.ajax({
            "url": "/search_condition/"+metadata_id,
            async : false,
            "success": function (jsondata) {
                $("#input_condition").children('label').remove();
                $("#select_condition").children('label').remove();
                if(jsondata==""){

                }else {
                    var count = columsName["length"]
                    for(var j=1;j<=5;j++){
                        var item1=" "+jsondata["field"+j+"_title"];
//                        console.log(jsondata["field"+j+"_title"])
                        if(jsondata["field"+j]!=null&&jsondata["field"+j+"_title"]!=null&&jsondata["field"+j+"_type"]==1){

                            for (var i = 1; i <= count; i++) {
                                var item2=$("#f"+i)[0].innerText;

                                if(item1==item2){
                                    console.log("innerText:"+$("#f"+i)[0].innerText);console.log("title:"+jsondata["field"+j+"_title"]);
                                    $("#f"+i).addClass("select")
//                                    $("#f"+i)[0].addClass("disable")

                                }
                            }
                            var searchContent1 = $('.search-content-list label.select').not(".disable");
                            $('.select-wrap').append(searchContent1.clone());
                            $('.select-wrap>label').removeClass('select');
                            searchContent1.addClass('disable');
                        }
                        if(jsondata["field"+j]!=null&&jsondata["field"+j+"_title"]!=null&&jsondata["field"+j+"_type"]==2){
                            for (var i = 1; i <= count; i++) {
                                var item2=$("#f"+i)[0].innerText;

                                if(item1==item2){
                                    console.log("innerText:"+$("#f"+i)[0].innerText);console.log("title:"+jsondata["field"+j+"_title"]);
                                    $("#f"+i).addClass("select")
//                                    $("#f"+i)[0].addClass("disable")

                                }
                            }
                            var searchContent = $('.search-content-list label.select').not(".disable");
                            $('.text-wrap').append(searchContent.clone());
                            searchContent.addClass('disable');
                            $('.text-wrap>label').removeClass('select');
                        }

                    }


                }

            },
            error: function(){
                window.location.reload();
            }
        });
        return searchNameList;
    }

        $('#searchModal').on('show.bs.modal', function (e) {
            columsName = getAllColumnsName(current_metadata_id)
            $("#div_search_condition").html("")
            var count = columsName["length"]
            for (var i = 1; i <= count; i++) {
                $("#div_search_condition").append("<label class='btn btn-default btn-sm' id='f"+i+"'> " + columsName["f"+i+"_title"] + "</label>")
            }

            setAllSearchName(current_metadata_id)

            $('.search-content-list').on('click','label',function(){

                $(this).toggleClass('select')
                var searchContentLength = $('.search-content-list label.select').length;
                if(searchContentLength>5){
                    $(this).removeClass('select');
                    $('#maxSearchContent').html("")
                    $('#maxSearchContent').append("最多选5个")
                    $('#maxSearchContent').fadeIn().fadeOut(3000);
                }
            })


            $('#addToSelect').click(function(){
    //            var searchContent = $('.search-content-list label.select').filter(':not(".disable")');
                var searchContent1 = $('.search-content-list label.select').not(".disable");
                $('.select-wrap').append(searchContent1.clone());
                $('.select-wrap>label').removeClass('select');
                searchContent1.addClass('disable');
            })

            $('.select-wrap').on("click",'label',function(){
                $(this).toggleClass('select');
            })
            $('#removeFromSelect').click(function(){
                var selectedContent = $('.select-wrap label.select');
                var selectedContentText = $('.select-wrap label.select').text();
                var searchContentText = $('.search-content-list label.disable').text();
                selectedContent.remove();
                var selectedArray = selectedContentText.split(" ");
                var searchArray = searchContentText.split(" ");
                $.each(searchArray, function(key, searchVal){
                    $.each(selectedArray, function(key1, selectedVal){
                        if(selectedVal == searchVal && searchVal != ''){
    //                        alert(selectedVal);
                            $('.search-content-list label:contains('+selectedVal+')').removeClass('select');
                            $('.search-content-list label:contains('+selectedVal+')').removeClass('disable');
    //                        $('#'+selectedVal).removeClass('disable');

                        }
                    });
                });
            })

            $('.text-wrap').on("click",'label',function(){
                $(this).toggleClass('select');
            })
            $('#addToText').click(function(){
                var searchContent = $('.search-content-list label.select').not(".disable");
                $('.text-wrap').append(searchContent.clone());
                searchContent.addClass('disable');
                $('.text-wrap>label').removeClass('select');
            })

            $('#removeFromText').click(function(){

                var selectedContent = $('.text-wrap label.select');
                var selectedContentText = $('.text-wrap label.select').text();
                var searchContentText = $('.search-content-list label.disable').text();
                selectedContent.remove();
                var selectedArray = selectedContentText.split(" ");
                var searchArray = searchContentText.split(" ");
                $.each(searchArray, function(key, searchVal){
                    $.each(selectedArray, function(key1, selectedVal){
                        if(selectedVal == searchVal && searchVal != ''){
                            $('.search-content-list label:contains('+selectedVal+')').removeClass('disable select');
                        }
                    });
                });
            })




    })

    $('.search-content-list').slimScroll({
        height: '600px'
    });

</script>
</body>
</html>
