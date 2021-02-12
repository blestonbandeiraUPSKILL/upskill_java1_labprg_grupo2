##### [Voltar ao início](https://github.com/blestonbandeiraUPSKILL/upskill_java1_labprg_grupo2/tree/main/README.md)

# title UC10 - Seriação (Não Automática) de Candidaturas


## Formato Breve

O colaborador de organização inicia o processo não automático de seriação dos candidatos à realização de um anúncio por si publicado. O sistema solicita dados (i.e. o anúncio, a classificação de cada uma das candidaturas e os outros colaboradores da organização participantes no processo). O colaborador introduz os dados solicitados. O sistema valida e apresenta os dados, pedindo que os confirme. O colaborador confirma. O sistema regista os dados juntamente com a data/hora atual e informa o colaborador do sucesso da operação.

## Formato Completo

**_Ator Primário:_**

- Colaborador da organização.

**_Partes interessadas e seus interesses:_**

- T4J: pretende que a plataforma seleccione candidaturas para a realização de tarefas publicadas pelas organizações.
- Organização: pretende estabelecer critérios que vão ao encontro das necessidades de cada tarefa.

**_Pré-condições:_**

1.	O colaborador da organização tem de estar registado na plataforma.
2.	A tarefa tem de estar publicada.
3.	O período de apresentação de candidaturas tem de ter terminado.
4.	O período de seriação e adjudicação das tarefas tem de ter iniciado.
5.	As candidaturas à tarefa não podem ainda ter sido seriadas.
6.	A tarefa não pode ainda ter sido adjudicada.
7.	Tem de existir pelo menos uma candidatura à oferta supra indicada.

**_Pós-condições_**

- As candidaturas à tarefa são seriadas de acordo com o regime pré-estabelecido pelo colaborador da organização.

**_Cenário de sucesso principal:_**

1.	O colaborador da organização consulta a lista de tarefas publicadas e por adjudicar. 
2.	O colaborador da organização solicita ao sistema a seriação das candidaturas. 
    a.	No caso de seriação subjectiva com atribuição opcional, se não houver nenhuma candidatura que preencha os critérios definidos, não é seleccionada nenhuma candidatura.
    b.	No caso de seriação subjectiva com atribuição obrigatória, o colaborador selecciona um freelancer, que cumpra os requisitos definidos pela organização, para a realização da tarefa.    
3.	O sistema procede à seriação das candidaturas e apresenta ao colaborador da organização uma candidatura correspondente aos critérios seleccionados.


### Fluxos alternativos

1.	O colaborador cancela a seriação da tarefa.
    a.	O caso de uso termina.
2.	O sistema detecta que não existem tarefas publicadas por adjudicar.
    a.	O caso de uso termina.
3.	O sistema detecta que não existem tarefas publicadas.
    a.	O caso de uso termina.
4.	O sistema detecta que a tarefa cujas candidaturas o colaborador pretende seriar ainda se encontra no período de apresentação de candidaturas.
    a.	O caso de uso termina.
5.	O sistema detecta que a tarefa ainda não se encontra no período de seriação e adjudicação de tarefas, ou que esse período já expirou.
    a.	O caso de uso termina.


## Diagrama de Sequência do Sistema
![UC10_Seriação_(Não_Automática)_de_Candidaturas_SSD](UC10_Seriação_(Não_Automática)_de_Candidaturas_SSD.png)

## Excerto do Modelo de Domínio
![UC10_Seriação_(Não_Automática)_de_Candidaturas_Modelo_Domin](UC10_Seriação_(Não_Automática)_de_Candidaturas_Modelo_Domin.png)

## Diagrama de sequência <br/>
![UC10_Seriação_(Não_Automática)_Candidaturas_DS](UC10_Seriação_(Não_Automática)_Candidaturas_DS.png)

## Diagrama de Classes <br/>
![UC10_Seriação_(Não_Automática)_de_Candidaturas_Diagrama_Classes](UC03_Definir_Categoria_Tarefa_Diagrama_Classes.png)

## Plano de Testes <br/>
[UC10 Seriação (Não Automática) de Candidaturas - Plano de Testes](UC10_Seriação_(Não_Automática)_de_Candidaturas_Plano_Testes.md)

##### [Voltar ao início](https://github.com/blestonbandeiraUPSKILL/upskill_java1_labprg_grupo2/tree/main/README.md)
