// Functions used in viewProducts.jsp
$("#deleteButton").click(function() {
    $('input[id="idProd"]:checked').each(function() {
        console.log("In js!");

        var arr = new Array();
        var toInt = parseInt(this.value);
        arr.push(toInt);

        $.ajax({
            type : "POST",
            url : "../product",
            data : {
                prodArray: arr
            },
            success : function(response) {
                $("#prodTable").load( "../product #prodTable" );
                console.log("success");
            },
            error : function(e) {
                alert('Error: ' + e);
            }
        });
    });
});

$("#exportButton").click(function() {
    var arr = new Array();
    $('input[id="idProd"]:checked').each(function() {
        var toInt = parseInt(this.value);
        arr.push(toInt);
        console.log(arr);
    });


        if(arr.length > 0) {
            $.ajax({
                url: "../product/export",
                type: 'POST',
                contentType: 'application/json',
                dataType: 'json',
                data: JSON.stringify(arr),

                error : function() {
                    $.ajax({
                        type: 'GET',
                        url: '../product/export',
                        success: function(data) {
                            document.write(data);

                        }
                    });
                }
            });

    }else{
        document.getElementById("mesageExport").style.color = "red";
        document.getElementById("mesageExport").innerHTML = "Please select some products !";
    }

});

$("#selectall").click(function () {
    $('.check').attr('checked', this.checked);
});

//For collapsible checkout form
$('.panel-heading h4 a input[type=checkbox]').on('click', function(e) {
    e.stopPropagation();
})


function billingFunction() {
    if (document.getElementById("same").checked) {
        document.getElementById("billingStreet").value = document.getElementById("deliveryStreet").value;
        document.getElementById("billingNumber").value = document.getElementById("deliveryNumber").value;
        document.getElementById("billingCity").value = document.getElementById("deliveryCity").value;
        document.getElementById("billingPhone").value = document.getElementById("deliveryPhone").value;
    } else {
        document.getElementById("billingStreet").value = "";
        document.getElementById("billingNumber").value = "";
        document.getElementById("billingCity").value = "";
        document.getElementById("billingPhone").value = "";
    }
}
