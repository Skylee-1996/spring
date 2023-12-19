<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

    
    
<jsp:include page="../layout/header.jsp"></jsp:include>
<div class="container-md">
<h1>즐거운 게시판</h1>
<br>
    <table class="table">
  <thead>
    <tr>
      <th scope="col">#</th>
      <th scope="col">title</th>
      <th scope="col">writer</th>
      <th scope="col">reg_date</th>
    </tr>
  </thead>
  <tbody>
  	<c:forEach items="${list }" var="bvo">
    <tr>
      <th scope="row">${bvo.bno }</th>
      <td><a href="/board/detail?bno=${bvo.bno }">${bvo.title }</a></td>
      <td>${bvo.writer }</td>
      <td>${bvo.reg_date }</td>
      <td>${bvo.read_count }</td>
    </tr>
    </c:forEach>
  </tbody>
</table>

<!--페이지네이션  -->
<nav aria-label="Page navigation example">
  <ul class="pagination">
  <!--이전  -->
   <c:if test="${ph.prev }">
    <li class="page-item">
      <a class="page-link" href="/board/list?pageNo=${ph.startPage-1 }&qty=${ph.pgvo.qty}" aria-label="Previous">
        <span aria-hidden="true">&laquo;</span>
      </a>
    </li>
    </c:if>
    <!--페이지번호  -->
   <c:forEach begin="${ph.startPage }" end="${ph.endPage }" var="i">
    <li class="page-item"><a class="page-link" href="/board/list?pageNo=${i }&qty=${ph.pgvo.qty}">${i }</a></li>
    </c:forEach>
    <!-- 다음 -->
    <c:if test="${ph.next }">
    <li class="page-item">
      <a class="page-link" href="/board/list?pageNo=${ph.endPage+1 }&qty=${ph.pgvo.qty}" aria-label="Next">
        <span aria-hidden="true">&raquo;</span>
      </a>
    </li>
    </c:if>
  </ul>
</nav>
</div>
<script>
	const isDel = `<c:out value="${isDel}" />`;
	if(isDel == 1){
		alert("게시글이 삭제 되었습니다.");
	}
</script>
<jsp:include page="../layout/footer.jsp"></jsp:include>
    