/**
 * Implements the autocomplete feature
 */

$(document).ready(function() {
	
	/**
	 * Gets the data from the database and uses it as for the autocomplete
	 * feature in the skills input
	 */
	$("#skill").autocomplete({
		source: function(req, res) {
			$.ajax({
				url: "https://jh-studio.herokuapp.com/gettags",
				type: "GET",
				success: function(data) {
					var tags = data.tags;
					res(tags);
				}
			});
		}
	});
});