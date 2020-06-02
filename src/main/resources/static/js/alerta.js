function crearMenu(html){
window.onload=()=>{
    //$("#navbarSupportedContent").html(html);
    document.getElementById("navbarSupportedContent").innerHTML = html;
}
}

function mostrarContrasena(){
      var pass = document.getElementById("password");
		if(pass.type == "password"){
			pass.type = "text";
			$('.icon').removeClass('fa fa-eye-slash').addClass('fa fa-eye');
		}else{
			pass.type = "password";
			$('.icon').removeClass('fa fa-eye').addClass('fa fa-eye-slash');
		}
	}
        
        function mostrarContrasena1(){
      var pass = document.getElementById("password1");
		if(pass.type == "password"){
			pass.type = "text";
			$('.icon1').removeClass('fa fa-eye-slash').addClass('fa fa-eye');
		}else{
			pass.type = "password";
			$('.icon1').removeClass('fa fa-eye').addClass('fa fa-eye-slash');
		}
	}
function mostrarContrasena2(){
      var pass = document.getElementById("password2");
		if(pass.type == "password"){
			pass.type = "text";
			$('.icon2').removeClass('fa fa-eye-slash').addClass('fa fa-eye');
		}else{
			pass.type = "password";
			$('.icon2').removeClass('fa fa-eye').addClass('fa fa-eye-slash');
		}
	}
        
function mensajeActivarInactivar(id,idestatus) {
let modulo = document.getElementById("nombreModulo").value;
console.log(idestatus);
    Swal
        .fire({
            title: idestatus==2?"Activar":"Inactivar",
            text: "¿Desea "+(idestatus==2?"Activar":"Inactivar")+" "+modulo+"?",
            icon: 'warning',
            showCancelButton: true,
            confirmButtonText: "Si",
            cancelButtonText: "No",
        })
        .then(resultado => {
            if (resultado.value) {
                // Hicieron click en "Sí"
          idestatus=idestatus==2?1:2;
          window.location = modulo+"/eliminar/"+id+"/"+idestatus;
                
            } else {
                // Dijeron que no
                console.log("*NO se cerro*");
            }
        });
}

function mensajeEliminar(id) {
let modulo = document.getElementById("nombreModulo").value;
console.log(id);
    Swal
        .fire({
            title: "Eliminar",
            text: "¿Desea Eliminar "+modulo+" ?",
            icon: 'warning',
            showCancelButton: true,
            confirmButtonText: "Si",
            cancelButtonText: "No",
        })
        .then(resultado => {
            if (resultado.value) {
                // Hicieron click en "Sí"
                window.location = modulo+"/eliminar/"+id;
            } else {
                // Dijeron que no
                console.log("*NO se cerro*");
            }
        });
}

function mensajeReiniciar(id) {
console.log(id);
    Swal
        .fire({
            title: "Reiniciar",
            text: "¿Desea Reiniciar Usuario?",
            icon: 'warning',
            showCancelButton: true,
            confirmButtonText: "Si",
            cancelButtonText: "No",
        })
        .then(resultado => {
            if (resultado.value) {
                // Hicieron click en "Sí"
                window.location = "updatePassword/"+id;
            } else {
                // Dijeron que no
                console.log("*NO se cerro*");
            }
        });
}

function mensajeCerrarSesion(){
    var URLdomain = window.location.host;
    
    Swal
        .fire({
            title: "Cerrar Sesión",
            text: "¿Desea cerrar sesión?",
            icon: 'warning',
            showCancelButton: true,
            confirmButtonText: "Si",
            cancelButtonText: "No",
        })
        .then(resultado => {
            if (resultado.value) {
                // Hicieron click en "Sí"
                window.location.replace("http://"+URLdomain+"/riife/logout");
            } else {
                // Dijeron que no
                console.log("*NO se cerro*");
            }
        });
}

function nobackbutton(){
window.location.hash="no-back-button";
window.location.hash="Again-No-back-button" //chrome
window.onhashchange=function(){window.location.hash="no-back-button";}
}


function LoadingSpinner (form, spinnerHTML) {
  form = form || document
  
  //Keep track of button & spinner, so there's only one automatic spinner per form
  var button
  var spinner = document.createElement('div')
  spinner.innerHTML = spinnerHTML
  spinner = spinner.firstChild

  //Delegate events to a root element, so you don't need to attach a spinner to each individual button.
  form.addEventListener('click', start)

  //Stop automatic spinner if validation prevents submitting the form
  //Invalid event doesn't bubble, so use capture
  form.addEventListener('invalid', stop, true)

  //Start spinning only when you click a submit button
  function start (event) {
    if (button) stop()
    button = event.target
    if (button.type === 'submit') {
      LoadingSpinner.start(button, spinner)
    }
  }

  function stop () {
    LoadingSpinner.stop(button, spinner)
  }

  function destroy () {
    stop()
    form.removeEventListener('click', start)
    form.removeEventListener('invalid', stop, true)
  }

  return {start: start, stop: stop, destroy: destroy}
}

//I guess these would be called class methods. They're useable without instantianing a new LoadingSpinner so you can start & stop spinners manually on any elements, for ajax and stuff.
LoadingSpinner.start = function (element, spinner) {
  element.classList.add('loading')
  return element.appendChild(spinner)
}

LoadingSpinner.stop = function (element, spinner) {
  element.classList.remove('loading')
  return spinner.remove()
}
        
var loginForm = document.querySelector('#loginForm')
var loginLoader = new LoadingSpinner(loginForm, 'Cargando...')


// capcha
function enabledSubmit() {
 var botonSubmit = document.getElementById("submit");
 botonSubmit.disabled = false;
}
  
  
  
  // modal
  
  function refreshModal()
    {
        $("#miModal").reload();
        //document.getElementById("miModal").reload();
    }

 // inicializar datatable de jquery

//$(document).ready
$(() => {
    $('#tablaDatos').DataTable( {
        scrollY:        "230px",
        scrollCollapse: false,
        paging:         true,
        searching: true,
        info:     true,
        ordering: false
    } );
} );

    
$(() => {
  function rescaleCaptcha(){
    var width = $('.g-recaptcha').parent().width();
    var scale;
    if (width < 302) {
      scale = width / 302;
    } else{
      scale = 1.0; 
    }
    $('.g-recaptcha').css('transform', 'scale(' + scale + ')');
    $('.g-recaptcha').css('-webkit-transform', 'scale(' + scale + ')');
    $('.g-recaptcha').css('transform-origin', '0 0');
    $('.g-recaptcha').css('-webkit-transform-origin', '0 0');
  }

  rescaleCaptcha();
  $(window).resize(()=> rescaleCaptcha());

});