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