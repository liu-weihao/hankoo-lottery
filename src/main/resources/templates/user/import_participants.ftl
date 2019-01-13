<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>导入参与者</title>
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
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 18px;">
    <legend>导入抽奖用户</legend>
</fieldset>
<form id="form" class="layui-form" style="margin: 8px;">
    <input id="overwrite" type="hidden" name="overwrite" value="false"/>
    <ul class="layui-timeline">
        <li class="layui-timeline-item">
            <i class="layui-icon layui-timeline-axis"></i>
            <div class="layui-timeline-content layui-text">
                <h3 class="layui-timeline-title"><a href="/template.xlsx" target="_blank">点击下载</a>抽奖用户模板表</h3>
            </div>
        </li>
        <li class="layui-timeline-item">
            <i class="layui-icon layui-timeline-axis"></i>
            <div class="layui-timeline-content layui-text">
                <h3 class="layui-timeline-title">填写表格，上传文件</h3>
                <div class="layui-upload-drag" id="uploader">
                    <i class="layui-icon"></i>
                    <p>点击上传，或将文件拖拽到此处</p>
                </div>
            </div>
        </li>
    </ul>
    <div class="layui-inline">
        <label class="layui-form-label">覆盖原有数据</label>
        <div class="layui-input-inline">
            <input type="checkbox" lay-skin="switch" lay-filter="overwrite"/>
        </div>
    </div>
</form>

<div class="layui-inline">
    <button id="upload_btn" class="layui-btn" style="margin: 25px;">立即导入</button>
</div>
<script src="/plugins/jquery/jquery-3.1.1.min.js"></script>
<script src="/plugins/layui/layui.js"></script>
<script src="/js/common.js"></script>
<script type="text/javascript">
    layui.use(['upload', 'form'], function () {
        var form = layui.form;
        var upload = layui.upload;
        //拖拽上传
        upload.render({
            elem: '#uploader',
            url: '/hankoo/user/participants/import.do',
            accept: 'file',
            exts: 'xlsx',
            auto: false,
            multiple: false,
            data: {
                overwrite: function () {
                    return $('#overwrite').val();
                }
            },
            bindAction: '#upload_btn',
            done: function(res){
                console.log(res)
            }
        });
        form.on('switch(overwrite)', function(data){
            $('#overwrite').val(data.elem.checked);
        });
    });
</script>

</body>
</html>