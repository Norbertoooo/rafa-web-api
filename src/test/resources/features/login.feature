# language: pt

Funcionalidade: Login

  Cenário: Retornar erro ao tentar efetuar login
    Dado Que exista não exista o seguinte Login
      | email             | senha          | perfil      |
      | teste@gmail.com   | loginteste     | TERAPEUTA   |
    Quando for solicitada o login com os sequintes dados
      | email             | senha          | perfil      |
      | teste@gmail.com   | loginteste     | TERAPEUTA   |
    Então usuario deve ser autenticado
