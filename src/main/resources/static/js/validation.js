function validNewTripForm() {

    var x = document.getElementById("nameTrip").value;

    if (  x.length > 25) {
        alert(x);
        var error = document.createElement('div');
        error.className = "invalid-feedback";
        error.innerHTML = "Name is too long! Max. 25 symbols";
        document.getElementById("nameTrip").className = "form-control is-invalid";
        document.getElementById("form-trip").appendChild(error);

    }else{
        document.getElementById("newTripForm").submit();
    }

}

