//导入文件：批量上传控制器数据
function importControlData(){
	
	var jsonData = new FormData($("#import-controlData-form")[0]); 
	jsonData.append("controlId", "121");//模拟其他参数
	
	$.ajax({
		url: _webRootPath+"getfile/io",//使用这个_webRootPath，防止跨域请求
		
		type : 'POST',
		dataType: 'json',
		data: jsonData,
        processData:false,
        contentType:false,
        
		success:function(data){
			if (data.resultCode=="success") {
				alert(1);
				document.writeln(data.resultCode+","+data.resultDesc);
			}else{//异常被BaseContoller中的ExceptionHandler捕获后，执行这里
				alert(2);
				document.writeln(data.resultCode+","+data.resultDesc);
			}
		},
		error: function (data) {//抛异常到这里
			alert(3);
			document.writeln(data.resultCode+","+data.resultDesc);
		},
		
	});
}



