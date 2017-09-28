<%@page contentType="text/html; charset=utf-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html"; charset="utf-8" >
	<title>京西(JX.COM)-综合网购首选-正品低价、品质保障、配送及时、轻松购物！</title>
	<link rel="stylesheet" href="/css1/reset.css" type="text/css">
	<link rel="stylesheet" href="/css1/style.css" type="text/css">
</head>
<body class="grey">
<div id="head">
	<div class="head_top">
		<div class="wrap clearfix">
			<div class="leftArea">
				<a href="#" id="collection">收藏京西</a>
			</div>
			<div class="rightArea">欢迎来到京西网！<a href="###" onclick="toLogin();">[登录]</a><a href="###" onclick="toRegist();">[免费注册]</a></div>
		</div>
	</div>
	<div class="search">
		<div class="wrap">
			<div class="logo">
				<a href="#"><img src="/images/logo.png" alt="京西商城"></a>
			</div>
			<div class="search_box">
				<input type="text" id="search_input">
				<input type="submit" value="搜 索" id="search_btn">
			</div>
			<div class="shop_car">
				<a href="/shopCart/toCart.do"><span class="car">购物车</span>
				<span class="num_text">0</span>
				</a>
			</div>
		</div>
	</div>
<div class="commodity_info wrap clearfix">
	<div class="info_left">
		<div class="commodity_img"><img src="/images/sp.jpg" alt="商品图片"></div>
		<ul class="clearfix">
			<li class="imgOn"><img src="/images/img_list.jpg" alt="缩略图"></li>
			<li><img src="/images/img_list.jpg" alt="缩略图"></li>
			<li><img src="/images/img_list.jpg" alt="缩略图"></li>
			<li><img src="/images/img_list.jpg" alt="缩略图"></li>
			<li><img src="/images/img_list.jpg" alt="缩略图"></li>
		</ul>
	</div>
	<div class="info_right">
		<h3 class="shop_name">${thisGoods.goodsDesc}</h3>
		<dl class="price">
			<dt>京西价</dt>
			<dd><b>￥</b>${thisGoods.price}</dd>
		</dl>
		<dl class="favourable">
			<dt>优惠</dt>
			<dd><span>满换购</span>购ipad加价优惠够配件或USB充电插座</dd>
		</dl>
		<div class="selection">
			<dl class="address">
				<dt>送到</dt>
				<dd><h3>北京市 海淀区 五环内<i></i></h3>有货，可当日出库</dd>
			</dl>
			<dl>
				<dt>库存数量</dt><dt>${thisGoods.goodsRepertory}</dt>
				<dd class="clearfix" id="app">
					<form>
					<div class="num_select">
							<span onclick="minus();">-</span>
					<input type="text" id= "goodsAccount" name="goodsAccount" v-bind:value=a>
							<span onclick="plus();">+</span>
					</div>
					<span class="limit_num">限购<b>9</b>件</span>
				</dd>
			</dl>
		</div>
		<div class="buy">
			<h4 class="selected">已选择<span>“白色|WIFI 16G”</span></h4>
			
			<div class="buy_btn">
			
				<a href="###" onclick="addCart();">加入购物车</a>
				<span class="ver_line"></span>
				<input type="hidden" id = "goodsId" name = "goodsId" value=${thisGoods.id}>
				<a href="###" onclick="toBuy();">立即购买</a>
			</div>
			<p class="notice">注意：此商品可提供普通发票，不提供增值税发票。</p>
		</div>
	</div>
</div>
<div class="main wrap clearfix">
	</div>
	
<script type="text/javascript" src="/js/jquery-mini.js"></script>
<script type="text/javascript" src="/js/vue.js"></script>
<script type="text/javascript" charset="utf-8">

var vm = {};
var vm2 = {};

function plus() {
    vm.plus();
    alert(vm.a);
}

function minus() {

    if (vm.a <= 1) {
        return false;
	}
    vm.minus();
    alert(vm.a);
}

$(document).ready(function () {
    var data = { a: 1 };
	vm = new Vue({
        el: '#app',
        data: data,
		methods: {
            plus: function () {
				this.a++;
            },

            minus: function () {
				this.a--
            }
		}
    })

});


function addCart() {
	$.ajax({
        url:'/shopCart/toIncreaseGoods.do', 
        type:'POST', //GET
        async:true,    //或false,是否异步
        data:{
	        goodsId:$('#goodsId').val(),
	        goodsAccount : $('#goodsAccount').val(),
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

function toBuy() {
	
	$.ajax({
        url:'/userOrder/nowCreateOrder.do', 
        type:'POST', //GET
        async:true,    //或false,是否异步
        data:{
	        goodsId:$('#goodsId').val(),
	        goodsAccount : $('#goodsAccount').val(),
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
            data.data.id;
            window.location = '/userOrder/toOrder.do?orderId=' + data.data.id;
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

function toLogin() {
	   window.location = '/user/toLogin.do?###'
	}
	
function toRegist() {
	   window.location = '/user/toRegist.do?###'
	}



</script>


	

<div id="footer">
	<p>慕课简介|慕课公告| 招纳贤士| 联系我们|客服热线：400-675-1234</p>
	<p>Copyright © 2006 - 2014 慕课版权所有   京ICP备09037834号   京ICP证B1034-8373号   某市公安局XX分局备案编号：123456789123</p>
	<div class="credit_rating">
		<a href="#"><img src="/images/pj.jpg" alt="信用评价"></a>
		<a href="#"><img src="/images/pj.jpg" alt="信用评价"></a>
		<a href="#"><img src="/images/pj.jpg" alt="信用评价"></a>
		<a href="#"><img src="/images/pj.jpg" alt="信用评价"></a>
	</div>
</div>
</body>
</html>