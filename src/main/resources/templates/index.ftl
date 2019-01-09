<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>首页-年会抽奖后台管理系统</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="format-detection" content="telephone=no">
    <!-- load css -->
    <link rel="stylesheet" href="/plugins/layui/css/layui.css">
    <link rel="stylesheet" type="text/css" href="/css/index/globle.css">
    <link rel="stylesheet" type="text/css" href="/css/index/admin.css">
</head>
<body>
<div class="layui-layout layui-layout-admin" id="layui_layout">
    <!-- 顶部区域 -->
    <div class="layui-header header header-demo">
        <div class="layui-main">
            <!-- logo区域 -->
            <div class="admin-logo-box">
                <a class="logo" href="/index"><img src="/images/logo.jpg" alt="logo"></a>
                <div class="larry-side-menu">
                    <i class="fa fa-bars" aria-hidden="true"></i>
                </div>
            </div>
            <!-- 顶级菜单区域 -->
            <div class="layui-larry-menu">
                <ul class="layui-nav clearfix" lay-filter="top">
                    <li class="layui-nav-item layui-this" page="content">
                        <a href="javascript: void(0);"><i class="iconfont icon-wangzhanguanli"></i>内容管理</a>
                    </li>
                </ul>
            </div>
            <!-- 右侧导航 -->
            <ul class="layui-nav larry-header-item">
                <li class="layui-nav-item first">
                    <a href="javascript: void(0);">
                        <img src="/images/index/userimg.jpg" class="userimg">
                        <cite>${LOGIN_TICKET_USER.username}</cite>
                        <span class="layui-nav-more"></span>
                    </a>
                    <dl class="layui-nav-child">
                        <dd>
                            <a href="javascript:void(0);" onclick="logout();">安全退出</a>
                        </dd>
                    </dl>
                </li>
            </ul>
        </div>
    </div>
    <!-- 左侧侧边导航开始 -->
    <div class="layui-side layui-side-bg layui-larry-side" id="larry-side">
        <div class="layui-side-scroll" id="larry-nav-side" lay-filter="side">
            <div class="user-photo">
                <a class="img" title="上传头像"><img id="avatar" src='${LOGIN_TICKET_USER.avatar!"/images/index/user.jpg"}'/></a>
                <p>您好！${LOGIN_TICKET_USER.username}， 欢迎登录</p>
            </div>
            <!-- 左侧菜单 -->
            <ul class="layui-nav layui-nav-tree leftmenu" page="content">
                <li class="layui-nav-item layui-this">
                    <a href="javascript: void(0);" data-url="/">
                        <i class="iconfont" data-icon='icon-home1'></i>
                        <span>我的首页</span>
                    </a>
                </li>
                <li class="layui-nav-item">
                    <a href="javascript: void(0);">
                        <i class="iconfont"></i>
                        <span>用户管理</span>
                        <em class="layui-nav-more"></em>
                    </a>
                    <dl class="layui-nav-child">
                        <dd>
                            <a href="javascript: void(0);" data-url="/users.web?type=10">
                                <i class="iconfont" data-icon='icon-yonghu1'></i>
                                <span>活动用户</span>
                            </a>
                        </dd>
                    </dl>
                    <dl class="layui-nav-child">
                        <dd>
                            <a href="javascript: void(0);" data-url="/users.web?type=20">
                                <i class="iconfont" data-icon='icon-yonghu1'></i>
                                <span>黑名单设置</span>
                            </a>
                        </dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a href="javascript: void(0);">
                        <i class="iconfont"></i>
                        <span>抽奖管理</span>
                        <em class="layui-nav-more"></em>
                    </a>
                    <dl class="layui-nav-child">
                        <dd>
                            <a href="javascript: void(0);" data-url="/trainItem.web">
                                <i class="iconfont" data-icon='icon-yonghu1'></i>
                                <span>奖项列表</span>
                            </a>
                        </dd>
                    </dl>
                    <dl class="layui-nav-child">
                        <dd>
                            <a href="javascript: void(0);" data-url="/agencyList.web">
                                <i class="iconfont" data-icon='icon-yonghu1'></i>
                                <span>中奖记录</span>
                            </a>
                        </dd>
                    </dl>
                </li>
            </ul>
        </div>
    </div>
    <!-- 左侧侧边导航结束 -->

    <!-- 右侧主体内容 -->
    <div class="layui-body" id="larry-body" style="bottom: 0; border-left: solid 2px #1AA094;">
        <div class="layui-tab layui-tab-card larry-tab-box" id="larry-tab" lay-filter="index">
            <ul class="layui-tab-title">
                <li class="layui-this" id="admin-home"><i class="iconfont icon-diannao1"></i><em>我的首页</em></li>
            </ul>
            <div class="layui-tab-content" style="min-height: 150px; ">
                <div class="layui-tab-item layui-show">
                    <iframe class="larry-iframe" data-id='0' src="/welcome"></iframe>
                </div>
            </div>
        </div>
    </div>

    <!-- 底部区域 -->
    <div class="layui-footer layui-larry-foot" id="larry-footer">
        <div class="layui-mian">
            <p class="p-admin">
                <span>Copyright&copy; 2018 - 2020 </span>
                <a target="_blank" href="https://zhuanlan.zhihu.com/wen8hao">知乎@文刀</a>. 版权所有
            </p>
        </div>
    </div>
</div>
<script src="/plugins/jquery/jquery-3.1.1.min.js"></script>
<script src="/plugins/layui/layui.js"></script>
<script src="/plugins/layui/extends/uploader.js"></script>
<script type="text/javascript" src="/js/index/larry.js"></script>
<script type="text/javascript" src="/js/index/index.js"></script>
<script src="/js/common.js"></script>
<script type="text/javascript">
    var element;
    layui.use([ 'jquery', 'layer', 'element' ], function() {
        element = layui.element;
    });

    function logout() {
        $.ajax({
            url: '/hankoo/user/logout.do',
            type: 'POST',
            dataType: 'json',
            success: function () {
                window.location.href = '/login.html';
            }
        });
    }
</script>
</body>
</html>
