<%@page contentType="text/html; charset=utf-8" %>
<!DOCTYPE html>
<html>
	<head lang="en">
		<meta charset="utf-8" />
		<title>最家</title>
		<link rel="stylesheet" type="text/css" href="/cart_css/public.css"/>
		<link rel="stylesheet" type="text/css" href="/cart_css/myorder.css" />
	</head>
	<body>
		<!------------------------------head------------------------------>
		<jsp:include page="commn/head.jsp"></jsp:include>
		<!------------------------------idea------------------------------>
		<div class="address mt">
			<div class="wrapper clearfix">
				<a href="#" class="fl">首页</a>
				<span>/</span>
				<a href="mygxin.html">个人中心</a>
				<span>/</span>
				<a href="myorderq.html" class="on">我的订单</a>
			</div>
		</div>
		
		<!------------------------------Bott------------------------------>
		<div class="Bott">
			<div class="wrapper clearfix">
				<jsp:include page="commn/zuo.jsp"></jsp:include>
				
				<div class="you fl" id="orderShow">
					<div class="my clearfix">
						<h2 class="fl">我的订单</h2>
						<a href="#" class="fl">请谨防钓鱼链接或诈骗电话，了解更多&gt;</a>
					</div>
					<div class="dlist clearfix">
						<ul class="fl clearfix" id="wa">
							<li id=-1 class="on"><a href="#2">全部有效订单</a></li>
							<li id= 1><a href="#2">待支付</a></li>
							<li id= 2><a href="#2l">待收货</a></li>
							<li id= 3><a href="#2">已关闭</a></li>
						</ul>
						<form action="#" method="get" class="fr clearfix">
							<input type="text" name="" id="" value="" placeholder="请输入商品名称、订单号" />
							<input type="button" name="" id="" value="" />
						</form>
					</div>
					<div class="dkuang deng" v-for="(order, index) in orders">
						<p class="one">{{order.status}}</p>
						<div class="word clearfix">
							<ul class="fl clearfix">
								<li>{{order.createTime}}</li>
								<li>{{order.userRealName}}</li>
								<li>订单号:{{order.id}}</li>
								<li>{{order.payChannel}}</li>
							</ul>
							<p class="fr">订单金额：<span>{{order.totalPrice}}</span>元</p>
						</div>
						<div class="shohou clearfix">
						
							<div v-for="goodsDetail in order.orderGoods">
								<a href="#" class="fl"><img src="/img/g1.jpg"/></a>
								<p class="fl"><a href="#">{{goodsDetail.goodsName}}</a><a href="#">¥{{goodsDetail.price}}×{{goodsDetail.goodsAccount}}</a></p>
							</div>
							
							<p class="fr">
								<a href="#" v-if="order.status == 1">立即支付</a>
								<a v-bind:href="'/userOrder/toMyOrderDetail.do?orderId='+order.id" >订单详情</a>
							</p>
						</div>
					</div>
					
					<div class="fenye clearfix">
						<a href="#" onclick="prePage();"><img src="/img/zuo.jpg" /></a>
						<a href="#"><span id = "currentPageNum"></span></a>
						<a href="#" onclick="nextPage();"><img src="/img/you.jpg"/></a>
					</div>
				</div>
			</div>
		</div>
		<!--返回顶部-->
		<jsp:include page="commn/gtop.jsp"></jsp:include>
		<!--footer-->
		<jsp:include page="commn/footer.jsp"></jsp:include>
		
<script type="text/javascript" src="/js/jquery-mini.js"></script>
<script type="text/javascript" src="/js/vue.js"></script>
<script type="text/javascript" charset="utf-8">
var currentStatus = -1;
var orderData = {};

var currentPage = 1;
var totalPageNum = -1;

$(document).ready(function() {
	orderData = new Vue({
		el : '#orderShow',
		data : {
			orders : []
		}
	});
	
	initOrderStatus();
	loadMyOrder();
});

function initOrderStatus() {
	$('#wa li').click(function() {
		$(this).addClass("on").siblings().removeClass("on");
		currentStatus = $(this).attr("id");
		alert(currentStatus);
		loadMyOrder();
	});
}

function nextPage() {
	if (currentPage + 1 >= totalPageNum) {
		return;
	}
	currentPage = currentPage + 1;
	loadMyOrder(currentPage);
}

function prePage() {
	if (currentPage <= 1) {
		return;
	}
	currentPage = currentPage - 1;
	loadMyOrder(currentPage);
}

function toOrderDetail() {
	$.ajax({
	    url:'/userOrder/loadMyOrder.do',
	    type:'POST', //GET
	    async:true,    //或false,是否异步
	    data:{
	    	status : currentStatus,
	    	pageNum : currentPage,
	    },
	    timeout:5000,    //超时时间
	    dataType:'json',    //返回的数据格式：json/xml/html/script/jsonp/text
	    beforeSend:function(xhr){
	        console.log(xhr)
	        console.log('发送前')
	    },
	    success:function(data,textStatus,jqXHR){
	        
	        if (!data.success) {
	        	alert(data.message);
	        	return;
	        }
	        
	        
	        window.location.href="/userOrder/toMyOrderDetail.do?#"
	       
	    },
	    error:function(xhr,textStatus){
	        console.log('错误')
	        console.log(xhr)
	        console.log(textStatus)
	    },
	    complete:function(){
	        console.log('结束')
	    }
	});
	
}

function loadMyOrder(currentPage) {
	$.ajax({
	    url:'/userOrder/loadMyOrder.do',
	    type:'POST', //GET
	    async:true,    //或false,是否异步
	    data:{
	    	status : currentStatus,
	    	pageNum : currentPage,
	    },
	    timeout:5000,    //超时时间
	    dataType:'json',    //返回的数据格式：json/xml/html/script/jsonp/text
	    beforeSend:function(xhr){
	        console.log(xhr)
	        console.log('发送前')
	    },
	    success:function(data,textStatus,jqXHR){
	        
	        if (!data.success) {
	        	alert(data.message);
	        	return;
	        }
	        
	        orderData.orders = data.data.data;
	        currentPage = data.data.pageNum;
	        totalPageNum = data.data.lastPage;
	        $('#currentPageNum').html(currentPage);
	    },
	    error:function(xhr,textStatus){
	        console.log('错误')
	        console.log(xhr)
	        console.log(textStatus)
	    },
	    complete:function(){
	        console.log('结束')
	    }
	});
}


		</script>
	</body>
</html>
