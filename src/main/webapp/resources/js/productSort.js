/**
 * Created by nicoletatica on 27/07/2016.
 */
$(document).ready(function () {

    if(getUrlParameter('sortValue')!=='' ) {
        $sortValue=getUrlParameter('sortValue');
        $("#sort").val($sortValue).change();
    }
})


var getUrlParameter = function getUrlParameter(sParam) {
    var sPageURL = decodeURIComponent(window.location.search.substring(1)),
        sURLVariables = sPageURL.split('&'),
        sParameterName,
        i;

    for (i = 0; i < sURLVariables.length; i++) {
        sParameterName = sURLVariables[i].split('=');

        if (sParameterName[0] === sParam) {
            return sParameterName[1] === undefined ? true : sParameterName[1];
        }
    }
};

function sortProduct() {
    $sortValue=$('#sort').val();
    $url=document.URL;

    if ($url.indexOf("?") <0){
        $url=$url+"?";
    }

    if ($url.indexOf("sort") <0){
        $categoryPositionIndex=$url.indexOf("category");
        if ($categoryPositionIndex>0){
            $url=$url.substr(0, $categoryPositionIndex) +"&sortValue="+$sortValue+"&" + $url.substr($categoryPositionIndex);

        }
        else {
            $url=$url+"&sortValue="+$sortValue;
        }
    }
    else{
        $currentSortValue=getUrlParameter('sortValue');
        $url=$url.replace($currentSortValue,$sortValue);
    }
    if ($url.indexOf("page=") >0){
        $currentPage=getUrlParameter('page');
        $url=$url.replace($currentPage,'1');
    }
    location.replace($url);


}
