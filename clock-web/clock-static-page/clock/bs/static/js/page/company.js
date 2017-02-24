//本地接口别名
var RESTApiURL = "/RESTApi/company";


function login_company(){
	$.ajax({
		type : "GET",
		url : RESTApiURL + "/login",
		dataType : "json",//设置需要返回的数据类型
		success : function(d) {
			alert(d);
		},
		error : function(d) {
			alert(d.responseText);
		}
	});//END ajax
}//END $("#login_btn").click
