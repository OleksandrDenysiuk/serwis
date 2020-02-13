$(document).ready(function () {
    $(".content").each(function () {
        $(this).css('display', 'none');
    });
    $("#profile-content").css('display', 'block');

});

$("#messages-link").click(function () {
    $(".content").each(function () {
        $(this).css('display', 'none');
    });
    $("#messages-content").css('display', 'block');
});

$("#profile-link").click(function () {
    $(".content").each(function () {
        $(this).css('display', 'none');
    });
    $("#profile-content").css('display', 'block');
});


$("#users-link").click(function () {
    $(".content").each(function () {
        $(this).css('display', 'none');
    });
    $("#users-content").css('display', 'block');
});

$("#places-link").click(function () {
    $(".content").each(function () {
        $(this).css('display', 'none');
    });
    $("#places-content").css('display', 'block');
});