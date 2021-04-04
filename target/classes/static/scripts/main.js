
var getUrlParameter = function getUrlParameter(sParam) {
  var sPageURL = window.location.search.substring(1),
      sURLVariables = sPageURL.split('&'),
      sParameterName,
      i;

  for (i = 0; i < sURLVariables.length; i++) {
      sParameterName = sURLVariables[i].split('=');

      if (sParameterName[0] === sParam) {
          return sParameterName[1] === undefined ? true : decodeURIComponent(sParameterName[1]);
      }
  }
};

var action = getUrlParameter('operacao');

if(action === "Excluir"){
  if(document.getElementsByTagName("input") !== null){
    var inputs = document.getElementsByTagName("input");
    for (var i = 0; i < inputs.length; i++) {
      inputs[i].readOnly = true;
    }
  }
  if(document.getElementsByTagName("select") !== null){
    var select = document.getElementsByTagName("select");
    for (var i = 0; i < select.length; i++) {
      select[i].disabled = true;
    }
  }
}

function verificaData(){
   var ida = document.getElementById("ida").value;
   var volta = document.getElementById("volta").value;
   var msg =  document.getElementById("msgErro");
   var btn =  document.getElementById("botaoSubmit");

   if((volta != "") && (volta < ida)){
     btn.disabled = true;
     msg.innerText ="Data de Volta deve ser posterior à Data de Ida";
   }
   else{
     btn.disabled = false;
     msg.innerText = "";
   }
}

