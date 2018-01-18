/*jslint browser: true*/
/*global $, document*/

$(document).ready(function() {
	"use strict";

	$('.ui.container .ui.embed').embed();
	
	$('#btn').on('click', function(){
		validate();
	});
});

// 유효성검사
function validate() {
	var pass = $('#Password').val();
	var passchk = $('#PasswordConfirm').val();
	var id = $('#id').val();
	var email = $('#Email').val();

	if (id == "") {
		alert("아이디를 입력해 주세요.");
		return false;
	}

	if (pass == "") {
		alert("비밀번호를 입력해 주세요.");
		return false;
	}

	if (passchk == "") {
		alert("비밀번호확인을 입력해 주세요.");
		return false;
	}

	if (email == "") {
		alert("이메일을 입력해 주세요.");
		return false;
	}

	if (pass != passchk) {
		alert("비밀번호를 확인해주세요.");
		return false;
	}
}
