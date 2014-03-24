
/*
Copyright (c) 2003-2011, CKSource - Frederico Knabben. All rights reserved.
For licensing, see LICENSE.html or http://ckeditor.com/license
*/

CKEDITOR.plugins.add( 'ThemeRayLink',
{	lang : ['en'], 
	init : function( editor ) 
	{
		// Add the link and unlink buttons.
		editor.addCommand( 'ThemeRayLink', new CKEDITOR.dialogCommand( 'ThemeRayLink' ) );

		editor.addCommand( 'ThemeRayUnLink', new CKEDITOR.unlinkCommand() );
		editor.ui.addButton( 'ThemeRayLink',
			{
				label : editor.lang.ThemeRayLink.link,
				command : 'ThemeRayLink',
				icon:this.path+"images/link.png"
			} );
		editor.ui.addButton( 'ThemeRayUnLink',
			{
				label : editor.lang.ThemeRayLink.unlink,
				command : 'ThemeRayUnLink',
				icon:this.path+"images/unlink.png"
			} );

		CKEDITOR.dialog.add( 'ThemeRayLink', this.path + 'dialogs/ThemeRayLink.js' );


		

		// Register selection change handler for the unlink button.
		 editor.on( 'selectionChange', function( evt )
			{
				/*
				 * Despite our initial hope, document.queryCommandEnabled() does not work
				 * for this in Firefox. So we must detect the state by element paths.
				 */
				var command = editor.getCommand( 'ThemeRayUnLink' ),
					element = evt.data.path.lastElement && evt.data.path.lastElement.getAscendant( 'a', true );
				if ( element && element.getName() == 'a' && element.getAttribute( 'href' ) )
					command.setState( CKEDITOR.TRISTATE_OFF );
				else
					command.setState( CKEDITOR.TRISTATE_DISABLED );
			} );

		editor.on( 'doubleclick', function( evt )
			{
				var element = CKEDITOR.plugins.ThemeRayLink.getSelectedLink( editor ) || evt.data.element;

				if ( !element.isReadOnly() )
				{
					if ( element.is( 'a' ) )
						evt.data.dialog =  ( element.getAttribute( 'name' ) && !element.getAttribute( 'href' ) ) ? 'biznizzAnchor' : 'ThemeRayLink';
					else if ( element.is( 'img' ) && element.data( 'cke-real-element-type' ) == 'biznizzAnchor' )
						evt.data.dialog = 'biznizzAnchor';
				}
			});

		// If the "menu" plugin is loaded, register the menu items.
		if ( editor.addMenuItems )
		{
			editor.addMenuItems(
				{

					ThemeRayLink :
					{
						label : 'ThemeRayLink',
						command : 'ThemeRayLink',
						group : 'ThemeRayLink',
						order : 1
					},

					ThemeRayUnLink :
					{
						label : editor.lang.unlink,
						command : 'ThemeRayUnLink',
						group : 'ThemeRayLink',
						order : 5
					}
				});
		}

		// If the "contextmenu" plugin is loaded, register the listeners.
		if ( editor.contextMenu )
		{
			editor.contextMenu.addListener( function( element, selection )
				{
					if ( !element || element.isReadOnly() )
						return null;

					var isAnchor = ( element.is( 'img' ) && element.data( 'cke-real-element-type' ) == 'biznizzAnchor' );

					if ( !isAnchor )
					{
						if ( !( element = CKEDITOR.plugins.ThemeRayLink.getSelectedLink( editor ) ) )
							return null;

						isAnchor = ( element.getAttribute( 'name' ) && !element.getAttribute( 'href' ) );
					}

					return isAnchor ?
							{ biznizzAnchor : CKEDITOR.TRISTATE_OFF } :
							{ ThemeRayLink : CKEDITOR.TRISTATE_OFF, ThemeRayUnLink : CKEDITOR.TRISTATE_OFF };
				});
		}
	},

	afterInit : function( editor )
	{
		// Register a filter to displaying placeholders after mode change.

		var dataProcessor = editor.dataProcessor,
			dataFilter = dataProcessor && dataProcessor.dataFilter;

		if ( dataFilter )
		{
			dataFilter.addRules(
				{
					elements :
					{
						a : function( element )
						{
							var attributes = element.attributes;
							if ( attributes.name && !attributes.href )
								return editor.createFakeParserElement( element, 'cke_anchor', 'biznizzAnchor' );
						}
					}
				});
		}
	},

	requires : [ 'fakeobjects' ]
} );

CKEDITOR.plugins.ThemeRayLink =
{
	/**
	 *  Get the surrounding link element of current selection.
	 * @param editor
	 * @example CKEDITOR.plugins.ThemeRayLink.getSelectedLink( editor );
	 * @since 3.2.1
	 * The following selection will all return the link element.
	 *	 <pre>
	 *  <a href="#">li^nk</a>
	 *  <a href="#">[link]</a>
	 *  text[<a href="#">link]</a>
	 *  <a href="#">li[nk</a>]
	 *  [<b><a href="#">li]nk</a></b>]
	 *  [<a href="#"><b>li]nk</b></a>
	 * </pre>
	 */
	getSelectedLink : function( editor )
	{
		try
		{
			var selection = editor.getSelection();
			if ( selection.getType() == CKEDITOR.SELECTION_ELEMENT )
			{
				var selectedElement = selection.getSelectedElement();
				if ( selectedElement.is( 'a' ) )
					return selectedElement;
			}

			var range = selection.getRanges( true )[ 0 ];
			range.shrink( CKEDITOR.SHRINK_TEXT );
			var root = range.getCommonAncestor();
			return root.getAscendant( 'a', true );
		}
		catch( e ) { return null; }
	}
};

CKEDITOR.ThemeRayUnLinkCommand = function(){};
CKEDITOR.ThemeRayUnLinkCommand.prototype =
{
	/** @ignore */
	exec : function( editor )
	{
		/*
		 * execCommand( 'unlink', ... ) in Firefox leaves behind <span> tags at where
		 * the <a> was, so again we have to remove the link ourselves. (See #430)
		 *
		 * TODO: Use the style system when it's complete. Let's use execCommand()
		 * as a stopgap solution for now.
		 */
		var selection = editor.getSelection(),
			bookmarks = selection.createBookmarks(),
			ranges = selection.getRanges(),
			rangeRoot,
			element;

		for ( var i = 0 ; i < ranges.length ; i++ )
		{
			rangeRoot = ranges[i].getCommonAncestor( true );
			element = rangeRoot.getAscendant( 'a', true );
			if ( !element )
				continue;
			ranges[i].selectNodeContents( element );
		}

		selection.selectRanges( ranges );
		editor.document.$.execCommand( 'ThemeRayUnLink', false, null );
		selection.selectBookmarks( bookmarks );
	},

	startDisabled : true
};

CKEDITOR.tools.extend( CKEDITOR.config,
{
	linkShowAdvancedTab : true,
	linkShowTargetTab : true
} );
