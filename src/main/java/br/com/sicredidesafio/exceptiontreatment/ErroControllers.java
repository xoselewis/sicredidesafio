package br.com.sicredidesafio.exceptiontreatment;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class ErroControllers {
        Integer status;
        LocalDateTime dataHoraErro;
        String mensagem;
        List<ErroFields> erroFields ;
}
