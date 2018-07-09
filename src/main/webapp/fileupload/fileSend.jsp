<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/resources/taglibs.jsp" %>



<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8"/>
    <title>欢迎-admin</title>
    
    

	<link rel="stylesheet" href="/resources/style.css" />
	<!-- 导入jquery，可以进行ajax请求 -->
	<script type="text/javascript" src="/resources/jquery.js"></script> 
	<!-- 导入调用方法的js -->
	<script type="text/javascript" src="/fileupload/fileup.js"></script> 
</head>

<body>

	<script type="text/javascript">
		var serverName = '<%=request.getServerName()%>';
		var serverPort = '<%=request.getServerPort()%>';
		var contextPath= '<%=request.getContextPath()%>';
		var _webRootPath = 'http://' + serverName + ':' + serverPort + "/";//防止跨域请求，保证同源策略
	
	</script>

	<!-- 文件上传 -->
	<div class="modal fade in" id="import-controlData-modal" tabindex="-4">
		<div class="modal-body">
			<form class="form-horizontal" id="import-controlData-form" enctype="multipart/form-data">
				<div class="form-group">
					<div class="col-sm-9">
						<input type="file" id="file-need-upload" name="uploadfile">
					</div>
				</div>
			</form>
		</div>
		<div class="modal-footer">
			<button type="button" class="btn btn-md" id="import-controlData-button"
				onclick="importControlData()">上传</button>
		</div>
	</div>
	
</body>
</html>



