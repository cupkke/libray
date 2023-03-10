var webUrl = "http://localhost:8080/MavenProject/book";
var base = {
	loginUrl: webUrl + "/user/login",
	UserListUrl: webUrl + "/user/list",
	BookListUrl: webUrl + "/book/list",
	BookListHomeUrl: webUrl + "/book/listhome",
	BookListAdminUrl: webUrl + "/book/listadmin",
	findUserUrl: webUrl + "/user/finduser",
	findBookUrl: webUrl + "/book/findBook",
	BorrowingListUrlUrl: webUrl + "/borrowing/list",
	BorrowingListAdminUrlUrl: webUrl + "/borrowing/listadmin",
	updateUserUrl: webUrl + "/user/updateUserData",
	updateBookUrl: webUrl + "/book/updateBookData",
	BorrowinglUrl: webUrl + "/book/borrowing",
	deleteBorrowingUrl: webUrl + "/book/returnBook",
	addUserUrl: webUrl + "/user/add",
	addBookUrl: webUrl + "/book/addBook",
	fileDeleteUrl: webUrl + "/user/deleteUser",
	fileDeleteUrlBook: webUrl + "/book/deleteBook",
	curPage:1,//当前页码
	maxPage:1,//最大页数

	//获取页面路径中的参数
	getParam:function(key){
		var queryString = location.search;
		queryString = queryString.substring(1);
		console.info(queryString);

		var pathPair = queryString.split("&");
		for(let i = 0;i < pathPair.length; i++){
			var str = pathPair[i];
			var keyValue = str.split("=");
			if(key == keyValue[0]){
				return keyValue[1];
			}
		}
		return false;
	},
	//页面跳转
	j:function(uri){
		location.href = uri;
	},
	//登陆验证
	hasLogin : function(){
		var token = sessionStorage.getItem("token");
		if(location.pathname.indexOf("login.html") > -1){
			return;
		}else{
			if(!token){
				base.j("login.html");
			}
		}
		
	},

	initPageBtn : function(){
		//改变页码
		var pageHtml = ' <li><a data-page="up" href="#">«</a></li>';
		for(let i = 1;i <= base.maxPage; i++){
			var class_name = "";
			if(base.curPage == i){
				class_name = "active"
			}
			pageHtml += ' <li><a data-page="'+ i +'"class="'+ class_name +'" href="#">'+ i +'</a></li>';
		}
		pageHtml +=' <li><a data-page="down" href="#">»</a></li>'
		$("#pagination_box").html(pageHtml);
	},
	//改变当前页码
	changeCurPage : function(pageOpt){
		
			var pageNum = base.curPage;
			//上一页
			if(pageOpt == "up"){
				if(base.curPage > 1){
					pageNum = base.curPage-1;
					base.curPage -= 1;
				}
			}
			//下一页
			if(pageOpt == "down"){
				if(base.curPage < base.maxPage){
					pageNum = base.curPage+1;
					base.curPage += 1;
				}
			}
			//普通页码
			if(pageOpt != "up" && pageOpt != "down"){
				//把字符转化为数字
				pageNum = parseInt(pageOpt);
				base.curPage = pageNum;
			}
			return pageNum;
	}
};
base.hasLogin();