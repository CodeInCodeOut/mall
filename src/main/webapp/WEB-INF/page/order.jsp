<%@page contentType="text/html; charset=utf-8" %>
<!DOCTYPE html>
<html>
	<head lang="en">
		<meta charset="utf-8" />
		<title>order</title>
		<link rel="stylesheet" type="text/css" href="/order_css/public.css"/>
		<link rel="stylesheet" type="text/css" href="/order_css/proList.css" />
		<link rel="stylesheet" type="text/css" href="/order_css/mygxin.css" />
	</head>
	<body>
		<div class="order cart mt">
			<!-----------------site------------------->
			<div class="site">
				<p class="wrapper clearfix">
					<span class="fl">订单确认</span>
					<img class="top" src="/order_img/temp/cartTop02.png">
				</p>
			</div>
			<!-----------------orderCon------------------->
			
			<div class="orderCon wrapper clearfix">
				<div class="orderL fl">
					<!--------h3---------------->
					<h3>收件信息<a href="/userOrder/toAddress.do?####" class="fr">新增地址</a></h3>
					<!--------addres---------------->
				
					<div class="addres clearfix" id ="addressShow">
						<div class="addre fl on"  v-for="(address, index) in addressArray" v-bind:id="'xx'+index" v-on:click="changeClass(index)"
						v-if="address.isDefauAdress ==1">
							<div class="tit clearfix">
								<p class="fl">{{address.userRealName}}
									<span class="default">[默认地址]</span>
								</p>
								<p class="fr">
									<a href="#">删除</a>
									<span>|</span>
									<a href="#" class="edit">编辑</a>
								</p>
							</div>
							<div class="addCon">
								<p>{{address.provinceName}}&nbsp;{{address.cityName}}&nbsp;{{address.areaName}}&nbsp;{{address.dtaAddress}}</p>
								<p>{{address.phone}}</p>
							</div>
						</div>
					
						<div class="addre fl" v-for="(address, index) in addressArray" v-bind:id="'xx'+index" v-on:click="changeClass(index)"
						v-if="address.isDefauAdress != 1">
							<div class="tit clearfix">
								<p class="fl">{{address.userRealName}}
									<span class="default">[默认地址]</span>
								</p>
								<p class="fr">
									<a href="#">删除</a>
									<span>|</span>
									<a href="#" class="edit">编辑</a>
								</p>
							</div>
							<div class="addCon">
								<p>{{address.provinceName}}&nbsp;{{address.cityName}}&nbsp;{{address.areaName}}&nbsp;{{address.dtaAddress}}</p>
								<p>{{address.phone}}</p>
							</div>
						</div>
					</div>
					<h3>支付方式</h3>
					<!--------way---------------->
					<div class="way clearfix" id="payChannelDiv">
						<img id="pay1" class="on" src="/order_img/temp/way01.jpg"> 
						<img id="pay2" src="/order_img/temp/way02.jpg"> 
						<img id="pay3" src="/order_img/temp/way03.jpg"> 
						<img id="pay4" src="/order_img/temp/way04.jpg"> s 
					</div>
					<h3>选择快递</h3>
					<!--------dis---------------->
					<div class="dis clearfix">
						<span class="on">顺风快递</span>
						<span>百世汇通</span>
						<span>圆通快递</span>
						<span>中通快递</span>
					</div>
				</div>
			<div id="goodsShow">
				<div class="orderR fr">
					<div class="msg" >
						<h3>订单内容<a href="cart.html" class="fr">返回购物车</a></h3>
						<!--------ul---------------->
						<ul class="clearfix" v-for="goods in orderDetail.goods">
							<li class="fl">
								<img src="/order_img/temp/order01.jpg">
							</li>
							<li class="fl">
								<p>{{goods.goodsName}}</p>
								<p>{{goods.goodsDesc}}</p>
								<p>数量：{{goods.goodsAccount}}</p>
							</li>
							<li class="fr">单价￥{{goods.thisTotalPrice}}</li>
							<a class="del" href="##" v-on:click="cancelOrder(goods.id)">取消订单</a>
						</ul>
					</div>
					<!--------tips---------------->
					<div class="tips">
						<p><span class="fl">商品金额：</span><span class="fr" id="totalMoney">￥{{orderDetail.totalPrice}}</span></p>
						<p><span class="fl">优惠金额：</span><span class="fr">￥{{orderDetail.preferentialMoney}}</span></p>
						<p><span class="fl">运费：</span><span class="fr">免运费{{orderDetail.freight}}</span></p>
					</div>
					<!--------tips count---------------->
					<div class="count tips">
						<p><span class="fl">合计：</span><span class="fr">￥{{orderDetail.totalPrice}}</span></p>
					</div>
				</div>
						<a href="####" class="pay" onclick="pay()">去支付</a>
			    </div>
				</div>
			</div>
				
		<!--编辑弹框-->
		<!--遮罩-->
		<div class="mask"></div>
		<div class="adddz editAddre">
			<form action="#" method="get">
				<input type="text" placeholder="姓名" class="on" />
				<input type="text" placeholder="手机号" />
				<div class="city">
					<select name="">
						<option value="省份/自治区">省份/自治区</option>
					</select>
					<select>
						<option value="城市/地区">城市/地区</option>
					</select>
					<select>
						<option value="区/县">区/县</option>
					</select>
					<select>
						<option value="配送区域">配送区域</option>
					</select>
				</div>
				<textarea name="" rows="" cols="" placeholder="详细地址"></textarea>
				<input type="text" placeholder="邮政编码" />
				<div class="bc">
					<input type="button" value="保存" />
					<input type="button" value="取消" />
				</div>
			</form>
		</div>
		<!--返回顶部-->
		<div class="gotop">
			<a href="cart.html">
			<dl>
				<dt><img src="/order_img/gt1.png"/></dt>
				<dd>去购<br />物车</dd>
			</dl>
			</a>
			<a href="#" class="dh">
			<dl>
				<dt><img src="/order_img/gt2.png"/></dt>
				<dd>联系<br />客服</dd>
			</dl>
			</a>
			<a href="mygxin.html">
			<dl>
				<dt><img src="/order_img/gt3.png"/></dt>
				<dd>个人<br />中心</dd>
			</dl>
			</a>
			<a href="#" class="toptop" style="display: none;">
			<dl>
				<dt><img src="/order_img/gt4.png"/></dt>
				<dd>返回<br />顶部</dd>
			</dl>
			</a>
			<p>400-800-8200</p>
		</div>
		
