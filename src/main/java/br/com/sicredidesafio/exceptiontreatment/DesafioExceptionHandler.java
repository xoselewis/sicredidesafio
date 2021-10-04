package br.com.sicredidesafio.exceptiontreatment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class DesafioExceptionHandler extends ResponseEntityExceptionHandler {

      @Autowired
      private MessageSource messageSource;

      @ExceptionHandler(RegistroFaltanteException.class)
      public ResponseEntity<Object> registroFaltanteException(RegistroFaltanteException ex, WebRequest request) {

            return corpoTratamentoException(ex, new HttpHeaders(), HttpStatus.NOT_FOUND,  request, ex.getMessage(), null);
      }

      @ExceptionHandler(NegocioException.class)
      public ResponseEntity<Object> tratamentoExecptioNegocio(NegocioException ex, WebRequest request) {

            return corpoTratamentoException(ex, new HttpHeaders(), HttpStatus.BAD_REQUEST,  request, ex.getMessage(), null);
      }

      @Override
      protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

            List<ErroFields> erroFields = new ArrayList<>();

            for (ObjectError erro  : ex.getBindingResult().getAllErrors()) {
                  String nome;
                  if(erro instanceof FieldError) {
                        nome = ((FieldError) erro).getField();
                  } else {
                        nome = erro.getObjectName();
                  }
                  erroFields.add(new ErroFields(nome, messageSource.getMessage(erro, LocaleContextHolder.getLocale())));
            }

            return corpoTratamentoException(ex, headers, status,  request, "Campos não preenchidos, favor completar!", erroFields);
      }

       private  ResponseEntity<Object> corpoTratamentoException(Exception ex, HttpHeaders headers, HttpStatus status,  WebRequest request, String mensagem, List<ErroFields> erroFields) {
             ErroControllers erroControllers = new ErroControllers();
             erroControllers.status = status.value();
             erroControllers.mensagem = mensagem;
             erroControllers.dataHoraErro = LocalDateTime.now();
             erroControllers.erroFields =  erroFields;

             return super.handleExceptionInternal(ex, erroControllers, headers, status, request);
       }

}
