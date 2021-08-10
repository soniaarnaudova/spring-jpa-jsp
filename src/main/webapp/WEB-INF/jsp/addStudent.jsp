<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

<link href="${jstlCss}" rel="stylesheet" />
<meta charset="ISO-8859-1">


<title>Add Student Page</title>
</head>
<body>
	<div class="container">
		<div>
			<h3 style="color: red">${message}</h3>
		</div>
        <div class="starter-template text-center" style="padding-left: 30px;">

        			<h3>Add Student</h3>

        		</div>
          <form class="needs-validation" novalidate method="POST" action="/addnew">
              <div class="form-row">
                <div class="col-md-4 mb-3">
                  <label for="validationCustom01">First name</label>
                  <input type="text" class="form-control" id="validationCustom01" placeholder="First name" name="firstName" required>
                  <div class="valid-feedback">
                    Looks good!
                  </div>
                </div>
                <div class="col-md-4 mb-3">
                  <label for="validationCustom02">Last name</label>
                  <input type="text" class="form-control" id="validationCustom02" placeholder="Last name" name="lastName" required>
                  <div class="valid-feedback">
                    Looks good!
                  </div>
                </div>
                <div class="col-md-4 mb-3">
                  <label for="validationCustomEmail">E-mail</label>
                  <div class="input-group">
                    <div class="input-group-prepend">
                      <span class="input-group-text" id="inputGroupPrepend">@</span>
                    </div>
                    <input type="text" class="form-control" id="validationCustomEmail" placeholder="Email" aria-describedby="inputGroupPrepend" name="emailId" required>
                    <div class="invalid-feedback">
                      Please choose a username.
                    </div>
                  </div>
                </div>
              </div>
              <div class="form-row">
                <div class="col-md-6 mb-3">
                  <label for="validationCustom03">Guardian Name</label>
                  <input type="text" class="form-control" id="validationCustom03" placeholder="guardianName" name="guardian.name" required>
                  <div class="invalid-feedback">
                    Please provide a valid name.
                  </div>
                </div>
                <div class="col-md-3 mb-3">
                  <label for="validationCustom04">Guardian E-mail</label>
                  <input type="text" class="form-control" id="validationCustom04" placeholder="guardianEmail" name="guardian.email" required>
                  <div class="invalid-feedback">
                    Please provide a valid email.
                  </div>
                </div>
                <div class="col-md-3 mb-3">
                  <label for="validationCustom05">Guardian Mobile</label>
                  <input type="text" class="form-control" id="validationCustom05" placeholder="guardianMobile" name="guardian.mobile" required>
                  <div class="invalid-feedback">
                    Please provide a valid mobile.
                  </div>
                </div>
              </div>

              <button class="btn btn-primary" type="submit">Submit form</button>
            </form>
         <div>
           <p><a href="${pageContext.request.contextPath}/index">Home page</a></p>
         </div>
            <script>
            // Example starter JavaScript for disabling form submissions if there are invalid fields
            (function() {
              'use strict';
              window.addEventListener('load', function() {
                // Fetch all the forms we want to apply custom Bootstrap validation styles to
                var forms = document.getElementsByClassName('needs-validation');
                // Loop over them and prevent submission
                var validation = Array.prototype.filter.call(forms, function(form) {
                  form.addEventListener('submit', function(event) {
                    if (form.checkValidity() === false) {
                      event.preventDefault();
                      event.stopPropagation();
                    }
                    form.classList.add('was-validated');
                  }, false);
                });
              }, false);
            })();
            </script>

    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>

</body>
</html>