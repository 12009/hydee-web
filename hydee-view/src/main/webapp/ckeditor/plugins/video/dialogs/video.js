(function() {
	var _videoUploadBtnDomId,_videoUrlDomId;
	CKEDITOR.dialog.add( 'videoDialog', function( editor ) {
		var dialogDefinition = {
	        title: 'MP4视频属性',
	        minWidth: 400,
	        minHeight: 90,
			// 设置弹窗内容
			contents: [
				{
					id: 'tab-video',
					label: '视频信息',
					elements: [
						{
		                    type: "hbox",
		                    align: 'right',
		                    padding: 5,
		                    children: [
		                    	{	
		                    		label: '视频链接',
									type: 'text',
									id: 'url',
									validate: CKEDITOR.dialog.validate.notEmpty( "视频链接地址必须填写" ),
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
		                },
						{
							type: 'text',
							id: 'width',
							label: '播放器宽度',
							width: '100px',
							hidden: 1
						},
						{
							type: 'text',
							id: 'height',
							label: '播放器高度',
							width: '100px',
							hidden: 1
						}
					]
				}
			],
			// 确定OK按钮
			onOk: function() {
				var dialog = this;
				var videoUrl = dialog.getValueOf( 'tab-video', 'url' );
				if(videoUrl.substr(-4) != '.mp4'){
					alert('视频连接地址错误,请填写真确的mp4视频文件地址.');
					return;
				}
				// var videoWidth = dialog.getValueOf( 'tab-video', 'width' );
				// 	videoWidth = videoWidth == '' ? '100%' : (videoWidth + 'px');
				// var videoHeight = dialog.getValueOf( 'tab-video', 'height' );
				// 	videoHeight = videoHeight == '' ? 'auto' : (videoHeight + 'px');
				var _videoHtml = "<video src='"+videoUrl+"' controls='controls' height='auto' width='100%'>";
					_videoHtml += "您的浏览器不支持 video 标签。";
					_videoHtml += "</video>";
				var videoHtml  = "<p><img class=\"cke_video\" data-cke-realelement=\"";
					videoHtml += encodeURI(_videoHtml);
					videoHtml += '\" data-cke-real-node-type="1" alt="MP4 视频" title="MP4 视频" src="data:image/gif;base64,R0lGODlhAQABAPABAP///wAAACH5BAEKAAAALAAAAAABAAEAAAICRAEAOw=="';
					videoHtml += ' data-cke-real-element-type="video" data-cke-resizable="false" align=""></p>';
				editor.insertHtml( videoHtml );
			},
			onLoad: function(){
				var dialog = this,
					uploadBtn = dialog.getContentElement('tab-video', 'uploadBtn').domId,
					videoUrl = dialog.getContentElement('tab-video', 'url').domId,
					uptokenUrl = "/hydee/qiniu/uptoken",
					domain = "http://hydee.xiaomi.hydee.cn/",
					uploadBtnDom = $("#" + uploadBtn),
					videoUrlDom = $("#" + videoUrl).find("input:eq(0)");
				var qnUploader = Qiniu.uploader({
	                runtimes: 'html5,html4',	    			//上传模式,依次退化
	                browse_button: uploadBtn,      				//上传选择的点选按钮，**必需**
	                uptoken_url: uptokenUrl, 					//Ajax请求upToken的Url，**强烈建议设置**（服务端提供）
	                unique_names: true,							// 默认false，key为文件名。若开启该选项，JS-SDK会为每个文件自动生成key（文件名）
	                domain: domain,   							//bucket 域名，下载资源时用到，**必需**
	                get_new_uptoken: true,						//设置上传文件的时候是否每次都重新获取新的token
	                max_file_size: "500MB",          			//最大文件体积限制
	                max_retries: 2,                   			//上传失败最大重试次数
	                chunk_size: '4mb',                			//分块上传时，每片的体积
	                auto_start: false,                 			//选择文件后自动上传，若关闭需要自己绑定事件触发上传
	                init: {
	                    'FilesAdded': function(up, files) {
	                        plupload.each(files, function(file) {
	                        	if(file.type == "video/mp4" || file.type == "video/ogg" || file.type == "video/webm" ){
			                		uploadBtnDom.attr({"disabled":"true"});
			                		up.start();
			                	}else{
			                		window.error("抱歉,目前仅支持mp4,ogg,webm格式视频播放。");
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