# language: pt

Funcionalidade: Login

  Cenário: Efetuar login com login já existente
    Dado Que exista o seguinte Login:
      | email             | senha          | perfil         |
      | teste@gmail.com   | loginteste     | ADMNISTRADOR   |
    Quando for solicitada o login
    Então usuario deve ser autenticado
