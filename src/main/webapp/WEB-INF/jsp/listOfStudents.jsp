<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

<link href="${jstlCss}" rel="stylesheet" />
<meta charset="ISO-8859-1">


<title>All Student Page</title>
</head>
<body>
	<div class="container">
		<div>
			<h3 style="color: red">${message}</h3>
		</div>

		<div class="starter-template text-center" style="padding-left: 30px;">

			<h2>All Students:</h2>

			 <div class="container">

              <p>Here you can see the list of the students, edit them, remove or update.</p>
		        <table class="table table-striped">
                  <thead>
                    <tr>
                      <th scope="col">Student ID</th>
                      <th scope="col">First Name</th>
                      <th scope="col">Last Name</th>
                      <th scope="col">actions</th>

                    </tr>
                  </thead>
                  <tbody>
                   <c:forEach var="student" items="${list}">
                    <tr>
                      <td>${student.studentId}</td>
                      <td>${student.firstName}</td>
                      <td>${student.lastName}</td>
                      <td>
                        <a href="${pageContext.request.contextPath}/edit/${student.studentId}">Edit</a><br>
                        <a href="${pageContext.request.contextPath}/delete/${student.studentId}">Delete</a><br>
                      </td>
                    </tr>
                    </c:forEach>
                  </tbody>
                </table>
                <div>
                   <p><a href="${pageContext.request.contextPath}/index">Home page</a></p>
                </div>
</div>
   <!-- Optional JavaScript -->
           <!-- jQuery first, then Popper.js, then Bootstrap JS -->
           <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
           <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
           <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>

 </body>
</html>