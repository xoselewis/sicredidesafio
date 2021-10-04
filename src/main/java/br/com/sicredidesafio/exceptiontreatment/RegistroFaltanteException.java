package br.com.sicredidesafio.exceptiontreatment;

public class RegistroFaltanteException extends NegocioException {

    private static final long serialVersionUID = -3340722291686016781L;

    public RegistroFaltanteException(String message) {
        super(message);
    }
}
