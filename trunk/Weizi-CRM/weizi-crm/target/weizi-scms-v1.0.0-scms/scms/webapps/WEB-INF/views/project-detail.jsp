<!doctype html>
<html>
<%@ include file="header.jsp" %>
<body>
<div class="main-container clearfix">
        <section class="m20">
            <!-- Nav tabs -->
            <ul class="nav nav-tabs" role="tablist">
                <li role="presentation" class="active"><a href="#information" role="tab" data-toggle="tab">基本信息</a></li>
                <li role="presentation"><a href="#headImg" role="tab" data-toggle="tab">门店图片</a></li>
                <%--<li role="presentation"><a href="#soundFile" role="tab" data-toggle="tab" >声音文件</a></li>--%>
                <%--<li role="presentation"><a href="#videoFile" role="tab" data-toggle="tab" >视频文件</a></li>--%>
            </ul>
            <!-- Tab panes -->
            <div class="tab-content">
                <div role="tabpanel" class="tab-pane active" id="information">
                    <div class="panel panel-info">
                        <div id="project_current_name" class="panel-heading">
                        </div>
                        <!-- Table -->
                        <table cellspacing="0" cellpadding="0" border="0" id="project_content_table" class="table table-hover table-bordered ">

                        </table>
                    </div>

                    <div class="mt10 row">
                        <div class="col-md-6">
                            <div class="panel panel-primary">
                                <div class="panel-heading">申诉反馈</div>
                                <div class="panel-body">
                                    <form id="form-appeal-image" class="form-horizontal">
                                        <input id="store_id" type="text" name="store" value="" hidden/>
                                        <input id="metadata_id" type="text" name="metadata_id" value="" hidden/>
                                        <div class="form-group">
                                            <label class="control-label col-md-2 ">申诉内容<span class="red">&nbsp;*<small>必填</small></span></label>
                                            <div class="col-md-10">
                                                <textarea name="appeal_content" class="form-control" placeholder="申诉内容" style="resize: none" rows="4"></textarea>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label col-md-2">申诉图片</label>
                                            <div class="col-md-10 upload-file">
                                                <input name="image_upload" id="file-3" type="file" multiple=true accept="image/*">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label col-md-2"></label>
                                            <div class="col-md-10">
                                                <button type="button" class="btn btn-primary" onclick="uploadAppealImage();">提交反馈</button>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>


                        <div class="col-md-6">
                            <div class="panel panel-primary">
                                <div class="panel-heading">申诉反馈记录</div>
                                <table cellspacing="0" cellpadding="0" border="0" id="appealTable" class="table table-hover table-bordered">
                                    <thead>
                                    <tr role="row">
                                        <th>申诉内容</th>
                                        <th>申诉图片</th>
                                        <th>申诉时间</th>
                                        <th>反馈处理状态</th>
                                        <th>申诉者</th>
                                    </tr>
                                    </thead>
                                </table>
                            </div>
                        </div>
                    </div>

                </div>


                <div role="tabpanel" class="tab-pane" id="headImg">
                    <div id="store_images" class="row">

                    </div>
                </div>


                <div role="tabpanel" class="tab-pane" id="soundFile">
                    <table cellspacing="0" cellpadding="0" border="0"  class="table table-hover table-bordered ">
                        <thead>
                        <tr role="row">
                            <th>文件名称</th>
                            <th>文件大小</th>
                            <th>上传时间</th>
                            <%--<th>上传者</th>--%>
                        </tr>
                        </thead>
                        <tbody role="alert" aria-live="polite" aria-relevant="all">
                        <c:forEach items="${list_field5}" var="item">
                            <tr>
                                <td><a href="${item.file_path}" target="_blank">${item.file_name}</a></td>
                                <td>${item.file_size}M</td>
                                <td>${item.file_time}</td>
                                <%--<td>${item.file_author}</td>--%>
                            </tr>
                        </c:forEach>
                        <c:if test="${list_field5 eq null}">
                            <tr>
                                <td>暂时没有文件上传</td>
                                <td></td>
                                <td></td>
                                <%--<td></td>--%>
                            </tr>
                        </c:if>
                        </tbody>
                    </table>
                </div>
                <div role="tabpanel" class="tab-pane" id="videoFile">
                    <table cellspacing="0" cellpadding="0" border="0"  class="table table-hover table-bordered ">
                        <thead>
                        <tr role="row">
                            <th>文件名称</th>
                            <th>文件大小</th>
                            <th>上传时间</th>
                            <%--<th>上传者</th>--%>
                        </tr>
                        </thead>
                        <tbody role="alert" aria-live="polite" aria-relevant="all">
                        <tbody role="alert" aria-live="polite" aria-relevant="all">
                        <c:forEach items="${list_field6}" var="item">
                            <tr>
                                <td><a href="${item.file_path}" target="_blank">${item.file_name}</a></td>
                                <td>${item.file_size}M</td>
                                <td>${item.file_time}</td>
                                <%--<td>${item.file_author}</td>--%>
                            </tr>
                        </c:forEach>
                        <c:if test="${list_field6 eq null}">
                            <tr>
                                <td>暂时没有文件上传</td>
                                <td></td>
                                <td></td>
                                <%--<td></td>--%>
                            </tr>
                        </c:if>
                        </tbody>
                    </table>
                </div>
            </div>
        </section>
    </div><!--end right-side-->
