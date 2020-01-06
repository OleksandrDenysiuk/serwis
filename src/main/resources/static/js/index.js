$(document).ready(function () {

    $('#AddToTripModal').on('show.bs.modal', function (event) {
        var button = $(event.relatedTarget); // Button that triggered the modal
        var recipient = button.data('whatever'); // Extract info from data-* attributes
        // If necessary, you could initiate an AJAX request here (and then do the updating in a callback).
        // Update the modal's content. We'll use jQuery here, but you could use a data binding library or other methods instead.
        console.log(recipient);
        var modal = $(this);
        modal.find('.modal-content form').attr('action', '/trip/' + recipient + '/add');
    })
    $('#NewMessageForm').on('show.bs.modal', function (event) {
        var button = $(event.relatedTarget); // Button that triggered the modal
        var recipient = button.data('whatever'); // Extract info from data-* attributes
        // If necessary, you could initiate an AJAX request here (and then do the updating in a callback).
        // Update the modal's content. We'll use jQuery here, but you could use a data binding library or other methods instead.
        console.log(recipient);
        var modal = $(this);
        modal.find('.modal-content form').attr('action', '/message/send/' + recipient);
    })


});

$( function() {
    $( "#slider-range" ).slider({
        range: true,
        min: 0,
        max: 50,
        place: [ 0, 20 ],
        slide: function( event, ui ) {
            $( "#amount" ).val( ui.values[ 0 ] + " - " + ui.values[ 1 ] );
        }
    });
    $( "#amount" ).val($( "#slider-range" ).slider( "values", 0 ) +
        " - " + $( "#slider-range" ).slider( "values", 1 ) );
} );

function show() {
    var formEditComment = document.getElementById("form-edit-comment");
    formEditComment.style.display = 'block';
    document.getElementById("comment-content")
        .style.display = "none";
}

