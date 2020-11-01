/**
 * 
 */
$(function(){
	
	$.post("UserServlet",{op:"check"}, data =>{
		
		
		var str = '';  //love界面的检查登录
		 
		if(data.code == 200){
			str += '<span><img class="img2" src="'+data.obj.uphoto+'"></span><span class="name">'+data.obj.uname+'</span>';
		}else{
			str += '<span><img class="img2" src="img/周杰伦.jpg"></span><span class="name">张三</span>';
			str += '<span hidden="hidden" id="uuid">'+ data.obj.uuid +'</span>';     // 通过隐藏域的形式获取用户的uuid
		}
		
		$("#log1").html("").append($(str));
	},"json");
	
	FindMusic();
	
	
})