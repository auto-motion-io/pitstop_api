package org.motion.motion_api.application.services;

import org.motion.motion_api.application.dtos.CreateClienteDTO;
import org.motion.motion_api.domain.entities.pitstop.Cliente;
import org.motion.motion_api.domain.repositories.pitstop.IClienteRepository;
import org.motion.motion_api.domain.repositories.pitstop.IVeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {
    @Autowired
    private IClienteRepository clienteRepository;
    @Autowired
    private IVeiculoRepository veiculoRepository;

    public List<Cliente> listarClientes(){
        return clienteRepository.findAll();
    }
    public Cliente cadastrar(CreateClienteDTO novoClienteDTO){
        Cliente cliente = new Cliente(novoClienteDTO);
        clienteRepository.save(cliente);
        return cliente;
    }
    public void deletar(Integer id){
        clienteRepository.deleteById(id);
    }
    public Cliente atualizar(Integer id, CreateClienteDTO novoClienteDTO){
        Cliente cliente = clienteRepository.findById(id).orElseThrow(()-> new RuntimeException("Cliente não encontrado com o id: " + id));
        cliente.setNome(novoClienteDTO.nome());
        cliente.setTelefone(novoClienteDTO.telefone());
        cliente.setEmail(novoClienteDTO.email());
        clienteRepository.save(cliente);
        return cliente;
    }
    public Cliente buscarPorId(Integer id){
        return clienteRepository.findById(id).orElseThrow(()-> new RuntimeException("Cliente não encontrado com o id: " + id));
    }
}
