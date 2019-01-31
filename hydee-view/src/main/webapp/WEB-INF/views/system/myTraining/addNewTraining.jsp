<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/include.inc.jsp"%>

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
                            <input type="hidden" id="baseId" value="${companyTrainClass.baseId}"/>
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

                <div class="div-ui-divide marginRight10"><span class="span-ui-dot"></span><span>培训习题</span></div>
                <div class="marginTop20 marginBottom20">
                    <span class="table-height30  floatLeft marginTop-1">当前选择的是:</span>
                    <span class="table-height30  selectedExercise">${trainBase.title}</span>
                    <div class="floatRight marginRight10">
                        <label class="positionRelative width250">
                            <input type="text" class="labelInput width250" id="searchTxt" autocomplete="off" placeholder="按标题搜索">
                            <input type="button" onclick="searchBase()" class="btn-hover-blue searchBtn" id="searchBtn" value="搜索">
                        </label>
                    </div>
                    <div class="clear"></div>
                </div>
                <div id="classDiv">
                    <ul class="ul-info heightAuto trainingBase" id="classUl">
                        <li></li>
                        <c:forEach items="${trainBases}" var="trainBase">
                            <li class="width290 marginRight10 marginBottom10" title="${trainBase.title}">
                                <label>
                                    <c:if test="${trainBase.baseId == companyTrainClass.baseId}">
                                        <input type="radio" checked="checked" name="question" class="question" data="${trainBase.title}" value="${trainBase.baseId}" value="radio">${trainBase.title}
                                    </c:if>
                                    <c:if test="${trainBase.baseId != companyTrainClass.baseId}">

                                        <input type="radio" name="question" class="question" data="${trainBase.title}" value="${trainBase.baseId}" value="radio">${trainBase.title}
                                    </c:if>
                                </label>
                            </li>
                        </c:forEach>
                        <div class="clear"></div>
                    </ul>
                </div>

                <div class="btn-noPosition marginTop20">
                    <input type="button" onclick="addClass()" class="btnForSure btn-hover-crimson marginRight10 width80" value="保存">
                    <input type="button" class="btnCancel btn-line-crimson width80" onclick="returnTrainClassList()"  value="取消">
                </div>
            </div>
        </div>
    </form>

</div>
<div class="clear"></div>

<script type="text/javascript">
    var _limitCount = $("#limitCount").val(),
        _contentType = $('#contentType').val() == '' ? 0 : $('#contentType').val(),
    	myeditor,_questionType,_content,_baseId = $('#baseId').val();
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
        $("#uploadButton").fileUpload();

        $('.question').change(function() {
            _baseId = $('input[name="question"]:checked').val();
            $('.selectedExercise').html($('input[name="question"]:checked').attr('data'));
        });
    });
    function returnTrainClassList(){
        loadingPage("/view/trainClass");
    }



    function addClass(){
        var _params = $(".login-main").params();
        if(_params != null){

            var oEditor = CKEDITOR.instances.content;
            if(1 == _contentType){
                _content = $("#uploadButton").attr("value");
                console.log(_content);
            }else{
                _content = oEditor.getData();
            }
            if(_content == ''){
                $.hd.tips($('#contentUpload'),'课件任务不能为空');
                return ;
            }
            console.log(_baseId);
            if(null == _baseId || '' == _baseId){
                $.hd.tips($(".selectedExercise"),"培训习题不能为空");
                return ;
            }
            _params.content = _content;
            _params.contentType = _contentType;
            _params.baseId=_baseId;
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

    /**
     *模糊搜索课件
     */
    function searchBase(){
        var _classDiv = $("#classDiv");
        var _searchTxt = $("#searchTxt");

        if(_classDiv.css("display") == 'none'){
            _classDiv.css('display','block');
        }
        var _name = _searchTxt.val();
        var v = _name.replace(/^\s+|\s+$/g,"");
        var trs = _classDiv.find("li:gt(0)");
        console.log(trs);
        trs.filter("::visible").hide();
        if(v == ""){
            trs.filter(":hidden").show();
        }else{
            trs.hide().filter(":contains('"+_searchTxt.val()+"')").show();
        }
    }
</script>