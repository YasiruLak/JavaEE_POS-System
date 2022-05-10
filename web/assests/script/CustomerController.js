$("#btnSaveCustomer").click(function (){
    let data = $("#customerForm").serialize();
    $.ajax({
        url:"customer",
        method:"POST",
        data:data,
        success: function (res){
            if (res.status == 200){
                alert(res.message);
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