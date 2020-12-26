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
    <div style="background: darkorange;height: 210; margin: 0">
        <nav role="navigation">
            <ul>
                <a href="#">
                    <li id="" class="meny"><p>Главная</p></li>
                </a>>
                <a href="#getGoods?categoryId=1&start=0&end=4">
                    <li><p id="categoryId=1">Игрушки</p>
                        <ul class="dropdown">
                            <a href="#getGoods?categoryId=2&start=0&end=4">
                                <li><p id="categoryId=2">Куклы</p>
                                    <ul class="dropdown">
                                        <a href="#getGoods?categoryId=3&start=0&end=4">
                                            <li id="categoryId=3" href="#categoryId=3" class="meny"><p>Барби</p></li>
                                        </a>
                                    </ul>
                                </li>
                            </a>
                            <a href="#getGoods?categoryId=4&start=0&end=4">
                                <li><p id="id=4">Машинки</p>
                                    <ul class="dropdown">
                                        <a href="#getGoods?categoryId=6&start=0&end=4">
                                            <li id="categoryId=6" class="meny"><p>Пластиковые</p></li>
                                        </a>
                                        <a href="#getGoods?categoryId=5&start=0&end=4">
                                            <li id="categoryId=5" class="meny"><p>Металлические</p>
                                                <ul class="dropdown"></ul>
                                            </li>
                                        </a>
                                    </ul>
                                </li>
                            </a>>
                        </ul>
                    </li>
                </a>
                <div id="botton" position="right-side">
                    <li style="height: 40;margin: 34;">
                        <form id="content">
                            <input formaction="" type="text" class="input" id="search-input">
                            <button type="reset" class="search" id="search-btn"></button>
                        </form>
                    </li>
                    <li style="margin: 34;"><a id="cart" href="cart"><img src="cart.png" width="40px"
                                                                          style="margin-left: 10; margin-right: 10;"></a>
                    </li>
                    <li style="margin: 34;"><a id="user" href="/panel"><img src="user.png" width="40px"
                                                                            style="margin-right: 10;"></a></li>
                </div>
            </ul>
        </nav>
    </div>
    <div id="sticky-headers-sticky-wrapper" class="sticky-wrapper" style="height: 200px;">
    </div>
