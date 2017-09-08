/**
 * Defines the interactions in the user profile page
 */

$(document).ready(function() {
	
	/**
	 * Adds a tag to a users 
	 */
	$("#add").click(function() {
		var skillString = $("#skill").val();
		var error = document.getElementById('skill-error');
		var errMessage = document.createElement('div');
		errMessage.setAttribute('id', 'error-message');
		$.ajax({
			url: "https://jh-studio.herokuapp.com/addusertag",
			type: "POST",
			data: {
				skill: skillString
			},
			success: function(res) {
				var json = JSON.parse(JSON.stringify(res));
				if(json.status == "success") {
					console.log(json.tid);
					var div = createTagElement(json.tid, skillString);
					var child = document.createElement('span');
					child.setAttribute('class', 'glyphicon glyphicon-remove remove');
					child.onclick = function() {
						var removal = $(this);
						removeTag(removal);
					};
					div.appendChild(child);
					var target = document.getElementById("displaySkills");
					target.appendChild(div);
				} else if(json.status == 'contains') {
					errMessage.textContent = "You've already added that skill";
					error.appendChild(errMessage);
				} else {
					errMessage.textContent = "You can only add a maximum of 5 skills";
					error.appendChild(errMessage);
				}
			}
		});
	});
	
	/**
	 * Creates a new Tag element
	 */
	function createTagElement(tid, skillString) {
		var div = document.createElement('div');
		div.textContent = skillString;
		div.setAttribute('class', 'tag');
		div.setAttribute('id', tid);
		return div;
	}
	
	/**
	 * Sets the default settings for the upload resume dialog
	 */
	$("#upload-resume").dialog({
		autoOpen: false,
		height: 500,
		width: 400
	});
	
	/**
	 * Opens the upload resume dialog
	 */
	$("#resume").click(function() {
		$("#upload-resume").dialog("open");
	});
	
	/**
	 * Removes a tag from the screen
	 */
	$(".remove").click(function() {
		var remove = $(this);
		removeTag(remove);
		
	});
	
	/**
	 * Removes a given tag
	 */
	function removeTag(rem) {
		var remParent = rem.parent();
		$.ajax({
			url: "http://localhost:8080/Freelance/removeusertag",
			type: "POST",
			data: {
				id: remParent.attr('id')
			},
			success: function() {
				remParent.remove();
			}
		});
	}
});