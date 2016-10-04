function readCookie(name) {
    var nameEQ = name + "=";
    var ca = document.cookie.split(';');
    for(var i=0;i < ca.length;i++) {
        var c = ca[i];
        while (c.charAt(0)==' ') c = c.substring(1,c.length);
        if (c.indexOf(nameEQ) == 0) return c.substring(nameEQ.length,c.length);
    }
    return null;
}

var idCart = parseInt(readCookie("cartId"));

$('#paymentMethod').on('change', function () {
    if(this.value === "RAMBURS"){
        $("#cardDetails").hide();
    } else {
        $("#cardDetails").show();
    }
});


$("#checkoutID").click(function() {
    $.ajax({
        type : "POST",
        url : "/mvc/products/customerOrderPlaced",
        data : {
            cartId: idCart
        },
        success : function(response) {
            alert(response);
            console.log("success");
        },
        error : function(e) {
            alert('An error occurred. ');
        }
    });
});