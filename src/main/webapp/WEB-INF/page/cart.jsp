<%@page contentType="text/html; charset=utf-8" %>
<!DOCTYPE html>
<html>
	<head lang="en">
		<meta charset="utf-8" />
		<title>cart</title>
		<link rel="stylesheet" type="text/css" href="/cart_css/public.css"/>
		<link rel="stylesheet" type="text/css" href="/cart_css/proList.css" />
	</head>
	<body>
			<div class="site">
				<p class=" wrapper clearfix">
					<span class="fl">购物车</span>
					<img class="top" src="/img/temp/cartTop01.png">
					<a href="index.html" class="fr">继续购物&gt;</a>
				</p>
			</div>
		<div id="goodsShow">	
			<div class="table wrapper">
				<div class="tr">
					<div>商品</div>
					<div>单价</div>
					<div>数量</div>
					<div>小计</div>
					<div>操作</div>
				</div>
				
				<div class="th" v-for="(goods, index) in shopCart">
					<div class="pro clearfix" >
						<label class="fl">
							<input type="checkbox" v-bind:id="index" v-on:click="goodsChecked" />
    						<span></span>
						</label>
						<a class="fl" href="#">
							<dl class="clearfix">
								<dt class="fl"><img src="/img/temp/cart02.jpg"></dt>
								<dd class="fl">
									<p>{{goods.Element.goodsName}}</p>
									<p>{{goods.Element.goodsName}}</p>
								</dd>
							</dl>
						</a>
					</div>
					<div class="price">￥{{goods.Element.price}}</div>
					<div class="number">
						<p class="num clearfix">
							<img class="fl sub" src="/img/temp/sub.jpg" v-on:click="subAccount(index);">
							<span class="fl" id = "goodsAccount">{{goods.goodsAccount}}</span>
							<img class="fl add" src="/img/temp/add.jpg" v-on:click="plusAccount(index);">
						</p>
					</div>
					<div class="price sAll">￥总价</div>
					<div class="price"><a class="del" href="#####" v-on:click="deleteGoodsShopCartId(goods.id)">删除</a></div>
				<div class="goOn">空空如也~<a href="index.html">去逛逛</a></div>
			 </div>
				<div class="tr clearfix">
					<label class="fl">
						<input class="checkAll" type="checkbox" id="selectAll" onclick="selectAll()"/>
						<span></span>
					</label>
				<p class="fl">
						<a href="#">全选</a>
						<a href="#" class="del">删除</a>
					</p>
					<p class="fr">
						<span>共<small id="sl">0</small>件商品</span>
						<span>合计:&nbsp;<small id="totalAmount">￥0.00</small></span>
						<a href="###" class="count" v-on:click="settlementReady()">结算</a>
					</p>
				</div>
				
		<div class="mask"></div>
			<div class="tipDel">
				<p>确定要删除该商品吗？</p>
				<p class="clearfix">
					<a class="fl cer" href="#">确定</a>
					<a class="fr cancel" href="#">取消</a>
				</p>
			</div>
		</div>	
	</div>
<script type="text/javascript" src="/js/jquery-mini.js"></script>
<script type="text/javascript" src="/js/vue.js"></script>
<script type="text/javascript" src="/js/json2.js"></script>
<script type="text/javascript" charset="utf-8">


var goodsShow = {};
var buyGoodsIndexArray = [];
$(document).ready(function() {
	goodsShow = new Vue({
		el : '#goodsShow',
		data : {
			shopCart : []
		}, 
		methods : {
			deleteGoodsShopCartId:function(id) {
				deleteCartId(id);
			}, 
			
			subAccount : function(index) {
				var sc = this.shopCart[index];
				if (sc.goodsAccount <= 1) {
					return;
				}
				sc.goodsAccount -= 1;
				countAmount();
			},
			
			plusAccount :function(index) {
				var sc = this.shopCart[index];
				sc.goodsAccount += 1;
				countAmount();
			},
			
			goodsChecked :function() {
				countAmount();
			},
			
			settlementReady : function() {
				settlementReady();
			},
			
		}
	});
	
	listShopCart();
});


