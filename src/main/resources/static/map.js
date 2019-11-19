var geocoder;
var placeJson;
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
        map: map
    });

    var addressContener = document.getElementById("address");

    map.addListener('click', function (e) {
        placeMarkerAndPanTo(e.latLng, map, icon, marker);
        addAddress(function (address) {
            addressContener.innerHTML = address;
            pushJson(e.latLng, address);
        }, e.latLng);
        currentLatLng = e.latLng;

    });

    var controlMarkers = document.getElementById("controlMarkers");

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

        markers.push({coords: currentLatLng, iconImage: icon});
        console.log(markers);

        for (var i = 0; i < markers.length; i++) {
            // Add markers
            addMarker(markers[i]);
        }
        sendOnePLaceData();
        document.getElementById("form").submit();
    });

    document.getElementById('files').addEventListener('change', handleFileSelect, false);

    map.controls[google.maps.ControlPosition.LEFT_TOP].push(controlMarkers);

}

function placeMarkerAndPanTo(latLng, map, icon, marker) {
    marker.setPosition(latLng);
    marker.setIcon(icon);
    map.panTo(latLng);
}

function sendOnePLaceData() {
    var xhr = new XMLHttpRequest();
    var url = "http://localhost:8080/place";
    xhr.open("POST", url, false);
    xhr.setRequestHeader("Content-Type", "application/json");
    var data = JSON.stringify(placeJson);
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
    placeJson = {
        latitude: latLng.lat().toString(),
        longitude: latLng.lng().toString(),
        address: address
    }
}

function addMarker(props) {
    var marker = new google.maps.Marker({
        position: props.coords,
        map: map,
        icon: props.iconImage
    });
}

function handleFileSelect(evt) {
    console.log(document.getElementById("files").files[0]);
    var files = evt.target.files; // FileList object

    // Loop through the FileList and render image files as thumbnails.
    for (var i = 0, f; f = files[i]; i++) {

        // Only process image files.
        if (!f.type.match('image.*')) {
            continue;
        }

        var reader = new FileReader();

        // Closure to capture the file information.
        reader.onload = (function(theFile) {
            return function(e) {
                // Render thumbnail.
                var span = document.createElement('span');
                span.innerHTML = ['<img class="thumb" src="', e.target.result,
                    '" title="', escape(theFile.name), '"/>'].join('');
                document.getElementById('list').insertBefore(span, null);

            };
        })(f);

        // Read in the image file as a data URL.
        reader.readAsDataURL(f);
    }

}



