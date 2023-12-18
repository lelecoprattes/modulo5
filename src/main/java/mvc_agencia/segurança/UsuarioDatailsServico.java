package mvc_agencia.segurança;



	import java.util.Collection;
	import java.util.stream.Collectors;

	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.security.core.GrantedAuthority;
	import org.springframework.security.core.authority.SimpleGrantedAuthority;
	import org.springframework.security.core.userdetails.UserDetails;
	import org.springframework.security.core.userdetails.UserDetailsService;
	import org.springframework.security.core.userdetails.UsernameNotFoundException;
	import org.springframework.stereotype.Service;

	import mvc_agencia.entidade.Pedidos;
	import mvc_agencia.entidade.Usuario;
	import mvc_agencia.repositorio.UsuarioRepositorio;

	@Service
	public class UsuarioDatailsServico implements UserDetailsService {

		@Autowired
	    private UsuarioRepositorio userRepository;// tras o user repositoryu

	    @Override
	    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
	        Usuario user = userRepository.findByEmail(email);//buscando por paramento u usuario pelo email

	        // se null fpr diferente de null ira guardar as informações do 
	        if (user != null) {
	            return new org.springframework.security.core.userdetails.User(user.getEmail(),// usuaria por pagao do sistema
	                    user.getPassword(),
	                    mapRolesToAuthorities(user.getRoles()));// um metodo so apra retorna lista de colecao so da roles
	        }else{
	            throw new UsernameNotFoundException("Invalid username or password.");        }
	    }

	    private Collection < ? extends GrantedAuthority> mapRolesToAuthorities(Collection <Pedidos> roles) {
	        Collection < ? extends GrantedAuthority> mapRoles = roles.stream()
	                .map(role -> new SimpleGrantedAuthority(role.getName()))
	                .collect(Collectors.toList());
	        return mapRoles;
	    }
	}
