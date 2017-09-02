/**
 * Defines the interactions in the user profile page
 */

$(document).ready(function() {
	
	/**
	 * Updates the user's phone number
	 */
	$("#updatePhone").click(function() {
		$.ajax({
			url: "PhoneController",
			type: "POST",
			data: {
				number: $("#number").val()
			},
			success: function() {
				$("#phoneNumber").load("profile.jsp" + " #phoneNumber");
			}
		});
	});
	
	/**
	 * Updates the user's location
	 */
	$("#updateLocation").click(function() {
		$.ajax({
			url: "LocationUpdateController",
			type: "POST",
			data: {
				location: $("#loc").val()
			},
			success: function() {
				$("#userLocation").load("profile.jsp" + " #userLocation");
			}
		});
	});
	
	var skills = [
	              "AutoCAD",
	              "3D Max",
	              "Google Sketchup",
	              "Illustrator",
	              "Photoshop",
	              "V-Ray"
	              ];
	$("#skill").autocomplete({
		source: skills
	});
	
	/**
	 * Adds a tag to a users 
	 */
	$("#add").click(function() {
		var skillString = $("#skill").val();
		var error = document.getElementById('skill-error');
		var errMessage = document.createElement('div');
		errMessage.setAttribute('id', 'error-message');
		$.ajax({
			url: "UpdateUserSkillsController",
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
			url: "RemoveTagController",
			type: "POST",
			data: {
				target: 'user',
				id: remParent.attr('id')
			}
		});
		remParent.remove();
	}
});