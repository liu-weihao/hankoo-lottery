/*
* @Author: Larry
* @Date:   2016-12-15 17:20:54
* @Last Modified by:   qinsh
* @Last Modified time: 2016-12-24 21:59:02
* +----------------------------------------------------------------------
* | LarryBlogCMS [ LarryCMS网站内容管理系统 ]
* | Copyright (c) 2016-2017 http://www.larrycms.com All rights reserved.
* | Licensed ( http://www.larrycms.com/licenses/ )
* | Author: qinshouwei <313492783@qq.com>
* +----------------------------------------------------------------------
*/
layui.define(['element'], function(exports){
   var  element = layui.element,
        $ = layui.jquery,
		layer = parent.layer === undefined ? layui.layer : parent.layer,
		module_name = 'navtab',
		globalTabIdIndex = 1,
		LarryTab = function(){
              this.config ={
              	  elem: undefined,
				  closed: true 
              };
		};
        var ELEM = {};
        /**
         * [参数设置 options]
         */
        LarryTab.prototype.set = function(options){
              var _this = this;
              $.extend(true, _this.config, options);
              return _this;
        };
        /**
         * [init 对象初始化]
         * @return {[type]} [返回对象初始化结果]
         */
        LarryTab.prototype.init  = function(){
             var _this = this;
             var _config = _this.config;
             if(typeof(_config.elem) !== 'string' && typeof(_config.elem) !== 'object') {
			       layer.alert('Tab选项卡错误提示: elem参数未定义或设置出错，具体设置格式请参考文档API.');
		     }
		     var $container;
		     if(typeof(_config.elem) === 'string') {
			     $container = $('' + _config.elem + '');
		     }
		     if(typeof(_config.elem) === 'object') {
			     $container = _config.elem;
		     }
		     if($container.length === 0) {
			     layer.alert('Tab选项卡错误提示:找不到elem参数配置的容器，请检查.');
		     }
		     var filter = $container.attr('lay-filter');
		     if(filter === undefined || filter === '') {
			      layer.alert('Tab选项卡错误提示:请为elem容器设置一个lay-filter过滤器');
		     }
		     _config.elem = $container;
		     ELEM.titleBox = $container.children('ul.layui-tab-title');
		     ELEM.contentBox = $container.children('div.layui-tab-content');
		     ELEM.tabFilter = filter;
		     return _this;
        };
        /**
         * [exists 在layui-tab中检查对应layui-tab-title是否存在，如果存在则返回索引值，不存在返回-1]
         * @param  {[type]} title [description]
         * @return {[type]}       [description]
         */
        LarryTab.prototype.exists = function(title){
            ELEM.titleBox === undefined ? this.init() : this;
			var tabId = -1;
			ELEM.titleBox.find('li').each(function(i, e) {
			    var _title = $(this).find("em").text();
			    if(_title === title) {
                    tabId = $(this).attr('lay-id');
			    }
		    });
		    return tabId;
        };
        /**
         * [tabAdd 增加选项卡，如果已存在则增加this样式]
         * @param  {[type]} data [description]
         * @return {[type]}      [description]
         */
        LarryTab.prototype.tabAdd = function(data){
            var _this = this;
		    var tabId = _this.exists(data.title);

		    if(tabId != -1){// 若存在，刷新tab
                var frame = ELEM.contentBox.find('iframe[data-id=' + tabId + ']');
                frame[0].src = data.href;
                element.render('tab');
		    } else {
                tabId = globalTabIdIndex++;
				var content = '<iframe src="' + data.href + '" data-id="' + tabId + '" class="larry-iframe"></iframe>';
				var title = '';
				// 若icon有定义
				if(data.icon !== undefined){
					if(data.icon.indexOf('icon-') !== -1) {
						title += '<i class="iconfont ' + data.icon + '"></i>';
					}else {
						title += '<i class="layui-icon ">' + data.icon + '</i>';
					}
				}
				title += '<em>' + data.title + '</em>';
				if(_this.config.closed) {
					title += '<i class="layui-icon layui-unselect layui-tab-close" data-id="' + tabId + '">&#x1006;</i>';
				}
				//添加tab
				element.tabAdd(ELEM.tabFilter, {
					id: tabId,
					title: title,
					content: content
				});
				//iframe 自适应
				ELEM.contentBox.find('iframe[data-id=' + tabId + ']').each(function() {
					$(this).height(ELEM.contentBox.height());
				});
				if(_this.config.closed) {
					//监听关闭事件
					ELEM.titleBox.find('li').children('i.layui-tab-close[data-id=' + tabId + ']').on('click', function() {
						element.tabDelete(ELEM.tabFilter, $(this).parent('li').attr("lay-id"));
					});
				}
			}
		    //切换到当前打开的选项卡
		    element.tabChange(ELEM.tabFilter, tabId);
        };
        /**
         * 刷新选项卡
         */
        LarryTab.prototype.reload = function(layId){
        	var frame = ELEM.contentBox.find('iframe[data-id=' + layId + ']');
        	if(frame[0].contentWindow.refresh && typeof(frame[0].contentWindow.refresh) == 'function'){
        		frame[0].contentWindow.refresh();//优先调用页面的刷新回调方法
        	}else{//没有刷新回调方法，则直接刷新整个iframe
        		frame[0].contentWindow.location.reload();
        	}
        };
        /**
         * 刷新选项卡
         */
        LarryTab.prototype.refresh = function(title){
            var layId = this.exists(title);
            if (layId === -1) return;
            this.reload(layId)
        };
        /**
         * 关闭选项卡
         */
        LarryTab.prototype.tabClose = function(index){
        	element.tabDelete(ELEM.tabFilter, index).init();
        };
        /**
         * 监听选项卡切换事件
         */
        LarryTab.prototype.listener = function(target, callback){
        	element.on("tab("+target+")", callback);
        };
    var navtab = new LarryTab();
    exports(module_name, function(options) {
		return navtab.set(options);
	});
		
});
