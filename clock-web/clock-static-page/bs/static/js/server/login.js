//本地接口别名
var RESTApiURL = "/RESTApi/";

// 登陆成功跳转到index.html
function login(email, password) {
    var param = "email=" + email + "&password=" + password;
    $('#loginLoading').show();
    $.ajax({
        url: RESTApiURL + 'company/login',
        type: 'post',
        async: false,
        data: param,
        timeout: 60000,
        success: function(json) {
            $('#loginLoading').hide();
            if (json.result) {
                window.location.href = "./index.html";
            } else {
                toastr.info(json.message);
            }
        },
        error: function() {
            $('#loginLoading').hide();
            toastr.error("网络异常");
        }
    });
    $.ajax({
        url: RESTApiURL + 'staff/login',
        type: 'post',
        async: false,
        data: param,
        timeout: 60000,
        success: function(json) {
            $('#loginLoading').hide();
            if (json.result) {
                window.location.href = "./index.html";
            } else {
                toastr.info(json.message);
            }
        },
        error: function() {
            $('#loginLoading').hide();
            toastr.error("网络异常");
        }
    });

} //END $("#login").function
