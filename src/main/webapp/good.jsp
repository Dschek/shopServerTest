<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; UTF-8"/>
    <meta name="keywords" content="magazine, site, buy"/>
    <meta name="description" content="Magazine"/>
    <link href="style.css" rel="stylesheet" type="text/css"/>
    <link href="favicon.ico" rel="shortcut icon" type="image/x-icon">
    <link href="cart.png">
    <title>Интернет магазин</title>
</head>
<body>
<script src="js/api/getGoods.js1"></script>
<header class="page-header zoo-header-4" id="page-header">
    <div style="background: darkorange;height: 150; margin: 0">
        <nav role="navigation">
            <ul>
                <a id="home" href="/"> <li id="home">
                    <img
                            src="https://raketaweb.ru/wp-content/themes/raketaweb/images/5bd05c447a923adabed9b2ee_logo-design20(2).png"
                            width="80px">
                </li>
                </a>
                <a href="/"><li><p>Главная</p>></li></a>>
                <li><a href="category?id=1">Игрушки</a>
                    <ul class="dropdown">
                        <li><a href="category?id=2">Куклы</a>
                            <ul class="dropdown">
                                <li><a href="category?id=3">Барби</a></li>
                            </ul>
                        </li>
                        <li><a href="category?id=4">Машинки</a>
                            <ul class="dropdown">
                                <li><a href="category?id=6">Пластиковые</a></li>
                                <li><a href="category?id=5">Металлические</a>
                                    <ul class="dropdown"></ul>
                            </ul>
                        </li>
                    </ul>
                </li>
                <div id="botton" position="right-side">
                    <li style="height: 40;">
                        <form id="content">
                            <input type="text" name="input" class="input" id="search-input">
                            <button type="reset" class="search" id="search-btn"></button>
                        </form>
                        <script>
                            (function () {
                                const input = document.getElementById("search-input");
                                const searchBtn = document.getElementById("search-btn");
                                const expand = () => {
                                    searchBtn.classList.toggle("close");
                                    input.classList.toggle("square");
                                };
                                searchBtn.addEventListener("click", expand);
                            })()
                        </script>
                        >
                    </li>
                    <li><a id="cart" href="#"><img src="cart.png" width="40px"
                                                   style="margin-left: 10; margin-right: 10;"></a></li>
                    <li><a id="user" href="#"><img src="user.png" width="40px" style="margin-right: 10;"></a></li>
                </div>
            </ul>
        </nav>
    </div>
    <div id="sticky-headers-sticky-wrapper" class="sticky-wrapper" style="height: 200px;">
    </div>
</header>
<br style="height: 40">
<main>

    <%
        out.println(request.getAttribute("good"));
    %>

</main>
</body>
</html>



