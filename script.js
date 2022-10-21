const urls = [
    'https://www.nasa.gov/sites/default/files/styles/full_width/public/thumbnails/image/stsci-01gfnn3pwjmy4rqxkz585bc4qh.png?itok=Xja4XWS0',
    'https://live.staticflickr.com/65535/52405131881_526bafc241_c.jpg',
    'https://live.staticflickr.com/65535/52388530181_7a7027b0cc_3k.jpg',
    'https://live.staticflickr.com/65535/52374360369_3f1388730c_k.jpg'
];

$(document).ready(function () {

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
        const albums = await getAlbums();
        const album = albums[0];
        const images = album.images;
        for (let i = 0; i < images.length; i++) {
            $(".grid").prepend('<div class="image-box"><img src="' + images[i].url + '" alt="image"></div>');
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

    $("a").on("click", function () {
        init().then(() => {
            updatePage();
        });
    });








});