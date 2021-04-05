function newPass(){
    var senha = document.getElementById("senha").value;
    var novaSenha = document.getElementById("novaSenha").value;
    var msg =  document.getElementById("msgErro");
    var btn =  document.getElementById("botaoSubmit");

    if ((novaSenha != "") && senha ==""){
        msg.innerText = "Adicione a senha antiga";
        btn.disabled = true;
    }
    else{
        btn.disabled = false;
        msg.innerText = "";
    }
}