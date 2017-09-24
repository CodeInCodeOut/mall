<%@page contentType="text/html; charset=utf-8" %>
<div class="zuo fl">
					<h3>
						<a href="#"><img src="/img/tx.png"/></a>
						<p class="clearfix"><span class="fl">[羊羊羊]</span><span class="fr">[退出登录]</span></p>
					</h3>
					<div>
						<h4>我的交易</h4>
						<ul>
							<li><a href="####" onclick="toMyCart()">我的购物车</a></li>
							<li><a href="####" onclick="toMyOrder()">我的订单</a></li>
							<li><a href="myprod.html">评价晒单</a></li>
						</ul>
						<h4>个人中心</h4>
						<ul>
							<li><a href="mygxin.html">我的中心</a></li>
							<li><a href="####" onclick="toMyAddress()">地址管理</a></li>
						</ul>
						<h4>账户管理</h4>
						<ul>
							<li  class="on"><a href="mygrxx.html">个人信息</a></li>
							<li><a href="remima.html">修改密码</a></li>
						</ul>
					</div>
				</div>
<script type="text/javascript" src="/js/jquery-mini.js"></script>
<script type="text/javascript" charset="utf-8">
function toMyOrder() {
	window.location.href="/userOrder/myorder.do?#"
}

function toMyCart() {
	window.location.href="/shopCart/toCart.do?#"
}

function toMyAddress() {
	window.location.href="/userOrder/toAddress.do?#"
}

</script>
				