function settlementReady() {
	var buyGoodsArray = [];
	$('.th :checkbox').each(function () {
		if ($(this).is(':checked')) {
		var attrValue = $(this).attr('id');
		buyGoodsArray.push(attrValue);
		}
	});
	
	var jsonObj = [];
	for (var i = 0; i < buyGoodsArray.length; i ++) {
		var index = buyGoodsArray[i];
		var cart = goodsShow.shopCart[index];
		var stringJson = {shopCartId : cart.id, goodsId : cart.goodsId, goodsAccount : cart.goodsAccount}
		jsonObj.push(stringJson);
		
	}
	
	var reqjson = JSON.stringify(jsonObj);
	settlement(reqjson)
}

function settlement(reqjson) {
	$.ajax({
        url:'/userOrder/createOrder4JsonBody.do', 
        type:'POST', //GET
        async:true,    //或false,是否异步
        data: reqjson,
	    contentType:"application/json",
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
            alert('success');
            data.data.id;
            window.location='/userOrder/toOrder.do?orderId=' + data.data.id;
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

function settlementl(reqjson) {
	$.ajax({
        url:'/userOrder/createOrder4Json.do', 
        type:'POST', //GET
        async:true,    //或false,是否异步
        data : {
        	buyMsg : reqjson
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
            alert('success');
            window.location='/userOrder/toOrder.do###';
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


function listShopCart() {
$.ajax({
    url:'/shopCart/cart.do',
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
        
        goodsShow.shopCart = data.data;
        var len = data.data.length;
        for (var i=0; i<len;i++) {
        	var cg = goodsShow.shopCart[i];
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

function selectAll() {
	$('.th :checkbox').each(function () {
		if ($('#selectAll').is(':checked')) {
			$(this).prop('checked', true);
		} else {
			$(this).prop('checked', false);
		}
	});
	
	countAmount();
}


function countAmount() {
	var goodsArray = [];
	$('.th :checkbox').each(function () {
		if ($(this).is(':checked')) {
		var attrValue = $(this).attr('id');
		goodsArray.push(attrValue);
		
		}
	});
	
	var total = 0;
	for (var i = 0; i < goodsArray.length; i ++) {
		var index = goodsArray[i];
		var cart = goodsShow.shopCart[index];
		var price = cart.Element.price;
		var id = cart.Element.id;
		var num = cart.goodsAccount;
		total += num * price;
	}
	$('#totalAmount').html(total);
}




function listGoods(cg, index) {
		$.ajax({
            url:'/shopCart/getGoodsInShopCart.do',
            type:'POST', //GET
            async:false,    //或false,是否异步
            data:{
            	goodsId:cg.goodsId,
            	goodsAccount:cg.goodsAccount,
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
                 
                 goodsShow.$set(cg, "Element", data.data);
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

function deleteCartId(id) {
			if (confirm("你确信要删除此商品吗？")) {
				$.ajax({
	                url:'/shopCart/deleteGoodsShopCartId.do',
	                type:'POST', //GET
	                async:true,    //或false,是否异步
	                data:{
	                	shopCartId : id,
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
	                    
	                    alert('删除成功');
	                   window.location.href='/shopCart/toCart.do?'; 
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
			} else {
				alert('删除失败');
			}
	
}





</script>


		<!--footer-->
		<div class="footer">
			<div class="top">
				<div class="wrapper">
					<div class="clearfix">
						<a href="#2" class="fl"><img src="/img/foot1.png"/></a>
						<span class="fl">7天无理由退货</span>
					</div>
					<div class="clearfix">
						<a href="#2" class="fl"><img src="/img/foot2.png"/></a>
						<span class="fl">15天免费换货</span>
					</div>
					<div class="clearfix">
						<a href="#2" class="fl"><img src="/img/foot3.png"/></a>
						<span class="fl">满599包邮</span>
					</div>
					<div class="clearfix">
						<a href="#2" class="fl"><img src="/img/foot4.png"/></a>
						<span class="fl">手机特色服务</span>
					</div>
				</div>
			</div>
			<p class="dibu">最家家居&copy;2013-2017公司版权所有 京ICP备080100-44备0000111000号<br />
			违法和不良信息举报电话：188-0130-1238，本网站所列数据，除特殊说明，所有数据均出自我司实验室测试</p>
		</div>
		
	</body>
</html>
