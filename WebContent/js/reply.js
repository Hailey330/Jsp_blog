function replyWrite(boardId, userId) {
		var data = {
			boardId: boardId,
			userId: userId,
			content: $("#reply__write__form").val()
		};

		$.ajax({
			type: "post",
			url: "/blog/reply?cmd=writeProc",
			data: JSON.stringify(data),
			contentType: "application/json; charset=utf-8",
			dataType: "json"
		}).done(function(result){
			// 정상 응답
			if(result == -1 || result == 0) {
				alert("댓글 작성 실패");
			} else {
				alert("댓글 작성 성공");
			// 1. reply__list 를 찾아서 내부를 비우기 - 1 응답 받았을 때 
				$("#reply__list").empty();
				console.log(result);
				renderReplyList(result);
				$("#reply__write__form").val("");
			}
			// 2. ajax 재호출 findAll()
			// 3. reply__list 를 찾아서 내부에 채워주기
		}).fail(function(error){
			alert("댓글 작성 실패");
		});

	}