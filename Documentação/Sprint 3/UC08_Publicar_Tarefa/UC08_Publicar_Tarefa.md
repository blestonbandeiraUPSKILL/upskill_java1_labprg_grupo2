##### [Voltar ao início](https://github.com/blestonbandeiraUPSKILL/upskill_java1_labprg_grupo2/tree/main/README.md)

# UC08 - Publicar Tarefa

## Formato Breve

O colaborador da organização inicia a publicação de uma tarefa. O sistema solicita a identificação da tarefa previamente criada pelo colaborador da organização. O colaborador identifica a tarefa. O sistema solicita os dados necessários para a publicação da tarefa (i.e. período de publicitação, período de apresentação de candidaturas, período de seriação, período de decisão e tipo de regimento aplicável). O colaborador da organização introduz os dados solicitados. O sistema valida e apresenta os dados ao colaborador da organização e pede a sua confirmação. O colaborador da organização confirma. O sistema regista os dados e cria um anúncio e informa o colaborador do sucesso da operação.

## Formato Completo

**_Ator Primário:_**

- Colaborador da organização.

**_Partes interessadas e seus interesses:_**

- Organização: pretende consultar e analisar uma tarefa previamente para a poder publicar.
- T4J: pretende que sejam publicadas tarefas na plataforma para que posteriormente sejam feitas candidaturas às mesmas.
- Freelancer: pode candidatar-se a tarefas que tenham sido publicadas.

**_Pré-condições:_**

- O colaborador deve estar registado na plataforma.

**_Pós-condições_**

- A tarefa deve estar publicada na plataforma.

**_Cenário de sucesso principal:_**

1.	O colaborador acede à área de publicação de tarefas.
2.	O sistema verifica se a organização tem tarefas por publicar.
3.	O sistema confirma que existem tarefas por publicar e retorna uma lista das mesmas.
4.	O colaborador seleciona uma tarefa para publicar.
5.	O sistema solicita a introdução de:
    a.	Período de publicitação;
    b.	Período de apresentação de candidaturas;
    c.	Período de seriação e adjudicação.
6.	O colaborador introduz os dados solicitados.
7.	O sistema confirma a introdução.
8.	O sistema torna a tarefa pública, e valida a sua publicação.
9.	O sistema retorna uma mensagem de sucesso: a tarefa foi publicada.
10.	O colaborador termina o processo.

### Fluxos alternativos

1.	O sistema não encontra tarefas por publicar relativas à organização do colaborador. <br/>
    a. O caso de uso termina.
2.	Dados mínimos obrigatórios em falta: <br/>
    a.	O sistema informa quais os dados em falta.
    b.	O sistema permita a introdução dos dados em falta (passo 4).
    c.	O colaborador da organização não introduz os dados em falta.
    O caso de uso termina.

## Diagrama de Sequência do Sistema
![UC08_Publicar_Tarefa](https://github.com/blestonbandeiraUPSKILL/upskill_java1_labprg_grupo2/blob/main/Documenta%C3%A7%C3%A3o/Sprint%203/UC08_Publicar_Tarefa/UC08_Publicar_Tarefa.png)

## Excerto do Modelo de Domínio
![UC08_ExcertoMD_Publicar_Tarefa](https://github.com/blestonbandeiraUPSKILL/upskill_java1_labprg_grupo2/blob/main/Documenta%C3%A7%C3%A3o/Sprint%203/UC08_Publicar_Tarefa/UC08_ExcertoMD_Publicar_Tarefa.png)

## Diagrama de sequência <br/>
![UC03_Definir_Categoria_Tarefa_Diagrama_Sequencia](UC03_Definir_Categoria_Tarefa_Diagrama_Sequencia.png)

## Diagrama de Classes <br/>
![UC03_Definir_Categoria_Tarefa_Diagrama_Classes](UC03_Definir_Categoria_Tarefa_Diagrama_Classes.png)

## Plano de Testes <br/>
[UC03 - Definir Categoria de Tarefa - Plano de Testes](UC03_Definir_Categoria_Tarefa_Plano_Testes.md)

##### [Voltar ao início](https://github.com/blestonbandeiraUPSKILL/upskill_java1_labprg_grupo2/tree/main/README.md)
