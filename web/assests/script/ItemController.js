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
        let qty = $(this).children().eq(2).text();
        let price = $(this).children().eq(3).text();

        $("#txtItemCode").val(id);
        $("#txtItemName").val(name);
        $("#txtItemUnitPrice").val(price);
        $("#txtItemQuantity").val(qty);
    });
}

