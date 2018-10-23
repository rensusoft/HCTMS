/*
    radialIndicator.js v 1.0.0
    Author: Sudhanshu Yadav
    Copyright (c) 2015 Sudhanshu Yadav - ignitersworld.com , released under the MIT license.
    Demo on: ignitersworld.com/lab/radialIndicator.html
*/
$(function(){
	  //添加加载层
	  var divPop = myLoading();
	  $.ajax({
          url: ctx+'/teachweb/selectTrainPlan.action',
          dataType: "json",
          data:{},
          async: false,
          success: function( data ) {
          data = eval(data.rows);
          var str="";
          var clickNum=0;
	      if(null != data){
	    	  str+='<ul class="list">';
     for(var i=0;i<data.length; i++){
    	 if(data[i].train_status==51||data[i].train_status==""||data[i].train_status==null){
        	 str+='<li class="opacity"><span><i>未轮转</i></span>'+
        	 '<a href="#">';
    }else if(data[i].train_status==52){
    	 str+='<li class="opacity"><span><b class="yellow"></b><i>待入科</i></span>'+
    	 '<a href="#">';
    }else if(data[i].train_status==58){
    	 str+='<li><span><b class="gary"></b><i>已出科</i></span>'+
    	 '<a href="'+ctx+'/teachweb/selectRotateProcessRight.action?id='+data[i].id+'&train_dept_id='+data[i].train_dept_id+'" target="rotateProcessRight" onclick="addClass();">';
    }else if(data[i].train_status==53){
    	clickNum=i;
   	 str+='<li><span><b class="green"></b><i>轮转中</i></span>'+
	 '<a href="'+ctx+'/teachweb/selectRotateProcessRight.action?id='+data[i].id+'&train_dept_id='+data[i].train_dept_id+'" target="rotateProcessRight" onclick="addClass();">';
}
    else{
    	clickNum=i;
    	 str+='<li ><span><b></b><i>待出科</i></span>'+
    	 '<a href="'+ctx+'/teachweb/selectRotateProcessRight.action?id='+data[i].id+'&train_dept_id='+data[i].train_dept_id+'" target="rotateProcessRight" onclick="addClass();">';
    }
   	 str+= '<i><img src="'+ctx+'/jsp/web/teach/img/house.png" alt=""/></i>'+
      '<span>'+data[i].orga_name+'</span>'+
      '<div class="rotate_process">'+
      '<div id="process'+i+'"></div>'+
      '</div>'+
      '</a>'+
      '</li>'+
   	  ' <script> '+
      '$("#process'+i+'").radialIndicator({'+
      'barColor: "#FAA07C",'+
      'barWidth: 3,'+
      'initValue: '+data[i].completion_rate+','+
      'roundCorner : true,'+
      'percentage: true,'+
      'radius:25'+
      ' });'+
      '</script>';
     }
     str+= '</ul>';
    //去掉加载层
	closeMyLoading(divPop);
    $("#leftDiv").html(str);
    $("a")[clickNum].click();
	 }	
    }
  });
});


function addClass(){
	 $("div ul li").click(function(){
		 if($(this).attr("class")!='opacity'){
			 $(this).addClass("border").siblings().removeClass("border");
		 }
	 });
}


