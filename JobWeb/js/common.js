$("#srcLi").bind("addSrc",function () {
    $("#srcLi").html("<li><div class=\"user-box\"><div class=\"avatar-lg\">\n" +
        "<img src=\"images/profile.jpg\" alt=\"头像\" class=\"avatar-img rounded\"></div>\n" +
        "</div><div class=\"user-box\"><div class=\"u-text\">\n" +
        "<h2 style=\"color: white\" id=\"name\"></h2>\n" +
        "</div></div></li>")
});
$("#nameLi").bind("addLi",function () {
    $("#nameLi").html("<li><div class=\"dropdown-divider\">" +
        "</div><a class=\"dropdown-item\" href=\"personal.html\">个人资料</a>\n" +
        "<a class=\"dropdown-item\" href=\"star.html\">收藏列表</a>\n" +
        "<div class=\"dropdown-divider\"></div>\n" +
        "<a class=\"dropdown-item\" onclick=\" return exitLogin()\" >退出登录</a>\n" +
        "</li>");
});

var name = $.cookie("userName");
<!--获取登陆状态-->


if( name  != "undefined"){
    $('#srcLi').trigger("addSrc");
    $("#name").text(name);
    $('#nameLi').trigger("addLi");
}
else{
    alert("你还未登录");
}

$("#user-name").text(name);
$("#user-phone").text(phone);


function exitLogin() {
    $.ajax({
        async: false,
        type:"POST",
        url:"http://localhost:8090/exit",
        data:{
            "userName":name,
        },
        xhrFields: {withCredentials: true},
        success:function (result) {
            if (result == "success") {
                $.removeCookie('userName',{ path: '/'});
                alert("退出成功");
                window.location.reload();
            }
        },
        error:function (result) {
            alert("系统错误，重新退出");
            return false;
        }
    });
}




var lastTime = new Date().getTime();
var currentTime = new Date().getTime();
var timeOut = 3 * 60 * 1000; //设置超时时间： 3分

$(function(){
    /* 鼠标移动事件 */
    $(document).mouseover(function(){
        lastTime = new Date().getTime(); //更新操作时间

    });
});

function toLoginPage()
{
    if(name == "undefined"){
        return false;
    }
    currentTime = new Date().getTime(); //更新当前时间
    if(currentTime - lastTime > timeOut){ //判断是否超时
        alert("长时间未操作，请重新登录")
        $.removeCookie('userName',{ path: '/'});
        window.close();//关闭当前页
        window.parent.location.replace("/login.html");//刷新父级页面;
    }
}



/* 定时器
 * 间隔1秒检测是否长时间未操作页面
 */
window.setInterval(toLoginPage, 1000);
