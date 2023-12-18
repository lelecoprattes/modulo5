package mvc_agencia.servico.impl;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import mvc_agencia.dto.UsuarioDto;
import mvc_agencia.entidade.Pedidos;
import mvc_agencia.entidade.Usuario;
import mvc_agencia.repositorio.PedidoRepositorio;
import mvc_agencia.repositorio.UsuarioRepositorio;

@Service
public class UsuarioServicoImpl implements UsuarioServico {

	@Autowired
    private UsuarioRepositorio usuarioRepositorio;
	@Autowired
    private PedidoRepositorio pedidoRepositorio;
	@Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void saveUser(UsuarioDto userDto) {
        Usuario user = new Usuario();
        user.setName(userDto.getFirstName() + " " + userDto.getLastName());
        user.setEmail(userDto.getEmail());
        // encrypt the password using spring security
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));

        Pedidos pedido = pedidoRepositorio.findByName("ROLE_ADMIN");
        if(pedido == null) {
            pedido = checkRoleExist();
        }
        user.setRoles(Arrays.asList(pedido));
        usuarioRepositorio.save(user);
    }

    @Override
    public Usuario findUserByEmail(String email) {
        return usuarioRepositorio.findByEmail(email);
    }

    @Override
    public List<UsuarioDto> findAllUsers() {
        List<Usuario> user = usuarioRepositorio.findAll();
        return user.stream()
                .map((cliente) -> mapToUserDto(cliente))
                .collect(Collectors.toList());
    }

    private UsuarioDto mapToUserDto(Usuario user){
        UsuarioDto userDto = new UsuarioDto();
        String[] str = user.getName().split(" ");
        userDto.setFirstName(str[0]);
        userDto.setLastName(str[1]);
        userDto.setEmail(user.getEmail());
        return userDto;
    }

    private Pedidos checkRoleExist(){
        Pedidos role = new Pedidos();
        role.setName("ROLE_ADMIN");
        return ( pedidoRepositorio).save(role);
    }
}
