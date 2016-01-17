<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>IMF</title>
    <jsp:include page="../common/headHtml.jsp"/>
</head>
<body>

<div class="col-md-9">
    <!-- Horizontal Form -->
    <div class="box box-info">
        <div class="box-header with-border">
            <h3 class="box-title">快递单新增</h3>
        </div>
        <!-- /.box-header -->
        <!-- form start -->
        <form class="form-horizontal" action="<%=request.getContextPath()%>/express/add" method="post">
            <div class="box-body">
                <div class="form-group">
                    <label for="company" class="col-sm-2 control-label">快递公司</label>

                    <div class="col-sm-10">
                        <select class="form-control" id="company" name="company">
                            <option>顺丰</option>
                            <option>圆通</option>
                            <option>申通</option>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label for="startNo" class="col-sm-2 control-label">起始单号</label>

                    <div class="col-sm-10">
                        <input class="form-control" id="startNo" name="startNo" placeholder="Start Number" value="10000">
                    </div>
                </div>
                <div class="form-group">
                    <label for="count" class="col-sm-2 control-label">总数</label>

                    <div class="col-sm-10">
                        <input type="number" class="form-control" id="count" name="count" placeholder="Count" value="10">
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
