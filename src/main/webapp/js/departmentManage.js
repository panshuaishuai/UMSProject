
 $(function(){
    	// 局部加载
    	 //点击部门管理加载到查询页面
	
         $(".menu-content-panel").find("div").on("click", function() {
            $this=$(this);
             $this.css("background", "#287DB6"); 
             $this.siblings("div").css("background", "#34495E");
        });
         
    });