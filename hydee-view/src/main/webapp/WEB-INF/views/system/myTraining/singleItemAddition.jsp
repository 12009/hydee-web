<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/include.inc.jsp" %>
<div class="right-main paddingLR40">
    <div class="div-ui-divide marginRight10 marginTop20"><span class="span-ui-dot"></span><span>添加培训习题</span><span style=" float:right; margin-right:20px;"><a style="color:#c6244b;" onclick="loadingPage('/view/trainingExercises/exercisesQuestionBank')">返回习题题库</a></span></div>
    <div class="marginRight10">
        <input type="hidden" value='${trainExercises}' id="trainExercises"/>
        <input type="hidden" id="exercisesId"/>
        <ul class="ul-info table-height30">
            <li class="width100 textAlignR marginRight20">分类</li>
            <li>
                <select id="classTypeId" style="height:30px; width:100px;">
                    <c:forEach items="${exercisesTypes}" var="types">
                        <option value="${types.classTypeId}">${types.name}</option>
                    </c:forEach>
                </select>
            </li>
            <div class="clear"></div>
        </ul>
        <ul class="ul-info table-height30 heightAuto">
            <li class="width100 textAlignR marginRight20">题目</li>
            <li>
                <textarea rows="3" class="width480" id="content" placeholder="100字以内" maxlength="100" ></textarea>
            </li>
            <div class="clear"></div>
        </ul>
        <ul class="ul-info table-height30 questionR">
            <li class="width100 textAlignR marginRight20">题型</li>
            <li>
                <label class="marginRight10" >
                    <input type="radio" name="type" value="multiTab" class="questionType" id="multiTab">多选
                </label>
                <label class="marginRight10">
                    <input type="radio" name="type" value="singleTab" class="questionType" id="singleTab">单选
                </label>
                <label >
                    <input type="radio" name="type" value="judgeTab" class="questionType" id="judgeTab">判断题
                </label>

            </li>
            <div class="clear"></div>
        </ul>

        <ul class="ul-info table-height30 optionA">
            <li class="width100 textAlignR marginRight20">选项A</li>
            <li>
                <input type="text" class="width480 optionTxt" rid="A" id="optionA" placeholder="300字以内" maxlength="300" onblur="blurText(this,'A')" />
            </li>
            <div class="clear"></div>
        </ul>
        <ul class="ul-info table-height30 optionB">
            <li class="width100 textAlignR marginRight20">选项B</li>
            <li>
                <input type="text" class="width480 optionTxt" rid="B" id="optionB" placeholder="300字以内" maxlength="300" onblur="blurText(this,'B')" />
            </li>
            <div class="clear"></div>
        </ul>
        <ul class="ul-info table-height30 optionC">
            <li class="width100 textAlignR marginRight20">选项C</li>
            <li>
                <input type="text" class="width480 optionTxt" rid="C" id="optionC" placeholder="300字以内" maxlength="300" onblur="blurText(this,'C')" />
            </li>
            <div class="clear"></div>
        </ul>
        <ul class="ul-info table-height30 optionD">
            <li class="width100 textAlignR marginRight20">选项D</li>
            <li>
                <input type="text" class="width480 optionTxt" rid="D" id="optionD" placeholder="300字以内" maxlength="300" onblur="blurText(this,'D')" />
            </li>
            <div class="clear"></div>
        </ul>
        <ul class="ul-info table-height30 optionE">
            <li class="width100 textAlignR marginRight20">选项E</li>
            <li>
                <input type="text" class="width480 optionTxt" rid="E" id="optionE" placeholder="300字以内" maxlength="300" onblur="blurText(this,'E')" />
            </li>
            <div class="clear"></div>
        </ul>
        <ul class="ul-info table-height30 optionF">
            <li class="width100 textAlignR marginRight20">选项F</li>
            <li>
                <input type="text" class="width480 optionTxt" rid="F" id="optionF" placeholder="300字以内" maxlength="300" onblur="blurText(this,'F')" />
            </li>
            <div class="clear"></div>
        </ul>
        <ul class="ul-info table-height30 answerR">
            <li class="width100 textAlignR marginRight20">答案</li>
            <li class="vertical-align-middle">
                <label class="marginRight10" for="A"><input type="checkbox" name="checkType" id="A" class="fakeRadio"><span id="checkA">A</span></label>
                <label class="marginRight10" for="B"><input type="checkbox" name="checkType" id="B" class="fakeRadio"><span id="checkB">B</span></label>
                <label class="marginRight10" for="C"><input type="checkbox" name="checkType" id="C" class="fakeRadio">C</label>
                <label class="marginRight10" for="D"><input type="checkbox" name="checkType" id="D" class="fakeRadio">D</label>
                <label class="marginRight10" for="E"><input type="checkbox" name="checkType" id="E" class="fakeRadio">E</label>
                <label for="F"><input type="checkbox" id="F" class="fakeRadio">F</label>
            </li>
            <div class="clear"></div>
        </ul>
        <div class="btn-noPosition marginTop20">
            <input type="button" onclick="addSingleItem(1)" class="btnForSure btn-hover-crimson marginRight10 width150" value="保存且继续下一题">
            <input type="button" onclick="addSingleItem(2)" class="btnForSure btn-hover-crimson marginRight10 width80" value="保存">
            <a onclick="loadingPage('/view/trainingExercises/exercisesQuestionBank')"><input type="button" class="btnCancel btn-line-crimson width80" value="取消"></a>
        </div>
    </div>

    <div class="bgShow"></div>
