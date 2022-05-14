

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
