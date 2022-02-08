var products = [];

function getProducts(){
	
	var xhttp = new XMLHttpRequest();
	
	xhttp.onreadystatechange = function() {
		if(this.readyState == 4 && this.status == 200){
			var response = JSON.parse(this.responseText);
			console.log(response);
		}
	}
	
	xhttp.open("GET", "http://localhost:8080/api/products", true);
	xhttp.send();
}

getProducts();