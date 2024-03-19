# Projeto de reskilling da Accenture

Projeto de aplicação bancária que permite transferências de valores.

# Diagrama

A seguir o diagrama de classes referente a aplicação:

<img src="https://github.com/marcelotvaz/reskilling-accenture-java/blob/main/images/Diagram-2024-03-19-173439.png" alt="Diagrama">

# Instruções

A aplicação simula um banco, quando ela é iniciada, aparece um menu inicial com as opções:

1 - Acessar conta
2 - Criar conta
3 - Sair

A primeira opção lhe dará acesso a uma conta existente no sistema, ao clicar nela será pedido o número da conta.
OBS: Foi criada uma conta de amostra com o número 123123

É possível também criar uma conta digitando 2, poderá ser criada tanto para pessoa física como pessoa jurídica

Ao acessar ou criar conta, a aplicação te jogará no menu principal, onde existirá as seguintes opções:

1 - Saldo
2 - Depósito
3 - Saque
4 - Transferências
5 - Extrato
6 - Deslogar

São funcionalidades padrões do banco, onde existirá uma interação com o valor na conta do usuário que inicialmente é 0.

No caso do extrato, será criado um arquivo .csv na pasta "c:\temp" do seu computador

Existe a funcionalidade que impede o funcionário de fazer transações maiores que o limite estabelecido por ele durante o periodo noturno (Das 20:00 até as 08:00)