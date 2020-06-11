/* 제이쿼리 이벤트 달아줌 */

$("#img__preview").on("change", function(e) {
		console.log(e.target.files);
		console.log(e.target.files[0].type);
		// file 0번지에 type이 image인지 아닌지 확인
		var f = e.target.files[0]; // 파일 객체
		if (!f.type.match("image*")) {
			alert("이미지 파일만 첨부할 수 있습니다.");
			$("#img__preview").val('');
			return;
		}
		
		// f.size = 1024*1024*2 = 2MB 넘어가면 return
		var maxFileSize = 1024 * 1024 * 2; 
		if(f.size > maxFileSize) {
			alert("이미지 파일은 2MB를 넘길 수 없습니다.");
			return;
		}
		
		var reader = new FileReader();

		reader.onload = function(e) { // reader.readAsDataURL(f)가 빨리 끝날 수도 있으니 순서 조정
			$("#img__wrap").attr("src", e.target.result);
		};

		reader.readAsDataURL(f); // 비동기 실행 → reader에 file을 복사함. 저장장치에 맡겨놓고 cpu는 다른 일을 할 수 있음!
		// ↑ 만약 동기 실행이라면 $("#img__wrap").attr("src", e.target.result - 사진 위치);

	});