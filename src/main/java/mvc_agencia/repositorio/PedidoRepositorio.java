package mvc_agencia.repositorio;

import mvc_agencia.entidade.Pedidos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepositorio extends JpaRepositorio<Pedidos, Long> {

    Pedidos findByName(String name);
}
