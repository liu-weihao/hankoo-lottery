<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>中奖用户</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="format-detection" content="telephone=no">

    <link rel="stylesheet" href="/plugins/layui/css/layui.css">
    <link rel="stylesheet" type="text/css" href="/css/index/globle.css">
    <link rel="stylesheet" type="text/css" href="/css/index/admin.css">
</head>

<body>
<div class="layui-inline">
    <form id="form" class="layui-form" style="margin: 8px;">
        <div class="layui-inline">
            <input class="layui-input" name="name" placeholder="姓名" autocomplete="off" value="">
        </div>
        <div class="layui-inline">
            <div class="layui-input-inline">
                <select name="prizeId">
                    <option value="">全部</option>
                    <option value="0">特等奖</option>
                    <option value="1">一等奖</option>
                    <option value="2">二等奖</option>
                    <option value="3">三等奖</option>
                    <option value="4">四等奖</option>
                    <option value="6">阳光普照奖</option>
                </select>
            </div>
        </div>
    </form>
</div>
<div class="layui-inline">
    <button id="search" class="layui-btn" onclick="refresh();">搜索</button>
</div>
<table id="grid"></table>
<script src="/plugins/jquery/jquery-3.1.1.min.js"></script>
<script src="/plugins/layui/layui.js"></script>
<script src="/plugins/layui/extends/laygrid.js"></script>
<script src="/js/common.js"></script>
<script type="text/javascript">
    var table;
    layui.use(['table', 'form'], function () {
        table = layui.table;
        laygrid({
            url: '/hankoo/lottery/prize/records.web',
            cols: [[
                {field: 'name', title: '姓名', align: 'center'},
                {field: 'prizeName', title: '奖项', align: 'center'},
                {field: 'award', title: '奖品', align: 'center'},
                {field: 'count', title: '数量', align: 'center'},
                {field: 'prizeTime', title: '时间', align: 'center'},
                {
                    field: '', title: '是否已领奖', align: 'center', templet: function (rowData) {
                        if (rowData.hasReceived) {
                            return "<a class='layui-btn layui-btn-xs layui-btn-primary' href='javascript:void(0);' onclick=\"receivePrize('" + rowData.id + "', 'false');\">已领取</a>";
                        }
                        return "<a class='layui-btn layui-btn-xs' href='javascript:void(0);' onclick=\"receivePrize('" + rowData.id + "', 'true');\">未领取</a>";
                    }
                }
            ]]
        });
    });

    function receivePrize(id, hasReceived) {
        $.ajax({
            url: '/hankoo/lottery/prize/records/receive.web',
            type: 'POST',
            data: {"id": id, "hasReceived": hasReceived},
            dataType: 'json',
            success: function (result) {
                if (result.status == SUCCESS) {
                    layer.msg('操作成功', {
                        icon: 6,
                        time: 600
                    }, function () {
                        refresh();
                    });
                } else {//请求失败
                    layer.msg(result.message, {
                        icon: 5,
                        time: 1000
                    });
                }
            }
        })
    }

    function refresh() {
        table.reload("grid", {
            where: $("#form").serializeJson()
        });
    }
</script>

</body>
</html>