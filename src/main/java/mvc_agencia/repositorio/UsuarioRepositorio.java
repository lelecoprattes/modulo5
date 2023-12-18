package mvc_agencia.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import mvc_agencia.entidade.Usuario;

public interface UsuarioRepositorio extends JpaRepository<Usuario, Long> {

    Usuario findByEmail(String email);

}
