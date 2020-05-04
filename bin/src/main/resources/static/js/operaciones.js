//select
function crearEncuesta(html){

    $("#divEncuesta").html(html);

}


(() => {
  'use strict';
  window.addEventListener('load', () =>{
    // Fetch all the forms we want to apply custom Bootstrap validation styles to
    var forms = document.getElementsByClassName('needs-validation');
    // Loop over them and prevent submission
    var validation = Array.prototype.filter.call(forms, (form) => {
      form.addEventListener('submit', (event) => {
        if (form.checkValidity() === false) {
          event.preventDefault();
          event.stopPropagation();
        }
        form.classList.add('was-validated');
      }, false);
    });
  }, false);
})();

//$(document).ready
$(() => {
     $('#cuestionarios').chosen({
     width:'200px',
     disable_search:false});
     $('#capitulos').chosen({
     width:'200px',
     disable_search:false});
     $('#subcapitulos').chosen({
     width:'200px',
     disable_search:false});
    $('.chosen-select').chosen({
     width:'200px',
     disable_search:false});
 
});


 function getCapitulosSubcapitulos(obj,idPregunta) {
      var id = obj.value;
      var pr = idPregunta !=0 ? "../" : "";
       
      
      $("#capitulos").load(pr+"refreshCapitulos/"+id+"/"+idPregunta,
      (req, res, msj)=>{
            if(res == "success"){
                $("#capitulos").trigger("chosen:updated");
            }
            if(res == "error"){
                alert("Error: " + msj.status + " " + msj.statusText);
            }
        });
      
      $("#subcapitulos").load(pr+"refreshSubCapitulos/"+id+"/"+idPregunta,
      (req, res, msj)=>{
            if(res == "success"){
                $("#subcapitulos").trigger("chosen:updated");
            }
            if(res == "error"){
                alert("Error: " + msj.status + " " + msj.statusText);
            }
        });
      
      
    }
    
    function getRespuestaOm(obj,idPregunta) {
      var id = obj.value;
      var pr = idPregunta !=0 ? "../" : "";
      
      $("#opcionesTodas").load(pr+"refreshTodo/"+id+"/"+idPregunta,
      (req, res, msj)=>{
            if(res == "success"){
                $("#enCatalogo").val(id);
                $('#catalogos').chosen({
                    width:'200px',
                    no_results_text:"No se encontro: "});
                $('.chosen-select').chosen({
                    width:'200px',
                    no_results_text:"No se encontro: "});
                
                $("#catalogos").trigger("chosen:updated");
                $("#enCatalogo").trigger("chosen:updated");
            
            }
            if(res == "error"){
                alert("Error: " + msj.status + " " + msj.statusText);
            }
        });
        
    }

    function getEnCatalogo(obj,idPregunta) {
      var id = obj.value;
      var pr = idPregunta !=0 ? "../" : "";
      $("#opcionesTodas").load(pr+"refreshEnCatalogo/"+id+"/"+idPregunta,
      (req, res, msj)=>{
            if(res == "success"){
                $('#catalogos').chosen({
                    width:'200px',
                    no_results_text:"No se encontro: "});
                $('.chosen-select').chosen({
                    width:'200px',
                    no_results_text:"No se encontro: "});
                $("#catalogos").trigger("chosen:updated");
            }
            if(res == "error"){
                alert("Error: " + msj.status + " " + msj.statusText);
            }
        });
    }
    
    
    function getEnCatalogoEspecificar(obj,idPregunta) {
      var id = obj.value;
      var pr = idPregunta !=0 ? "../" : "";
      $("#opcionesEnCatalogo").load(pr+"refreshEnCatalogoEspecificar/"+id+"/"+idPregunta,
      (req, res, msj)=>{
            if(res == "success"){
                $('.chosen-select').chosen({
                    width:'200px',
                    no_results_text:"No se encontro: "});
                $(".chosen-select").trigger("chosen:updated");
            }
            if(res == "error"){
                alert("Error: " + msj.status + " " + msj.statusText);
            }
        });
      
    }
  
function getOtroEspecificar(obj, name) {
      var id = obj.value;
      var etiqueta = document.getElementById("respuestas["+name+"].respuestaEspecifica");
      //$(document).ready
      
$(() => {
    if(id=='OT'){
      etiqueta.style.display = 'block';
      }
      else{
      etiqueta.style.display = 'none';
      }
      
});}
 
 function getSino(obj, name) {
      var id = obj.value;
      //$(document).ready
      var num = name+1;
      var etiqueta = document.getElementById("preguntas["+num+"].idPregunta_omeo");
$(() => {
      if(id=='SI'){
      etiqueta.style.display = 'block';
      }
      else{
      etiqueta.style.display = 'none';
      }
});}

 function getOmeo(desc, name) {
      //$(document).ready
      var etiqueta = document.getElementById("respuestas["+name+"].respuestaEspecifica");
      const visible = etiqueta.style.display;
$(() => {
      
      if(desc=='OT'){
      if(visible=='none'){
      etiqueta.style.display = 'block';
      }
      else{
      etiqueta.style.display = 'none';
      }
      }
});}
   