;(function ($, window, document) {
    "use strict";
    //circumfence and quart value to start bar from top
    var circ = Math.PI * 2,
        quart = Math.PI / 2;

    //function to convert hex to rgb

    function hexToRgb(hex) {
        // Expand shorthand form (e.g. "03F") to full form (e.g. "0033FF")
        var shorthandRegex = /^#?([a-f\d])([a-f\d])([a-f\d])$/i;
        hex = hex.replace(shorthandRegex, function (m, r, g, b) {
            return r + r + g + g + b + b;
        });

        var result = /^#?([a-f\d]{2})([a-f\d]{2})([a-f\d]{2})$/i.exec(hex);
        return result ? [parseInt(result[1], 16), parseInt(result[2], 16), parseInt(result[3], 16)] : null;
    }

    function getPropVal(curShift, perShift, bottomRange, topRange) {
        return Math.round(bottomRange + ((topRange - bottomRange) * curShift / perShift));
    }


    //function to get current color in case of 
    function getCurrentColor(curPer, bottomVal, topVal, bottomColor, topColor) {
        var rgbAryTop = topColor.indexOf('#') != -1 ? hexToRgb(topColor) : topColor.match(/\d+/g),
            rgbAryBottom = bottomColor.indexOf('#') != -1 ? hexToRgb(bottomColor) : bottomColor.match(/\d+/g),
            perShift = topVal - bottomVal,
            curShift = curPer - bottomVal;

        if (!rgbAryTop || !rgbAryBottom) return null;

        return 'rgb(' + getPropVal(curShift, perShift, rgbAryBottom[0], rgbAryTop[0]) + ',' + getPropVal(curShift, perShift, rgbAryBottom[1], rgbAryTop[1]) + ',' + getPropVal(curShift, perShift, rgbAryBottom[2], rgbAryTop[2]) + ')';
    }

    //to merge object
    function merge() {
        var arg = arguments,
            target = arg[0];
        for (var i = 1, ln = arg.length; i < ln; i++) {
            var obj = arg[i];
            for (var k in obj) {
                if (obj.hasOwnProperty(k)) {
                    target[k] = obj[k];
                }
            }
        }
        return target;
    }

    //function to apply formatting on number depending on parameter
    function formatter(pattern) {
        return function (num) {
            if(!pattern) return num.toString();
            num = num || 0
            var numRev = num.toString().split('').reverse(),
                output = pattern.split("").reverse(),
                i = 0,
                lastHashReplaced = 0;

            //changes hash with numbers
            for (var ln = output.length; i < ln; i++) {
                if (!numRev.length) break;
                if (output[i] == "#") {
                    lastHashReplaced = i;
                    output[i] = numRev.shift();
                }
            }

            //add overflowing numbers before prefix
            output.splice(lastHashReplaced+1, output.lastIndexOf('#') - lastHashReplaced, numRev.reverse().join(""));

            return output.reverse().join('');
        }
    }


    //circle bar class
    function Indicator(container, indOption) {
        indOption = indOption || {};
        indOption = merge({}, radialIndicator.defaults, indOption);

        this.indOption = indOption;

        //create a queryselector if a selector string is passed in container
        if (typeof container == "string")
            container = document.querySelector(container);

        //get the first element if container is a node list
        if (container.length)
            container = container[0];

        this.container = container;

        //create a canvas element
        var canElm = document.createElement("canvas");
        container.appendChild(canElm);

        this.canElm = canElm; // dom object where drawing will happen

        this.ctx = canElm.getContext('2d'); //get 2d canvas context

        //add intial value 
        this.current_value = indOption.initValue || indOption.minValue || 0;

    }


    Indicator.prototype = {
        constructor: radialIndicator,
        init: function () {
            var indOption = this.indOption,
                canElm = this.canElm,
                ctx = this.ctx,
                dim = (indOption.radius + indOption.barWidth) * 2, //elm width and height
                center = dim / 2; //center point in both x and y axis


            //create a formatter function
            this.formatter = typeof indOption.format == "function" ? indOption.format : formatter(indOption.format);

            //maximum text length;
            this.maxLength = indOption.percentage ? 4 : this.formatter(indOption.maxValue).length;

            canElm.width = dim;
            canElm.height = dim;

            //draw a grey circle
            ctx.strokeStyle = indOption.barBgColor; //background circle color
            ctx.lineWidth = indOption.barWidth;
            ctx.beginPath();
            ctx.arc(center, center, indOption.radius, 0, 2 * Math.PI);
            ctx.stroke();

            //store the image data after grey circle draw
            this.imgData = ctx.getImageData(0, 0, dim, dim);

            //put the initial value if defined
            this.value(this.current_value);

            return this;
        },
        //update the value of indicator without animation
        value: function (val) {
            //return the val if val is not provided
            if (val === undefined || isNaN(val)) {
                return this.current_value;
            }

            val = parseFloat(val);
            
            var ctx = this.ctx,
                indOption = this.indOption,
                curColor = indOption.barColor,
                dim = (indOption.radius + indOption.barWidth) * 2,
                minVal = indOption.minValue,
                maxVal = indOption.maxValue,
                center = dim / 2;

            //limit the val in range of 0 to 100
            val = val < minVal ? minVal : val > maxVal ? maxVal : val;

            var perVal = Math.round(((val - minVal) * 100 / (maxVal - minVal)) * 100) / 100, //percentage value tp two decimal precision
                dispVal = indOption.percentage ? perVal + '%' : this.formatter(val); //formatted value

            //save val on object
            this.current_value = val;


            //draw the bg circle
            ctx.putImageData(this.imgData, 0, 0);

            //get current color if color range is set
            if (typeof curColor == "object") {
                var range = Object.keys(curColor);

                for (var i = 1, ln = range.length; i < ln; i++) {
                    var bottomVal = range[i - 1],
                        topVal = range[i],
                        bottomColor = curColor[bottomVal],
                        topColor = curColor[topVal],
                        newColor = val == bottomVal ? bottomColor : val == topVal ? topColor : val > bottomVal && val < topVal ? indOption.interpolate ? getCurrentColor(val, bottomVal, topVal, bottomColor, topColor) : topColor : false;

                    if (newColor != false) {
                        curColor = newColor;
                        break;
                    }
                }
            }

            //draw th circle value
            ctx.strokeStyle = curColor;

            //add linecap if value setted on options
            if (indOption.roundCorner) ctx.lineCap = "round";

            ctx.beginPath();
            ctx.arc(center, center, indOption.radius, -(quart), ((circ) * perVal / 100) - quart, false);
            ctx.stroke();

            //add percentage text
            if (indOption.displayNumber) {
                var cFont = ctx.font.split(' '),
                    weight = indOption.fontWeight,
                    fontSize = indOption.fontSize || (dim / (this.maxLength - (Math.floor(this.maxLength*1.4/4)-1)));

                cFont = indOption.fontFamily || cFont[cFont.length - 1];


                ctx.fillStyle = indOption.fontColor || curColor;
                ctx.font = weight +" "+ fontSize + "px " + cFont;
                ctx.textAlign = "center";
                ctx.textBaseline = 'middle';
                ctx.fillText(dispVal, center, center);
            }

            return this;
        },
        //animate progressbar to the value
        animate: function (val) {

            var indOption = this.indOption,
                counter = this.current_value || indOption.minValue,
                self = this,
                incBy = Math.ceil((indOption.maxValue - indOption.minValue) / (indOption.frameNum || (indOption.percentage ? 100 : 500))), //increment by .2% on every tick and 1% if showing as percentage
                back = val < counter;

            //clear interval function if already started
            if (this.intvFunc) clearInterval(this.intvFunc); 

            this.intvFunc = setInterval(function () {

                if ((!back && counter >= val) || (back && counter <= val)) {
                    if (self.current_value == counter) {
                        clearInterval(self.intvFunc);
                        return;
                    } else {
                        counter = val;
                    }
                }

                self.value(counter); //dispaly the value

                if (counter != val) {
                    counter = counter + (back ? -incBy : incBy)
                }; //increment or decrement till counter does not reach  to value
            }, indOption.frameTime);

            return this;
        },
        //method to update options
        option: function (key, val) {
            if (val === undefined) return this.option[key];

            if (['radius', 'barWidth', 'barBgColor', 'format', 'maxValue', 'percentage'].indexOf(key) != -1) {
                this.indOption[key] = val;
                this.init().value(this.current_value);
            }
            this.indOption[key] = val;
        }

    };

    /** Initializer function **/
    function radialIndicator(container, options) {
        var progObj = new Indicator(container, options);
        progObj.init();
        return progObj;
    }

    //radial indicator defaults
    radialIndicator.defaults = {
        radius: 50, //inner radius of indicator
        barWidth: 5, //bar width
        barBgColor: '#eeeeee', //unfilled bar color
        barColor: '#99CC33', //filled bar color , can be a range also having different colors on different value like {0 : "#ccc", 50 : '#333', 100: '#000'}
        format: null, //format indicator numbers, can be a # formator ex (##,###.##) or a function
        frameTime: 10, //miliseconds to move from one frame to another
        frameNum: null, //Defines numbers of frame in indicator, defaults to 100 when showing percentage and 500 for other values
        fontColor: null, //font color
        fontFamily: null, //defines font family
        fontWeight: 'bold', //defines font weight
        fontSize : null, //define the font size of indicator number
        interpolate: true, //interpolate color between ranges
        percentage: false, //show percentage of value
        displayNumber: true, //display indicator number
        roundCorner: false, //have round corner in filled bar
        minValue: 0, //minimum value
        maxValue: 100, //maximum value
        initValue: 0 //define initial value of indicator
    };
    
    window.radialIndicator = radialIndicator;

    //add as a jquery plugin
    if ($) {
        $.fn.radialIndicator = function (options) {
            return this.each(function () {
                var newPCObj = radialIndicator(this, options);
                $.data(this, 'radialIndicator', newPCObj);
            });
        };
    }

}(window.jQuery, window, document, void 0));




function showDivInfo(title,htmlContent){
	//弹出层展现数据信息
	mypopdiv(1,title,"1100px",(pHeight-50)+"px","","","N","<div style='height:"+(pHeight-95)+"px;overflow:auto;padding:10px;'>"+htmlContent+"</div>");
}

function showByParent(s_user_auth_id,s_orga_id,data_type_id){
	mypopdiv(2,"数据录入详情","1000px",(pHeight-50)+"px","50px",(pWidth-1000)/2+"px","N",ctx + '/jsp/web/teach/stuActiveDataInfo.jsp?stu_auth_id='+s_user_auth_id+'&dept_id='+s_orga_id+'&data_type_id='+data_type_id);
}