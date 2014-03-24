

AUI().ready(

function(A) {

/* -------- navigation submenu effect --------- */
(function($){
	
	//cache nav
	var nav = $("#navigation");
	
	//add indicator and hovers to submenu parents
	nav.find("li").each(function() {
		if ($(this).find("ul").length > 0) {
			

			//show subnav on hover
			$(this).mouseenter(function() {
				$(this).find("ul").stop(true, true).slideDown();
			});
			
			//hide submenus on exit
			$(this).mouseleave(function() {
				$(this).find("ul").stop(true, true).slideUp();
			});
		}
	});
});

       
}
);


Liferay.Portlet.ready(

	/*
	This function gets loaded after each and every portlet on the page.

	portletId: the current portlet's id
	node: the Alloy Node object of the current portlet
	*/

	function(portletId, node) {
	}
);

Liferay.on(
	'allPortletsReady',

	/*
	This function gets loaded when everything, including the portlets, is on
	the page.
	*/

	function() {
	}
);