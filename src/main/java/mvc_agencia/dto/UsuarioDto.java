package mvc_agencia.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class UsuarioDto
{
    private Long id;
    @NotEmpty
    private String firstName;// primeiro nome e guardar no banco de dados
    @NotEmpty
    private String lastName;// segundo nome e quadar no banco de dados
    @NotEmpty(message = "O e-mail não deve estar vazio")
    @Email
    private String email;
    @NotEmpty(message = "A senha não deve estar vazia")
    private String password;
}