</div>
<script type="text/javascript">
    var _questionType;
    var _trainExercises = $('#trainExercises').val();
    var choose = ['A','B','C','D','E','F'];
    $(function(){
        $('.questionType').change(function(){
            var selected = $(this).val();
            _questionType = selected;
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
                }else{
                    for(var i=0;i<choose.length;i++){
                        blurText($('#option'+choose[i]),choose[i]);
                    }
                }
            }else if(selected.indexOf('multiTab') != -1){
                $('.answerR input[type="checkbox"]').unbind();
                $(':checkbox[name="checkType"]').removeAttr('checked');
            }

        });
        _questionType = $('.questionR').find("input[type='radio']:checked").attr("id");


        if(_trainExercises != ''){
            var _trainExercisesList = JSON.parse(_trainExercises);
            $('#content').html(_trainExercisesList.content);
            $('#classTypeId').find('option[value='+ _trainExercisesList.classTypeId +']').attr('selected',true);
            $('input[name = "type"][value = "'+ _trainExercisesList.type +'"]').attr("checked","checked");
            if(_trainExercisesList.type.indexOf('judgeTab') != -1){
                hideText();
            }else{
                $.each(_trainExercisesList.optionsList,function(index,value){
                    $('#option'+value.optionNo).val(value.content);
                });
                forbidText();
            }

            var _answer = _trainExercisesList.answer.split('');
            $.each(_answer,function(i,v){
                $('input[type = "checkbox"][id = "'+ v +'"]').attr("checked","checked");
            });
            $('#exercisesId').val(_trainExercisesList.exercisesId);
            var typeId = _trainExercisesList.type;
            if(typeId.indexOf('singleTab') != -1 || typeId.indexOf('judgeTab') != -1){
                $('.answerR input[type="checkbox"]').each(function(){
                    $(this).click(function(){
                        if($(this).is(':checked')){
                            $(':checkbox[name="checkType"]').removeAttr('checked');
                            $(this).attr('checked','checked');
                        }
                    });
                });
                if(typeId.indexOf('judgeTab') != -1){
                    hideText();
                }
            }else if(typeId.indexOf('multiTab') != -1){
                $('.answerR input[type="checkbox"]').unbind();
                $(':checkbox[name="checkType"]').removeAttr('checked');
            }
        }
    });

    function addSingleItem(_type){
        var _checkBox = $('.answerR input[type = "checkbox"]').is(':checked');
        if(!_checkBox){
            $.hd.tips($('.answerR input[type = "checkbox"][id = "A"]'),"请选择答案！");
            return ;
        }
        var _params = {};
        _params.classTypeId = $('#classTypeId').val();
        _params.content = $('#content').val();
        _params.type = _questionType;
        _params.exercisesId = $('#exercisesId').val();
        var _answer = '';
        $('.answerR input[type="checkbox"]:checked').each(function(){
            _answer += $(this).attr("id");
        });
        var tabType = $('.questionR').find("input[type='radio']:checked").attr("id");
        if(tabType.indexOf('multiTab') != -1){
            if(_answer.length < 2){
                $.hd.tips($('.answerR input[type = "checkbox"][id = "A"]'),"请选择多个答案！");
                return ;
            }
        }
//        if(_answer.length < 2){
//            $.hd.tips($('.answerR input[type = "checkbox"][id = "A"]'),"请选择多个答案！");
//            return ;
//        }
        _params.answer = _answer;
        var options = [];
        $.each(choose,function(index,value){
            var option = {};
            option.name = value;
            option.content = $('#option'+value).val();
            options.push(option);
        });
        _params.option = JSON.stringify(options);
        $.ajax({
            url 	 : "${mvcPath}/view/trainingExercises/saveOrUpdateSingleExercises",
            type 	 : "post",
            dataType : "json",
            data 	 : _params,
            success  : function(data, textStatus, XMLHttpResponse) {
                if(data.result){
                    if(_type == 1){
                        loadingPage('/view/trainingExercises/singleItemAddition');
                    }else{
                        loadingPage('/view/trainingExercises/exercisesQuestionBank');
                    }

                }else{
                    $.hd.alertMsg.error("服务器繁忙,稍后请重试!");
                }
            },
            error : function(XMLHttpRequest, textStatus, errorThrown) {
                console.log(textStatus);
            }
        });
    }
</script>