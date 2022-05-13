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

function loadCustomerComboBoxData(value){
    $("#txtOrderCusID").append(value);
}

// function loadItemComboBoxData(value){
//     $("#txtOrderItemCode").append(value);
// }