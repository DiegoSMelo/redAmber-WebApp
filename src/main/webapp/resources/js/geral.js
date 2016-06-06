$(document).ready(function() {
	$("#bsc_avancada").hide();

	$(".btn_escondeBscAvancada").click(function() {
		esconde_bscAvancada();
	});

	$(".data").mask("99/99/9999");
	$(".hora").mask("99:99");
	$(".inputRG").mask("999999?999");
	$(".numero").mask("9?99999");
	$(".dataInicial").attr("placeholder", "");
	
	jQuery(".telefone")
    .mask("(99) 9999-9999?9")
    .focusout(function (event) {  
        var target, phone, element;  
        target = (event.currentTarget) ? event.currentTarget : event.srcElement;  
        phone = target.value.replace(/\D/g, '');
        element = $(target);  
        element.unmask();  
        if(phone.length > 10) {  
            element.mask("(99) 99999-999?9");  
        } else {  
            element.mask("(99) 9999-9999?9");  
        }  
    });
	
	
	
	$(".btnSalvar").click(function(){
		exibeImgLoad();
	});
	
	
	//$(".atualizarbtn").hide();
	//$(".img-load").hide("slow");

});


function validarEmail(email) {
	var emailReg = /^([\w-\.]+@([\w-]+\.)+[\w-]{2,4})?$/;
	console.log(emailReg.test(email));
	return emailReg.test(email);
}

function exibe_bscAvancada(){
	$("#bsc_avancada").show("slow");
	$(".area_input_busca").hide("slow");
}

function esconde_bscAvancada(){
	$("#bsc_avancada").hide("slow");
	$(".area_input_busca").show("slow");
}

function exibeModalHorario(){
	$('.modalHorario').modal('show');
}

function fechaModalHorario(){
	$('.modalHorario').modal('hide');
}

function exibeModalAula(){
	$('.modalAula').modal('show');
}

function fechaModalAula(){
	$('.modalAula').modal('hide');
}

function exibeResumoGradeAula(strClass, resumo){
	
	$("."+strClass).text(resumo);
}

function escondeImgLoad(){
	//$(".img-load").hide("slow");
	$(".img-load").css("display", "none");
}

function exibeImgLoad(){
	$(".img-load").show("slow");
}



