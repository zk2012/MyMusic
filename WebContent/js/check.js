/**
 * 
 */
$(function(){
	
	$.post("UserServlet",{op:"check"}, data =>{
		
		var str = '';
		
		if(data.code == 200){
			str += '<li><img src="'+data.obj.uphoto+'"></li><li id="uname">'+data.obj.uname+'</li>';
			//str1 += '<span><img class="img2" src="'+data.obj.uphoto+'"></span><span class="name">'+data.obj.uname+'</span>';
		}else{
			str += '<li><img src="img/周杰伦.jpg"></li><li id="uname">张三</li>';
			//str1 += '<span><img class="img2" src="img/周杰伦.jpg"></span><span class="name">张三</span>';
		}
		
		$("#log").html("").append($(str));
		//$("#log1").html("").append($(str1));
	},"json");
	
})