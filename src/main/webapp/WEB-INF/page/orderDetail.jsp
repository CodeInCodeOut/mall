<%@page contentType="text/html; charset=utf-8" %>
<!DOCTYPE html>
<html>
	<head lang="en">
		<meta charset="utf-8" />
		<title>个人信息</title>
		<link rel="stylesheet" type="text/css" href="/cart_css/public.css"/>
		<link rel="stylesheet" type="text/css" href="/cart_css/myorder.css" />
	</head>
	<body>
		<!------------------------------head------------------------------>
		<jsp:include page="commn/head.jsp"></jsp:include>
		<!------------------------------idea------------------------------>
		<div class="address mt">
			<div class="wrapper clearfix">
				<a href="index.html" class="fl">首页</a>
				<span>/</span>
				<a href="myorderq.html" class="on">我的订单</a>
				<span>/</span>
				<a href="#" class="on">订单详情</a>
			</div>
		</div>
		
		<!------------------------------Bott------------------------------>
		<div class="Bott">
			<div class="wrapper clearfix">
				<jsp:include page="commn/zuo.jsp"></jsp:include>
				
				<div class="you fl " id="goodsShow">
					<div class="my clearfix">
						<h2>订单详情<a href="#">请谨防钓鱼链接或诈骗电话，了解更多&gt;</a></h2>
						<h3>订单号：<span>{{orderDetail.id}}</span></h3>
					</div>
					<div class="orderList">
						<div class="orderList1" v-for="goods in orderDetail.orderGoods">
							<h3>{{orderDetail.status}}</h3>
							<div class="clearfix">
								<a href="#" class="fl"><img src="/order_img/g1.jpg"/></a>
								<p class="fl"><a href="#">{{goods.goodsName}}</a><a href="#">¥{{goods.price}}×{{goods.goodsAccount}}</a></p>
							</div>
						</div>
						<div class="orderList1">
							<h3>收货信息</h3>
							<p>姓 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名：<span>{{orderDetail.userRealName}}</span></p>
							<p>联系电话：<span>{{orderDetail.phone}}</span></p>
							<p>收货地址：<span>{{orderDetail.provinceName}}{{orderDetail.cityName}}{{orderDetail.areaName}}
							{{orderDetail.dtaAddress}}</span></p>
						</div>
						<div class="orderList1">
							<h3>支付方式及送货时间</h3>
							<p>支付方式：<span>{{orderDetail.payChannel}}</span></p>
							<p>送货时间：<span>{{orderDetail.deliveryTime}}</span></p>
						</div>
						<div class="orderList1 hei">
							<h3><strong>商品总价：</strong><span>¥{{orderDetail.totalPrice}}</span></h3>
							<p><strong>运费：</strong><span>¥{{orderDetail.freight}}</span></p>
							<p><strong>订单金额：</strong><span>¥99</span></p>
							<p><strong>实付金额：</strong><span>¥99</span></p>
						</div>
						
					</div>
					
					
				</div>
			</div>
		</div>
		
		
		<!--返回顶部-->
		<jsp:include page="commn/gtop.jsp"></jsp:include>
		
		<jsp:include page="commn/footer.jsp"></jsp:include>
		
<script src="js/jquery.min.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript" src="/js/vue.js"></script>
<script type="text/javascript" src="/js/json2.js"></script>
<script type="text/javascript" charset="utf-8">

var orderId = '${orderId}';
var goodsShow = {};

$(document).ready(function() {
	goodsShow = new Vue({
		el : '#goodsShow',
		data : {
			orderDetail : []
		},
		
	methods : {
		
		cancelOrder:function(id) {
			cancelOrder(id);
		},
		
	}
	
	});
	listOrderDetail();
});
	
	function listOrderDetail() {
		$.ajax({
		    url:'/userOrder/listOrderDetail.do?',
		    type:'POST', //GET
		    async:true,    //或false,是否异步
		    data:{
		    	orderId : orderId,
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
		        
		        goodsShow.orderDetail = data.data;
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
