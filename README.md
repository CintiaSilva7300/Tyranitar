## Java Spring Boot

## Requisitos

* Docker
* MSQL
* Java - Spting Boot

# Em um terminal de sua escolha Executar os comano na sequencia para baixar a imagem o HabbitMQ e execuatr:

```
docker pull rabbitmq
docker run --rm -d -it -p 15672:15672 -p 5672:5672 rabbitmq:3-management
```
## Acesso do HabiitMQ

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
* Nessa requição enviar um arquivo no formato csv para que ele seja processado e enviado para uma fila no Habbitmq
* Acessar o caminho para fazer a requicição o tipo 'POST' no postman http://localhost:8080/api/financial-upload

Local:
```
http://localhost:8080/api/financial-upload
```

Clone do seguno repositorio, que é reponsavel por ler os dados enviados para o HabbitMQ e salva-los no banco de dados

```
- git clone https://github.com/CintiaSilva7300/financial-transaction.git
- cd financial-transaction
```

Executar o projeto para que ele consuma os daos enviados e salve no banco de dados
