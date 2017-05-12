//本地接口别名
var RESTApiURL = "/RESTApi/";

//i18n初始化
function initI18n(){
    $.i18n.init({
        lng: 'zh-CN',
        load: 'unspecific',
        fallbackLng: false,
        resGetPath: 'locales/__lng__/__ns__.json'
    }, function(err, t) {
        $('[data-i18n]').i18n(); // 通过选择器集体翻译
    });
}//END initI18n().function

// 初始化用户信息
function initInfo(level) {
  $("#name").text("齐钟昱");
  $("#position").text("普通员工");
  $(".logo-element").text("钟昱");
  //同事@提醒
  $("#colleaguesInforms").text(4);
  //系统通知
  $("#systemInforms").text(6);
  // 短信息时间
  $(".chat-date").text("3.17.2017");
  //短信息数量
  $("#chatNum").text(3);
} //END $("#initInfo").function

// 获得左侧菜单,level 用户级别
function initMenu(level) {
   $.ajax({
        url: RESTApiURL + 'common/getMenu/' + level,
        type: 'get',
        data: null,
        timeout: 6000,
        success: function(json) {
          if(json.result){
            for(var menu in json.data){
              $('#'+json.data[menu].parent).show();
              for(var childe in json.data[menu].childs){
                $('#'+json.data[menu].childs[childe]).show();
              }
            }  
          }else{
            toastr.error(json.message);
          }
        },
        error: function() {
          $("#side-menu li").show();
          toastr.error("网络异常");
        }
    });
} //END $("#initMenu").function



