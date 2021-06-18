<a href="https://www.santander.com.br/">
<h1 align="center">
    <img width="300" src="https://upload.wikimedia.org/wikipedia/commons/b/b8/Banco_Santander_Logotipo.svg">
</h1></a>

# 😃 SCHOOL SANTANDER 👍
Criado para demonstrar minhas habilidades com a linguagem Java
em construções backend.
Desenvolvi essa API utilizando de ferramentas como Spring Boot,
Spring Security, entre outras para que esse projeto tenha como
uma base de consulta e cadastro de estudantes, professores e
cursos.

## 🧩 Tecnológias utilizadas
Foram utilizadas as seguintes tecnólogias para o desenvolcimento
do projeto SCHOOL <img width="90" src="https://upload.wikimedia.org/wikipedia/commons/b/b8/Banco_Santander_Logotipo.svg">
.

- [Java](https://docs.oracle.com/en/java/) ✌
- [Spring Boot](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/)


- [Maven](https://maven.apache.org/guides/index.html)
- [Apache Tomcat](http://tomcat.apache.org/tomcat-8.5-doc/)
- [Spring Web](https://docs.spring.io/spring-framework/docs/current/reference/html/web.html#spring-web)
- [Spring Security](https://docs.spring.io/spring-security/site/docs/current/reference/html5/)
- [Spring Data MongoDB](https://spring.io/projects/spring-data-mongodb)
- [Lombok](https://projectlombok.org/) 👍
- [Swagger](https://swagger.io/)
- [MongoDB](http://localhost:8081/pb/santander/courses/all)

## 💾 Como baixar o projeto
```bash
    # Clonar o projeto do repositório GitHub
    $ git clone https://github.com/MauricioMH35/SantanderSchool.git
    
    # Entrar no diretório do projeto clonado na sua máquina
    $ cd SantanderSchool
    
    # Instalando as dependências do Maven (pom.xml)
    $ mnv clean install
    
    # Inicia o projeto utilizando o Maven
    $ mvn spring-boot:run
```

## Para a utilização da API
Para utilizar a API será necessário que se tenha configurado 
no arquivo `application.yml` a `uri` de seu banco de dados 
`MongoDB` e inserir um usuário com o seguinte `JSON`: 

Informações do arquivo `application.yml`:
```yml
 spring:
  data:
    mongodb:
      uri: mongodb://localhost:27017/santanderSchool
      # Trocar o endereço URI para a conexão com o seu
      # banco de dados MongoDB
```

Corpo das informações JSON que podem ser trocadas, com exceção 
das `roles`, assim pode obter acesso a outros endpoints:
```json
    {
        "name": "Santander Developer",
        "cpf": "000.000.00-00",
        "email": "developer.jr@santander.com",
        "username": "santanderDeveloper",
        "password": "dev",
        "roles": "user, admin"
    }
```

Depois de que a string de conexão com o banco de dados MongoDB 
foi configurado de acordo com as configurações para com o seu 
banco, seja remoto ou local, como o caso mostrado no projeto, e 
inserido o usuário ao banco de dados, com o modelo de dados que 
foi apresentado.
E lembrando de não mudar os valores da tag `roles` para que o seu
usuário criado tenha acesso de administrado e usuário, você deve 
ser capaz de visualizar um página como essa para efetuar o login e
visualizar a documentação da API:

Rota para acessar o formulário de login: 
```
    http://localhost:8081/login
``` 

Imgagem da página de login:
<img width="512" src="https://ik.imagekit.io/mauriciohidani/login_DRHJhHk8d.png">

Endereço da documentação criada com `Swagger`:
```
    http://localhost:8081/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config
```

Imagem da documentação:
<img wirdth="512" src="https://ik.imagekit.io/mauriciohidani/swagger_3eu5N2_S8.png">

Com a documentação será possível a visualização dos modelos das 
requisições e será possível executar testes com a API.