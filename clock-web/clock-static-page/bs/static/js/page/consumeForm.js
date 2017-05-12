var consumptionData = {};

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
            //如果到达最后一页
            if(newIndex == 3){
                statisticsConsumeForm();
            }
            //对于隐藏和不可用的字段禁止校验
            form.validate().settings.ignore = ":disabled,:hidden";
            
            // 校验字段
            return form.valid();
        },
        onCanceled: function(event, currentIndex) {
            swal({
                title: "确定退出吗?",
                text: "是否保存为草稿!",
                type: "warning",
                showCancelButton: true,
                confirmButtonColor: "#DD6B55",
                confirmButtonText: "存为草稿",
                cancelButtonText: "不存为草稿",
                closeOnConfirm: false,
                closeOnCancel: false 
            },function (isConfirm) {
                if (isConfirm) {
                    swal({
                        title: "已存为草稿?",
                        text: "本功能暂未实现!",
                        type: "success",
                    },function(){
                        $('#createFormModal').modal('hide');
                    });
                } else {
                    swal({
                        title: "已取消?",
                        type: "error",
                    },function(){
                        $('#createFormModal').modal('hide');
                    });
                }
            });
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
            var result = submitConsumeForm(consumptionData);
            if(result.result == true){
                swal({
                    title: "添加成功",
                    type: "success"
                },function(){
                    $('#createFormModal').modal('hide');
                });
            }else{
                toastr.error(result.message);
            }
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
function initSearchProjectResult(projectNumber){
    var result = getProjectByNumber(projectNumber);
    if(result.available != 1){
        toastr.error("该项目不可用");
        return false;
    }
    consumptionData.projectId = result.projectId;
    $("#projectName").val(result.name);
    $("#header").val(result.header);
    $("#startTime").val(result.startTime);
    $("#available").val("项目可用");
    $("#projectSite").val(result.site);
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
        consumptionData.startTime = start.format('YYYY-MM-DD');
        consumptionData.endTime = end.format('YYYY-MM-DD');
        consumptionData.time = Math.ceil((end - start)/(1000*60*60*24));
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


function statisticsConsumeForm(){
    consumptionData.consumeStandard = $('#consumeStandardSelect>option:selected').text();
    consumptionData.accommodationStandard = $('#accommodationStandardSelect>option:selected').text();
    consumptionData.consumePurpose = $('#consumePurpose>option:selected').text();
    consumptionData.myFoodData = JSON.stringify(myFoodData);
    consumptionData.allFoodConsume = 0;
    for(var tempId in myFoodData){
        consumptionData.allFoodConsume += parseFloat(myFoodData[tempId].sum);
    }
    consumptionData.myAccommodationData = JSON.stringify(myAccommodationData);
    consumptionData.allAccommodationConsume = 0;
    for(var tempId in myAccommodationData){
        consumptionData.allAccommodationConsume += parseFloat(myAccommodationData[tempId].sum);
    }
    consumptionData.myTravelData = JSON.stringify(myTravelData);
    consumptionData.allTravelConsume = 0;
    for(var tempId in myTravelData){
       consumptionData.allTravelConsume += parseFloat(myTravelData[tempId].sum);
    }
    consumptionData.allConsume = consumptionData.allFoodConsume+consumptionData.allAccommodationConsume+consumptionData.allTravelConsume;

    var html = "<h2>所属项目<small>"+ $('#projectName').val() +"</small>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp";
    html += "第一审批人<small>"+ $('#header').val() +"</small></h2>";
    html += "<h2>报销天数,共<small>"+ consumptionData.time +"</small>天&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp"
    html += "差补标准<small>"+ consumptionData.consumeStandard +"</small></h2>"
    html += "<h2>住宿标准<small>"+ consumptionData.accommodationStandard +"</small>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp"
    html += "出差目的<small>"+ consumptionData.consumePurpose +"</small></h2>"
    html += "<h2>食补<small>"+ consumptionData.allFoodConsume +"</small>&nbsp&nbsp&nbsp&nbsp";
    html += "房补<small>"+ consumptionData.allAccommodationConsume +"</small>&nbsp&nbsp&nbsp&nbsp";
    html += "车补<small>"+ consumptionData.allTravelConsume +"</small>&nbsp&nbsp&nbsp&nbsp";
    html += "共计<small>"+ consumptionData.allConsume +"</small></h2>";
    $('#finishP').html(html);
    
}

var myFoodData = [];
var myAccommodationData = []; 
var myTravelData = []; 

//渲染食补列表
function initFoodTable(page) {
    //全选功能
    $("#checkFoodAll").click(function() {
        if ($("#checkFoodAll").get(0).checked) {
            $("#foodTabel input[name='checkbox']").each(function() {
                $(this).attr("checked", true);
            });
        } else {
            $("#foodTabel input[name='checkbox']").each(function() {
                $(this).attr("checked", false);
            });
        }
    });

    var tb = $('#foodTabel tbody');
    //清空原来数据
    tb.html('');
    //写入到页面数据
    if(myFoodData != "" && myFoodData.length != 0){
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
function delFoodData(id){
    myFoodData.splice(id,1);
}

//食补表添加
function addFoodData(){
    var time = $('#foodConsumptionTime').val();
    var site = $('#foodConsumptionSite').val();
    var sum = $('#foodConsumptionSum').val();
    var note = $('#foodConsumptionNote').val();
    if(myFoodData.length == 0){
        myFoodData.push({'id':1, 'time': time, 'site': site, 'sum': sum, 'note': note});
    }else{
        var maxId=eval(myFoodData)[eval(myFoodData).length-1]["id"];  
        var addId = ++maxId;
        myFoodData.push({'id':addId, 'time': time, 'site': site, 'sum': sum, 'note': note});
    }
}

//食补表修改
function editFoodData(id){
    var editId = 0;
    for(var tempId in myFoodData){
        if(myFoodData[tempId].id == id)
            break;
        editId++;
    }
    var time = $('#foodConsumptionTime').val();
    var site = $('#foodConsumptionSite').val();
    var sum = $('#foodConsumptionSum').val();
    var note = $('#foodConsumptionNote').val();
    myFoodData[editId].time = time;
    myFoodData[editId].site = site;
    myFoodData[editId].sum = sum;
    myFoodData[editId].note = note;
}



//渲染房补列表
function initAccommodationTable(page) {
    //全选功能
    $("#checkAccommodationAll").click(function() {
        if ($("#checkAccommodationAll").get(0).checked) {
            $("#accommodationTabel input[name='checkbox']").each(function() {
                $(this).attr("checked", true);
            });
        } else {
            $("#accommodationTabel input[name='checkbox']").each(function() {
                $(this).attr("checked", false);
            });
        }
    });

    var tb = $('#accommodationTabel tbody');
    //清空原来数据
    tb.html('');
    //写入到页面数据
    if(myAccommodationData != "" && myAccommodationData.length != 0){
        var j = 10;
        for(var i = (page-1)*10 ; i < myAccommodationData.length ; i++){
            var tr = "<tr> <td> <input name=\"checkbox\" type=\"checkbox\" id="+ i +"> </td>";
            tr += " <td>" + myAccommodationData[i].id + "</td>";
            tr += " <td>" + myAccommodationData[i].time + "</td>";
            tr += " <td>" + myAccommodationData[i].site + "</td>";
            tr += " <td>" + myAccommodationData[i].sum + "</td>";
            tr += " <td>" + myAccommodationData[i].note + "</td>";
            tr += " </tr>";
            tb.append(tr);
            j--;
            //每页最多显示10条记录
            if(j==0)
                break;
        }
    }
}

//房补表删除
function delAccommodationData(id){
    myAccommodationData.splice(id,1);
}

//房补表添加
function addAccommodationData(){
    var time = $('#accommodationConsumptionTime').val();
    var site = $('#accommodationConsumptionSite').val();
    var sum = $('#accommodationConsumptionSum').val();
    var note = $('#accommodationConsumptionNote').val();
    if(myAccommodationData.length == 0){
        myAccommodationData.push({'id':1, 'time': time, 'site': site, 'sum': sum, 'note': note});
    }else{
        var maxId=eval(myAccommodationData)[eval(myAccommodationData).length-1]["id"];  
        var addId = ++maxId;
        myAccommodationData.push({'id':addId, 'time': time, 'site': site, 'sum': sum, 'note': note});
    }
}

//房补表修改
function editAccommodationData(id){
    var editId = 0;
    for(var tempId in myAccommodationData){
        if(myAccommodationData[tempId].id == id)
            break;
        editId++;
    }
    var time = $('#accommodationConsumptionTime').val();
    var site = $('#accommodationConsumptionSite').val();
    var sum = $('#accommodationConsumptionSum').val();
    var note = $('#accommodationConsumptionNote').val();
    myAccommodationData[editId].time = time;
    myAccommodationData[editId].site = site;
    myAccommodationData[editId].sum = sum;
    myAccommodationData[editId].note = note;
}

//渲染车补列表
function initTravelTable(page) {
    //全选功能
    $("#checkTravelAll").click(function() {
        if ($("#checkTravelAll").get(0).checked) {
            $("#travelTabel input[name='checkbox']").each(function() {
                $(this).attr("checked", true);
            });
        } else {
            $("#travelTabel input[name='checkbox']").each(function() {
                $(this).attr("checked", false);
            });
        }
    });

    var tb = $('#travelTabel tbody');
    //清空原来数据
    tb.html('');
    //写入到页面数据
    if(myTravelData != "" && myTravelData.length != 0){
        var j = 10;
        for(var i = (page-1)*10 ; i < myTravelData.length ; i++){
            var tr = "<tr> <td> <input name=\"checkbox\" type=\"checkbox\" id="+ i +"> </td>";
            tr += " <td>" + myTravelData[i].id + "</td>";
            tr += " <td>" + myTravelData[i].time + "</td>";
            tr += " <td>" + myTravelData[i].startSite + "</td>";
            tr += " <td>" + myTravelData[i].endSite + "</td>";
            tr += " <td>" + myTravelData[i].sum + "</td>";
            tr += " <td>" + myTravelData[i].note + "</td>";
            tr += " </tr>";
            tb.append(tr);
            j--;
            //每页最多显示10条记录
            if(j==0)
                break;
        }
    }
}

//出行表删除
function delTravelData(id){
    myTravelData.splice(id,1);
}

//出行表添加
function addTravelData(){
    var time = $('#travelConsumptionTime').val();
    var startSite = $('#travelConsumptionStartSite').val();
    var endSite = $('#travelConsumptionEndSite').val();
    var sum = $('#travelConsumptionSum').val();
    var note = $('#travelConsumptionNote').val();
    if(myTravelData.length == 0){
        myTravelData.push({'id':1, 'time': time, 'startSite': startSite, 'endSite': endSite, 'sum': sum, 'note': note});
    }else{
        var maxId=eval(myTravelData)[eval(myTravelData).length-1]["id"];  
        var addId = ++maxId;
        myTravelData.push({'id':addId, 'time': time, 'startSite': startSite, 'endSite': endSite, 'sum': sum, 'note': note});
    }
}

//出行表修改
function editTravelData(id){
    var editId = 0;
    for(var tempId in myTravelData){
        if(myTravelData[tempId].id == id)
            break;
        editId++;
    }
    var time = $('#travelConsumptionTime').val();
    var startSite = $('#travelConsumptionStartSite').val();
    var endSite = $('#travelConsumptionEndSite').val();
    var sum = $('#travelConsumptionSum').val();
    var note = $('#travelConsumptionNote').val();
    myTravelData[editId].time = time;
    myTravelData[editId].startSite = startSite;
    myTravelData[editId].endSite = endSite;
    myTravelData[editId].sum = sum;
    myTravelData[editId].note = note;
}

// 渲染模态框的新增表单
$(document).on('click', '#createFormBtn', function() {
    initCreateForm();//模态表单
    intiDaterangepicker();//时间选择器
    initSelector();//多选框
    initFoodTable(1);//默认为食补页
});
//刷新页面,不然下次加载模态框css不渲染
$("#createFormModal").on("hidden.bs.modal", function() {  
    location.reload(true);
}); 
//根据项目编号查询信息按钮响应
$(document).on('click', '#searchProjectBtn', function() {
    var projectNumber = $('#projectNumber').val();
    if(projectNumber != "")
        initSearchProjectResult(projectNumber);
    else
        toastr.error("项目编号不可为空");
        return false;
});

//报销明细中的页码,从1开始,每页10条
var page = 1;
//最大页数(默认食补表的)
var maxPage = Math.ceil(myFoodData.length/10) ;
//操作的tab页,默认食补表的
var vTable = 'food';

//切换到食补表格按钮
$(document).on('click', '#foodTabBtn', function() {
    page = 1;
    maxPage = Math.ceil(myFoodData.length/10) ;
    vTable = 'food';
    initFoodTable(page);//默认为食补页
});
//切换到房补表格按钮
$(document).on('click', '#accommodationTabBtn', function() {
    page = 1;
    maxPage = Math.ceil(myAccommodationData.length/10) ;
    vTable = 'accommodation';
    initAccommodationTable(page);//住房补贴
});
//切换到车补表格按钮
$(document).on('click', '#travelTabBtn', function() {
    page = 1;
    maxPage = Math.ceil(myTravelData.length/10) ;
    vTable = 'travel';
    initTravelTable(page);//出行补贴
});

//如果只有一页,则下一页按钮不可用
if(maxPage <= 1){
    $("#nextPage").addClass('disabled');
}

//上一页按钮
$(document).on('click', '#previousPageBtn', function() {
    $("#nextPage").removeClass('disabled');
    page -= 1;
    if(page == 1){
        $("#previousPage").addClass('disabled');
    }
    if(page >= 1){
        if(vTable=='food')
            initFoodTable(page);
        else if(vTable=='accommodation')
            initAccommodationTable(page);
        else if(vTable=='travel')
            initTravelTable(page);
    }else{//不翻页,page值还原
        page += 1;
    }
});    
//下一页按钮
$(document).on('click', '#nextPageBtn', function() {
    $("#previousPage").removeClass('disabled');
    page += 1;
    if(page == maxPage){
        $("#nextPage").addClass('disabled');
    }
    if(page <= maxPage){        
        if(vTable=='food')
            initFoodTable(page);
        else if(vTable=='accommodation')
            initAccommodationTable(page);
        else if(vTable=='travel')
            initTravelTable(page);
    }else{
        page -= 1;
    }
});
//删除按钮
$(document).on('click', '#btnDel', function() {
    var i=0;
    $('input[name="checkbox"]:checked').each(function(){ 
        //在json中删除
        if(vTable=='food')
            delFoodData($(this)[0].id-i);  
        else if(vTable=='accommodation')
            delAccommodationData($(this)[0].id-i);  
        else if(vTable=='travel')
            delTravelData($(this)[0].id-i);  
        i++;
    }); 
    //重新载入table
    if(vTable=='food')
        initFoodTable(page);
    else if(vTable=='accommodation')
        initAccommodationTable(page);
    else if(vTable=='travel')
        initTravelTable(page);
});

//新增按钮
$(document).on('click', '#btnAdd', function() {
    if(vTable=='food'){
        $('#foodInfoModal').modal('show');
        $('#foodConsumptionId').val("");
        $('#foodConsumptionTime').val("");
        $('#foodConsumptionSite').val("");
        $('#foodConsumptionSum').val("");
        $('#foodConsumptionNote').val("");
    }else if(vTable=='accommodation'){
        $('#accommodationInfoModal').modal('show');
        $('#accommodationConsumptionId').val("");
        $('#accommodationConsumptionTime').val("");
        $('#accommodationConsumptionSite').val("");
        $('#accommodationConsumptionSum').val("");
        $('#accommodationConsumptionNote').val("");
    }else if(vTable=='travel'){
        $('#travelInfoModal').modal('show');
        $('#travelConsumptionId').val("");
        $('#travelConsumptionTime').val("");
        $('#travelConsumptionStartSite').val("");
        $('#travelConsumptionEndSite').val("");
        $('#travelConsumptionSum').val("");
        $('#travelConsumptionNote').val("");
    }
});
//编辑按钮
$(document).on('click', '#btnEdit', function() {
    var id;
    $('input[name="checkbox"]:checked').each(function(){ 
        id = $(this)[0].id;
    });
    if(vTable=='food'){
        $('#foodInfoModal').modal('show');
        $('#foodConsumptionId').val(myFoodData[id].id);
        $('#foodConsumptionTime').val(myFoodData[id].time);
        $('#foodConsumptionSite').val(myFoodData[id].site);
        $('#foodConsumptionSum').val(myFoodData[id].sum);
        $('#foodConsumptionNote').val(myFoodData[id].note);
    }else if(vTable=='accommodation'){
        $('#accommodationInfoModal').modal('show');
        $('#accommodationConsumptionId').val(myAccommodationData[id].id);
        $('#accommodationConsumptionTime').val(myAccommodationData[id].time);
        $('#accommodationConsumptionSite').val(myAccommodationData[id].site);
        $('#accommodationConsumptionSum').val(myAccommodationData[id].sum);
        $('#accommodationConsumptionNote').val(myAccommodationData[id].note);
    }else if(vTable=='travel'){
        $('#travelInfoModal').modal('show');
        $('#travelConsumptionId').val(myTravelData[id].id);
        $('#travelConsumptionTime').val(myTravelData[id].time);
        $('#travelConsumptionStartSite').val(myTravelData[id].startSite);
        $('#travelConsumptionEndSite').val(myTravelData[id].endSite);
        $('#travelConsumptionSum').val(myTravelData[id].sum);
        $('#travelConsumptionNote').val(myTravelData[id].note);
    }
});

//食补编辑模态框提交按钮
$(document).on('click', '#foodInfoModalSubmit', function() {
    if($('#foodConsumptionId').val() != ""){//修改信息
        editFoodData($('#foodConsumptionId').val());
    }else{//新增信息
        addFoodData();
    }
    $('#foodInfoModal').modal('hide');
    initFoodTable(page);
});

//住宿编辑模态框提交按钮
$(document).on('click', '#accommodationInfoModalSubmit', function() {
    if($('#accommodationConsumptionId').val() != ""){//修改信息
        editAccommodationData($('#accommodationConsumptionId').val());
    }else{//新增信息
        addAccommodationData();
    }
    $('#accommodationInfoModal').modal('hide');
    initAccommodationTable(page);
});

//出行编辑模态框提交按钮
$(document).on('click', '#travelInfoModalSubmit', function() {
    if($('#travelConsumptionId').val() != ""){//修改信息
        editTravelData($('#travelConsumptionId').val());
    }else{//新增信息
        addTravelData();
    }
    $('#travelInfoModal').modal('hide');
    initTravelTable(page);
});