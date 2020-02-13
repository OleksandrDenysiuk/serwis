window.onload = function() {
    var rating = document.getElementById("rating").value;
    if(rating !== "0"){
        document.getElementById("star-" + rating).checked = true;
    }

};

function show() {
    var formEditComment = document.getElementById("form-edit-comment");
    formEditComment.style.display = 'block';
    document.getElementById("comment-content")
        .style.display = "none";
}

function rate(score) {
    var submitRating = document.getElementById('ratingsForm');
    var rate = document.getElementById("rating");
    rate.value = score.toString();
    submitRating.submit();
}
