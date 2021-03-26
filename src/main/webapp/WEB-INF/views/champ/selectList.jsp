<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    <script type="text/javascript" src="/resources/js/jquery-3.5.1.js"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath }/resources/js/scrap.js"></script>
    <script src="${pageContext.request.contextPath }/resources/dist/circle-progress.js"></script>
    <style type="text/css">
        #content {
            background-color: white;
            margin-left: 0px;
        }

        .content1 {
            border: 3px solid black;
            border-radius: 25px;
            background-color: #ffffff;
            position: absolute;
            background-color: black;
            width: 30%;
            height: auto;
            color: white;

        }

        #ChampList {

            margin-top: 10px;
            position: relative;
            left: 70px;
            width: 1200px;
            height: 1200px;
        }

        #headingOne {
            position:
        }

        .skill_info {
            position: absolute;
            border: 3px solid black;
            border-radius: 25px;
            background-color: #ffffff;
            top: 5px;
            left: 450px;
            background-color: black;
            width: 30%;
            height: auto;
            color: white;

        }

        #picture {
            display: inline-block;
        }

        #skillIcon {
            position: absolute;
            top: 54px;
            left: 122px;
        }

        #skillIcon img {
            padding-left: 2px;

        }

        status {
            color: #50B4FF;
        }

        #selectSkill p {
            color: #50B4FF;
        }

        #name {
            font-size: 30px;
            color: black;
        }

        .content {
            border: 3px solid black;
            border-radius: 25px;
            background-color: #ffffff;
            position: absolute;
            background-color: black;
            width: 30%;
            height: auto;
            color: white;
        }


        #story {
            position: absolute;
            border: 3px solid black;
            width: 300px;
            height: 100px;
            top: 405px;
            text-overflow: ellipsis;
            overflow: hidden;
            white-space: nowrap;
            background-color: #FFA500;
        }

        #gametip {
            position: absolute;
            border: 3px solid black;
            width: 300px;
            height: 100px;
            top: 525px;
            background-color: #54BD54;
            text-overflow: ellipsis;
            overflow: hidden;
            white-space: nowrap;
        }


        #gametip2 {
            position: absolute;
            border: 3px solid #69A8AA;
            background-color: #54BD54;
            top: 413px;
            left: 891px;
            width: 300px;
            display: none;
            color: black;
        }

        .buttonCol {
            width: 98px;
        }

        .sic {
            padding-left: 50px;
        }

        #whichi {
            display: inline-block;
            position: absolute;
            right: 90px;
            top: 198px;
        }

        #whichi h1 {
            padding-left: 80px;
        }

        .table {


            margin-bottom: 1rem;
            background-color: transparent;
        }


        #picture {
            display: inline-block;
        }

        #name {
            display: inline-block;
        }

        #simte img {
            width: 50px;
        }

        #headingOne {
            position: absolute;
            left: 122px;
            top: 2px;
        }

        #headingTwo {
            position: absolute;
            left: 122px;
            top: 20px;

        }

        .collapse.show {
            display: inline-table;
            position: inherit;
        }

        #table1 img {
            width: 70px;
        }

        #circle1 strong {
            position: absolute;
            top: 50px;
            left: 70px;
            text-align: center;
            line-height: 40px;
            font-size: 24px;
        }

        #circle2 strong {
            position: absolute;
            top: 50px;
            left: 260px;
            text-align: center;
            line-height: 40px;
            font-size: 24px;
        }

        #circle3 strong {
            position: absolute;
            top: 50px;
            left: 440px;
            text-align: center;
            line-height: 40px;
            font-size: 24px;
        }

        .circleAll {
            padding-left: 30px;
            position: absolute;
            left: 600px;
            top: 5px;
        }

        #iteminfo {
            position: absolute;
            width: 300px;
            height: 550px;
            top: 200px;
            left: 1250px;
        }

        .tt {
            position: relative;
            display: inline-block;
            border-bottom: 2px dotted Sienna;
            background-color: yellow;
        }

        stats {
            color: #fafad2;
        }

        rules {
            color: #00ffff;
        }

        #faceToChamp {
            width: 40%;
        }

        #faceToChamp input {
            width: 49%;
        }
    </style>
