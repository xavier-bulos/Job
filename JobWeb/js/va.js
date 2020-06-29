// JavaScript Document
var ele_pass = document.getElementsByTagName("input")[1];
	var ele_progress = document.getElementsByClassName("progress-bar progress-bar-danger")[0];
	var begin_classname = "progress-bar progress-bar-";
	var regxs = [];
	regxs.push(/[^a-zA-Z0-9_]/g);//(/[^a-zA-Z0-9_]/g)
	regxs.push(/[a-zA-Z]/g);//(/[a-zA-Z]/g)
	regxs.push((/[0-9]/g));//(/[0-9]/g)
 
	ele_pass.onkeyup = function () {
  	var val = this.value;
  	var len = val.length;
  	var sec = 0;
  	if (len >= 6) { // 至少六个字符
   	 for (var i = 0; i < regxs.length; i++) {
     	 if (val.match(regxs[i])) {
      	  sec++;
      	}
   	 }
  	}
  var result = (sec / regxs.length) * 100;
  ele_progress.style.width = result + "%";
  if(result > 0 && result <= 50){
    ele_progress.setAttribute("class",begin_classname + "danger");
	document.getElementById("tip").innerHTML='密码强度：不安全';
  }else if (result > 50 && result < 100) {
    ele_progress.setAttribute("class",begin_classname + "warning");
	document.getElementById("tip").innerHTML='密码强度：一般';
  } else if (result == 100) {
    ele_progress.setAttribute("class",begin_classname + "success");
	document.getElementById("tip").innerHTML='密码强度：安全';
  }
 
}