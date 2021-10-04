package br.com.sicredidesafio.service;

import br.com.sicredidesafio.exceptiontreatment.NegocioException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class ObterStatusCPF {

    public boolean statusAssociado(String cpf) {

        RestTemplate restTemplate = new RestTemplate();
        RestTemplateBuilder  restTemplateBuilder = new  RestTemplateBuilder();
        restTemplate = restTemplateBuilder.build();

        try {
            String status = restTemplate.getForObject("https://user-info.herokuapp.com/users/" + cpf , String.class);

            if (status.indexOf("ABLE_TO_VOTE") != -1) {
                return true;
            }

            return false;

          } catch (NegocioException ex){
            log.info("CPF do associado é inválido" );
            throw new NegocioException("CPF do associado é inválido");
          }
    }
}
