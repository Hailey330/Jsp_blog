function replyDelete(replyId) {
	
	$.ajax({
	      type : "post",
	      url : "/blog/reply?cmd=deleteProc",
	      data : "replyId=" + replyId,
	      contentType : "application/x-www-form-urlencoded; charset=utf-8",
	      dataType : "text"
	   }).done(function(result) {
		   if(result == "1") {
	         alert("댓글 삭제 성공");
	         var replyItem = $("#reply-" + replyId);
	         replyItem.remove();
		   } else {
			   alert("댓글 삭제 실패");			   
		   }
	   }).fail(function(error) {
		   alert("댓글 삭제 실패");
	   });
   }

function replyWrite(boardId, userId) {
   if(userId === undefined){ // type 비교는 ===
      alert("로그인이 필요합니다.");
      return;
   }
   
   var data = {
      // 변수가 아니라 그냥 key : value 임
      boardId : boardId,
      userId : userId,
      content : $("#reply__write__form").val()
   };

   $.ajax({
      type : "post",
      url : "/blog/reply?cmd=writeProc",
      data : JSON.stringify(data),
      contentType : "application/json; charset=utf-8",
      dataType : "json"
   }).done(function(result) {
      console.log(result);
      // 정상 응답
      if (result == -1 || result == 0) {
         alert("댓글 작성 실패");
      } else {
         alert("댓글 작성 성공");
         $("#relpy__list").empty();
         console.log(result);
         renderReplyList(result, userId);
         $("#relpy__write__form").val("");
         
         location.reload();
      }
   }).fail(function(error) {
      alert("댓글 작성 실패");
   });
}

function renderReplyList(replyDtos, userId) {
   for(var replyDto of replyDtos) {
      $("#relpy__list").append(makeReplyItem(replyDto, userId));
   }
}

function makeReplyItem(replyDto, userId) {
	// reply-id 추가 시작
	var replyItem = `<li id="reply-${replyDto.reply.id} class="media">`;
	// reply-id 추가 끝
   if(replyDto.userProfile == null){
      replyItem += `<img src="/blog/image/userProfile.png" class="img-circle">`;   
   }else{
      replyItem += `<img src="${replyDto.userProfile}" class="img-circle">`;
   }

   replyItem += `<div class="media-body">`;
   replyItem += `<strong class="text-primary">${replyDto.username}</strong>`;
   replyItem += `<p>${replyDto.reply.content}</p>`;
   replyItem += `</div>`;
   // 휴지통 추가 시작
   replyItem += `<div class="m-2">`;
   if(replyDto.reply.userId == userId) {
   replyItem += `<i onclick="replyDelete(${replyDto.reply.id})" class="material-icons i__btn">highlight_off</i>`;
   }
   replyItem += `</div>`;
   // 휴지통 추가 끝
   replyItem += `</li>`;
   return replyItem;
} 