</div><!--end main-container-->


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
    var searchCondition={};
    var current_row_object=null;
    var current_store=""


    function deleteProjectContent() {

        $("#form-content-delete").ajaxSubmit({
            url: '/content/delete/'+${content_id},
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
        var trArr = current_row_object.parentNode.children;
        for(var trNo= 0; trNo < trArr.length; trNo++){
            if(current_row_object == current_row_object.parentNode.children[trNo]){
                current_store=document .getElementById ("project_content_table").rows[trNo+1].cells[3].innerText
                $(".text-danger").html("");$(".text-danger").append(current_store)

            }
        }
        console.log("current_store:"+current_store);
        console.log("current_metadata_id:"+current_metadata_id);
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


    $(function () {

        function ObjColumn(id, name) {
            this.data = id;
            this.title = name;
            this.bSortable = false;
        }

        function getAllColumnsName(){
            var columnsNameList={};
            $.ajax({
                "url": "/metadata/content",
                "data":{"content_id":"${content_id}"},
                type: "POST",
                async : false,
                "success": function (jsondata) {
                    if(jsondata==""){
//                        window.location.href="/";
                    }
                    current_metadata_id=jsondata["id"]
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


        function createTable(columns) {
            $("#project_content_table").DataTable({
                "sort":false,
                ajax: {
                    url: "/content/id/"+${content_id},
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
                        return "<div aria-label='Small button group' role='group' class='hidden btn-group btn-group-sm'>"
                                +"<button class='btn btn-default' type='button' data-toggle='modal' data-target='#Deleteuser'  onclick='getRowNo(this)' disabled='true'>"
                                +"<span class='glyphicon glyphicon-trash'></span>删除</button>"
                                +"</div>"
                    }},
                    {
                        "targets":[3], "data":"f3", "render":function(data, type, full){
                                    current_store=data
                        console.log("current_store:"+current_store)

                                    return data;
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
                "dom": "<'row'>" + "t" + "<'row'>",
                "initComplete": function () {

                    $('#appealTable').DataTable( {
                        "sort":false,
                        ajax: {
                            url: "/appeal/store/"+current_store,
                            async : true,
                            type: "GET",
                            dataType:"json"
                        },
                        //列的配置信息
                        "columns": {"content":"申诉内容","images":"申诉图片","created":"申诉时间","status":"反馈处理状态","author":"申诉者"},
                        "columnDefs":[
                            {"targets":[0], "data":"content", "render":function(data, type, full){
                                console.log(data)
                                return data
                            }},
                            {
                                "targets":[1], "data":"images", "render":function(data, type, full){
                                var ret_href=""
                                var count=data.length;
                                for(var i=0;i<count;i++){
                                    ret_href=ret_href+"<a href='"+data[i]+"'  target='_blank'>"+"&nbsp;&nbsp;图片"+(i+1)+"&nbsp;&nbsp;</a>"
                                }
                                console.log(data)
                                return ret_href
                            }},
                            {
                                "targets":[2], "data":"created", "render":function(data, type, full){
                                console.log(data)
                                return data
                            }},
                            {
                                "targets":[3], "data":"status", "render":function(data, type, full){
                                console.log(data)
                                return data
                            }},
                            {
                                "targets":[4], "data":"author", "render":function(data, type, full){
                                console.log(data)
                                return data
                            }}
                        ],
                        "search":false,
                        "language": {
                            "processing":"查询中......",
                            "loadingRecords":"正在加载记录中,请稍等......",
                            "lengthMenu": "每页_MENU_ 条记录",
                            "zeroRecords": "没有符合条件记录",
                            "info": "&nbsp;&nbsp;&nbsp;&nbsp;申诉反馈总数：_TOTAL_ ",
                            "infoEmpty": "目前项目中没有任何记录",
                            "search": "搜索：",
                            "infoFiltered": "(从 _MAX_ 条记录过滤)",
                            "paginate": {
                                "previous": "上一页",
                                "next": "下一页"
                            }
                        },

                        "dom": "<'row'>" + "t" + "<'row'<'col-xs-6'i><'col-xs-6'p>>",
                        "initComplete": function () {
                            console.log("current_store is ;["+current_store+"]");
                            getStoreInformation();
                        }
                    } );
                }


            });


        }

        onLoadPageProject();

        function onLoadPageProject() {
            columsName = getAllColumnsName();
            console.log(columsName);

            var number = columsName["length"];
            columns=[];
            columns.push(new ObjColumn("id",""));
            for (var i = 1; i <= number; i++) {
                var item = new ObjColumn("f" + i, columsName["f" + i]);
                columns.push(item);
            }

            table_project = createTable(columns);

        }


        function getStoreInformation(){
            $.ajax({
                "url": "/content/ftp/store/"+current_store,
                type: 'get',
                data:{"metadata_id":current_metadata_id},
                async : true,
                "success": function (jsondata) {
                    if(jsondata!=""){
                        console.log("getStoreInformation:"+jsondata)
                        var images=jsondata["field1"]
                        var count=images.length
                        $("#store_images").html("")
                        for(var i=0;i<count;i++) {
                            var decode_url=decodeURI(images[i])
                            console.log("decode_url: "+decode_url)
                            /*if (decode_url.indexOf(" ")==-1&&decode_url.indexOf("(")==-1&&decode_url.indexOf(")")==-1) {
                                $("#store_images").append("<div class='col-md-3'><div class='head-img'><a target='_blank' href='"+decode_url+"'><img src='" +decode_url+"_211x282.jpg'></a></div></div>")
                            } else {
                                $("#store_images").append("<div class='col-md-3'><div class='head-img'><a target='_blank' href='"+decode_url+"'><img src='" +decode_url+"'></a></div></div>")
                            }*/
                            $("#store_images").append("<div class='col-md-3'><div class='head-img'><a target='_blank' href='"+decode_url+"'><img src='" +decode_url+"_211x282.jpg'></a></div></div>")
                            }
                    }
                },
                error: function(){

                }
            });

        }

    });

    //申述
    function uploadAppealImage() {

        $("#store_id").prop("value",current_store);
        $("#metadata_id").prop("value",current_metadata_id);
        var content=$("#file-3").prop("value");
        if(content != ""){
            $("#form-appeal-image").ajaxSubmit({
                url: '/content/appeal/image',
                type: 'post',
                async: true,
                dataType: 'json',
                contentType: 'multipart/form-data',
                success: function (jsondata) {
                    console.log(jsondata)
                    if(jsondata["status"]=="success"){
                        window.location.reload();
                    }else {

                    }
                },
                error: function (XmlHttpRequest, textStatus, errorThrown) {
                    console.log("uploadAppealImage error.")

                }
            });
        }else {
            $("#form-appeal-image").ajaxSubmit({
                url: '/content/appeal/appeal',
                type: 'post',
                async: true,
                dataType: 'json',
                success: function (jsondata) {
                    console.log(jsondata)
                    if(jsondata["status"]=="success"){
                        window.location.reload();
                    }else {

                    }
                },
                error: function (XmlHttpRequest, textStatus, errorThrown) {
                    console.log("uploadAppealImage error.")

                }
            });
        }
    }



    $(document).ready(function() {



        $("#file-3").fileinput({
            showUpload: false,
            showCaption: false,
            showClose: false,
//            showAction: false,
            browseClass: "btn btn-info btn-sm",
            fileType: "any",
            previewFileIcon: "<i class='glyphicon glyphicon-king'></i>"
        });
    } );
</script>
</body>
</html>
