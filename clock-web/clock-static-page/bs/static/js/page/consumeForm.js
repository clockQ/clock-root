function initCreateForm() {
    $("#consumeInfoForm").steps({
        bodyTag: "fieldset",
        onStepChanging: function(event, currentIndex, newIndex) {
            // 总是可以后退,即使字段无效
            if (currentIndex > newIndex) {
                return true;
            }
            // 最后一页将不可以next
            if (newIndex === 4) {
                return false;
            }
            //取出当前表单对象
            var form = $(this);
            //后退则清理当前填写
            if (currentIndex < newIndex) {
                // 移除错误标记
                $(".body:eq(" + newIndex + ") label.error", form).remove();
                $(".body:eq(" + newIndex + ") .error", form).removeClass("error");
            }

            //对于隐藏和不可用的字段禁止校验
             form.validate().settings.ignore = ":disabled,:hidden";
            
            // 校验字段
            return form.valid();
        },
        onFinishing: function(event, currentIndex) {
            var form = $(this);

            // Disable validation on fields that are disabled.
            // At this point it's recommended to do an overall check (mean ignoring only disabled fields)
            form.validate().settings.ignore = ":disabled";

            // Start validation; Prevent form submission if false
            return form.valid();
        },
        onFinished: function(event, currentIndex) {
            var form = $(this);

            // Submit form input
            form.submit();
        }
    }).validate({
        errorPlacement: function(error, element) {
            element.before(error);
        },
        rules: {
            projectNumber:"required",
            consumePurpose:"required",
            consumeStandardSelect:"required",
            accommodationStandardSelect:"required"

        },
        messages: {
            projectNumber: "请输入项目编号",
            consumePurpose: "请选择出差目的",
            consumeStandardSelect:"请选择差补标准",
            accommodationStandardSelect:"请选择住宿标准"
        }
    });
}

//渲染搜索结果
function initSearchProjectResult(){
    $("#projectName").val("天津CBoss回归测试");
    $("#header").val("汪映功");
    $("#startTime").val("2017-03-01");
    $("#available").val("可用");
}

//渲染时间选取器
function intiDaterangepicker() {
    $('#reportrange span').html(moment().subtract(29, 'days').format('YYYY-MM-DD') + '  ==>  ' + moment().format('YYYY-MM-DD'));
    $('#reportrange').daterangepicker({
        format: 'MM/DD/YYYY',
        startDate: moment().subtract(29, 'days'),
        endDate: moment(),
        minDate: '01/01/2000',
        maxDate: new Date(),
        dateLimit: { days: 60 },
        showDropdowns: true,
        showWeekNumbers: true,
        timePicker: false,
        timePickerIncrement: 1,
        timePicker12Hour: true,
        ranges: {
            '今天': [moment(), moment()],
            '前7天': [moment().subtract(6, 'days'), moment()],
            '前30天': [moment().subtract(29, 'days'), moment()],
            '这个月': [moment().startOf('month'), moment().endOf('month')]
        },
        opens: 'right',
        drops: 'down',
        buttonClasses: ['btn', 'btn-sm'],
        applyClass: 'btn-primary',
        cancelClass: 'btn-default',
        separator: ' to ',
        locale: {
            applyLabel: '确定',
            cancelLabel: '取消',
            fromLabel: '自',
            toLabel: '至',
            customRangeLabel: '自定义',
            daysOfWeek: ['日', '一', '二', '三', '四', '五', '六'],
            monthNames: ['一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月'],
            firstDay: 1
        }
    }, function(start, end, label) {
        $('#reportrange span').html(start.format('YYYY-MM-DD') + '  ==>  ' + end.format('YYYY-MM-DD'));
        console.log(start.format('YYYY-MM-DD'), end.format('YYYY-MM-DD'));
    });
}

//渲染多选框
function initSelector() {
    var config = {
        '.chosen-select': {},
        '.chosen-select-deselect': { allow_single_deselect: true },
        '.chosen-select-no-single': { disable_search_threshold: 10 },
        '.chosen-select-no-results': { no_results_text: 'Oops, nothing found!' },
        '.chosen-select-width': { width: "95%" }
    }
    for (var selector in config) {
        $(selector).chosen(config[selector]);
    }
    $(".chosen-container ").css("width","100%");
}


// Examle data for jqGrid
var myFoodData = [
    { "id":"1","time": "2010-05-24", "site": "test", "sum": "10.00", "note": "note", },
    { "id":"2","time": "2010-05-25", "site": "test2", "sum": "20.00", "note": "note2" },
    { "id":"3","time": "2007-09-01", "site": "test3", "sum": "30.00", "note": "note3" },
    { "id":"4","time": "2007-10-04", "site": "test", "sum": "10.00", "note": "note3" },
    { "id":"5","time": "2007-10-05", "site": "test2", "sum": "20.00", "note": "note3" },
    { "id":"6","time": "2007-09-06", "site": "test3", "sum": "30.00", "note": "note3" },
    { "id":"7","time": "2007-10-04", "site": "test", "sum": "10.00", "note": "note3" },
    { "id":"8","time": "2007-10-03", "site": "test2", "sum": "300.00", "note": "note3" },
    { "id":"9","time": "2007-09-01", "site": "test3", "sum": "400.00", "note": "note3" },
    { "id":"10","time": "2007-09-01", "site": "test3", "sum": "400.00", "note": "note3" },
    { "id":"11", "time": "2007-10-01", "site": "test", "sum": "200.00", "note": "note3" },
    { "id":"12","time": "2007-10-02", "site": "test2", "sum": "300.00", "note": "note3" }
]; 

//食品表中的数据总数
var foodTableSum = myFoodData.length;
//最大页数
var maxPage = Math.ceil(foodTableSum/10) ;

//渲染食补列表
function initFoodTable(page) {
    if(page <= 0){
        return false;
    }
    if(page > maxPage){
        return false;
    }
    //全选功能
    $("#checkAll").click(function() {
        if ($("#checkAll").attr("checked") == false) {
            $("input[name='checkbox']").each(function() {
                $(this).attr("checked", true);
            });
        } else {
            $("input[name='checkbox']").each(function() {
                $(this).attr("checked", false);
            });
        }
    });

    var tb = $('#foodTabel tbody');
    //清空原来数据
    tb.html('');
    //写入到页面数据
    if(myFoodData != ""){
        var j = 10;
        for(var i = (page-1)*10 ; i < myFoodData.length ; i++){
            var tr = "<tr> <td> <input name=\"checkbox\" type=\"checkbox\" id="+ i +"> </td>";
            tr += " <td>" + myFoodData[i].id + "</td>";
            tr += " <td>" + myFoodData[i].time + "</td>";
            tr += " <td>" + myFoodData[i].site + "</td>";
            tr += " <td>" + myFoodData[i].sum + "</td>";
            tr += " <td>" + myFoodData[i].note + "</td>";
            tr += " </tr>";
            tb.append(tr);
            j--;
            //每页最多显示10条记录
            if(j==0)
                break;
        }
    }
}

//食补表删除
function delFoodTable(id){
    myFoodData.splice(id,1);
}

//食补表修改
function editFoodTable(id){
    $('#consumptionTime').val(myFoodData[id].time);
    $('#consumptionSite').val(myFoodData[id].site);
    $('#consumptionSum').val(myFoodData[id].sum);
    $('#consumptionNote').val(myFoodData[id].note);
}

//食补表添加
function addFoodTable(){

}

//渲染房补补列表
function initAccommodationTable() {
    // alert("initAccommodationTable");
}


//渲染车补列表
function initTravelTable() {
    // alert("initTravelTable");
}



