package com.spring.voluptuaria.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class Method {

    public String verificaMetodo(String operacao){

        switch (operacao){
            case "Adicionar":   return  "post";
            case "Editar":  return  "put";
            case "Excluir": return  "delete";
            default: return null;
        }

    }
}
