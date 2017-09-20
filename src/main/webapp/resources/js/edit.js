/**
 * 
 */

$(document).ready(function() {
	$("#save").click(function() {
		$.ajax({
			type : "POST",
			url : "http://www.hsd-studio.com/edit/save",
			data : {
				'first' : $('#first').val(),
				'last' : $('#last').val(),
				'email': $('#email').val(),
				'phone': $('#phone').val(),
				'location': $('#location').val(),
				'university': $('#university').val(),
				'graduation': $('#graduation').val()
			},
			success : function(dataString) {
				showMessage(dataString);
			}
		});
	});
	
	var redirect = 'http://www.hsd-studio.com/profile?handle=' + $('#handle').text(); //URL to redirect to
	
	/**
	 * Show message when invalid save
	 */
	function showMessage(message) {
		if(message == 'success') {
			window.location = redirect;
		} else {
			var div = document.createElement('div');
			div.textContent = "Please Enter a Valid Year";
			var target = document.getElementById("err");
			target.appendChild(div);
		}
	}
	
	/**
	 * Changes the user's password.  Will first check if the old password
	 * is correct and that it matches with the old password
	 */
	$('#save-pass').click(function() {
		var oldPassword = $('#old-pass').val();
		var oldConfirmed = $('#confirm-pass').val();
		if(oldPassword != oldConfirmed) {
			createError("Passwords do not match!");
		} else {
			$.ajax({
				type : "POST",
				url : "UpdatePasswordController",
				data : {
					'oldPassword' : oldPassword,
					'newPassword' : $('#new-pass').val()
				},
				success : function(res) {
					if(res == 'success') {
						window.location = redirect;
					} else {
						createError("Wrong Password");
					}
				}
			});
		}
	});
	
	/**
	 * Creates and displays the error message when a user incorrectly attempts
	 * to change their password
	 */
	function createError(msg) {
		var error = document.getElementById("display-err");
		var div = document.createElement('div');
		div.setAttribute('id', 'message');
		div.textContent = msg;
		error.appendChild(div);
	}
});