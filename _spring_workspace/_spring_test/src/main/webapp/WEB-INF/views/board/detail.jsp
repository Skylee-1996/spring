<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="../layout/header.jsp"></jsp:include>
    <div class="container-md">
     <h1>Board Detail Page</h1>
   <div class="mb-3">
  <label for="bno" class="form-label">Bno</label>
  <input type="text" class="form-control" name="bno" id="bno" value="${bvo.bno }" readonly="readonly">
	</div>
   <div class="mb-3">
  <label for="title" class="form-label">Title</label>
  <input type="text" class="form-control" name="title" id="title" value="${bvo.title }" readonly="readonly">
	</div>
<div class="mb-3">
  <label for="writer" class="form-label">Writer</label>
  <input type="text" class="form-control" name="writer" id="writer" value="${bvo.writer }" readonly="readonly">
</div>
<div class="mb-3">
  <label for="reg_date" class="form-label">reg_date</label>
  <span class="badge text-bg-primary">조회수${bvo.read_count }</span>
  <input type="text" class="form-control" name="reg_date" id="reg_date" value="${bvo.reg_date }" readonly="readonly">
</div>
<div class="mb-3">
  <label for="content" class="form-label">Content</label>
  <textarea class="form-control" id="content" name="content" rows="3" readonly="readonly">${bvo.content }
  </textarea>
  
 <a href="/board/list"><button type="button" class="btn btn-primary">List</button></a>
 <a href="/board/modify?bno=${bvo.bno }"><button type="button" class="btn btn-success">Modify</button></a>
 <a href="/board/remove?bno=${bvo.bno }"><button type="button" class="btn btn-danger">delete</button></a>
 
 <br>
 <hr>
 <!--댓글등록라인-->
 <div class="input-group mb-3">
 	<span class="input-group-text" id="cmtWriter">${ses.id }</span>
 	<input type="text"  class="form-control" id="cmtText" placeholder="Add Comment...">
 	<button class="btn btn-outline-secondary" type="button" id="cmtAddBtn">댓글 등록</button>
 </div>
 
 <br>
 
</div>
 <!--댓글표시라인-->
 <div class="accordion" id="accordionExample">
  <div class="accordion-item">
    <h2 class="accordion-header">
      <button class="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
       no. cno, writer, reg_date
      </button>
    </h2>
    <div id="collapseOne" class="accordion-collapse collapse show" data-bs-parent="#accordionExample">
      <div class="accordion-body">
        <strong>Add Comment..</strong>
      </div>
    </div>
  </div>
</div>
    
   </div>
 
 
<br>
<script type="text/javascript">
 const bnoVal = `<c:out value="${bvo.bno}"/>`
</script>

<script src="/resources/js/boardComment.js"></script>

 
    
<jsp:include page="../layout/footer.jsp"></jsp:include>