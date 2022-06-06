function setItemCount(){
    $.ajax({
        url:"item?option=COUNT",
        method:"GET",
        success:function (resp){
            parseInt($("#itemCount").text(resp));
        },
        error:function (ob, statusText, error) {
        }

    });
}

function setCustomerCount(){
    $.ajax({
        url:"customer?option=COUNT",
        method:"GET",
        success:function (resp){
            parseInt($("#customerCount").text(resp));
        },
        error:function (ob, statusText, error) {
        }

    });
}

function setOrdersCount(){
    $.ajax({
        url:"orders?option=COUNT",
        method:"GET",
        success:function (resp){
            parseInt($("#orderCount").text(resp));
        },
        error:function (ob, statusText, error) {
        }

    });
}

setOrdersCount();
setCustomerCount();
setItemCount();