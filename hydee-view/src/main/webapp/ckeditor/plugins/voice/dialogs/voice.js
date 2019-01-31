(function() {
    var _videoUploadBtnDomId,_videoUrlDomId;
    CKEDITOR.dialog.add( 'voiceDialog', function( editor ) {
        var dialogDefinition = {
            title: 'MP3音频属性',
            minWidth: 400,
            minHeight: 90,
            // 设置弹窗内容
            contents: [
                {
                    id: 'tab-voice',
                    label: '音频信息',
                    elements: [
                        {
                            type: "hbox",
                            align: 'right',
                            padding: 5,
                            children: [
                                {   
                                    label: '音频链接',
                                    type: 'text',
                                    id: 'url',
                                    validate: CKEDITOR.dialog.validate.notEmpty( "音频链接地址必须填写" ),
                                    width: '300px'
                                },
                                {
                                    type: 'button',
                                    style: "display:inline-block;margin-top:14px;",
                                    id: 'uploadBtn',
                                    label: '选择文件',
                                    width: '80px'
                                }
                            ]
                        }
                    ]
                }
            ],
            // 确定OK按钮
            onOk: function() {
                var dialog = this;
                var voiceUrl = dialog.getValueOf( 'tab-voice', 'url' );
                if(voiceUrl.substr(-4) != '.mp3'){
                    alert('视频连接地址错误,请填写真确的mp3音频文件地址.');
                    return;
                }
                var voiceHtml  = "<img class=\"cke_voice\" data-cke-realelement=\"";
                    voiceHtml += encodeURI("<embed height=\"100\" width=\"100\" src=\""+voiceUrl+"\" />");
                    voiceHtml += '\" data-cke-real-node-type="1" alt="MP3 音频" title="MP3 音频" src="data:image/gif;base64,R0lGODlhAQABAPABAP///wAAACH5BAEKAAAALAAAAAABAAEAAAICRAEAOw=="';
                    voiceHtml += ' data-cke-real-element-type="voice" data-cke-resizable="false" align="">';
                editor.insertHtml( voiceHtml );
            },
            onLoad: function(){
                var dialog = this,
                    uploadBtn = dialog.getContentElement('tab-voice', 'uploadBtn').domId,
                    videoUrl = dialog.getContentElement('tab-voice', 'url').domId,
                    uptokenUrl = "/hydee/qiniu/uptoken",
                    domain = "http://hydee.xiaomi.hydee.cn/",
                    uploadBtnDom = $("#" + uploadBtn),
                    videoUrlDom = $("#" + videoUrl).find("input:eq(0)");
                var qnUploader = Qiniu.uploader({
                    runtimes: 'html5,html4',                    //上传模式,依次退化
                    browse_button: uploadBtn,                   //上传选择的点选按钮，**必需**
                    uptoken_url: uptokenUrl,                    //Ajax请求upToken的Url，**强烈建议设置**（服务端提供）
                    unique_names: true,                         // 默认false，key为文件名。若开启该选项，JS-SDK会为每个文件自动生成key（文件名）
                    domain: domain,                             //bucket 域名，下载资源时用到，**必需**
                    get_new_uptoken: true,                      //设置上传文件的时候是否每次都重新获取新的token
                    max_file_size: "500MB",                     //最大文件体积限制
                    max_retries: 2,                             //上传失败最大重试次数
                    chunk_size: '4mb',                          //分块上传时，每片的体积
                    auto_start: false,                          //选择文件后自动上传，若关闭需要自己绑定事件触发上传
                    init: {
                        'FilesAdded': function(up, files) {
                            plupload.each(files, function(file) {
                                console.log(file.type);
                                if(file.type == "audio/mp3" || file.type == "audio/mpeg"){
                                    uploadBtnDom.attr({"disabled":"true"});
                                    up.start();
                                }else{
                                    console.error("抱歉,目前仅支持mp3格式音频播放。");
                                }
                            });
                        },
                        'UploadProgress': function(up, file) {
                            // 每个文件上传时,处理相关的事情
                            //console.log("-->"+JSON.stringify(up));
                            uploadBtnDom.html("正在上传(已完成"+up.total.percent+"%)");
                        },
                        'FileUploaded': function(up, file, info) {
                            var domain = up.getOption('domain'),
                                res = JSON.parse(info),
                                sourceLink = domain + res.key; //获取上传成功后的文件的Url
                            uploadBtnDom.removeAttr("disabled");
                            uploadBtnDom.html("上传文件");
                            videoUrlDom.val(sourceLink);
                        },
                        'Error': function(up, err, errTip) {
                            //上传出错时,处理相关的事情
                            console.log(up,err,errTip);
                        }
                    }
                });
            }
        };
        return dialogDefinition;
    });
})();