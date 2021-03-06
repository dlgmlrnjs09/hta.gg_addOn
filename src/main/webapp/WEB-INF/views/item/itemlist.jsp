<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <style type="text/css">
        #main {
            position: relative;
        }

        .content {

            border-radius: 25px;
            background-color: #ffffff;
            position: absolute;
            background-color: #343a40;
            width: 400px;
            height: auto;
            color: white;

        }

        #info {

            height: 500px;
            width: 500px;
            position: fixed;
            left: 1050px;
            top: 130px;

        }

        img {
            margin: 2px;
        }

        h1 {
            color: white;
        }

        stats {
            color: #fafad2;
        }

        rules {
            color: #00ffff;
        }

        .radio {
            color: #ffffff;
        }

        .sortResultDiv {
            background: #ffffff;
        }

        #winloseRateDiv {
            color: #ffffff;
        }
    </style>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    <script type="text/javascript" src="/resources/js/jquery-3.5.1.js"></script>
    <script type="text/javascript">
        $(function () {
            $(window).scroll(function (event) {
                if (jQuery(window).scrollTop() > jQuery(".banner").offset().top) {
                    jQuery("#info").css("position", "fixed");
                } else if ((jQuery(window).scrollTop() < jQuery(".banner").offset().top)) {
                    jQuery("#info").css("position", "static");
                }

            })
            $
                .ajax({
                    contentType: 'application/json',
                    url: "/itemlist",
                    type: 'get',
                    dataType: 'json',

                    success: function (data) {

                        $
                            .each(
                                data,
                                function (key, value) {

                                    var str2 = "<img src=http://ddragon.leagueoflegends.com/cdn/11.2.1/img/item/" + value.icon + " style='width:60px; '" +
                                        "onmouseover='iteminfoinfo(" + value.inum + ")' onmouseout='iteminfo2()'>";
                                    if (value.price <= 500 && value.name != '????????? ???' || value.tags == 'SpellVamp' || value.tags == 'Jungle') {
                                        $(str2).appendTo('#start').addClass("iteminfo");
                                    } else if (value.tags == 'Boots' || value.tags == 'MagicPenetration' || value.name == "??????????????? ?????????") {
                                        $(str2).appendTo('#boots').addClass("iteminfo");
                                    } else if (value.tags == 'Active' || value.tags == 'Consumable' || value.tags == 'Trinket') {
                                        $(str2).appendTo('#actives').addClass("iteminfo");
                                    } else if (value.tags == 'SpellDamage' || value.name == '????????????' || value.tags == 'ManaRegen') {
                                        $(str2).appendTo('#mana').addClass("iteminfo");
                                    } else if (value.tags == 'AttackSpeed' || value.tags == 'CriticalStrike' || value.tags == 'OnHit') {
                                        $(str2).appendTo('#attackSpeed').addClass("iteminfo");
                                    } else if (value.tags == 'Mana') {
                                        $(str2).appendTo('#mana').addClass("iteminfo");
                                    } else if (value.tags == 'Armor' || value.tags == 'Health' || value.tags == 'SpellBlock' || value.tags == 'HealthRegen' || value.tags == 'NonbootsMovement') {
                                        $(str2).appendTo('#armor').addClass("iteminfo");
                                    } else if (value.tags == 'CriticalStrike') {
                                        $(str2).appendTo('#critical').addClass("iteminfo");
                                    } else if (value.tags == 'Trinket' || value.tags == 'ArmorPenetration' || value.tags == 'CriticalStrike' || value.tags == 'LifeSteal' || value.tags == 'Damage' || value.tags == 'ArmorPenetration' || value.tags == 'Vision' || value.tags == 'ArmorPenetration' || value.tags == 'Vision' || value.tags == 'SpellVamp' || value.tags == 'Stealth') {
                                        $(str2).appendTo('#demege').addClass("iteminfo");
                                    } else if (value.tags == 'Demage') {
                                        $(str2).appendTo('#demege').addClass("iteminfo");
                                    } else if (value.tags = 'null') {
                                        $(str2).appendTo('#actives').addClass("iteminfo");
                                    }

                                })

                    }
                })

        });

        var killAvg = 0;
        var deathAvg = 0;
        var assistAvg = 0;
        var kda = 0;

        var img = "";

        function iteminfoinfo(inum) {
            var x = event.clientX;
            var y = event.clientY;

            $.get({
                url: '/getKdaAvg?inum=' + inum,
                success: function (data) {
                    killAvg = parseFloat(data.getElementsByTagName("doubles")[0].getElementsByTagName("item")[0].innerHTML);
                    deathAvg = parseFloat(data.getElementsByTagName("doubles")[0].getElementsByTagName("item")[1].innerHTML);
                    assistAvg = parseFloat(data.getElementsByTagName("doubles")[0].getElementsByTagName("item")[2].innerHTML);
                    kda = (killAvg + deathAvg) / deathAvg
                    kda = kda.toFixed(2);
                }
            });

            $.get({
                url: '/getUseItemCountByChampion?inum=' + inum,
                success: function (data) {

                    img = "<br>??? ???????????? ?????? ?????? ????????? ????????? TOP 5<br>";

                    for (let i=0; i<5; i++) {
                        img += "<img style='width=75px; height: 75px;' src='http://ddragon.leagueoflegends.com/cdn/11.3.1/img/champion/" + Object.keys(data[i]) +"'>";
                    }
                    /*console.log(img)*/
                },
                dataType: 'json',
                async: false
            });

            $.ajax({
                contentType: 'application/json',
                url: "/iteminfo?inum=" + inum,
                dataType: 'json',
                success: function (data) {

                    var msg = "";

                    if (!isNaN(kda)) {
                        msg = "??? ???????????? ????????? ???????????? ?????? KDA : <span style='color: red; font-weight: bold'>" + kda + " [" + killAvg + "/" + deathAvg + "/" + assistAvg + "]</span>"
                    }

                    console.log(img)
                    var nameeffect = "<div><h2>" + data.name + "</h2><p>??????:" + data.price + "<br><img src=http://ddragon.leagueoflegends.com/cdn/11.2.1/img/item/" + data.icon + " style='width:64px;'><br>" + data.effect + "<br>" + msg + "<br>" + img + "</div>";

                    $("#effect").empty();
                    $(nameeffect).appendTo("#info").addClass('content');

                    killAvg = 0;
                    deathAvg = 0;
                    assistAvg = 0;
                    kda = 0;
                }
            })
        }

        function iteminfo2() {

            $("#info").empty();
        }
    </script>
