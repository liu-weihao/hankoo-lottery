/** 系统出现异常 */
var ERROR = 500;
/** 请求超时 */
var TIMEOUT = 408;
/** 找不到页面 */
var NOT_FOUND = 404;
/** 无效的请求 */
var BAD_REQUEST = 400;
/** 登录过期 */
var LOGIN_EXPIRE = 401;
/** 无权限访问 */
var NO_AUTHORITY = 405;
/** 必填的参数没有传入 */
var PARAM_BLANK = 300;
/** 用户未登录，访问了需要登录的api */
var NOT_LOGIN = 301;
/** 业务处理未满足条件，导致失败 */
var FAILED = 100;
/** 正常情况 */
var SUCCESS = 200;
/** 提示框 */
var layer;
layui.use(['layer'], function () {
    var layer = layui.layer;
    /** 全局ajax异常处理 */
    $.ajaxSetup({
        type: "post",
        error: function (jqXHR, textStatus, errorThrown) {
            layer.closeAll("loading");
            switch (jqXHR.status) {
                case (ERROR):
                    layer.msg("服务器系统内部错误");
                    break;
                case (BAD_REQUEST):
                    layer.msg("无效的请求");
                    break;
                case (LOGIN_EXPIRE):
                    layer.msg("登录过期，请重新登录", function () {
                        window.top.location = getBasePath() + "/login.html";
                    });
                    break;
                case (NO_AUTHORITY):
                    layer.msg("无权限执行此操作");
                    break;
                case (NOT_FOUND):
                    layer.msg("您请求的页面未找到");
                    break;
                case (TIMEOUT):
                    layer.msg("请求超时");
                    break;
                default:
                    break;
            }
        }
    });
});

/**
 * form表单验证公共方法
 * formId：待验证表单id
 * submitFun：验证成功后表单数据提交函数，回传form对象
 * $(function(){
 * 		formValid('userForm', submitForm);
 * });
 * @return 验证对象validate
 */
function formValid(formId, submitFun) {
    return $("#" + formId).validate({
        errorPlacement: function (error, element) {
            if (error && error.text().length > 0) {
                element.parent().append(error.addClass("wrong"));
            }
        },
        errorElement: "span",
        ignore: ".ignore", //不需要验证的标签，请加上class="ignore"，建议input:file标签加上，暂不支持此标签验证
        submitHandler: function (form) {
            if (typeof submitFun == 'function') {
                submitFun($(form));
            } else {
                form.submit();
            }
        }
    });
}
/**
 * JS中获取URL地址根路径
 * @returns {String}
 */
function getBasePath() {
    //获取当前网址，如： http://localhost:8083/uimcardprj/share/meun.jsp
    var curWwwPath = window.document.location.href;    //获取主机地址之后的目录，如： uimcardprj/share/meun.jsp
    var pathName = window.document.location.pathname;
    var pos = curWwwPath.indexOf(pathName);    //获取主机地址，如： http://localhost:8083
    var localhostPath = curWwwPath.substring(0, pos);    //获取带"/"的项目名，如：/uimcardprj
    var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
    return localhostPath + projectName + '/';
}

/**
 * 日期格式化
 * @param fmt
 * @returns {*}
 */
Date.prototype.format = function (fmt) {
    var o = {
        "M+": this.getMonth() + 1, //月份
        "D+": this.getDate(), //日
        "d+": this.getDate(), //日
        "H+": this.getHours(), //小时
        "h+": this.getHours(), //小时
        "m+": this.getMinutes(), //分
        "s+": this.getSeconds(), //秒
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度
        "S": this.getMilliseconds() //毫秒
    };
    if (/([Y,y]+)/.test(fmt)) {
        fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    }
    for (var k in o) {
        if (new RegExp("(" + k + ")").test(fmt)) {
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
        }
    }
    return fmt;
};

/**
 * 日期截取
 */
function dateFormatYMD(data) {
    if (!data || data.length === 0) {
        return "";
    } else {
        return data.substring(0, 10);
    }
}


/**
 * layer关闭iframe窗口(子页面调用)
 * @param refresh 是否刷新父级页面，true 需要刷新，那么需要在父级页面实现refresh(index)方法
 */
function close(refresh) {
    var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
    parent.layer.close(index); //再执行关闭
    if (refresh && parent.refresh && typeof parent.refresh == "function") {
        parent.refresh(index);//调用父级页面的刷新方法，回传子窗口的索引号
    }
}

(function ($) {
    /**
     * 将form表单序列化成一个JSON对象
     */
    $.fn.serializeJson = function () {
        var serializeObj = {};
        var array = this.serializeArray();
        $(array).each(function () {
            if (serializeObj[this.name]) {
                if ($.isArray(serializeObj[this.name])) {
                    serializeObj[this.name].push(this.value);
                } else {
                    serializeObj[this.name] = [serializeObj[this.name], this.value];
                }
            } else {
                serializeObj[this.name] = this.value;
            }
        });
        return serializeObj;
    };

})(jQuery);
