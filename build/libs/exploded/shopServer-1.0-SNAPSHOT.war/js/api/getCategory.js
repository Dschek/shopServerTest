// (function () {
//     category = {}
//     fetch("getCategory").then((response) => {
//         console.log("response received");
//         response.json().then((category) => {
//             if (json != null) {
//                 let str = "<p><a href=\"http://localhost:8081/getGoods?category=()\">Related&amp; Upsell</a></p>"
//                 menu = document.getElementById("category")
//                 for (let category of json) {
//                     parentId = category["parentId"]
//                     column = menu.getElementsByClassName("category-"+parentId)
//                     if(column == null)
//
//                 }
//             }
//         });
//     });
//     console.log("request sent");
// })();