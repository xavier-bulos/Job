
//登录+注册操作操作
var phone =/[1][3-9][0-9]{9,9}/;
var validCode=true;
/*
function cliLogin() {
	//$.trim用于去除空白字符
	var txtUser = $.trim($("#username").val());
	var txtPwd = $("#password").val();
	var txtrePwd = $("#re-password").val();
	var phones = $.trim($("#phone").val());
	if ($.trim(txtUser) == "") {
		Tip('请输入账号');
		$("#username").focus();
		return false;
	}
	else if ($.trim(txtPwd) == "") {
		Tip('请输入密码！');
		$("#password").focus();
		return false;
	}
	else if($("#re-password").length > 0 && $.trim(txtrePwd) != $.trim(txtPwd)){
		Tip('两次输入的密码不一致');
		$("#re-password").focus();
		return false;
	}
	//注册
	 if ($("#phone").length > 0 && $.trim(phones) == "") {
		Tip('请填写手机号码！');
		$("#phone").focus();
		return false;
	}
	else if( $("#phone").length > 0 && !phone.test(phones)){
		Tip('手机号格式不正确 ,请重新输入');
		$("#phone").focus();
		return false;
}
	else{
		hideTip();
	}
	return true;
}

function Sendpwd(sender) {
	var time=30;
	var code=$(sender);
		if (validCode) {
			validCode=false;
			code.addClass("msgs1").attr("disabled",true);;
		var t=setInterval(function  () {
			time--;
			code.val(time+"秒");
			if (time==0) {
				clearInterval(t);
			code.val("重新获取");
				validCode=true;
			code.removeClass("msgs1").attr("disabled",false);
			}
		},1000);
		}

}
*/
function Tip(msg) {
	$(".tishi").show().html("<div class='prompt'><i class='tishi_icon'></i>"+msg+"</div>");
}
function hideTip() {
	$(".tishi").hide();
}
jQuery(function(){
	"use strict";
		$(".navList .navLi").hover(function(){
		$(this).addClass("active");
		$(this).find(".navSub").stop().slideDown();	
	},function(){
		$(this).removeClass("active");
		$(this).find(".navSub").stop().slideUp();
		$(".navThrList").slideUp();
		$(".navFouList").slideUp();
	});
	function scro(){
			if($(document).scrollTop()>50){$(".headerbg").addClass("active");}else{$(".headerbg").removeClass("active");}
		}
		scro();
		$(window).scroll(function(){
			scro();
		});
			$(".benefits_name").hover(function(){
			$(this).addClass("hover");
			$(this).stop().animate({bottom: "0px", opacity:1 , height:"400px"}, "fast");
		},function(){
			$(this).removeClass("hover");
			$(this).stop().animate({bottom: "0px", opacity:1 , height:"400px"}, "fast") ; 			
		}
	);
		$(".Program_name ").hover(function() {
	    $(this).addClass("hover");
		$(this).find(".Program_title").stop()
		.animate({ opacity:1 , height:"100%"}, "fast")
		.css("display","block");

	}, function() {
	    $(this).removeClass("hover");  
		$(this).find(".Program_title").stop()
		.animate({ opacity: 1,height:"40px"}, "fast")
		.css("display","block");
	});
		$(".user_casestyle").hover(function(){
			$(this).addClass("hover");
			$(this).find(".hd a").css("display","block");
			//$(this).stop().animate({bottom: "0px", opacity:1 , height:""}, "fast")
		},function(){
			$(this).removeClass("hover");
			$(this).find(".hd a").css("display","none");
			//$(this).stop().animate({bottom: "0px", opacity:1 , height:""}, "fast")  			
		}
	);
	//置顶图标显示
$('#top-back').hide();
$(window).scroll(function(){
	 if($(this).scrollTop() > 350){
		$("#top-back").fadeIn();
	 }
	 else{
		$("#top-back").fadeOut();
	 }
  });
$(".bannerNimg").css("background-image", "url(" + $(".bannerNimg").find("img").attr("src") + ")");
//置顶事件
function topBack(){
  $('body,html').animate({scrollTop:0},300);
}
});

$.fn.countTo = function (options) {
	"use strict";
	options = options || {};	
	return $(this).each(function () {
		// set options for current element
		var settings = $.extend({}, $.fn.countTo.defaults, {
			from:            $(this).data('from'),
			to:              $(this).data('to'),
			speed:           $(this).data('speed'),
			refreshInterval: $(this).data('refresh-interval'),
			decimals:        $(this).data('decimals')
		}, options);
		
		// how many times to update the value, and how much to increment the value on each update
		var loops = Math.ceil(settings.speed / settings.refreshInterval),
			increment = (settings.to - settings.from) / loops;
		
		// references & variables that will change with each update
		var self = this,
			$self = $(this),
			loopCount = 0,
			value = settings.from,
			data = $self.data('countTo') || {};
		
		$self.data('countTo', data);
		
		// if an existing interval can be found, clear it first
		if (data.interval) {
			clearInterval(data.interval);
		}
		data.interval = setInterval(updateTimer, settings.refreshInterval);
		
		// initialize the element with the starting value
		render(value);
		
		function updateTimer() {
			value += increment;
			loopCount++;
			
			render(value);
			
			if (typeof(settings.onUpdate) == 'function') {
				settings.onUpdate.call(self, value);
			}
			
			if (loopCount >= loops) {
				// remove the interval
				$self.removeData('countTo');
				clearInterval(data.interval);
				value = settings.to;
				
				if (typeof(settings.onComplete) == 'function') {
					settings.onComplete.call(self, value);
				}
			}
		}
		
		function render(value) {
			var formattedValue = settings.formatter.call(self, value, settings);
			$self.html(formattedValue);
		}
	});
};

$.fn.countTo.defaults = {
	from: 0,               // the number the element should start at
	to: 0,                 // the number the element should end at
	speed: 300,           // how long it should take to count between the target numbers
	refreshInterval: 100,  // how often the element should be updated
	decimals: 0,           // the number of decimal places to show
	formatter: formatter,  // handler for formatting the value before rendering
	onUpdate: null,        // callback method for every time the element is updated
	onComplete: null       // callback method for when the element finishes updating
};

function formatter(value, settings) {
	return value.toFixed(settings.decimals);
}


