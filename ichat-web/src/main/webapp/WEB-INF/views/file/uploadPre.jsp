<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>TheBo</title>
    <jsp:include page="../common/headHtml.jsp"/>
</head>
<body>

<div class="col-md-9">
    <!-- Horizontal Form -->
    <div class="box box-info">
        <div class="box-header with-border">
            <h3 class="box-title">文件上传</h3>
        </div>
        <!-- /.box-header -->
        <!-- form start -->
        <form class="form-horizontal" action="<%=request.getContextPath()%>/file/upload" method="post" enctype="multipart/form-data">
            <div class="box-body">
                <div class="form-group">
                    <label for="file" class="col-sm-2 control-label">上传附件</label>

                    <div class="col-sm-10">
                        <input type="file" id="file" name="file">
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <div class="checkbox">
                            <label>
                                <input type="checkbox"> Remember me
                            </label>
                        </div>
                    </div>
                </div>
            </div>
            <!-- /.box-body -->
            <div class="box-footer">
                <button type="button" class="btn btn-default" onclick="history.go(-1)">Cancel</button>
                <button type="submit" class="btn btn-info pull-right">Submit</button>
            </div>
            <!-- /.box-footer -->
        </form>
    </div>
</div>
</body>
</html>
