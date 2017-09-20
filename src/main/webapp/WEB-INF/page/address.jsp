<%@page contentType="text/html; charset=utf-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
	<head lang="en">
		<meta charset="utf-8" />
		<title>最家</title>
		<link rel="stylesheet" type="text/css" href="/address_css/public.css"/>
		<link rel="stylesheet" type="text/css" href="/address_css/mygxin.css" />
		<link rel="stylesheet" type="text/css" href="/address_css/jquery-ui-css.css" />
	</head>
	<body>
		<!------------------------------head------------------------------>
		<div class="head ding">
			<div class="wrapper clearfix">
				<div class="clearfix" id="top">
					<h1 class="fl"><a href="index.html"><img src="/img/logo.png"/></a></h1>
					<div class="fr clearfix" id="top1">
					</div>
				</div>				
			</div>
		</div>
		<!------------------------------idea------------------------------>
		<div class="address mt">
			<div class="wrapper clearfix">
				<a href="index.html" class="fl">首页</a>
				<span>/</span>
				<a href="mygxin.html">个人中心</a>
				<span>/</span>
				<a href="address.html" class="on">地址管理</a>
			</div>
		</div>
		
		<!------------------------------Bott------------------------------>
		<div class="Bott">
			<div class="wrapper clearfix">
				<div class="zuo fl">
					<h3>
						<a href="#"><img src="/img/tx.png"/></a>
						<p class="clearfix"><span class="fl">[羊羊羊]</span><span class="fr">[退出登录]</span></p>
					</h3>
					<div>
						<h4>我的交易</h4>
						<ul>
							<li><a href="cart.html">我的购物车</a></li>
							<li><a href='/userOrder/myorder.do?'>我的订单</a></li>
							<li><a href="myprod.html">评价晒单</a></li>
						</ul>
						<h4>个人中心</h4>
						<ul>
							<li><a href="mygxin.html">我的中心</a></li>
							<li class="on"><a href="address.html">地址管理</a></li>
						</ul>
						<h4>账户管理</h4>
						<ul>
							<li><a href="mygrxx.html">个人信息</a></li>
							<li><a href="remima.html">修改密码</a></li>
						</ul>
					</div>
				</div>
				<div class="you fl" id="addressShow">
					<h2>收货地址</h2>
						<div>
							<a href="#2" id="addxad"><img src="/img/jia.png"/></a>
							<span>添加新地址</span>
						</div>
					<div class="add" v-for="(address, index) in addressArray">
						
						<div id="dizhi">
							<p>{{address.userRealName}}</p>
							<p>{{address.phone}}</p>
							<p>{{address.provinceName}}{{address.cityName}}{{address.areaName}}</p>
							<p>{{address.dtaAddress}}</p>
							
							<span v-on:click="modifyAddress(index)">修改</span>
							<span v-on:click="deleteAddress(index)">删除</span>
							
						</div>
					</div>		
				</div>
			</div>
		</div>
		<!--编辑删除弹框-->
		<div id="delete-confirm" title="删除地址">
		
		</div>
		<!--编辑弹框-->
		<!--遮罩-->
		<div class="mask"></div>
		<div class="adddz">
			<form action="#" method="get">
				<input type="text" id="userRealName" placeholder="姓名" class="on" />
				<input type="text" id="phone" placeholder="手机号" />
				<div class="city">
					<select name=""  id="addProvince" onchange="listCity()">
						<option value="省份/自治区" title="省份/自治区/名字">省份/自治区</option>
					</select>
					<select id="addCity" onchange="listArea()">
						<option value="城市/地区"  title="城市/地区/名字">城市/地区</option>
					</select>
					<select id="addArea">
						<option value="区/县" title="区/县/名字">区/县</option>
					</select>
					<select>
						<option value="配送区域">配送区域</option>
					</select>
				</div>
				<textarea name="" rows="" cols="" id="detailAddress" placeholder="详细地址"></textarea>
				<input type="radio" id="xx" name="defAdd" value="2" style="width: 15px;height: 20px">否
				<input type="radio" id="xxx" name="defAdd" value="1" style="width: 15px;height: 20px">是
				<div class="bc">
					<input type="button" id="save" value="保存" />
					<input type="button"  value="取消" />
				</div>
			</form>
		</div>
		<!--返回顶部-->
		<div class="gotop">
			<a href="cart.html">
			<dl>
				<dt><img src="/img/gt1.png"/></dt>
				<dd>去购<br />物车</dd>
			</dl>
			</a>
			<a href="#" class="dh">
			<dl>
				<dt><img src="/img/gt2.png"/></dt>
				<dd>联系<br />客服</dd>
			</dl>
			</a>
			<a href="mygxin.html">
			<dl>
				<dt><img src="/img/gt3.png"/></dt>
				<dd>个人<br />中心</dd>
			</dl>
			</a>
			<a href="#" class="toptop" style="display: none">
			<dl>
				<dt><img src="/img/gt4.png"/></dt>
				<dd>返回<br />顶部</dd>
			</dl>
			</a>
			<p>400-800-8200</p>
		</div>
		<div class="footer">
			<p class="dibu">最家家居&copy;2013-2017公司版权所有 京ICP备080100-44备0000111000号<br />
			违法和不良信息举报电话：400-800-8200，本网站所列数据，除特殊说明，所有数据均出自我司实验室测试</p>
		</div>
		
