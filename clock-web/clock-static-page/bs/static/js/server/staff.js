//本地接口别名
var RESTApiURL = "/RESTApi/staff/";

// 公司注册
function register_company(email,password) {
    var param = { "companyName": "hangzhoushuma", "email": "45678", "password": "12345", "phone": "0432-1234" };
    Ajax.postJson(RESTApiURL + "register", param,
        function(json, status) {
            alert(json.message);
        }
    ); //END ajax
} //END $("#register_company").function

// 公司删除
function remove_company(){
	Ajax.deleteParam(RESTApiURL + 54, null,
		function(json,status){
			alert(json.message);
		}
	);//END ajax
}//END $("#remove_company").function

// 公司信息修改
function modify_company(){
	var param = {"companyName":"test","email":"45678","password":"12345"};
	Ajax.putJson(RESTApiURL + 20, param,
		function(json,status){
			alert(json.message);
		}
	);//END ajax
}//END $("#modify_company").function

// 根据id获得公司信息
function findCompanyById(){
	Ajax.getParam(RESTApiURL + 1,null,
		function(json,status){
			alert(json.message);
		}
	);//END ajax
}//END $("#findCompanyById").function

// 获得公司名称集合
function findCompanyByName(){
	var param = "name=test";
	Ajax.getParam(RESTApiURL + 'getCompanyByName',param,
		function(json,status){
			alert(json);
		}
	);//END ajax
}//END $("#findCompanyByName").function

// 获得公司邮箱集合
function findCompanyByEmail(){
	var param = "email=test";
	Ajax.getParam(RESTApiURL + 'getCompanyByEmail',param,
		function(json,status){
			alert(json);
		}
	);//END ajax
}//END $("#findCompanyByEmail").function
