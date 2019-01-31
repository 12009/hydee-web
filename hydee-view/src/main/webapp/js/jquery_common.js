(function ($) {

    $.widget("custom.dropList", {//下拉可搜索的列表的插件
        options: {
            dropListz:[],
            key:"id",
            name:"name",
            inputName:"demo",
            width:"340px",
            input:true,
            appendId:"",
            appendFun:"",
            endFun:""
        },
        _create: function () {},
        _init: function () {
            var that=this.element;
            if(that.find("ul").length<1){
                that.prepend("<input type='hidden' name='"+this.options.inputName+"' value=''>");
                that.find(".dropList-top").css("width",this.options.width).append("<div><span>▼</span></div><input type='text'><ul></ul>");
            }
            if(!this.options.input){
                that.find(".dropList-top input[type='text']").hide();
            }
            var liList=this.options.dropListz;
            var thatUl=that.find("ul");
            var liListId=this.options.key;
            var liListName=this.options.name;
            $.each(liList,function(index,value){
                var judgeExit=true;
                thatUl.find("li").each(function(){
                    if(value[liListId]==$(this).attr("data_id")){
                        judgeExit=false;
                    }
                })
                if(judgeExit){
                    thatUl.append("<li judgeD='"+value[liListName]+"' data_id='"+value[liListId]+"'>"+value[liListName]+"</li>");
                }
            })
            this._addEvent();
        },
        _addEvent:function(){
            var listId=this.options.appendId;
            var listFun=this.options.appendFun;
            var that0=this;
            var that=this.element;
            var nowInput=that.find(".dropList-top input");
            var nowUl=that.find("ul");
            that.find(".dropList-con").click(function(e){
                that0._stopPropa(e);
                $(".dropList-top").hide();
                that.find(".dropList-con").removeClass("ok");
                that.find(".dropList-top").show();
                that.find("input").focus();
            })
            nowInput.keyup(function(){
                var val=$(this).val();
                $(this).parent().find("ul li").hide().each(function(){
                    var judgeD=$(this).attr("judgeD");
                    if(judgeD.indexOf(val)>=0){
                        $(this).show();
                    }
                })
            })
            nowInput.click(function(e){
                that0._stopPropa(e);
                return;
            })
            nowUl.find("li").click(function(){
                var html=$(this).html();
                var id=$(this).attr("data_id");
                if(html=="不填"){
                    html="";
                    if(that0.options.defaultStr){
                        html=that0.options.defaultStr;
                    }
                    that.find(".dropList-con").removeClass("ok");
                }else{
                    that.find(".dropList-con").addClass("ok");
                }
                that.find(".dropList-con").html(html).attr("dataSelectId",$(this).attr("data_id"));
                if(listId!=""){
                    $("#"+listId).append("<li><label>"+html+"</label><span class='doorListDelete two sp'>×</span></li>");
                    if(listFun!=""){
                        listFun();
                    }
                }
                that.find("input[type='hidden']").val(id);
                that0._refresh();
            })
            $("body").click(function(){
                that0._refresh();
            })
        },
        destroy: function () {},
        _refresh:function(){
            var that=this.element;
            that.find(".dropList-top").hide();
            that.find(".dropList-top input").val("");
            that.find("ul li").show();
            if(this.options.endFun!=""){
                this.options.endFun();
            }
            this.destroy();
        },
        _stopPropa:function(e){
            if (e.stopPropagation){
                e.stopPropagation();
            }else{
                e.cancelBubble = true;
            }
        }
    });

    $.widget("custom.colorSelect",{
        options:{
            colorArray:["#B4E9EF","#CFF8E6","#C876C2","#C697C3","#A6A3CE","#6469A9","#E6DED1","#ECA29F","#FA4A54","#76C08D","#E3A335","#B47941"],
            conName:"",
            inputHiddenId:""
        },
        _create: function () {},
        _init: function () {
            var that=this.element;
            if(that.parent().find(".colorSelect").length==0){
                var colorA=this.options.colorArray;
                var tdDom="<tr>";
                for(var i=0;i<colorA.length;i++){
                    if(i!=0&&i%6==0&&i!=colorA.length-1){
                        tdDom=tdDom+"</tr><tr>";
                    }
                    tdDom=tdDom+"<td style='background-color:"+colorA[i]+";' colorSelect='"+colorA[i]+"'></td>";
                    if(i==colorA.length-1){
                        tdDom=tdDom+"</tr>";
                    }
                }
                that.parent().css("position","relative").append("<table class='colorSelect'></table>").find(".colorSelect").append(tdDom);
            }
            this._addEvent();
        },
        _addEvent:function() {
            var thiss=this;
            var that=this.element;
            var colorSelectDom=that.parent().find(".colorSelect");
            that.click(function(e){
                thiss._stopPropa(e);
                colorSelectDom.fadeIn();
            })
            colorSelectDom.find("td").click(function(){
                var colorValue=$(this).attr("colorSelect");
                if(thiss.options.inputHiddenId){
                    $("#"+thiss.options.inputHiddenId).attr("value",colorValue);
                }
                if(thiss.options.conName){
                    $(thiss.options.conName).css("backgroundColor",colorValue);
                }
                that.css("backgroundColor",colorValue);
                colorSelectDom.fadeOut();
            })
            $("body").click(function(e){
                thiss._stopPropa(e);
                colorSelectDom.fadeOut();
            })
            thiss._destroy();
        },
        _destroy: function () {},
        _stopPropa:function(e){
            if (e.stopPropagation){
                e.stopPropagation();
            }else{
                e.cancelBubble = true;
            }
        }
    })

    $.widget("custom.getProduce",{
        options:{
            goodsList   :[],
            judgeLayer  :"",
            appendId    :"",
            appendFun   :"",
            judgeIdExist:true,
            endFun      :""
        },
        _create: function () {
            if($(".chooseProduce").length<1){
                $("body").append("<div class='chooseProduce'> <div class='cProduceHeader'> " +
                    "<div class='cProduceHeaderLeft floatLeft'>指定连锁</div>" +
                    "<div class='cProduceHeaderRight floatRight'><input type='text' class='inputSearch width200'><" +
                    "input type='button' id='searchCustomer' onclick='searchCustomer()' value='搜索' class='btn-hover-crimson'></div>" +
                    "<div class='clear'> </div></div>" +
                    "<div class='cProduceMain'><ul></ul></div>" +
                    "<div class='cProduceSelectedShow'><ul><div class='clear'></div> " +
                    "</ul></div><div class='cProduceFooter'><div class='pager floatLeft'>" +
                    "<a href='javascript:void (0);'>上一页</a><span class='font-color-crimson'> 1 </span>" +
                    "<a href='javascript:void (0);'>下一页</a></div>" +
                    "<div class='goToPage floatLeft'>共"+ _count +"条&nbsp;&nbsp;共20页&nbsp;&nbsp;"+ _pageSize +"条/页&nbsp;&nbsp;跳转至&nbsp;&nbsp;" +
                    "<input type='text' class='width50'>&nbsp;&nbsp;页&nbsp;&nbsp;<input type='button' class='btn-hover-crimson btn-gray' value='跳转'>" +
                    "</div><div class='cProduceFooterFun floatRight'>" +
                    "<input type='button' value='确定' class='cProduceSure btn-hover-crimson width60 marginRight10'>" +
                    "<input type='button' value='取消' class='cProduceCancel btn-hover-crimson width60'>" +
                    "</div><div class='clear'> </div></div></div>");
            }
            $(".bgShow,.chooseProduce").fadeIn();


            this._addEvent();
        },
        _init: function () {

        },
        _addEvent:function() {
            var thatt=this;
            $("body").unbind("click").delegate(".cProduceMain li","click",function(){
                var that=$(this);
                if(that.find("input[type=checkbox]").attr("checked")=="checked"){
                    $(".cProduceSelectedShow ul").append("<li dataId='"+that.attr("dataId")+"' dataDes='"+that.find(".con").text()+"'>"+that.find(".name").text()+"<span class='deleteDom one'></span></li>");
                }else{
                    $(".cProduceSelectedShow ul li").each(function(){
                        if($(this).attr("dataId")==that.attr("dataId")){
                            $(this).remove();
                        }
                    })
                }
            })

            $("body").delegate(".cProduceSelectedShow .deleteDom","click",function(){
                var judgeId=$(this).parent().attr("dataId");
                if(confirm("确认删除？")){
                    $(this).parent().remove();
                    $(".cProduceMain li").each(function(){
                        if($(this).attr("dataId")==judgeId){
                            $(this).find("input[type=checkbox]").removeAttr("checked");
                        }
                    })
                }
            })

            var judgeExit=false;
            if(this.options.judgeIdExist) judgeExit=true;
            var judgeS=thatt.options.judgeLayer;
            var deleteFun=thatt.options.appendFun;
            var appendStyle=thatt.options.appendStyle;
            $("body").delegate(".cProduceSure","click",function(){
                var appendId=thatt.options.appendId;
                if(appendId){
                    var appendDom=$("#"+appendId);
                    $(".cProduceSelectedShow li").each(function(){
                        var that=$(this);
                        var thisId=that.attr("dataId");
                        var judgeNowD=true;
                        if(judgeExit){
                            appendDom.find("li").each(function(){
                                if($(this).attr("dataId")==thisId){
                                    judgeNowD=false;
                                }
                            })
                        }
                        if(judgeNowD){
                            if(appendDom.find(".all").length>0){
                                $("<li dataId='"+thisId+"' title='"+that.text()+"'>" +
                                    "<span class='shopName'>"+that.text()+"</span>" +
                                    "<span class='shopDelete' onclick='"+deleteFun+"($(this))'>×</span></li>").insertBefore(appendDom.find(".all"));
                            }else{
                                $("<li dataId='"+thisId+"' title='"+that.text()+"'>" +
                                    "<span class='shopName'>"+that.text()+"</span>" +
                                    "<span class='shopDelete' onclick='"+deleteFun+"($(this))'>×</span></li>").insertBefore(appendDom.find(".clear"));
                            }
                        }
                    })
                }
                thatt._refresh();
            })

            $("body").delegate(".cProduceCancel","click",function(){
                thatt._refresh();
            })
        },
        _refresh:function(){
            var that=this;
            $(".bgShow,.chooseProduce").fadeOut();
            setTimeout(function(){
                $(".chooseProduce").remove();
                that._destroy();
            },300);
        },
        _destroy: function () {},
        _stopPropa:function(e){
            if (e.stopPropagation){
                e.stopPropagation();
            }else{
                e.cancelBubble = true;
            }
        }
    })

    $.widget("custom.histogramDataShow", {
        options: {},
        _create: function () {},
        _init: function () {
            var that=this.element;
            that.find("ul").empty();
            var timeArray=[];
            var weekArray=[];
            var nowdate = new Date();
            for(var i=0;i<30;i++){
                nowdate.setDate(nowdate.getDate()-1);
                var lsDate = nowdate.toLocaleDateString();
                var lsYear=this._getNewString(lsDate.split("/")[0]);
                var lsMonth=this._getNewString(lsDate.split("/")[1]);
                var lsDay=this._getNewString(lsDate.split("/")[2]);
                if(lsYear!=undefined&&lsMonth!=undefined&&lsDay!=undefined){
                    lsDate=lsYear+"/"+lsMonth+"/"+lsDay;
                }
                var lsWeek=this._getWeekC(nowdate.getDay().toString());
                timeArray.splice(0,0,lsDate);
                weekArray.splice(0,0,lsWeek);
            }
            var dataSum=this.options.dataList;
            var maxSum=this._getMaoPao(dataSum);
            for(var i=0;i<30;i++){
                var lsDataSum=dataSum[i];
                var lsDom;
                if(lsDataSum!=""){
                    lsDom="<li class='on' style='left: "+31*i+"px;height:"+120*(lsDataSum/maxSum)+"px' dataTime='"+timeArray[i]+"' dataWeek='"+weekArray[i]+"' dataSum='"+dataSum[i]+"'></li>";
                }else{
                    lsDom="<li style='left: "+31*i+"px;' dataTime='"+timeArray[i]+"' dataWeek='"+weekArray[i]+"' dataSum='"+dataSum[i]+"'></li>";
                }
                that.find("ul").append(lsDom);
            }
            this._addEvent();
        },
        _addEvent:function(){
            var that0=this;
            var that0e=that0.element;
            var floatData=that0e.find(".floatDaTa");
            var setTimeShow;
            $(".dataShow li").hover(function(){
                var that=$(this);
                var newLeft=parseInt(that.css("left"))-73+"px";
                var thatH=that.height()+5;
                that0._aniHover(newLeft,thatH,that,setTimeShow);
            },function(){
                setTimeShow=setTimeout(function(){
                    floatData.fadeOut();
                },1500);
            })
            this._destroy();
        },
        _destroy: function () {},
        _aniHover:function(newLeft,thatH,obj,setTimeShow){
            var lsFloatData=obj.parent().parent().find(".floatDaTa");
            var dataT=obj.attr("dataTime");
            var dataW=obj.attr("dataWeek");
            var dataS=obj.attr("dataSum");
            if(dataT=="") dataT="0000-00-00";
            if(dataW=="") dataW="星期日";
            if(dataS=="") dataS="0";
            var dataShow=obj.parent().attr("dataShow");
            lsFloatData.find(".date").text(dataT+" "+dataW);
            lsFloatData.find(".dataSum").text(dataShow+dataS);
            lsFloatData.css({"left":newLeft,"bottom":thatH+3,"opaticy":"0"}).fadeIn();
            setTimeout(function(){
                lsFloatData.css({"bottom":thatH,"opacity":"1","transtion":"all 0.2s ease-in-out"});
            },50);
            clearTimeout(setTimeShow);
        },
        _getNewString:function(num){
            try{
                if(num.toString().length==1){
                    num="0"+num;
                }
            }catch(e){}
            return num;
        },
        _getWeekC:function(week){
            var lsWeek="星期日";
            switch (week){
                case "0":lsWeek="星期日";
                    break;
                case "1":lsWeek="星期一";
                    break;
                case "2":lsWeek="星期二";
                    break;
                case "3":lsWeek="星期三";
                    break;
                case "4":lsWeek="星期四";
                    break;
                case "5":lsWeek="星期五";
                    break;
                case "6":lsWeek="星期六";
                    break;
                default :break;
            }
            return lsWeek;
        },
        _getMaoPao:function(obj){
            var maxValue=obj[0];
            for(var i=0;i<obj.length;i++){
                var j=i+1;
                for(j;j<obj.length;j++){
                    if(obj[j]>maxValue){
                        maxValue=obj[j];
                    }
                }
            }
            return maxValue;
        },
    });

    $.widget("custom.getArcCanvas",{
        options:{
            canvas:"myCanvas",
            bgColor:"#DBD8D3",
            arcColor:"#DF003C",
            maxValue:"75",
            x : 50,
            y : 50,
            r : 40
        },
        _create: function () {},
        _init: function () {
            var canvasS=this.element.attr("id");
            var x=this.options.x;
            var y=this.options.y;
            var r=this.options.r;
            var bgColor=this.options.bgColor;
            var arcColor=this.options.arcColor;
            var arcLine=this.options.arcLine;

            var maxValue;
            var judgeStyle=this.options.judgeStyle;
            if(judgeStyle=="time"){
                var startTime=this.options.timeStart;
                var endTime=this.options.timeEnd;
                var eTy=endTime.split("/")[0];
                var eTm=endTime.split("/")[1];
                var eTr=endTime.split("/")[2];
                var date=new Date();
                var dTy=date.getFullYear();
                var dTm=date.getMonth()+1;
                var dTr=date.getDate();
                if(dTy>eTy||(dTy==eTy&&dTm>eTm)||(dTy==eTy&&dTm==eTm&&dTr>eTr)){
                    maxValue=100;
                }else{
                    var objInterval = {'D' : 1000 * 60 * 60 * 24, 'H' : 1000 * 60 * 60, 'M' : 1000 * 60, 'S' : 1000, 'T' : 1};
                    var dt1 = Date.parse(startTime);
                    var dt2 = Date.parse(endTime);
                    var dt3=Date.parse(dTy+"/"+dTm+"/"+dTr);
                    maxValue=((((dt3 - dt1) / objInterval["D"])/((dt2 - dt1) / objInterval["D"]))*100).toFixed(0);
                }
            }else if(judgeStyle=="number"){
                var numStart=this.options.numStart;
                var numEnd=this.options.numEnd;
                maxValue=(numEnd/numStart*100).toFixed(0);
            }
            $("#"+canvasS).parent().find(".perC").text(maxValue+"%");;

            var canvasBg = document.getElementById(canvasS+"Bg");
            try{
                var ctxBg = canvasBg.getContext("2d");
                ctxBg.clearRect(0,0,canvasBg.width,canvasBg.height);
                ctxBg.beginPath();
                ctxBg.strokeStyle = bgColor;
                ctxBg.lineWidth = arcLine;
                var circle = {
                    x : x,
                    y : y,
                    r : r
                };
                ctxBg.arc(circle.x, circle.y, circle.r, 0, Math.PI * 2, false);
                ctxBg.stroke();

                var timeP;
                var bfb=0;
                drawPro();
                function drawPro(){
                    addCanvas(canvasS,bfb);

                    timeP=setTimeout(function(){
                        drawPro();
                    },5);

                    if(bfb>=maxValue){
                        clearTimeout(timeP);
                        bfb=0;
                        return;
                    }
                    bfb+=1;
                }

                function addCanvas(myc,per){
                    var canvas = document.getElementById(myc);
                    try{
                        var ctx = canvas.getContext("2d");
                        ctx.clearRect(0,0,canvas.width,canvas.height);
                        ctx.beginPath();
                        ctx.strokeStyle = arcColor;
                        ctx.lineWidth = arcLine;
                        var circle = {
                            x : x,
                            y : y,
                            r : r
                        };
                        ctx.arc(circle.x, circle.y, circle.r, 0, Math.PI * per/50, false);
                        ctx.stroke();
                    }catch(e){}
                }
            this._destroy();
        }catch(e){}},
        _destroy: function () {}
    })

    $.widget("custom.getLineShow",{
        options:{
            dataObj:{}
        },
        _create: function () {},
        _init: function () {
            var that=this;
            var thate=this.element;
            var dataObj=that.options.dataObj;
            var dataObj_dataArray=[];

            thate.find(".timeSqu li").empty();
            for(var i=0;i<dataObj.length;i++){
                dataObj_dataArray.push(parseInt(dataObj[i].data));
                thate.find(".timeSqu li:eq("+i+")").text(dataObj[i].time);
            }

            var maxData=that._maopaoOrder(dataObj_dataArray);
            var maxRealData=that._getNewValue(maxData);

            thate.find(".posDataShow ul li").empty();
            for(var i=0;i<7;i++){
                var topValue=250-(dataObj_dataArray[i]/maxRealData*250)-2.5;
                thate.find(".posDataShow ul li:eq("+i+")").append("<span class='dataLineImgShow' style='top:"+topValue+"px'></span>");
            }

            var singleArray=[];
            var posbLeft=thate.find(".dataShowLineChart").offset().left;
            thate.find(".posDataShow ul li").each(function(){
                var that=$(this);
                var imgDom=that.find(".dataLineImgShow");
                var cleft=imgDom.offset().left-posbLeft+2.5;
                var ctop=parseFloat(imgDom.css("top").split("px")[0])+2.5;
                singleArray.push({
                    "left":cleft,
                    "top":ctop
                })
            })

            thate.find(".floatLine").remove();
            var timeLine=0.3;
            for(var i=0;i<singleArray.length;i++){
                if(i!=singleArray.length-1){
                    var j=i+1;
                    var angle=that._getAngle(singleArray[i],singleArray[j]);
                    var divW=Math.sqrt(Math.pow((singleArray[j].top-singleArray[i].top),2)+Math.pow((singleArray[j].left-singleArray[i].left),2));
                    that._addDom0(thate,singleArray,angle,divW,i,timeLine,timeLine*i);
                }
            }

            this._destroy();
        },
        _getNewValue:function(data){
            var thate=this.element;
            var maxValue=5000*(parseInt(data/5000)+1);
            for(var i=6;i>1;i--){
                var j=i-2;
                thate.find(".pos1"+i).text(maxValue/5*(5-j));
            }
            return maxValue;
        },
        _getAngle:function(start,end){
            var diff_x = end.left - start.left,
                diff_y = end.top - start.top;
            return 360*Math.atan(diff_y/diff_x)/(2*Math.PI);
        },
        _addDom0:function(ppDom,singleArray,angle,divW,i,k,j){
            var lsId=(new Date()).getTime()+i;
            var lsDom="<div class='floatLine' id='"+lsId+"' style='left:"+singleArray[i].left+"px;top:"+singleArray[i].top+"px;width:"+0+"px;transform:rotate("+angle+"deg);-webkit-transform:rotate("+angle+"deg);'></div>";
            ppDom.find(".dataShowLineChart").append(lsDom);
            setTimeout(function(){
                $("#"+lsId).css({"width":divW+"px","transition":"width "+k+"s linear "+j+"s"});
            },50)
        },
        _maopaoOrder:function(obj){
            var maxValue=obj[0];
            for(var i=0;i<obj.length;i++){
                var j=i+1;
                for(j;j<obj.length;j++){
                    if(obj[j]>maxValue){
                        maxValue=obj[j];
                    }
                }
            }
            return maxValue;
        },
        _destroy: function () {}
    })

})(jQuery);