<script type="text/javascript" src="/js/jquery-mini.js"></script>
<script type="text/javascript" src="/js/jquery-ui-js.js"></script>
<script type="text/javascript" src="/js/vue.js"></script>
<script type="text/javascript" charset="utf-8">
var actionFlag = false;
var addressId = -1;
var showAddress = {};

var deleteAddressId = -1;
$(document).ready(function(){
	
	showAddress = new Vue({
		el : '#addressShow',
		data : {
			addressArray : []
		},
		methods : {
			
			modifyAddress : function(index) {
				$('.mask').show();
				$('.adddz').show();
				
				var address = this.addressArray[index];
				initModify(address);
				actionFlag = false;
			},
			
			deleteAddress : function(index) {
				var address = this.addressArray[index];
				deleteAddressId = index;
				$('#delete-confirm').html('用户：' + address.userRealName + address.dtaAddress);
				$('#delete-confirm').dialog("open");
			
			}
		},
	});
	
	$('#addxad').click(function() {
		initAddAddress();
		
		$('.mask').show();
		$('.adddz').show();
		
		listProvince(); // actionFlag = false 时 增加地址；
		actionFlag = true;
	});
	
	$('.bc>input').click(function() {
		$('.mask').hide();
		$('.adddz').hide();
	});
	
	$('#save').click(function() {
		choiceAction();
		
		
	});
	
	listUserAddress();
	
	initDeleteDialog();
	
});

function choiceAction() {
	if (actionFlag) {
		increaseAddress();
	} else {
		modifyAddress();
	}
}



function initModify(address) {
	alert(address.id);
	listProvince();
	$('#addProvince').val(address.provinceCode);
	
	listCity();
	$('#addCity').val(address.cityCode);
	
	listArea();
	$('#addArea').val(address.areaCode);
	
	$('#userRealName').val(address.userRealName);
	$('#phone').val(address.phone);
	$('#detailAddress').val(address.dtaAddress);
	
	if (1 == address.isDefauAdress) {
		$('input:radio[name="defAdd"][value="1"]').prop('checked', true);
	} else {
		$('input:radio[name="defAdd"][value="2"]').prop('chcked', true);
	}
	
	isDefauAdress : $('input:radio[name="defAdd"]:checked').val();
		
	addressId = address.id; // 注意赋值取值
}


