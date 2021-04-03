package com.spring.voluptuaria.service;

import com.spring.voluptuaria.configuration.MyUserDetails;
import com.spring.voluptuaria.model.Funcionario;
import com.spring.voluptuaria.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    FuncionarioRepository funcionarioRepository;

    @Override
    public UserDetails loadUserByUsername(String cpf) throws UsernameNotFoundException {
        Optional<Funcionario> user = funcionarioRepository.findFuncionarioByCpf(cpf);

        user.orElseThrow(() -> new UsernameNotFoundException("Not found: "));
        return user.map(MyUserDetails::new).get();

    }
}
