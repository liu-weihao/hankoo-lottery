<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>活动参与者</title>
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
<form id="statistics_form" class="layui-form layui-form-pane" style="margin: 8px;">
    <div class="layui-inline">
        <label class="layui-form-label">参与人数</label>
        <div class="layui-input-inline">
            <label id="total" class="layui-form-label">-</label>
        </div>
    </div>
    <div class="layui-inline">
        <label class="layui-form-label">中奖人数</label>
        <div class="layui-input-inline">
            <label id="winnerCount" class="layui-form-label">-</label>
        </div>
    </div>
</form>
<div class="layui-inline">
    <form id="form" class="layui-form" style="margin: 8px;">
        <div class="layui-inline">
            <input class="layui-input" name="name" placeholder="姓名" autocomplete="off" value="">
        </div>
        <div class="layui-inline">
            <div class="layui-input-inline">
                <select name="isWinner">
                    <option value="">全部</option>
                    <option value="1">已中奖</option>
                    <option value="0">未中奖</option>
                </select>
            </div>
        </div>
    </form>
</div>
<div class="layui-inline">
    <button id="search" class="layui-btn" onclick="refresh();">搜索</button>
</div>
<#--
<div class="layui-inline" style="margin: 10px;">
    <button class="layui-btn" onclick="saveQz();">新增</button>
</div>
-->
<table id="grid"></table>
<script src="/plugins/jquery/jquery-3.1.1.min.js"></script>
<script src="/plugins/layui/layui.js"></script>
<script src="/plugins/layui/extends/laygrid.js"></script>
<script src="/js/common.js"></script>
<script type="text/javascript">
    var table;
    layui.use(['table', 'form'], function () {
        table = layui.table;
        getParticipantStatistics();
        laygrid({
            url: '/hankoo/user/participants.web',
            height: "full-132",
            cols: [[
                {field: 'name', title: '姓名', align: 'center'},
                {field: 'info', title: '附加信息', align: 'center'},
                {
                    field: '', title: '是否中奖', align: 'center', templet: function (rowData) {
                        if (rowData.isWinner) {
                            return "<span style='color: red'>是</span>";
                        }
                        return '<i><无></i>'
                    }
                },
                {
                    field: '', title: '操作', align: 'center', templet: function (rowData) {
                        return "<a class='layui-btn layui-btn-xs' href='javascript:void(0);' onclick=\"removeParticipant('" + rowData.id + "');\">删除</a>";
                    }
                }
            ]]
        });
    });

    function getParticipantStatistics() {
        $.ajax({
            url: '/hankoo/user/participants/statistics.web',
            type: 'GET',
            dataType: 'json',
            success: function (result) {
                if (result.status == SUCCESS) {
                    $("#total").html(result.body.total);
                    $("#winnerCount").html(result.body.winnerCount);
                }
            }
        })
    }

    function removeParticipant(id) {
        $.ajax({
            url: '/hankoo/user/participants.web',
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

    function refresh() {
        table.reload("grid", {
            where: $("#form").serializeJson()
        });
    }
</script>

</body>
</html>