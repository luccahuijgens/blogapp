function loadPost(id){
$.getJSON("/blog/restservices/blogs/"+id,function(post){
$("#postbanner").append("<img src=/blog/imgs/posts/"+post.id+"-banner.jpg style=width:100%>");
$("#posthead").append("<b style=font-size:2.5em;font-family:Tiempos Headline,Tiempos,Georgia,serif;>"+post.title+"</b><h5>"+post.author.name+"&emsp;<span class=w3-opacity>"+post.publicationdate+"</span></h5>"+
"<h4>"+post.header+"</h4>");
$("#postcontent").append("<p>"+post.content+"</p>")
});
}