$("document").ready(function(){
	
	/*
	 * This JQuery function only allows one category of Accounting for a post.
	 */
	$('select').change(function() {
		if($( this ).hasClass("postformlist") ){
		$(this)
	        .parent('div').parent('div').siblings('div').find('.postformlist')
	        .attr('disabled', true).end()
		}
	    if(this.class == "default")
	        $(this)
	        .parent('div').parent('div').siblings('div').find('.postformlist')
	        .attr('disabled', false)
	});
}); 