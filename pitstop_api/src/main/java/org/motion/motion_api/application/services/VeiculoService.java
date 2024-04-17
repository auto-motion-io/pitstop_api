package org.motion.motion_api.application.services;

import org.motion.motion_api.application.dtos.veiculo.CreateVeiculoDTO;
import org.motion.motion_api.application.exceptions.RecursoNaoEncontradoException;
import org.motion.motion_api.domain.entities.pitstop.Cliente;
import org.motion.motion_api.domain.entities.pitstop.Veiculo;
import org.motion.motion_api.domain.repositories.pitstop.IClienteRepository;
import org.motion.motion_api.domain.repositories.pitstop.IOrdemDeServicoRepository;
import org.motion.motion_api.domain.repositories.pitstop.IVeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VeiculoService {
    @Autowired
    IVeiculoRepository veiculoRepository;
    @Autowired
    IClienteRepository clienteRepository;
    @Autowired
    IOrdemDeServicoRepository ordemDeServicoRepository;

    public List<Veiculo> listarVeiculos(){
        return veiculoRepository.findAll();
    }
    public Veiculo cadastrar(CreateVeiculoDTO novoVeiculoDTO){
        Cliente cliente = clienteRepository.findById(novoVeiculoDTO.fkCliente()).orElseThrow(()-> new RecursoNaoEncontradoException("Cliente não encontrado com o id: " + novoVeiculoDTO.fkCliente()));
        Veiculo veiculo = new Veiculo(novoVeiculoDTO, cliente);
        veiculoRepository.save(veiculo);
        return veiculo;
    }
    public Veiculo buscarPorId(Integer id){
        return veiculoRepository.findById(id).orElseThrow(()-> new RuntimeException("Veiculo não encontrado com o id: " + id));
    }
    public void deletar(Integer id){
        Veiculo veiculo = buscarPorId(id);
        if(ordemDeServicoRepository.existsByVeiculo(veiculo)){
            throw new RuntimeException("Veiculo não pode ser deletado, pois está associado a uma ordem de serviço");
        }
        veiculoRepository.delete(veiculo);
    }
    public Veiculo atualizar(Integer id, CreateVeiculoDTO novoVeiculoDTO){
        Veiculo veiculo = buscarPorId(id);
        veiculo.setPlaca(novoVeiculoDTO.placa());
        veiculo.setMarca(novoVeiculoDTO.marca());
        veiculo.setModelo(novoVeiculoDTO.modelo());
        veiculo.setCor(novoVeiculoDTO.cor());
        veiculo.setAnoFabricacao(novoVeiculoDTO.ano());
        veiculoRepository.save(veiculo);
        return veiculo;
    }
}
