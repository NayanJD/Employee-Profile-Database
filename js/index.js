
$(".no").change(function chk(){
  if(isNaN(document.frm.tno.value)){
     $(".no-err").slideDown(500);
  }
  else{
    $(".no-err").slideUp(500);
  }
});

$(".age").change(function chk(){
  var age=document.frm.tage.value;
  if(age<18 || age>60){
     $(".age-err").slideDown(500);
  }
  else{
    $(".age-err").slideUp(500);
  }
});

/*function chk(){
   alert(document.frm.tno.value);
}*/

function chkrty() {
    var age=document.frm.tage.value;
    var name=document.frm.tname.value;
    var no=document.frm.tno.value;
    var date=document.frm.tdate.value;
    var add=document.frm.taddress.value;
    var mail=document.frm.temail.value;
    var flag=true;
    
    if(age==""){
        $(".age-empty").slideDown(500);
        //document.tno.value.focus();
        flag= false;
    }
    else{
         $(".age-empty").slideUp(500);
    }
    if(name==""){
        $(".name-empty").slideDown(500);
        //document.tno.value.focus();
        flag=false;
    }
    else{
        $(".name-empty").slideUp(500);
         //document.frm.submit();
    }
    if(no==""){
        $(".no-empty").slideDown(500);
        flag=false;
    }
    else{
         $(".no-empty").slideUp(500);
    }
    if(date==""){
        $(".date-empty").slideDown(500);
        flag=false;
    }
    else{
         $(".date-empty").slideUp(500);
    }
    if(add==""){
        $(".address-empty").slideDown(500);
        flag=false;
    }
    else{
         $(".address-empty").slideUp(500);
    }
    
    if(mail==""){
        $(".email-empty").slideDown(500);
        flag=false;
    }
    else{
         $(".email-empty").slideUp(500);
    }
    
    if(flag==false){
        
        return false;
    }
    else{
        
        return true;
    }
}






$(".email").focus(function(){
  $(".email-help").slideDown(500);
}).blur(function(){
  $(".email-help").slideUp(500);
});

$(".name").focus(function(){
  $(".name-help").slideDown(500);
}).blur(function(){
  $(".name-help").slideUp(500);
});

$(".no").focus(function(){
  $(".no-help").slideDown(500);
}).blur(function(){
  $(".no-help").slideUp(500);
});

$(".age").focus(function(){
  $(".age-help").slideDown(500);
}).blur(function(){
  $(".age-help").slideUp(500);
});

$(".date").focus(function(){
  $(".date-help").slideDown(500);
}).blur(function(){
  $(".date-help").slideUp(500);
});

$(".photo").focus(function(){
  $(".file-help").slideDown(500);
}).blur(function(){
  $(".file-help").slideUp(500);
});

$(".address").focus(function(){
  $(".address-help").slideDown(500);
}).blur(function(){
  $(".address-help").slideUp(500);
});