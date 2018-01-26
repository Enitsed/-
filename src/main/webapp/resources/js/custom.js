/*jslint browser: true*/
/*global $, document*/

$(document).ready(function () {
	"use strict";

	// 글쓰기 write 버튼
	$('#writeBtn').click(function () {
		$(location).attr('href', "http://localhost:8090/finalproject/boardWrite");
	});

	// 회원가입 성공 여부 알림
	if (document.location.href == "http://localhost:8090/finalproject/signUp") {
		$('form').on('submit', signUpCheckStatus());
	}
	
	//아이디 찾기 알림
	if(findIdStatus != ""){
		$('.findIdStatus .ui.header').text(findIdStatus);
		$('.ui.tiny.modal.findIdStatus').modal('show');
	}
	
	//아이디 찾기 닺기
	$('.findIdStatus .actions .button').on('click',function(){
		$('.ui.tiny.modal.findIdStatus').modal('hide');
	})
	
	//비밀번호 찾기 알림
	if(findPwStatus != ""){
		$('.findPwStatus .ui.header').text(findPwStatus);
		$('.ui.tiny.modal.findPwStatus').modal('show');
	}
	
	//비밀번호 찾기 닺기
	$('.findPwStatus .actions .button').on('click',function(){
		$('.ui.tiny.modal.findPwStatus').modal('hide');
	})
	
	// 아이디 찾기 유효성 검사
	$('.ui.form#findId').form({
		on: 'blur',
		fields: {
			findName: {
				identifier: 'findName',
				rules: [{
					type: 'empty',
					prompt: '이름을 입력해주세요.'
			}]
			},
			findEmail: {
				identifier: 'findEmail',
				rules: [{
					type: 'empty',
					prompt: '이메일을 입력하세요.'
				}, {
					type: 'email',
					prompt: '이메일 형식으로 입력해주세요.'
				}]
			}
		}
	});

	// 비밀번호 찾기 유효성 검사
	$('.ui.form#findPw').form({
		on: 'blur',
		fields: {
			findId: {
				identifier: 'findId',
				rules: [{
					type: 'empty',
					prompt: '아이디를 입력해주세요.'
			}]
			},
			findName: {
				identifier: 'findName',
				rules: [{
					type: 'empty',
					prompt: '이름을 입력하세요.'
				}]
			}
		}
	});
	
	//비밀번호 찾기 알림
	if(findPwStatus != ""){
		$('.findPwStatus .ui.header').text(findPwStatus);
		$('.ui.tiny.modal.findPwStatus').modal('show');
	}
	
	//아이디 찾기 닺기
	$('.findPwStatus .actions .button').on('click',function(){
		$('.ui.tiny.modal.findPwStatus').modal('hide');
	})
	
	// 로그인 성공 여부 알림창
	if (loginStatus != "") {
		$('.loginStatus .ui.header').text(loginStatus);
		$('.ui.tiny.modal.loginStatus').modal('show');
	}
	
	// 로그인 성공 여부 알림창 닫기
	$('.loginStatus .actions .button').on('click', function () {
		$('.ui.tiny.modal.loginStatus').modal('hide');
	});
	
	// 영화 상세보기
	$('.main_movie').on('click', function () {
		var movie_num = $('.movie_num').val();
		var index = $(this).find('input[type="hidden"]').val();
		var modal = '#modal' + index;

		$.ajax({
			url: 'info?movie_num=' + index,
			type: 'GET',
			dataType: 'json',
			success: function (data) {
				// alert(JSON.stringify(data.info[0].movie_kor_title));
				$('.event').remove();
				var comment = "";
				for (var i = 0; i < data.comment.length; i++) {
					var sdate = new Date(data.comment[i].regdate);
					var sm = sdate.getFullYear() + "/";
					sm = sm + (sdate.getMonth() + 1) + "/";
					sm = sm + sdate.getDate();

					comment +=
						'<div class="event">' +
						'<div class="label">' +
						'<img src="resources/images/user.png">' +
						'</div>' +
						'<input type="hidden" class="comment_num" value="' + data.comment[i].comment_num + '"/>' +
						'<div class="content">' +
						'<div class="summary">' +
						'<a class="user">' + data.comment[i].mem_id + '</a>' +
						'<input type="hidden" class="mem_id" value="' + data.comment[i].mem_id + '"/>' +
						'<div class="date">' + sm + '</div></div>' +
						'<div class="extra text">' + data.comment[i].replytext + '</div>' +
						'<div class="meta">' +
						'<a class="like" value="' + data.comment[i].likecount + '"><i class="like icon"></i>' + data.comment[i].likecount + '</a>' +
						'</div></div></div>'

				}
				$(comment).appendTo(".ui.large.feed");

				$(modal).modal('show');
				/*
				$('.like').on('click', function () {
				   $.ajax({
				      type: 'GET',
				      dataType: 'json',
				      url: 'like',
				      data: 'mem_id=' + $('.mem_id').val() + '&comment_num=' + $('.comment_num').val(),
				      success: function (data) {
				         var like = $('.like').attr('value');
				         // var like =$('.like').text();
				         alert(data.like);
				         alert(like);
				         if (data.like == null) {
				            like += 1;
				            $('.like').text(like);
				         } else {
				            like -= 1;
				            $('.like').text(like);

				         }
				      }
				   });

				});
				*/

			}
		});
	});

	// 아이디 중복체크
	$('#checkId').on('click', function (e) {

		if ($('#id').val() == '') {
			$('.idFail .ui.header').text("아이디를 입력해주세요.");
			$('.ui.tiny.modal.idFail').modal('show');
			e.preventDefault();
		} else {
			$.ajax({
				type: 'POST',
				dataType: 'text',
				url: 'checkId',
				data: 'mem_id=' + $('#id').val(),
				success: function (result) {
					if (result == "true") {
						$('.idFail .ui.header').text("아이디가 이미 존재합니다.");
						$('.ui.tiny.modal.idFail').modal('show');
						$('#id_check_result').val(1);
					} else {
						$('.idFail .ui.header').text("사용할 수 있는 아이디입니다.");
						$('.ui.tiny.modal.idFail').modal('show');
						$('#id_check_result').val(2);
					}
				},
				error: function (request, status, error) {
					alert("code:" + request.status + "\n" + "message:" + request.responseText + "\n" + "error:" + error);
				}
			});
		}
	});

	// 중복확인창 닫기
	$('.idFail .actions .ui.button').on('click', function (e) {
		$('.ui.tiny.modal.idFail').modal('hide');
	});

	// 별점
	$('.ui.rating')
		.rating();

	// 카카오톡 로그인 버튼 이미지
	$('#kakaoLoginImage').on('mouseenter', function () {
		$(this).prop('src', 'resources/images/loginBtnHover.png');
		$(this).css('cursor', 'pointer');
	});

	$('#kakaoLoginImage').on('mouseleave', function () {
		$(this).prop('src', 'resources/images/loginBtn.png');
		$(this).css('cursor', 'default');
	});

	// 로그인 창 띄우기
	$('#loginBtn').on('click', function () {
		$('.ui.modal.login_modal').modal('show');
	});

	// 로그인 창 닫기
	$('#closeBtn').on('click', function () {
		$('.ui.modal.login_modal').modal('hide');
	});

	// 메인 페이지 카드
	$('.special.cards .image.main_movie').dimmer({
		on: 'hover'
	});

	// 메뉴 드랍 다운
	$('.ui.dropdown').dropdown();

	// 로그인 유효성 검사
	$('.ui.form#loginForm').form({
		on: 'blur',
		fields: {
			loginId: {
				identifier: 'loginId',
				rules: [{
					type: 'empty',
					prompt: '아이디를 입력해주세요.'
			}]
			},
			loginPassword: {
				identifier: 'loginPassword',
				rules: [{
					type: 'empty',
					prompt: '비밀번호를 입력하세요.'
			}, {
					type: 'minLength[6]',
					prompt: '비밀번호 : 최소 {ruleValue} 글자 이상 입력 하세요.'
			}]
			}
		}
	});

	// 회원가입 유효성 검사
	$('.ui.form#signUpForm').form({
		on: 'blur',
		fields: {
			id: {
				identifier: 'id',
				rules: [{
					type: 'empty',
					prompt: '아이디를 입력해주세요.'
			}]
			},
			id_check_result: {
				identifier: 'id_check_result',
				rules: [{
					type: 'not[0]',
					prompt: '중복 체크를 하지 않으셨습니다. 중복체크 해주세요.'
			}, {
					type: 'not[1]',
					prompt: '이미 존재하는 아이디입니다.'
			}]
			},
			password: {
				identifier: 'password',
				rules: [{
					type: 'empty',
					prompt: '비밀번호를 입력하세요.'
			}, {
					type: 'minLength[6]',
					prompt: '비밀번호 : 최소 {ruleValue} 글자 이상 입력 하세요.'
			}]
			},
			passwordConfirm: {
				identifier: 'passwordConfirm',
				rules: [{
					type: 'empty',
					prompt: '비밀번호 확인란에도 입력하세요.'
			}, {
					type: 'minLength[6]',
					prompt: '비밀번호 확인 : 최소 {ruleValue} 글자 이상 입력 하세요.'
			}, {
					type: 'match[password]',
					prompt: '{ruleValue} 와 동일해야합니다. '
			}]
			},
			name: {
				identifier: 'name',
				rules: [{
					type: 'empty',
					prompt: '이름을 입력해주세요.'
			}]
			},
			gender: {
				identifier: 'gender',
				rules: [{
					type: 'empty',
					prompt: '성별을 선택해 주세요.'
			}]
			},
			email: {
				identifier: 'email',
				rules: [{
					type: 'email',
					prompt: '이메일 형식으로 입력해주세요.'
			}]
			},
			terms: {
				identifier: 'terms',
				rules: [{
					type: 'checked',
					prompt: '회원가입 약관에 동의해야 합니다.'
			}]
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
			success: function (authObj) {
				Kakao.API.request({
					url: '/v1/user/me',
					success: function (res) {
						alert(res.properties.nickname + '님 로그인 되었습니다.');
						// location.href="login.do?name="+res.properties.nickname;
						location.href = "loginsuccess?kid=" + res.kaccount_email
						persistAccessToken: false;
					},
				})
				// persistAccessToken: false,
			},
			fail: function (err) {
				alert(JSON.stringify(err));
			}
		});
	};

	// 카카오톡 로그아웃
	function ktout() {
		Kakao.Auth.logout(function () {
			setTimeout(function () {
				location.href = "logout";
				persistAccessToken: false;
				alert('로그아웃 되엇습니다');
			}, 1000); // 로그아웃 처리되는 타임을 임시적으로 1000설정
		});
	};
	// ]]>

	function signUpCheckStatus() {

		// 회원 가입 성공 여부 알림창
		if (signUpStatus == "true") {
			// 회원가입 성공시
			$('.idFail .ui.header').text("회원가입에 성공하였습니다.");
			$('.ui.tiny.modal.idFail').modal('show');
			// similar behavior as an HTTP redirect
			$('.ui.tiny.modal.idFail').modal({
				onHidden: function () {
					window.location.replace("http://localhost:8090/finalproject/main");
				}
			});
		} else if (signUpStatus == "false") {
			$('.idFail .ui.header').text("회원가입에 실패하였습니다. 다시 시도해 주세요");
			$('.ui.tiny.modal.idFail').modal('show');
		}
	};

	var slideIndex = 1;
	showSlides(slideIndex);

	function plusSlides(n) {
		var slides = document.getElementsByClassName("slide_box");
		for(var i = 0 ; i < 3 ; i ++){
			if(slides.length <=  slideIndex + i + n ||  slideIndex + n < 1){
				return;
			}
		}
		showSlides(slideIndex += n);
	}

	function currentSlide(n) {
		showSlides(slideIndex = n);
	}

	function showSlides(n) {
		var i;
		var slides = document.getElementsByClassName("slide_box");
	
		for (i = 0; i < slides.length; i++) {
			slides[i].style.display = "none";
		}

		slides[slideIndex - 1].style.display = "block";
		
		for(i = 0 ; i < 3 ; i ++){
			slides[slideIndex + i].style.display = "inline"
		}
	}