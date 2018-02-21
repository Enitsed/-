/*jslint browser: true*/
/*global $, document*/

$(document).ready(function () {
	
	$('#input_img').on('change',imgup);
		
	function imgup(e){
		$('.img_wrap').empty();
		$('.profile_wrap').css('background-image', 'url("")');
		var files = e.target.files;
		var filesArr = Array.prototype.slice.call(files);
		
		filesArr.forEach(function(f){
			if(!f.type.match('image.*')){
				alert('이미지만 가능');
				return false;
			}
			sel_file =f;
			
			var reader = new FileReader();
			reader.onload = function(e){
				var img = new Image();
			    img.src = e.target.result;
			    img.width = 200;
			    img.height = 200;
				$('.img_wrap').append(img);

				var filename;
				if(window.FileReader){  // modern browser
					filename=$('#input_img').prop('files')[0];
					//filename = $('.img_wrap').get(0).files[0];
				} 
				else {  // old IE
					//var filename = $('#input_img').val().split('/').pop().split('\\').pop();  // 파일명만 추출
				}
				
				
				var fileList = [];
				fileList.push(img);
				var form_data = new FormData();
				form_data.append('mem_profile',filename);
				form_data.append('mem_id',session_id);

				$.ajax({
					type:'POST',
					url:'updateprofile',
					data : form_data,
					datatype:'json',
					contentType : false, 
					enctype 	: 'multipart/form-data',
					processData : false,
					
				});

			}
			reader.readAsDataURL(f);
		});
	}//
	
	function imgupload(){
		var filename = $('#input_img').get(0).files[0];
		var fileList = [];
		fileList.push(filename);
		
		var form_data = new FormData();
		form_data.append('mem_profile',fileList[0]);
		
		$.ajax({
			type:'POST',
			url:'updateInfo',
			data : form_data,
			datatype:'json',
			contentType : false, 
			enctype 	: 'multipart/form-data',
			processData : false,
			success: function() {
				
			}
		});

	}
	
	"use strict";
	
	
	// 메인 페이지 동영상
	$('#banner').on('click', function(){
		$('body .dimmer').dimmer('show');
		$("#banner").data("vide").getVideoObject().pause();
		$('.ui.video').video();
	});
	
	// 버튼 혹은 동영상 바깥 클릭 후 메인 동영상 재생 및 디머 숨기기
	$('#banner_close').on('click', function(){
		main_video_dimmer();
	});
	
	function main_video_dimmer() {
		$('body .dimmer').dimmer('hide');
		$("#banner").data("vide").getVideoObject().play();
	};
	
	// 메인 헤더 아이콘 애니메이션
	$('.circular.users.icon').transition({
	    animation : 'pulse',
	    duration  : '3s'
	  });
	
	var currentPosition = parseInt($("#sidebox").css("top")); 
	$(window).scroll(function() { 
		var position = $(window).scrollTop(); 
		$("#sidebox").stop().animate({
			"top":position+currentPosition+"px"
			},1000); 
		});

	// http://localhost:8090/finalproject/free
	var boardUrl = document.location.href.slice(0, 39);
	if(boardUrl.match(/free/gi)){
		if(board_category != 0){
			$('.boardCategoryMenu').children().eq(board_category-1).addClass("active");
		}
	}
	
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
	
	//회원등급 수정 알림
	if(memUpdateStatus != ""){
		$('.memUpdateStatus .ui.header').text(memUpdateStatus);
		$('.ui.tiny.modal.memUpdateStatus').modal('show');
	}
	
	//회원등급 수정 닫기
	$('.memUpdateStatus .actions .button').on('click',function(){
		$('.ui.tiny.modal.memUpdateStatus').modal('hide');
		$(location).attr('href', "http://localhost:8090/finalproject/main");
	})
	
	//회원정보 수정 알림
	if(document.location.href == "http://localhost:8090/finalproject/memUpdate"){
		if(updateInfoStatus != ""){
			$('.updateInfoStatus .ui.header').text(updateInfoStatus);
			$('.ui.tiny.modal.updateInfoStatus').modal('show');
		}
	}
	
	//회원정보 수정 닫기
	$('.updateInfoStatus .actions .button').on('click',function(){
		$('.ui.tiny.modal.updateInfoStatus').modal('hide');
		$(location).attr('href', "http://localhost:8090/finalproject/main");
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
	
	//아이디 찾기 닫기
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
	
	$('.ui.rating.point').rating();
	// 별점
	$('.ui.rating.point').on("click",function(){
	    event.stopPropagation();
		var rating = $(this).rating("get rating", this);
		var num =  $('#member_num').val();
		var movie_num = $(this).attr("id");
		/*alert(rating + " " + num + " " + movie_num);*/
		if(num < 1){
			alert("로그인부터 해라");
			return;
		}
		$.ajax({
			type: 'GET',
			dataType: 'json',
			url: 'addrating.do?movie_num=' + movie_num + "&member_num=" + num + "&rating="+rating,
			success: function (res) {
				alert("평점이 등록 되었습니다.")
			},
			error: function (request, status, error) {
				alert("code:" + request.status + "\n" + "message:" + request.responseText + "\n" + "error:" + error);
			}
		});
	});
	
	$('.ui.rating.avgRat').rating('disable');

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
	
	
	// 게시판 글 카테고리 선택 검증
	$('.form.boardWrite')
	.form({
	  on: 'blur',
	  fields: {
		  board_category: {
	      identifier  : 'board_category',
	      rules: [
	        {
	          type   : 'empty',
	          prompt : '게시판 글 카테고리를 선택하세요.'
	        }
	      ]
	    },
      board_name: {
          identifier  : 'board_name',
          rules: [
            {
              type   : 'maxLength[20]',
              prompt : '제목은 20자 이내로 작성하세요.'
            }
          ]
        },
        board_content: {
          identifier  : 'board_content',
          rules: [
            {
              type   : 'maxLength[500]',
              prompt : '내용은 500자 이내로 작성하세요.'
            }
          ]
        }
	  }
	});
});//document 끝



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
				alert('로그아웃 되었습니다');
			}, 1000); // 로그아웃 처리되는 타임을 임시적으로 1000설정
		});
		location.href = "logout";
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
			$(slides[i]).css("display", "none");
		}

		$(slides[slideIndex - 1]).css("display", "block");
		
		for(i = 0 ; i < 3 ; i ++){
			$(slides[slideIndex + i]).css("display", "inline");
		}
	}
	
	// 영화 코멘트
	   var more=4;
	   
	   // 영화 상세보기
	   $('.main_movie').on('click', function () {
		  
	      var mnum = $(this).find('input[type="hidden"]').val();
	      var modal = '#modal' + mnum;
	      var hi = $(this).parent().find('.hiddennum').attr('name');//10으로 초기화

	      $.ajax({
	         url: 'info?movie_num=' + mnum,
	         type: 'GET',
	         async: false,
	         dataType: 'json',
	         success: function (data) {
	            $('.event').remove();
	            $.each(data,function(index,value){
	  
					var comment="";
					var sdate = new Date(value.regdate);
					var sm = sdate.getFullYear() + "/";
					sm = sm + (sdate.getMonth() + 1) + "/";
					sm = sm + sdate.getDate();

					comment +=
						'<div class="event">' +
						'<div class="label">' +
						'<img src="./profile/'+value.profile+'">' +
						'</div>' +
						'<input type="hidden" class="comment_num" value="' + value.comment_num + '"/>' +
						'<div class="content">' +
						'<div class="summary">' +
						'<a class="user">' + value.mem_id + '</a>' +
						'<input type="hidden" class="movie_num" value="'+value.movie_num+'"/>'+
						'<div class="date">' + sm + '</div></div>' +
						'<div class="extra text">' + value.replytext + '</div>' +
						'<div class="meta">' +
						'<a class="like" value="'+value.comment_num+'" name="'+value.comment_num+'"><i class="like icon"></i>' + value.likecount + '</a>'
						if(session_id==value.mem_id){
							comment+='<a class="del" value="'+value.comment_num+'" id="'+value.movie_num+'"><i class="trash icon"></i>삭제</a>'
						}
						+'</div></div></div>';
						$(comment).appendTo(".ui.large.feed");
						if(index==more)
		            		return false;
	            	
				});//each

	            $('#hidden').remove();
	            $('.horizontal.divider .more').remove();
	            var plus="";
	            if(data.length>more)
	            	plus+= '<input type="hidden" value="'+mnum+'" id="hidden"/>'+
	            	'<div class="ui horizontal divider"><a class="more" id="10">댓글 더보기</a></div>'
					$(plus).appendTo('.seemore');

	            $(modal).modal('show');
	           
	         }//success
	      });//ajax끝
	      
	   });//movie modal 클릭 끝
	   
	   //$(document).on('click','.ui.blue.labeled.submit.icon.button',commentinsert);
	   $(document).on('click','.ui.basic.label',commentinsert);
	   
	   function commentinsert(){
	      var reset = $(this).parent().find('.comment_m');
	      var comments = reset.val();//replytext
	      var number = parseInt($(this).attr('id'));
	      if(comments==''){
	    	  alert('내용을 입력해주세요');
	    	  return false;
	      }
	     
	      $.ajax({
	         
	         url : 'insertcomment?mem_id='+session_id+'&replytext='+comments+'&movie_num='+number+'&mem_num='+session_num+'&profile='+profile,
	         type:'GET',
	         async: false,

	         dataType:'json',
	         success : function(data){
	            $('.event').remove();

	            $.each(data,function(index,value){
	            	 
	               var insert="";
	               var sdate = new Date(value.regdate);
	               var sm = sdate.getFullYear() + "/";
	               sm = sm + (sdate.getMonth() + 1) + "/";
	               sm = sm + sdate.getDate();

	               insert +=
	                  '<div class="event">' +
	                  '<div class="label">' +
	                  '<img src="./profile/'+value.profile+'">' +
	                  '</div>' +
	                  '<input type="hidden" class="comment_num" value="' + value.comment_num + '"/>' +
	                  '<div class="content">' +
	                  '<div class="summary">' +
	                  '<a class="user">' + value.mem_id + '</a>' +
	                  '<input type="hidden" class="mem_id" value="' + value.mem_id + '"/>' +
	                  '<div class="date">' + sm + '</div></div>' +
	                  '<div class="extra text">' + value.replytext + '</div>' +
	                  '<div class="meta">' +
	                  '<a class="like" value="'+value.comment_num+'"><i class="like icon"></i>' + value.likecount + '</a>'
	                  if(session_id==value.mem_id){
	                     insert+='<a class="del" value="'+value.comment_num+'" id="'+value.movie_num+'"><i class="trash icon"></i>삭제</a>'
	                  }
	                  +'</div></div></div>'
	                  $(insert).appendTo(".ui.large.feed");
	                  if(index==more)
	                   	  return false;
	                  
	            });
	            $('#hidden').remove();
	            $('.horizontal.divider .more').remove();
	            var plus2="";
	            if(data.length>more)
	            	plus2+= '<input type="hidden" value="'+number+'" id="hidden"/>'+
	            	'<div class="ui horizontal divider"><a class="more" id="10">댓글 더보기</a></div>'
					$(plus2).appendTo('.seemore');
	            reset.val('');
	         }//success끝
	      });//ajax끝
	   }
	   
	 
	$(document).on('click','.like',like);
	   function like(){
		   
	      var num = parseInt($(this).attr('value')); //코멘트 번호
	      var now = $(this);
	      now.attr("class", "asd");
	      var likey = parseInt($(this).text());
	      if(!session_id){
	    	  alert('먼저 로그인을 해주세요.');
		         return false;
	      }
	         
	      $.ajax({
	         type: 'GET',
	         dataType: 'json',
	         url: 'like',
	         async: false,
	         data: 'mem_id=' + session_id + '&comment_num=' + num,
	         success: function (data) {
	            if (data.like != null) {
	            	likey+=1;
	                now.text('  '+likey+' '+'Likes');	
	        
	            } else if(data.like==null){
	           		 likey-=1;        
	                 now.text('  '+likey+' '+'Likes');	
	           	}
	            now.attr("class", "like");
	         }//success끝
	      });//ajax끝
	   }//like function()끝
	   
	   
	   $(document).on('click','.del',del);
	   
	   function del(){
	      var comment_number = parseInt($(this).attr('value'));
	      var movie_number = parseInt($(this).attr('id'));
	      
	      
	      $.ajax({
	    	  url : 'deletecomment',
	    	  type:'GET',
	    	  async: false,
	    	  data : 'comment_num='+comment_number+'&movie_num='+movie_number,
	    	  success : function(data){
	    		  $('.event').remove();

	              $.each(data,function(index,value){
	                 var insert="";
	                 var sdate = new Date(value.regdate);
	                 var sm = sdate.getFullYear() + "/";
	                 sm = sm + (sdate.getMonth() + 1) + "/";
	                 sm = sm + sdate.getDate();

	                 insert +=
	                    '<div class="event">' +
	                    '<div class="label">' +
	                    '<img src="./profile/'+value.profile+'">' +
	                    '</div>' +
	                    '<input type="hidden" class="comment_num" value="' + value.comment_num + '"/>' +
	                    '<div class="content">' +
	                    '<div class="summary">' +
	                    '<a class="user">' + value.mem_id + '</a>' +
	                    '<input type="hidden" class="mem_id" value="' + value.mem_id + '"/>' +
	                    '<div class="date">' + sm + '</div></div>' +
	                    '<div class="extra text">' + value.replytext + '</div>' +
	                    '<div class="meta">' +
	                    '<a class="like" value="'+value.comment_num+'"><i class="like icon"></i>' + value.likecount + '</a>'
	                    if(session_id==value.mem_id){
	                       insert+='<a class="del" value="'+value.comment_num+'" id="'+value.movie_num+'"><i class="trash icon"></i>삭제</a>'
	                    }
	                    +'</div></div></div>'
	                    $(insert).appendTo(".ui.large.feed");
	                    if(index==more)
	                  	  return false;
	                    
	              });
	              
	              $('#hidden').remove();
	              $('.horizontal.divider .more').remove();
	              
	             
	              var plus2="";
	              if(data.length>more)
	              	plus2+= '<input type="hidden" value="'+movie_number+'" id="hidden"/>'+
	  					 '<a class="more" id="10">댓글 더보기</a>'
	              	'<div class="ui horizontal divider"><a class="more" id="10">댓글 더보기</a></div>'
	              
	  				$(plus2).appendTo('.seemore');
	           
	    	  }//success
	      });//ajax
	   }//함수끝
	   
	   $(document).on('click','.ui.horizontal.divider .more',morecomment);
	   
	   function morecomment(){
		  
		   var hiddennum = $(this).parent().parent().find('.hiddennum');
		   var hiddennum2 = parseInt(hiddennum.attr('name'));
		   
			var page = parseInt($(this).attr('id'));
			var mov_num = $(this).parent().prev().val();
			
			$.ajax({
				url :'morecomment',
				dataType:'json',
		    	type:'GET',
		    	async: false,
				data :'page='+hiddennum2+'&movie_num='+mov_num,
				success : function(data){
					 $('.event').remove();
			            $.each(data,function(index,value){
							var comment="";
							var sdate = new Date(value.regdate);
							var sm = sdate.getFullYear() + "/";
							sm = sm + (sdate.getMonth() + 1) + "/";
							sm = sm + sdate.getDate();

							comment +=
								
								'<div class="event">' +
								'<div class="label">' +
								'<img src="./profile/'+ value.profile +'">' +
								'</div>' +
								'<input type="hidden" class="comment_num" value="' + value.comment_num + '"/>' +
								'<div class="content">' +
								'<div class="summary">' +
								'<a class="user">' + value.mem_id + '</a>' +
								'<input type="hidden" class="movie_num" value="'+value.movie_num+'"/>'+
								'<div class="date">' + sm + '</div></div>' +
								'<div class="extra text">' + value.replytext + '</div>' +
								'<div class="meta">' +
								'<a class="like" value="'+value.comment_num+'" name="'+value.comment_num+'"><i class="like icon"></i>'+value.likecount+'</a>'
								if(session_id==value.mem_id){
									comment+='<a class="del" value="'+value.comment_num+'" id="'+value.movie_num+'"><i class="trash icon"></i>삭제</a>'
								}
								+'</div></div></div>';
								$(comment).appendTo(".ui.large.feed");
								
								if(index==hiddennum)
									return false;
						});//each
						
			            $('#hidden').remove();
			            $('.horizontal.divider .more').remove();
			            
			            var plus="";
			            if(data.length+2>hiddennum2)
			            	plus+= '<input type="hidden" value="'+mov_num+'" id="hidden"/>'+
								 '<div class="horizontal divider"><a class="more" id="10">댓글 더보기</a></div>'
							$(plus).appendTo('.seemore');
		               
				}//success
			
			});//ajax끝
			hiddennum2=hiddennum2+5;
			hiddennum.attr('name',hiddennum2);
			
		};//함수끝
	
		
		