API REST - commands

localhost:8080/pizza - method.GET -> Retorna todos pedidos
localhost:8080/pizza - method.POST -> Adiciona um pedido
localhost:8080/pizza/{pid}}/personalizar - method.POST -> Adiciona uma personalizacao a pizza pid
localhost:8080/pizza/{pid} - method.GET -> Retorna um pedido pid específico


JSON body example, pedido:
{"tamanho":"media", "sabor":"calabresa"}

JSON body example, personalizacao:
{"nome":"sem cebola"}

Utilizando h2 database para persistir os dados.