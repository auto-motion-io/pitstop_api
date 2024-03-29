package org.motion.motion_api.application.services;

import org.motion.motion_api.application.dtos.CreateTarefaDTO;
import org.motion.motion_api.application.dtos.UpdateTarefaDTO;
import org.motion.motion_api.application.exception.RecursoNaoEncontradoException;
import org.motion.motion_api.application.services.strategies.BaseService;
import org.motion.motion_api.application.services.util.ServiceHelper;
import org.motion.motion_api.domain.entities.Oficina;
import org.motion.motion_api.domain.entities.pitstop.Tarefa;
import org.motion.motion_api.domain.repositories.IOficinaRepository;
import org.motion.motion_api.domain.repositories.pitstop.ITarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TarefaService {

    @Autowired
    private ITarefaRepository tarefaRepository;
    @Autowired
    private IOficinaRepository oficinaRepository;
    @Autowired
    ServiceHelper serviceHelper;


    public List<Tarefa> listarTodasTarefasPorIdOficina(int id) {
        if (!oficinaRepository.existsById(id))
            throw new RecursoNaoEncontradoException("Oficina não encontrada com o id: " + id);
        return tarefaRepository.findByOficinaIdOficina(id);
    }


    public Tarefa buscarPorId(int id) {
        return tarefaRepository.findById(id).orElseThrow(() -> new RecursoNaoEncontradoException("Tarefa não encontrada com o id: " + id));
    }


    public Tarefa criar(CreateTarefaDTO createTarefaDTO) {
        Oficina oficina = serviceHelper.pegarOficinaValida(createTarefaDTO.fkOficina());
        Tarefa tarefa = new Tarefa(createTarefaDTO, oficina);
        tarefaRepository.save(tarefa);
        return tarefa;
    }


    public Tarefa atualizar(int id, UpdateTarefaDTO dto) {
        Tarefa tarefa = buscarPorId(id);
        tarefa.setStatus(dto.status());
        return tarefaRepository.save(tarefa);
    }


    public void deletar(int id) {

    }
}