</head>

<body>
<div id="ChampList">
    <div id="pictureSkill">
        <div id="picture"></div>
        <div id="skillIcon"></div>
        <div class='circleAll'>
            <div style='display:inline' id="circle1"><strong></strong><strong id='strong2'></strong></div>
            <div style='display:inline' id="circle2"><strong></strong></div>
            <div style='display:inline' id="circle3"><strong></strong></div>
        </div>
        <div class="panel-heading" role="tab" id="headingOne">
            <h5 class="pa">
                <a data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="true"
                   aria-controls="collapseOne">
                    스토리 바로보기
                </a>
            </h5>
        </div>
        <div id="collapseOne" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOne">
            <div class="panel-body 1">

            </div>
        </div>

        <div class="panel panel-default">
            <div class="panel-heading" role="tab" id="headingTwo">
                <h5 class="panel-title">
                    <a class="collapsed" data-toggle="collapse" data-parent="#accordion" href="#collapseTwo"
                       aria-expanded="false" aria-controls="collapseTwo">
                        챔피언 공략 바로보기
                    </a>
                </h5>
            </div>
            <div id="collapseTwo" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingTwo">
                <div class="panel-body 2">

                </div>
            </div>
        </div>
    </div>

    <%--<div id="faceToChamp" >
        <input type="button" value="카운터 챔피언"> <input type="button" value="상대하기 어려운 챔피언">
        <table>
            <tr>
                <th>챔피언 이름</th>
                <th>승률</th>
                <th>상대전적</th>
            </tr>
        </table>
    </div>--%>
    <%--table class="table table-striped winlist" id='recommendTable'>
        <tr>

        </tr>
    </table>--%>
<%--${championRankMap}
    ${championRankMap[1].SNICKNAME}--%>
    <br>
    <br>

    <input type="hidden" id="rating0025" value="<fmt:formatNumber value="${(resultList[0].win0025 / (resultList[0].win0025 + resultList[1].lose0025)) * 100}"/>">
    <input type="hidden" id="rating2530" value="<fmt:formatNumber value="${(resultList[0].win2530 / (resultList[0].win2530 + resultList[1].lose2530)) * 100}"/>">
    <input type="hidden" id="rating3035" value="<fmt:formatNumber value="${(resultList[0].win3035 / (resultList[0].win3035 + resultList[1].lose3035)) * 100}"/>">
    <input type="hidden" id="rating3540" value="<fmt:formatNumber value="${(resultList[0].win3540 / (resultList[0].win3540 + resultList[1].lose3540)) * 100}"/>">
    <input type="hidden" id="rating4045" value="<fmt:formatNumber value="${(resultList[0].win4000 / (resultList[0].win4000 + resultList[1].lose4000)) * 100}"/>">

    <c:forEach var="i" items="${winloseByTierMap}">
        <c:set var="rating" value="${i.value[0] / (i.value[0] + i.value[1]) * 100}"/>
        <c:if test="${rating == 'NaN' || rating <= 0}">
            <c:set var="rating" value="0"/>
        </c:if>
        <input type="hidden" id="tierRate_${i.key}" value="${rating}">
    </c:forEach>



    <div id="chart_div"></div>
    <div id="tierWinloseRateChart"></div>
    <h3>추천아이템</h3>
    <table class="table table-striped winlist" id='recommendTable' style="text-align: center;">
        <tr>
            <c:forEach var="item" items="${recommItem}" varStatus="stats">
                <c:if test="${stats.index < 6}">
                    <td>
                        <img src="http://ddragon.leagueoflegends.com/cdn/11.4.1/img/item/${item}.png">
                    </td>
                </c:if>
            </c:forEach>
        </tr>
    </table>
    <h3>장인랭킹</h3>
    <table class="table table-striped winlist" id='championRankTable' style="text-align: center;">
        <tr>
            <th>순위</th>
            <th>닉네임</th>
            <th>플레이횟수</th>
            <th>추천아이템</th>
        </tr>
        <c:forEach var="item" items="${championRankMap}" varStatus="stats">
            <c:if test="${stats.index < 3}">
                <tr>
                    <td>${stats.index + 1}</td>
                    <td><a href="/match/search?sName=${item.SNICKNAME}">${item.SNICKNAME}</a></td>
                    <td>${item.COUNT}</td>
                    <td>
                        <c:forEach var="item" items="${rankerItemListMap[item.SNICKNAME]}" varStatus="st">
                            <c:if test="${st.index < 6}">
                                <img src="http://ddragon.leagueoflegends.com/cdn/11.4.1/img/item/${item}.png">
                            </c:if>
                        </c:forEach>
                    </td>
                </tr>
            </c:if>
        </c:forEach>
    </table>
    <span class="wwww">${winloseByTierMap}</span>
    <div id='iteminfo'></div>


    <div id='selectskill'></div>

    <div id="simte">

    </div>

    <div id="itemcon"></div>
    <div id="selectSkill"></div>
    <div id="skillinfo"></div>
    <div id="nn"></div>
    <div id="story2"></div>
    <div id="gametip2"></div>


    <div id='tt'></div>
