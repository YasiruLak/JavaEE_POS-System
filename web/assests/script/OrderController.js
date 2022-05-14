

function setCurrentDate(){
    let orderDate = $('#txtOrderDate');
    let today = new Date();
    let dd = String(today.getDate()).padStart(2, '0');
    let mm = String(today.getMonth() + 1).padStart(2, '0');
    let yyyy = today.getFullYear();
    today = dd + '/' + mm + '/' + yyyy;
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