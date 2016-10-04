/**
 * Created by mateimihai on 7/20/2016.
 */
$('#cartIcon').hover(function () {
    $(this).css('width','8%');
},function () {
    $(this).css('width','7%');
})


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

function refreshCartProductsNumber(cartIdentifier){
    $.ajax({
        type : "GET",
        url : "/mvc/cart/getProductsNumber",
        data : {cartId: idCart},
        success : function(result){
            $("#prodNr").text(result);
        },
        error : function(e) {
            console.log('Error: ', e);
        }
    });
}

var toggleMenu = false;
var idCart = parseInt(readCookie("cartId"));

$(document).ready(refreshCartProductsNumber(idCart));

$("html").click(function () {
    toggleMenu = false;
    $('#cartPanel').css("visibility", "hidden");
})

function deleteEntryFunction(val) {
    $.ajax({
        type: "POST",
        url: contextURL + "cart/removeFromCart",
        data: {
            entryId: val,
            cartId: idCart
        },
        success: function (response) {
            refreshCartProductsNumber(idCart);
            var toggleMenu = false;
            $('#cartPanel').css("visibility", "hidden");
            console.log("successs!");
        },
        error: function (e) {
            alert('Error: ' + e);
        }
    });
}

$('#cartIcon, #prodNr').click(function () {

    var entriesNo = 0;

    if(!toggleMenu){
        toggleMenu = true;
        $.ajax({
            type : "POST",
            url : "/mvc/cart/entries",
            data : {cartId: idCart},
            success : function(result) {

                $("#entry-data").html("");

                $("#total-value").text(result.total);
                $.each(result.entries, function (index, entry) {
                        $("#entry-data").html($("#entry-data").html() +
                            "<div class='entry-line'>" +
                                "<span class='entry-quantity'>" + entry.quantity + "  x  " + "</span>" +
                                "<span class='entry-name'>" + entry.name + "   " + "</span>" +
                                "<span class='entry-subtotal'>" + entry.subtotal + "$ </span>" +
                                "<span> <a class='removeProd' onclick=\"deleteEntryFunction(" + entry.id + ")\"> x </a> </span>" +
                            "</div>"
                        );
                    entriesNo++;
                    }
                );

                $('#cartPanel').css("position","absolute");
                refreshCartProductsNumber(idCart);
                $('#cartPanel').css("visibility", "visible");
                $('#cartPanel').animate({height: (entriesNo*30 + 65).toString() + '%'}, 200);
            },
            error : function(e) {
                console.log('Error: ', e);
            }
        });
    }else{
        toggleMenu = false;
        $('#cartPanel').animate({height: '0%'}, 200);
        $('#cartPanel').delay(200);
        $('#cartPanel').css("visibility", "hidden");
    }
    }
)

$("#addButtonID").click(function() {
         $.ajax({
             type : "POST",
             url : "/mvc/cart/addToCart",
             data : {
                 quantity: $("#quantityFieldID").val(),
                 productId: $('#product-id').val(),
                 cartId: idCart
             },
             success : function(response) {
                 alert(response);
                 refreshCartProductsNumber(idCart);
                 var toggleMenu = false;
                 $('#cartPanel').css("visibility", "hidden");
                 console.log("success");
             },
             error : function(e) {
                alert('An error occurred while trying to add the product to the cart. Please try again later. ');
             }
        });
});

$("#checkoutButtonID").click(function () {
    window.location.href = contextURL + "cart/?cartId=" + idCart;
})

