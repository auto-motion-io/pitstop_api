package org.motion.motion_api.application.services;

import org.motion.motion_api.application.dtos.financeiro.CreateFinanceiroDTO;
import org.motion.motion_api.application.dtos.financeiro.ResponseDataFinanceiro;
import org.motion.motion_api.application.dtos.financeiro.ResponseFinanceiroDTO;
import org.motion.motion_api.application.services.util.ServiceHelper;
import org.motion.motion_api.domain.entities.Oficina;
import org.motion.motion_api.domain.entities.pitstop.Financeiro;
import org.motion.motion_api.domain.repositories.pitstop.IFinanceiroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FinanceiroService {
    @Autowired
    private IFinanceiroRepository financeiroRepository;
    @Autowired
    private ServiceHelper serviceHelper;

    public ResponseDataFinanceiro listarDadosFinanceiros(int idOficina) {
        List<Financeiro> financas = financeiroRepository.findAllByOficina_IdOficina(serviceHelper.pegarOficinaValida(idOficina).getIdOficina());
        List<Financeiro> saidas = financas.stream().filter(f -> f.getTransacao().equals("saida")).toList();
        List<Financeiro> entradas = financas.stream().filter(f -> f.getTransacao().equals("entrada")).toList();
        Double valorTotalEntradas = entradas.stream().mapToDouble(Financeiro::getValor).sum();
        Double valorTotalSaidas = saidas.stream().mapToDouble(Financeiro::getValor).sum();
        Double saldo = valorTotalEntradas - valorTotalSaidas;
        return new ResponseDataFinanceiro(valorTotalEntradas,valorTotalSaidas,saldo);
    }

    public ResponseFinanceiroDTO registrarTransacaoFinanceira(CreateFinanceiroDTO dto){
        Oficina oficina = serviceHelper.pegarOficinaValida(dto.getIdOficina());
        Financeiro financeiro = new Financeiro(dto,oficina);
        return null;
    }
}
