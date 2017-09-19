/**
 * 
 */

$(document).ready(function() {
	
	$(document).ajaxStart(function(){
        $("#wait").css("display", "block");
        $("#submit").css("display", "none");
        $("#error").css("display", "none");
    });
	
    $(document).ajaxComplete(function(){
        $("#wait").css("display", "none");
        $("#submit").css("display", "block");
        $("#error").css("display", "block");
    });
	
	$("#submit").click(function() {
		var first = $('#first').val();
		var last = $('#last').val();
		var email = $('#email').val();
		var handle = $('#handle').val();
		var password = $('#password').val();
		var errorDiv = $('#error');
		var errorList = $('#error-list');
		if(errorList.has()) {
			errorList.empty();
		}
		if(first != "" && last != "" && email != "" && handle != "" && password != "" ) {
			$.ajax({
				type : "POST",
				url : "https://jh-studio.herokuapp.com/register",
				data : {
					'first' : first,
					'last' : last,
					'email': email,
					'handle': handle,
					'password': password,
				},
				success : function(res) {
					if(res == 'success') {
						window.location = "https://jh-studio.herokuapp.com/profile?handle=" + handle;
					} else {
						errorDiv.css('display', 'block');
						errorList.append('<li id=\'same-handle-error\'>Username already taken</li>');
					}
				}
			});
		} else {
			errorDiv.css('display', 'block');
			if(first == "") {
				errorList.append('<li id=\'first-error\'>Please enter your first name</li>');
			}
			if(last == "") {
				errorList.append('<li id=\'last-error\'>Please enter your last name</li>');
			}
			if(email == "") {
				errorList.append('<li id=\'email-error\'>Please enter a valid email</li>');
			}
			if(handle == "") {
				errorList.append('<li id=\'handle-error\'>Please enter username</li>');
			}
			if(password == "") {
				errorList.append('<li id=\'password-error\'>Please enter a password</li>');
			}
		}
	});
	
});