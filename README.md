# Desafio Técnico Back-end
****
## Aplicação com Spring-Boot.
Solução Backend para gerenciar sessões de votação de pautas. 
****

### Pré Requisitos

- [X] Java 11
- [X] RabbitMQ
- [X] Mysql

### Pode encontrar uso de:
- [X] Lombok
- [X] Swagger
- [X] Springdoc
- [X] JPA
****
### Funcionalidades implementadas e [disponiveis](https://sicredidesafio.herokuapp.com/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#/votacao-controller/cadastrar):
- Cadastrar uma nova pauta.
- Abrir uma nova seção de votação.
- Receber votos de associados.
- Contabiliza os votos e coloca o resultado via mensagem em lista a ser consumida por outros sistemas ou Api.

### Adicional
- Não disponivél na documentação gerada por solicitação mas presente no código implementação de CRUD completos.