</head>
<body>
<div style="width:800px;height: 100%; background-color: black" id="main">
    <h1>?????? ?????????</h1>
    <div class="btn-group" role="group" aria-label="Button group with nested dropdown">
        <button type="button" id="sortPopByAll" class="btn btn-secondary">??????</button>
        <button type="button" id="sortPopByPrice" class="btn btn-secondary">?????????</button>

        <div class="btn-group" role="group">
            <button id="btnGroupDrop1" type="button" class="btn btn-secondary dropdown-toggle" data-toggle="dropdown"
                    aria-haspopup="true" aria-expanded="false">
                ?????????
            </button>
            <div class="dropdown-menu" aria-labelledby="btnGroupDrop1">
                <a class="dropdown-item" href="#" onclick="sortItemBestByTag('Active')">????????????</a>
                <a class="dropdown-item" href="#" onclick="sortItemBestByTag('SpellDamage')">????????????</a>
                <a class="dropdown-item" href="#" onclick="sortItemBestByTag('Damage')">????????????</a>
                <a class="dropdown-item" href="#" onclick="sortItemBestByTag('AttackSpeed')">??????/??????</a>
                <a class="dropdown-item" href="#" onclick="sortItemBestByTag('Armor')">????????????</a>
                <a class="dropdown-item" href="#" onclick="sortItemBestByTag('Boots')">????????????</a>
            </div>
        </div>
    </div>
    <div id="popSort_resultDiv" class="sortResultDiv">
        <div id="popSort_searchDiv"></div>
        <div id="popSort_itemList"></div>
    </div>
    <br>

    <h1>????????? ?????????</h1>
    <div id="winloseRateDiv">
        <input type="checkbox" id="priceCheckbox">
        ?????? : <input type="number" id="minPrice" min="1" max="4000" step="1" value="0" disabled="disabled"> ~
        <input type="number" id="maxPrice" min="1" max="4000" step="1" value="0" disabled="disabled">
        <br>
        <label for="tag">?????? :</label><select id="tag">
            <option value="0" selected="selected">??????</option>
            <option value="Active">????????????</option>
            <option value="SpellDamage">????????????</option>
            <option value="Damage">????????????</option>
            <option value="AttackSpeed">??????/??????</option>
            <option value="Armor">????????????</option>
            <option value="Boots">????????????</option>
        </select>
        <br>
        <input type="button" id="sortBtn" value="??????" style="width: 250px; height: 50px;">
    </div>
    <div id="sort_resultDiv" class="sortResultDiv">
    </div>
    <div id="start"><h1>???????????????</h1></div>
    <div id="actives"><h1>??????????????????</h1></div>
    <div id="mana"><h1>????????????</h1></div>
    <div id="armor"><h1>????????????</h1></div>
    <div id="attackSpeed"><h1>??????????????????_?????????</h1></div>
    <div id="boots"><h1>????????????</h1></div>
    <div id="demege"><h1>????????????</h1></div>
    <div id="iteminfo"></div>
    <div id="effect"></div>
