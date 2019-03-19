$(function showInfo() {
    $.ajax({
        url: "/showInfo",
        async: true,
        type: "GET",
        dataType: "json",
        data: {"name": "吃猫鱼"},
        success: function (user) {
            $("#show_face_img").attr({"src": user.data.face});
        }
    })
})