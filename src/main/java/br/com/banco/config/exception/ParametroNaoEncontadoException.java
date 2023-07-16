package br.com.banco.config.exception;

public class ParametroNaoEncontadoException extends RuntimeException {
    public ParametroNaoEncontadoException(String mensagem) {
        super(mensagem);
    }
}
