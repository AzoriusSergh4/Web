package practica1.cuadro;


import java.util.List;

import practica1.autor.Autor;
import practica1.cliente.Cliente;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CuadroRepository extends JpaRepository<Cuadro, Long> {

	List<Cuadro> findDistinctCuadroByTituloContainsIgnoreCaseOrDescripcionContainsIgnoreCase(String titulo, String descripcion);
	List<Cuadro> findByAutor(Autor autor);

	List<Cuadro> findAllOrderByTitulo(String titulo);
	List<Cuadro> findAllOrderByAutor(Autor autor);
	List<Cuadro> findAllOrderByComprador(Cliente comprador);
	List<Cuadro> findAllOrderByPrecio(int precio);
	
	
}
