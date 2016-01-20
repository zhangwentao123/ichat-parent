<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>AdminLTE 2 | Dashboard</title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <script src="<%=request.getContextPath()%>/resources/plugins/jQuery/jQuery-2.1.4.min.js"></script>

    <jsp:include page="common/headHtml.jsp"/>

    <script>
        function refreshMain(url) {
            var baseUrl = "/wechat"
            $(".content-wrapper").load(baseUrl + url, null, null);
        }
    </script>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">

    <jsp:include page="index/top.jsp"/>
    <jsp:include page="index/left.jsp"/>
    <jsp:include page="index/main.jsp"/>
    <jsp:include page="index/footer.jsp"/>
    <jsp:include page="index/sider.jsp"/>


    <!-- /.control-sidebar -->
    <!-- Add the sidebar's background. This div must be placed
         immediately after the control sidebar -->
    <div class="control-sidebar-bg"></div>
</div>
<!-- ./wrapper -->

<jsp:include page="common/plugins.jsp"/>
</body>
</html>
