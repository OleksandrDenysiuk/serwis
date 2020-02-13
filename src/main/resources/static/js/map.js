var geocoder;
var map;
var icon;

function initMap() {
    map = new google.maps.Map(document.getElementById('map'), {
        zoom: 14,
        center: {lat: 51.25052702419269, lng: 22.57244110107422},
        mapTypeControl: false
    });
    var marker = new google.maps.Marker({
        map: map
    });

    initDataOnMap(map, marker);

    var type = document.getElementById("type");

    var icon_food = {
        url: 'https://firebasestorage.googleapis.com/v0/b/travelinfo-1cc54.appspot.com/o/bar.png?alt=media&token=1a2506f3-7e66-4fdb-b282-9c3eb1c1eff1', // url
        scaledSize: new google.maps.Size(50, 50), // scaled size
    };
    var icon_place = {
        url: 'https://firebasestorage.googleapis.com/v0/b/travelinfo-1cc54.appspot.com/o/building.png?alt=media&token=b33b0163-8293-4492-be83-d0120c4f49f9', // url
        scaledSize: new google.maps.Size(50, 50), // scaled size
    };
    var icon_sleep = {
        url: 'https://firebasestorage.googleapis.com/v0/b/travelinfo-1cc54.appspot.com/o/hotel.png?alt=media&token=53a39efd-ec02-445d-8c8f-f2c16cc21d52', // url
        scaledSize: new google.maps.Size(50, 50), // scaled size
    };

    map.addListener('click', function (e) {
        if (icon == undefined) {
            alert("Please choose type of place!")
        } else {
            placeMarkerAndPanTo(e.latLng, map, icon, marker);
            addAddress(function (address) {
                addressContener.innerHTML = address.city + " , " + address.country;
                document.getElementById("address").value = address.city + " , " + address.country;
            }, e.latLng);
            document.getElementById("latitude").value = e.latLng.lat().toString();
            document.getElementById("longitude").value = e.latLng.lng().toString();
        }
    });


    var addressContener = document.getElementById("addressContainer");

    if (getDataPlace() == null) {
        var leftBar = document.getElementById("leftBar");
    } else {
        var leftBar = document.getElementById("leftBarEdit");
    }


    var imgContener1 = document.getElementById("place");
    imgContener1.addEventListener('click', event => {
        imgContener1.style.backgroundColor = "#e0e3e7";
        imgContener2.style.backgroundColor = "white";
        imgContener3.style.backgroundColor = "white";
        icon = icon_place;
        type.value = "place";
    });

    var imgContener2 = document.getElementById("food");
    imgContener2.addEventListener('click', event => {
        imgContener1.style.backgroundColor = "white";
        imgContener2.style.backgroundColor = "#e0e3e7";
        imgContener3.style.backgroundColor = "white";
        icon = icon_food;
        type.value = "food";
    });

    var imgContener3 = document.getElementById("sleep");
    imgContener3.addEventListener('click', event => {
        imgContener1.style.backgroundColor = "white";
        imgContener2.style.backgroundColor = "white";
        imgContener3.style.backgroundColor = "#e0e3e7";
        icon = icon_sleep;
        type.value = "sleep";
    });

    var myLatLng = {
        lat: Number(document.getElementById("latitude").value),
        lng: Number(document.getElementById("longitude").value)
    };

    if (type.value == "place") {
        imgContener1.click();
        placeMarkerAndPanTo(myLatLng, map, icon, marker);
        addressContener.innerHTML = document.getElementById("address").value;
    } else if (type.value == "food") {
        imgContener2.click();
        placeMarkerAndPanTo(myLatLng, map, icon, marker);
        addressContener.innerHTML = document.getElementById("address").value;
    } else if (type.value == "sleep") {
        imgContener3.click();
        placeMarkerAndPanTo(myLatLng, map, icon, marker);
        addressContener.innerHTML = document.getElementById("address").value;
    }

    var btnAdd = document.getElementById("btnAdd").addEventListener('click', event => {
        console.log($("#address").value);
        var $fileUpload = $("input[type='file']");
        document.getElementById("tags").value = tagsTest;
        document.getElementById("form").submit();
    });

    document.getElementById('files').addEventListener('change', handleFileSelect, false);

    var input = document.getElementById("button-addon3");

    var tagsTest = [];
    var data = getDataPlace();
    if (data != null) {
        tagsTest = data.tags;
    }

    document.getElementById("inputTags")
        .addEventListener("keyup", function (event) {
            var value = document.getElementById("inputTags").value;
            if (event.keyCode === 13 && value !== "") {
                var newTag = document.createElement("span");
                newTag.className = "input-group-text";
                newTag.innerHTML = value;
                input.appendChild(newTag);
                document.getElementById("inputTags").value = "";
                tagsTest.push(value);
            }
        });
    document.getElementById("inputTags")
        .addEventListener("keydown", function (event) {
            var tagsContainer = document.getElementById("button-addon3");
            var value = document.getElementById("inputTags").value;
            if (event.keyCode === 8 && value === "") {
                tagsContainer.removeChild(tagsContainer.lastChild);
                tagsTest.pop();
            }
        });

    map.controls[google.maps.ControlPosition.LEFT_TOP].push(leftBar);

    map.controls[google.maps.ControlPosition.TOP_CENTER].push(
        document.getElementById("btnsReturn")
    );

}

