//页面加载时执行
$(function () {
	initDate();
	initRoundRobin();
	initNews();
	initStudentInfo();
    var pHeight=$(".list_p").children("p").height();
    //console.log(pHeight)
    $("progress").height(pHeight);
    //canvas宽高
    $("#canvas_circle").height($(".atten_img").height()*0.68);
    $("#canvas_circle").width($(".atten_img").width()*0.68);
    if($(".atten_img").height()<250){
    	$("#canvas_circle").height($(".atten_img").height()*0.68);
        $("#canvas_circle").width($(".atten_img").width()*0.60);
    }
    $(".atten_img .panel").height($(".news .panel").height());
    if(parseInt($("#date").height())<=350){
		$(".day").attr("style","font-size:6em");
		$(".year").attr("style","top:29%");
	}
});
//日期板块
function initDate(){
	 //日历日期
    var mydate = new getServerDate();
    var year=mydate.getFullYear();
    var month=mydate.getMonth();
    var day=mydate.getDate();
    var weekDay=mydate.getDay();
    $(".yearNum").text(year);
    $(".monthNum").text(month + 1);
    $(".day").text(day);
    switch(weekDay)
    {
    case 1:
    	 $(".weekNum").text("一");	
    break;
    case 2:
    	 $(".weekNum").text("二");	
    break;
    case 3:
    	 $(".weekNum").text("三");	
        break;
    case 4:
    	 $(".weekNum").text("四");
        break;
    case 5:
    	 $(".weekNum").text("五");
        break;
    case 6:
    	 $(".weekNum").text("六");
        break;
    default:
    	 $(".weekNum").text("日");
    break;
    }
}
//全院轮转情况
function initRoundRobin(){
	//添加加载层
	var divPop = myLoading();

	var options = {
			chart: {
			renderTo: 'container',
			type: 'column',
			},
			title: {
			text: '全院轮转概况'
			},
			 xAxis: {            categories: [], //x轴
				 labels: { 
                     rotation: 0, 
		        },
			 },
			 yAxis: {
		            min: 0,
		            minRange: 1,
		            title: {
		                text: '学生数'
		            },
//		            stackLabels: {
//		                enabled: true,
//			          
//		                style: {
//		                    fontWeight: 'bold',
//		                    color: (Highcharts.theme && Highcharts.theme.textColor) || 'gray',
//		                  
//		                }                            //是否显示柱状图总数
//		          
//		            }
		        },
		        legend: {
		            align: 'right',
		            x: -100,
		            verticalAlign: 'top',
		            y: 0,
		            floating: true,
		            backgroundColor: (Highcharts.theme && Highcharts.theme.background2) || 'white',
		            borderColor: '#CCC',
		            borderWidth: 1,
		            shadow: false
		        },
		        tooltip: {
		            formatter: function () {
		                return '<b>' + this.x + '</b><br/>' +
		                    this.series.name + ': ' + this.y + '<br/>' +
		                    '总量: ' + this.point.stackTotal;
		            }
		        },
		        plotOptions: {
		            column: {
		                stacking: 'normal',
		                dataLabels: {
		                    enabled: true,     //是否显示单个柱状图不同颜色区域内数目
		                    color: (Highcharts.theme && Highcharts.theme.dataLabelsColor) || 'white',
		                    style: {
		                        textShadow: '0 0 3px black'
		                    }
		                }
		      
		            }
		        },
	        series: [{
	            name: '入科报到',
	            color:"#7cb5ec",
	            data: [],
	        }, {
	            name: '轮转中',
	            color:"#90ed7d",
	            data: [],
	        }, {
	            name: '待出科',
	            color:"#434348",
	            data: [],
	        }],
	        credits: {  
	            enabled: false     //不显示LOGO 
	        }  
	    };
	var newTodoCount=[];
	var newDoingCount=[];
	var newDidCount=[];
	 var newOrgaName=[];
var url = ctx + "/teachweb/getRoundRobin.action";
$.ajax({
    url: url,
    type: "get",
    cache: false,
    dataType: "json",
    success: function(data){
    	
    	for(var i=0;i<data.row.length;i++){
    		newOrgaName.push(data.row[i].orga_name);
    		newTodoCount.push(data.row[i].todoCount);
    		newDoingCount.push(data.row[i].doingCount);
    		newDidCount.push(data.row[i].didCount);
    		} 
    	var obj = newTodoCount;
    	var obj1 =newDoingCount;
    	var obj2 =newDidCount;
    	var obj3=newOrgaName;
    	
    	options.series[0].data = obj;
    	options.series[1].data = obj1;
    	options.series[2].data = obj2;
    	var chart = new Highcharts.Chart(options);
    	chart.xAxis[0].setCategories(obj3);
    	//去掉加载层
		closeMyLoading(divPop);
    }
});
}
//待办事宜板块
function initNews(){
	var url=ctx+"/teachweb/getNews.action"; 
	var postData={};
	doAjax(url,postData,2,function(res){
		var str = "";
		for ( var j = 0; j < res.data.length; j++) {
			str += " <tr><td>[<i>"+(res.data)[j].type_Name+"</i>]</td><td>"+(res.data)[j].title+"</td>" +
					"<td>"+(res.data)[j].sendTimeStr+"</td></tr>";
		}
		$("#table_news").html(str);
	});
}
//学生详情板块
function initStudentInfo() {
    //绘制饼图
    //比例数据和颜色
	var url=ctx+"/teachweb/getStudentInfo.action"; 
	var postData={};
	doAjax(url,postData,2,function(res){
		var color=["#008000", "#FFAA00", "#00AABB","#9AC0CD","#8F8F8F","#8DEEEE","#4A4A4A","#00EEEE","#008B45","#8B1C62"];
		var data_arr = [];
		var text_arr = [];
		var color_arr = [];
		for (var i = 0; i < res.data.length; i++) {
			data_arr.push((res.data)[i].completion_type);
			text_arr.push((res.data)[i].typeName);
			color_arr.push(color[i]);
		}
		 drawCircle("canvas_circle", data_arr, color_arr, text_arr);
	});
}
//饼图
function drawCircle(canvasId, data_arr, color_arr, text_arr)
{
    var c = document.getElementById(canvasId);
    var ctx = c.getContext("2d");

    var radius = c.height / 2 - 20; //半径
    var ox = radius + 20, oy = radius + 20; //圆心

    var width = 30, height = 15; //图例宽和高
    var posX = ox * 2 + 20, posY = 30;   //
    var textX = posX + width + 5, textY = posY + 10;

    var startAngle = 0; //起始弧度
    var endAngle = 0;   //结束弧度
    for (var i = 0; i < data_arr.length; i++)
    {
        //绘制饼图
        endAngle = endAngle + data_arr[i] * Math.PI * 2; //结束弧度
        ctx.fillStyle = color_arr[i];
        ctx.beginPath();
        ctx.moveTo(ox, oy); //移动到到圆心
        ctx.arc(ox, oy, radius, startAngle, endAngle, false);
        ctx.closePath();
        ctx.fill();
        startAngle = endAngle; //设置起始弧度

        //绘制比例图及文字
        ctx.fillStyle = color_arr[i];
        ctx.fillRect(posX, posY + 20 * i, width, height);
        ctx.moveTo(posX, posY + 20 * i);
        ctx.font = 'bold 12px 微软雅黑';    //斜体 30像素 微软雅黑字体
        ctx.fillStyle = color_arr[i]; //"#000000";
        var percent = text_arr[i] + "：" + 100 * data_arr[i] + "%";
        ctx.fillText(percent, textX, textY + 20 * i);
    }
}

function getServerDate(){
    return new Date($.ajax({async: false}).getResponseHeader("Date"));
}