</div>
<div id="info">
</div>
<c:if test="${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.username eq 'admin'}">
    <a href="/item">????????????</a>
</c:if>
</body>
<script>

    $("#sortPopByAll").click(function (e) {
        $.get({
            url: '/sortItemBestByAll',
            success: function (data) {

                $("#popSort_itemList").empty();
                $("#popSort_searchDiv").empty();

                for (let i = 0; i < 12; i++) {
                    let imgNum = data.getElementsByTagName('item')[i].getElementsByTagName('ITEM')[0].innerHTML;
                    let imgName = imgNum + '.png';
                    $("#popSort_itemList").append("<img src=http://ddragon.leagueoflegends.com/cdn/11.2.1/img/item/" + imgName + " style='width:60px; height: 60px;' onmouseover='iteminfoinfo(" + imgNum + ")' onmouseout='iteminfo2()'>");
                }
            }
        })
    });

    $("#sortPopByPrice").click(function (e) {

        $("#popSort_searchDiv").empty();

        /*$("#popSort_resultDiv").empty();*/
        let input = "<input type='text' value='1' id='pop_minPrice'>~" +
            "<input type='text' value='1000' id='pop_maxPrice'>" +
            "<input type='button' value='??????' id='pop_searchBtn'><br>";
        $("#popSort_searchDiv").append(input);


        $("#pop_searchBtn").click(function (e) {

            const minPrice = $("#pop_minPrice").val();
            const maxPrice = $("#pop_maxPrice").val();

            $.get({
                url: '/sortItemBestByPrice?minPrice=' + minPrice + '&maxPrice=' + maxPrice,
                success: function (data) {

                    $("#popSort_itemList").empty();

                    for (let i = 0; i < 12; i++) {
                        let imgNum = data.getElementsByTagName('item')[i].getElementsByTagName('ITEM')[0].innerHTML;
                        let imgName = imgNum + '.png';
                        $("#popSort_itemList").append("<img src=http://ddragon.leagueoflegends.com/cdn/11.2.1/img/item/" + imgName + " style='width:60px; height: 60px;' onmouseover='iteminfoinfo(" + imgNum + ")' onmouseout='iteminfo2()'>");
                    }
                }
            })
        })
    });

    function sortItemBestByTag(tag) {
        $.get({
            url: '/sortItemBestByTag?tag=' + tag,
            success: function (data) {
                $("#popSort_itemList").empty();
                $("#popSort_searchDiv").empty();

                for (let i = 0; i < 12; i++) {
                    let imgNum = data.getElementsByTagName('item')[i].getElementsByTagName('ITEM')[0].innerHTML;
                    let imgName = imgNum + '.png';
                    $("#popSort_itemList").append("<img src=http://ddragon.leagueoflegends.com/cdn/11.2.1/img/item/" + imgName + " style='width:60px; height: 60px;' onmouseover='iteminfoinfo(" + imgNum + ")' onmouseout='iteminfo2()'>");
                }
            }
        })
    }

    $("#priceCheckbox").change(function (e) {

        if ($("#priceCheckbox").prop('checked')) {
            $("#minPrice").val("1");
            $("#maxPrice").val("4000");
            $("#minPrice").attr('disabled', false);
            $("#maxPrice").attr('disabled', false);
        } else {
            $("#minPrice").val("0");
            $("#maxPrice").val("0");
            $("#minPrice").attr('disabled', true);
            $("#maxPrice").attr('disabled', true);
        }
    });
    
    $("#sortBtn").click(function () {
        var minPrice = $("#minPrice").val();
        var maxPrice = $("#maxPrice").val();
        var tag = $("#tag").val();


        console.log(tag);

        $.get({
            url: '/sortItemBestByRating?minPrice=' + minPrice + '&maxPrice=' + maxPrice + '&tag=' + tag,
            dataType: 'json',
            success: function (data) {
                $("#sort_resultDiv").empty();
                for (let i=0; i<data.length; i++) {
                    let imgName = data[i][0] + '.png';
                    let tooltip = '[' + (parseInt(i)+1) + '???]' + data[i][1] + '%';
                    console.log(tooltip);
                    $("#sort_resultDiv").append("<img title=" + tooltip + " src=http://ddragon.leagueoflegends.com/cdn/11.2.1/img/item/" + imgName + " style='width:60px; height: 60px;' onmouseover='iteminfoinfo(" + data[i][0] + ")' onmouseout='iteminfo2()'>");
                }
            }
        })
    })
</script>
</html>