var arr = [];
var first, second, third;
var myNumber;
var hasBegun = false, isOver = false;
var participants = [];
var prizeId;
/*将产生随机数的方法进行封装*/
function ran(num) {
	var name = participants[Math.floor((Math.random()*participants.length))].name;
	while($.inArray(name, arr) !== -1) {
		name = participants[Math.floor((Math.random()*participants.length))].name;
	}
	arr[num] = name;
    return name;
}

/*获取所有的参与者*/
function getParticipants() {
    $.ajax({
        url : "/hankoo/user/participants.do",
        type : "GET",
        dataType : "json",
        success : function(result) {
            if(result.status === 200){
                participants = result.body;
                $(document).keydown(function(e) {
                    if (!e) e = window.event;
                    if (e.keyCode === 32) {
                        if (isOver) {
                            alert("抽奖活动已结束");
                            return;
                        }
                        if (!hasBegun) {
                            myNumber = setInterval(showRandomNum, 55);//55ms运行一次
                            hasBegun = true;
                        } else {
                            draw();
                        }
                    }
                });
            } else{
                alert("抽奖系统初始化失败");
            }
        }
    });
}/*获取所有的参与者*/
function init() {
    $.ajax({
        url : "/hankoo/lottery/prize.do?prizeId=" + prizeId,
        type : "GET",
        dataType : "json",
        success : function(result) {
            if(result.status === 200){
                var prize = result.body;
                if (prize && !prize.isOver) {
                    getParticipants();
                } else {
                    isOver = true;
                    alert("抽奖活动已结束");
                }
            } else{
                alert("抽奖系统初始化失败");
            }
        }
    });
}
/*抽奖*/
function draw() {
    $.ajax({
        url : "/hankoo/lottery/draw.do",
        data: {"prizeId": prizeId},
        type : "POST",
        dataType : "json",
        success : function(result) {
            if(result.status === 200){
                var participantIds = result.body;
                var names = [];
                for (var i=0; i<participantIds.length; i++) {
                    for (var j=0; j<participants.length; j++) {
                        if (participantIds[i] === participants[j].id) {
                            names[i] = participants[j].name;
                        }
                    }
                }
                clearInterval(myNumber);
                hasBegun = false;
                isOver = true;
                setResult(names);
                // $("#first").html(names[0]);
                // $("#second").html(names[1]);
                // $("#third").html(names[2]);
            } else{
                alert("抽奖系统初始化失败");
            }
        }
    });
}

$(function () {
    prizeId = $("#prizeId").val();
    init();
});
