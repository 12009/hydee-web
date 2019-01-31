CKEDITOR.plugins.add( 'voice', {
    icons: 'voice',
    onLoad: function() {
        CKEDITOR.addCss("img.cke_voice{background-image: url(" + CKEDITOR.getUrl(this.path + "images/placeholder.png") + ");background-position: center center;background-repeat: no-repeat;border: 1px solid #a9a9a9;width: 100px;height: 27px;}");
    },
    init: function( editor ) {
        //Plugin logic goes here.
        editor.addCommand( 'insertVoice', new CKEDITOR.dialogCommand( 'voiceDialog' ) );
        
        editor.ui.addButton( 'Voice', {
            label: '音频',
            command: 'insertVoice',
            toolbar: 'insert'
        });
        
        CKEDITOR.dialog.add( 'voiceDialog', this.path + 'dialogs/voice.js' );
    }
});