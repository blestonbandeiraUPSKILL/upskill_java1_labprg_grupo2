# UC13-Registar Organização

## Formato Breve

A entidade verificadora acede ao perfil do candidato a freelancer e procede à verificação e validação dos seus dados e competências atribuindo a cada uma um nível de proeficiência de acordo com os graus previamente definidos em cada competência técnica. Após o termino deste processo a entidade verificadora informa a plataforma da sua decisão considerando a veracidade dos dados/competências introduzidos pelo candidato a freelancer. O sistema devolve uma mensagem á entidade verificadora e envia notificação ao adminstrativo que existe um candidato válido para registar.

## Formato Completo

### Actor Principal

* Entidade Verificadora

### Partes interessadas e seus interesses

* Administrativo: Pretende que os candidatos a freelancers sejam válidos para que possam ser registados.
* T4J: pretende que a plataforma tenha freelancers válidos.
* Candidato a Freelancer: Pretende que a sua candidatura seja verificada e validada.

### Pré-condições

* A Entidade Verificadora tem de ter acesso aos dados dos candidatos.
* Tem de haver pelo menos um candidato a freelancer.

### Pós-condições

* A candidatura a freelancer é validada para ser registada.

### Cenário de sucesso principal

1. A entidade acede aos dados do candidato a freelancer.
2. A entidade verifica e valida todos os dados introduzidos pelo candidato(nome,NIF,endereço postal,contacto telefónico,e-mail).
3. A entidade verifica e valida as habilitações nomeadamente(grau, designação do curso, instituição que concede o grau; média do curso).
4. A entidade verifica as competências introduzidas pelo candidato e atribui um grau de proficiência a cada uma delas.
5. O sistema informa a entidade que o candidato foi validado com sucesso.

### Fluxos alternativos

1. A Entidade Verificadora cancela a verificação e validação do candidato. a) O caso de uso termina.
2. O sistema detecta que não existem candidaturas por validar. a) O caso de uso termina.

### SSD
![UC13-SSD](UC13-SSD.png)

### Diagrama de Sequência
![UC13_Diagrama_de_Sequência.png](UC13_Diagrama_de_Sequência.png)

### Diagrama de Classes
![UC13-Verificação e Validação dos Candidatos](UC13-Verificação_e_Validação_dos_Candidatos.png)

##### [Voltar ao início](https://github.com/ajorgesantosp/upskill_java1_g1/blob/main/README.md)
