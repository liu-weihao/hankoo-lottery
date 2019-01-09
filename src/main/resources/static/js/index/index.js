/*
 * @Author: Larry
 * @Date:   2016-12-15 17:20:54
 * @Last Modified by:   qinsh
 * @Last Modified time: 2016-12-24 22:06:18
 * +----------------------------------------------------------------------
 * | LarryBlogCMS [ LarryCMS网站内容管理系统 ]
 * | Copyright (c) 2016-2017 http://www.larrycms.com All rights reserved.
 * | Licensed ( http://www.larrycms.com/licenses/ )
 * | Author: qinshouwei <313492783@qq.com>
 * +----------------------------------------------------------------------
 */
'use strict';
layui.use([ 'jquery', 'layer', 'element' ], function() {
	window.jQuery = window.$ = layui.jquery;
	window.layer = layui.layer;
	var element = layui.element;
	// 监听导航点击
	element.on('nav(top)', function(elem) {
		var page = elem.attr("page");
		if($("ul[page='"+page+"']").is(":hidden")){//如果当前切换的菜单没有显示，再切换成显示状态，否则不做任何动作
			$("ul[page='"+page+"']").parent().find("ul").toggle();
		}
	});
	// larry-side-menu向左折叠
	$('.larry-side-menu').click(function() {
		var sideWidth = $('#larry-side').width();
		if (sideWidth === 200) {
			$('#larry-body').animate({
				left : '0'
			}); // admin-footer
			$('#larry-footer').animate({
				left : '0'
			});
			$('#larry-side').animate({
				width : '0'
			});
		} else {
			$('#larry-body').animate({
				left : '200px'
			});
			$('#larry-footer').animate({
				left : '200px'
			});
			$('#larry-side').animate({
				width : '200px'
			});
		}
	});
});
