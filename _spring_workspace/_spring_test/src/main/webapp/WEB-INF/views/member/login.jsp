<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="../layout/header.jsp"></jsp:include>
  <div class="container-md">
     <h1>Login Page</h1>
     <form action="/member/login" method="post">
       	<div class="mb-3">
		  <label for="id" class="form-label">ID</label>
		  <input type="text" class="form-control" name="id" id="id" placeholder="id...">
		</div>
		 	<div class="mb-3">
		  <label for="pw" class="form-label">PassWord</label>
		  <input type="password" class="form-control" name="pw" id="pw" placeholder="pw...">
		</div>
		<button type="submit" class="btn btn-primary">Login</button>
     </form>
   </div>

<jsp:include page="../layout/footer.jsp"></jsp:include>