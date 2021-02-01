//音乐部分
	
    var music=new Array();//音乐数组  （换成数据库的形式）
	var music_pics=new Array();   //音乐图片（换成数据库的形式）
	var len;  //计算数组长度     （相当于在数据库里面做统计） 
	var num=0;   //音乐索引
    var str = 0;  //一个判断播放的缩影      0 没有单机其他页面的歌曲  /  1  单机了其他页面的歌曲
    var sumtime;                 //获取总时长
	var mid=[];
    let nowId;

    //默认切换的歌单
    $(function (){
		  
		  $.post("MusicServlet",{op:"finds"}, data =>{
			 
			  $.each(data,function(index,item){
				  music[item.mid-1]=item.musicname;
				  music_pics[item.mid-1]=item.mphoto;
				  
			  })
			 len=music.length;
		  },"json")
		 
	  })
    

    //自动调用
   	function next1(num){
   		var img = 'img/'+music_pics[num];
    	var times=window.setInterval('show()',10);
    	//定时器中show没有数、参数      而鼠标移入时有参数
    	function show(){
    		$("#container img").eq(0).removeAttr("src","img/pic_1.jpg");
			$("#container img").eq(0).attr("src",img);
			
			var bgimg=$("#container img").eq(0).attr("src");
			
			$("#music").eq(0).css("background","url("+ bgimg +") no-repeat");
			$("#music").eq(0).css("background-size","cover");
    	};
    	//开始轮播   开启定时器
    	function  start(){
    		times=setInterval(show,10);
    	}
   	}
    
    
    
     //finds页面的播放/暂停   点击事件
	function play2(id){ 
		nowId = id;
		var mn = $("#musicname li span").eq(1+6*(id-1)).text();
		var mp = $("#musicname li span img").eq(id-1).attr("src");
	//	alert(mp);
		
		parent.window.play1(1,mn,mp);
		$("#mid").text(id);
		alert(id);
	}

    //love页面的播放/暂停   点击事件
	function play3(id){ 
		nowId = id;
		var mn = $(".mn").eq(id).text();
		var mp = $(".ph img").eq(id).attr("src");
	//	alert(mp);
		parent.window.play1(1,mn,mp);
	}
	
    

