$(document).ready(function () {
    
    $(".new-box").on("click", function () {
        $(".grid").prepend('<div class="image-box"><img src="https://www.nasa.gov/sites/default/files/styles/full_width/public/thumbnails/image/stsci-01gfnn3pwjmy4rqxkz585bc4qh.png?itok=Xja4XWS0" alt="image"></div>');
    });

    async function fetchJWId() {
        const response = await fetch('http://localhost:8090/getAlbum/1');
        const movies = await response.text();
        return movies;
      }
      fetchJWId().then(movies => {
        console.log(movies); // fetched movies
      });

});