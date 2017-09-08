/**
 * Defines how the elements in the jobs.jsp file should behave.
 */

$(document).ready(function() {
	
	$("#applicationForm").dialog({
		autoOpen: false,
		height: 500,
		width: 400
	});
	
	$(".applyButton").click(function() {
		$("#applicationForm").dialog("open");
	});
	
	$("#fileInput").change(function() {
		var x = document.getElementById("fileInput");
	    var txt = "";
	    if ('files' in x) {
	        if (x.files.length == 0) {
	            txt = "Select one or more files.";
	        } else {
	            for (var i = 0; i < x.files.length; i++) {
	                txt += "<br><strong>" + (i+1) + ". file</strong><br>";
	                var file = x.files[i];
	                if ('name' in file) {
	                    txt += "name: " + file.name + "<br>";
	                }
	                if ('size' in file) {
	                    txt += "size: " + file.size + " bytes <br>";
	                }
	            }
	        }
	    } 
	    else {
	        if (x.value == "") {
	            txt += "Select one or more files.";
	        } else {
	            txt += "The files property is not supported by your browser!";
	            txt  += "<br>The path of the selected file: " + x.value; // If the browser does not support the files property, it will return the path of the selected file instead. 
	        }
	    }
	});
	
	/**
	 * Adds a skill tag onto the job screen.  Will produce an error if there are already 5
	 * skills attached to the job
	 */
	$("#add").click(function() {
		var skillString = $("#skill").val();
		$.ajax({
			url: "https://jh-studio.herokuapp.com/job/add",
			type: "POST",
			data: {
				jid: $("#jid").text(),
				skill: skillString
			},
			success: function(res) {
				var json = JSON.parse(JSON.stringify(res));
				if(json.status == "success") {
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
				} else if(json.status == "contains"){
					createErrorMessage("Job already contains that skill");
				} else {
					createErrorMessage("You can only add a maximum of 5 skills per job");
				}
			}
		});
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
			url: "https://jh-studio.herokuapp.com/job/remove",
			type: "POST",
			data: {
				id: remParent.attr('id'),
				jid: $('#jid').text()
			},
			success: function(res){
				if(res == 'success') {
					remParent.remove();
				} else {
					createErrorMessage("Each job must have a minimum of one skill");
				}
				
			}
		});
	}
	
	/**
	 * Creates an error message and displays it onto the screen
	 */
	function createErrorMessage(msg) {
		var error = document.getElementById('skill-error');
		var errMessage = document.createElement('div');
		errMessage.setAttribute('id', 'error-message');
		errMessage.textContent = msg;
		error.appendChild(errMessage);
	}
	
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
});