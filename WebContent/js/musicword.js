
function getword(){
var myMusic = document.getElementById("audio");
//var btn = document.getElementsByClassName("play")[0];
var con = document.getElementsByClassName("content")[0];
var txt = document.getElementById("txt");
//var mark = true;

//btn.onclick = function(){//点击播放
//	if (mark)
//	{
//		myMusic.play();//播放音乐
//		mark = false;
//		this.className += ' rotate';
//	}else{
//		myMusic.pause();//暂停音乐
//		mark = true;
//		this.className = "play";  
//	}
//}

   var lrc = txt.value;//获取到歌词

   var lrcArr = lrc.split("[");//清楚[

   var html = '';
for (var i=0;i<lrcArr.length ;i++)
{
	
	var arr = lrcArr[i].split("]");//清楚]
	var time = arr[0].split(".");//清楚.
	var text = arr[1];//获取歌词部分
	var timer = time[0].split(":");//去除时间中的:
	var ms = timer[0]*60 + timer[1]*1;//时间转换为秒钟
	if (text)
	{
		
		html += "<p id="+ms+">"+text+"</p>";
	}
	con.innerHTML = html;
}
//歌词同步
var num = 0;
var oP = con.getElementsByTagName("p");//通过标签名获取p
myMusic.addEventListener("timeupdate",function(){
	var curTime = parseInt(this.currentTime);//获取当前播放的时间
	if (document.getElementById(curTime))
	{
		for (var i=0;i<oP.length ;i++ )
		{
			oP[i].style.cssText = "color:#ccc;font-size:12px;";
		}
		document.getElementById(curTime).style.cssText = "color:#F26E6F;font-size:18px;";
		if (oP[7+num].id == curTime)//只能判断成功一次
		{
			con.style.top = -20*num +"px";
			num++;//num = num+1
		}
	}
});

//myMusic.addEventListener("ended",function(){
//	btn.className = "play"; 
//	mark = true;
//	this.currentTime = 0;
//	con.style.top = 0;
//	num = 0;
//})
}
