/**
 * @license Copyright (c) 2003-2015, CKSource - Frederico Knabben. All rights reserved.
 * For licensing, see LICENSE.md or http://ckeditor.com/license
 */

CKEDITOR.editorConfig = function(config) {
	// 界面语言，默认为 'en'
    config.language = 'zh-cn';

// 设置宽高
//    config.width = widthA-700;
//    config.height = heightA-140;
	//config.toolbarCanCollapse = true;
	config.enterMode = CKEDITOR.ENTER_BR;  
	config.shiftEnterMode = CKEDITOR.ENTER_P; 

config.toolbar = 
[
['Source'],
['Checkbox','Radio','TextField', 'Textarea', 'Select'],
['Table','HorizontalRule','PageBreak'],
['JustifyLeft','JustifyCenter','JustifyRight','JustifyBlock'],
['Bold','Italic','Underline','Strike','-','Subscript','Superscript','FontSize']
];

	// Define changes to default configuration here. For example:
	// config.language = 'fr';
	// config.uiColor = '#AADC6E';
};
