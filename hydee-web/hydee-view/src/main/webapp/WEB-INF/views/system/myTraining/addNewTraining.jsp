<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/include.inc.jsp"%>
<style>
    .questionList li{
        white-space:nowrap;overflow:hidden;text-overflow:ellipsis;
    }
</style>
<div class="right-main paddingLR40">
    <div class="div-ui-divide marginRight10 marginTop20"><span class="span-ui-dot"></span><span>培训课件基本信息</span></div>
    <form id="addClass" class="red">
        <div class="marginRight10">
            <div class="login-main">
                <ul class="ul-info table-height30">
                    <li class="width100 textAlignR marginRight20">课件标题</li>
                    <li>
                        <label class="input">
                            <input type="hidden" id="classId" value="${companyTrainClass.classId}"/>
                            <input type="hidden" id="limitCount" value="${limitCount}"/>
                            <input type="hidden" id="contentType" value="${companyTrainClass.contentType}"/>
                            <label class="inputLabel" for="title" id="titleNameLabel">10字以内</label>
                            <input type="text" id="title" value="${companyTrainClass.title}" ctype="required" class="labelInput width200" placeholder="10字以内" autocomplete="off" maxlength="10">
                        </label>
                    </li>
                    <div class="clear"></div>
                </ul>
                <ul class="ul-info heightAuto vertical-align-top">
                    <li class="width100 textAlignR marginRight20">课件内容</li>
                    <li class="width400 lineHeight20 ">
                        <label  >
                            <input type="radio" name="contentType" id="contentArea" checked="checked" value="0" />文本编辑器
                        </label>
                        <label>
                            <input type="radio" name="contentType" id="contentUpload" value="1"/>文件上传(仅支持PDF格式)
                        </label>
                    </li>
                    <div class="clear"></div>
                </ul>
                <ul>
                    <li class="width100 textAlignR marginRight20"></li>
                    <li class="textAlignC">
                        <div style="margin-top: 30px;" id="editor">
                            <textarea name="CKEditor" id="content">${companyTrainClass.content}</textarea>
                        </div>
                        <div id="upload" class="marginLeft30"><a id="uploadButton" tips="showUpload" value="${companyTrainClass.content}" class="width80 fakeBtn-hover-crimson floatLeft" type="PDF|pdf" >上传文件</a></div><%-- |msword|ppt|vnd.ms-excel|docx|xlsx|pptx --%>
                        <span id="showUpload" class="fakeBtn-hover-crimson"></span>
                    </li>
                    <div class="clear"></div>
                </ul>

                <ul class="ul-info heightAuto marginTop10">
                    <li class="width100 marginRight20 textAlignR">培训习题</li>
                    <li class="width170 "><a class="font-color-blue addQuestion">添加</a><span class="font-color-666 marginLeft10">(最多${limitCount}题)</span></li>
                    <div class="clear"></div>
                </ul>
                <ul class="questionList paddingLeft120">
                    <c:forEach items="${exercisesList}" var="item">
                        <li class="li-question" title="${item.content}" tabType="${item.type}" tabA="${item.optionA}" tabB="${item.optionB}" tabC="${item.optionC}" tabD="${item.optionD}" rightAnswer="${item.answer}">
                            <a href="javascript:;" class="questionDetail">${item.content}
                            <c:choose>
                                <c:when test="${item.type == 'singleTab'}">
                                    (单选)
                                </c:when>
                                <c:otherwise>
                                    (多选)
                                </c:otherwise>
                            </c:choose>
                            </a>
                            <span class="questionDelete" onclick="deleteParent($(this))">删除
                            </span>
                        </li>
                    </c:forEach>
                    <%--<li class="li-question" title="螺旋藻中对降低高血脂有辅助功效的成分是什么?" tabType="singleTab" tabA="螺旋藻A?" tabB="螺旋藻B?" tabC="螺旋藻C?" tabD="螺旋藻D?" rightAnswer="A"><a href="javascript:;" class="questionDetail"> 螺旋藻中对降低高血脂有辅助功效的成分是什么?(单选)</a><span class="questionDelete" onclick="deleteParent($(this))">删除</span></li>--%>
                </ul>
                <div class="btn-noPosition marginTop20">
                    <input type="button" onclick="addClass()" class="btnForSure btn-hover-crimson marginRight10 width80" value="保存">
                    <input type="button" class="btnCancel btn-line-crimson width80" onclick="returnTrainClassList()"  value="取消">
                </div>
            </div>
        </div>
    </form>
    <div class="shadowWhite displayNone addQuestionShadow">
        <div class="contentWrapper">
            <div class="content">
                <ul class="ul-info heightAuto">
                    <li class="width80 textAlignR  marginRight20">题干</li>
                    <li>
                        <textarea rows="3" class="width480" id="questionTitle"></textarea>
                    </li>
                    <div class="clear"></div>
                </ul>
                <ul class="ul-info table-height30 questionR">
                    <li class="width80 textAlignR  marginRight20">题型</li>
                    <li>
                        <label>
                            <input type="radio" value="multiTab" class="questionType" checked="checked" name="type" id="multiTab">多选
                        </label>
                        <label class="marginRight10" >
                            <input type="radio" value="singleTab" class="questionType" name="type" id="singleTab">单选
                        </label>
                    </li>
                    <div class="clear"></div>
                </ul>
                <ul class="ul-info table-height30">
                    <li class="width80 textAlignR  marginRight20">选项A</li>
                    <li>
                        <input type="text" class="width480" id="questionA">
                    </li>
                    <div class="clear"></div>
                </ul>
                <ul class="ul-info table-height30">
                    <li class="width80 textAlignR  marginRight20">选项B</li>
                    <li><input type="text" class="width480" id="questionB"></li>
                    <div class="clear"></div>
                </ul>
                <ul class="ul-info table-height30">
                    <li class="width80 textAlignR  marginRight20">选项C</li>
                    <li><input type="text" class="width480" id="questionC"></li>
                    <div class="clear"></div>
                </ul>
                <ul class="ul-info table-height30">
                    <li class="width80 textAlignR  marginRight20">选项D</li>
                    <li><input type="text" class="width480" id="questionD"></li>
                    <div class="clear"></div>
                </ul>
                <ul class="ul-info table-height30 answerR">
                    <li class="width80 textAlignR marginRight20">答案</li>
                    <li class="vertical-align-middle">
                        <label class="marginRight10" for="A"><input type="checkbox" name="checkType" id="A" class="fakeRadio">A</label>
                        <label class="marginRight10" for="B"><input type="checkbox" name="checkType" id="B" class="fakeRadio">B</label>
                        <label class="marginRight10" for="C"><input type="checkbox" name="checkType" id="C" class="fakeRadio">C</label>
                        <label for="D"><input type="checkbox" name="checkType" id="D" class="fakeRadio">D</label>
                    </li>
                    <div class="clear"></div>
                </ul>
            </div>
            <div class="btn-wraper">
                <input type="button" class="btnForSure btn-hover-crimson marginRight10 width80" value="保存">
                <input type="button" class="btnCancel btn-line-crimson width80" value="取消">
            </div>
        </div>
    </div>
