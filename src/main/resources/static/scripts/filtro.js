

function filtro(classe, valor){
    console.log(valor)
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

