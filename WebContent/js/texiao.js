
			
	    //页面跳转	
	   function showpage(obj,page){
        $("#rightmain").attr("src",page);
        $("#side li").removeClass("on");
        $(obj).parent().addClass("on");
    }
	   
//定义图片编号
    	var index=1;
    	//定时器
    	var times=window.setInterval('show()',1500);
    	//定时器中show没有数、参数      而鼠标移入时有参数
    	function show(id){
    		//判断参数
    		if(id==undefined){
    			//意味着是定时器的内容
    			index++;
    			//循环1-6并判断
    			if(index==6){
    				index=1;
    			}
    			}else{
    				//意味着是鼠标移入事件含参数
    				index=id;
    				clearInterval(times);   //清除定时器
    			}
    			
    			
    			//轮播效果--jQuery
    			$("#pic").attr("src","img/pic_"+index+".jpg");
    			//编号样式变化      //$("tr:eq(1)")
    			$("#scroll_number li").attr("class","scroll_number_out");
    			//第一种选择元素方法
      			  $("#scroll_number li").eq(index-1).attr("class","scroll_number_over");
                //第二种选择元素方法方法
                //$('#scroll_number li:eq('+(index-1)+')').attr("class","scroll_number_over");
               
    	};
    	//开始轮播   开启定时器
    	function  start(){
    		times=setInterval(show,2000);
    	}
    	
     //显示隐藏密码	
    $(".password").on("click", ".glyphicon-eye-close", function () {
        $(this).removeClass("glyphicon-eye-close").addClass("glyphicon-eye-open");
        $(this).prev().attr("type", "text");
    });
     
    $(".password").on("click", ".glyphicon-eye-open", function () {
        $(this).removeClass("glyphicon-eye-open").addClass("glyphicon-eye-close");
        $(this).prev().attr("type", "password");
    });
    
function getmytime(){
        //获取当前audio
        var aud = $("#audio")[0];
        //局部load()一下
       // aud.load();

    
     	aud.oncanplay = function () {  
           // console.log("myVid.duration=="+aud.duration);
            var _duration = transTime(aud.duration);
            var secs=transec(aud.duration);
            console.info("时长："+_duration);
            $("#audiotime").html(_duration);    //渲染时长
            $(".music_program").animate({width:'0px',opactiy:'show'},secs*1000);   //进度条
           }            
}
//转换音频时长分/秒显示
    function transTime(time) {
        var duration = parseInt(time);
        var minute = parseInt(duration/60);
        var sec = duration%60+'';
        var isM0 = ':';
        if(minute == 0){
            minute = '00';
        }else if(minute < 10 ){
            minute = '0'+minute;
        }
        if(sec.length == 1){
            sec = '0'+sec;
        }
        return minute+isM0+sec;
    }

function transec(time) {
        var duration = parseInt(time);
        var minute = parseInt(duration/60);
        var sec = duration%60+'';
        var secs;
        
        secs=minute*60+parseInt(sec);
        
        return secs;
}