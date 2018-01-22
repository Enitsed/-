/*jslint browser: true*/
/*global $, document*/

$(document).ready(function() {
	"use strict";

	// 영화 상세보기
	$('#movie_modal').on('click', function() {
		$('.ui.modal.movie').modal('show');
	})
	
	//아이디 중복체크
	$('#checkId').on('click',function(){
		$.ajax({
			type:'POST',
			dataType:'text',
			url:'chkId',
			data:'mem_id='+$('#id').val(),
			success:function(rs){
				chkId(rs);
			},
			error:function(request,status,error){
	            alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
	           }
		});
	});
	
	// 별점
	$('.ui.rating')
	  .rating();

	// 카카오톡 로그인
	$('#kakaoLoginImage').on('mouseenter', function() {
		$(this).prop('src', 'resources/images/loginBtnHover.png');
		$(this).css('cursor', 'pointer');
	})

	$('#kakaoLoginImage').on('mouseleave', function() {
		$(this).prop('src', 'resources/images/loginBtn.png');
		$(this).css('cursor', 'default');
	})

	// 로그인 창 띄우기
	$('#loginBtn').on('click', function() {
		$('.ui.modal.login_modal').modal('show');
	});

	// 로그인 창 닫기
	$('#closeBtn').on('click', function() {
		$('.ui.modal.login_modal').modal('hide');
	});

	// 메인 페이지 카드
	$('.special.cards .image.main_movie').dimmer({
		on : 'hover'
	});

	// 메뉴 드랍 다운
	$('.ui.dropdown').dropdown();

	// 로그인 유효성 검사
	$('.ui.form#loginForm').form({
		on : 'blur',
		fields : {
			id : {
				identifier : 'id',
				rules : [ {
					type : 'empty',
					prompt : '아이디를 입력해주세요.'
				} ]
			},
			password : {
				identifier : 'password',
				rules : [ {
					type : 'empty',
					prompt : '비밀번호를 입력하세요.'
				}, {
					type : 'minLength[6]',
					prompt : '비밀번호 : 최소 {ruleValue} 글자 이상 입력 하세요.'
				} ]
			}
		}
	});

	// 회원가입 유효성 검사
	$('.ui.form#signUpForm').form({
		on : 'blur',
		fields : {
			id : {
				identifier : 'id',
				rules : [ {
					type : 'empty',
					prompt : '아이디를 입력해주세요.'
				} ]
			},
			password : {
				identifier : 'password',
				rules : [ {
					type : 'empty',
					prompt : '비밀번호를 입력하세요.'
				}, {
					type : 'minLength[6]',
					prompt : '비밀번호 : 최소 {ruleValue} 글자 이상 입력 하세요.'
				} ]
			},
			passwordConfirm : {
				identifier : 'passwordConfirm',
				rules : [ {
					type : 'empty',
					prompt : '비밀번호 확인란에도 입력하세요.'
				}, {
					type : 'minLength[6]',
					prompt : '비밀번호 확인 : 최소 {ruleValue} 글자 이상 입력 하세요.'
				}, {
					type : 'match[password]',
					prompt : '{ruleValue} 와 동일해야합니다. '
				} ]
			},
			name : {
				identifier : 'name',
				rules : [ {
					type : 'empty',
					prompt : '이름을 입력해주세요.'
				} ]
			},
			gender : {
				identifier : 'gender',
				rules : [ {
					type : 'empty',
					prompt : '성별을 선택해 주세요.'
				} ]
			},
			email : {
				identifier : 'email',
				rules : [ {
					type : 'email',
					prompt : '이메일 형식으로 입력해주세요.'
				} ]
			},
			terms : {
				identifier : 'terms',
				rules : [ {
					type : 'checked',
					prompt : '회원가입 약관에 동의해야 합니다.'
				} ]
			}
		}
	});
});

// <![CDATA[
// 사용할 앱의 JavaScript 키를 설정해 주세요.
Kakao.init('331f6e91bdb4a956167313811ffb0d23');

// 카카오 로그인 버튼을 생성합니다.
function ktlogin() {
	Kakao.Auth.login({
		success : function(authObj) {
			Kakao.API.request({
				url : '/v1/user/me',
				success : function(res) {
					alert(res.properties.nickname + '님 로그인 되었습니다.');
					// location.href="login.do?name="+res.properties.nickname;
					location.href = "loginsuccess?kid=" + res.kaccount_email
					persistAccessToken: false;
				},
			})
			// persistAccessToken: false,
		},
		fail : function(err) {
			alert(JSON.stringify(err));
		}
	});
};

// 카카오톡 로그아웃
function ktout() {
	Kakao.Auth.logout(function() {
		setTimeout(function() {
			location.href = "logout";
			persistAccessToken: false;
			alert('로그아웃 되엇습니다');
		}, 1000); // 로그아웃 처리되는 타임을 임시적으로 1000설정
	});
};
// ]]>

