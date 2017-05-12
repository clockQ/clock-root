//本地接口别名
var RESTApiURL = "/RESTApi/";

var loginErrorMessage = "";
// 登陆成功跳转到consumeForm.html
function login(email, password) {
    var param = "email=" + email + "&password=" + password;
    $.ajax({
        url: RESTApiURL + 'company/login',
        type: 'post',
        async: false,
        data: param,
        timeout: 6000,
        success: function(json) {
            if (json.result) {
                //保存登录人的等级
                $.cookie('loginRole', 0); 
                //保存登录人的id
                $.cookie('loginRoleId', json.data.companyId); 
                window.location.href = "./consumeForm.html";
            }
        }
    });
    $.ajax({
        url: RESTApiURL + 'staff/login',
        type: 'post',
        async: false,
        data: param,
        timeout: 6000,
        success: function(json) {
            if (json.result) {
                //保存登录人的等级
                $.cookie('loginRole', json.data.leave); 
                //保存登录人的id
                $.cookie('loginRoleId', json.data.staffId); 
                window.location.href = "./consumeForm.html";
            } else {
                loginErrorMessage = json.message;
                toastr.error(loginErrorMessage);
            }
        }
    });

} //END $("#login").function
