<%@page import="com.cos.blog.dto.DetailResponseDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../include/nav.jsp"%>

<div class="container">
	<!-- 자바 스크립트가 들어갈 부분이 아니면 javascript: 사용  -->
	<%@ include file="../include/goBack.jsp"%>
	
	<c:if test="${sessionScope.principal.id == detailDto.boardDto.board.userId}">
		<a href="/blog/board?cmd=update&id=${detailDto.boardDto.board.id}" class="btn btn-warning">수정</a>
		<button class="btn btn-danger" onclick="deleteById(${detailDto.boardDto.board.id})">삭제</button>
	</c:if>

	<br />
	<br />
	<h6 class="m-2">
		작성자 : <i>${detailDto.boardDto.username}</i> 조회수 : <i>${detailDto.boardDto.board.readCount}</i>
	</h6>
	<br />
	<h3 class="m-2">
		<b>${detailDto.boardDto.board.title}</b>
	</h3>
	<hr />

	<div class="form-group">
		<div class="m-2">${detailDto.boardDto.board.content}</div>
	</div>


	<hr />
	<!-- 댓글 박스 -->
	<div class="row bootstrap snippets">
		<div class="col-md-12">
			<div class="comment-wrapper">
				<div class="panel panel-info">
					<div class="panel-heading m-2">
						<b>Comment</b>
					</div>
					<div class="panel-body">

						<textarea id="reply__write__form" class="form-control" placeholder="write a comment..." rows="3"></textarea>
						<br>
						<button onclick="replyWrite(${detailDto.boardDto.board.id}, ${sessionScope.principal.id})" class="btn btn-primary pull-right">댓글쓰기</button>
						<div class="clearfix"></div>
						<hr />

						<!-- 댓글 리스트 시작-->
						<ul id="reply__list" class="media-list">

							<c:forEach var="replyDto" items="${detailDto.replyDtos}">
								<!-- 댓글 아이템 -->
								<li class="media"><img onerror="this.src='/blog/image/userProfile.png'" src="${replyDto.userProfile}" alt="" class="img-circle">
									<div class="media-body">
										<strong class="text-primary">${replyDto.username}</strong>
										<p>
											${replyDto.reply.content}
										</p>
									</div></li>
							</c:forEach>
						</ul>
						<!-- 댓글 리스트 끝-->
					</div>
				</div>
			</div>

		</div>
	</div>
	<!-- 댓글 박스 끝 -->

</div>





<script>
	function deleteById(boardId) {
		$.ajax({
			type: "POST",
			url: "/blog/board?cmd=delete&id="+boardId,
			dataType: "text"
		}).done(function(result){
			console.log(result);
			if(result == 1) {
				alert("삭제 성공");
				location.href="/blog/index.jsp";				
			} else {
				alert("삭제 실패");
			}
		}).fail(function(error){
			console.log(error);
			console.log(error.responseText);
			console.log(error.status);
			alert("서버 오류");
		});
	}

</script>


<script src="/blog/js/reply.js"></script>
<%@ include file="../include/footer.jsp"%>