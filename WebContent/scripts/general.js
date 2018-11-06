function loadCategories(){
$.getJSON("/blog/restservices/categories",function(categories){
	$.each(categories,function(i,category){
	$("#categorylinklist").append(
	"<li><a href=#home><i class=\"fa fa-angle-right\">&emsp;</i>"+category.name+"</a></li>");
	});
});
}

function loadMaster(){
$.getJSON("/blog/restservices/users/role/master",function(master){
$("#masterauthor").append(
"<h4><b>"+master[0].username+"</b></h4>"+
"<p>"+master[0].description+"</p>");
$("#header").append(
"<h1><b>MY BLOG</b></h1>"+
"<p>Welcome to the blog of <span class=w3-tag>"+master[0].username+"</span></p>");
$("#masterauthorimage").append("<img src=/blog/imgs/users/"+master[0].id+".jpg style=width:100%;max-width:350px;max-height:300px;>");
});
}

function loadLatest(){
	$.getJSON("/blog/restservices/blogs?amount=4",function(blogs){
	$.each(blogs,function(i,blog){
	if(i<3){
	$("#latestposts").append(
"<li class=\"w3-padding-16\" onclick=window.location.href=\"/blog/posts/"+blog.id+".html\">"+
"<img src=/blog/imgs/posts/"+blog.id+"-icon.jpg alt=Image class=\"w3-left w3-margin-right\" style=width:50px>"+
"<span class=w3-large>"+blog.title+"</span><br>"+
"<span>By "+blog.author.name+"</span></li></a>");
	}else{
			$("#latestposts").append(
		"<li class=\"w3-padding-16 w3-hide-medium w3-hide-small\" onclick=window.location.href=\"/blog/posts/"+blog.id+".html\">"+
		"<img src=/blog/imgs/posts/"+blog.id+"-icon.jpg alt=Image class=\"w3-left w3-margin-right\" style=width:50px>"+
		"<span class=w3-large>"+blog.title+"</span><br>"+
		"<span>By "+blog.author.name+"</span></li></a>");
		}
	});
	});
}