CKEDITOR.plugins.add( 'video', {
    icons: 'video',
    onLoad: function() {
        CKEDITOR.addCss("img.cke_video{background-image: url(" + CKEDITOR.getUrl(this.path + "images/placeholder.png") + ");background-position: center center;background-repeat: no-repeat;border: 1px solid #a9a9a9;width: 400px;height: 300px;}");
    },
    init: function( editor ) {
        //Plugin logic goes here.
		//editor.addCommand( 'insertVideo', new CKEDITOR.dialogCommand( 'videoDialog' ) );
		
		//包含属性的标签,也可以增加自定义属性。
		var b = "video[controls,id,height,poster,width,src];source[src,type]";
		editor.addCommand( 'insertVideo', new CKEDITOR.dialogCommand( 'videoDialog', {
			allowedContent: b,//允许的内容
			requiredContent: "video"
		} ) );
		
		editor.ui.addButton( 'Video', {
			label: '视频',
			command: 'insertVideo',
			toolbar: 'insert'
		});
		
		CKEDITOR.dialog.add( 'videoDialog', this.path + 'dialogs/video.js' );
    }
});