function modifyAddress() {
	$.ajax({
	    url:'/userAddress/modifyAddress.do', 
	    type:'POST', //GET
	    async:true,    //或false,是否异步
	    data:{
	    	
	    	userRealName : $('#userRealName').val(),
	    	phone : $('#phone').val(),
	    	dtaAddress : $('#detailAddress').val(),
	    	provinceCode : $('#addProvince').val(),
	    	cityCode : $('#addCity').val(),
	    	areaCode : $('#addArea').val(),
   			isDefauAdress : $('input:radio[name="defAdd"]:checked').val(),
   			provinceName : $('#addProvince option:selected').attr("title"),
   			cityName : $('#addCity option:selected').attr("title"),
   			areaName : $('#addArea option:selected').attr("title"),
   			id: addressId,
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
	        listUserAddress();
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

function initDeleteDialog() {
	 $( "#delete-confirm" ).dialog({
	        resizable: false,
	        height:140,
	        modal: true,
	        autoOpen: false,
	        buttons: {
	          "删除": function() {
	        	  
	        deleteAddressById();
	        
	           $( this ).dialog( "close" );
	          },
	          "取消": function() {
	           $( this ).dialog( "close" );
	          }
	        }
	 
	      });
	 
}


function deleteAddressById() {
	var deleteAdd = showAddress.addressArray[deleteAddressId];
		$.ajax({
            url:'/userAddress/deleteAddress.do',
            type:'POST', //GET
            async:true,    //或false,是否异步
            data:{
            	addressId : deleteAdd.id
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
                //listUserAddress(); 
            	showAddress.addressArray.splice(deleteAddressId,1);
               
            },
            error:function(xhr,textStatus){
                console.log('错误')
                console.log(xhr)
                console.log(textStatus)
                
              alert('删除失败');
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

function increaseAddress() {
	$.ajax({
	    url:'/userAddress/addUserAddress.do', 
	    type:'POST', //GET
	    async:true,    //或false,是否异步
	    data:{
	    	
	    	userRealName : $('#userRealName').val(),
	    	phone : $('#phone').val(),
	    	dtaAddress : $('#detailAddress').val(),
	    	provinceCode : $('#addProvince').val(),
	    	cityCode : $('#addCity').val(),
	    	areaCode : $('#addArea').val(),
   			isDefauAdress : $('input:radio[name="defAdd"]:checked').val(),
   			provinceName : $('#addProvince option:selected').attr("title"),
   			cityName : $('#addCity option:selected').attr("title"),
   			areaName : $('#addArea option:selected').attr("title")
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
	        listUserAddress();
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


function initAddAddress() {
	$('#userRealName').val('');
	$('#userRealName').val('');
	$('#phone').val('');
	$('#detailAddress').val('');
	$('#addProvince').val('省份/自治区');
	$('#addCity').val('城市/地区');
	$('#addArea').val('区/县');
	
	$('input:radio[name="defAdd"]:checked').val('');
	
}

function listProvince() {
	$.ajax({
        url:'/choiceAddress/choiceProvince.do',
        type:'POST', //GET
        async:false,    //或false,是否异步
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
        	 
        	 var results = data.data;
             var msg = '';
             for (var i = 0; i < results.length; i ++) {
             	var pro = results[i];
             	msg += '<option value="'+ pro.code +'"title="' + pro.name + '">' + pro.name + '</option>';
             }
             $('#addProvince').append(msg);
             $('#addCity').html('<option value="城市/地区">城市/地区</option>');
        	 
           
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


function listCity() {
	$.ajax({
        url:'/choiceAddress/choiceCity.do',
        type:'POST', //GET
        async:false,    //或false,是否异步
        data:{
        	provincecode:$('#addProvince').val(),
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
        	 
        	 var results = data.data;
             var msg = '<option value="城市/地区">城市/地区</option>';
             for (var i = 0; i < results.length; i ++) {
             	var city = results[i];
             	msg += '<option value="'+ city.code + '"title="' + city.name + '">' + city.name + '</option>';
             }
             $('#addCity').html(msg);
             $('#addArea').html('<option value="区/县">区/县</option>');
            
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

function listArea() {
	$.ajax({
        url:'/choiceAddress/choiceArea.do',
        type:'POST', //GET
        async:false,    //或false,是否异步
        data:{
        	citycode:$('#addCity').val(),
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
        	 
        	 var results = data.data;
             var msg = '<option value="区/县">区/县</option>';
             for (var i = 0; i < results.length; i ++) {
             	var area = results[i];
             	msg += '<option value="'+ area.code + '"title="' + area.name + '">' + area.name + '</option>';
             }
             $('#addArea').html(msg);
            
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
