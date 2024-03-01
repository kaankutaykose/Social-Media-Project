/*
fetch('http://localhost:8080/posts')
      .then(response => response.json())
      .then(json => console.log(json))
*/

var postList = document.getElementById("postListesi")
let data = fetch('http://localhost:8080/posts')
      .then(response => response.json())
      .then(veri => {
            veri.forEach(element =>{
            if(element.image_url==null&&element.video_url==null){
                postList.innerHTML +=
                                `<div class="card" style="width: 18rem;">
                                    <h5 class="card-title" >${element.userName}</h5>
                                    <div class="card-body"><p class="card-text">${element.title}</p>
                                    <p class="card-text">${element.text}</p>
                                    <img src="images/icons8-favorite-24.png">
                                    <p>${element.likeList.length}</p>
                                    <img src="images/icons8-speech-24.png">
                                    <p>${element.commentList.length}</p>
                                    </div>
                                 </div>`
            }
            if(element.image_url!=null){
                postList.innerHTML +=
                                `<div class="card" style="width: 18rem;">
                                    <img width="400" height="300" src="${element.image_url}" alt="Nature" style="width:100%" />
                                    <h5 class="card-title" >${element.userName}</h5>
                                    <div class="card-body"><p class="card-text">${element.title}</p>
                                    <p class="card-text">${element.text}</p>
                                    <img src="images/icons8-favorite-24.png">
                                    <p>${element.likeList.length}</p>
                                    <img src="images/icons8-speech-24.png">
                                    <p>${element.commentList.length}</p>
                                    </div>
                                 </div>`
            }
            else{
                postList.innerHTML +=
                                `<div class="card" style="width: 18rem;">
                                    <iframe width="400" height="300" src="${element.video_url}" alt="Nature" style="width:100%"></iframe>
                                    <h5 class="card-title" >${element.userName}</h5>
                                    <div class="card-body"><p class="card-text">${element.title}</p>
                                    <p class="card-text">${element.text}</p>
                                    <img src="images/icons8-favorite-24.png">
                                    <p>${element.likeList.length}</p>
                                    <img src="images/icons8-speech-24.png">
                                    <p>${element.commentList.length}</p>
                                    </div>
                                 </div>`

            }

            });
      });




function addPost(){
var post_userId = document.getElementById("post_userId").value
var post_text = document.getElementById("post_text").value
var post_title = document.getElementById("post_title").value
var post_video_url = document.getElementById("post_video_url").value
var post_image_url = document.getElementById("post_image_url").value

console.log({post_userId,post_text,post_title,post_video_url,post_image_url})
fetch('http://localhost:8080/posts', {
  method: 'POST',
  body: JSON.stringify({
    userId: post_userId,
    text: post_text,
    title: post_title,
    image_url: post_image_url,
    video_url: post_video_url,
  }),
  headers: {
    'Content-type': 'application/json; charset=UTF-8',
  },
})
  .then((response) => response.json())
  .then((json) => console.log(json));
}


