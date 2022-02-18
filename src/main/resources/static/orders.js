function populateOrders(orders){
    orders.map(function(order){
        var orderElement = document.createElement("div");
        switch(order.status){
            case "CANCELLED": arrivalMessage=`Cancelled on ${order.cancelDate} at ${order.cancelTime}`; break;
            case "DELAYED": arrivalMessage=`Arriving after ${order.expectedArrivalDate} at ${order.expectedArrivalTime}`; break;
            case "ARRIVED": arrivalMessage=`Arrived on ${order.actualArrivalDate} at ${order.actualArrivalTime}`; break;
            default: arrivalMessage=`Arriving on ${order.expectedArrivalDate} at ${order.expectedArrivalTime}`; break;
        }
        orderElement.className="order"
        orderElement.innerHTML =
            `
            <div class="order-field" id="orderId"> 
                Order Number: ${order.id}
            </div>
            <div class="order-field" id="orderDateTime"> 
                Ordered on ${order.orderDate} at ${order.orderTime}
            </div>
            <div class="order-field" id="expectedArrivalDateTime"> 
                ${arrivalMessage}
            </div>
            <div class="order-field" id="orderId"> 
                Order Status: ${order.status}
            </div>
            `;
        
        $("#order-container").append(orderElement);
    });
}

get("http://localhost:8080/api/orders", populateOrders );

