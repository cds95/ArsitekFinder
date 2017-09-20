/**
 * Javascript file defining the actions for the jobs browsing page
 */

$(document).ready(function() {
	
	/**
	 * Filters job
	 */
	$(".designer").click(function() {
		var text = $(this).text();
		filterJob(text);
	});
	
	$('#cat-list').change(function() {
		var text = $(this).val();
		console.log(text);
		filterJob(text);
	});
	
	/**
	 * Sends an ajax call so as to filter jobs on page
	 */
	function filterJob(text) {
		$.ajax({
			url: "http://www.hsd-studio.com/jobs/filter",
			type: "GET",
			data: {
				type: text
			},
			success: function() {
				if(text == "All Jobs") {
					text = "All";
				} else if(text == "My Jobs") {
					text = "My";
				}
				$("#jobHead").text(text + " Jobs");
				$(".jobContainer").load("jobs.jsp" + " .jobs");
			}
		});
	}
	/**
	 * Closes a job
	 */
	$(".remove").click(function() {
		var jid = $(this).attr('id');
		$.ajax({
			url: "http://www.hsd-studio.com/remove",
			type: "POST",
			data: {
				type: 'close',
				jid: jid
			},
			success: function() {
				$(".jobContainer").load("jobs.jsp" + " .jobs");
			}
		});
	});
	
	$(".open").click(function() {
		var jid = $(this).attr('id');
		$.ajax({
			url: "RemoveJobController",
			type: "POST",
			data: {
				type: 'open',
				jid: jid
			},
			success: function() {
				console.log("SUCCESS");
				$(".jobContainer").load("jobs.jsp" + " .jobs");
			}
		});
	});
});
