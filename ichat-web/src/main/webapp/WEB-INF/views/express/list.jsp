<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>IMF</title>
    <jsp:include page="../common/headHtml.jsp"/>
    <script src="//cdn.bootcss.com/jquery/2.1.4/jquery.js"></script>
    <script>
        function refreshData(){
            $.getJSON("/wechat/express/count", null, function(json){
                if(json.length > 0) {
                    $("#count").html(json["count"]);
                } else {
                    $("#count").html("Null");
                }
            })
        }

        $(function(){
            refreshData();
            setInterval(refreshData, 5000);
        });
        $(document).ready(function(){
            $("#count").html(12);
            $.ajax({
                type: "POST",
                url: "/wechat/express/count",
                dataType: "json",
                data: {
                  name: "",
                    sex: ""
                },
                success: function(data){
                    $("#count").html(data["count"]);
                },
                error: function(jqXHR){
                    $("#count").html("error:" + jqXHR.status);
                }
            });
        });
    </script>
</head>
<body>
<section class="content-header">
    <h1>
        Data Tables
        <small>advanced tables</small>
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
        <li><a href="#">Tables</a></li>
        <li class="active">Data tables</li>
    </ol>
</section>

<section class="content">
    <div class="row">
        <div class="col-xs-12">
            <div class="box">
                <div class="box-header">
                    <h3 class="box-title">Data Table With Full Features
                        <small id="count" class="label pull-right bg-green">new</small>
                    </h3>
                    <a href="/wechat/express/addPre" class="pull-right">新增单号</a>
                </div>
                <!-- /.box-header -->
                <div class="box-body">
                    <table id="example" class="table table-bordered table-striped">
                        <thead>
                        <tr>
                            <th>序号</th>
                            <th>快递单号</th>
                            <th>快递公司</th>
                            <th>状态</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${expressNumList}" var="row" varStatus="status">
                            <tr>
                                <td>${status.index + 1 }</td>
                                <td><a href="/express/json/${row.no}">${row.no}</a></td>
                                <td>${row.company}</td>
                                <td>${row.status}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
                <!-- /.box-body -->
            </div>
            <!-- /.box -->
        </div>
        <!-- /.col -->
    </div>
    <!-- /.row -->
</section>
<!-- /.content -->


<jsp:include page="../common/plugins.jsp"/>
<!-- page script -->
<script>
    $(function () {
        $("#example").DataTable();
        /**$('#example2').DataTable({
            "paging": true,
            "lengthChange": false,
            "searching": false,
            "ordering": true,
            "info": true,
            "autoWidth": false
        });*/
    });
</script>

</body>
</html>
