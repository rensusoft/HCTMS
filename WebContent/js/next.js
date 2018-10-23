$(function(){
//排课下一步操作
    $('.page').css('visibility','hidden');
    $('.page').first().css('visibility','visible');
    $('#status').find('li').eq(0).addClass('aa');
    $('.page').find('.next').click(function(){
        var $page = $(this).parents('.page');
    	$page.css('visibility','hidden');
        $page.next().css('visibility','visible');
        var bb = $('.active').next().addClass('active');
        bb.addClass('aa');
    });
    $('.page').find('.prev').click(function(){
    	var $page = $(this).parents('.page');
    	$page.css('visibility','hidden');
    	$page.prev().css('visibility','visible');
    	$('.active').last().removeClass('active');
    });

    $('#status').find('li').click(function(){
        if($(this).hasClass('aa')){
            $(this).nextAll().removeClass('active');
            $(this).prevAll().addClass('active');
            $(this).addClass('active');
            var $t = $(this).index();
            $('.page').css('visibility','hidden');
            $('.page').eq($t).css('visibility','visible');
        }else{
            $(this).addClass('disabled');
        }
    });

    var provinces = ['王一', '王二', '王三', '王四', '王五']; 
            var substringMatcher = function (strs) {
                return function findMatches(q, cb) {
                    var matches, substrRegex;
                    matches = [];
                    substrRegex = new RegExp(q, 'i');
                    $.each(strs, function (i, str) {
                        if (substrRegex.test(str)) {
                            matches.push({ value: str });
                        }
                    });
                    cb(matches);
                };
            };

            $('#search').typeahead({
                hint: true,
                highlight: true,
                minLength: 1
            },
            {
                name: 'provinces',
                displayKey: 'value',
                source: substringMatcher(provinces)
            });
    dateSelect('classtime');
    $('#timepicki').timepicki();
//数字微调
    $( "#spinner" ).spinner();
//第三步表格追加删除
    $('.page input[name="model"]').click(function(){
        var _checked = $(this).prop('checked');
        var modelTr = '<tr class="Model"><td>模型</td><td></td><td><a href="javascript:;" class="btn btn-info" onclick="model_edit();">编辑模型</a></td></tr>';
        if(_checked){
            $(modelTr).appendTo('#needTable tbody');
        }else{
            $('.Model').remove();
        }
    });
    $('.page input[name="loss"]').click(function(){
        var _checked = $(this).prop('checked');
        var modelTr = '<tr class="Loss"><td>耗材</td><td></td><td><a href="javascript:;" class="btn btn-info" onclick="loss_edit();">编辑耗材</a></td></tr>';
        if(_checked){
            $(modelTr).appendTo('#needTable tbody');
        }else{
            $('.Loss').remove();
        }
    });
    $('.page input[name="teacher"]').click(function(){
        var _checked = $(this).prop('checked');
        var modelTr = '<tr class="Teacher"><td>教师</td><td></td><td><a href="javascript:;" class="btn btn-info" onclick="teacher_edit();">编辑教师</a></td></tr>';
        if(_checked){
            $(modelTr).appendTo('#needTable tbody');
        }else{
            $('.Teacher').remove();
        }
    });
    $('.page input[name="score"]').click(function(){
        var _checked = $(this).prop('checked');
        var modelTr = '<tr class="case"><td>病例</td><td></td><td><a href="javascript:;" class="btn btn-info" onclick="score_edit();">编辑病例</a></td></tr><tr class="score"><td>评分表</td><td></td><td><a href="javascript:;" class="btn btn-info" onclick="score_edit();">编辑评分表</a></td></tr><tr class="SP"><td>SP</td><td><input style="margin-right: 7px;" type="checkbox" name="Sp" id="needSP">课程需要SP</td><td><a href="javascript:;" class="btn btn-info" onclick="sp_edit();">编辑SP</a></td></tr>';
        if(_checked){
            $(modelTr).appendTo('#needTable2 tbody');
            $('#needSP').click(function(){
                var _checked2 = $(this).prop('checked');
                if(_checked2){
                    layer.open({
                        type:2,
                        title:'编辑SP',
                        area:['860px','700px'],
                        content:'./sp_edit.html',
                        btn:['确定','取消']
                    });
                }
            });
        }else{
            $('.case,.score,.SP').remove();
        }
    });
//第三步更换房间显示隐藏
    $('.page input[name="change"]').click(function(){
        var _checked = $(this).prop('checked');
        if(_checked){
            $('.change').show();
        }else{
            $('.change').hide();
        }
    });
//第二步课程模板Tree
    var singleObj = {
            treeName : 'cmodelTree',
            zNodes : [
                { id:1, pId:0, name:"全部课程模板", open:true},
                { id:11, pId:1, name:"课程类型1", open:true},
                { id:111, pId:11, name:"课程类型1-1"},
                { id:112, pId:11, name:"课程类型1-2"},
                { id:113, pId:11, name:"课程类型1-3"},
                { id:12, pId:1, name:"课程类型2", open:true},
                { id:121, pId:12, name:"课程类型2-1"},
                { id:122, pId:12, name:"课程类型2-2"},
                { id:13, pId:1, name:"课程类型3", open:true},
                { id:131, pId:13, name:"课程类型3-1"},
                { id:132, pId:13, name:"课程类型3-2"},
                { id:133, pId:13, name:"课程类型3-3"}
            ]
        };
        singleTree(singleObj);

    $('#select-c-model').click(function(){
        $('.all-c-model').toggle();
    });
    $('.all-c-model table').find('a').click(function(){
        var txt = $(this).text();
        $('input[name="select-c-model"]').val(txt);
        $('.all-c-model').hide();
    });

//选择DEBRIEF房间
    $('#selBtn').click(function(){
        var obj = {
            selTree: "roomTree",
            inputText: "roomSel",
            treeContent: "allRoom",
            selBtn:"selBtn",
            znodes: [
                { id:1, pId:0, name:"PBL", open:true,nocheck:true},
                { id:11, pId:1, name:"304房间"},
                { id:12, pId:1, name:"305房间"},
                { id:13, pId:1, name:"306房间"},
                { id:2, pId:0, name:"SP考站", nocheck:true},
                { id:21, pId:2, name:"404房间"},
                { id:22, pId:2, name:"405房间"},
                { id:23, pId:2, name:"406房间"},
                { id:3, pId:0, name:"模型考站", nocheck:true},
                { id:31, pId:3, name:"504房间"},
                { id:4, pId:0, name:"实验室", nocheck:true},
                { id:41, pId:4, name:"604房间"}
            ]
        }
        singleSelectTree(obj);
    });
//选择授课房间
    $('#selBtn2').click(function(){
        var obj = {
            selTree: "roomTree2",
            inputText: "roomSel2",
            treeContent: "allRoom2",
            selBtn:"selBtn",
            znodes: [
                { id:1, pId:0, name:"PBL", open:true,nocheck:true},
                { id:11, pId:1, name:"304房间"},
                { id:12, pId:1, name:"305房间"},
                { id:13, pId:1, name:"306房间"},
                { id:2, pId:0, name:"SP考站", nocheck:true},
                { id:21, pId:2, name:"404房间"},
                { id:22, pId:2, name:"405房间"},
                { id:23, pId:2, name:"406房间"},
                { id:3, pId:0, name:"模型考站", nocheck:true},
                { id:31, pId:3, name:"504房间"},
                { id:4, pId:0, name:"实验室", nocheck:true},
                { id:41, pId:4, name:"604房间"}
            ]
        }
        singleSelectTree(obj);
    });
//重置功能
    $('#items .pageReset').click(function(){
        $(this).parents('form')[0].reset();
    });
})