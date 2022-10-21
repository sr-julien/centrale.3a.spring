$(document).ready(function () {
    
    $(".new-box").on("click", function () {
        $(".grid").prepend('<div class="image-box"><img src="https://www.nasa.gov/sites/default/files/styles/full_width/public/thumbnails/image/stsci-01gfnn3pwjmy4rqxkz585bc4qh.png?itok=Xja4XWS0" alt="image"></div>');
    });

    async function init() {
        const response = await fetch('http://localhost:8090/init');
        const res = await response.json();
        return res;
      }

      async function getAlbums() {
        const response = await fetch('http://localhost:8090/getAlbums');
        const res = await response.json();
        return res;
      }

      // Add an image to the database
      async function addImage(id) {
        const response = await fetch('http://localhost:8090/addImage/' + String(Number(id)), {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: 'https://www.nasa.gov/sites/default/files/styles/full_width/public/thumbnails/image/stsci-01gfnn3pwjmy4rqxkz585bc4qh.png?itok=Xja4XWS0' 
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


    async function main() {
        await init();
        const albums = await getAlbums();
        let id = albums[0].id;
        await addImage(id);
        let album = await getAlbum(id);
        console.log(album);
        console.log(album.images[0].url);
    }

    main();
    
    
    
        
        
      

});