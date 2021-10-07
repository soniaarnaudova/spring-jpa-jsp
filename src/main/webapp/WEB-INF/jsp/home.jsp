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
    		<div class="starter-template" style="padding-left: 30px;">
                     <p>
                     <a href="/add">Add new student</a>
                     </p>
                     <br/>
                     <p>
                     <a href="/list">List all students</a>
                     </p>
                     <br/>
                     <p>
                     <a href="/download/students">Download Student Records(student.xls format)</a>
                     </p>
                </div>
                <p/>
            <hr/>
        <div class="starter-template" style="padding-left: 30px;">
            <p>Display existing students by e-mail address - read from xls/xlsx file.</p>
    	    <p style="color: red;">The excel file has a format like this:
    				fileName.xlsx or fileName.xls</p>
    	    <p>Select a file to be processed:</p>
    	    <form action="/displayList" method="post"
                				enctype="multipart/form-data">
                	<div class="container">
                	    <input type="file" name="file" size="50" required /> <br /> <br />
                	    <input type="submit" value="OK" />
                	</div>
             </form>
         </div>
		
	<hr/>
        <div class="starter-template" style="padding-left: 30px;">
                <p>Find existing students by e-mail address.</p>
                <form action="/email" method="post">
                            	<div class="container">
                            		<input type="text" name="email" size="50" required /> <br /> <br />
                            		<input type="submit" value="OK" />
                            	</div>
                </form>
         </div>
     </div>

	
</body>
</html>
