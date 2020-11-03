/**
 * 
 */
/**
 * 
 */
$(function(){
	
	$.post("UserServlet",{op:"check"}, data =>{
		
		showUid(data.obj.uuid);
		
	},"json");
	
})