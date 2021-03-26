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
                                    if (value.price <= 500 && value.name != '전령의 눈' || value.tags == 'SpellVamp' || value.tags == 'Jungle') {
                                        $(str2).appendTo('#start').addClass("iteminfo");
                                    } else if (value.tags == 'Boots' || value.tags == 'MagicPenetration' || value.name == "헤르메스의 발걸음") {
                                        $(str2).appendTo('#boots').addClass("iteminfo");
                                    } else if (value.tags == 'Active' || value.tags == 'Consumable' || value.tags == 'Trinket') {
                                        $(str2).appendTo('#actives').addClass("iteminfo");
                                    } else if (value.tags == 'SpellDamage' || value.name == '리치베인' || value.tags == 'ManaRegen') {
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

        function iteminfoinfo(inum) {
            console.log(event.clientX);
            var x = event.clientX;
            var y = event.clientY;
            $.ajax({
                contentType: 'application/json',
                url: "/iteminfo?inum=" + inum,
                dataType: 'json',
                success: function (data) {


                    /*$("#iteminfo").text((data.effect));*/
                    /*$(data.effect).appendTo("#iteminfo");*/
                    var nameeffect = "<div><h2>" + data.name + "</h2><p>가격:" + data.price + "<br><img src=http://ddragon.leagueoflegends.com/cdn/11.2.1/img/item/" + data.icon + " style='width:64px;'><br>" + data.effect + "</div>";
                    $("#effect").empty();
                    $(nameeffect).appendTo("#info").addClass('content');

                    //ddd
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
    <h1>인기 아이템</h1>
    <div class="btn-group" role="group" aria-label="Button group with nested dropdown">
        <button type="button" id="sortPopByAll" class="btn btn-secondary">전체</button>
        <button type="button" id="sortPopByPrice" class="btn btn-secondary">가격별</button>

        <div class="btn-group" role="group">
            <button id="btnGroupDrop1" type="button" class="btn btn-secondary dropdown-toggle" data-toggle="dropdown"
                    aria-haspopup="true" aria-expanded="false">
                태그별
            </button>
            <div class="dropdown-menu" aria-labelledby="btnGroupDrop1">
				<a class="dropdown-item" href="#" onclick="sortItemBestByTag('Active')">특수효과</a>
				<a class="dropdown-item" href="#" onclick="sortItemBestByTag('SpellDamage')">마법공격</a>
				<a class="dropdown-item" href="#" onclick="sortItemBestByTag('Damage')">물리공격</a>
				<a class="dropdown-item" href="#" onclick="sortItemBestByTag('AttackSpeed')">공속/치명</a>
				<a class="dropdown-item" href="#" onclick="sortItemBestByTag('Armor')">방어특화</a>
				<a class="dropdown-item" href="#" onclick="sortItemBestByTag('Boots')">이동속도</a>
            </div>
        </div>
    </div>
    <div id="popSort_resultDiv" class="sortResultDiv">
		<div id="popSort_searchDiv"></div>
		<div id="popSort_itemList"></div>
    </div>
    <br>

    <h1>고승률 아이템</h1>
    <div class="btn-group" role="group" aria-label="Button group with nested dropdown">
        <button type="button" class="btn btn-secondary">전체</button>
        <button type="button" class="btn btn-secondary">가격별</button>

        <div class="btn-group" role="group">
            <button id="btnGroupDrop2" type="button" class="btn btn-secondary dropdown-toggle" data-toggle="dropdown"
                    aria-haspopup="true" aria-expanded="false">
                태그별
            </button>
            <div class="dropdown-menu" aria-labelledby="btnGroupDrop1">
				<a class="dropdown-item" href="#">특수효과</a>
				<a class="dropdown-item" href="#">마법공격</a>
				<a class="dropdown-item" href="#">물리공격</a>
				<a class="dropdown-item" href="#">공속/치명</a>
				<a class="dropdown-item" href="#">방어특화</a>
				<a class="dropdown-item" href="#">이동속도</a>
            </div>
        </div>
    </div>
    <br>

    <div id="sort_resultDiv" class="sortResultDiv">
        안녕하세요 이희권입니다
    </div>
    <div id="start"><h1>스타팅추천</h1></div>
    <div id="actives"><h1>특수사용효과</h1></div>
    <div id="mana"><h1>마법공격</h1></div>
    <div id="armor"><h1>방어특화</h1></div>
    <div id="attackSpeed"><h1>공격속도특화_치명타</h1></div>
    <div id="boots"><h1>이동속도</h1></div>
    <div id="demege"><h1>물리공격</h1></div>
    <div id="iteminfo"></div>
    <div id="effect"></div>
</div>
<div id="info">
</div>
<c:if test="${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.username eq 'admin'}">
    <a href="/item">업데이트</a>
</c:if>
</body>
<script>

	$("#sortPopByAll").click(function (e) {
		$.get({
			url: '/sortItemBestByAll',
			success: function (data) {

				$("#popSort_itemList").empty();
				$("#popSort_searchDiv").empty();

				for (let i=0; i<12; i++) {
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
            "<input type='button' value='검색' id='pop_searchBtn'><br>";
        $("#popSort_searchDiv").append(input);


		$("#pop_searchBtn").click(function (e) {

			const minPrice = $("#pop_minPrice").val();
			const maxPrice = $("#pop_maxPrice").val();

			$.get({
				url: '/sortItemBestByPrice?minPrice=' + minPrice + '&maxPrice=' + maxPrice,
				success: function (data) {

					$("#popSort_itemList").empty();

					for (let i=0; i<12; i++) {
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
                console.log();
				$("#popSort_itemList").empty();
				$("#popSort_searchDiv").empty();

				for (let i=0; i<12; i++) {
					let imgNum = data.getElementsByTagName('item')[i].getElementsByTagName('ITEM')[0].innerHTML;
					let imgName = imgNum + '.png';
					$("#popSort_itemList").append("<img src=http://ddragon.leagueoflegends.com/cdn/11.2.1/img/item/" + imgName + " style='width:60px; height: 60px;' onmouseover='iteminfoinfo(" + imgNum + ")' onmouseout='iteminfo2()'>");
				}
			}
		})
	}
</script>
</html>