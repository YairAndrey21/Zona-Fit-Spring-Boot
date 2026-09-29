package gm.zona_fit.service;


import gm.zona_fit.model.Cliente;
import gm.zona_fit.repository.IClienteRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService implements IClienteServicio{

    @Autowired
    private IClienteRepositorio clienteRepositorio;

    @Override
    public List<Cliente> listarClientes() {
        List<Cliente> clientes = clienteRepositorio.findAll() ;
        return clientes;
    }

    @Override
    public Cliente buscarClientePorId(Integer id) {
        Cliente cliente = clienteRepositorio.findById(id).orElse(null);
        return cliente;
    }

    @Override
    public void guardarCliente(Cliente cliente) {
        clienteRepositorio.save(cliente);
    }

    @Override
    public void eliminarCliente(Cliente cliente) {
        clienteRepositorio.delete(cliente);
    }
}
