# Workplace-Meli



### Equipe 4 W-3

Requesito 6 bootcampw3



## Projeto Final Wave-3 Digital-House



***API Meli-Frios, aplicação desenvolvida para compra e venda de produtos frescos.***



### **Antes de usar a API siga as \*instruções de autenticação na documentação\* para ter seu token de acesso**



#### **Faça o clone do projeto e o execute, e faça uso do swagger. Seguindo as instruções abaixo na documentação**

Em seu **application.propeties**, insira as seguintes linhas:

```
spring.datasource.url=jdbc:mysql://localhost:3306/bootcampw3 /*nome do banco MySQL => bootcampw3*/
spring.datasource.username=root /*usuário*
spring.datasource.password	/*senha*/
```



<u>Insira no banco a seguinte Query para criar seu usuário:</u>



***INSERT** **into** users (username ,**password** )**values**('thiago','$2a$10$nuNzCf8js96QGhe53QeBTu3PEBcJV1sCY9xuE47kTIIAvSgzm8fYy');*

***UPDATE** users **set** enabled=**TRUE**;*



## Auth

Autenticação se faz necessária para utilizar os endpoints.



### AUTH - User



seguindo o exemplo abaixo de usuário teste, use para se registrar no banco:



```
{
  "user": "thiago",
  "senha": "123"
}
```



No End-Point a seguir:

***Link para uso:***

http://localhost:8080/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#/autenticacao-controller



Com o token em mãos, você pode criar o seu usuário, seguindo o modelo abaixo:

* Pegue o token gerado e libere no botão "**Authorize**" ***no lado direito superior da página***, inserindo o token para uso da documentação Swagger.

​		- após liberação no sistema, você poderá usar a documentação por completo.



**Requisoito 6**

Importante:
No sistema **Mel Frios 1.0**, é possível controlar o meu estoque saber os produtos que estão para vencer, cadastrar novos produtos etc… porem não  e possivel enviar o pedido na casa do meu cliente, e gostaria de ter este novo recurso no meu sistema.

Como melhoria para este requisito, foi possivel criar um sistema de Shipping onde e possivel enviar. um produto para o cliente e o mesmo pode rastrear o andamento do seu roduto por meio do um codigo de rastreio ate a casa do cliente.

Para resover o problema proposto neste senario foi possivel elaborar uma atualizacao esta esta descrita no arquivo abaixo;

https://github.com/pedroallima/BootCampFinalG4W3/blob/master/database/Requisito-6-entrega.pdf

Obs: Neste projeto e possivel utilizar o Postman ou o Swegger para poder testar os recursos de Back-end.

![image](https://github.com/pedroallima/BootCampFinalG4W3/blob/master/database/diagram/Requisito-6.png?raw=true)




**Meli Frios 1.1
---

### GET - ml-shipping-01 

*Veja uma lista dos produtos, com o histórico de acompanhamento, se o rastreamento não existir deve retornar um **"404 Not Found"**. *



Pare poder utilizar que o codigo de rastreamento ja tenha sido gerado anteriormente e alimentado com os historico do codigo de rastreio, antes se faza necessario autenticarse: "**user**" e colocar no campo de senha o seu **token** gerado no passo anterior:

```
/api/v1/fresh-products/shippingcode/list/{CODIGO_RASTREIO}

```

***Link para Swegger:***

http://localhost:8080/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#/shipping-controller/litShipping

***Link para POstman:***

http://localhost:8080/api/v1/fresh-products/shippingcode/list/{CODIGO_RASTREIO}

Exempode codigo a ser colocado **

**ML3253220BR**
---
---

#### GET - ml-shipping-02

É possível adicionar novos status do pedido, tornando possivel, gerando um histórico de acompanhamento, para o cliente.
Informando o id do codigo de envio e o numero do Status que sera adicionado para o cliente.


```
{
        "id_status":7,
        "id_shipping":1
}
```


***Link para Swegger:***

http://localhost:8080/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#/shipping-history-controller/saveInboundOrder

***Link para POstman:***

http://localhost:8080/api/v1/fresh-products/shippingHistory/save

---
---


#### GET - ml-shipping-03

Neste local e possivel Criar um codigo de rastreamento para que o comprado possa aconpanhar o seu pedido, desde a saida da WhareHouse ate a endereco de destino.


```
{
        "id_status":7,
        "id_shipping":1
}
```



***Link para Swegger:***

http://localhost:8080/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#/shipping-history-controller/saveInboundOrder

***Link para POstman:***

http://localhost:8080/api/v1/fresh-products/shippingHistory/save
