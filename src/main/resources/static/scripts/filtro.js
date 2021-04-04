
var classe = "id";

function getClasse(cls){
    classe = cls;
    document.getElementById("filtrar").value = '';
    filtro(null);
}

function filtro(valor){
    console.log(classe);
    console.log(valor);
     trs = document.getElementsByClassName("filtro");

    for( i =0; i<trs.length; i++) {
        tds = trs[i].getElementsByClassName(classe);

        for( j =0; j<tds.length; j++){

           if(valor == "" || valor == null) {
             trs[i].style.display='';

           }

          else if(tds[j].getAttribute("value") != valor){
              trs[i].style.display='none';
           }
           else {
             trs[i].style.display='';
           }

        }
    }

}

