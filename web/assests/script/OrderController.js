function generateOrderID() {
    $("#txtOrderID").val("O00-0001");
    $.ajax({
        url: "order",
        method: "GET",
        success: function (resp) {
            for (const orders of resp.data){
                let orderId = orders.oId;
                let tempId = parseInt(orderId.split("-")[1]);
                tempId = tempId+1;
                if (tempId <= 9){
                    $("#txtOrderID").val("O00-000"+tempId);
                }else if (tempId <= 99) {
                    $("#txtOrderID").val("O00-00" + tempId);
                }else if (tempId <= 999){
                    $("#txtOrderID").val("O00-0" + tempId);
                }else {
                    $("#txtOrderID").val("O00-"+tempId);
                }
            }

        }
    });
}

generateOrderID();

function setCurrentDate(){
    let orderDate = $('#txtOrderDate');
    let today = new Date();
    let dd = String(today.getDate()).padStart(2, '0');
    let mm = String(today.getMonth() + 1).padStart(2, '0');
    let yyyy = today.getFullYear();
    today = yyyy + '-' + mm + '-' + dd;
    orderDate.val(today);
}

setCurrentDate();

function loadItemComboBoxData(value) {
    $("#txtOrderItemCode").append(value);

}

function loadCustomerComboBoxData(value){
    $("#txtOrderCusID").append(value);
}

$("#txtOrderCusID").click(function (){

    let id = $("#txtOrderCusID").val();
    let name = $("#txtOrderCusName").val();
    let address = $("#txtOrderCusAddress").val();
    let contact = $("#txtOrderCusContact").val();


    $.ajax({
        url: "customer?option=GETALL",
        method: "GET",
        success: function (resp) {
            for (const customer of resp.data) {

                if (customer.id == id){

                    name = customer.name;
                    address = customer.address;
                    contact = customer.contact;

                    $("#txtOrderCusName").val(name);
                    $("#txtOrderCusAddress").val(address);
                    $("#txtOrderCusContact").val(contact);
                }

            }
        }
    });

});


$("#txtOrderItemCode").click(function (){

    let id = $("#txtOrderItemCode").val();
    let name = $("#txtOrderItemName").val();
    let qtyOnHand = $("#txtOrderItemQtyOnHand").val();
    let price = $("#txtOrderItemPrice").val();

    $.ajax({
        url:"item?option=GETALL",
        method:"GET",
        success:function (resp){
            for (const item of resp.data){
                if (item.itemCode == id){
                    name = item.name;
                    qtyOnHand = item.qtyOnHand;
                    price = item.price;

                    $("#txtOrderItemName").val(name);
                    $("#txtOrderItemQtyOnHand").val(qtyOnHand);
                    $("#txtOrderItemPrice").val(price);
                }
            }
        }
    });
});

var tableRow;

$("#btnAddToCart").click(function () {
    let duplicate = false;

    for (let i = 0; i < $("#addToCartTable tr").length; i++) {
        if($("#txtOrderItemCode option:selected").text() == $("#addToCartTable tr").children(':nth-child(1)')[i].innerText){
            duplicate = true;
        }
    }

    if (duplicate != true){
        loadOrderDetail();
        minusQty($("#txtQty").val());
        manageTotal($("#txtQty").val() * $("#txtOrderItemPrice").val());

    }else if (duplicate == true){
        manageQuantity(tableRow.children(':nth-child(4)').text(),$("#txtQty").val());
        $(tableRow).children(':nth-child(4)').text($("#txtQty").val());

        updateManageTotal(tableRow.children(':nth-child(5)').text(),$("#txtQty").val() * $("#txtOrderItemPrice").val());
        $(tableRow).children(':nth-child(5)').text($("#txtQty").val() * $("#txtOrderItemPrice").val());
    }

    $("#addToCartTable>tr").click('click',function () {

        tableRow = $(this);
        let itemCode = $(this).children(":eq(0)").text();
        let itemName = $(this).children(":eq(1)").text();
        let unitPrice = $(this).children(":eq(2)").text();
        let qty = $(this).children(":eq(3)").text();
        let total = $(this).children(":eq(4)").text();

        $("#txtOrderItemCode").val(itemCode);
        $("#txtOrderItemName").val(itemName);
        $("#txtOrderItemPrice").val(unitPrice);
        $("#txtQty").val(qty);

    });

});

var itemCode;
var itemName;
var itemPrice;
var itemQty;
var itemOrderQty;

$("#addToCartTable").empty();
function loadOrderDetail() {

    itemCode = $("#txtOrderItemCode").val();
    itemName = $("#txtOrderItemName").val();
    itemPrice = $("#txtOrderItemPrice").val();
    itemQty = $("#txtOrderItemQtyOnHand").val();
    itemOrderQty = $("#txtQty").val();


    let total;

    total = itemPrice * itemOrderQty;

    $("#addToCartTable").append("<tr>" +
        "<td>" + itemCode + "</td>" +
        "<td>" + itemName + "</td>" +
        "<td>" + itemPrice + "</td>" +
        "<td>" + itemOrderQty + "</td>" +
        "<td>" + total + "</td>" +
        "</tr>");
}

function minusQty(orderQty){
    var minusQty = parseInt(orderQty);
    var manageQty = parseInt($("#txtOrderItemQtyOnHand").val());

    manageQty = manageQty - minusQty;

    $("#txtOrderItemQtyOnHand").val(manageQty);
}

var total = 0;
function manageTotal(amount){
    total = amount;
    $("#total").val(total);
}

function updateManageTotal(prvTotal,nowTotal) {
    total -= prvTotal;
    total += nowTotal;

    $("#total").val(total);
}

function manageQuantity(prevQty,nowQty){
    var prevQty = parseInt(prevQty);
    var nowQty = parseInt(nowQty);
    var availableQty = parseInt($("#txtOrderItemQtyOnHand").val());

    availableQty = availableQty + prevQty;
    availableQty = availableQty - nowQty;

    $("#txtOrderItemQtyOnHand").val(availableQty);
}