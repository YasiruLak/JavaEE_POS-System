//Hide All Content
$("#dashboardContent").css("display", "block");
$("#customerContent").css("display", "none");
$("#itemContent").css("display", "none");
$("#orderContent").css("display", "none");
$("#purchaseOrderContent").css("display", "none");

$("#linkHome").css('color', 'white');
$("#linkCustomer").css('color', 'black');
$("#linkItem").css('color', 'black');
$("#linkOrder").css('color', 'black');
$("#linkPurchaseOrder").css('color', 'black');


$("#linkCustomer").click(function () {
    $("#dashboardContent").css("display", "none");
    $("#customerContent").css("display", "block");
    $("#itemContent").css("display", "none");
    $("#orderContent").css("display", "none");
    $("#purchaseOrderContent").css("display", "none");

    $("#linkHome").css('color', 'black');
    $("#linkCustomer").css('color', 'white');
    $("#linkItem").css('color', 'black');
    $("#linkOrder").css('color', 'black');
    $("#linkPurchaseOrder").css('color', 'black');


});

$("#linkItem").click(function () {
    $("#dashboardContent").css("display", "none");
    $("#customerContent").css("display", "none");
    $("#itemContent").css("display", "block");
    $("#orderContent").css("display", "none");
    $("#purchaseOrderContent").css("display", "none");

    $("#linkHome").css('color', 'black');
    $("#linkCustomer").css('color', 'black');
    $("#linkItem").css('color', 'white');
    $("#linkOrder").css('color', 'black');
    $("#linkPurchaseOrder").css('color', 'black');
});

$("#linkOrder").click(function () {
    $("#dashboardContent").css("display", "none");
    $("#customerContent").css("display", "none");
    $("#itemContent").css("display", "none");
    $("#orderContent").css("display", "block");
    $("#purchaseOrderContent").css("display", "none");

    $("#linkHome").css('color', 'black');
    $("#linkCustomer").css('color', 'black');
    $("#linkItem").css('color', 'black');
    $("#linkOrder").css('color', 'white');
    $("#linkPurchaseOrder").css('color', 'black');

    loadCustomerComboBoxData();
    loadItemComboBoxData();


});

$("#linkPurchaseOrder").click(function () {
    $("#dashboardContent").css("display", "none");
    $("#customerContent").css("display", "none");
    $("#itemContent").css("display", "none");
    $("#orderContent").css("display", "none");
    $("#purchaseOrderContent").css("display", "block");

    $("#linkHome").css('color', 'black');
    $("#linkCustomer").css('color', 'black');
    $("#linkItem").css('color', 'black');
    $("#linkOrder").css('color', 'black');
    $("#linkPurchaseOrder").css('color', 'white');

    loadAllOrders();
    bindOrderDetailsClickEvent();

});

$("#linkHome").click(function () {
    $("#dashboardContent").css("display", "block");
    $("#customerContent").css("display", "none");
    $("#itemContent").css("display", "none");
    $("#orderContent").css("display", "none");
    $("#purchaseOrderContent").css("display", "none");

    $("#linkHome").css('color', 'white');
    $("#linkCustomer").css('color', 'black');
    $("#linkItem").css('color', 'black');
    $("#linkOrder").css('color', 'black');
    $("#linkPurchaseOrder").css('color', 'black');


});