function placeMarkerAndPanTo(latLng, map, icon, marker) {
    marker.setPosition(latLng);
    marker.setIcon(icon);
    map.panTo(latLng);
}

function addAddress(callback, latLng) {
    geocoder = new google.maps.Geocoder;
    geocoder.geocode({
        'location': {
            lat: latLng.lat(),
            lng: latLng.lng()
        }
    }, function (results, status) {
        let storableLocation = {};
        for (var ac = 0; ac < results[0].address_components.length; ac++) {

            var component = results[0].address_components[ac];

            if (component.types.includes('sublocality') || component.types.includes('locality')) {
                storableLocation.city = component.long_name;
            } else if (component.types.includes('administrative_area_level_1')) {
                storableLocation.state = component.short_name;
            } else if (component.types.includes('country')) {
                storableLocation.country = component.long_name;
                storableLocation.registered_country_iso_code = component.short_name;
            }
        }
        callback(storableLocation);
    });
}

function handleFileSelect(evt) {
    if (document.contains(document.getElementById("list"))) {
        document.getElementById("list").remove();
    }

    var containerImages = document.createElement('output');
    containerImages.id = 'list';

    if (getDataPlace() == null) {
        document.getElementById("leftBar").insertBefore(containerImages, document.getElementById("form"));
    } else {
        document.getElementById("leftBarEdit").insertBefore(containerImages, document.getElementById("form"));
    }


    var files = evt.target.files; // FileList object

    if (files.length > 9) {
        var div = document.createElement('div');
        div.innerHTML = "<div class=\"alert alert-danger\" role=\"alert\">\n" +
            "  Max 9 images. Try again!\n" +
            "</div>"
        document.getElementById('list').insertBefore(div, null);
    } else {
        // Loop through the FileList and render image files as thumbnails.
        for (var i = 0, f; f = files[i]; i++) {

            // Only process image files.
            if (!f.type.match('image.*')) {
                continue;
            }

            var reader = new FileReader();

            // Closure to capture the file information.
            reader.onload = (function (theFile) {
                return function (e) {
                    // Render thumbnail.
                    var div = document.createElement('div');
                    div.className = "block btn-group";
                    div.innerHTML = ['<img class="thumb" src="', e.target.result,
                        '" title="', escape(theFile.name), '"/>'].join('');
                    document.getElementById('list').insertBefore(div, null);
                };
            })(f);

            // Read in the image file as a data URL.
            reader.readAsDataURL(f);
        }
    }
}

