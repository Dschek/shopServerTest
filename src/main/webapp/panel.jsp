<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; UTF-8"/>
    <meta name="keywords" content="magazine, site, buy"/>
    <meta name="description" content="Magazine"/>
    <link href="adminStyle.css" rel="stylesheet" type="text/css"/>
    <link href="favicon.ico" rel="shortcut icon" type="image/x-icon">
    <link href="cart.png">
    <title>Интернет магазин</title>
</head>
<body>

<div id="meny" style="display: table-row;">

    <div class="sidebar sidebar-main sidebar-default"
         style="-webkit-tap-highlight-color: rgba(0,0,0,0);  display: table-cell; font-family: -apple-system,BlinkMacSystemFont,"
         segoe="" ui
    ","roboto","oxygen","ubuntu","cantarell","fira="" sans","droid="" sans","helvetica="" neue",sans-serif;=""
    font-size:="" 13px;="" line-height:="" 1.4285715;="" box-sizing:="" border-box;="" position:="" relative;=""
    box-shadow:="" 0="" 1px="" 3px="" rgba(0,0,0,0.12),0="" 2px="" rgba(0,0,0,0.24);="" z-index:="" 5;="" display:=""
    table-cell;="" vertical-align:="" top;="" width:="" 260px;="" background-color:="" #fff;="" color:="" #000;"="">
    <div class="sidebar-content">

        <div class="sidebar-category sidebar-category-visible">
            <div class="category-content no-padding" style="
    background-color: #fff;
    display: table-caption;
">
                <ul class="navigation navigation-main navigation-accordion" style="
    background: cornflowerblue;
    display: table-cell;
">
                    <li><a href="/" class="legitRipple"><i class="fa fa-globe"></i> <span>Сайт</span></a></li>
                    <li><a href="#" class="legitRipple"><i class="fa fa-globe"></i> <span>Профиль</span></a></li>
                    <li><a href="#addgood" class="legitRipple"><i class="fa fa-globe"></i>
                        <span>Добавить товар</span></a></li>
                    <li><a href="#logout" class="legitRipple"><i class="fa fa-globe"></i> <span>Выход</span></a></li>
                </ul>
            </div>
        </div>

    </div>
</div>


<div id="content"></div>

<script>




    let query = "";
    const get = () => {
        fetch(query).then((response) => {
                response.text().then((text) => {
                    console.log(text)
                    content.innerHTML = text;
                    const adminElement = document.getElementById("loginform"),
                        profileElement = document.getElementById("profile"),
                        contentElement = document.getElementById("addContent")
                    if (adminElement != null)
                        adminElement.onsubmit = admin;
                    else if ( profileElement != null)
                        profileElement.onsubmit = profile;
                    else if (contentElement != null)
                        contentElement.onsubmit = addContent;
                });
            }
        )
    }
    const post = () => {
        fetch(query, {
            method: "post",
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(
                postBody
            )
        }).then(()=>{
            main()
        })
    }
    addContent = (e) => {
        const params = document.getElementById("content").getElementsByClassName("input")
        if (params.length == 2 && params[0].value != "" && params[1].value != ""&& params[3].value != "") {
            postBody = {
                "name": params[0].value,
                "surName": params[1].value,
                "lastName": params[2].value,
                "email": params[3].value,
                "password": params[4].value
            }
            post()
        }
        return false;
    }
    profile = (e) => {
        const params = document.getElementById("content").getElementsByClassName("input")
        if (params.length == 2 && params[0].value != "" && params[1].value != ""&& params[3].value != "") {
            postBody = {
                "name": params[0].value,
                "surName": params[1].value,
                "lastName": params[2].value,
                "email": params[3].value,
                "password": params[4].value
            }
            post()
        }
        return false;
    }
    admin = (e) => {
        const params = document.getElementById("content").getElementsByClassName("input")
        if (params.length == 2 && params[0].value != "" && params[1].value != "") {
            postBody = {
                "login": params[0].value,
                "password": params[1].value
            }
            post()
        }
        return false;
    }
    const content = document.getElementById("content")
    const main = () => {
        switch (window.location.hash) {
            case ("#addGood"):
                query = "addgood"
                get()
                break;
            case ("#logOut"):
                query = "logout"
                get();
                if (content == "")
                    window.location.hash = ""
                break;
            default:
                query = "admin"
                get()
            //document.getElementById("loginform").onsubmit = admin
        }
    }
    let postBody = null;
    window.onhashchange = main;
    main()
</script>


</body>
</html>