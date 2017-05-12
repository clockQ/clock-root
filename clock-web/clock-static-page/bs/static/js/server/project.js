//本地接口别名
var RESTApiURL = "/RESTApi/";

// 根据项目编号获得项目信息
function getProjectByNumber(projectNumber){
	var param = "number="+projectNumber;
	var result;
	Ajax.getParamSync(RESTApiURL + 'project/getProjectByNumber',param,
		function(json,status){
			if(json.result)
				result = json.data;//callback(null,json.data);
			else
				result = json.message;//callback(json.message,null);
		}
	);//END ajax
	return result;
}//END $("#getProjectByNumber").function
