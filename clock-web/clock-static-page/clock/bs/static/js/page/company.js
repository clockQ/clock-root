//本地接口别名
var {company} = 'RESTApi/company';


function login_company(){
	$.ajax({
		type : "POST",
		url : '/RESTApi/company/login',
		dataType : "json",//设置需要返回的数据类型
		success : function(d) {
			alert(d);
		},
		error : function(d) {
			alert(d.responseText);
		}
	});//END ajax
}//END $("#login_btn").click
