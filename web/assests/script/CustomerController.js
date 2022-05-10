$("#btnSaveCustomer").click(function (){
    let data = $("#customerForm").serialize();
    $.ajax({
        url:"customer",
        method:"POST",
        data:data,
        success: function (res){
            if (res.status == 200){
                loadAllCustomer();
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

loadAllCustomer();

$("#btnGetAllCustomer").click(function (){
    reset();
    loadAllCustomer();
});

function reset(){
    $("#txtCusId").val("");
    $("#txtCusName").val("");
    $("#txtCusAddress").val("");
    $("#txtCusContact").val("");
}

function bindClickEvent() {

    $("#customerTable>tr").click(function () {

        let id = $(this).children().eq(0).text();
        let name = $(this).children().eq(1).text();
        let address = $(this).children().eq(2).text();
        let contact = $(this).children().eq(3).text();

        $("#txtCusId").val(id);
        $("#txtCusName").val(name);
        $("#txtCusAddress").val(address);
        $("#txtCusContact").val(contact);
    });
}

function loadAllCustomer(){
    $("#customerTable").empty();
    $.ajax({
        url:"customer?option=GETALL",
        method:"GET",
        success:function (resp){
            for (const customer of resp.data){
                let row = `<tr><td>${customer.id}</td><td>${customer.name}</td><td>${customer.address}</td><td>${customer.contact}</td></tr>`;
                $("#customerTable").append(row);
            }
            bindClickEvent();
        }
    });

}

$("#btnDeleteCustomer").click(function (){
    let customerID = $("#txtCusId").val();

    $.ajax({
        url: "customer?customerID=" + customerID,
        method: "DELETE",

        success: function (res) {
            console.log(res);
            if (res.status == 200) {
                alert(res.message);
                reset();
                loadAllCustomer();
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

$("#btnUpdateCustomer").click(function (){
    let cusOb = {
        id: $("#txtCusId").val(),
        name: $("#txtCusName").val(),
        address: $("#txtCusAddress").val(),
        contact: $("#txtCusContact").val()
    };
    $.ajax({
        url: "customer",
        method: "PUT",
        contentType: "application/json",
        data: JSON.stringify(cusOb),
        success: function (res){
            if (res.status == 200){
                alert(res.message);
                reset();
                loadAllCustomer()
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