</div>
<div class="clear"></div>
<div class="bgShow"></div>

<script type="text/javascript">
    var _limitCount = $("#limitCount").val(),
        _contentType = $('#contentType').val() == '' ? 0 : $('#contentType').val(),
    	myeditor,_questionType,_content;
    $(function()
    {
        myeditor = CKEDITOR.replace('content');
        $('#upload').hide();
        var _editor = $('#editor');
        var _upload = $('#upload');
        $('input[name = "contentType"]').change(function(){
            $('input[name = "contentType"]').removeAttr("checked");
            var _type = $(this).val();
            $('input[name = "contentType"][value = "'+ _type +'"]').attr("checked","checked");
            _contentType = _type;
            if(_type == 0) {
                _editor.show();
                _upload.hide();
            }
            if(_type == 1){
                _upload.show();
                _editor.hide();
            }

        });

        $('input[name = "contentType"][value = "'+ _contentType +'"]').attr("checked","checked");
        if(_contentType == 0){
            _editor.show();
            _upload.hide();
        }else{
            _upload.show();
            _editor.hide();
        }
        $('input[name = "contentType"][value = "'+ 1 +'"]').mouseover(function(){
            if(_contentType == 0){
                $.hd.tips($('input[name = "contentType"][value = "'+ 1 +'"]'),"您正在编辑课件内容，切换至文本上传后，内容将会被清空。");
            }
        });
        initQuestion();
        $("#uploadButton").fileUpload();

    });
    function returnTrainClassList(){
        loadingPage("/view/trainClass");
    }

    function initQuestion(){
        $('.questionType').change(function(){
            var selected = $(this).val();
            console.log(selected);
            $(':checkbox[name="checkType"]').removeAttr('checked');
            if(selected == 'singleTab'){
                $('.answerR input[type="checkbox"]').each(function(){
                    $(this).click(function(){
                        if($(this).is(':checked')){
                            $(':checkbox[name="checkType"]').removeAttr('checked');
                            $(this).attr('checked','checked');
                        }
                    });
                });
            }else if(selected == 'multiTab'){
                $('.answerR input[type="checkbox"]').unbind();
                $(':checkbox[name="checkType"]').removeAttr('checked');
            }
        });
        _questionType = $('.questionR').find("input[type='radio']:checked").attr("id");
        console.log(_questionType);
    }

    function addClass(){
        var _params = $(".login-main").params();
        var _exercises=[];
        if(_params != null){
            $('.questionList li').each(function(index){
                var _jsonValue = {};
                var _options = [];
                var _title=$(this).attr('title');
                var _tabType=$(this).attr('tabType');
                var _tabA=$(this).attr('tabA');
                var _tabB=$(this).attr('tabB');
                var _tabC=$(this).attr('tabC');
                var _tabD=$(this).attr('tabD');
                var _rightAnswer=$(this).attr('rightAnswer');
                _jsonValue.title = _title;
                _jsonValue.tabType = _tabType;
                _jsonValue.options = [];
//                var reg = /^tab[ABCDEF]{1}$/;

//                console.log($(this).attributes.attr('tabD'));
                _jsonValue.options.push({name: "A",content :_tabA});
                _jsonValue.options.push({name: "B",content :_tabB});
                _jsonValue.options.push({name: "C",content :_tabC});
                _jsonValue.options.push({name: "D",content :_tabD});
                _jsonValue.rightAnswer = _rightAnswer;
                _exercises.push(_jsonValue);
            });
            var oEditor = CKEDITOR.instances.content;
            if(1 == _contentType){
                _content = $("#uploadButton").attr("value");
                console.log(_content);
            }else{
                _content = oEditor.getData();
            }
            if(_exercises.length > _limitCount){
                $.hd.alertMsg.error("习题最多"+ _limitCount +"个！");
                return ;
            }
            if(_exercises.length <= 0){
                $.hd.tips($('.addQuestion'),'培训习题不能为空');
                return ;
            }
            if(_content == ''){
                $.hd.tips($('#contentUpload'),'课件任务不能为空');
                return ;
            }
            _params.content = _content;
            _params.contentType = _contentType;
            _params.exercises=JSON.stringify(_exercises);
            console.log(_params);
            // 请求新增
            $.ajax({
                url 	 : "${mvcPath}/view/trainClass/saveOrUpdateClass",
                type 	 : "post",
                dataType : "json",
                data 	 : _params,
                success  : function(data, textStatus, XMLHttpResponse) {
                    if(data.result){
                        $.hd.alertMsg.info("保存成功！");
                        loadingPage('/view/trainClass');
                    }else{
                        $.hd.alertMsg.error("服务器繁忙,稍后请重试!");
                    }
                },
                error : function(XMLHttpRequest, textStatus, errorThrown) {
                    console.log(textStatus);
                }
            });
        }
    }

    function deleteParent(obj){
//        if(confirm("确认删除？")){
//            obj.parent().remove();
//        }
        $.hd.alertMsg.confirm("删除","确认删除吗?",function(){
            obj.parent().remove();
        });
    }
</script>