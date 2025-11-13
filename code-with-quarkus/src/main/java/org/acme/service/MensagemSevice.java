package org.acme.service;

import jakarta.enterprise.context.ApplicationScoped;

    @ApplicationScoped
    public class MensagemSevice {

        private String conteudo;

        public void enviarMensagem(String mensagem) {
            this.conteudo = mensagem;
            System.out.println("Confirme o conteúdo da mensagem: " + conteudo);
        }

        public String getConteudo() {
            return conteudo;
        }

    }
