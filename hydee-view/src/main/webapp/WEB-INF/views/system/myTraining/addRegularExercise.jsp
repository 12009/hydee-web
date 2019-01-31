<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/include.inc.jsp" %>
<style>
    .questionList li{
        white-space:nowrap;overflow:hidden;text-overflow:ellipsis;
    }
</style>
<div class="right-main">
    <div class="div-ui-divide marginRight10 marginTop20"><span class="span-ui-dot"></span><span>添加培训习题</span><span style=" float:right; margin-right:20px;"><a style="color:#c6244b;" onclick="loadingPage('/view/trainingExercises')" >返回习题列表</a></span></div>
    <div class="marginRight10">
        <div class="login-main">
            <ul class="ul-info table-height30">
                <input type="hidden" id="limitCount" value="${limitCount}"/>
                <input type="hidden" id="baseId" name="baseId" value="${companyTrainBase.baseId}"/>
                <li class="width100 textAlignR marginRight20">习题名称</li>
                <li>
                    <label class="input">
                        <label class="inputLabel" id="titleNameLabel">10字以内</label>
                        <input type="text" value="${companyTrainBase.title}" id="title" ctype="required" class="labelInput width200" placeholder="10字以内" autocomplete="off" maxlength="10">
                    </label>
                </li>
                <div class="clear"></div>
            </ul>
            <ul class="ul-info table-height30">
                <li class="width100 textAlignR marginRight20">习题类别</li>
                <li>
                    <select class="classTypeId" style="height:30px; width:100px;">
                        <c:forEach items="${exercisesTypes}" var="types">
                            <c:if test="${companyTrainBase.classTypeId == types.classTypeId}">
                                <option value="${types.classTypeId}" selected="selected">${types.name}</option>
                            </c:if>
                            <c:if test="${companyTrainBase.classTypeId != types.classTypeId}">
                                <option value="${types.classTypeId}">${types.name}</option>
                            </c:if>
                        </c:forEach>
                    </select>
                </li>
                <li class=" marginLeft20 addExerciseType"><a class="font-color-blue">添加习题分类</a></li>
                <div class="clear"></div>
            </ul>

            <ul class="ul-info heightAuto marginTop10">
                <li class="width100 marginRight20 textAlignR">习题添加</li>
                <li class="width170 "><a class="font-color-blue addQuestion">添加</a><span class="font-color-666 marginLeft10">(最多添加${limitCount}道题目)</span></li>
                <div class="clear"></div>
            </ul>
            <%-- 习题列表 --%>
            <ul style="width:1160px;" class="questionList paddingLR40 ul-info heightAuto marginTop10">
                <c:forEach items="${trainExercisesList}" var="item">
                    <li class="li-question" style="float:left; width:330px; height:220px;" title="${item.content}" rightAnswer="${item.answer}" tabType="${item.type}"
                        <c:choose>
                            <c:when test="${item.type == 'judgeTab'}">
                                tabA="正确" tabB="错误"
                            </c:when>
                            <c:otherwise>
                                <c:forEach items="${item.optionsList}" var="option">
                                    tab${option.optionNo}="${option.content}"
                                </c:forEach>
                            </c:otherwise>
                        </c:choose>
                         >
                        <c:choose>
                            <c:when test="${item.type == 'singleTab'}">
                                (单选)
                            </c:when>
                            <c:when test="${item.type == 'judgeTab'}">
                                (判断)
                            </c:when>
                            <c:otherwise>
                                (多选)
                            </c:otherwise>
                        </c:choose>
                        <a href="javascript:;" class="questionDetail">${item.content}
                        </a>
                        <c:choose>
                            <c:when test="${item.type == 'judgeTab'}">
                                答案：
                                <c:if test="${item.answer == 'A'}">
                                    正确
                                </c:if>
                                <c:if test="${item.answer == 'B'}">
                                    错误
                                </c:if>
                                <span class="questionDelete" onclick="deleteParent($(this))">删除</span>
                                <br><span tabA="正确" tabB="错误"></span>
                            </c:when>
                            <c:otherwise>
                                答案：${item.answer}
                                <span class="questionDelete" onclick="deleteParent($(this))">删除</span>
                                <c:forEach items="${item.optionsList}" var="option">
                                    <br><span tab${option.optionNo} = '${option.content}'>选项${option.optionNo}:${option.content}</span>
                                </c:forEach>
                            </c:otherwise>
                        </c:choose>
                    </li>
                </c:forEach>
            </ul>
            <div class="clear"></div>
        </div>

    </div>
    <div class="btn-noPosition marginTop20">
        <input type="button" onclick="addExerciseBase()" class="btnForSure btn-hover-crimson marginRight10 width80" value="保存">
        <a onclick="loadingPage('/view/trainingExercises')"><input type="button" class="btnCancel btn-line-crimson width80" value="取消"></a>
    </div>
