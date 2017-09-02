/**
 * Javascript file defining the actions for the jobs browsing page
 */

$(document).ready(function() {
	var jid = -1;
	
	$("#applicationForm").dialog({
		autoOpen: false
	});
	
	$(".designer").click(function() {
		var text = $(this).text();
		$.ajax({
			url: "FilterController",
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
	});
	
	$(".remove").click(function() {
		var jid = $(this).attr('id');
		$.ajax({
			url: "RemoveJobController",
			type: "POST",
			data: {
				type: 'close',
				jid: jid
			},
			success: function() {
				console.log("SUCCESS");
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