function getIconByType(type) {
    switch (type) {
        case "food":
            return {
                url: 'https://firebasestorage.googleapis.com/v0/b/travelinfo-1cc54.appspot.com/o/bar.png?alt=media&token=1a2506f3-7e66-4fdb-b282-9c3eb1c1eff1', // url
                scaledSize: new google.maps.Size(50, 50), // scaled size
            };
        case "sleep":
            return {
                url: 'https://firebasestorage.googleapis.com/v0/b/travelinfo-1cc54.appspot.com/o/hotel.png?alt=media&token=53a39efd-ec02-445d-8c8f-f2c16cc21d52', // url
                scaledSize: new google.maps.Size(50, 50), // scaled size
            };
        case "place":
            return {
                url: 'https://firebasestorage.googleapis.com/v0/b/travelinfo-1cc54.appspot.com/o/building.png?alt=media&token=b33b0163-8293-4492-be83-d0120c4f49f9', // url
                scaledSize: new google.maps.Size(50, 50), // scaled size
            };
    }
}

function initMapPlaceDetails() {
    map = new google.maps.Map(document.getElementById('map'), {
        zoom: 14,
        center: {lat: 51.25052702419269, lng: 22.57244110107422},
        mapTypeControl: false,
        zoomControl: false,
        streetViewControl: false,
        fullscreenControl: false
    });

    var marker = new google.maps.Marker({
        map: map
    });

    placeMarkerAndPanTo(getLatLong(),
        map,
        getIconByType(getType()),
        marker);

    map.controls[google.maps.ControlPosition.LEFT_TOP].push(
        document.getElementById("leftBarViewDetails"));
    map.controls[google.maps.ControlPosition.RIGHT_TOP].push(
        document.getElementById("rightBarViewDetails"));
    map.controls[google.maps.ControlPosition.TOP_CENTER].push(
        document.getElementById("btnsReturn")
    );
}

function initMapTripDetails() {
    map = new google.maps.Map(document.getElementById('map'), {
        zoom: 14,
        center: {lat: 51.25052702419269, lng: 22.57244110107422},
        mapTypeControl: false,
        zoomControl: false,
        streetViewControl: false,
        fullscreenControl: false
    });

    var data = getDataTrip();

    function printElt(element, index, array) {
        var marker = new google.maps.Marker({
            map: map
        });
        console.log(data);
        placeMarkerAndPanTo(element.latLng, map, getIconByType(element.type), marker);
    }

    data.forEach(printElt);

    map.controls[google.maps.ControlPosition.LEFT_TOP].push(
        document.getElementById("leftBarViewDetails"));
    map.controls[google.maps.ControlPosition.RIGHT_TOP].push(
        document.getElementById("rightBarViewDetails"));
    map.controls[google.maps.ControlPosition.TOP_CENTER].push(
        document.getElementById("btnsReturn")
    );
}

function initDataOnMap(map, marker) {
    if (getDataPlace() !== null) {
        //get data onload page from spring veriable
        var place = getDataPlace();

        //set css on type button
        var btnType = document.getElementById(place.type.toString());
        btnType.style.backgroundColor = "#e0e3e7";

        //set marker
        placeMarkerAndPanTo(place.latlong, map, getIconByType(place.type.toString()), marker);

        // set images
        var containerImages = document.createElement('output');
        containerImages.id = 'list';
        document.getElementById("leftBarEdit").insertBefore(containerImages, document.getElementById("form"));
        for (var i = 0, f; f = place.files[i]; i++) {
            var div = document.createElement('div');
            div.className = "block btn-group";
            div.innerHTML = ['<img class="thumb" src="/images/', f.toString(),
                '" title=""/>'].join('');
            document.getElementById('list').insertBefore(div, null);
        }

        //set description
        document.getElementById("description").value = place.description;

        //set tags
        for (var i = 0, f; f = place.tags[i]; i++) {
            addTag(f);
        }

        $("#type").val(place.type);
        $("#latitude").val(place.latlong.lat);
        $("#longitude").val(place.latlong.lng);
        $("#address").val(place.address);
        $("#tags").val(place.tags);
    }

}

function addTag(value) {
    var input = document.getElementById("button-addon3");
    var newTag = document.createElement("span");
    newTag.className = "input-group-text";
    newTag.innerHTML = value;
    input.appendChild(newTag);
    document.getElementById("inputTags").value = "";
}


$("#btn-edit-trip").click(function () {
    $("#form-edit-trip").submit();
});










