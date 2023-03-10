var borrowing = {
	init_list:function(){
		$(".pagination li > a").click(function(e){
			//取消HTML默认行为
			e.preventDefault();
			var ele = $(e.target);
			var pageOpt = ele.attr("data-page");
			var pageNum = base.changeCurPage(pageOpt);
			borrowing.list(pageNum,"");
		})
	},
	init_search:function(){
		$("#search_btn").click(function(){
			borrowing.list(1,"");
		})
	
	},
	init_list_admin:function(){
		$(".pagination li > a").click(function(e){
			//取消HTML默认行为
			e.preventDefault();
			var ele = $(e.target);
			var pageOpt = ele.attr("data-page");
			var pageNum = base.changeCurPage(pageOpt);
			var keyword = $("#seach_box").val().trim();
			borrowing.listadmin(pageNum,keyword);
		})
	},
	init_search:function(){
		$("#search_btn").click(function(){
			var keyword = $("#seach_box").val().trim();
			borrowing.listadmin(1,keyword);
		})
	
	},
	//获取借阅列表
	list : function(pageNum,keyword){
		var usernumber = sessionStorage.getItem("usernumber");
		$.get(base.BorrowingListUrlUrl,{"page_num":pageNum,"page_size":10,"keyword":usernumber},function(data){
			if(data.code == 200){
				var pageData = data.data;
				//获取当前页
				base.curPage = pageData.curPage;
				//获取总页数
				base.maxPage = pageData.totalPage;
				//改变页码
				base.initPageBtn();
				//重新绑定页码单击事件
				borrowing.init_list();
				//更改页面数据
				var dataHtml = "";
				var borrowings = pageData.data;
				for(let i = 0; i < borrowings.length; i++){
					var borrowinginfo = borrowings[i];
					
						dataHtml += "<tr>";
						dataHtml += "<td>" + borrowinginfo.bookid + "</td>";
						dataHtml += "<td>" + borrowinginfo.bookname + "</td>";
						dataHtml += "<td>" + borrowinginfo.usernumber + "</td>";
						dataHtml += "<td>" + borrowinginfo.username + "</td>";
						dataHtml += "<td>" + borrowinginfo.date + "</td>";
						dataHtml += "<td id="+borrowinginfo.id+">" + " <button id="+borrowinginfo.bookid+" value="+borrowinginfo.bookname+">"+'还书'+"</button>";
						dataHtml += "</tr>";
					
					
				}
			
				$("#teacher_list tbody").html(dataHtml);
				$(document).click(function(e) { // 在页面任意位置点击而触发此事件
						var bookid = $(e.target).attr("id");   // e.target表示被点击的目标  
						var bookname = $(e.target).attr("value");
						var id = $(e.target).parent().attr("id");
						var borrowingnumber = /\d/;
						if(borrowingnumber.test(id)){
						var r = confirm("是否归还*"+bookname+"*！");
						if (r == true) {
								borrowing.delete(id,bookid);
							}
						} 	
					  })
			}else{
				alert(data.msg);
			}
		})
	},
	listadmin : function(pageNum,keyword){
		var number = /\d{11}/;
		var admin= /\d{5}/
		if(number.test(keyword)||admin.test(keyword)){
			$.get(base.BorrowingListUrlUrl,{"page_num":pageNum,"page_size":10,"keyword":keyword},function(data){
				if(data.code == 200){
					var pageData = data.data;
					//获取当前页
					base.curPage = pageData.curPage;
					//获取总页数
					base.maxPage = pageData.totalPage;
					//改变页码
					base.initPageBtn();
					//重新绑定页码单击事件
					borrowing.init_list_admin();
					//更改页面数据
					var dataHtml = "";
					var borrowings = pageData.data;
					for(let i = 0; i < borrowings.length; i++){
						var borrowinginfo = borrowings[i];
						
							dataHtml += "<tr>";
							dataHtml += "<td>" + borrowinginfo.bookid + "</td>";
							dataHtml += "<td>" + borrowinginfo.bookname + "</td>";
							dataHtml += "<td>" + borrowinginfo.usernumber + "</td>";
							dataHtml += "<td>" + borrowinginfo.username + "</td>";
							dataHtml += "<td>" + borrowinginfo.date + "</td>";
							dataHtml += "</tr>";
						
						
					}
				
					$("#teacher_list tbody").html(dataHtml);
				}else{
					alert(data.msg);
				}
			})
			return ;
		}
		$.get(base.BorrowingListAdminUrlUrl,{"page_num":pageNum,"page_size":10,"keyword":keyword},function(data){
			if(data.code == 200){
				var pageData = data.data;
				//获取当前页
				base.curPage = pageData.curPage;
				//获取总页数
				base.maxPage = pageData.totalPage;
				//改变页码
				base.initPageBtn();
				//重新绑定页码单击事件
				borrowing.init_list_admin();
				//更改页面数据
				var dataHtml = "";
				var borrowings = pageData.data;
				for(let i = 0; i < borrowings.length; i++){
					var borrowinginfo = borrowings[i];
					
						dataHtml += "<tr>";
						dataHtml += "<td>" + borrowinginfo.bookid + "</td>";
						dataHtml += "<td>" + borrowinginfo.bookname + "</td>";
						dataHtml += "<td>" + borrowinginfo.usernumber + "</td>";
						dataHtml += "<td>" + borrowinginfo.username + "</td>";
						dataHtml += "<td>" + borrowinginfo.date + "</td>";
						dataHtml += "</tr>";
					
					
				}
			
				$("#teacher_list tbody").html(dataHtml);
			}else{
				alert(data.msg);
			}
		})
	},

	delete : function(id,bookid){
		$.get(base.deleteBorrowingUrl,{"bookid":bookid,"id":id},function(data){
			if(data.code == 200){
				alert("已归还！");
				location.href = "borrowing.html";
			}else{
				alert(data.msg);
			}
		})
	},
	init_add : function(){
		$("#add_teacher_btn").click(borrowing.add);
	},
	add:function(){
		var postData = $("#teacher_add_form").serialize();
		console.info(postData);
	 	$.get(base.addborrowingUrl,postData,function(data){
	 		if(data.code == 200){
	 			alert("添加成功！");
	 			base.j("borrowing-list.html");
	 		}else{
				alert("添加失败！");
	 			// alert(data.msg);
	 		}
	 	})
	 },
}

