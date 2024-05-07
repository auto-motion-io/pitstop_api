package org.motion.motion_api.application.services.observer;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.Data;
import org.motion.motion_api.domain.entities.pitstop.Gerente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;


@Service
public class AccountCreationNotificationObserver implements Observer {

    @Autowired
    private JavaMailSender emailSender;

    @Override
    public void update(Object data) {
        if (data instanceof AccountCreationData) {
            AccountCreationData notificationData = (AccountCreationData) data;
            Gerente gerente = notificationData.getGerente();
            String senhaGerada = notificationData.getGeneratedPassword();

            String htmlBody = "<html>" +
                    "<head><link href='https://fonts.googleapis.com/css2?family=Roboto:wght@400;700&display=swap' rel='stylesheet'></head>" +
                    "<body style='font-family: Roboto, sans-serif;'><h2>Olá! " + gerente.getNome() + "</h2>" +
                    "<p>Sua nova senha é: <strong>" + senhaGerada + "</strong></p>" +
                    "<p>Ela poderá ser utilizada no seu primeiro acesso</p>" +
                    "<p>Atenciosamente,</p>" +
                    "<p>A equipe motion</p>" +
                    "</body></html>";


            try {
                MimeMessage message = emailSender.createMimeMessage();
                MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
                helper.setTo(gerente.getEmail());
                helper.setSubject("Sua nova senha");
                helper.setText(htmlBody, true); // Habilita o processamento de HTML
                emailSender.send(message);
            }catch (MessagingException ex){
                ex.printStackTrace();
            }
        }
    }


    @Data
    public static class AccountCreationData {
        private Gerente gerente;
        private String generatedPassword;

        public AccountCreationData(Gerente gerente, String senhaGerada) {
            this.gerente = gerente;
            this.generatedPassword = senhaGerada;
        }
    }
}

