window.onload = function() {
    var rating = document.getElementById("rating").value;
    //alert(rating);
    document.getElementById("star-" + rating).checked = true;
};

function show() {
    var formEditComment = document.getElementById("form-edit-comment");
    formEditComment.style.display = 'block';
    document.getElementById("comment-content")
        .style.display = "none";
}


var submitRating = document.getElementById('ratingsForm');
var rate = document.getElementById("rating");

function rateThisShit(score) {
    rate.value = score.toString();
    submitRating.submit();
}
