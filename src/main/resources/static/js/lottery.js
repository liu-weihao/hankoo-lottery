var arr = [];
var myNumber, single;
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
            if(result.status === 200) {
                participants = result.body;
                $(document).keydown(function(e) {
                    if (!e) e = window.event;
                    if (e.keyCode === 32) {
                        if (isOver) {
                            alert("抽奖活动已结束，点击姓名重抽");
                            return;
                        }
                        if (!hasBegun) {
                            myNumber = setInterval(showRandomNum, 25);//25ms运行一次
                            hasBegun = true;
                        } else {
                            draw();
                        }
                    }
                });

                $(".draw").click(function () {
                    var index = this.getAttribute("data");
                    var nodes = this.children;
                    //大前提是已经完整抽过一次了
                    if (isOver) {
                        if (!hasBegun) {
                            console.log(index + "重抽");
                            hasBegun = true;
                            nodes[1].innerHTML = "";
                            single = setInterval(function() {
                                nodes[0].innerHTML = ran(index);
                            }, 25);//25ms运行一次
                        } else {
                            redraw(this.id);
                        }
                    }
                });
            } else{
                alert("抽奖系统初始化失败");
            }
        }
    });
}

function init() {
    $.ajax({
        url : "/hankoo/lottery/prize.do?prizeId=" + prizeId,
        type : "GET",
        dataType : "json",
        success : function(result) {
            if(result.status === 200){
                var prize = result.body;
                if (prize && !prize.isOver) {
                    $(".prize-name").html(prize.award);
                    $("#award").attr("src", prize.pic);
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
            clearInterval(myNumber);
            if(result.status === 200){
                var participantIds = result.body;
                var winners = [];
                for (var i=0; i<participantIds.length; i++) {
                    for (var j=0; j<participants.length; j++) {
                        if (participantIds[i] === participants[j].id) {
                            winners[i] = participants[j];
                        }
                    }
                }
                hasBegun = false;
                isOver = true;
                setResult(winners);
            } else{
                alert("抽奖系统初始化失败");
            }
        }
    });
}
/*重抽*/
function redraw(elem) {
    $.ajax({
        url : "/hankoo/lottery/redraw.do",
        data: {"prizeId": prizeId},
        type : "POST",
        dataType : "json",
        success : function(result) {
            clearInterval(single);
            if(result.status === 200){
                var nodes = document.getElementById(elem).children;
                var participantId = result.body;
                for (var i=0; i<participants.length; i++) {
                    if (participantId === participants[i].id) {
                        nodes[0].innerHTML = participants[i].name;
                        nodes[1].innerHTML = participants[i].info;
                    }
                }
                hasBegun = false;
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
