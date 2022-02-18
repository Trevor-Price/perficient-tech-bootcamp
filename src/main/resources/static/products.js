function populateProducts(products){
	products.map(function(product){
		var item = document.createElement("div");
		var stockMessage = (product.quantityAvailable > 0 && product.quantityAvailable <= 5) 
			? `Only ${product.quantityAvailable} left!`
			: (product.quantityAvailable <= 0) ? "Out of stock" : "";
		item.className="product"
		item.innerHTML =
			`
			<div class="product-field" id="productImage"> 
				<img src=${product.imageurl} width=100px height=100px > <b>${stockMessage}</b>
			</div>
			<div class="product-field" id="productDescription"> 
				${product.description}
			</div>
			<div class="product-field" id="productBrandName"> 
				By <i>${product.brand}</i>
			</div>
			<div class="product-field" id="productPrice"> 
				$${product.price} <button type="button" class="btn btn-primary">Buy</button>
			</div>
			`;
		$("#product-container").append(item);
	});
}

get("http://localhost:8080/api/products", populateProducts );
