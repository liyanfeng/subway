//PoupMenu
function showMenu(id, popupEl) {
    var el = document.getElementById(id);
    el.style.display = "block";
    if (popupEl) {
        var el = popupEl;
        var cls = el.className || "";
        cls = cls.replace(" showPopup", "");
        cls += " showPopup";
        el.className = cls;
    }
}
function hideMenu(id, popupEl) {
    var el = document.getElementById(id);
    el.style.display = "none";
    if (popupEl) {
        var el = popupEl;
        var cls = el.className || "";
        cls = cls.replace("showPopup", "");
        el.className = cls;
    }
}

//function removeIFrames() {
//    var iframes = document.getElementsByTagName("iframes");
//    for (var i = 0, l = iframes.length; i < l; i++) {
//        var iframe = iframes[i];
//        //if (iframe.src && ifram.src.indexOf("miniui.com") == -1) {
//            iframe.parentNode.removeChild(iframe);
//        //}
//    }
//}
//removeIFrames()
//window.onload = function () {
//    removeIFrames();
//}