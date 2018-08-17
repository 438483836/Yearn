var base_url = 'http://localhost:8080';


var login = function () {
    var uids = document.getElementById("user").value;
    var pwds = document.getElementById("pwd").value;
    $.ajax({
        url: base_url + "/login",       
        cache: false,
        data: {user: uids, pwd: pwds},
        success: function (data) {
            noticeBox('登录成功,您的角色为：' + data);
        },
        error: function () {
            errorBox('登录失败！');
        }
    });
}

var up = function () {
    var barcode = document.getElementById("barcode").value;
    var smt = document.getElementById("smt").value;
    var exit = document.getElementById("exit").value;
    console.log(barcode+"--"+smt+"--"+exit);
    $.ajax({
        url: base_url + "/upload",
        cache: false,
        data: {barcode: barcode, smt: smt, exit: exit},
        success: function (data) {
            console.log(data);
            if("上件成功"==data){
                noticeBox(data);
            }else{
                warningBox("上件失败:"+data)
            }
        },
        error: function () {
            errorBox("请求失败！");
        }
    });
}


var pack = function ( e,s) {
    var e = document.getElementById("export").value;
    var status = document.getElementById("status").value;
    $.ajax({
        url: base_url + "/pack",
        cache: false,
        data: {export: e, status: s},
        success: function (data) {
            console.log(data);
            if("失败"==data){
                warningBox("失败");
            }else{
                noticeBox("下件数量:"+data);
            }
        },
        error: function () {
            errorBox("请求失败！");
        }
    });
}

var search = function () {
    var bar = document.getElementById("barcode2").value;
    console.log(bar);
    $.ajax({
        url: base_url + "/search",
        cache: false,
        data: {barcode: bar},
        success: function (data) {
            noticeBox("查询成功=====>" + data);
        },
        error: function () {
            errorBox("查询失败! ");
        }
        
    });
}

var saveSlogan = function () {
    var bar = document.getElementById("barcode2").value;
    var slogan = document.getElementById("slogan2").value;
    console.log(bar+"--"+slogan);
    $.ajax({
       url: base_url + "/upSlogan",
       cache: false,
       data: {barcode: bar, slogan: slogan},
       success: function (data) {
           noticeBox("添加格口号成功=====>>" + data);
       },
       error: function () {
           errorBox("添加格口号失败");
       } 
    });
}

var saveData = function () {

    var dataPacket = document.getElementById("dataPacket").value;
    var packetSize = document.getElementById("packetSize").value;
    var plcCode = document.getElementById("plcCode").value;
    var barCode = document.getElementById("barCode3").value;
    var packageInt = document.getElementById("packageInt").value;
    var packageDec = document.getElementById("packageDec").value;
    var slogan = document.getElementById("slogan").value;
    var backup = document.getElementById("backup").value;
    var checkData = document.getElementById("checkData").value;

    console.log(dataPacket+"--"+packetSize+""+plcCode+"--"+barCode+"--"+packageInt+"--"+packageDec+"--"+slogan+"--"+backup+"--"+checkData);
    $.ajax({
        url: base_url + "/saveData",
        cache: false,
        data: {dataPacket: dataPacket, packetSize: packetSize, plcCode: plcCode, barCode: barCode, packageInt: packageInt, packageDec: packageDec, slogan: slogan, backup: backup, checkData: checkData},
        success: function (data) {
            noticeBox("保存成功" + data);
        },
        error: function () {
          noticeBox("保存失败! ");  
        },

    });

    
}

var searchDeskMess = function () {
    
    var barcode = document.getElementById("barcode3").value;

    if(event.keyCode == 13){

        console.log(barcode);
        $.ajax({
            url: base_url + "/searchDeskMess",
            cache: false,
            data: {barcode: barcode},
            success: function (data) {
                noticeBox("查询成功=====>" + data);
            },
            error: function () {
                errorBox("查询失败! ");
            }

        });
    }
    
    
}

var paInformationUp =  function () {
    
    var sortPortCode = document.getElementById("sortPortCode").value;
    var packageCode = document.getElementById("packageCode").value;
    var bindingTime = document.getElementById("bindingTime").value;
    var employeeCode = document.getElementById("employeeCode").value;
    var employeeName = document.getElementById("employeeName").value;
    var siteName = document.getElementById("siteName").value;
    var uploadTime = document.getElementById("uploadTime").value;
    var lineCode = document.getElementById("lineCode").value;
    
    console.log(sortPortCode+"---"+packageCode+"---"+bindingTime+"---"+employeeCode+"---"+employeeName+"---"+siteName+"---"+uploadTime+"---"+lineCode);
    $.ajax({
        url: base_url+"/upInformation",
        cache: false,
        data: {sortPortCode: sortPortCode, packageCode:packageCode, bindingTime:bindingTime, employeeCode:employeeCode, employeeName:employeeName, siteName:siteName,uploadTime:uploadTime, lineCode:lineCode},
        success: function (data) {
            noticeBox("查询成功=====>" + data);
        },
        error: function () {
            errorBox("查询失败! ");
        }
    });
    
}

var findData = function () {
    
    var barcode = document.getElementById("barcode4").value;
    console.log(barcode);
    $.ajax({
        url: base_url + "/findData",
        cache: false,
        data: {barcode: barcode},
        success: function (data) {
            noticeBox("查询成功=====>" + data);
        },
        error: function () {
            errorBox("查询失败! ");
        }

    });
}




var noticeBox = function (msg) {
    event.preventDefault();
    event.stopPropagation();
    return $.growl.notice({
        title: "提醒",
        message: msg
    });
}
var errorBox = function (msg) {
    event.preventDefault();
    event.stopPropagation();
    return $.growl.error({
        title: "错误",
        message: msg
    });

}
var warningBox = function (msg) {
    event.preventDefault();
    event.stopPropagation();
    return $.growl.warning({
        title: "警告",
        message: msg
    });
}