</div>


<script type="text/javascript">

    $(function () {

        console.log("test")


        var list = ${championid}

            $.ajax({
                url: "/champ/count",
                data: {
                    championid: list
                },
                dataType: 'json',
                success: function (data) {
                    if (data.winrank == null) {
                        $('#circle1').circleProgress({
                            value: 0,

                            size: 180,
                            fill: {
                                gradient: ["#FFF56E", "#8B4513"]
                            }
                        }).on('circle-animation-progress',
                            //그래프 애니메이션이 진행되는 동안
                            function (event, progress, stepValue) {
                                //progress -현재 진행상탱 0.0~1.0
                                //stepValue - 현재까지 그려진 그래프 값
                                $(this).find('strong').html("승률<br>통계부족");

                            });

                    } else {
                        $('#circle1').circleProgress({
                            value: data.winrank.WINRANK / 100,

                            size: 180,
                            fill: {
                                gradient: ["red", "orange"]
                            }
                        }).on('circle-animation-progress',
                            //그래프 애니메이션이 진행되는 동안
                            function (event, progress, stepValue) {
                                //progress -현재 진행상탱 0.0~1.0
                                //stepValue - 현재까지 그려진 그래프 값
                                $(this).find('strong').html("승률:" + Math.round(data.winrank.WINRANK * progress) + '<i>%</i><br>승률:' + data.winrank.RANK + '위');
                            });
                    }
                    if (data.banrank == null) {
                        $('#circle2').circleProgress({
                            value: 0,

                            size: 180,
                            fill: {
                                gradient: ["#FFF56E", "#8B4513"]
                            }
                        }).on('circle-animation-progress',
                            //그래프 애니메이션이 진행되는 동안
                            function (event, progress, stepValue) {
                                //progress -현재 진행상탱 0.0~1.0
                                //stepValue - 현재까지 그려진 그래프 값
                                $(this).find('strong').html("벤률<br>통계부족");

                            });

                    } else {
                        $('#circle2').circleProgress({
                            value: data.banrank.BANPER / 100,

                            size: 180,
                            fill: {
                                gradient: ["#FFF56E", "#8B4513"]
                            }
                        }).on('circle-animation-progress',
                            //그래프 애니메이션이 진행되는 동안
                            function (event, progress, stepValue) {
                                //progress -현재 진행상탱 0.0~1.0
                                //stepValue - 현재까지 그려진 그래프 값
                                $(this).find('strong').html("벤률:" + Math.round(data.banrank.BANPER * progress) + '<i>%</i><br>벤률:' + data.banrank.RANK + '위');

                            });
                    }
                    ;
                    if (data.maxCount == null) {
                        $('#circle3').circleProgress({
                            value: 0,

                            size: 180,
                            fill: {
                                gradient: ["#FFF56E", "#8B4513"]
                            }
                        }).on('circle-animation-progress',
                            //그래프 애니메이션이 진행되는 동안
                            function (event, progress, stepValue) {
                                //progress -현재 진행상탱 0.0~1.0
                                //stepValue - 현재까지 그려진 그래프 값
                                $(this).find('strong').html("픽률<br>통계부족");

                            });
                    } else {
                        $('#circle3').circleProgress({
                            value: data.maxCount.PICKPER / 100,

                            size: 180,
                            fill: {
                                gradient: ["#FFD2D7", "#B9062F"]
                            }
                        }).on('circle-animation-progress',
                            //그래프 애니메이션이 진행되는 동안
                            function (event, progress, stepValue) {
                                //progress -현재 진행상탱 0.0~1.0
                                //stepValue - 현재까지 그려진 그래프 값
                                $(this).find('strong').html("픽률:" + Math.round(data.maxCount.PICKPER * progress) + '<i>%</i><br>픽률:' + data.maxCount.RANK + '위');
                            });
                    }
                    $("#pickbtn").css({
                        border: "3px solid red",
                    })

                    $("#banbtn").click(function () {
                        $(this).css({
                            border: "3px solid red",
                        })
                        $("#winbtn").css({
                            border: "none",
                        })
                        $("#pickbtn").css({
                            border: "none",
                        })
                        $("#pickben2").empty();


                    })
                    $("#pickbtn").click(function () {
                        $(this).css({
                            border: "3px solid red",
                        })
                        $("#banbtn").css({
                            border: "none",
                        })
                        $("#winbtn").css({
                            border: "none",
                        })

                        $("#pickben2").empty();


                    })
                    $("#winbtn").click(function () {
                        $(this).css({
                            border: "3px solid red",
                        })
                        $("#pickbtn").css({
                            border: "none",
                        })
                        $("#pickbtn").css({
                            border: "none",
                        })

                        $("#pickben2").empty();

                    })

                    if (data.itemlist.length == 0) {
                        $("#table1").append(
                            "<tbody><tr><td id='tabletd'>통계가 부족하여 데이터가 없습니다.</td></tr></tbody>");
                    } else {
                        $("#table1").append(
                            "<tbody><tr><td id='tabletd'>승리팀 세팅</td></tr></tbody>");

                        var rune1 = "";
                        var rune2 = "";
                        var spell11 = "";
                        $.each(data.itemlist, function (key, value) {
                            rune1 = value.RUNE1;
                            rune2 = value.RUNE2;
                            spell11 = value.SPELL1;

                            let item1 = "";
                            let item2 = "";
                            let item3 = "";
                            let item4 = "";
                            let item5 = "";
                            let item6 = "";
                            if (value.ITEM1) {
                                item1 = "<img onmouseover='ganada(event);' onmouseout='ganaout();' id='" + value.ITEM1 + "' src='http://ddragon.leagueoflegends.com/cdn/11.4.1/img/item/" + value.ITEM1 + ".png'>"
                            } else {
                                item1 = "<img  src='/resources/images/noItem.png'>"

                            }
                            if (value.ITEM2) {
                                item2 = "<img onmouseover='ganada(event);' onmouseout='ganaout();' id='" + value.ITEM2 + "' src='http://ddragon.leagueoflegends.com/cdn/11.4.1/img/item/" + value.ITEM2 + ".png'>"


                            } else {
                                item2 = "<img  src='/resources/images/noItem.png'>"

                            }
                            if (value.ITEM3) {
                                item3 = "<img onmouseover='ganada(event);' onmouseout='ganaout();' id='" + value.ITEM3 + "' src='http://ddragon.leagueoflegends.com/cdn/11.4.1/img/item/" + value.ITEM3 + ".png'>"

                            } else {
                                item3 = "<img <img src='/resources/images/noItem.png'>"
                            }
                            if (value.ITEM4) {
                                item4 = "<img onmouseover='ganada(event);' onmouseout='ganaout();' id='" + value.ITEM4 + "' src='http://ddragon.leagueoflegends.com/cdn/11.4.1/img/item/" + value.ITEM4 + ".png'>"

                            } else {
                                item4 = "<img  src='/resources/images/noItem.png'>"
                            }
                            if (value.ITEM5) {
                                item5 = "<img onmouseover='ganada(event);' onmouseout='ganaout();' id='" + value.ITEM5 + "' src='http://ddragon.leagueoflegends.com/cdn/11.4.1/img/item/" + value.ITEM5 + ".png'>"

                            } else {
                                item5 = "<img src='/resources/images/noItem.png'>"
                            }
                            if (value.ITEM6) {
                                item6 = "<img onmouseover='ganada(event);' onmouseout='ganaout();' id='" + value.ITEM6 + "' src='http://ddragon.leagueoflegends.com/cdn/11.4.1/img/item/" + value.ITEM6 + ".png'>"

                            } else {
                                item6 = "<img src='/resources/images/noItem.png'>"
                            }


                            var rune1 = value.RUNE1;
                            var rune2 = value.RUNE2;
                            var spell1 = value.SPELL1;
                            var spell2 = value.SPELL2;
                            var cc = "";
                            var dd = "";
                            var spell_1 = "";
                            var spell_2 = "";
                            $.ajax({
                                url: 'http://ddragon.leagueoflegends.com/cdn/11.3.1/data/ko_KR/runesReforged.json',
                                dataType: 'json',
                                type: "get",
                                success: function (data) {

                                    var m1 = "";
                                    for (idx in data) {
                                        if (data[idx].id == rune1) {
                                            cc = "<img  src='http://ddragon.leagueoflegends.com/cdn/img/" + data[idx].icon + "'>";

                                        }
                                        if (data[idx].id == rune2) {
                                            dd = "<img  src='http://ddragon.leagueoflegends.com/cdn/img/" + data[idx].icon + "'>";

                                        }

                                    }
                                    if (spell1 == 21) {
                                        spell_1 = "SummonerBarrier.png";

                                    } else if (spell1 == 1) {
                                        spell_1 = "SummonerBoost.png";
                                    }
                                    if (spell1 == 14) {
                                        spell_1 = "SummonerDot.png";

                                    } else if (spell1 == 3) {
                                        spell_1 = "SummonerExhaust.png";
                                    }
                                    if (spell1 == 4) {
                                        spell_1 = "SummonerFlash.png";

                                    } else if (spell1 == 6) {
                                        spell_1 = "SummonerHaste.png";
                                    }
                                    if (spell1 == 7) {
                                        spell_1 = "SummonerHeal.png";

                                    } else if (spell1 == 13) {
                                        spell_1 = "SummonerMana.png";
                                    }
                                    if (spell1 == 11) {
                                        spell_1 = "SummonerSmite.png";

                                    } else if (spell1 == 12) {
                                        spell_1 = "SummonerTeleport.png";
                                    }
                                    if (spell1 == 21) {
                                        spell_1 = "SummonerBarrier.png";

                                    } else if (spell1 == 1) {
                                        spell_1 = "SummonerBoost.png";
                                    }
                                    if (spell1 == 14) {
                                        spell_1 = "SummonerDot.png";

                                    } else if (spell1 == 3) {
                                        spell_1 = "SummonerExhaust.png";
                                    }
                                    if (spell1 == 4) {
                                        spell_1 = "SummonerFlash.png";

                                    } else if (spell1 == 6) {
                                        spell_1 = "SummonerHaste.png";
                                    }
                                    if (spell1 == 7) {
                                        spell_1 = "SummonerHeal.png";

                                    } else if (spell1 == 13) {
                                        spell_1 = "SummonerMana.png";
                                    } else if (spell1 == 11) {
                                        spell_1 = "SummonerSmite.png";

                                    } else if (spell1 == 12) {
                                        spell_1 = "SummonerTeleport.png";
                                    }


                                    if (spell2 == 21) {
                                        spell_2 = "SummonerBarrier.png";

                                    } else if (spell2 == 1) {
                                        spell_2 = "SummonerBoost.png";
                                    }
                                    if (spell2 == 14) {
                                        spell_2 = "SummonerDot.png";

                                    } else if (spell2 == 3) {
                                        spell_2 = "SummonerExhaust.png";
                                    }
                                    if (spell2 == 4) {
                                        spell_2 = "SummonerFlash.png";

                                    } else if (spell2 == 6) {
                                        spell_2 = "SummonerHaste.png";
                                    }
                                    if (spell2 == 7) {
                                        spell_2 = "SummonerHeal.png";

                                    } else if (spell2 == 13) {
                                        spell_2 = "SummonerMana.png";
                                    }
                                    if (spell2 == 11) {
                                        spell_2 = "SummonerSmite.png";

                                    } else if (spell2 == 12) {
                                        spell_2 = "SummonerTeleport.png";
                                    }
                                    if (spell2 == 21) {
                                        spell_2 = "SummonerBarrier.png";

                                    } else if (spell2 == 1) {
                                        spell_2 = "SummonerBoost.png";
                                    }
                                    if (spell2 == 14) {
                                        spell_2 = "SummonerDot.png";

                                    } else if (spell2 == 3) {
                                        spell_2 = "SummonerExhaust.png";
                                    }
                                    if (spell2 == 4) {
                                        spell_2 = "SummonerFlash.png";

                                    } else if (spell2 == 6) {
                                        spell_2 = "SummonerHaste.png";
                                    }
                                    if (spell2 == 7) {
                                        spell_2 = "SummonerHeal.png";

                                    } else if (spell2 == 13) {
                                        spell_2 = "SummonerMana.png";
                                    } else if (spell2 == 11) {
                                        spell_2 = "SummonerSmite.png";

                                    } else if (spell2 == 12) {
                                        spell_2 = "SummonerTeleport.png";
                                    }

                                    let spell_11 = "<img onmouseover='spell(event);' onmouseout='spellout();'  src='http://ddragon.leagueoflegends.com/cdn/11.4.1/img/spell/" + spell_1 + "'>";
                                    let spell_22 = "<img onmouseover='spell(event);'  onmouseout='spellout();' src='http://ddragon.leagueoflegends.com/cdn/11.4.1/img/spell/" + spell_2 + "'>";
                                    $("<tr><td>" + (key + 1) + "</td><td>" + item1 + "</td><td >" + item2 +
                                        "</td><td >" + item3 + "</td><td >" + item4 + "</div></td><td>" + item5 + "</td><td>" + item6 + "</td><td>" + spell_11 + "</td><td>" + spell_22 +
                                        "</td><td> " + cc +
                                        "</td><td>" + dd + "</td></tr>").appendTo("#table1 tbody");
                                }
                            })


                        })

                    }
                }

            })
        $.ajax({
            url: "/champ/selectList1",
            data: {
                championid: list
            },
            dataType: 'json',
            success: function (data) {


                let Q = JSON.parse(data.skillQ_info);
                let W = JSON.parse(data.skillW_info);
                let E = JSON.parse(data.skillE_info);
                let R = JSON.parse(data.skillR_info);

                let skillp = JSON.parse(data.skillP_info);
                let SkillP = skillp.description;


                let q_ = Q.tooltip.split("{");

                var q_sum = "";
                for (var kk = 0; kk <= q_.length; kk += 2) {

                    q_sum += "?"
                        + q_[kk].substring(
                            q_[kk].indexOf('}}') + 2,
                            q_[kk].length);

                }
                let SkillQ = "<p>" + Q.name + "</p><br>" + q_[0] + q_sum;


                let w_ = W.tooltip.split("{");

                var w_sum = "";
                for (var kk = 0; kk <= w_.length; kk += 2) {

                    w_sum += "<"
                        + w_[kk].substring(
                            w_[kk].indexOf('}}') + 2,
                            w_[kk].length);

                }
                let SkillW = "<p>" + W.name + "</p><br>" + w_[0] + w_sum;

                let e_ = E.tooltip.split("{");
                var e_sum = "";
                for (var kk = 0; kk <= e_.length; kk += 2) {

                    e_sum += "<"
                        + e_[kk].substring(
                            e_[kk].indexOf('}}') + 2,
                            e_[kk].length);


                }
                let SkillE = "<p>" + E.name + "</p><br>" + e_[0] + e_sum;
                let r_ = R.tooltip.split("{");

                var r_sum = "";
                for (var kk = 0; kk <= r_.length; kk += 2) {

                    r_sum += "?"
                        + r_[kk].substring(
                            r_[kk].indexOf('}}') + 2,
                            r_[kk].length);

                }
                let SkillR = "<p>" + R.name + "</p><br>" + r_[0] + r_sum;

                $("#picture")
                    .append(
                        "<div><img src='http://ddragon.leagueoflegends.com/cdn/11.4.1/img/champion/" + data.picture + "'><br>" + data.name + "</div>");
                $("#name").append(data.name);


                $("#skillIcon")
                    .append(
                        "<img  id='sp' src='http://ddragon.leagueoflegends.com/cdn/11.4.1/img/passive/" + data.skillP_icon + "'>");
                $("#skillIcon")
                    .append(
                        "<img  id='sq' src='http://ddragon.leagueoflegends.com/cdn/11.4.1/img/spell/" + data.skillQ_icon + "'>");
                $("#skillIcon")
                    .append(
                        "<img  id='sw' src='http://ddragon.leagueoflegends.com/cdn/11.4.1/img/spell/" + data.skillW_icon + "'>");
                $("#skillIcon")
                    .append(
                        "<img  id='se' src='http://ddragon.leagueoflegends.com/cdn/11.4.1/img/spell/" + data.skillE_icon + "'>");
                $("#skillIcon")
                    .append(
                        "<img  id='sr' src='http://ddragon.leagueoflegends.com/cdn/11.4.1/img/spell/" + data.skillR_icon + "'>");
                var tip = data.tip;
                if (data.tip.length == 10) {
                    tip = "데이터가 부족해 통계가 없습니다."

                }
                $("<div><h3 style='color:black;'>스토리</h3><a style='color:black' id='storyboard' href='#'>" + data.story + "</a></div>").appendTo(".panel-body.1")
                $("<div><h3 style='color:black;'>게임팁</h3><a style='color:black' id='storyboard' href='#'>" + tip + "</a></div>").appendTo(".panel-body.2")
                $("#story").hover(function () {


                    $("#story2").append("<span>" + data.story + "</span>");
                    $("#story2").css({
                        display: "inline"
                    })

                }, function () {
                    $("#story2").css({
                        display: "none"
                    })
                    $("#story2").empty();

                })
                $("#gametip").hover(function () {


                    $("#gametip2").append("<span>" + data.tip + "</span>");
                    $("#gametip2").css({
                        display: "inline"
                    })

                }, function () {
                    $("#gametip2").css({
                        display: "none"
                    })
                    $("#gametip2").empty();

                })


                $("#sp").hover(
                    function () {
                        $("<div><status>" + skillp.name + "</status><br><br><br>" + SkillP + "</div>").appendTo(
                            "#selectSkill").addClass(
                            "skill_info");
                    }, function () {
                        $("#selectSkill").empty();
                    })

                $("#sq").hover(
                    function () {
                        $("<div>" + SkillQ + "</div>").appendTo(
                            "#selectSkill").addClass(
                            "skill_info");
                    }, function () {
                        $("#selectSkill").empty();
                    })

                $("#sw").hover(
                    function () {
                        $("<div>" + SkillW + "</div>").appendTo(
                            "#selectSkill").addClass(
                            "skill_info");
                    }, function () {
                        $("#selectSkill").empty();
                    })

                $("#se").hover(
                    function () {
                        $("<div>" + SkillE + "</div>").appendTo(
                            "#selectSkill").addClass(
                            "skill_info");
                    }, function () {
                        $("#selectSkill").empty();
                    })

                $("#sr").hover(
                    function () {
                        $("<div>" + SkillR + "</div>").appendTo(
                            "#selectSkill").addClass(
                            "skill_info");
                    }, function () {
                        $("#selectSkill").empty();
                    })
                var name = data.picture;
                var name1 = name.substring(0, name.length - 4);


            }

        })


    })

    function ganada(e) {
        $("#iteminfo").empty();

        var lets = e.target.src.split("/");
        var code = lets[7].substr(0, lets[7].length - 4)
        $.ajax({
            url: "/iteminfo?inum=" + code,
            type: 'get',
            dataType: 'json',
            success: function (data) {
                var nameeffect = "<div><img src='http://ddragon.leagueoflegends.com/cdn/11.4.1/img/item/" + data.icon + "'><br>" + data.name + "<br>" + data.effect + "</div>";

                $("#iteminfo").append(nameeffect);
                $("#iteminfo").css({
                    backgroundColor: "black",
                    color: "white",
                })
            }
        })
    }

    function ganaout() {
        $("#iteminfo").css({
            backgroundColor: "white",
            color: "white",
        })
        $("#iteminfo").empty();

    }

    function spell(e) {
        $("#iteminfo").empty();
        var lets = e.target.src.split("/");
        var code = lets[7].substr(0, lets[7].length - 4);
        $.ajax({
            url: "http://ddragon.leagueoflegends.com/cdn/11.4.1/data/ko_KR/summoner.json",
            dataType: 'json',
            success: function (data) {
                var cc = data.data[code].description;
                var bb = data.data[code].name;
                var nameeffect = "<div><img src='http://ddragon.leagueoflegends.com/cdn/11.4.1/img/spell/" + code + ".png'><br>" + bb + "<br>" + cc + "</div>";

                $("#iteminfo").append(nameeffect);
                $("#iteminfo").css({
                    backgroundColor: "black",
                    color: "white",
                })
            }
        })


    }

    function spellout() {
        $("#iteminfo").css({
            backgroundColor: "white",
            color: "white",
        })
        $("#iteminfo").empty();

    }
