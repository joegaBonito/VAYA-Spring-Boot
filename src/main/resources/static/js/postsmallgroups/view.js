$("document").ready(function() {
	// this is the id of the form
	$("#commentForm").submit(function(e) {

	    var url = "/postsmallgroup/comment/add"; // the script where you handle the form input.

	    $.ajax({
	           type: "POST",
	           url: url,
	           data: $("#commentForm").serialize(), // serializes the form's elements.
	           success: function(data)
	           {
	               alert(data); // show response from the php script.
	           }
	         });

	    e.preventDefault(); // avoid to execute the actual submit of the form.
	});
	
});
