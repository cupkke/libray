var user = {
	init_login:function(){
		$("#login-btn").click(user.login);
	},
	init_list:function(){
		$(".pagination li > a").click(function(e){
			//取消HTML默认行为
			e.preventDefault();
			var ele = $(e.target);
			var pageOpt = ele.attr("data-page");
			var pageNum = base.changeCurPage(pageOpt);
			var keyword = $("#seach_box").val().trim();
			user.list(pageNum,keyword);
		})
	},
	init_search:function(){
		$("#search_btn").click(function(){
			var keyword = $("#seach_box").val().trim();
			user.list(1,keyword);
		})
	
	},
	login: function(){
		var login_name = $("#login-form input[name='login_name']").val().trim();
		var password = $("#login-form input[name='password']").val().trim();
		if(!login_name || login_name.length < 5){
			return alert("用户名不合法");
		}

		if(!password || password.length < 6) {
			return alert("密码不合法");
		}
		console.info($("#login-form").serialize());
		$.get(base.loginUrl,$("#login-form").serialize(),function(data){
			if(data.code == 200) {
				var token = data.data.token;
				var username = data.data.username;
				//将token存入sessionStorage
				sessionStorage.setItem("token", token);
				sessionStorage.setItem("usernumber", login_name);
				sessionStorage.setItem("username", username);
				user.listBorrowing();
				
				// return token ;
			} else {
				alert("用户名或密码错误！");
			}
		})
	},
	//获取用户列表
	list : function(pageNum,keyword){
		$.get(base.UserListUrl,{"page_num":pageNum,"page_size":10,"keyword":keyword},function(data){
			if(data.code == 200){
				var pageData = data.data;
				//获取当前页
				base.curPage = pageData.curPage;
				//获取总页数
				base.maxPage = pageData.totalPage;
				//改变页码
				base.initPageBtn();
				//重新绑定页码单击事件
				user.init_list();
				//更改页面数据
				var dataHtml = "";
				var users = pageData.data;
				for(let i = 0; i < users.length; i++){
					var userinfo = users[i];
					dataHtml += "<tr>";
					dataHtml += "<td>" + userinfo.usernumber + "</td>";
					dataHtml += "<td>" + userinfo.username + "</td>";
					dataHtml += "<td>" + userinfo.gender + "</td>";
					dataHtml += "<td>" + userinfo.age + "</td>";
					dataHtml += "<td>" + userinfo.classinfo + "</td>";
					dataHtml += "<td >" + " <button id="+userinfo.usernumber+">"+'删除'+"</button>" + " <button><a href='user-edit.html?usernumber="+ userinfo.usernumber +"'>编辑</a></button>" + "</td>";
					dataHtml += "</tr>";
				}
				$("#teacher_list tbody").html(dataHtml);

				 //获取文件id，删除文件
				 $(document).click(function(e) { // 在页面任意位置点击而触发此事件
				var id = $(e.target).attr("id");       // e.target表示被点击的目标
					var usernumber = /\d/;
					if(usernumber.test(id)){
					var r = confirm("是否删除学号为"+id);
					if (r == true) {
							user.delete(id);
						}
					} 
						
				  })
			}else{
				alert(data.msg);
			}
		})
	},
	delete : function(usernumber){
		$.get(base.fileDeleteUrl,{"usernumber":usernumber},function(data){
			if(data.code == 200){
				alert("删除成功！");
				location.href = "user-list.html";
			}else{
				alert(data.msg);
			}
		});
	},
	init_userlist : function(){
		var usernumber = sessionStorage.getItem("usernumber");
		console.info(usernumber);
		$.get(base.findUserUrl,{"usernumber":usernumber},function(data){
			if(data.code == 200){
				var user = data.data;
				console.info(user);
				$("#usernumber").val(user.usernumber);
				$("#password").val(user.password);
				$("#username").val(user.username);
				if(user.gender == "男"){
					$("#gender_man").attr("checked",true)
				}
				if(user.gender == "女"){
					$("#gender_woman").attr("checked",true)
				}
				$("#age").val(user.age);
				
			}else{
				alert(data.msg);
			}
		})
	},
	init_update : function(){
		var usernumber = base.getParam("usernumber");
		$.get(base.findUserUrl,{"usernumber":usernumber},function(data){
			if(data.code == 200){
				var user = data.data;
				$("#usernumber").val(user.usernumber);
				$("#password").val(user.password);
				$("#username").val(user.username);
				if(user.gender == "男"){
					$("#gender_man").attr("checked",true)
				}
				if(user.gender == "女"){
					$("#gender_woman").attr("checked",true)
				}
				$("#age").val(user.age);
				
			}else{
				alert(data.msg);
			}
		})
		//绑定提交点击事件
		$("#update_teacher_btn").click(user.update);
	},
	update : function(){
		var postData = $("#update_teacher_form").serialize();
		console.info(postData);
		$.get(base.updateUserUrl,postData,function(data){
			if(data.code == 200){
				alert("修改成功！");
				location.href = "user-list.html";
			}
		})
	},
	init_add : function(){
		$("#add_teacher_btn").click(user.add);
	},
	add:function(){
		var usernumber = $("#teacher_add_form input[name = 'usernumber']").val().trim();
		var pwd = $("#teacher_add_form input[name = 'password']").val().trim();
		var re_pwd = $("#teacher_add_form input[name = 're_password']").val().trim();
		var age = $("#teacher_add_form input[name = 'age']").val().trim();
		var number = /\d{11}/
		var agetest = /\d/
		if(!number.test(usernumber)){
			return alert("学号错误！");
		}
		if(pwd.length < 6){
			return alert("密码至少为6位");
		}
		if(pwd != re_pwd){
			return alert("两次密码不一致！");
		}
		if(!agetest.test(age)){
			return alert("年龄只能是数字！");
		}
		if(age<=0 || age>=110){
			return alert("年龄不合法！");
		}
		var postData = $("#teacher_add_form").serialize();
		console.info(postData)
		$.get(base.addUserUrl,postData,function(data){
			if(data.code == 200){
				alert("添加成功！");
				base.j("user-list.html");
			}else{
				alert(data.msg);
			}
		})
	},
	listBorrowing : function(){
		$.get(base.BorrowingListUrlUrl,{"page_num":1,"page_size":10,"keyword":sessionStorage.getItem("usernumber")},function(data){
			if(data.code == 200){
				var pageData = data.data;
				//获取当前页
				base.curPage = pageData.curPage;
				//获取总页数
				base.maxPage = pageData.totalPage;
				//改变页码
				base.initPageBtn();
				//重新绑定页码单击事件
				user.init_list();
				//更改页面数据
				var dataHtml = "";
				var borrowings = pageData.data;
				for(let i = 0; i < borrowings.length; i++){
					var borrowinginfo = borrowings[i];
					
						var dateYear = borrowinginfo.date.slice(0,4);
						var deteMonth = borrowinginfo.date.substring(5,7);
						var dateData = borrowinginfo.date.substring(8,11);
						var now = new Date();
						var nowMonth = now.getMonth()+1; //当前月 
						var nowYear = now.getFullYear(); //当前年 
						var nowDate = now.getDate();
						if(nowYear-dateYear <= 1){
							if(nowYear-dateYear == 1){
								nowMonth = nowMonth+12;
								if(nowMonth-deteMonth >1){
									alert("您所借阅的某本图书已超期，请及时归还！！！");
									break;
								}
							}
							else{
								let d = new Date(dateYear,deteMonth,0)
								var sum = d.getDate()-dateData;
								if(sum+nowDate > 30 && deteMonth != nowMonth){
									alert("您所借阅的某本图书已超期，请及时归还！！！");
									break;
								}
							}
						}else{
							alert("您所借阅的某本图书已超期，请及时归还！！！");
							break;
						}
					
					
				}
				//跳转页面
				location.href="home.html";
			}else{
				alert(data.msg);
			}
		});

	},
}

