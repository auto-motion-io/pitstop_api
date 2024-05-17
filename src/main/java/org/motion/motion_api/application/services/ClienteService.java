package org.motion.motion_api.application.services;

import org.motion.motion_api.domain.dtos.pitstop.cliente.CreateClienteDTO;
import org.motion.motion_api.application.services.util.ServiceHelper;
import org.motion.motion_api.domain.entities.Oficina;
import org.motion.motion_api.domain.entities.pitstop.Cliente;
import org.motion.motion_api.domain.repositories.pitstop.IClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {
    @Autowired
    private IClienteRepository clienteRepository;
    @Autowired
    private ServiceHelper serviceHelper;
    public List<Cliente> listarClientes(){
        return clienteRepository.findAll();
    }
    public Cliente cadastrar(CreateClienteDTO novoClienteDTO){
        Oficina oficina = serviceHelper.pegarOficinaValida(novoClienteDTO.fkOficina());
        Cliente cliente = new Cliente(novoClienteDTO, oficina);
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
