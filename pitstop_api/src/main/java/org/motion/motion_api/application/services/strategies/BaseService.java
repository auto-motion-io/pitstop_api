package org.motion.motion_api.application.services.strategies;

import java.util.List;

public interface BaseService <T,D> {
    List<T> listarTodos();
    T buscarPorId(Integer id);
    T criar(T object);
    T atualizar(Integer id, D dto);
    void deletar(Integer id);
}
