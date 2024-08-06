# sistemadecadastrosimples
Projeto simples de estudo com o intuito de melhorar a base na linguagem Java. Projeto baseado em um ROADMAP de Java feito pelo Lucas Carrilho @devmagro.
Acesso ao roadmap => https://docs.google.com/document/d/12ek1Wsd_ibuwTOjHtLPZwEWdy5-A7cRoO2Bf-v5G1_s/edit

# Explicando projeto
Sistema de cadastro simples utilizando apenas metodos nativos do Java, adicionando os cadastros ao diretorio pessoascadastradas/ em formato .txt. 
## O sistema possui funções de:##

- Cadastrar novos usuarios
- Listar usuarios cadastrados
- Cadastrar novas perguntas
- Deletar perguntas
- Pesquisar usuarios cadastrados.

A maior dificuldade que enfrentei ao fazer esse projeto foi a formatação dos dados após a exclusão de algum dado, seja ele usuarios ou perguntas do formulário.Devido a fato de usuarios e perguntas tivessem "id" o planejamento de formatação foi pensado de forma que após a exclusão de uma pergunta, as linhas em brancos seriam removidas e todas as perguntas iriam ser sobrescritas com novos id.O mesmo valia, mesmo sem a opção de excluir algum usuario cadastrados, o sistema foi planejado para mesmo que um usuario for excluido de forma manual o método Usuarios.formatarUsuarios(),formata todos os cadastros ao fim da execução do sistema.
