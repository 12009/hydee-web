/**
 * 描述		：封装项目整体自定义JS类库
 * 作者 		: LuoF
 * 创建时间	: 2016-08-19
 * 修改时间	: --
 * 修改备注	: --
 */
(function($){
	var mvcPath = "/hydee";
	/****************************************************
	 *  扩展$功能函数 
	 ****************************************************/
	/*
	 * 在选择的元素上加载loading动画效果
	 * 使用案例	：$('body').loading(true);
	 * 参数说明	: 
	 * 		openning - 是否为开启laoding画面
	 * 		options  - 参数选项
	 */
	$.fn.loading = function(openning,options){
		var _this = $(this),
			_selector = _this.selector.replace(/^./,"_").replace(/^#/,"__");
			openning = openning || $(".hd_loading_bg[selector="+_selector+"]").length == 0;
		var options = $.extend({
			color	:'#FFFFFF',
			alpha	:0.6,
			gifImg	:'data:image/gif;base64,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'
		}, options);
		if(!$('#jquery_hd_loading_style').length){
	        var style='<style id="jquery_hd_loading_style" type="text/css">';
	        style += '.hd_loading_bg{position: absolute; z-index:1000; background-color:'+options.color+'; background-color:rgba(0,0,0,'+options.alpha+');overflow:hidden;text-align:center;}';
	        style += '.hd_loading_bg img{display: inline-block; margin:0 auto;}'
	        style += '</style>';
			$(document.body).append(style);
	    }
		if(openning){
			_this.each(function(){
				var element = $(this);
				var element_top = element.offset().top,
					element_left = element.offset().left,
					element_height = element.outerHeight(),
					element_width = element.outerWidth();
				var loading = $("<div class='hd_loading_bg'><img class='hd_loading_img' src='"+options.gifImg+"' /></div>").appendTo(document.body);
				loading.css({
					left		:element_left+"px",
					top			:element_top+"px",
					width		:element_width+"px",
					height		:element_height+"px",
					lineHeight	:Math.min($(window).height(),element_height)+"px"
				});
				loading.attr("selector",_selector);
			});
		}else{
			$(".hd_loading_bg[selector="+_selector+"]").remove();
		}
		return this;
	};
	/*
	 * 获取选择元素中的所有input参数
	 * input标签中如果定义ctype属性,将会按照ctype属性来校验内容是否合法
	 * input参数名称优先选取name属性,弱无name属性则取id属性值,均无则会摒弃此参数
	 */
	$.fn.params = function(){
		var params = {},
			checked = true;
		$(this).find("input,select").each(function(){
			var _this = $(this),
				_ctype = _this.attr('ctype') || "none",
				_errTips = _this.attr('errtips');
			// 校验ctype属性
			switch (_ctype) {
			case "none":
				break;
			case "email":
				if(_this.val().match( /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/) == null){
					checked = false;
					$.hd.tips(_this,_errTips || "请输入正确的邮箱地址!")
				}
				break;
			case "mobile":
				if(_this.val().match(/^1[3|4|5|7|8]\d{9}$/) == null){
					checked = false;
					$.hd.tips(_this,_errTips || "请输入正确的手机号码!")
				}
				break;
			case "password":
				if(_this.val().match(/^[\S]{6,12}$/) == null){
					checked = false;
					$.hd.tips(_this,_errTips || "密码格式(6-12位)错误,请重新输入!")
				}
				break;
			case "username":
				if(_this.val().match(/[a-zA-Z0-9_@.]{5,}/) == null){
					checked = false;
					$.hd.tips(_this,_errTips || "用户名格式(英文或数字，至少五位)错误,请重新输入!")
				}
				break;
			case "required":
				if(_this.attr("type") == "checkbox" && _this.attr("checked") == null){
					checked = false;
					$.hd.tips(_this,_errTips || "请勾选此项选择框!")
				}else{
					if(_this.val() == null || _this.val().length == 0){
						checked = false;
						$.hd.tips(_this,_errTips || "此项为必填项!")
					}
				}
				break;
			case "url":
				if(_this.val().match(/[a-zA-Z0-9][-a-zA-Z0-9]{0,62}([a-zA-Z0-9][-a-zA-Z0-9]{0,62})+?/) == null){
					checked = false;
					$.hd.tips(_this,_errTips || "网址格式(http://https://开头)错误,请重新输入!")
				}
				break;
			case "date":
				break;

			case "double":
				if(_this.val().match(/^(([0-9]+)|([0-9]+\.[0-9]{1,2}))$/) == null){
					checked = false;
					$.hd.tips(_this,_errTips || "请输入正确格式（可小数点后两位）!")
				}
				break;
			default:
				var _reg = new RegExp(_ctype);
				if(_this.val().match(_reg) == null){
					checked = false;
					$.hd.tips(_this,_errTips || "请核对信息是否填写正确!")
				}
				break;
			}
			if( !checked ) return false;
			var _paramName = _this.attr("name") || _this.attr("id"),
				_prefix = _this.attr("prefix") || "";
			if( _paramName != null ){
				if(_this.attr("type") == "checkbox"){
					params[_this.attr("name") || _this.attr("id")] = _this.attr("checked") == null ? 0 : 1;
				}else{
					if(_ctype == "date"){
						params[_this.attr("name") || _this.attr("id")] = new Date(_this.val().replace(/-/g,"/"));
					}else{
						params[_this.attr("name") || _this.attr("id")] = _prefix + _this.val();
					}
				}
			}
		});
		return checked ? params : null;
	}

	/*
	 * 在选择元素上加载ERP门店列表
	 */
	$.fn.erpBusies = function(companyName, allErpBusiRsp, selectedBusiRsp, callback, options){
		var domIdSuffix = "_" + new Date().getTime(),_this = $(this);
		var options = $.extend({},options);
		var allErpBusiRsp = allErpBusiRsp || [];
		var selectedBusiNos = [];
		// 初始化样式
		//if(!$('#jquery_hd_loading_style').length){
			var style = '<style id="jquery_hdsec_erpBook_style" type="text/css">';
			style += '.busiSelectedDialog span { -moz-user-select: none; -khtml-user-select: none; user-select: none; }';
			style += '.busiSelectedDialog span.btnR { float: right; padding-right: 6px; padding-top: 4px; cursor: pointer; }';
			style += '.busiSelectedDialog i.group { color: #1874CD !important; }';
			style += '.busiSelectedDialog span.group { color: #104E8B; }';
			style += '.busiSelectedDialog .left { float: left; width: 47%; height: 95%; overflow:hidden; border-bottom: #cacaca 1px solid; margin-top: 1%; margin-left: 1%;}';
			style += '.busiSelectedDialog .left .search { height: 7%; min-height: 26px; max-height: 32px; width: auto; position: relative; border: #C1CDCD 1px solid; margin-bottom: 1%;}';
			style += '.busiSelectedDialog .left .search input { width: 90%; border: none; line-height: 24px; margin-left: 2px;}';
			style += '.busiSelectedDialog .left .search i { position: absolute; right: 5px; top: 2px; line-height: 24px; color: gray;}';
			style += '.busiSelectedDialog .left .book_content { width: auto; border: #C1CDCD 1px solid; height: 90%; overflow: hidden; padding-bottom: 10px;}';
			style += '.busiSelectedDialog .book_detail { width: auto; height: 100%; overflow: auto;}';
			style += '.busiSelectedDialog .right { float: right; width: 48%; border: #C1CDCD 1px solid; height: 95%; overflow:hidden; margin-top: 1%; }';
			style += '.busiSelectedDialog .header { line-height: 28px; background: #E0EEEE; width: auto; height: 28px; border-bottom: #cacaca 1px solid; }';
			style += '.busiSelectedDialog .header a.btn_clear { float: right; line-height: 28px; margin-right: 5px; color: blue; cursor: pointer;}';
			style += '.busiSelectedDialog .header .btn_r { float: right; height: 28px; line-height: 28px; margin-right: 10px; cursor: pointer;}';
			style += '.busiSelectedDialog .header .btn_l { float: left; height: 28px; line-height: 28px; margin-left: 10px; cursor: pointer;}';
			style += '.busiSelectedDialog .header span { line-height: 28px; margin-left: 5px; }';
			style += '.busiSelectedDialog ul.book { width: auto; padding-left: 5px; margin-bottom: 40px;}';
			style += '.busiSelectedDialog ul.book li { width: 100%; line-height: 20px; }';
			style += '.busiSelectedDialog ul#hiddenSelectedUl { display: none; }';
			style += '.busiSelectedDialog ul#busiSelected li:hover { background: #EBEBEB; }';
			style += '.busiSelectedDialog ul#busiSelected li { cursor: pointer; }';
			style += '.busiSelectedDialog ul.book li i { color: #B9D3EE; }';
			style += '.busiSelectedDialog ul.busiList li:hover { background: #EBEBEB; }';
			style += '.busiSelectedDialog ul.busiList { width: auto; padding-left: 20px; }';
			style += '.busiSelectedDialog ul.busiList span.deptLi { display: inline-block; width: 90%; cursor: pointer; overflow:hidden; text-overflow:ellipsis; white-space:nowrap;}';
			style += '.busiSelectedDialog .clear{ width:1px; height:0; clear:both;}';
			style += '</style>';
			$(document.body).append(style);
		//}
		if(!_this.hasClass("busiSelectedDialog")){
			_this.addClass("busiSelectedDialog");
		}
		// 初始化HTML标签
		var html = '<div class="left">';
		html += '<div class="search">';
		html += '<input class="searchTxt" placeholder="请输入门店编码/名称" /><i class="fa fa-search fa-la"></i>';
		html += '</div>';
		html += '<div class="book_content">';
		html += '<div class="header">';
		html += '<span>选择门店</span>';
		html += '<lable style="float: right;"><input type="checkbox" name="busiType" value="2" checked />加盟店</lable>';
		html += '<lable style="float: right;"><input type="checkbox" name="busiType" value="1" checked />直营店</lable>';
		html += '</div>';
		html += '<div class="book_detail">';
		html += '<ul class="book">';
		/** 加载ERP门店列表 */
		html += '<li id="group_company">';
		html += '<i class="fa fa-building fa-la group"></i> <span class="group">'+companyName+'</span>';
		html += '<span class="btnR groupSel group">[全选]</span>';
		html += '<ul class="busiList">';
		html += '<li></li>';
		html += '</ul>';
		html += '<ul id="hiddenSelectedUl"></ul>';
		html += '</li>';
		/** 加载常用分组门店列表 */
		/*html += '<li id="group_company">';
		 html += '<i class="fa fa-user fa-la group"></i> <span class="group">常用分组</span>';
		 html += '<ul class="deptList">';
		 html += '<li><ul></ul></li>';
		 html += '</ul>';
		 html += '</li>';*/

		html += '</ul>';
		html += '</div>';
		html += '</div>';
		html += '</div>';
		html += '<div class="right">';
		/*html += '<div class="header">';
		 html += '<div class="btn_l"><a id="addLocalUserGroup" class="a-upload">保存常用分组</a></div>';
		 html += '<div class="btn_r"><a href="javascript:;" class="a-upload"><input type="file" name="attach" id="upload_'+domIdSuffix+'">上传Excel名单</a></div>';
		 html += '<div class="btn_r"><a href="javascript:;" class="a-upload" onclick="window.open(\''+mvcPath+'/updateStroe/users.xlsx\')">下载Excel模板</a></div>';
		 html += '</div>';*/
		html += '<div class="header">';
		html += '<span>已选门店列表 </span><span id="sel_count">(0)</span><a class="btn_clear">[清空]</a>';
		html += '</div>';
		html += '<div class="book_detail">';
		html += '<ul class="book" id="busiSelected">';
		html += '</ul>';
		html += '</div>';
		html += '</div>';
		// 实例化HTML片段
		var element = $(html).prependTo(_this);
		/* 定义配置 */
		var selectedBusiList = [],
			bookSelectedUl = element.find("ul#busiSelected"),
			selBusiCountSpan = element.find("span#sel_count"),
			hiddenSelectedUl = element.find("ul#hiddenSelectedUl"),
			bookAllUl = element.find(".busiList");
		// 注册通用函数
		var func = {
			/**
			 * 初始化左侧ERP门店列表
			 */
			initLiHtmlLeft: function(){
				$.each(allErpBusiRsp, function(i, busi){
					var _busiHtml = "<li rel='"+busi.busiNo+"' businame='"+busi.busiName+"' busitype='"+busi.busiType+"'>";
					_busiHtml += "<span class='deptLi'><i class='fa fa-shopping-cart fa-la'></i> ["+busi.busiNo+"] "+busi.busiName+"</span>";
					_busiHtml += "<span class='btnR deptBtn'><i class='fa fa-plus-square-o'></i></span>"
					_busiHtml += "</li>";
					element.find(".busiList:eq(0)").append(_busiHtml);
				});
				//  注册右侧清空事件
				element.find(".btn_clear").click(function(){
					func.clearSelected();
				});
				// 注册公司全门店列表全选事件
				element.find("#group_company .groupSel").click(function(){
					func.selectGroupUsers("group_company");
				});
			},
			/**
			 * 注册Li标签点击事件
			 */
			initBusiLiEvents: function(){
				// 注册门店添加/删除事件
				element.find(".busiList li").unbind().click(function(){
					var _li = $(this),_busiNo = _li.attr("rel"),_busiName = _li.attr("businame");
					func.selectedBusi(_busiNo, _busiName, _li);
				});
			},
			/**
			 * 初始化右侧Li标签
			 */
			initLiHtmlRight: function(){
				if(selectedBusiRsp == null || selectedBusiRsp.length == 0) return;
				$.each(selectedBusiRsp, function(i, busi){
					func.selectedBusi( busi.busno );
				})
			},
			/**
			 * 选择门店响应事件
			 */
			selectedBusi: function( busiNo ){
				if(busiNo == null) return false;
				_lis = element.find(".busiList li[rel='"+busiNo+"']");
				var _index = selectedBusiNos.indexOf( busiNo );
				// 判断人员是否已添加至选择队列
				if(_index > -1){
					var _selectedLiByTarget = bookSelectedUl.find("li[rel="+busiNo+"]");
					// 修改左侧人员LI标签添加按钮图标
					element.find(".busiList li[rel='"+busiNo+"']").find(".btnR i").attr("class","fa fa-plus-square-o");
					// 重选择队列删除
					bookSelectedUl.find("li[rel="+busiNo+"]").remove();
					// 删除数据
					selectedBusiNos.splice(_index, 1);
				}else{
					// 修改人员LI标签添加按钮图标
					element.find(".busiList li[rel='"+busiNo+"']").find(".btnR i").attr("class","fa fa-minus-square-o");
					// 克隆LI标签复制到选择队列
					_lis.eq(0).clone(true).appendTo(bookSelectedUl);
					// 追加数据
					selectedBusiNos.push(busiNo, 1);
				}
				func.resetSelBusiList();
			},
			/**
			 * 清空已选择人员
			 */
			clearSelected: function(){
				selectedBusiNos = [];
				element.find("#busiSelected").html("");
				// 修改按钮图标
				element.find(".busiList .btnR i").attr("class","fa fa-plus-square-o");
				func.resetSelBusiList();
			},
			/**
			 * 全选分组列表人员
			 * @param groupId	:待全选的分组元素ID
			 */
			selectGroupUsers: function(groupId){
				var $groupUl = element.find("#"+groupId);
				// 修改按钮图标
				$groupUl.find(".busiList .btnR i").attr("class","fa fa-minus-square-o");
				// 添加队列
				$groupUl.find("ul.busiList li:visible").each(function(){
					var _this = $(this),_busiNo = _this.attr("rel"),_busiName = _this.attr("businame");
					if(selectedBusiNos.indexOf(_busiNo) < 0){
						selectedBusiNos.push(_busiNo);
						_this.clone(true).appendTo(bookSelectedUl);
					}
				});
				func.resetSelBusiList();
			},
			/**
			 * 重置已选人员列表数值
			 */
			resetSelBusiList: function(){
				selectedBusiList = [];
				$.each(allErpBusiRsp, function(i, busi){
					if(selectedBusiNos.indexOf(busi.busiNo) > -1){
						selectedBusiList.push(busi);
					}
				});
				selBusiCountSpan.html("("+selectedBusiList.length+")");
				// 调用回调函数
				if( typeof callback == "function" ){
					callback( selectedBusiList );
				}
			}
		};
		// 初始化左侧列表
		func.initLiHtmlLeft();
		// 初始化右侧列表
		func.initLiHtmlRight();
		// 初始化标签事件
		func.initBusiLiEvents();
		// 注册直营,加盟店点击事件
		element.find("input[name=busiType]").change(function() {
			var _this = $(this),val = _this.attr("value");
			console.log("busitype >> " + val);
			if(_this.is(':checked')) {
				element.find("li[busitype="+val+"]").appendTo(bookAllUl);
			} else {
				element.find("li[busitype="+val+"]").appendTo(hiddenSelectedUl);
			}
		});
		/**
		 * 快速搜索通讯录人员
		 */
		element.find("input.searchTxt:eq(0)").bind('keyup', function(event) {
			var _that = $(this),
				_value = _that.val().replace(/^\s+|\s+$/g,""),
				trsLi = bookAllUl.find("li:gt(0)"),
				trsUl = bookAllUl.find("ul:gt(0)");
			setTimeout(function(){
				var _currValue = _that.val().replace(/^\s+|\s+$/g,"");
				if(_value == _currValue){
					if(_value == ""){
						trsLi.filter(":hidden").show();
						trsUl.hide().eq(0).show();
					}else{
						trsLi.hide().filter(":contains('"+_that.val()+"')").show();
						trsUl.hide().filter(":contains('"+_that.val()+"')").show();
					}
				}
			}, 100);
		});
		// 初始化UI
	}
	
	/****************************************************
	 *  扩展$静态工具函数 
	 ****************************************************/
	$.extend({
	hd:{
		/*
		 * 封装窗口弹出函数
		 */
		alertMsg : {
			version	: "v1.0",
			window	: null,
			init	: function(title,msg,success,needBgShow){
				var that = this;
				// 初始化HTML
				if(!$(".bgShow").length){
					$(document.body).append("<div class='bgShow'></di>");
				}
				if(!$("#hd_alertMsg").length){
					var html  = '<div id="hd_alertMsg" class="shadowWhite displayNone fabu_1">';
						html += '<div class="contentWrapper" >';
						html += '<div class="content">';
						html += '<div class="detailTitle">'+title+'</div>';
						html += '<div class="div-line"></div>';
						html += '<ul class="ul-info table-height30">';
						html += '<li class="detailMsg" style="margin-top:20px; margin-left:70px; font-size:16px;">'+msg+'</li>';
						html += '</ul></div>';
						html += '<div class="btn-wraper width360">';
						html += '<input type="button" value="确定" class="btn-hover-crimson marginRight10 btnForSure">';
						html += '<input type="button" value="取消" class="btn-line-crimson btnCancel">';
						html += '</div></div></div>';
					var _this = that.window = $(html).appendTo(document.body);
				}else{
					that.window.find(".detailTitle:eq(0)").html(title);
					that.window.find(".detailMsg:eq(0)").html(msg);
				}
				that.window.find(".btnCancel:eq(0)").click(function(){
					that.close(needBgShow);
					$(".btnForSure").off("click");
				});
				that.window.find(".btnForSure").click(function(){
					if(typeof success == "function"){
						success();
					}
					that.close(needBgShow);
					$(this).off("click");
				});
			},
			open	: function(showMask,timeout,needCancel){
				var showMask = showMask || false;
				$("#hd_alertMsg").show();
				if(showMask) $(".bgShow").show();
				if(timeout > 0){
					setTimeout(function(){
						$(".bgShow,#hd_alertMsg").hide();
					}, timeout);
				}
				if(needCancel) {
					this.window.find(".btnCancel").show();
				}else {
					this.window.find(".btnCancel").hide();
				}
			},
			close	: function(needBgShow){
				needBgShow = needBgShow == null ? false : true;
				if(needBgShow){
					$(".bgShow").show();
				}else{
					$(".bgShow").hide();
				}
				$("#hd_alertMsg").hide();
			},
			// 普通窗口弹出
			info	: function(msg,title,showMask,sucess,needBgShow){
				this.init(title || "信息",msg,sucess,needBgShow);
				this.open(showMask || false, 0, false);
			},
			// 错误提示窗口
			error	: function(msg,title,showMask,needBgShow){
				this.init(title || "错误",msg,null,needBgShow);
				this.open(showMask || false, 0, false);
			},
			// 确认提示窗口
			confirm	: function(title,msg,sucess,showMask,needBgShow){
				this.init(title,msg,sucess,needBgShow);
				this.open(showMask || true, 0, true);
			}
		},
		/*
		 * 提示气泡简易封装
		 */
		tips	: function(node, msg, color, time){
			$('.jq_tips_box').remove();
			currTips = node.tips({
				side	:1,
	            msg		:msg || '',
	            bg 		:color || "#FF5080",
	            time 	:time || 5
			});
		},
		/*
		 * PDF转化为跑马灯
		 */
		pdfFile : function(_content,_div){
			$.ajax({
				url 	 : _content+"?odconv/jpg/info",
				type 	 : "post",
				dataType : "json",
				success  : function(data, textStatus, XMLHttpResponse) {
					_num = data.page_num;
					console.log(data.page_num);
					var _html = '<ul class="roundabout" id="myroundabout">';
					for(var i=1;i<=_num;i++){
						_html += '<li><img style="width: 100%" src="'+ _content +'?odconv/jpg/page/'+ i +'"></li>'
					}
					_html += '</ul>';
					_div.append(_html);
					//$('#myroundabout').roundabout({
					//	autoplay: true,//自动播放
					//	autoplayDuration: 3000,//时间
					//	autoplayPauseOnHover: true,//自动鼠标移上停滞
					//	shape: 'figure8',  //支持属性theJuggler、figure8、waterWheel、square、conveyorBeltLeft、conveyorBeltRight、goodbyeCruelWorld、diagonalRingLeft、diagonalRingRight、rollerCoaster、tearDrop、tickingClock、flurry、nowSlide、risingEssence随便换
					//	minOpacity: 1
					//});
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					console.log(textStatus);
				}
			});
		}
	}
	});
	/****************************************************
	 *  扩展$挂件库 
	 ****************************************************/
	/*
	 * 七牛云上传插件-图片
	 */
	$.widget("hydee.fileUpload", {
		defualtTypeLimit :"jpg|png|gif|jpeg",
		tipsDom : null,
        options  :{
        	uptokenUrl	:mvcPath+"/qiniu/uptoken",
        	browseBtn	:"",
        	domain		:"http://hydee.xiaomi.hydee.cn/",
        	limit		:"100mb",
        	typeLimit	:"",
        	delIcon		:"data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAACAAAAAgCAYAAABzenr0AAAA/0lEQVRYhe2WIQ7CQBBFn0BU9Bg9QA+AQCAQCERFZQ9QwQEQTRAIDoBAcAQEAoHgQD1ARUUROw1Ns6VbdkNCsi8Z0WZm/s/sph3weMwJgCfQjMRdcp2TiEAOZAORS87chWAkom3zszQfEm+jAQ6d50R6TWIL1IyPe0rsTMUjET8C4VTnGkJgLyaMJtGetcuLFEjPhUlyipqAa2pTAwHvW7wC1hai3folX0z1ItFlBsSa3FgjoKu3NpAAlSa3Qh2fcwPX3rsN6kL1ab8RY/XegDfgDXgD/2/gl/WcgIdF/Q3Lf0GKGm3B+C7YjwK1A2Q2BkDtciXTd8AStZx6PB95AXJbj/rWJggbAAAAAElFTkSuQmCC",
        	defualtIcon	:"",
        	limitFile	:1,		// 可扩展的上传单元上限
        	html		:"",	// 扩展上传单元html代码块
        	defualtSize	:120	// 默认元素宽高像素
        },
        uploader :null,
        _create	 :function (){
        	var _this = this.element,_that = this,
        		_img = _this.attr("value") || "none",
        		_defualtImg = _this.attr("defualt") || _that.options.defualtIcon;
        	_that.options.typeLimit = _this.attr("type") || _that.defualtTypeLimit;
        	if(_this.attr("tips") != null){
        		_that.tipsDom = $("#"+_this.attr("tips"));
        	}
        	if(!_this.hasClass("qiniuFileUpload")) _this.addClass("qiniuFileUpload");
        	this.options.html = $("<p>").append(_this.clone()).html();
        	// 追加插件样式
        	if(!$('#widget_hd_fileUpload_style').length){
    	        var style='<style id="widget_hd_fileUpload_style" type="text/css">';
    	        style += '.hd_fileUpload_del_icon{index-z:1;position: absolute;bottom: 2px;right: 2px;height: 32px;width: 32px;text-align: right; overflow: hidden; display: none;}';
    	        style += '.hd_fileUpload_img{float: left;index-z:-1;}'
    	        style += '</style>';
    	        $(document.body).append(style);
    	    }
			_this.css("position","relative");
			_this.css("float","left");
			_this.css("margin-left","5px");
			console.log(_this.width() + ":" + _this.css("width"));
			console.log(_this);
			if(_this.css("width") == "" || _this.width() == 0) _this.width(_that.options.defualtSize);
			if(_this.css("height") == "" || _this.height() == 0) _this.height(_that.options.defualtSize);
			if(_that.options.typeLimit == _that.defualtTypeLimit){
				// 完成插件html代码块
				var _html  = "<img class='hd_fileUpload_del_icon' src='"+_that.options.delIcon+"'/>";
				_html += "<img class='hd_fileUpload_img' width='"+ _this.width()+"px' height='"+ _this.height()+"px' src='"+_img+"' onerror='$(this).attr(\"src\",\""+_defualtImg+"\")'/>";
				$(_html).appendTo(_this);
				_this.mouseover(function(){
					if(_this.attr("value") != '') _this.find(".hd_fileUpload_del_icon").show();
				}).mouseout(function(){
					_this.find(".hd_fileUpload_del_icon").hide();
				});
				_this.find(".hd_fileUpload_del_icon").click(function(e){
					if (e.stopPropagation){
						e.stopPropagation();
					}else{
						e.cancelBubble = true;
					}
					if(window.confirm("确认删除此图片吗?")){
						_that.delFile();
					}
				});
			}
        },
        delFile	 : function(callback){
        	var _that = this,_this = this.element,_defualtImg = _this.attr("defualt") || _that.options.defualtIcon;
        	$.ajax({
    			url 	 : mvcPath+"/qiniu/delFile",
    			type 	 : "post",
    			dataType : "json",
    			data 	 : {fileLink :_this.attr("value")},
    			success  : function(data, textStatus, XMLHttpResponse) {
    				if(_that.options.limitFile > 1 && _this.siblings('.qiniuFileUpload[value=]').length > 0){
    					_that.uploader = null;
    					_this.remove();
    				}
    				if(typeof callback == "function"){
    					callback(data);
    				}else{
    					if(data.result){
    						_this.find(".hd_fileUpload_img").attr("src", _defualtImg);
    						_this.attr("value","");
    					}else{
							$.hd.alertMsg.error(data.errors || "服务器繁忙,稍后请重试!");
    					}
    				}
    			}
    		});
        },
        _init	: function (){
        	var _that = this,_this = this.element,_id = _this.attr("id"),_limit = _this.attr("limit") || "1";
        		
        	if(typeof Qiniu == "undefined"){
        		throw Error("Qiniu's SDK is not import!");
        	}
        	if(_this.attr("id") == null || _this.attr("id") == ""){
        		_this.attr("id","qiniu_fup_"+new Date().getTime())
        	}
        	this.options.browseBtn = _this.attr("id");
        	this.options.limitFile = parseInt(_limit);
            //引入Plupload 、qiniu.js后
            this.uploader = Qiniu.uploader({
                runtimes: 'html5,html4',	    			//上传模式,依次退化
                browse_button: this.options.browseBtn,      //上传选择的点选按钮，**必需**
                uptoken_url: this.options.uptokenUrl, 		//Ajax请求upToken的Url，**强烈建议设置**（服务端提供）
                unique_names: true,							// 默认false，key为文件名。若开启该选项，JS-SDK会为每个文件自动生成key（文件名）
                domain: this.options.domain,   				//bucket 域名，下载资源时用到，**必需**
                get_new_uptoken: true,						//设置上传文件的时候是否每次都重新获取新的token
                max_file_size: this.options.limit,          //最大文件体积限制
                max_retries: 2,                   			//上传失败最大重试次数
                chunk_size: '4mb',                			//分块上传时，每片的体积
                auto_start: false,                 			//选择文件后自动上传，若关闭需要自己绑定事件触发上传
                init: {
                    'FilesAdded': function(up, files) {
                        plupload.each(files, function(file) {
                        	var _typeReg = new RegExp(_that.options.typeLimit+"$");
                        	console.log("当前文件mime类型: "+file.type);
                        	// 文件添加进队列后,处理相关的事情
                        	if(file.type.match(_typeReg)){
                        		if(_this.attr("value") != ""){
                        			_that.delFile(function(){
                        				up.start();
                        			});
                        		}else{
                        			up.start();
                        		}
                        	}else{
                        		console.error("抱歉,暂不支持你选择的文件格式。");
								_that.tipsDom.tips({
									side : 1,
									msg : "抱歉,暂不支持你选择的文件格式。",
									bg : '#FF5080',
									time : 15
								});
                        	}
                        });
                    },
                    'UploadProgress': function(up, file) {
                    	if(_that.tipsDom != null && _that.tipsDom.length > 0){
                    		_that.tipsDom.html("正在上传...(已完成"+up.total.percent+"%)");
                    	}
                    },
                    'FileUploaded': function(up, file, info) {
                        var domain = up.getOption('domain'),
                        	res = JSON.parse(info),
                        	sourceLink = domain + res.key, //获取上传成功后的文件的Url
                        	fileSize = _this.siblings('.qiniuFileUpload').length + 1,
                        	fileLimit = _that.options.limitFile;
                        _this.attr("value",sourceLink);
                        if(_that.tipsDom != null && _that.tipsDom.length > 0){
                    		_that.tipsDom.html("已上传");
                    	}
                        if(_that.options.typeLimit == 'jpg|png|gif|jpeg'){
	                        _this.find(".hd_fileUpload_img").attr("src",sourceLink);
	                        // 自动追加下一个上传框
	                        if(fileLimit > 1 && fileSize < fileLimit){
	                        	var _newUpload = $(_that.options.html).appendTo(_this.parent());
	                        	_newUpload.fileUpload();
	                        }
                        }
                    },
                    'Error': function(up, err, errTip) {
                    	//上传出错时,处理相关的事情
                    	console.log(up,err,errTip);
                    }
                }
            });
        },
        destroy	:function () {
        	this.uploader = null;
        }
    });
    /*
     * 分页插件脚本
     */
    $.widget("hydee.pagenation",{
    	options  :{
    		pageSize	:5,
    		count		:1,
    		pageIndex	:1,
    		pageCount	:0,
    		form		:"",
    		filter		:"#filter"
    	},
    	_create	 :function(){
    		var _this = this.element,_that = this,_count,_pageSize,_totalPage;
    		this.options.count = _count = parseInt(_this.attr("count") || this.options.count);
    		this.options.pageIndex = _index = parseInt(_this.attr("page") || this.options.pageIndex);
    		this.options.pageSize = _pageSize = parseInt(_this.attr("size") || this.options.pageSize);
    		this.options.form = _this.attr("form");
    		this.options.filter = _this.attr("filter") || this.options.filter;
			_totalPage = parseInt(_count/_pageSize);
    		this.options.pageCount = _count < _pageSize ? 1 :
    				(_count % _pageSize == 0 ? _totalPage : (_totalPage + 1));
    		console.log(this.options.pageCount);
    		var _html  = "<div class=\"pageSum\">每页 "+this.options.pageSize+" 条  共 "+(this.options.pageCount)+" 页</div><div class=\"pageDetail\">";
	    		_html += "<div class=\"pager\"><a class='pref' href=\"javascript:;\" >上一页</a>";
	    		_html += "<span class=\"font-color-crimson\" >"+(this.options.pageIndex)+"</span>";
	    		_html += "<a class='next' href=\"javascript:;\">下一页</a></div>";
	    		_html += '<div class="goToPage">共'+(this.options.pageCount)+'页 跳转至 <input id="currentPage" type="text" class="width50">页<input type="button" class="btn-hover-crimson" value="确定"></div></div>';
    		$(_html).appendTo(_this);
    		if(this.options.pageIndex <= 1) _this.find(".pref").attr("disabled","disabled");
    		if(this.options.pageIndex >= this.options.pageCount) _this.find(".next").attr("disabled","disabled");
    	},
    	_init	:function(){
    		var _this = this.element,_that = this;
    		// 上一页,下一页
    		_this.find(".pager a").click(function(){
    			var __this = $(this);
    			if(__this.attr("disabled") != null) return false;
    			var _currentPage = __this.hasClass("pref") ? Math.max(0,_that.options.pageIndex-1) : Math.min(_that.options.pageCount,_that.options.pageIndex+1);
    			_that.loadPage(_currentPage);
    		});
    		// 跳转
    		_this.find(".btn-hover-crimson").click(function(){
    			var _currentPage = Math.min(_that.options.pageCount, parseInt(_this.find("#currentPage").val() || "1"));
    				_currentPage = Math.max(0,_currentPage);
    			_that.loadPage(_currentPage);
    		});
    	},
    	loadPage :function(_currentPage){
    		var _params = $(this.options.filter).params();
    		_params = $.extend({
				showCount	:this.options.pageSize,
				currentPage	:_currentPage
			},_params);
			// 重新加载页面
			loadingPage(this.options.form, _params);
    	}
    });

})(jQuery);
