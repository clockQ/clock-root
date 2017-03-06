//本地接口别名
var RESTApiURL = "/RESTApi/company";

// 公司注册
function register_company(){
	var param = {"companyName":"hangzhoushuma","email":"45678","password":"12345","phone":"0432-1234"};
	Ajax.postJson(RESTApiURL + "/register",param,function(data,status){
		alert(data.message);
	});//END ajax
}//END $("#register_company").function

// 公司登录
function login_company(){
	var param = {"email":"testmail42","password":"12345"};
	Ajax.postJson(RESTApiURL + "/login",param,function(data,status){
		alert(data.message);
	});//END ajax
}//END $("#login_btn").function

// 公司删除
function remove_company(){
	var param = {companyId:13};
	Ajax.postJson(RESTApiURL + "/remove",param,function(data,status){
		alert(data);
	});//END ajax
}//END $("#login_btn").function

// 根据id查询公司信息
function id_company(){
	var param = 14;
	alert("123");
	Ajax.getParam(RESTApiURL + "/getCompany",param,function(data,status){
		alert(data);
	});//END ajax
}//END $("#login_btn").function

// 根据名字获得公司列表
function name_company(){
	var param = "email = testmail42";
	Ajax.getParam(RESTApiURL + "/getCompanyNameList",param,function(data,status){
		alert(data);
	});//END ajax
}//END $("#login_btn").function
