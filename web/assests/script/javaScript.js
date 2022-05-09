//Hide All Content
$("#dashboardContent").css("display", "block");
$("#customerContent").css("display", "none");
$("#itemContent").css("display", "none");
$("#orderContent").css("display", "none");
$("#purchaseOrderContent").css("display", "none");

$("#linkHome").css('color', 'blue');
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
    $("#linkCustomer").css('color', 'blue');
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
    $("#linkItem").css('color', 'blue');
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
    $("#linkOrder").css('color', 'blue');
    $("#linkPurchaseOrder").css('color', 'black');

    setCurrentDate();
    generateOrderID();

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
    $("#linkPurchaseOrder").css('color', 'blue');

    loadAllOrderDetail();
    loadAllOrder();
});

$("#linkHome").click(function () {
    $("#dashboardContent").css("display", "block");
    $("#customerContent").css("display", "none");
    $("#itemContent").css("display", "none");
    $("#orderContent").css("display", "none");
    $("#purchaseOrderContent").css("display", "none");

    $("#linkHome").css('color', 'blue');
    $("#linkCustomer").css('color', 'black');
    $("#linkItem").css('color', 'black');
    $("#linkOrder").css('color', 'black');
    $("#linkPurchaseOrder").css('color', 'black');

    customerCount();
    itemCount();
    orderCount();
});









