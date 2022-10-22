const urls = [
    'https://www.nasa.gov/sites/default/files/styles/full_width/public/thumbnails/image/stsci-01gfnn3pwjmy4rqxkz585bc4qh.png?itok=Xja4XWS0',
    'https://live.staticflickr.com/65535/52405131881_526bafc241_c.jpg',
    'https://live.staticflickr.com/65535/52388530181_7a7027b0cc_3k.jpg',
    'https://live.staticflickr.com/65535/52374360369_3f1388730c_k.jpg'
];

$(document).ready(function () {

    var getUrlParameter = function getUrlParameter(sParam) {
        var sPageURL = window.location.search.substring(1),
            sURLVariables = sPageURL.split('&'),
            sParameterName,
            i;
    
        for (i = 0; i < sURLVariables.length; i++) {
            sParameterName = sURLVariables[i].split('=');
    
            if (sParameterName[0] === sParam) {
                return sParameterName[1] === undefined ? true : decodeURIComponent(sParameterName[1]);
            }
        }
        return false;
    };

    var albumId = getUrlParameter('id');

    async function init() {
        const response = await fetch('http://localhost:8090/init');
        const res = await response.text();
        return res;
    }

    async function getAlbums() {
        const response = await fetch('http://localhost:8090/getAlbums');
        const res = await response.json();
        return res;
    }

    // Add an image to the database
    async function addImage(id, url) {
        const response = await fetch('http://localhost:8090/addImage/' + String(Number(id)), {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: url
        });
        const res = await response.text();
        return res;
    }

    // Get album from the database
    async function getAlbum(id) {
        const response = await fetch('http://localhost:8090/getAlbum/' + String(Number(id)));
        const res = await response.json();
        return res;
    }

    // Delete an image from the database
    async function deleteImage(id) {
        const response = await fetch('http://localhost:8090/deleteImage/' + String(Number(id)), {
            method: 'DELETE'
        });
        const res = await response.text();
        return res;
    }

    // Delete an album from the database
    async function deleteAlbum(id) {
        const response = await fetch('http://localhost:8090/deleteAlbum/' + String(Number(id)), {
            method: 'DELETE'
        });
        const res = await response.text();
        return res;
    }

    // Create a new album
    async function createAlbum(name) {
        const response = await fetch('http://localhost:8090/addAlbum/' + name);
        const res = await response.json();
        return res;
    }

    async function start() {
        res = await getAlbums();
        if (res.length === 0) {
            await init();
            const album = await createAlbum("James Webb")
            const id = album.id;
            console.log(id);
            for (let i = 0; i < urls.length; i++) {
                await addImage(id, urls[i]);
            }
        }
    }

    // Update the page with the new album
    async function updatePage() {
        $(".image-box").remove();
        const album = await getAlbum(albumId);
        console.log(album);
        const images = album.images;
        for (let i = 0; i < images.length; i++) {
            const url = images[i].url;
            div = document.createElement("div");
            a = document.createElement("a");
            div.className = "image-box";
            a.onclick = deleteImage(images[i].id);
            a.textContent = "(delete image)";
            div.innerHTML = "<img src=" + url + '" alt="image">' + a.outerHTML + "'"
            $(".grid").prepend(div);
        }
        $("h1").text(album.name);
        $("h1").attr("id", album.id);
        document.title = album.name;
    }

    start().then(() => {
        updatePage();
    });

    // Add a new image to the album with the URL in the input box
    $("button").on("click", function () {
        console.log("add image");
        const id = $("h1").attr("id");
        const url = $("#image-url").val();
        console.log(url);
        addImage(id, url).then(() => {
            updatePage();
        });
        $("#image-url").val("");
    });

    $("#image-url").on("keypress", function (e) {
        if (e.which === 13) {
            console.log("add image");
            const id = $("h1").attr("id");
            const url = $("#image-url").val();
            console.log(url);
            addImage(id, url).then(() => {
                updatePage();
            });
            $("#image-url").val("");
        }
    });

    $("#init").on("click", function () {
        init().then(() => {
            updatePage();
        });
    });








});