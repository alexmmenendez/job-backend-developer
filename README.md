# Job-Backend-Developer

## Instruções

   1) - Para rodar a aplicação é necessário dizer antes que neste app é utilizado:

    - Spring Security com JWT para autenticação.
    - H2 Database Engine

    Desta forma quando for startada a aplicação, será criado um cliente admin (user: alex.menendez, password:123),
    que deverá ser autenticado e partir disto será possível realizar as demais requisições.

   2) - Utilize o Postman(https://www.getpostman.com/downloads/) para testar a aplicação.

        - No projeto está disponível uma collection com login, e o crud do cliente.
        - Environment local: 'Localhost.postman_environment.json'
        - Environment remoto: 'Heroku.postman_environment.json'


    Link: https://job-case-intelipost.herokuapp.com/

   3) - Caso prefira fazer localmente o teste, certifique-se de ter git, java 1.8 e maven 3+ instalados

    Execute:

    $ git clone https://github.com/alexmmenendez/job-backend-developer.git

    $ mvn clean install -DskipTests=true

    $ mvn spring-boot: run


## Solução

    Assumo que quando li que o desafio estava em melhorar a performance e a latência com o bd fiquei intrigado em como fazer isso,
 uma vez que até então nunca precisei utilizar.

    Pesquisado a respeito dentro da stack do Spring, encontrei artigos sobre JPA Cache, basicamente é um gerenciador de caching
 para o Spring de forma inteligente, podendo restringir, criar condições de caching, liberar e inserir ao caching etc.

    Dentre algumas da funcionalidades sito em especial:

    @CachePut anotação ideal para atualizar dados que já foram cacheados, com esta anotanção o método será invocado e o retorno será
cacheado.

    @CacheEvict anotação para retirar do caching, pode ser utilizados em métodos de remoção, como utilizei, ou em métodos de rotina
para remover o que está a mais tempo em memória.

    Para demonstração do caching, na busca de um cliente há o método 'simulateSlowService', que realizada uma Thread.sleep();
simulando uma sistema lento. Após a primeira busca de um cliente x, na próxima não ocorrerá esperado sleep(), pois o Spring já
terá cacheado este e cliente e não invocará o método para buscar o cliente no banco novamente.
