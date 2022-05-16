// function generateOrderID() {
//     $("#txtOrderID").val("O00-0001");
//     $.ajax({
//         url: "order",
//         method: "GET",
//         success: function (resp) {
//             for (const orders of resp.data){
//                 let orderId = orders.oId;
//                 let tempId = parseInt(orderId.split("-")[1]);
//                 tempId = tempId+1;
//                 if (tempId <= 9){
//                     $("#txtOrderID").val("O00-000"+tempId);
//                 }else if (tempId <= 99) {
//                     $("#txtOrderID").val("O00-00" + tempId);
//                 }else if (tempId <= 999){
//                     $("#txtOrderID").val("O00-0" + tempId);
//                 }else {
//                     $("#txtOrderID").val("O00-"+tempId);
//                 }
//             }
//
//         }
//     });
// }
//
// generateOrderID();

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

function loadItemComboBoxData() {
    $("#txtOrderItemCode").empty();
    $("#txtOrderItemCode").append($("<option></option>").attr("value", 0).text("Select Item"));
    console.log("Enter")
    let count = 0;
    $.ajax({
        url:"item?option=GETALL",
        method:"GET",
        success:function (res){
            console.log(res);
            for (const item of res.data){
                $("#txtOrderItemCode").append($("<option></option>").attr("value", count).text(item.itemCode));
                count++;
            }
        },
        error:function (ob, textStatus, error) {
            alert(textStatus);
        }
    });

}

function loadCustomerComboBoxData(){
    $("#txtOrderCusID").empty();
    $("#txtOrderCusID").append($("<option></option>").attr("value", 0).text("Select Customer"));
    console.log("Enter")
    let count = 0;
    $.ajax({
        url:"customer?option=GETALL",
        method:"GET",
        success:function (res){
            console.log(res);
            for (const customer of res.data){
                $("#txtOrderCusID").append($("<option></option>").attr("value", count).text(customer.id));
                count++;
            }
        },
        error:function (ob, textStatus, error) {
            alert(textStatus);
        }
    });
}

$("#txtOrderCusID").click(function (){

    let id = $("#txtOrderCusID option:selected").text();
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

    console.log(1);

    let id = $("#txtOrderItemCode option:selected").text();
    let name = $("#txtOrderItemName").val();
    let qtyOnHand = $("#txtOrderItemQtyOnHand").val();
    let price = $("#txtOrderItemPrice").val();

    $.ajax({
        url:"item?option=GETALL",
        method:"GET",

        success:function (resp){
            console.log(2);
            for (const item of resp.data){
                if (item.itemCode == id){

                    console.log(3);
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

        $("#txtOrderItemCode option:selected").text(itemCode);
        $("#txtOrderItemName").val(itemName);
        $("#txtOrderItemPrice").val(unitPrice);
        $("#txtQty").val(qty);
        $("#total option:selected").text(total);

    });

});

var itemCode;
var itemName;
var itemPrice;
var itemQty;
var itemOrderQty;

$("#addToCartTable").empty();
function loadOrderDetail() {

    itemCode = $("#txtOrderItemCode option:selected").text();
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
    $("#total option:selected").text(total);
}

function updateManageTotal(prvTotal,nowTotal) {
    total -= prvTotal;
    total += nowTotal;

    $("#total option:selected").text(total);
}

function manageQuantity(prevQty,nowQty){
    var prevQty = parseInt(prevQty);
    var nowQty = parseInt(nowQty);
    var availableQty = parseInt($("#txtOrderItemQtyOnHand").val());

    availableQty = availableQty + prevQty;
    availableQty = availableQty - nowQty;

    $("#txtOrderItemQtyOnHand").val(availableQty);
}