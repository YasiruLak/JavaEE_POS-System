$("#btnItemSave").click(function (){
    let data = $("#itemForm").serialize();
    $.ajax({
        url:"item",
        method:"POST",
        data:data,
        success: function (res){
            if (res.status == 200){
                alert(res.message);
                reset();
                loadAllItem();
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
});

loadAllItem();

function reset(){
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
        $("#txtItemUnitPrice").val(price);
        $("#txtItemQuantity").val(qtyOnHand);
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
                reset();
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
                reset();
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