</header>
<br style="height: 40">
<main style="text-align: center;">
    <button type="reset" id="lessGoods" style="border-radius: 100%;background: greenyellow;"><h1>Назад</h1></button>
    <div id="contentPage"></div>
    <button type="reset" id="moreGoods" style="border-radius: 100%;background: greenyellow;"><h1>Дальше</h1></button>
    <script>
        (function () {
                const searchOpen = () => {
                    searchBtn.classList.toggle("close");
                    input.classList.toggle("square");
                };
                //Goods
                let good1 = {
                    id: 111,
                    picture: "aaa",
                    price: "123"
                }
                //

                const cart = () => {
                    let goods = document.createElement("div");
                    fetch(query).then((response) => {
                        response.json().then((json) => {
                            if (json.result == undefined || json.result == null)
                                window.location = "panel";
                            let result = '<div><form>'
                            for (let good of json) {
                                result +='<p style="display: inline-flex;"><input type="checkbox" value="'
                                    +good[0].id
                                + '" style="margin: auto;display: flex;align-items: center;"><div style="margin-left: 100px;margin-right: 100px;"><a href="#good?id='
                                + good[0].id
                                + '"><img class="image-additional"src="'
                                + good[0].picture
                                + '"></a><h3 class="goodPrice">'
                                + good[0].price
                                + ' Руб.</h3></div><h1 style="    margin: auto; display: flex; align-items: center;">'
                                + good[1].count
                                + 'ШТ</h1></p>'
                            }
                            result+='</form></div>'
                            elementPageLess.hidden = true
                            elementPageMore.hidden = true
                            removeChild()
                            elementGood.innerHTML = result
                        })
                    })
                }

                const getGoods = () => {
                    let goods = document.createElement("div");
                    fetch(query).then((response) => {
                            response.json().then((json) => {
                                    const size = json.length
                                    if (size === 0) {
                                        console.log("null")
                                        elementPageMore.hidden = true
                                        if (start <= 0) {
                                            console.log("start <= 0")
                                            elementPageLess.hidden = true
                                            elementGoods.innerText = "Error"
                                            return
                                        }
                                        window.location = "#getGoods?categoryId=" + categoryId + "&start=" + (start - sizeCont) + "&end=" + start;
                                        return;
                                    } else if (size < sizeCont && start > 0 && (end - start) > size) {
                                        window.location = "#getGoods?categoryId=" + categoryId + "&start=" + start + "&end=" + (start + size);
                                        elementPageMore.hidden = true
                                        return;
                                    }
                                    for (let good of json) {
                                        let element = document.createElement("figure")
                                        element.id = "id=" + good.id
                                        element.innerHTML = "<a href=\"#" + "good?id=" + good.id + "\">" +
                                            "<img class=\"image-additional\" src=\"" + good.picture +
                                            "\"><p><h3 class=\"goodPrice\">" + good.price +
                                            " Руб.</h3></p></a>"
                                        goods.insertAdjacentElement("beforeend", element)
                                    }
                                    if (size === sizeCont)
                                        elementPageMore.hidden = false
                                    removeChild()
                                    elementGoods.insertAdjacentElement("afterbegin", goods)
                                    if (start <= 0)
                                        elementPageLess.hidden = true
                                    else
                                        elementPageLess.hidden = false
                                }
                            ).catch(error => {
                                elementPageMore.hidden = true
                            });
                        }
                    )
                }
                const removeChild = () => {
                    let child
                    console.log("remove")
                    while ((child = elementGoods.firstChild) != null) {
                        console.log("removeGoods")
                        elementGoods.removeChild(child)
                    }
                    while ((child = elementGood.firstElementChild) != null) {
                        console.log("removeGood")
                        elementGood.removeChild(child)
                    }
                }
                //Good
                const getGood = () => {
                    fetch(query).then((response) => {
                            response.json().then((json) => {
                                elementPageLess.hidden = true
                                elementPageMore.hidden = true
                                removeChild()
                                elementGood.innerHTML = "<div class=\"goodImage\"><img class=\"image-additional\" src=\"" + json.picture
                                    + "\"><h3 class=\"goodPrice\">" + json.title + "</h3><p></p><br><p>" + json.price
                                    + "</p></div>"
                            });
                        }
                    )
                }
                //SearchBotton
                // const search = () => {
                //     console.log(input.textContent)
                // }

                const onFormSubmit = (e) => {
                    search = input.value
                    query = "#getGoods?start=0&end=4&search=" + search
                    window.location.hash = query
                    return false;
                }


                const form = document.getElementById("content");
                form.onsubmit = onFormSubmit;
                const input = document.getElementById("search-input");
                const searchBtn = document.getElementById("search-btn");
                searchBtn.addEventListener("click", searchOpen);
                //Goods
                const main = () => {
                    query = window.location.hash.replace(/^#/, "");
                    if (query != "") {
                        let params = query.split(/[&]|[?]|[=]/)
                        for (let i = 1, size = params.length; i < size; i++) {
                            if (!/^\d+$/.test(params[i + 1]))
                                continue
                            switch (params[i]) {
                                case "categoryId":
                                    categoryId = +params[++i]
                                    break
                                case "start":
                                    start = +params[++i]
                                    break
                                case "end":
                                    end = +params[++i]
                                    break
                                case "id":
                                    id = +params[++i]
                            }
                        }
                        console.log(start)
                        if (start < 0) {
                            query.replace(/start=-(\d)+/, "start=0")
                            window.location.hash = "#" + query;
                            return;
                        }
                        if (params[0] == "getGoods") {
                            getGoods()
                        } else if (params[0] == "good")
                            getGood()
                        else if (params[0] == "cart") {
                            cart()
                        } else {
                            query = "getGoods?&start=0&end=4"
                            getGoods()
                        }
                    } else {
                        query = "getGoods?start=0&end=4"
                        getGoods()
                    }
                    console.log(query)
                }
                //// Elements page
                const contentPage = document.getElementById("contentPage")
                const elementGoods = document.createElement("div")
                elementGoods.style = "-webkit-column-width: 200px; -moz-column-width: 200px;column-width: 200px;-webkit-column-count: 2;-moz-column-count: 2;column-count: 2;-webkit-column-gap: 30px;-moz-column-gap: 30px;column-gap: 30px;-webkit-column-rule: 1px solid #ccc;-moz-column-rule: 1px solid #ccc;column-rule: 1px solid #ccc;"
                contentPage.insertAdjacentElement("afterbegin", elementGoods)
                const elementGood = document.createElement("div")
                contentPage.insertAdjacentElement("afterbegin", elementGood)
                let start = 0,
                    sizeCont = 4,
                    end = 0,
                    categoryId = 0,
                    id,
                    query,
                    search,
                    elementPageLess = document.getElementById("lessGoods"),
                    elementPageMore = document.getElementById("moreGoods");
                elementPageMore.onclick = () => {
                    query = "getGoods?categoryId=" + categoryId + "&start=" + (start + sizeCont) + "&end=" + (start + sizeCont * 2);
                    window.location = "#" + query;
                }
                elementPageLess.onclick = () => {
                    if (start < sizeCont)
                        start = sizeCont
                    query = "getGoods?categoryId=" + categoryId + "&start=" + (start - sizeCont) + "&end=" + start;
                    window.location = "#" + query
                }

                ////
                main()
                window.onhashchange = main;
            }
        )
        ()
    </script>
</main>
</body>
</html>



