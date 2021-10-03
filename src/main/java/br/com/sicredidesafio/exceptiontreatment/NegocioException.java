package br.com.sicredidesafio.exceptiontreatment;


public class NegocioException  extends RuntimeException {

    private static final long serialVersionUID = 6842909593968026140L;

    public NegocioException(String message) {
        super(message);
    }
}
