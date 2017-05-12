/**
 * @author clock
 * 实现ajax的一些通用方法
 */
var Ajax = (function(){
	return {
		//设置整体全局超时时间
		TIME_OUT : 60000,



		/**
		 * 使用post方法提交json的异步请求
		 * @param  {[type]}   url      [请求地址]
		 * @param  {[type]}   param    [字符串,在本方法中会转为json串]
		 * @param  {Function} callback [回调函数function(json,status){}]
		 * @return {[type]}            [description]
		 */
		postJson : function(url, param, callback) {
			this.ajax(url, 'post', JSON.stringify(param), 'json', "application/json;charset=utf-8", callback, true);
		},

		/**
		 * 使用post方法提交json的同步请求
		 */
		postJsonSync : function(url, param, callback) {
			this.ajax(url, 'post', JSON.stringify(param), 'json', "application/json;charset=utf-8", callback, false);
		},

		//不传json而是传字符串,如果action采用string为参数接收时使用
		postParam : function(url, param, callback) {
			this.ajax(url, 'post', param, 'json', 'application/x-www-form-urlencoded;charset=utf-8', callback, true);
		},

		/**
		 * 使用get方法提交字符串的异步请求
		 * @param  {[type]}   url      [请求地址]
		 * @param  {[type]}   param    [字符串,可以为空,操作的id直接写入url]
		 * @param  {Function} callback [回调函数function(json,status){}]
		 * @return {[type]}            [description]
		 */
		getParam : function(url, param, callback) {
			this.ajax(url, 'get', param, 'json', 'application/x-www-form-urlencoded;charset=utf-8', callback, true);
		},

		/**
		 * 使用get方法提交字符串的同步请求
		 * @param  {[type]}   url      [请求地址]
		 * @param  {[type]}   param    [字符串,可以为空,操作的id直接写入url]
		 * @param  {Function} callback [回调函数function(json,status){}]
		 * @return {[type]}            [description]
		 */
		getParamSync : function(url, param, callback) {
			this.ajax(url, 'get', param, 'json', 'application/x-www-form-urlencoded;charset=utf-8', callback, false);
		},

		/**
		 * 使用delete方法的异步请求
		 * @param  {[type]}   url      [请求地址]
		 * @param  {[type]}   param    [字符串,一般为空,操作的id直接写入url]
		 * @param  {Function} callback [回调函数function(json,status){}]
		 * @return {[type]}            [description]
		 */
		deleteParam : function(url, param, callback) {
			this.ajax(url, 'delete', param, 'json', 'application/x-www-form-urlencoded;charset=utf-8', callback, true);
		},

		/**
		 * 使用put方法提交json的异步请求
		 * @param  {[type]}   url      [请求地址]
		 * @param  {[type]}   param    [字符串,在本方法中会转为json串]
		 * @param  {Function} callback [回调函数function(json,status){}]
		 * @return {[type]}            [description]
		 */
		putJson : function(url, param, callback) {
			this.ajax(url, 'put', JSON.stringify(param), 'json', 'application/json;charset=utf-8', callback, true);
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
		 *            param 发送数据
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
				}
			};
			if (contentType != null)
				ajaxConfig.contentType = contentType;
			$.ajax(ajaxConfig);
		},
	}
})();


