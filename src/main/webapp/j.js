<script>
    (
    const goods = () => {
    const expand = (json) => {
    let goods = "";
    for (let good of json)
    goods += "<figure class=\"goodImage\"><a href=\"/good?id=" + good.id +
    "\" class=\"product-item-photo\"><img class=\"image-additional\" src=\"" + good.picture +
    "\"></a><figcaption><h3 class=\"goodPrice\">" + good.price +
    " Руб.</h3></figcaption></figure>"
    return goods
}
    const more = () => {
    content(query + "start=" + start + "&end=" + end)
    start += 4
    end += 4
}
    let pageMore = () => {
    const elementPage = document.createElement("div")
    const page = "<button type=\"reset\" id=\"moreGoods\" style=\"border-radius: 100%;background: greenyellow;\"> <h1>Еще</h1></button>"
    elementPage.innerHTML = page
    elementPage.onclick = more
    contentPage.insertAdjacentElement("afterend", elementPage)
}
    let searchGoods = () => {
    contentPage.removeChild();
    elementPage.hidden = false
}
    const contentPage = document.getElementById("contentPage")
    //contentPage.removeChild()
    let start = 0;
    let end = 4;
    let query = "getGoods?"
    more()
    pageMore()

}
    goods()
    )
</script>