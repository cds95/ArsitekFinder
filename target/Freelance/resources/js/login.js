/**
 * Manages the login page's javascript
 */

$(document).ready(function() {
	$('#log').click(function() {
		var user = $('#username').val();
		var pass = $('#password').val();
		$.ajax({
			type : "POST",
			url : "http://localhost:8080/Freelance/logging",
			data : {
				'username' : user,
				'password' : pass
			},
			success : function(dataString) {
				showMessage(dataString);
			}
		});
	});
	
	$(document).ajaxStart(function(){
        $("#wait").css("display", "block");
        $("#log").css("display", "none");
    });
	
    $(document).ajaxComplete(function(){
        $("#wait").css("display", "none");
        $("#log").css("display", "block");
    });
	
	/**
	 * Decides on what to do after user authentication.
	 */
	function showMessage(res) {
		var target = document.getElementById("invalid-container");
		if (res == 'fail') {
			if($('#invalid-container').children().length == 0) {
				var div = document.createElement('div');
				div.textContent = 'Invalid username or password';
				div.setAttribute('id', 'invalid');
				target.appendChild(div);
			}
		} else {
			window.location='http://localhost:8080/Freelance/';
		}
	}
});