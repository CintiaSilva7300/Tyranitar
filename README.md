## Java Spring Boot

## Requisitos

* Docker
* MSQL
* Java - Spting Boot

# Em um terminal de sua escolha Executar os comando na sequencia para baixar a imagem o RabbitMQ e execuatar:

```
docker pull rabbitmq
docker run --rm -d -it -p 15672:15672 -p 5672:5672 rabbitmq:3-management
```
## Acesso do RabbitMQ

```
http://localhost:15672/#/queues
```
Username: 
```
guest
```
Password:
```
 guest
```

Clone do repositorio, executar projeto na sua ide

```
- git clone https://github.com/CintiaSilva7300/Tyranitar.git
- cd Tyranitar
```

## Utilizar o postman para fazer uma requicição 

## obs: 
* Nessa requição enviar o arquivo (input-data) no formato csv para que ele seja processado e enviado para uma fila no Rabbitmq
* Acessar o caminho para fazer a requicição o tipo 'POST' no postman, local: http://localhost:8080/api/financial-upload

Local:
```
http://localhost:8080/api/financial-upload
```

Clone do segundo repositorio, que é reponsavel por ler os dados enviados para o RabbitMQ e salva-los no banco de dados (mysql)

```
- git clone https://github.com/CintiaSilva7300/financial-transaction.git
- cd financial-transaction
```

Executar o projeto (financial-transaction) para que ele consuma os daos enviados e salve no banco de dados (mysql)