</div>
<div class="shadowWhite displayNone addQuestionShadow">
    <div class="contentWrapper">
        <div class="content">
            <ul class="ul-info heightAuto">
                <li class="width80 textAlignR  marginRight20">题干</li>
                <li>
                    <textarea rows="3" class="width480" maxlength="100" placeholder="100字以内"></textarea>
                </li>
                <div class="clear"></div>
            </ul>
            <ul class="ul-info table-height30 questionR">
                <li class="width80 textAlignR  marginRight20">题型</li>
                <li>
                    <label>
                        <input type="radio" class="questionType" value="multiTab" checked="checked" name="type" id="multiTab">多选
                    </label>
                    <label class="marginRight10" >
                        <input type="radio" class="questionType" value="singleTab" name="type" id="singleTab">单选
                    </label>
                    <label >
                        <input type="radio" name="type" value="judgeTab" class="questionType" id="judgeTab">判断题
                    </label>
                </li>
                <div class="clear"></div>
            </ul>
            <ul class="ul-info table-height30 optionA">
                <li class="width80 textAlignR  marginRight20">选项A</li>
                <li>
                    <input type="text" class="width480 optionTxt" rid="A" id="optionA" placeholder="300字以内" maxlength="300" onblur="blurText(this,'A')">
                </li>
                <div class="clear"></div>
            </ul>
            <ul class="ul-info table-height30 optionB">
                <li class="width80 textAlignR  marginRight20">选项B</li>
                <li><input type="text" class="width480 optionTxt" rid="B" id="optionB" placeholder="300字以内" maxlength="300" onblur="blurText(this,'B')"></li>
                <div class="clear"></div>
            </ul>
            <ul class="ul-info table-height30 optionC">
                <li class="width80 textAlignR  marginRight20">选项C</li>
                <li><input type="text" class="width480 optionTxt" rid="C" id="optionC" placeholder="300字以内" maxlength="300" onblur="blurText(this,'C')"></li>
                <div class="clear"></div>
            </ul>
            <ul class="ul-info table-height30 optionD">
                <li class="width80 textAlignR  marginRight20">选项D</li>
                <li><input type="text" class="width480 optionTxt" rid="D" id="optionD" placeholder="300字以内" maxlength="300" onblur="blurText(this,'D')"></li>
                <div class="clear"></div>
            </ul>
            <ul class="ul-info table-height30 optionE">
                <li class="width80 textAlignR  marginRight20">选项E</li>
                <li><input type="text" class="width480 optionTxt" rid="E" id="optionE" placeholder="300字以内" maxlength="300" onblur="blurText(this,'E')"></li>
                <div class="clear"></div>
            </ul>
            <ul class="ul-info table-height30 optionF">
                <li class="width80 textAlignR  marginRight20">选项F</li>
                <li><input type="text" class="width480 optionTxt" rid="F" id="optionF" placeholder="300字以内" maxlength="300" onblur="blurText(this,'F')"></li>
                <div class="clear"></div>
            </ul>
            <ul class="ul-info table-height30 answerR">
                <li class="width80 textAlignR marginRight20">答案</li>
                <li class="vertical-align-middle">
                    <label class="marginRight10" for="A"><input type="checkbox" id="A" name="checkType" class="fakeRadio"><span id="checkA">A</span></label>
                    <label class="marginRight10" for="B"><input type="checkbox" id="B" name="checkType" class="fakeRadio"><span id="checkB">B</span></label>
                    <label class="marginRight10" for="C"><input type="checkbox" id="C" name="checkType" class="fakeRadio">C</label>
                    <label class="marginRight10" for="D"><input type="checkbox" id="D" name="checkType" class="fakeRadio">D</label>
                    <label class="marginRight10" for="E"><input type="checkbox" id="E" name="checkType" class="fakeRadio">E</label>
                    <label for="F"><input type="checkbox" id="F" name="checkType" class="fakeRadio">F</label>
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
<div class="shadowWhite displayNone increaseBonus"><!--添加习题分类-->
    <div class="contentWrapper">
        <div class="content">
            <div class="detailTitle">添加习题分类</div>
            <div class="div-line"></div>
            <ul class="ul-info table-height30" style="margin-top:60px;">
                <li class="width80">习题类别</li>
                <li class="width180"><input type="text" id="exercisesType" class="width250"></li>
                <div class="clear"></div>
            </ul>

        </div>
        <div class="btn-wraper width360">
            <input type="button" value="确定" onclick="addExerciseType()" class="btn-hover-crimson marginRight10 btnExerciseTypeSure">
            <input type="button" value="取消" class="btn-line-crimson btnCancelType">
        </div>
    </div>
