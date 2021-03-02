##### [Voltar ao início](https://github.com/blestonbandeiraUPSKILL/upskill_java1_labprg_grupo2/tree/main/README.md)

# UC10-A - Seriar Candidaturas de Anúncio (não automaticamente)


## Formato Breve

O colaborador de organização inicia o processo de seriação dos candidatos a um anúncio por si publicado que tenha como tipo de regimento atribuído a 'Seriação não automática'. O sistema informa os candidatos habilitados para seriação e solicita a inserção da ordenação a ser atribuída a cada candidato no processo de seriação deste anúncio. O colaborador introduz os dados solicitados. O sistema valida e apresenta os dados, pedindo que os confirme. O colaborador confirma. O sistema mostra a lista de colaboradores da organização e solicita informar se houve outros colaboradores envolvidos no processo de seriação. O colaborador seleciona os colaboradores adicionais envolvidos (caso haja) no processo de seriação. O sistema apresenta a lista de colaboradores adicionais participantes no processo de seriação, pedindo confirmação. O colaborador confirma. O sistema regista todos dados juntamente com a data/hora atual e informa o colaborador do sucesso da operação.

## Formato Completo

**_Ator Primário:_**

- Colaborador da organização.

**_Partes interessadas e seus interesses:_**

- Plataforma: Pretende que as Organizações possam seriar as candidaturas para a realização das tarefas por si publicadas.
- Organização: Pretende que os anúncios para as tarefas publicadas da organização tenham resultado seriado das candidaturas recebidas.
- Colaborador da organização: Pretende determinar e ordenar as melhores candidaturas às tarefas por si publicadas
- Freelancer: Pretende saber o resultado de sua candidatura a um anúncio.

**_Pré-condições:_**

1.	O colaborador da organização tem de estar registado na plataforma.
2.	O colaborador tem que ter publicado ao menos uma tarefa.
3.	O anúncio da tarefa publicada por este colaborador tem de estar no período de seriação de candidaturas.
4.  Deve haver ao menos uma candidatura válida ao anúncio da tarefa.
5.  A(s) candidatura(s) não poderm ter sido ainda seriadas.

**_Pós-condições_**

- As candidaturas ao anúncio da tarefa são seriadas de acordo com o regime pré-estabelecido pelo colaborador da organização.

**_Cenário de sucesso principal:_**

1.	O colaborador da organização consulta a lista de tarefas publicadas, com tipo de regimento atribuído sendo de 'Seriação não automática',
que estejam com o período de seriação aberto e ainda não estejam seriadas.
2.  O colaborador seleciona uma tarefa desta lista e solicita ao sistema a lista de candidaturas válidas para este anúncio.
3.  O sistema informa as candidaturas válidas recebidas para este anúncio e solicita ao colaborador a seriação dessas candidaturas.
4.  O colaborador informa, para cada candidatura válida recebida, o valor de colocação ordenada para o processo de seriação deste anúncio.
5.  O sistema mostra a lista ordenada das candidaturas e pede confirmação.
6.  O colaborador confirma a ordenação das candidaturas ao sistema.
7.  O sistema mostra a lista de colaboradores da organização e solicita informar se houve colabores adicionais envolvidos no processo de seriação.
8.  O colaborador informa os colaboradores adicionais envolvidos no processo de seriação (caso haja). 
9. O sistema mostra a lista dos colaboradores adicionais envolvidos e solicita confirmação. 
10.  O colaborador confirma.
11.  O sistema regista todos dados juntamente com a data/hora atual e informa o colaborador do sucesso da operação


### Fluxos alternativos

1.	O colaborador cancela a seriação da tarefa.
    a.	O caso de uso termina.
2.	O sistema detecta que não existem tarefas publicadas pelo colaborador.
    a.	O caso de uso termina. 
3.	O sistema detecta que não existem anúncios em período de seriação.
    a.	O caso de uso termina.
4.  O sistema detecta que não houve nenhuma candidatura ao anúncio em período de seriação.
    a.	O caso de uso termina.
5.  O colaborador insere a mesma colocação para duas candidaturas diferentes.
    a.	O sistema permite a correção dos dados introduzidos (passo 3).
    b.  O colaborador não altera os dados.
    c.  O caso de uso termina.

## Diagrama de Sequência do Sistema
![UC10_Seriação_(Não_Automática)_de_Candidaturas_SSD](UC10_Seriação_(Não_Automática)_de_Candidaturas_SSD.png)

## Excerto do Modelo de Domínio
![UC10_Seriação_(Não_Automática)_de_Candidaturas_Modelo_Domin](UC10_Seriação_(Não_Automática)_de_Candidaturas_Modelo_Domin.png)

## Diagrama de sequência <br/>
![UC10_Seriação_(Não_Automática)_Candidaturas_DS](UC10_Seriação_(Não_Automática)_Candidaturas_DS.png)

## Diagrama de Classes <br/>
![UC10_Seriação_(Não_Automática)_Candidaturas_DC](UC10_Seriação_(Não_Automática)_Candidaturas_DC.png)

## Plano de Testes <br/>
[UC10 Seriação (Não Automática) de Candidaturas - Plano de Testes](UC10_Seriação_(Não_Automática)_de_Candidaturas_PlanoTestes.md)

##### [Voltar ao início](https://github.com/blestonbandeiraUPSKILL/upskill_java1_labprg_grupo2/tree/main/README.md)
