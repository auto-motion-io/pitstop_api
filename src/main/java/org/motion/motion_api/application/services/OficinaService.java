package org.motion.motion_api.application.services;

import jakarta.transaction.Transactional;
import org.motion.motion_api.application.services.util.ServiceHelper;
import org.motion.motion_api.domain.dtos.oficina.UpdateLogoOficinaDTO;
import org.motion.motion_api.domain.dtos.oficina.UpdateOficinaDTO;
import org.motion.motion_api.application.exceptions.DadoUnicoDuplicadoException;
import org.motion.motion_api.application.services.strategies.OficinaServiceStrategy;
import org.motion.motion_api.domain.entities.Oficina;
import org.motion.motion_api.domain.entities.pitstop.InformacoesOficina;
import org.motion.motion_api.domain.repositories.IOficinaRepository;
import org.motion.motion_api.domain.repositories.pitstop.IInformacoesOficinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OficinaService implements OficinaServiceStrategy{

    @Autowired
    private IOficinaRepository oficinaRepository;

    @Autowired
    private IInformacoesOficinaRepository informacoesOficinaRepository;
    @Autowired
    private ServiceHelper serviceHelper;

    public List<Oficina> listarTodos() {
        return oficinaRepository.findAll();
    }

    public Oficina buscarPorId(int id) {
        return serviceHelper.pegarOficinaValida(id);
    }

    public List<Oficina> buscarPorTipoVeiculo(String tipo){
        return oficinaRepository.findByInformacoesOficina_TipoVeiculosTrabalhaContainingIgnoreCase(tipo);
    }

    public List<Oficina> buscarPorTipoVeiculoPropulsaoMarcaNome(String tipoVeiculo, String tipoPropulsao, String marca, String nome){
        return oficinaRepository.findByInformacoesOficina_TipoVeiculosTrabalhaContainingIgnoreCaseAndInformacoesOficina_TipoPropulsaoTrabalhaContainingIgnoreCaseAndInformacoesOficina_MarcasVeiculosTrabalhaContainingIgnoreCaseAndNomeContainingIgnoreCase(tipoVeiculo, tipoPropulsao, marca,nome);
    }


    public Oficina criar(Oficina oficina) {
        checarConflitoCnpj(oficina);

        InformacoesOficina informacoesOficina = setDefaultInfo();

        informacoesOficinaRepository.save(informacoesOficina);
        oficina.setLogoUrl("https://jeyoqssrkcibrvhoobsk.supabase.co/storage/v1/object/public/ofc-photos/base_oficina_image.png");
        oficina.setInformacoesOficina(informacoesOficina);
        return oficinaRepository.save(oficina);
    }

    @Transactional
    public Oficina atualizar(int id, UpdateOficinaDTO oficinaAtualizada) {
        Oficina oficina = serviceHelper.pegarOficinaValida(id);

        oficina.setNome(oficinaAtualizada.nome());
        oficina.setCep(oficinaAtualizada.cep());
        oficina.setNumero(oficinaAtualizada.numero());
        oficina.setComplemento(oficinaAtualizada.complemento());
        oficina.setHasBuscar(oficinaAtualizada.hasBuscar());

        return oficinaRepository.save(oficina);
    }

    @Transactional
    public Oficina atualizarLogoUrl(int id, UpdateLogoOficinaDTO dto) {
        Oficina oficina = serviceHelper.pegarOficinaValida(id);
        oficina.setLogoUrl(dto.getUrl());
        return oficinaRepository.save(oficina);
    }

    public void deletar(int id) {
        serviceHelper.pegarOficinaValida(id);
        oficinaRepository.deleteById(id);
        //throw new NotImplementedException("");
    }



    //MÉTODOS AUXILIARES

    /**
     * @param oficina Oficina a ser checada.
     * @return CnpjDuplicadoException caso o cnpj já esteja cadastrado.
     */
    private void checarConflitoCnpj(Oficina oficina){
        //TODO tirar listagem e implementar um select específico para o cnpj
        if(listarTodos().stream().anyMatch(o->o.getCnpj().equals(oficina.getCnpj())))
            throw new DadoUnicoDuplicadoException("CNPJ já cadastrado");
    }

    private InformacoesOficina setDefaultInfo(){
        return new InformacoesOficina(
                "",
                "08:00",
                "18:00",
                "10:00",
                "18:00",
                "false;true;true;true;true;true;true",
                "carro;moto",
                "combustão",
                "agrale;bepobus;chevrolet;ciccobus;daf;effa-jmc;fiat;ford;foton;gmc;hyundai;iveco;jac;man;marcopolo;mascarello;maxibus;mercedes-benz;navistar;neobus;puma-alfa;saab-scania;scania;shacman;sinotruk;volkswagen;volvo;walkbus;acura;alfa romeo;am gen;asia motors;aston martin;audi;baby;bmw;brm;bugre;byd;cab motors;cadillac;caoa chery;caoa chery/chery;cbt jipe;chana;changan;chrysler;citroën;cross lander;d2d motors;daewoo;daihatsu;dfsk;dodge;effa;engesa;envelo;ferrari;fibravan;fyber;geely;gm - chevrolet;great wall;gurgel;gwm;hafei;hitech electric;honda;isuzu;jaguar;jeep;jinbei;jpx;kia motors;lada;lamborghini;land rover;lexus;lifan;lobini;lotus;mahindra;maserati;matra;mazda;mclaren;mercury;mg;mini;mitsubishi;miura;nissan;peugeot;plymouth;pontiac;porsche;ram;rely;renault;rolls-royce;rover;saab;saturn;seat;seres;shineray;smart;ssangyong;subaru;suzuki;tac;toyota;troller;vw - volkswagen;wake;walk;adly;amazonas;aprilia;atala;avelloz;bajaj;bee;benelli;beta;bimota;brandy;brava;brp;buell;bueno;bull;bycristo;cagiva;caloi;daelim;dafra;dayang;dayun;derbi;ducati;emme;fever;fox;fusco motosegura;fym;garinni;gas gas;green;haobao;haojue;harley-davidson;hartford;hero;husaberg;husqvarna;indian;iros;jiapeng volcano;johnnypag;jonny;kahena;kasinski;kawasaki;ktm;kymco;landum;lavrale;lerivo;lon-v;magrão triciclos;malaguti;miza;moto guzzi;motocar;motorino;mrx;mv agusta;mvk;niu;orca;pegassi;piaggio;polaris;regal raptor;riguete;royal enfield;sanyang;siamoto;sundown;`super soco`;targos;tiger;traxx;triumph;ventane motors;vento;voltz;watts;wuyang;yamaha;zontes"
        );
    }
}
