/**
 * 
 */

$(document).ready(function() {
	var availableTags = [
	                     "Surabaya, East Java, Indonesia",
	                     "Jakarta, DKI, Indonesia"
	                   ];
	$("#jobSearch").autocomplete({
		source: availableTags
	});
	
	$("#submit").click(function() {
		var handle = $('#handle').val();
		$.ajax({
			type : "POST",
			url : "http://localhost:8080/Freelance/register",
			data : {
				'first' : $('#first').val(),
				'last' : $('#last').val(),
				'email': $('#email').val(),
				'handle': handle,
				'password': $('#password').val(),
			},
			success : function(res) {
				if(res == 'success') {
					window.location = "http://localhost:8080/Freelance/profile?handle=" + handle;
				}
				
			}
		});
	});
});