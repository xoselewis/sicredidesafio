package br.com.sicredidesafio.exceptiontreatment;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErroControllers {
        Integer status;
        LocalDateTime dataHoraErro;
        String mensagem;
        List<ErroFields> erroFields ;
}
