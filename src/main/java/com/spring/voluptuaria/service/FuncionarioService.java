package com.spring.voluptuaria.service;

import com.spring.voluptuaria.exception.NotFoundException;
import com.spring.voluptuaria.model.Funcionario;
import com.spring.voluptuaria.repository.FuncionarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class FuncionarioService {

   private final FuncionarioRepository funcionarioRepository;
   private final TipoFuncionarioService tipoFuncionarioService;

    public List<Funcionario> findAll() {
        return funcionarioRepository.findAll();
    }

    public Funcionario findById(Long id) throws NotFoundException {
           return funcionarioRepository.findById(id)
                .orElseThrow(()-> new NotFoundException(id));
    }

    public Funcionario save(Funcionario funcionario) throws NotFoundException {
        funcionario.setTipoFuncionario(tipoFuncionarioService.findById(funcionario.getIdTipoFuncionario()));

        if(funcionario.getIdTipoFuncionario()== 1) {
            funcionario.setRoles("ROLE_ADMIN");
        }
        else{
            funcionario.setRoles("ROLE_USER");
        }
        return funcionarioRepository.save(funcionario);
    }

    public void delete(Funcionario funcionario){
        funcionarioRepository.delete(funcionario);
    }

    public Optional<Funcionario> findFuncionarioByCpf(String cpf){
        return funcionarioRepository.findFuncionarioByCpf(cpf);
    }

    @ResponseStatus(HttpStatus.OK)
    public void updateFuncionario(Funcionario funcionario, String novaSenha) throws Exception {
        if( funcionario.getSenha() == "" || funcionario.getSenha() == null) {
            funcionario.setSenha(findById(funcionario.getId()).getSenha());
        }
        else{
            if(funcionario.getSenha().equals(findById(funcionario.getId()).getSenha())) {
                funcionario.setSenha(novaSenha);
            }
            else{
                throw new Exception("Senha Incorreta");

            }
        }
        save(funcionario);

    }
}
