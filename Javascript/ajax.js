/* ------------------------------------------
 * Deverloper : Tae-ho Lee
 * E-mail : developdestroyer@gmail.com
 * Date : 2018-07-24
------------------------------------------- */


//ajax로 요청하고 json데이터 받기
  function login_request(){
      $.ajax({
          url: "/login_req",
          type: "post",
          data: {
              "user_id": document.getElementById("exampleInputEmail1").value,
              "user_pw" : document.getElementById("exampleInputPassword1").value
          },
          dataType: "json",
          error: function(xhr, ajaxOptions, thrownError){

          },
          success: function(data){
              if(data.result == 'success') {
					//alert("로그인 성공 : " + data.result_code);
					location.href="/";
              
              }else{
					alert("로그인에 실패하였습니다.");	                	
              }
          }
      });
	

	}
	
//멀티타입파트 방식으로 전송하기, ajax multipart/form-data
    function file_mode_regist(){
    	
    	var formData = new FormData();
    	formData.append("csvFile", $("input[name=csvFile]")[0].files[0]);
    	$.ajax({
    	url: '/regist/csv',
    	data: formData,
    	processData: false,
    	contentType: false,
    	type: 'POST',
    	success: function(data){
    		if(data.result == 'success')
	    		alert("콘텐츠 등록이 완료 되었습니다.\n");
    	 }
    	});
    }
	
	
	