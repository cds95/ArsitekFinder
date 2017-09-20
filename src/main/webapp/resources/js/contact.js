/**
 * 
 */

$(document).ready(function() {
	 
	$(".btn").click(function() {
		var name = $("#name").val();
		var email = $("#email").val();
		var subject = $("#subject").val();
		var message = $("#message").val();
		$.ajax({
			type : "POST",
			url : "http://www.hsd-studio.com/contactus",
			data : {
				'name': name,
				'email': email,
				'subject': subject,
				'message': message
			},
			success : function(res) {
				if(res == 'success') {
					$("#result").css("display", "block");
				}
				
			}
		});
	});
});