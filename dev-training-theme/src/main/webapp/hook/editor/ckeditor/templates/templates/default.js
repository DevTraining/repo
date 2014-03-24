/*
Copyright (c) 2003-2011, CKSource - Frederico Knabben. All rights reserved.
For licensing, see LICENSE.html or http://ckeditor.com/license
*/

CKEDITOR.addTemplates('default', {
    imagesPath: themeDisplay.getPathThemeRoot()+'hook/editor/ckeditor/templates/templates/images/',
    templates: [
    {
        title: 'Two columns (50/50)',
        image: 'two-columns-50-50.png',
		  description: 'A template that defines two columns (50/50), each one with a title, and some text.',
		  html: '<div class="col-full"><div class="twocol-one"><p><strong>Two columns</strong><br />Your first column contents goes here</p></div><div class="twocol-one last"><p><strong>Two columns</strong><br />Your second column contents goes here</p></div><div class="woo-sc-divider flat">&nbsp;</div></div>'
    }, {
        title: 'Three columns',
        image: 'three-columns.png',
        description: 'A template that defines three columns, each one with a title, and some text.',
        html: '<div class="col-full"><div class="threecol-one"><p><strong>Three columns</strong><br />your first column contents goes here</p></div><div class="threecol-one"><p><strong>Three columns</strong><br />your second column contents goes here</p></div><div class="threecol-one last"><p><strong>Three columns</strong><br />your third column contents goes here</p></div><div class="woo-sc-divider flat">&nbsp;</div></div>'
    }, {
        title: 'Two Columns (30/70)',
        image: 'two-columns-30-70.png',
        description: 'A template that defines two columns (30/70), each one with a title, and some text.',
        html: '<div class="col-full"><div class="twocol-onethird"><p><strong>Two column 30-70</strong><br />Your first column contents goes here.</p></div><div class="twocol-twothird last"><p><strong>Two columns</strong><br />Your second column contents goes here.</p></div><div class="woo-sc-divider flat">&nbsp;</div></div>'
    }, {
        title: 'Four columns',
        image: 'four-columns.png',
        description: 'A template that defines Four columns, each one with a title, and some text.',
        html: '<div class="col-full"><div class="fourcol-one"><p><strong>Four columns</strong><br /> Your first column contents goes here.</p></div><div class="fourcol-one"><p><strong>Four columns</strong><br /> Your second column contents goes here.</p></div><div class="fourcol-one"><p><strong>Four columns</strong><br /> Your third column contents goes here.</p></div><div class="fourcol-one last"><p><strong>Four columns</strong><br /> Your forth column contents goes here.</p></div><div class="woo-sc-divider flat"> &nbsp;</div></div>'
    }, {
        title: 'Three columns (25/25/50)',
        image: 'three-columns-25-25-50.png',
        description: 'A template that defines three columns, each one with a title, and some text.',
        html: '<div class="col-full"><div class="threecol-onequarter"><p><strong>Three columns</strong><br /> Your first column contents goes here.</p></div><div class="threecol-onequarter"><p><strong>Three columns</strong><br /> Your second column contents goes here.</p></div><div class="threecol-onehalf last"><p><strong>Three columns</strong><br /> Your third column contents goes here.</p></div><div class="woo-sc-divider flat"> &nbsp;</div></div>'
    }, {
        title: 'Two Columns (25/75)',
        image: 'two-columns-25-75.png',
        description: 'A template that defines two columns (50/75), each one with a title, and some text.',
        html: ' <div class="col-full"><div class="twocol-onequarter"><p><strong>Two columns (1/4)-(3/4)</strong><br /> Your first column contents goes here.</p></div><div class="twocol-threequarter last"><p><strong>Two columns</strong><br /> Your third column contents goes here.</p></div><div class="woo-sc-divider flat"> &nbsp;</div></div>'
    },   
    {
        title: 'Image and Title',
        image: 'template1.gif',
        description: 'One main image with a title and text that surround the image.',
        html: '<h3><img style="margin-right: 10px" height="100" width="100" align="left"/>Type the title here</h3><p>Type the text here</p>'
    }, {
        title: 'Strange Template',
        image: 'template2.gif',
        description: 'A template that defines two colums, each one with a title, and some text.',
        html: '<table cellspacing="0" cellpadding="0" style="width:100%" border="0"><tr><td style="width:50%"><h3>Title 1</h3></td><td></td><td style="width:50%"><h3>Title 2</h3></td></tr><tr><td>Text 1</td><td></td><td>Text 2</td></tr></table><p>More text goes here.</p>'
    }, {
        title: 'Text and Table',
        image: 'template3.gif',
        description: 'A title with some text and a table.',
        html: '<div style="width: 80%"><h3>Title goes here</h3><table style="width:150px;float: right" cellspacing="0" cellpadding="0" border="1"><caption style="border:solid 1px black"><strong>Table title</strong></caption></tr><tr><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td></tr><tr><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td></tr><tr><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td></tr></table><p>Type the text here</p></div>'
    }]
});