//  //  start :  0 没有点歌的状态 / 1 点歌状态       img 背景图片和图片的切换
		function photo1(img){   
			
				$("#music").eq(0).css("background","url("+ img +") no-repeat");
				$("#container img").eq(0).removeAttr("src","img/pic_1.jpg");
				$("#container img").eq(0).attr("src",img);
		}
     
	//播放/暂停   点击事件
	function play1(id,mn,mp){  //音乐名、音乐图片
		var img=music_pics[num];
        var line=$("#line").text();   //进度条状态     0   还没有获取音乐总时长  /   1  获取了总时长
		var status = $("#static").text();  // 得到隐藏域的值        状态    0   没有赋音乐  /1  付了音乐
		var status1,line1;
		status1=parseInt(status);
		line1=parseInt(line);
		var audio = $("#audio")[0];
		

		 if(status1 == 0){
        	$('audio').eq(0).attr('src','../musicname/'+ music[num] +'.mp3');
            $("#doplay").eq(0).text("正在播放："+music[num]);   //正在播放
		    $('.f_l a img').eq(0).attr('src', img);
		    $("textarea").load('歌词/'+ music[num] +'.txt');
		    $.getScript("js/musicword.js");
        	$("#static").text('1');
        	getmytime();                             //得到时长
        	
        }
		
   	 if(id == 0){
   	 	
	   if(audio.paused){
//	     $(".music_program").start();
		 $("#btn li span").eq(1).removeAttr('class','glyphicon glyphicon-play');
		 $("#btn li span").eq(1).attr("class","glyphicon glyphicon-pause");
		 if(line1 == 1){          //已经获取了总时长
		 	$(".music_program").animate({width:'0px',opactiy:'show'},(sumtime-audio.currentTime)*1000);   //进度条
		 }
		 
		 audio.play();
		
		
	 }else if(audio.played){
		audio.pause();
		sumtime=audio.duration;
		$("#line").text('1');
		$(".music_program").stop().animate();     //停止进度条
		$("#btn li span").eq(1).removeAttr('class','glyphicon glyphicon-pause');
		$("#btn li span").eq(1).attr("class","glyphicon glyphicon-play");
	 }
   }else if(id == 1){    //单机其他页面的音乐      mp/mn 是从其他函数传过来的参数
   	  //alert(str);
   	  img=mp;
   	  if(audio.paused){
   	  	$(".music_program").stop();
 	  	$(".music_program").animate({width:'100%',opactiy:'show'},1);   //进度条(1秒补状态)
		$('audio').eq(0).attr('src','../musicname/'+ mn +'.mp3');
		$('.f_l a img').eq(0).attr('src',mp);
		$("#doplay").eq(0).text("正在播放："+mn);
		$("textarea").load('歌词/'+ mn +'.txt');   //获取歌词
		$("#btn li span").eq(1).removeAttr('class','glyphicon glyphicon-play');
		$("#btn li span").eq(1).attr("class","glyphicon glyphicon-pause");
		 getmytime(); 
		audio.play();
		
	}else if(audio.played){
		$(".music_program").stop();
 	  	$(".music_program").animate({width:'100%',opactiy:'show'},1);   //进度条(1秒补状态)
		$('audio').eq(0).attr('src','../musicname/'+ mn +'.mp3');    //音乐名
		$('.f_l a img').eq(0).attr('src',mp);       //音乐图片
		$("#doplay").eq(0).text("正在播放："+mn);
		 getmytime(); 
		audio.play();
   	 }
   }
	

   }
	
	
	//上一首
	prev.onclick = function(){
		num = (num + len - 1) % len;
		$(".music_program").stop();
		$(".music_program").animate({width:'100%',opactiy:'show'},1);   //进度条(1秒补状态)
	    $('audio').eq(0).removeAttr('src','music/小咪 - 即兴.mp3');          //移除上一首音乐(这个可以不动)
	    $('audio').eq(0).attr('src','../musicname/'+ music[num] +'.mp3');        //音乐切换（数据库中的音乐名）
	    $("#btn li span").eq(1).attr("class","glyphicon glyphicon-pause");   //暂停图标切换
	    $('.f_l a img').eq(0).attr('src', music_pics[num]);          //图片切换（同下）
	    $("#doplay").eq(0).text("正在播放："+music[num]);                      //正在播放
        str = 0;
        getmytime();
        next1(num);
        audio.play();
		
	}
	
	//下一首
	next.onclick = function(){
//		num=2;
//		alert(music[2]);
      var micstatic=parseInt($("#miscstatic").text());
		
		if(micstatic == 1){
			 num = (num + 1) % len;
		}else if(micstatic == 2){
			num=Math.round(Math.random()*(len-1));
		}else if(micstatic == 3){
			
			$(".music_program").animate({width:'100%',opactiy:'show'},1);   //进度条(1秒补状态)
		}
	    $(".music_program").stop();
	    $(".music_program").animate({width:'100%',opactiy:'show'},1);   //进度条(1秒补状态)
	    $('audio').eq(0).removeAttr('src','music/小咪 - 即兴.mp3');
	    $('audio').eq(0).attr('src','../musicname/'+ music[num] +'.mp3');
	    $("#btn li span").eq(1).attr("class","glyphicon glyphicon-pause");
	    $('.f_l a img').eq(0).attr('src', music_pics[num]);
	    $("#doplay").eq(0).text("正在播放："+music[num]);
	    str = 0;
	    getmytime();
	    next1(num);
        audio.play();
		
	}
	
	
	//播放切换
    function choose1(id){
		if(id == 1){          //循环播放
			$("#audio").removeAttr('loop','loop');       //不管有没有单曲循环，先做一个移除操作
			$("#choose").removeAttr('class','glyphicon glyphicon-retweet');
			$("#choose").attr('class','glyphicon glyphicon-retweet');
			$("#miscstatic").text('1');
		}else if(id == 2){    //随机播放
			$("#audio").removeAttr('loop','loop');       //不管有没有单曲循环，先做一个移除操作
			$("#choose").removeAttr('class','glyphicon glyphicon-retweet');
			$("#choose").attr('class','glyphicon glyphicon-random');
			$("#miscstatic").text('2');
		}else if(id == 3){    //单曲循环
			$("#choose").removeAttr('class','glyphicon glyphicon-retweet');
			$("#choose").attr('class','glyphicon glyphicon-repeat');
			$("#miscstatic").text('3');
			$("#audio").attr('loop','loop');
			$(".music_program").animate({width:'100%',opactiy:'show'},1);   //进度条(1秒补状态)
		}
	}

	//自动播放
	audio.addEventListener('ended',function(){
	   next.onclick();
	//   next1();      //调用切换海报图片
    },false);