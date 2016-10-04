/**
 * Created by nicoletatica on 19/07/2016.
 */
var arr;
var maxSizeArr
$(document).ready(function () {
    arr = new Array();
    $urlString=document.URL;
    $url=$urlString.replace('/"/', "'");
    if ($url.indexOf('category=')) {
        $param=$url.split("?")[1];
        $paramArray=$param.replace(/&/g,'').split('category=');
        var numbers = /^[0-9]+$/;
        $.each($paramArray, function () {
            $number=parseInt(this);
            if (!isNaN($number)) {
                $categorySelectedValue=this;
                $checkBox = $('[value=\'' + $categorySelectedValue + '\']');
                $checkBox.attr("checked", true);
                arr.push($categorySelectedValue);
                addCategoyBox($checkBox);
            }
        });
    }
    
})

function addCategoyBox($selectedCheckboxCategory) {
    $valueInput = $($selectedCheckboxCategory).val();

    if ($($selectedCheckboxCategory).is(':checked')) {
        closeButton = "<a   href=\"";
        $add = '';
        rectangleDiv = "<div id=\"rectangle\"  class=\"" + $valueInput + "\" style='display: inline-block; align: center' >";
        $add += (rectangleDiv);
        $add += $($selectedCheckboxCategory).attr("id");
        $add += closeButton + "javascript:deleteFilter('" + $valueInput + "')\"> X </a>";
        $add += "</div>";
        $("#selectedCategories").append($add);

    }
}

function applySelectedFilter($selectedCheckboxCategory) {
    $valueInput= $($selectedCheckboxCategory).val();


    $url=document.URL;
    if( $($selectedCheckboxCategory).is(':checked') ) {
       addCategoyBox($selectedCheckboxCategory)
        arr.push($valueInput);
        if ($url.indexOf("?") <0){
            $url=$url+"?";
        }
        if ($url.indexOf("?")<$url.length-1){
            $url=$url+"&";
        }
        if ($url.indexOf("&category"+$valueInput) <0){
            $url=$url+"category="+$valueInput;
            location.replace($url);
        }
            }
    else {
        if ($url.indexOf("&category="+$valueInput) >= 0){
            $url=$url.replace("&category="+$valueInput,'');
        }
        if ($url.indexOf("category="+$valueInput) >= 0){
            $url=$url.replace("category="+$valueInput,'');
        }
        $("#selectedCategories").find("."+$valueInput).remove();
        if(jQuery.inArray( $valueInput, arr )){
            arr.splice($.inArray($valueInput, arr),1);

        }
        if ($url.indexOf("page=") >0){
            $currentPage=getUrlParameter('page');
            $url=$url.replace($currentPage,'1');
        }

        location.replace($url);
    }

}

function deleteFilter($selectedValue) {
    $checkBox=$('[value=\''+$selectedValue+'\']');
    $checkBox.attr("checked", false);
    applySelectedFilter($checkBox);

}

