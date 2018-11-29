function loadPosts(category){
$.getJSON("/blog/restservices/blogs/category/"+category+"?amount=3",function(blogs){
$.each(blogs,function(i,post){
$("#blogs").append("<div class=\"w3-margin w3-white\">"+
    "<img src=/blog/imgs/posts/"+post.id+"-banner.jpg style=max-height:350px;width:100%>"+
    "<div class=w3-container>"+
      "<h3><b>"+post.title+"</b></h3>"+
      "<h5>"+post.author.name+", <span class=w3-opacity>"+post.publicationdate+"</span></h5></div>"+

    "<div class=w3-container>"+
      "<p>"+post.header+"</p>"+
      "<div class=w3-row>"+
        "<div class=\"w3-col m8 s12\">"+
          "<p><a href=/blog/posts/"+post.id+".html><button class=\"w3-button w3-padding-large w3-white w3-border\"><b>READ MORE »</b></button></a></p></div>"+
      "</div></div></div><hr>");
});
});
}