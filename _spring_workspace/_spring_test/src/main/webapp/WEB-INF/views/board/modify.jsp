<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<jsp:include page="../layout/header.jsp"></jsp:include>
    <div class="container-md">
     <h1>Board modify Page</h1>
     <c:set value="${boardDTO.bvo }" var="bvo"/>
  <form action="/board/modify" method="post">
   <div class="mb-3">
  <label for="bno" class="form-label">Bno</label>
  <input type="text" class="form-control" name="bno" id="bno" value="${bvo.bno }" readonly="readonly">
	</div>
   <div class="mb-3">
  <label for="title" class="form-label">Title</label>
  <input type="text" class="form-control" name="title" id="title" value="${bvo.title }" >
	</div>
<div class="mb-3">
  <label for="writer" class="form-label">Writer</label>
  <input type="text" class="form-control" name="writer" id="writer" value="${bvo.writer }" readonly="readonly">
</div>
<div class="mb-3">
  <label for="reg_date" class="form-label">reg_date</label>
  <input type="text" class="form-control" name="reg_date" id="reg_date" value="${bvo.reg_date }" readonly="readonly">
</div>
<div class="mb-3">
  <label for="content" class="form-label">Content</label>
  <textarea class="form-control" id="content" name="content" rows="3" >${bvo.content }
  </textarea>
  
  <!--파일 표시라인  -->
  <hr>
  <br>
  이미지
  <c:set value="${boardDTO.flist }" var="flist"/>
  <div>
  	<ul class="list-group">
  		<!--파일 개수만큼 li를 추가하여 파일을 표시, 타입이 1인경우만 표시 -->
  		<!--li-> div -> img 그림표시  
  			     div -> 파일이름, 작성일, span size
  		-->
  		<c:forEach items="${flist }" var="fvo">
  			<li class="list-group-item">
  				<c:choose>
  					<c:when test="${fvo.file_type > 0 }">
  						<div>
  						<!-- /upload/save_dir/uuid_file_name  -->
  							<img alt="" src="/upload/${fn:replace(fvo.save_dir, '\\', '/') }/${fvo.uuid}_th_${fvo.file_name}">
  						</div>
  					</c:when>
  					<c:otherwise>
  						<div>
  							<!--아이콘 같은 모양 하나 가져와서 넣기  -->
  						</div>
  					</c:otherwise>
  				</c:choose>
  				<div>
  					<!--div => 파일이름, 작성일, span size  -->
  					 <div>${fvo.file_name }</div>
  					 ${fvo.reg_date }
  				</div>
  				<span>${fvo.file_size }Byte</span>
  				
  				<button type="button" data-uuid="${fvo.uuid }" class="file-x">X</button>
  			</li>
  		</c:forEach>
  		
  	</ul>
  </div>
  
  <!-- file 입력 라인 추가 -->
<div class="mb-3">
  <label for="file" class="form-label">fileUpload</label>
  <input type="file" class="form-control" name="files" id="file" multiple="multiple" style="display : none">
    <button type="button" class="btn btn-primary" id="trigger">FileUpload</button>
</div>
<!-- 파일목록 표시라인  -->
<div class="mb-3" id="fileZone">
	
</div>
  
 <a><button type="button" class="btn btn-primary">List</button></a>
<button type="submit" class="btn btn-success">Modify</button>
</div>
</form>
 </div>
 
<script src="/resources/js/boardModify.js"></script>
<script src="/resources/js/boardRegister.js"></script>
 
<jsp:include page="../layout/footer.jsp"></jsp:include>