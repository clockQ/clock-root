/**
 * 实现ajax的一些通用方法
 */
var Ajax = (function(){
	return {
		//设置整体全局超时时间
		TIME_OUT : 60000,



		/**
		 * 使用post方法提交json的异步请求
		 * @param  {[type]}   url      [请求地址]
		 * @param  {[type]}   param    [json字符串,在本方法中会转为json串]
		 * @param  {Function} callback [回调函数function(json,status){}]
		 * @return {[type]}            [description]
		 */
		postJson : function(url, param, callback) {
			this.ajax(url, 'post', JSON.stringify(param), 'json', "application/json;charset=utf-8", callback, true);
		},
		//不传json而是传字符串
		postParam : function(url, param, callback) {
			this.ajax(url, 'post', param, 'json', 'application/x-www-form-urlencoded charset=utf-8', callback, true);
		},

		/**
		 * 使用get方法提交json的异步请求
		 * @param  {[type]}   url      [请求地址]
		 * @param  {[type]}   param    [json字符串,在本方法中会转为json串]
		 * @param  {Function} callback [回调函数function(json,status){}]
		 * @return {[type]}            [description]
		 */
		getJson : function(url, param, callback) {
			this.ajax(url, 'get', JSON.stringify(param), 'json', "application/json;charset=utf-8", callback, true);
		},
		//不传json而是传字符串
		getParam : function(url, param, callback) {
			this.ajax(url, 'get', param, 'json', 'application/x-www-form-urlencoded charset=utf-8', callback, true);
		},

		/**
		 ******************** 基于jQuery ajax的封装，可配置化
		 * 
		 * @method ajax
		 * @param {String}
		 *            url HTTP(POST/GET)请求地址
		 * @param {String}
		 *            type POST/GET
		 * @param {Object}
		 *            param json参数命令和数据
		 * @param {String}
		 *            dataType 返回的数据类型
		 * @param {String}
		 *            contentType 请求contentType
		 * @param {Function}
		 *            callback [optional,default=undefined]
		 *            请求成功回调函数,返回数据data和isSuc
		 */
		ajax : function(url, type, param, dataType, contentType, callback,
				async) {
			var cache = (dataType === "html") ? true : false;
			var ajaxConfig = {
				url : url,
				type : type,
				data : param,
				cache : false,
				dataType : dataType,
				async : async,
				timeout : this.TIME_OUT,
				//crossDomain : true,
				success : function(data) {
					if (!data) {
						return;
					}
					if (dataType === "html") {
						callback(data, true);
						return;
					}
					if (callback && data) {
						callback(data || {}, true);
					}
				},
				complete : function(XMLHttpRequest, textStatus) {
					var retErr = {};
					if (XMLHttpRequest.status == 910
							|| XMLHttpRequest.status == 911) {
						alert("登录失效,请重新登录！");
						window.location.href = "./login.html";
						return;
					} else if (XMLHttpRequest.status == 404) {
						retErr['retCode'] = "SCRM-404";
						retErr['retMessage'] = "请求数据失败,请检查请求路径！";
						if (callback)
							callback(retErr, false);
					} else if (XMLHttpRequest.status == 500) {
						retErr['retCode'] = "SCRM-500";
						retErr['retMessage'] = "获取数据失败！请稍后再试！";
						if (callback)
							callback(retErr, false);
					}
				}
			};
			if (contentType != null)
				ajaxConfig.contentType = contentType
			$.ajax(ajaxConfig);
		},
	}
})();


