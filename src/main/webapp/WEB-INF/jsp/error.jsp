<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css"
	href="webjars/bootstrap/3.3.7/css/bootstrap.min.css" />
<link href="${jstlCss}" rel="stylesheet" />
<meta charset="ISO-8859-1">


<title>Home Page</title>
</head>
<body>
	<div class="form-group">
    		<div>
    			<h3 style="color: red">${message}</h3>
    		</div>
    		<div>
                <p><a href="${pageContext.request.contextPath}/index">Home page</a></p>
            </div>

    </div>
</body>
</html>