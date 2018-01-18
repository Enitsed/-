/*jslint browser: true*/
/*global $, document*/

$(document).ready(function() {
	"use strict";

	$('.ui.form').form({
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
					prompt : '최소 {ruleValue} 글자 이상 입력 하세요.'
				} ]
			},
			passwordConfirm : {
				identifier : 'passwordConfirm',
				rules : [ {
					type : 'empty',
					prompt : '비밀번호 확인란에도 입력하세요.'
				}, {
					type : 'minLength[6]',
					prompt : '최소 {ruleValue} 글자 이상 입력 하세요.'
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
