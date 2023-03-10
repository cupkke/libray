var book = {
	init_list:function(){
		$(".pagination li > a").click(function(e){
			//取消HTML默认行为
			e.preventDefault();
			var ele = $(e.target);
			var pageOpt = ele.attr("data-page");
			var pageNum = base.changeCurPage(pageOpt);
			var keyword = $("#seach_box").val().trim();
			book.list(pageNum,keyword);
		})
	},
	init_search:function(){
		$("#search_btn").click(function(){
			var keyword = $("#seach_box").val().trim();
			book.list(1,keyword);
		})
	
	},
	//获取图书列表
	list : function(pageNum,keyword){
		$.get(base.BookListAdminUrl,{"page_num":pageNum,"page_size":10,"keyword":keyword},function(data){
			if(data.code == 200){
				var pageData = data.data;
				//获取当前页
				base.curPage = pageData.curPage;
				//获取总页数
				base.maxPage = pageData.totalPage;
				//改变页码
				base.initPageBtn();
				//重新绑定页码单击事件
				book.init_list();
				//更改页面数据
				var dataHtml = "";
				var books = pageData.data;
				for(let i = 0; i < books.length; i++){
					var bookinfo = books[i];
					dataHtml += "<tr>";
					dataHtml += "<td>" + bookinfo.id + "</td>";
					dataHtml += "<td>" + bookinfo.bookname + "</td>";
					dataHtml += "<td>" + bookinfo.author + "</td>";
					dataHtml += "<td>" + bookinfo.publisher + "</td>";
					dataHtml += "<td>" + bookinfo.price + "</td>";
					dataHtml += "<td>" + bookinfo.category + "</td>";
					dataHtml += "<td style='width:570px'>" + bookinfo.bookdesc + "</td>";
					dataHtml += "<td>" + bookinfo.amount + "</td>";
					dataHtml += "<td >" + "<button value='"+bookinfo.bookname+"-"+ bookinfo.author +"'id='"+bookinfo.id+"'>"+'删除'+"</button>" 
					                    + " <button><a href='book-edit.html?id="+ bookinfo.id +"'>编辑</a></button>" + "</td>";
					dataHtml += "</tr>";
				}
				$("#teacher_list tbody").html(dataHtml);

				 //获取文件id，删除文件
				 $(document).click(function(e) { // 在页面任意位置点击而触发此事件
				var id = $(e.target).attr("id");   // e.target表示被点击的目标  
				var value = $(e.target).attr("value");
				 var booknumber = /\d/;
				 if(booknumber.test(id)){
				 var r = confirm("是否删除编号为"+id);
				 if (r == true) {
				 		book.delete(id);
				 	}
				 } 
						
				  })
			}else{
				alert(data.msg);
			}
		})
	},
	delete : function(id){
		$.get(base.fileDeleteUrlBook,{"id":id},function(data){
			if(data.code == 200){
				alert("删除成功！");
				location.href = "book-list.html";
			}else{
				alert(data.msg);
			}
		});
	},
	init_booklist : function(){
		var booknumber = sessionStorage.getItem("booknumber");
		console.info(booknumber);
		$.get(base.findBookUrl,{"booknumber":booknumber},function(data){
			if(data.code == 200){
				var book = data.data;
				console.info(book);
				$("#booknumber").val(book.booknumber);
				$("#password").val(book.password);
				$("#bookname").val(book.bookname);
				if(book.gender == "男"){
					$("#gender_man").attr("checked",true)
				}
				if(book.gender == "女"){
					$("#gender_woman").attr("checked",true)
				}
				$("#age").val(book.age);
				
			}else{
				alert(data.msg);
			}
		})
	},
	init_update : function(){
		var id = base.getParam("id");
		$.get(base.findBookUrl,{"id":id},function(data){
			if(data.code == 200){
				var book = data.data;
				$("#id").val(book.id);
				$("#bookname").val(book.bookname);
				$("#author").val(book.author);
				$("#publisher").val(book.publisher);
				$("#price").val(book.price);
				$("#category").val(book.category);
				$("#amount").val(book.amount);
				$("#bookdesc").val(book.bookdesc);
			}else{
				alert(data.msg);
			}
		})
		//绑定提交点击事件
		$("#update_teacher_btn").click(book.update);
	},
	update : function(){
		var postData = $("#update_teacher_form").serialize();
		$.get(base.updateBookUrl,postData,function(data){
			if(data.code == 200){
				alert("修改成功！");
				location.href = "book-list.html";
			}else{
				alert("修改失败！！");
				// alert(data.msg);
			}
		})
	},
	init_add : function(){
		$("#add_teacher_btn").click(book.add);
	},
	add:function(){
		var amount = $("#amount").val().trim();
		if(amount == 0){
			alert("数量不能为'0'或'空'！");
			return ;
		}else{
			var jiaoyan = /\d/;
			if(!jiaoyan.test(amount)){
				alert("数量只能为数字！");
				return ;
			}
			var postData = $("#teacher_add_form").serialize();
				$.get(base.addBookUrl,postData,function(data){
					if(data.code == 200){
						base.j("book-list.html");
						alert("添加成功！");
							
					}else{
						 alert(data.msg);
					}
				});
	
		}
		
	},
}

