$(document).ready(function(){
	var a=$('.selected-item').css("background-color", "#AAAAAA");
	a.css("color", "black");
	
});
function cambia(obj){
    //alert(obj.files.length)
	//alert($(obj).prev('label').text());
	$(obj).prev('label').text(obj.files.length + " Archivos Seleccionados");
}
console.log("cargado");
function hideComplement(){
	document.getElementById('ca1').classList.add('btn-primary'); 
	document.getElementById('step-1').style.display='block'; 
	console.log(event);
	event.preventDefault();
}
/*
function hide(){<!-- TRABAJO FUTURO 
					    <li class="active" ><a id="first" data-toggle="tab" href="javascript:void(0);" onclick="hide(); index(); document.getElementById('stepam-0').style.display='block';"  data-target="#ma">Modos de Acceso</a></li>
					    <li><a id="carac" class="tab-a" data-toggle="tab" href="javascript:void(0);" data-target="#ca" onclick="hide(); index(); document.getElementById('stepca-0').style.display='block'; console.log(event);event.preventDefault();" >Caracteristica de Accesibilidad</a></li>
					    <li><a id="control" data-toggle="tab" href="javascript:void(0);" data-target="#coa" onclick="hide(); index();  document.getElementById('stepcoa-0').style.display='block';" >Control de Accesibilidad</a></li>
					    <li><a id="riesgo" data-toggle="tab" href="javascript:void(0);" data-target="#ra" onclick="hide(); index();  console.log('aded'); document.getElementById('stepra-0').style.display='block'; ;" >Riesgo de Accesibilidad</a></li>
						<p:remoteCommand name="cero" actionListener="#{beanFormulario.setActiveIndexZero()}" ></p:remoteCommand>
						-->
	console.log("Entra");
	var x = document.getElementsByClassName("setup-content");
	var i=0;
	for(i=0;i<x.length;i++){
		x[i].style.display="none";
	}
	var circle=document.getElementsByClassName("btn-circle");
	//console.log(circle);
	i=0;
	for(i=0;i<circle.length;i++){
		circle[i].classList.remove('btn-primary');
	}
}
*/
function hideComplement(){
	document.getElementById('ca1').classList.add('btn-primary'); 
	document.getElementById('step-1').style.display='block'; 
	console.log(event);
	event.preventDefault();
}
$('a.tab-a').click(function(e)
		{
		    // Special stuff to do when this link is clicked...

		    // Cancel the default action
		    e.preventDefault();
		});