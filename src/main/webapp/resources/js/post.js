/**
 * 
 */

$(document).ready(
		function() {
			var jobs = [];
			var counter = 0;
			
			/**
			 * Submits the job into the database.  User must submit at least one skill to post the job
			 */
			$("#sub").click(function() {
				var str = jobs.toString();
				if (str == "") {
					createError("Please enter what skills are required for the job");
				} else {
					$.ajax({
						url : "SearchController",
						type : "GET",
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

			var availableTags = [ "Surabaya, East Java, Indonesia",
					"Jakarta, DKI, Indonesia", "Medan, Sumatra, Indonesia",
					"Bandung, West Java, Indonesia", ];
			$("#loc").autocomplete({
				source : availableTags
			});

			var skills = [ "AutoCAD", "3D Max", "Google Sketchup",
					"Illustrator", "Photoshop", "V-Ray" ];
			$("#skill").autocomplete({
				source : skills
			});
			
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
						console.log(jobs.toString());
					};
					div.appendChild(child);
					
					var target = document.getElementById("displaySkills");
					target.appendChild(div);
					jobs.push(skillString);
					counter = counter + 1;
				} else {
					createError("You Can Only Add a Maximum of 5 Skills");
				}
			});
			
			$(".remove").click(function() {
				var remove = $(this).parent();
				remove.remove();
			});
			
			/**
			 * Removes a skill from the job array
			 */
			function removeFromArray(skill) {
				for(var i = 0; i < jobs.length; i++) {
					if(jobs[i] == skill) {
						jobs.splice(i, 1);
					}
				}
			}
		});