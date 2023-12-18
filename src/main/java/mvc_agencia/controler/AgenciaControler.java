package mvc_agencia.controler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.validation.Valid;
import mvc_agencia.dto.UsuarioDto;
import mvc_agencia.entidade.Usuario;
import mvc_agencia.servico.impl.UsuarioServico;

@Controller
public class AgenciaControler {
	//
	@Autowired
	private UsuarioServico usuarioServico;// para salvar e trazer usuario e buscar por emal

    @GetMapping("/agenciaindex") //um get para pagina inicial aqui se chama index
    public String home(){
        return "agenciaindex";
    }
    
    @GetMapping("/pedido")// um get para abrir a pagina login
    public String login(){
        return "pedido";
    }
    
    @GetMapping("/register") //um get para abrir a pagina de regidter
    public String showRegistrationForm(Model model){
        // ao abrir a pagina de register ira crie um objeto de modelo para salvar no formulario e fazer a validacao
    	UsuarioDto usuario = new UsuarioDto();
        model.addAttribute("usuario", usuario);
        return "register";
    }
    
    @PostMapping("/register/save")// para salva usar o post ele vai passa rpor parametro 
    public String registration(@Valid @ModelAttribute("cliente") UsuarioDto usuarioDto,// o usser dto
                               BindingResult result,
                               Model model){
        Usuario existingUser = usuarioServico.findUserByEmail(usuarioDto.getEmail());
// esse condicao e para ver se tem o emaile se o campo nao esta fazio e mostra um mensagem caso ja tenha um email ja cadastrado
        if(existingUser != null && existingUser.getEmail() != null && !existingUser.getEmail().isEmpty()){
            result.rejectValue("email", null,
                    "Já existe uma conta cadastrada com o mesmo email");
        }
//aqui ele passo ele volta para formulario de regidtro e retona o que vc ja tinha digitado antes
        if(result.hasErrors()){
            model.addAttribute("user", usuarioDto);
            return "register";
        }
// nao tendo probla nas partes de cima ele salva o usuria e venia para register success
        usuarioServico.saveUser(usuarioDto);
        return "redirect:/register?success";
    }
    // entrar na pagina  users e quadar a lista e passa como parametro para a pagina users
    @GetMapping("/cliente")
    public String users(Model model){
        List<UsuarioDto> cliente = usuarioServico.findAllcliente();
        model.addAttribute("cliente", cliente);
        return "cliente";
    }

}