</script>


<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
<script type="text/javascript">
    var rating0025 = $("#rating0025").val();
    var rating2530 = $("#rating2530").val();
    var rating3035 = $("#rating3035").val();
    var rating3540 = $("#rating3540").val();
    var rating4000 = $("#rating4000").val();
    if (isNaN(rating0025)) rating0025 = 0;
    if (isNaN(rating2530)) rating2530 = 0;
    if (isNaN(rating3035)) rating3035 = 0;
    if (isNaN(rating3540)) rating3540 = 0;
    if (isNaN(rating4000)) rating4000 = 0;

    google.charts.load('current', {'packages':['corechart']});
    google.charts.setOnLoadCallback(drawChart);
    google.charts.setOnLoadCallback(drawChart2);

    function drawChart() {
        var data = google.visualization.arrayToDataTable([
            ['게임 시간', '승률'],
            ['00~25',  rating0025],
            ['25~30',  rating2530],
            ['30~35',  rating3035],
            ['35~40',  rating3540],
            ['40~',  rating4000]
        ]);

        var options = {
            title: '게임 길이별 승률',
            vAxis: {minValue: 0}
        };

        var chart = new google.visualization.AreaChart(document.getElementById('chart_div'));
        chart.draw(data, options);
    }

    function drawChart2() {


        var data = google.visualization.arrayToDataTable([
            ['티어', '승률'],
            ['아이언',  parseInt($("#tierRate_IRON").val())],
            ['브론즈',  parseInt($("#tierRate_BRONZE").val())],
            ['실버',  parseInt($("#tierRate_SILVER").val())],
            ['골드',  parseInt($("#tierRate_GOLD").val())],
            ['플레티넘',  parseInt($("#tierRate_PLATINUM").val())],
            ['다이아',  parseInt($("#tierRate_DIAMOND").val())],
            ['마스터', parseInt($("#tierRate_MASTER").val())],
            ['그랜드마스터', parseInt($("#tierRate_GRANDMASTER").val())],
            ['챌린저', parseInt($("#tierRate_CHALLENGER").val())]
        ]);

        var options = {
            title: '티어별 승률',
            vAxis: {minValue: 0}
        };

        var chart = new google.visualization.AreaChart(document.getElementById('tierWinloseRateChart'));
        chart.draw(data, options);
    }
</script>

</body>
</html>