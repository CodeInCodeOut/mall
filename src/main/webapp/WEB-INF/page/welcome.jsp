<%@page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html"; charset="utf-8" >
	<title>京西(JX.COM)-综合网购首选-正品低价、品质保障、配送及时、轻松购物！</title>
	<link rel="stylesheet" href="css/reset.css" type="text/css">
	<link rel="stylesheet" href="css/style.css" type="text/css">
</head>
<body>
<div id="head">
	<div class="head_top">
		<div class="wrap clearfix">
			<div class="leftArea">
				<a href="#" id="collection">收藏京西</a>
			</div>
			
			<shiro:guest>
			<div class="rightArea">欢迎来到京西网！<a href="user/toLogin.do?#">[登录]</a><a href="user/toRegist.do?#">[免费注册]</a></div>
			</shiro:guest>
			<shiro:user>
			<div class="rightArea">欢迎[<shiro:principal/>]来到京西网！<a href="/userOrder/toMyOrder.do"></a>我的订单</div>
			</shiro:user>
			
			
		</div>
	</div>
	<div class="search">
		<div class="wrap">
			<div class="logo">
				<a href="#"><img src="images/logo.png" alt="京西商城"></a>
			</div>
			<div class="search_box">
				<input type="text" id="search_input">
				<input type="submit" value="搜 索" id="search_btn">
			</div>
			<div class="shop_car">
				<span class="car">购物车</span>
				<span class="num_text">0</span>
			</div>
		</div>
	</div>
	<div class="nav">
		<div class="wrap">
			<div class="allCommodity">
				<h3>全部商品分类</h3>
			</div>
		</div>
	</div>
<div id="goodsShow">
			<div class="shopArea" v-for="Element in category">
		<div class="wrap">
			<div class="shop_title">
				<h3>{{Element.sortName}}</h3>
			</div>
			<div class="main clearfix">
				<div class="shop_banner">
					<img src="images/ad01.jpg" alt="shop_banner">
				</div>
				<div class="shop_list">
					<div class="shoplist_box clearfix">
					
						<div class="shopItem_top" v-for="(goods, index) in Element.goodsList" v-if="index < 4">
						<a v-bind:href="'display/toGoodsIntroduce.do?goodsId=' + goods.id ">
							<div class="shop_img"><img src="images/HTC.jpg" alt="商品"></div>
						</a>
							<h4>{{goods.goodsName}}</h4>
							<p>{{goods.price}}元</p>
						</div>
						<div class="shopItem_bottom" v-for="(goods, index) in Element.goodsList" v-if="index >= 4">
						<a v-bind:href="'display/toGoodsIntroduce.do?goodsId=' + goods.id ">
							<div class="shop_img"><img src="images/NFC.jpg" alt="商品"></div>
						</a>
							<div class="shop_text">
								<p>{{goods.goodsName}}</p>
								<span>￥{{goods.price}}</span>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
	
<script type="text/javascript" src="/js/jquery-mini.js"></script>
<script type="text/javascript" src="/js/vue.js"></script>
<script type="text/javascript" charset="utf-8">
var goodsShow = {};
	$(document).ready(function() {
		goodsShow = new Vue({
			el : '#goodsShow',
			data : {
				category : []
			}
		});
		
		listSort();
	});
	
function listSort() {
	$.ajax({
        url:'/display/ListSort.do',
        type:'POST', //GET
        async:true,    //或false,是否异步
        data:{
        	
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
            
            goodsShow.category = data.data;
            var len = data.data.length;
            for (var i=0; i<len;i++) {
            	var cg = goodsShow.category[i];
            	listGoods(cg, i);
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
	
	function listGoods(cg, index) {
			$.ajax({
                url:'/display/listGoods.do',
                type:'POST', //GET
                async:true,    //或false,是否异步
                data:{
                	sortId:cg.id,
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
                     
                     cg["goodsList"] = data.data;
                     goodsShow.category.splice(index, 1, cg)
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


<div id="footer">
	<p>慕课简介|慕课公告| 招纳贤士| 联系我们|客服热线：400-675-1234</p>
	<p>Copyright © 2006 - 2014 慕课版权所有   京ICP备09037834号   京ICP证B1034-8373号   某市公安局XX分局备案编号：123456789123</p>
	<div class="credit_rating">
		<a href="#"><img src="images/pj.jpg" alt="信用评价"></a>
		<a href="#"><img src="images/pj.jpg" alt="信用评价"></a>
		<a href="#"><img src="images/pj.jpg" alt="信用评价"></a>
		<a href="#"><img src="images/pj.jpg" alt="信用评价"></a>
	</div>
</div>

</body>
</html>
