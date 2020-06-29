
jQuery(document).ready(function() {
	
    /*
        Fullscreen background
    */
    $.backstretch("assets/img/backgrounds/1.jpg");
    
    /*
	    Modals
	*/
	$('.launch-modal').on('click', function(e){
		e.preventDefault();
		$( '#' + $(this).data('modal-id') ).modal();
	});
    
    /*
        Form validation	
    */
    $('.registration-form input[type="text"], .registration-form textarea').on('focus', function() {
    	$(this).removeClass('input-error');
    });
    
    $('.registration-form').on('submit', function(e) {
    	
    	$(this).find('input[type="text"], textarea').each(function(){
    		if( $(this).val() == "" ) {
    			e.preventDefault();
    			$(this).addClass('input-error');
    		}
    		else {
    			$(this).removeClass('input-error');
    		}
    	});
    	
    });
    
    
});

// function checkInCorrect()      //判断用户名和密码是否为空
//         {
//             if (document.getElementById('username').value == "") {
//                 alert('请输入用户名！')
//                 document.getElementById('username').focus();
//                 return false
//             }
//             if (document.getElementById('password').value == "") {
//                 alert('请输入密码！')
//                 document.getElementById('password').focus();
//                 return false
//             }
//             else {
//                 saveInfo();
//                 return true;
//             }
//         };
 
//         saveInfo = function () {
//             try {
//                 var isSave = document.getElementById('remember_password').checked;   //判断保存按键是否选中
//                 if (isSave) {
//                     var username = document.getElementById('username').value;
//                     var password = document.getElementById('password').value;
//                     if (username != "" && password != "") {
//                         SetCookie(username, password);
//                     }
//                 } else {
//                     SetCookie("", "");
//                 }
//             } catch (e) {
 
//             }
//         };
 
//         function SetCookie(username, password) {
//             var Then = new Date();
//             Then.setTime(Then.getTime() + 1866240000000); //7天有效期
//             document.cookie = "username=" + username + "%%" + password + ";expires=" + Then.toGMTString();
//         };
 
//         function GetCookie() {
//             var nmpsd;
//             var nm;
//             var psd;
//             var cookieString = new String(document.cookie);
//             var cookieHeader = "username=";
//             var beginPosition = cookieString.indexOf(cookieHeader);
//             cookieString = cookieString.substring(beginPosition);
//             var ends = cookieString.indexOf(";");
//             if (ends != -1) {
//                 cookieString = cookieString.substring(0, ends);
//             }
//             if (beginPosition > -1) {
//                 nmpsd = cookieString.substring(cookieHeader.length);
//                 if (nmpsd != "") {
//                     beginPosition = nmpsd.indexOf("%%");
//                     nm = nmpsd.substring(0, beginPosition);
//                     psd = nmpsd.substring(beginPosition + 2);
//                     document.getElementById('username').value = nm;
//                     document.getElementById('password').value = psd;
//                     if (nm != "" && psd != "") {
//                         // document.forms[0].checkbox.checked = true;
//                         document.getElementById('remember_password').checked = true;
//                     }
//                 }
//             }
//         };