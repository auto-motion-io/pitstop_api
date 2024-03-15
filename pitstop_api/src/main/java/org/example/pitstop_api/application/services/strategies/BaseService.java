package org.example.pitstop_api.application.services.strategies;

import org.example.pitstop_api.application.dtos.UpdateOficinaDTO;
import org.example.pitstop_api.domain.entities.Oficina;

import java.util.List;

public interface BaseService <T,D> {
    List<T> listarTodos();
    T buscarPorId(Integer id);
    T criar(T object);
    T atualizar(Integer id, D dto);
    void deletar(Integer id);
}
