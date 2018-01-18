<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, width=device-width" />
    <title>Login Demo - Kakao JavaScript SDK</title>
    <script src="//developers.kakao.com/sdk/js/kakao.min.js"></script>
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$('#btn2').on('click',function(){
		if($('#mem_id').val()==""){
			alert('아이디를 입력하세요');
			return false;
		}
		else if($('#mem_pw').val()==""){
			alert('비밀번호를 입력하세요');
			return false;
		}
		//else{
			//location.href = "check?id=" + $('#mem_id').val()+"pw"+$('#mem_pw').val();

		//}
	});
});
</script>
</head>
<body>
	<form class="ui container" action="check">
		<h1>회원가입</h1>
		<div class="ui form segment">
			<div class="field">
				<label for="id">아이디</label>
				<input id="mem_id" name="mem_id" placeholder="아이디" type="text">
			</div>

				<div class="field">
					<label for="password">비밀번호</label>
					<input id="mem_pw" name="mem_pw" type="password">
				</div>
			
			<button class="ui blue button" type="submit" id="btn2">Submit</button>
			 <a id="kakao-login-btn"></a>
		</div>
	</form>>
	
	<script type='text/javascript'>
        //<![CDATA[
        // 사용할 앱의 JavaScript 키를 설정해 주세요.
        Kakao.init('331f6e91bdb4a956167313811ffb0d23');
        // 카카오 로그인 버튼을 생성합니다.
        Kakao.Auth.createLoginButton({
            container: '#kakao-login-btn',
            success: function (authObj) {
            	Kakao.API.request({
            		url:'/v1/user/me',
            		success:function(res){
                    alert(res.properties.nickname+'님 로그인 되었습니다.');
                    //location.href="login.do?name="+res.properties.nickname;
                   location.href="loginsuccess?kid="+res.kaccount_email
   				    persistAccessToken : false;
            		},
            	})
				//persistAccessToken: false,
            },
            fail: function (err) {
                alert(JSON.stringify(err));
            }
        });
      //]]>
    </script>
</body>
</html>