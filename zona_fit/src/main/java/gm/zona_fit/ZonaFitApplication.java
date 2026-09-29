package gm.zona_fit;

import gm.zona_fit.model.Cliente;
import gm.zona_fit.service.IClienteServicio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class ZonaFitApplication implements CommandLineRunner {

    @Autowired
    private IClienteServicio clienteServicio;

    private static final Logger logger = LoggerFactory.getLogger(ZonaFitApplication.class);

	public static void main(String[] args) {
        logger.info("Iniciando la aplicación");
		SpringApplication.run(ZonaFitApplication.class, args);
        logger.info("App finalizada");
	}

    @Override
    public void run(String... args) throws Exception {
        zonaFitApp();
    }

    private void zonaFitApp(){
        Scanner sc = new Scanner((System.in));
        int op = 0;
        while(op != 6){
            showMenu();
            logger.info("Ingresa opción: ");
            try {
                op = Integer.parseInt(sc.nextLine());
                switch (op){
                    case 1 :
                        List<Cliente> clientes = clienteServicio.listarClientes();
                        clientes.forEach(cliente -> logger.info(cliente.toString()));
                        break;
                    case 2:
                        logger.info("Ingresa id del cliente a buscar: ");
                        int id = Integer.parseInt(sc.nextLine());
                        Cliente cliente = clienteServicio.buscarClientePorId(id);
                        if(cliente == null){
                            logger.info("Cliente no encontrado");
                        }
                        else {
                            logger.info(cliente.toString());
                        }
                        break;
                    case 3:
                        logger.info("Ingresa nombre: ");
                        String nombre = sc.nextLine();
                        logger.info("Ingresa apellido: ");
                        String apellido = sc.nextLine();
                        logger.info("Ingresa membresia: ");
                        int mem = Integer.parseInt(sc.nextLine());
                        cliente = new Cliente();
                        cliente.setNombre(nombre);
                        cliente.setApellido(apellido);
                        cliente.setMembresia(mem);
                        clienteServicio.guardarCliente(cliente);
                        logger.info("Usuario agregado exitosamente");
                        break;
                    case 4:
                        logger.info("Ingrese el id del cliente a actualizar: ");
                        id = Integer.parseInt(sc.nextLine());
                        cliente = clienteServicio.buscarClientePorId(id);
                        if(cliente!=null){
                            logger.info("Ingresa nombre: ");
                            nombre = sc.nextLine();
                            logger.info("Ingresa apellido: ");
                            apellido = sc.nextLine();
                            logger.info("Ingresa membresia: ");
                            mem = Integer.parseInt(sc.nextLine());
                            cliente = new Cliente();
                            cliente.setNombre(nombre);
                            cliente.setApellido(apellido);
                            cliente.setMembresia(mem);
                            cliente.setId(id);
                            clienteServicio.guardarCliente(cliente);
                            logger.info("Usuario actualizado exitosamente");
                        }
                        else {
                            logger.info("Usuario no encontrado");
                        }
                        break;
                    case 5:
                        logger.info("Ingrese el id del usuario a eliminar: ");
                        id = Integer.parseInt(sc.nextLine());
                        cliente = clienteServicio.buscarClientePorId(id);
                        if(cliente != null){
                            clienteServicio.eliminarCliente(cliente);
                            logger.info("Cliente eliminado");
                        }else{
                            logger.info("Cliente no encontrado");
                        }
                        break;
                    case 6:
                        logger.info("Saliendo del sistema");
                        break;
                    default:
                        logger.info("Opcion no reconocida");
                        break;
                }
            } catch (Exception e) {
                logger.info("Error: Entrada inválida. Por favor, ingresa los datos correctamente.");
            }
        }

    }
    private void showMenu(){
        logger.info("\n--- Menú de Clientes ---");
        logger.info("1. Listar clientes");
        logger.info("2. Buscar cliente por ID");
        logger.info("3. Agregar cliente");
        logger.info("4. Actualizar cliente");
        logger.info("5. Eliminar cliente");
        logger.info("6. Salir");
    }
}
