##### [Voltar ao início](https://github.com/blestonbandeiraUPSKILL/upskill_java1_labprg_grupo2/tree/main/README.md)

# UC13A - Atribuição de Tarefa (Automática)


## Formato Breve

Findo o período de seriação, e tendo esta sido realizada de forma automática, o sistema atribui a tarefa ao freelancer que apresentou o segundo preço mais baixo para a sua realização.


## Formato Completo

**_Ator Primário:_**

- Sistema

**_Partes interessadas e seus interesses:_**

- T4J: Pretende que as organizações consigam ter freelancers atribuídos às suas tarefas.
- Organização: Pretende que as suas tarefas sejam atribuídas a freelancers que se propõem realizá-las.
- Freelancer: Pretende ter tarefas atribuídas para que as possa realizar.

**_Pré-condições:_**

- O período de seriação tem de ter terminado.
- Tem de haver pelo menos duas candidaturas seriadas. 

**_Pós-condições_**

- A tarefa é atribuída ao freelancer que apresentou o segundo preço mais baixo.

**_Cenário de sucesso principal:_**

1. O sistema detecta que o período de seriação terminou.
2. O sistema atribui a tarefa ao freelancer que apresentou o segundo preço mais baixo.

### Fluxos alternativos

1.  Não se deu a seriação porque havia apenas uma candidatura à tarefa.
    a. A tarefa não é atribuída.
    b. O caso de uso termina.
2. O período de seriação não chegou ao fim.
    a. O caso de uso termina.

## Diagrama de Sequência do Sistema
![UC13A_AtribuicaoTarefa_Automatica_SSD](UC13A_AtribuicaoTarefa_Automatica_SSD.png)

## Excerto do Modelo de Domínio
![UC13A_AtribuicaoTarefa_Automatica_Modelo_Dominio](UC13A_AtribuicaoTarefa_Automatica_Modelo_Dominio.png)

## Diagrama de sequência <br/>
![UC13A_AtribuicaoTarefa_Automatica_Diagrama_Sequencia](UC13A_AtribuicaoTarefa_Automatica_Diagrama_Sequencia.png)

## Diagrama de Classes <br/>
![UC13A_AtribuicaoTarefa_Automatica_Diagrama_Classes](UC13A_AtribuicaoTarefa_Automatica_Diagrama_Classes.png)


##### [Voltar ao início](https://github.com/blestonbandeiraUPSKILL/upskill_java1_labprg_grupo2/tree/main/README.md)
