loadAllCustomer();
// $("#btnSaveCustomer").attr('disabled', true);



$("#btnSaveCustomer").click(function () {
    let data = $("#customerForm").serialize();

    if ($("#txtCusId").val() == '') {
        alert("Can not be Customer Id empty");
    } else if ($("#txtCusName").val() == '') {
        alert("Can not be Customer Name empty");
    }else if ($("#txtCusAddress").val() == '') {
        alert("Can not be Customer Address empty");
    }else if ($("#txtCusContact").val() == '') {
        alert("Can not be Customer Contact empty");
    }else{
        console.log(data);
        $.ajax({
            url: "customer",
            method: "POST",
            data: data,
            success: function (res) {
                console.log(res);
                if (res.status == 200) {
                    loadAllCustomer();
                    alert(res.message);
                    resetCustomer();
                } else {
                    console.log(res)
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

$("#btnGetAllCustomer").click(function () {
    resetCustomer();
    loadAllCustomer();

});

function resetCustomer() {
    $("#txtCusId").val("");
    $("#txtCusName").val("");
    $("#txtCusAddress").val("");
    $("#txtCusContact").val("");
    $("#txtSearchCustomer").val("");
}

function loadAllCustomer() {
    $("#customerTable").empty();
    $.ajax({
        url: "customer?option=GETALL",
        method: "GET",
        success: function (resp) {
            for (const customer of resp.data) {
                let row = `<tr><td>${customer.id}</td><td>${customer.name}</td><td>${customer.address}</td><td>${customer.contact}</td></tr>`;
                $("#customerTable").append(row);

            }
            bindClickEvents();

        }
    });
}


$("#btnDeleteCustomer").click(function () {
    let customerID = $("#txtCusId").val();

    $.ajax({
        url: "customer?customerID=" + customerID,
        method: "DELETE",

        success: function (res) {
            console.log(res);
            if (res.status == 200) {
                alert(res.message);
                resetCustomer();
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

$("#btnUpdateCustomer").click(function () {
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
        success: function (res) {
            if (res.status == 200) {
                alert(res.message);
                resetCustomer();
                loadAllCustomer()
            } else if (res.status == 400) {
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

$("#btnSearchCustomer").click(function () {
    let customerID = $("#txtSearchCustomer").val();
    $("#customerTable").empty();
    $.ajax({
        url: "customer?option=SEARCH&customerID=" + customerID,
        method: "GET",
        success: function (resp) {
                let row = `<tr><td>${resp.id}</td><td>${resp.name}</td><td>${resp.address}</td><td>${resp.contact}</td></tr>`;
                $("#customerTable").append(row);

            bindClickEvents();
        }

    });
});

function bindClickEvents() {
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