</div>

<div class="bgShow"></div>
<script type="text/javascript">
    var _questionType;
    var _limitCount = $("#limitCount").val();
    var _questionOption = ['A','B','C','D','E','F'];
    var addQ;
    var oldDom;
    var bgJudge=false;
    function stopProp(e){//阻止冒泡方法
        if(e.stopPropagation) e.stopPropagation();else e.cancelBubble = true;
    }
    $(function()
    {
        $("body").delegate(".addQuestion","click",function(e){
            stopProp(e);
            addQ="addQuestion";
            oldDom=$(".questionList");
            if(oldDom.find("li").length< _limitCount ){
                $(".addQuestionShadow").removeClass("displayNone");
                $(".bgShow").show();
                forbidCheckBox();
                bgJudge=true;
            }else{
                if(confirm("最多只能添加"+_limitCount+"个题目")){
                    obj.parent().remove();
                }
            }
        });

        $("body").delegate(".li-question a","click",function(e) {//新建培训课件，增加题目
            stopProp(e);
            addQ="a";
            var thisLi=$(this).parent();
            oldDom=thisLi;
            var shadow=$(".addQuestionShadow");
            shadow.removeClass("displayNone");
            $(".bgShow").show();
            bgJudge=true;
            shadow.find("textarea").val(thisLi.attr("title"));
            shadow.find("input[type=radio]"+"#"+thisLi.attr("tabType")).attr("checked","checked");
            for(var i=0;i<shadow.find("input[type='text']").length;i++){
                var choose = shadow.find("input[type=text]").eq(i).val(thisLi.attr("tab"+_questionOption[i]));
                if('' == choose) break;
            }
            var rightTab=thisLi.attr("rightanswer");
            showText();
            if(thisLi.attr("tabtype") == "singleTab" || thisLi.attr("tabtype") == "judgeTab"){
                shadow.find("input[type=checkbox]"+"#"+thisLi.attr("rightanswer")).attr("checked","checked");
                $('.answerR input[type="checkbox"]').each(function(){
                    $(this).click(function(){
                        if($(this).is(':checked')){
                            $(':checkbox[name="checkType"]').removeAttr('checked');
                            $(this).attr('checked','checked');
                        }
                    });
                });
                if(thisLi.attr("tabtype") == "judgeTab" ){
                    hideText();
                }
            }else{
                for(var i=0; i<rightTab.length; i++){
                    shadow.find("input[type=checkbox]").each(function(e){
                        if($(this).attr("id")==rightTab[i]){
                            $(this).attr("checked","checked");
                        }
                    })
                }
                $('.answerR input[type="checkbox"]').unbind();
            }
            shadow.find("input[type=radio]"+"#"+thisLi.attr("")).attr("checked","checked");
            forbidText();
            if(thisLi.attr("tabtype") != "judgeTab"){
                $('#A').attr("disabled", false);
                $('#B').attr("disabled", false);
            }else{
                $('#A').attr("disabled", true);
                $('#B').attr("disabled", true);
            }
        });

        $("body").delegate(".addQuestionShadow .btnForSure","click",function(e){
            //if(checkQuestionParams()){
            clearTest();
            var _checkBox = $('.answerR input[type = "checkbox"]').is(':checked');
            if(!_checkBox){
                $.hd.tips($('.answerR input[type = "checkbox"][id = "A"]'),"请选择答案！");
                return ;
            }
            stopProp(e);
            var thisGp=$(this).parent().parent();
            var title=thisGp.find("textarea").val();
            var tabType=thisGp.find("input[type='radio']:checked").attr("id");
            var rightTab="";
            thisGp.find("input[type='checkbox']:checked").each(function(e){
                rightTab+=$(this).attr("id");
            });
            var _chooseType = '';
            if(tabType.indexOf('judgeTab') != -1){
                _chooseType = '判断';
            }else if(tabType.indexOf('singleTab') != -1){
                _chooseType = '单选';
            }else{
                if(rightTab.length < 2){
                    $.hd.tips($('.answerR input[type = "checkbox"][id = "A"]'),"请选择多个答案！");
                    return ;
                }
                _chooseType = '多选';
            }
            var newDom = '<li  class="li-question" style="float:left; width:330px; height:220px;"  title='+title+' tabType='+tabType+' rightAnswer='+rightTab+' ';
            if(tabType.indexOf('judgeTab') != -1){
                newDom += 'tabA="正确"';
                newDom += 'tabB="错误"';
            }else{
                for(var j=0;j<thisGp.find("input[type='text']").length;j++){
                    var choose = thisGp.find("input[type='text']").eq(j).val();
                    if('' == choose) break;
                    newDom += 'tab'+_questionOption[j]+'="'+ choose+'"';
                }
            }
            newDom += '>';
            newDom += '<span>('+_chooseType+')</span>';
            newDom += '<a href="javascript:;" class="questionDetail">'+ title;
            if(tabType.indexOf('judgeTab') != -1){
                var _answer = '';
                if(rightTab.indexOf('A') != -1){
                    _answer = '正确';
                }else if(rightTab.indexOf('B') != -1){
                    _answer = '错误';
                }
                newDom += '<span>答案：'+_answer+'</span>';
                newDom += '</a><span style="margin-left:10px;" class="font-color-blue questionDelete" onclick="deleteParent($(this))">删除</span><br>';
            }else{
                newDom += '<span>答案：'+rightTab+'</span>';
                newDom += '</a><span style="margin-left:10px;" class="font-color-blue questionDelete" onclick="deleteParent($(this))">删除</span>';
                for(var i=0;i<thisGp.find("input[type='text']").length;i++){
                    var choose = thisGp.find("input[type='text']").eq(i).val();
                    if('' == choose) break;
                    newDom += '<br><span tab'+ _questionOption[i] +' = '+ choose +'>选项'+ _questionOption[i] +'：'+ choose +'</span>';
                }
            }
            //var newDom="<li class='li-question' title='"+title+"' tabType='"+tabType+"' tabA='"+tabA+"' tabB='"+tabB+"' tabC='"+tabC+"' tabD='"+tabD+"' rightAnswer='"+rightTab+"'><a href='javascript:;' class='questionDetail'>"+title+"</a><span class='questionDelete' onclick='deleteParent($(this))'>删除</span></li>";
            //var newDom=" <li class='li-question' title='"+thisGp.find("textarea").val()+"' tabType='"+thisGp.find("input[type='radio'] :checked").attr("id")+"' tabA='"+thisGp.find("input[type='text']").eq(0).val()+"' tabB='"+thisGp.find("input[type='text']").eq(1).val()+"' tabC='"+thisGp.find("input[type='text']").eq(2).val()+"' tabD='"+thisGp.find("input[type='text']").eq(3).val()+"' rightAnswer='"+thisGp.find("input[type='checkbox']:checked").each().attr("id")+"'><a href='javascript:;' class='questionDetail'>"+thisGp.find("textarea").val()+"</a><span class='questionDelete' onclick='deleteParent($(this))'>删除</span></li>";
            switch(addQ){
                case "addQuestion":oldDom.append(newDom);
                    break;
                case "a":oldDom.replaceWith(newDom);
                    break;
                default :
                    break;
            }
            thisGp.find("textarea").val("");
            thisGp.find("input[type='radio']").eq(0).click();
            thisGp.find("input[type='text']").val("");
            thisGp.find("input[type='checkBox']").removeAttr("checked");
            $(".addQuestionShadow").addClass("displayNone");
            $(".bgShow").hide();
            //}
        });

        $("body").delegate(".addQuestionShadow .btnCancel","click",function(e){
            stopProp(e);
            var thisGp=$(this).parent().parent();
            thisGp.find("textarea").val("");
            thisGp.find("input[type='radio']").eq(0).click();
            thisGp.find("input[type='text']").val("");
            thisGp.find("input[type='checkBox']").removeAttr("checked");
            $(".addQuestionShadow").addClass("displayNone");
            $(".bgShow").hide();
        });

        $('.addExerciseType').bind('click',function(){
            $(".increaseBonus,.bgShow").fadeIn();
        });
        $('.btnCancelType').bind('click',function(){
            $(".increaseBonus,.bgShow").fadeOut();
        });

        $('.questionType').change(function(){
            var selected = $(this).val();
            $(':checkbox[name="checkType"]').removeAttr('checked');
            showText();
            if(selected.indexOf('singleTab') != -1 || selected.indexOf('judgeTab') != -1){
                $('.answerR input[type="checkbox"]').each(function(){
                    $(this).click(function(){
                        if($(this).is(':checked')){
                            $(':checkbox[name="checkType"]').removeAttr('checked');
                            $(this).attr('checked','checked');
                        }
                    });
                });
                if(selected.indexOf('judgeTab') != -1){
                    hideText();
                }
            }else if(selected.indexOf('multiTab') != -1){
                $('.answerR input[type="checkbox"]').unbind();
                $(':checkbox[name="checkType"]').removeAttr('checked');
            }
            if(selected.indexOf('judgeTab') != -1){
                $('#A').attr("disabled", false);
                $('#B').attr("disabled", false);
            }else{
                $('#A').attr("disabled", true);
                $('#B').attr("disabled", true);
                for(var i=0;i<_questionOption.length;i++){
                    blurText($('#option'+_questionOption[i]),_questionOption[i]);
                }
            }
        });
        _questionType = $('.questionR').find("input[type='radio']:checked").attr("id");

    });


    function deleteParent(obj){
        $.hd.alertMsg.confirm("删除","确认删除吗?",function(){
            obj.parent().remove();
        });
    }

    /**
     * 新增习题分类
     */
    function addExerciseType(){
        var _exercisesType = $('#exercisesType').val();
        $.ajax({
            url 	 : "${mvcPath}/view/trainingExercises/saveOrUpdateExercisesType",
            type 	 : "post",
            dataType : "json",
            data 	 : {name:_exercisesType},
            success  : function(data, textStatus, XMLHttpResponse) {
                if(data.result){
                    loadingPage('/view/trainingExercises/addRegularExercise');
                }else{
                    switch (data.status) {
                        case 1035:
                            // 名字被占用
                            $.hd.tips($("#exercisesType"), data.errors);
                            break;
                        default:
                            $.hd.alertMsg.error("服务器繁忙,稍后请重试!");
                            break;
                    }
                }
            },
            error : function(XMLHttpRequest, textStatus, errorThrown) {
                console.log(textStatus);
            }
        });
    }

    function addExerciseBase(){
        var _params = $(".login-main").params();
        var _exercises=[];
        if(_params != null){
            $('.questionList li').each(function(index){
                var _jsonValue = {};
                var _options = [];
                var _title=$(this).attr('title');
                var _tabType=$(this).attr('tabType');
                for(var i=0;i<_questionOption.length;i++){
                    var _tab = $(this).attr('tab'+_questionOption[i]);
                    _options.push({name:_questionOption[i],content:_tab});
                }
                var _rightAnswer=$(this).attr('rightanswer');
                _jsonValue.title = _title;
                _jsonValue.tabType = _tabType;
                _jsonValue.options = _options;
                _jsonValue.rightAnswer = _rightAnswer;
                _exercises.push(_jsonValue);
            });

            if(_exercises.length <= 0){
                $.hd.tips($(".addQuestion"), "题目不能为空！");
                return ;
            }

            if(_exercises.length > _limitCount){
                $.hd.alertMsg.error("习题最多"+ _limitCount +"个！");
                return ;
            }
            _params.type = 2;
            _params.classTypeId = $('.classTypeId').val();
            _params.exercises=JSON.stringify(_exercises);
            // 请求新增
            $.ajax({
                url 	 : "${mvcPath}/view/trainingExercises/saveOrUpdateBase",
                type 	 : "post",
                dataType : "json",
                data 	 : _params,
                success  : function(data, textStatus, XMLHttpResponse) {
                    if(data.result){
                        $.hd.alertMsg.info("保存成功！");
                        loadingPage('/view/trainingExercises');
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

</script>