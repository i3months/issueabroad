$(document).ready(function(){

    $(".upup").click(function(){
        $('html').animate({scrollTop : 0}, 600);
    });

    $(".downdown").click(function(){
        $('html').animate({scrollTop : ($('.foot').offset().top)}, 600);
    });



    $(".searchbutton").click(function(){
        let value = $(".searchinput").val();

        value = value.trim();

        if(value.length < 2) {
            alert("두 자리 이상의 검색어를 입력해주세요.");
            return;
        }

        if(value == "") {
            alert("검색어를 입력해주세요.")
            return;
        }

        let form = document.getElementById("formform");

        form.action = "/search/" + value;
        form.method = "GET";
        form.submit();
    });


});