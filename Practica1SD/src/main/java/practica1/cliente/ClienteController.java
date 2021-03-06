package practica1.cliente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import practica1.GaleriaController;

import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/cliente")
public class ClienteController extends GaleriaController{
    @Autowired
    private ClienteRepository clienteRepository;

    @RequestMapping("/addCliente")
    public String addClient(Model model) {
        return "nuevoCliente";
    }

    @RequestMapping("/editarCliente/{id}")
    public String editarCliente(Model model, @PathVariable long id) {
        Optional<Cliente> opcional = this.clienteRepository.findById(id);
        if(opcional.isPresent()){
            model.addAttribute("cliente", opcional.get());
        }

        return "editarCliente";
    }

    @PostMapping("/")
    public String addCliente(Model model, @RequestParam Map<String, String> mappedCliente) {
        Cliente cliente = this.crearClienteDesdeMap(mappedCliente);

        this.clienteRepository.save(cliente);
        cargaGaleria(model);
        return "galeria";
    }

    @GetMapping("/{id}")
    public String editarCliente(Model model, @PathVariable long id, @RequestParam Map<String, String> mappedCliente) {
        Cliente cliente = this.crearClienteDesdeMap(mappedCliente);

        Optional<Cliente> opcional = this.clienteRepository.findById(id);
        if(opcional.isPresent()){
            Cliente clienteAnterior = opcional.get();
            clienteAnterior.actualizarCliente(cliente);
            this.clienteRepository.save(clienteAnterior);
        }
        cargaGaleria(model);
        return "galeria";
    }

    @GetMapping("/buscarPorNombre")
    public String buscarClientePorNombre(Model model, @RequestParam String nombre) {
        if (nombre == null || nombre.equals("")) {
            cargaGaleria(model);
        } else {
            cargaGaleria(model);
            model.addAttribute("clientes", clienteRepository.findByNombreContainsIgnoreCase(nombre));
        }

        return "galeria";
    }

    @GetMapping("/buscarPorApellidos")
    public String buscarClientePorApellidos(Model model, @RequestParam String apellidos) {
        if (apellidos == null || apellidos.equals("")) {
            cargaGaleria(model);
        } else {
            cargaGaleria(model);
            model.addAttribute("clientes", clienteRepository.findByApellidosContainsIgnoreCase(apellidos));
        }

        return "galeria";
    }

    @GetMapping("/buscarPorEmail")
    public String buscarClientePorEmail(Model model, @RequestParam String email) {
        if (email == null || email.equals("")) {
            cargaGaleria(model);
        } else {
            cargaGaleria(model);
            model.addAttribute("clientes", clienteRepository.findByEmailContainsIgnoreCase(email));
        }

        return "galeria";
    }

    private Cliente crearClienteDesdeMap(Map<String, String> mappedCliente) {
        Cliente cliente = new Cliente();

        cliente.setNombre(mappedCliente.get("nombre"));
        cliente.setApellidos(mappedCliente.get("apellidos"));
        cliente.setNif(mappedCliente.get("nif"));;
        cliente.setEmail(mappedCliente.get("email"));
        cliente.setTelefono(mappedCliente.get("telefono"));
        cliente.setDireccionPostal(mappedCliente.get("direccionPostal"));

        return cliente;
    }
}
