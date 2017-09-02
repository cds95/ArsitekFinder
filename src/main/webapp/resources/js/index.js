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
});