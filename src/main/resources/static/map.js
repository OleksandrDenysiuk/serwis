var geocoder;
var placesJson = [];
var id = 0;
var currentLatLng;
var markers = [];
var map;
var icon;

function initMap() {
    var icon_food = {
        url: 'img/food.png', // url
        scaledSize: new google.maps.Size(50, 50), // scaled size
    };
    var icon_place = {
        url: 'img/place.png', // url
        scaledSize: new google.maps.Size(50, 50), // scaled size
    };
    var icon_sleep = {
        url: 'img/sleep.png', // url
        scaledSize: new google.maps.Size(50, 50), // scaled size
    };

    map = new google.maps.Map(document.getElementById('map'), {
        zoom: 14,
        center: {lat: 51.25052702419269, lng: 22.57244110107422},
        mapTypeControl: false,
        fullscreenControl: true,
        fullscreenControlOptions: {
            position: google.maps.ControlPosition.BOTTOM_RIGHT
        }
    });

    var marker = new google.maps.Marker({
        map: map,
        title: 'test'
    });

    var addressContener = document.getElementById("address");

    map.addListener('click', function (e) {
        placeMarkerAndPanTo(e.latLng, map, icon, marker);
        addAddress(function (address) {
            addressContener.innerHTML = address;
        }, e.latLng);
        currentLatLng = e.latLng;
    });

    var controlPlaces = document.getElementById("controlPlaces");
    var controlMarkers = document.getElementById("controlMarkers");

    var btnSendDataOfPlaces = document.getElementById("btnSendDataOfPlaces");
    btnSendDataOfPlaces.addEventListener('click', event => {
        sendData();
    });

    var btnAddPlace = document.getElementById("btnAddPlace")
        .addEventListener('click', event => {
            controlMarkers.style.display = "inline";
        });

    var imgContener1 = document.getElementById("place");
    var imgContener2 = document.getElementById("food");
    var imgContener3 = document.getElementById("sleep");
    imgContener1.addEventListener('click', event => {
        imgContener1.style.backgroundColor = "blue";
        imgContener2.style.backgroundColor = "white";
        imgContener3.style.backgroundColor = "white";
        icon = icon_place;
    });

    imgContener2.addEventListener('click', event => {
        imgContener1.style.backgroundColor = "white";
        imgContener2.style.backgroundColor = "blue";
        imgContener3.style.backgroundColor = "white";
        icon = icon_food;
    });

    imgContener3.addEventListener('click', event => {
        imgContener1.style.backgroundColor = "white";
        imgContener2.style.backgroundColor = "white";
        imgContener3.style.backgroundColor = "blue";
        icon = icon_sleep;
    });

    var btnAdd = document.getElementById("btnAdd").addEventListener('click', event => {

        var placeContenerP = document.createElement('P');
        placeContenerP.className = "address";

        addAddress(function (address) {
            placeContenerP.innerHTML = address;
            pushJson(currentLatLng, address);
        }, currentLatLng);

        var placeContenerDiv = document.createElement('DIV');
        placeContenerDiv.className = "addressContainer";

        placeContenerDiv.appendChild(placeContenerP);
        controlPlaces.appendChild(placeContenerDiv);

        controlMarkers.style.display = "none";
        btnSendDataOfPlaces.style.display = "inline";
        controlPlaces.appendChild(btnSendDataOfPlaces);

        markers.push({coords: currentLatLng, iconImage: icon});
        console.log(markers);

        for (var i = 0; i < markers.length; i++) {
            // Add markers
            addMarker(markers[i]);
        }
    });

    const realFileBtn = document.getElementById("chooseFile");
    const customBtn = document.getElementById("btnChooseFile");

    customBtn.addEventListener("click", function () {
        realFileBtn.click();
    });

    realFileBtn.addEventListener("change", function () {
        console.log(realFileBtn.value);
        var imageContainer = document.getElementById("icon");
        imageContainer.style.background = "none";
        var image = document.createElement("img");
        image.className = "img";
        image.src = realFileBtn.value;
        imageContainer.appendChild(image);
    });


    map.controls[google.maps.ControlPosition.LEFT_TOP].push(controlMarkers);
    map.controls[google.maps.ControlPosition.RIGHT_TOP].push(controlPlaces);
}

function placeMarkerAndPanTo(latLng, map, icon, marker) {
    marker.setPosition(latLng);
    marker.setIcon(icon);
    map.panTo(latLng);
}

function sendData() {
    var xhr = new XMLHttpRequest();
    var url = "http://localhost:8080/";
    xhr.open("POST", url, true);
    xhr.setRequestHeader("Content-Type", "application/json");
    var data = JSON.stringify(placesJson);
    xhr.send(data);
}

function addAddress(callback, latLng) {
    geocoder = new google.maps.Geocoder;
    geocoder.geocode({
        'location': {
            lat: latLng.lat(),
            lng: latLng.lng()
        }
    }, function (results, status) {
        callback(results[1].formatted_address);
    });
}

function pushJson(latLng, address) {
    placesJson.push(
        {
            id: id++,
            lat: latLng.lat(),
            lng: latLng.lng(),
            address: address
        });
}

function addMarker(props) {
    var marker = new google.maps.Marker({
        position: props.coords,
        map: map,
        icon: props.iconImage
    });
}
