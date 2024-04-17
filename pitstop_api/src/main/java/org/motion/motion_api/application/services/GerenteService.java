package org.motion.motion_api.application.services;


import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.apache.commons.lang3.NotImplementedException;
import org.motion.motion_api.application.dtos.gerente.*;
import org.motion.motion_api.application.exceptions.DadoUnicoDuplicadoException;
import org.motion.motion_api.application.exceptions.RecursoNaoEncontradoException;
import org.motion.motion_api.application.services.authorization.AuthorizationService;
import org.motion.motion_api.application.services.strategies.GerenteServiceStrategy;
import org.motion.motion_api.application.services.util.ServiceHelper;
import org.motion.motion_api.domain.entities.Oficina;
import org.motion.motion_api.domain.entities.pitstop.Gerente;
import org.motion.motion_api.domain.repositories.IOficinaRepository;
import org.motion.motion_api.domain.repositories.pitstop.IGerenteRepository;
import org.motion.motion_api.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.List;


@Service
public class GerenteService implements GerenteServiceStrategy {

    @Autowired
    IGerenteRepository gerenteRepository;

    @Autowired
    IOficinaRepository oficinaRepository;
    @Autowired
    ServiceHelper serviceHelper;

    @Autowired
    AuthorizationService authorizationService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;

    @Autowired
    private JavaMailSender emailSender;


    public List<Gerente> listarTodos() {
        return gerenteRepository.findAll();
    }

    public Gerente buscarPorId(int id) {
        return gerenteRepository.findById(id).orElseThrow(() -> new RecursoNaoEncontradoException("Gerente não encontrado com o id: " + id));
    }

    public Gerente criar(CreateGerenteDTO novoGerenteDTO) throws MessagingException {
        Oficina oficina = serviceHelper.pegarOficinaValida(novoGerenteDTO.fkOficina());
        verificarEmailDuplicado(novoGerenteDTO.email());
        //oficinaComGerenteCadastrado(oficina);
        String senhaGerada = geradorDeSenhaAleatoria();
        String senhaCriptografada = new BCryptPasswordEncoder().encode(senhaGerada);
        System.out.println(senhaGerada);
        Gerente gerente = new Gerente(novoGerenteDTO, oficina, senhaCriptografada);
        gerenteRepository.save(gerente);
        enviarEmailComSenha(novoGerenteDTO, senhaGerada);

        return gerente;
    }


    @Transactional
    public Gerente atualizar(int id, UpdateGerenteDTO updateGerenteDTO) {
        Gerente gerente = buscarPorId(id);
        gerente.setNome(updateGerenteDTO.nome());
        gerente.setSobrenome(updateGerenteDTO.sobrenome());
        gerenteRepository.save(gerente);
        return gerente;
    }

    @Override
    public void deletar(int id) {
        throw new NotImplementedException();
    }

    @Transactional
    public Gerente atualizarSenha(int id, UpdateSenhaGerenteDTO updateSenhaGerenteDTO) {
        Gerente gerente = buscarPorId(id);
        gerente.setSenha(updateSenhaGerenteDTO.senha());
        gerenteRepository.save(gerente);
        return gerente;
    }


    public LoginGerenteResponse login(@Valid LoginGerenteRequest request) {
        Gerente gerente = gerenteRepository.findGerenteByEmail(request.email());
        if (gerente == null)
            throw new RecursoNaoEncontradoException("Usuário não encontrado com email: " + request.email());

        var usernamePassword = new UsernamePasswordAuthenticationToken(request.email(), request.senha());
        var auth = authenticationManager.authenticate(usernamePassword);
        String token = tokenService.generateToken((Gerente) auth.getPrincipal());

        return new LoginGerenteResponse(gerente.getIdGerente(), gerente.getEmail(), gerente.getSobrenome(), gerente.getStatus(), gerente.getOficina(), token);
    }


    private void verificarEmailDuplicado(String email) {
        if (gerenteRepository.existsByEmail(email)) {
            throw new DadoUnicoDuplicadoException("Email já cadastrado");
        }
    }


    /**
     * @param oficina
     * @return void
     * @throws DadoUnicoDuplicadoException
     */
    private void oficinaComGerenteCadastrado(Oficina oficina) {
        if (gerenteRepository.existsByOficina(oficina))
            throw new DadoUnicoDuplicadoException("Oficina com gerente já cadastrado");
    }


    private String geradorDeSenhaAleatoria() {
        final String CARACTERES_PERMITIDOS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder(6);
        for (int i = 0; i < 6; i++) {
            int indice = random.nextInt(CARACTERES_PERMITIDOS.length());
            sb.append(CARACTERES_PERMITIDOS.charAt(indice));
        }
        return sb.toString();
    }

    private void enviarEmailComSenha(CreateGerenteDTO user, String password) throws MessagingException {
        String htmlBody = "<html>" +
                "<head><link href='https://fonts.googleapis.com/css2?family=Roboto:wght@400;700&display=swap' rel='stylesheet'></head>" +
                "<body style='font-family: Roboto, sans-serif;'><h2>Olá! " + user.nome() + "</h2>" +
                "<p>Sua nova senha é: <strong>" + password + "</strong></p>" +
                "<p>Ela poderá ser utilizada no seu primeiro acesso</p>" +
                "<p>Atenciosamente,</p>" +
                "<p>A equipe motion</p>" +
                "</body></html>";


        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
        helper.setTo(user.email());
        helper.setSubject("Sua nova senha");
        helper.setText(htmlBody, true); // Habilita o processamento de HTML
        emailSender.send(message);
    }



}
