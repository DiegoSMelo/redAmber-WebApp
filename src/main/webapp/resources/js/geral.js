$(document).ready(function() {
	$("#bsc_avancada").hide();

	$(".btn_escondeBscAvancada").click(function() {
		esconde_bscAvancada();
	});

	$(".data").mask("99/99/9999");
	$(".hora").mask("99:99");
	
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

function fechaModal(){
	$('.modal').modal('hide');
}
