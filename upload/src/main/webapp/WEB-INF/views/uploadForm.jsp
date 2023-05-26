<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- form 안 file 요소가 존재 시 enctype 변경 필수 -->
	<form action="" method="post" enctype="multipart/form-data">
		<div>
			<label for="name">name</label>
			<input type="text" name="name" id="name" />
		</div>
		<div>
			<label for="file">file</label>
			<!-- multiple : 파일을 여러개 업로드할 수 있는 요소 
				 accept : 첨부할 수 있는 확장자를 정할 수 있음
			-->
			<input type="file" name="file" id="file" multiple accept="image/*, .txt"/>
		</div>
		<div>
			<button type="submit">submit</button>
		</div>
	</form>	
</body>
</html>