##### [Voltar ao início](https://github.com/blestonbandeiraUPSKILL/upskill_java1_labprg_grupo2/tree/main/README.md)

# UC10 - Seriar Candidaturas de Anúncio (automaticamente)


## Formato Breve

O colaborador de organização inicia o processo de seriação dos candidatos a um anúncio por si publicado. O sistema informa os candidatos habilitados para seriação e solicita ao colaborador informar se deseja proceder a seriação das candidaturas. O colaborador confirma que deseja proceder a operaçãoo de seriação. O sistema realiza a seriação automática das candidaturas e apresenta o resultado ao colaborador. O sistema regista o resultado da seriação juntamente com a data/hora atual e informa ao colaborador do sucesso da operação.

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

- As candidaturas à tarefa são seriadas automaticamente, por preço, de acordo com o regime pré-estabelecido pelo colaborador da organização.

**_Cenário de sucesso principal:_**

1.	O colaborador da organização consulta a lista de tarefas publicadas que estejam com o período de seriação aberto e ainda não estejam seriadas.
2.  O colaborador seleciona uma tarefa desta lista e solicita ao sistema a lista de candidaturas válidas para este anúncio.
3.  O sistema informa as candidaturas válidas recebidas para este anúncio e solicita ao colaborador informar se deseja proceder com a seriação dessas candidaturas.
4.  O colaborador confirma. O sistema procede a seriação automática das candidaturas e mostra ao colaborador a lista final da seriação das candidaturas.
5.  O sistema regista a seriação dada às candidaturas e informa o sucesso da operação.


### Fluxos alternativos

1.	O colaborador cancela a seriação da tarefa.
    a.	O caso de uso termina.
2.	O sistema detecta que não existem tarefas publicadas pelo colaborador.
    a.	O caso de uso termina. 
3.	O sistema detecta que não existem anúncios em período de seriação.
    a.	O caso de uso termina.
4.  O sistema detecta que não houve nenhuma candidatura ao anúncio em período de seriação.
    a.	O caso de uso termina.


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
