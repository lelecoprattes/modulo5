package mvc_agencia.servico.impl;

import java.util.List;

import mvc_agencia.dto.UsuarioDto;
import mvc_agencia.entidade.Usuario;

public interface UsuarioServico {
    void saveUser(UsuarioDto usuarioDto);

    Usuario findUserByEmail(String email);

    List<UsuarioDto> findAllUsers();
}
