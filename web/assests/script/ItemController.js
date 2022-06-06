loadAllItem();

$("#btnItemSave").click(function (){
    let itemOb = {
        "itemCode": $("#txtItemCode").val(),
        "itemName": $("#txtItemName").val(),
        "itemQty": $("#txtItemQuantity").val(),
        "itemPrice": $("#txtItemUnitPrice").val()
    };

    if ($("#txtItemCode").val() == '') {
        alert("Can not be Item Code empty");
    } else if ($("#txtItemName").val() == '') {
        alert("Can not be Item Name empty");
    }else if ($("#txtItemQuantity").val() == '') {
        alert("Can not be Item Quantity empty");
    }else if ($("#txtItemUnitPrice").val() == '') {
        alert("Can not be Item Price empty");
    }else{
        $.ajax({
            url:"item",
            method:"POST",
            contentType: "application/json",
            data: JSON.stringify(itemOb),
            success: function (res){
                if (res.status == 200){
                    loadAllItem();
                    alert(res.message);
                    resetItem();
                }else{
                    alert(res.data);
                }
            },
            error: function (ob, textStatus, error) {
                console.log(ob);
                console.log(textStatus);
                console.log(error);
            }
        });
    }

});

$("#btnGetAllItem").click(function (){
    resetItem();
    loadAllItem();
});


function resetItem(){
    $("#txtItemCode").val("");
    $("#txtItemName").val("");
    $("#txtItemUnitPrice").val("");
    $("#txtItemQuantity").val("");
}

function bindClickEvent() {

    $("#itemToTable>tr").click(function () {

        let id = $(this).children().eq(0).text();
        let name = $(this).children().eq(1).text();
        let qtyOnHand = $(this).children().eq(2).text();
        let price = $(this).children().eq(3).text();

        $("#txtItemCode").val(id);
        $("#txtItemName").val(name);
        $("#txtItemQuantity").val(qtyOnHand);
        $("#txtItemUnitPrice").val(price);

    });
}

function loadAllItem(){
    $("#itemToTable").empty();
    $.ajax({
        url:"item?option=GETALL",
        method:"GET",
        success:function (resp){
            for (const item of resp.data){
                let row = `<tr><td>${item.itemCode}</td><td>${item.name}</td><td>${item.qtyOnHand}</td><td>${item.price}</td></tr>`;
                $("#itemToTable").append(row);

            }
            bindClickEvent();


        }
    });
}

$("#btnItemDelete").click(function (){
    let itemCode = $("#txtItemCode").val();

    $.ajax({
        url: "item?itemCode=" + itemCode,
        method: "DELETE",

        success: function (res) {
            console.log(res);
            if (res.status == 200) {
                alert(res.message);
                resetItem();
                loadAllItem();
            } else if (res.status == 400) {
                alert(res.data);
            } else {
                alert(res.data);
            }

        },
        error: function (ob, status, t) {
            console.log(ob);
            console.log(status);
            console.log(t);
        }
    });
});


$("#btnItemUpdate").click(function (){
    let itemOb = {
        itemCode: $("#txtItemCode").val(),
        itemName: $("#txtItemName").val(),
        itemQty: $("#txtItemQuantity").val(),
        itemPrice: $("#txtItemUnitPrice").val()
    };
    $.ajax({
        url: "item",
        method: "PUT",
        contentType: "application/json",
        data: JSON.stringify(itemOb),
        success: function (res){
            if (res.status == 200){
                alert(res.message);
                resetItem();
                loadAllItem();
            } else if (res.status == 400){
                alert(res.message);
            } else {
                alert(res.data);
            }
        },
        error: function (ob, errorStus) {
            console.log(ob);
            console.log(errorStus);
        }
    });
});

$("#btnItemSearch").click(function (){
    let itemCode = $("#txtSearchItem").val();
    $("#itemToTable").empty();
    $.ajax({
        url:"item?option=SEARCH&itemCode=" + itemCode,
        method:"GET",
        success:function (resp){
                let row = `<tr><td>${resp.itemCode}</td><td>${resp.name}</td><td>${resp.qtyOnHand}</td><td>${resp.price}</td></tr>`;
                $("#itemToTable").append(row);

            bindClickEvent();
        }
    });
});


