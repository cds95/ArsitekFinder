/**
 * Defines the interactions that take place in the post job page
 */

$(document).ready(
		function() {
			var tags = [];
			var counter = 0; //Tracks how many skills have been added
			
			/**
			 * Sends request to update a particular job
			 */
			$("#update").click(function() {
				var jid = $("#jid").text();
				$.ajax({
					url : "https://jh-studio.herokuapp.com/editinfo",
					type : "POST",
					data : {
						jid : jid,
						description : $("#description").val(),
						title : $("#title").val(),
						price : $("#price").val(),
						type : $("#type").val(),
					}, success : function(res) {
						if(res == 'success') {
							alert('Job Successfully Updated');
						}
					}
				});
			});
			
			/**
			 * Submits the job into the database.  User must submit at least one skill to post the job
			 */
			$("#sub").click(function() {
				var str = tags.toString();
				if (str == "") {
					createError("Please enter what skills are required for the job");
				} else {
					$.ajax({
						url : "https://jh-studio.herokuapp.com/postjob",
						type : "POST",
						data : {
							description : $("#description").val(),
							title : $("#title").val(),
							price : $("#price").val(),
							type : $("#type").val(),
							location : $("#loc").val(),
							skills : str
						},
						success : function() {
							alert("Successfully Posted Job");
						}
					});
				}
			});
			
			/**
			 * Creates the error message when no skills are added to the job
			 */
			function createError(message) {
				var div = document.createElement('div');
				div.textContent = message;
				div.setAttribute('id', 'error');
				var target = document.getElementById("error");
				target.append(div);
			}
			
			/**
			 * Adds a tag to the screen.  Creates an error when the user tries to add more
			 * than 5 tags to the job
			 */
			$("#add").click(function() {
				if(counter < 5) {
					var div = document.createElement('div');
					var skillString = $("#skill").val();
					div.textContent = skillString;
					div.setAttribute('class', 'tag');
					
					var child = document.createElement('span');
					child.setAttribute('class', 'glyphicon glyphicon-remove remove');
					child.onclick = function() {
						var removal = $(this).parent();
						removal.remove();
						counter = counter - 1;
						removeFromArray(removal.text());
					};
					div.appendChild(child);
					
					var target = document.getElementById("displaySkills");
					target.appendChild(div);
					tags.push(skillString);
					counter = counter + 1;
				} else {
					createError("You Can Only Add a Maximum of 5 Skills");
				}
			});
			
			/**
			 * Removes a tag when it it's remove button is clicked
			 */
			$(".remove").click(function() {
				var remove = $(this).parent();
				remove.remove();
			});
			
			/**
			 * Removes a skill from the job array
			 */
			function removeFromArray(skill) {
				for(var i = 0; i < tags.length; i++) {
					if(tags[i] == skill) {
						tags.splice(i, 1);
					}
				}
			}
		});