<script type="text/javascript" src="/js/jquery-mini.js"></script>
<script type="text/javascript" src="/js/vue.js"></script>
<script type="text/javascript" src="/js/json2.js"></script>
<script type="text/javascript" charset="utf-8">
var orderId = '${orderId}';
var goodsShow = {};
var showAddress = {};

var checkedPayChannel = 4;
var checkedAddressId = -1;
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
	
	showAddress = new Vue({
		el : '#addressShow',
		data : {
			addressArray : []
		},
		
		methods:{
			changeClass : function(index) {
				$('#xx' + index).addClass("on").siblings().removeClass("on");
				checkedAddressId = this.addressArray[index].id;
			}
		}
		
	});
	listUserAddress();
	listOrder();
	getPayChannel();
});



function getPayChannel() {
	$.ajax({
	    url:'/pay/getPayChannle.do',
	    type:'POST', //GET
	    async:true,    //或false,是否异步
	    data: {
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
	        
	        var channels = data.data;
	       
			var msg = '';
			for (var i=0;i<channels.length;i++) {
				var channel = channels[i];
				if (channel.payCode === 2) {
					msg += '<img id="'+channel.payCode+'" src="/order_img/temp/way01.jpg"> ';
				}
				if (channel.payCode === 3) {
					msg += '<img id="'+channel.payCode+'" src="/order_img/temp/way02.jpg"> ';
				}
				if (channel.payCode === 4) {
					msg += '<img id="'+channel.payCode+'" class="on" src="/order_img/temp/way05.png"> ';
				}
			}
			
			$('#payChannelDiv').html(msg);
		    payChannelChange();
	        
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

function payChannelChange() {
	$('.way img').click(function() {
		$(this).addClass("on").siblings().removeClass("on");
		checkedPayChannel = $(this).attr("id");
		alert(checkedPayChannel);
	});
	
	$('.dis span').click(function() {
		$(this).addClass("on").siblings().removeClass("on");
	});
}

function pay() {
	$.ajax({
	    url:'/userOrder/pay.do',
	    type:'POST', //GET
	    async:true,    //或false,是否异步
	    data: {
	    	orderId : orderId,
	    	payChannel :checkedPayChannel,
	    	addressId: checkedAddressId
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
	        
	       alert("订单生成OK，即将去支付" + data.data.payOrderUrl);
	       window.location.href=data.data.payOrderUrl;
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

function listOrder() {
$.ajax({
    url:'/userOrder/listOrder.do',
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

function listUserAddress() {
	$.ajax({
	    url:'/userAddress/listUserAddress.do', 
	    type:'POST', //GET
	    async:true,    //或false,是否异步
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
	        showAddress.addressArray = data.data;
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

function cancelOrder(id) {
	if (confirm("你确信要取消订单吗？")) {
		$.ajax({
            url:'/userOrder/cancelOrder.do',
            type:'POST', //GET
            async:true,    //或false,是否异步
            data:{
            	orderId : id,
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
               window.location.href='/userOrder/toOrder.do?'; 
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
						<a href="#2" class="fl"><img src="/order_img/foot1.png"/></a>
						<span class="fl">7天无理由退货</span>
					</div>
					<div class="clearfix">
						<a href="#2" class="fl"><img src="/order_img/foot2.png"/></a>
						<span class="fl">15天免费换货</span>
					</div>
					<div class="clearfix">
						<a href="#2" class="fl"><img src="/order_img/foot3.png"/></a>
						<span class="fl">满599包邮</span>
					</div>
					<div class="clearfix">
						<a href="#2" class="fl"><img src="/order_img/foot4.png"/></a>
						<span class="fl">手机特色服务</span>
					</div>
				</div>
			</div>
			<p class="dibu">最家家居&copy;2013-2017公司版权所有 京ICP备080100-44备0000111000号<br />
			违法和不良信息举报电话：188-0130-1238，本网站所列数据，除特殊说明，所有数据均出自我司实验室测试</p>
		</div>
		
	</body>
</html>
