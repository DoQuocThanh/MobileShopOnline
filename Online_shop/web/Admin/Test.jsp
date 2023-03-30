<div class="card-body">
    <div class="table-responsive">
        <table class="table table-bordered" width="100%">
            <thead>
                <tr>

                    <th>OrderID</th>
                    <th>ProductName</th>
                    <th>Picture</th>
                    <th>Quantity</th>
                    <th>Discount</th>
                    <th>Unitprice</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${list}" var="o"> 
                    <tr>
                        <td>${o.orderID}</td>
                        <td>${o.productName}</td>
                        <td>${o.picture}</td>
                        <td>${o.quantity}</td>
                        <td>${o.discount}</td>
                        <td>${o.unitprice}</td>
                    </tr>
                </c:forEach>

            </tbody>
        </table>
    </div>
</div>