<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>奖项列表</title>
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
<table id="grid"></table>
<script src="/plugins/jquery/jquery-3.1.1.min.js"></script>
<script src="/plugins/layui/layui.js"></script>
<script src="/plugins/layui/extends/laygrid.js"></script>
<script src="/js/common.js"></script>
<script type="text/javascript">
    var table;
    layui.use(['table', 'form'], function () {
        table = layui.table;
        getStatistics();
        laygrid({
            url: '/hankoo/lottery/prizes.web',
            height: 'full-20',
            cols: [[
                {field: 'name', title: '奖项', align: 'center'},
                {field: 'award', title: '奖品名称', align: 'center'},
                {field: 'count', title: '奖品数量', align: 'center'},
                {
                    field: '', title: '是否已结束', align: 'center', templet: function (rowData) {
                    if (rowData.isOver) {
                        return "<span style='color: red'>已结束</span>";
                    }
                    return '<i><未结束></i>'
                }
                }
            ]]
        });
    });

    function removeBlackParticipant(id) {
        if (window.confirm("您确认要从黑名单中移除这名参与者吗？")) {
            $.ajax({
                url: '/hankoo/user/participants/black.web',
                type: 'DELETE',
                data: {"id": id},
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
    }

    function getStatistics() {
        $.ajax({
            url: '/hankoo/user/participants/black/statistics.web',
            type: 'GET',
            dataType: 'json',
            success: function (result) {
                if (result.status == SUCCESS) {
                    var form = $("#statistics_form");
                    var arr = [];
                    var statistics = result.body;
                    for (k in statistics) {
                        arr.push('<div class="layui-inline">' +
                                '    <label class="layui-form-label">' + k +'</label>' +
                                '    <div class="layui-input-inline">' +
                                '        <label id="total" class="layui-form-label">' + statistics[k] + '人</label>' +
                                '    </div>' +
                                '</div>');
                    }
                    form.append(arr.join(''));
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