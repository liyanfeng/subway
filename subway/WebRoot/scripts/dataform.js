(function($) {

	function DataForm() {
	}

	var closeWindow = function(action) {
		if (action == "close" && form.isChanged()) {
			if (confirm("数据被修改了，是否先保存？")) {
				return false;
			}
		}
		if (window.CloseOwnerWindow)
			return window.CloseOwnerWindow(action);
		else
			window.close();
	};

	// 解析js对象为参数名=参数值方式
	var parseParam = function(config){
		//解析js对象为参数名=参数值方式
		var paramData = "";
		var iterParam = function(obj){
			if(obj.constructor == Object){
				var index = 0;
				for(var i in obj){
					if(index != 0){
						paramData += "&";
					}
					if(obj[i].constructor == Object){
						iterParam(obj[i],i);
					}else {
						var value = obj[i];
						if(value.constructor == Date){//日期转换
							value = mini.formatDate(value,"yyyy-MM-dd HH:mm:ss");
						}/*else {value = value.replace(/[&]/g,"&amp;");}*/
						
						if(arguments.length == 2){
							paramData += arguments[1] + "."+ i + "=" + value;
						}else {
							paramData +=  i + "=" + value;
						}
					}
					index++;
				}
			}
		};
		iterParam(config);
		return paramData;
	};

	$.extend(DataForm.prototype, {
		init : function() {
			mini.parse();
		},
		saveData : function(options) {
			var defaults = {
				url : "",
				id : "",
				validator : function() {
					return true;
				}
			};
			var opts = $.extend(defaults, options);
			var dataform = new mini.Form(opts.id);

			// 表单验证
			dataform.validate();
			if (dataform.isValid() == false) {
				return;
			}

			if (!opts.validator()) { // 业务验证
				return;
			}

			var data = dataform.getData(); // 获取表单数据
			var paramData = parseParam(data);
			$.ajax({
				url : basePath + opts.url,
				type : "post",
				data : paramData,
				cache : false,
				success : function(text) {
					closeWindow("save");
				},
				error : function(jqXHR, textStatus, errorThrown) {
					closeWindow();
				}
			});
		},
		onCancel : function() {
			closeWindow("cancel");
		},
		showUpdate : function(config) {
			var form = new mini.Form(config.formId);
			var myurl = config.dataUrl;
			var param = config.fieldId + "=" + config.data.row[config.fieldId];
			if (myurl.indexOf("?") == -1) {
				myurl += "?" + param;
			} else {
				myurl += "&" + param;
			}
			$.ajax({
				url : basePath + myurl,
				cache : false,
				success : function(text) {
					var o = mini.decode(text);
					form.setData(o);
					form.setChanged(false);

					$.form.loadFormSuccess(form);
				}
			});
		},
		loadFormSuccess : function() { // 表单数据加载完成后执行
		}
	});

	$.form = new DataForm();
})(jQuery);