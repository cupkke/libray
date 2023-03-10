var first = {
	init_list:function(){
		$(".pagination li > a").click(function(e){
			//取消HTML默认行为
			e.preventDefault();
			var ele = $(e.target);
			var pageOpt = ele.attr("data-page");
			var pageNum = base.changeCurPage(pageOpt);
			var keyword = $("#seach_box").val().trim();
			first.list(pageNum,keyword);
		})
	},
	init_search:function(){
		$("#search_btn").click(function(){
			var keyword = $("#seach_box").val().trim();
			first.list(1,keyword);
		})
	
	},
	//获取图书列表
	list : function(pageNum,keyword){
		$.get(base.BookListUrl,{"page_num":pageNum,"page_size":5,"keyword":keyword},function(data){
			if(data.code == 200){
				var pageData = data.data;
				//获取当前页
				base.curPage = pageData.curPage;
				//获取总页数
				base.maxPage = pageData.totalPage;
				//改变页码
				base.initPageBtn();
				//重新绑定页码单击事件
				first.init_list();
				//更改页面数据
				var dataHtml = "";
				var books = pageData.data;
				for(let i = 0; i < books.length; i++){
					var bookinfo = books[i];
					if(bookinfo.amount>0){
						dataHtml += "<tr>";
						dataHtml += "<td>" + bookinfo.id + "</td>";
						dataHtml += "<td>" + bookinfo.bookname + "</td>";
						dataHtml += "<td>" + bookinfo.author + "</td>";
						dataHtml += "<td>" + bookinfo.publisher + "</td>";
						dataHtml += "<td>" + bookinfo.price + "</td>";
						dataHtml += "<td>" + bookinfo.category + "</td>";
						dataHtml += "<td style='width:580px'>" + bookinfo.bookdesc + "</td>";
						dataHtml += "<td>" + bookinfo.amount + "</td>";
						dataHtml += "<td >" + " <button value="+bookinfo.bookname+" id="+bookinfo.id+">"+'借书'+"</button>" ;
						dataHtml += "</tr>";
					}else{
						dataHtml += "<tr>";
						dataHtml += "<td>" + bookinfo.id + "</td>";
						dataHtml += "<td>" + bookinfo.bookname + "</td>";
						dataHtml += "<td>" + bookinfo.author + "</td>";
						dataHtml += "<td>" + bookinfo.publisher + "</td>";
						dataHtml += "<td>" + bookinfo.price + "</td>";
						dataHtml += "<td>" + bookinfo.category + "</td>";
						dataHtml += "<td style='width:580px'>" + bookinfo.bookdesc + "</td>";
						dataHtml += "<td>" + bookinfo.amount + "</td>";
						dataHtml += "<td >" + "全部借出" +"</td>";
						dataHtml += "</tr>";
					}
				}
				$("#teacher_list tbody").html(dataHtml);


				$("#teacher_list tbody td button").click(function(e) { // 在页面任意位置点击而触发此事件
					var bookid = $(e.target).attr("id");   // e.target表示被点击的目标  
					var bookname = $(e.target).attr("value");
					console.info(bookname);
					var username = sessionStorage.getItem("username");
					var usernumber = sessionStorage.getItem("usernumber");
					var data = {"bookid":bookid,
								"bookname":bookname,
								"username":username,
								"usernumber":usernumber};
					var booknumber = /\d/;
					if(booknumber.test(bookid)){
					var r = confirm("是否借阅编号为"+bookid+"的图书！");
					if (r == true) {
						$.get(base.BorrowinglUrl,data,function(data){
							if(data.code == 200){
								alert("借阅成功！");
								location.href = "home.html";
							}else{
								alert(data.msg);
							}
						})
						}
					} 
						
					});
			}else{
				alert(data.msg);
			}
		})
	},
	//获取图书列表
	listhome : function(pageNum,keyword){
		$.get(base.BookListHomeUrl,{"page_num":pageNum,"page_size":5,"keyword":keyword},function(data){
			if(data.code == 200){
				var pageData = data.data;
				//获取当前页
				base.curPage = pageData.curPage;
				//获取总页数
				base.maxPage = pageData.totalPage;
				//改变页码
				base.initPageBtn();
				//重新绑定页码单击事件
				first.init_list();
				//更改页面数据
				var dataHtml = "";
				var books = pageData.data;
				for(let i = 0; i < books.length; i++){
					var bookinfo = books[i];
					dataHtml += "<tr>";
					dataHtml += "<td>" + "#" + "</td>";
					dataHtml += "<td>" + bookinfo.bookname + "</td>";
					dataHtml += "<td>" + bookinfo.author + "</td>";
					dataHtml += "<td>" + bookinfo.publisher + "</td>";
					dataHtml += "<td>" + bookinfo.price + "</td>";
					dataHtml += "<td>" + bookinfo.category + "</td>";
					dataHtml += "<td style='width:580px'>" + bookinfo.bookdesc + "</td>";
					dataHtml += "</tr>";
				}
				$("#teacher_list tbody").html(dataHtml);
			}else{
				alert(data.msg);
			}
		})
	},
	update : function(){
		var postData = $("#update_teacher_form").serialize();
		$.get(base.updateBookUrl,postData,function(data){
			if(data.code == 200){
				alert("修改成功！");
				location.href = "book-list.html";
			}
		})
	},
}

