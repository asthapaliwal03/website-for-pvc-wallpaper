/**
 * 
 */
//quantity add to cart
function add_to_cart(pid,pname,price){
	let cart=localStorage.getItem("cart");
	if(cart==null){
		let products=[];
		let product={"proid":pid,"pname":pname,"price":price,"quantity":1};
		products.push(product);
		localStorage.setItem("cart",JSON.stringify(products));
	}
	else{
		let pcart=JSON.parse(cart);
		let oproduct=pcart.find( (item) => item.proid==pid );
		if(oproduct){
			oproduct.quantity=oproduct.quantity+1;
			pcart.map((item)=>{
				if(oproduct.proid==item.proid){
					item.quantity=oproduct.quantity;
				}
			})
			localStorage.setItem("cart",JSON.stringify(pcart));
			console.log("product quantity increase");
			
		}
		else{
			let product={"proid":pid,"pname":pname,"price":price,"quantity":1};
			pcart.push(product);
			localStorage.setItem("cart",JSON.stringify(pcart))
			console.log("new product added");
		}
		
	}
	updateCart();
}
//update cart
function updateCart(){
	let strcart=localStorage.getItem("cart");
	let cart=JSON.parse(strcart);
	if(cart== null || cart.length==0){
		console.log("empty card")
		$(".cart-no-item").html("0");
		$(".cart-body").html("<h3>Cart is empty</h3>");
		$(".check-out").attr('disabled',true);
	}else{
		console.log("data stored in card");
		$(".cart-no-item").html(`${cart.length}`);
		let table=`
		<table class='table'>
			<thread class='thead-light'>
			<tr>
			<th>Item Name</th>
			<th>Price</th>
			<th>Quantity</th>
			<th>Total price</th>
			<th>Action</th>
			</tr>
			</thead>
		
		`;
		let totalprice=0;
		cart.map((item)=>{
			table=table+`
				<tr>
					<td>${item.pname}</td>
					<td>${item.price}</td>
					<td>${item.quantity}</td>
					<td>${item.price * item.quantity}</td>
					<td><button class='btn btn-danger' onclick='removeFromCart(${item.proid})'>REMOVE</button></td>
				</tr>
			`
			totalprice=totalprice+item.price * item.quantity;
		})
		
		table=table+`<tr style='font-weight:bold; font-size:17px;'>
			<td colspan='4'>Total Price</td>
			<td >${totalprice}</td>
		</tr>`
		table=table+`</table>`;
		$(".cart-body").html(table);
		$(".check-out").attr('disabled',false);
	}
}
function removeFromCart(pid){
	let cart=JSON.parse(localStorage.getItem('cart'));
	let newcart=cart.filter((item) => item.proid != pid)
	localStorage.setItem('cart',JSON.stringify(newcart))
	updateCart();
	
}

function goToCheckOut(){
	window.location="checkOut.jsp"
}
function gotohomePage(){
	window.location="index.jsp"
}
$(document).ready(function(){
	updateCart();
	});
