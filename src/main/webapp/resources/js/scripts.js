function aguarde(){
	$("#errormessage").html("Aguarde...");
}

function beforeLogin(){
    $("#beforelogin").html("Estamos autenticando o usuário. Aguarde só um instante...");
 }

 function loginSucess(message){
	
       $("#formLogin").hide("slow");
       $("#titulo").hide("slow");
       $("#beforelogin").html(message + '<div id="followingBallsG"><div id="followingBallsG_1" class="followingBallsG"> </div><div id="followingBallsG_2" class="followingBallsG"></div> <div id="followingBallsG_3" class="followingBallsG"></div><div id="followingBallsG_4" class="followingBallsG"></div></div>');
       $("#beforelogin").show("slow");
       
       setTimeout(function () { window.location.href = "/redAmber-WebApp/home/index.xhtml" }, 2000);
 }
 



 function loginError(message){
    $("#errormessage").html(message);
 }
 
 
    
