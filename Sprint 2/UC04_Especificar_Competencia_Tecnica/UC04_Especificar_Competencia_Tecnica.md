# UC04-Especificar Competência Técnica

## Formato Breve

O administrativo inicia a especificação de uma competência técnica. O sistema solicita os dados necessários (i.e. código único, área de atividade e descrição breve e detalhada). O administrativo introduz os dados solicitados. O sistema solicita os vários graus de proficiência aplicáveis a esta CT. O administrativo introduz os dados de cada grau. O sistema valida e apresenta os dados ao administrativo, pedindo que os confirme. O administrativo confirma
os dados. O sistema regista os dados e informa o administrativo do sucesso da operação.

## Formato Completo

### Actor Principal

* Administrativo

### Partes interessadas e seus interesses

* Administrativo: Pretende que a competência técnica fique registada na plataforma
* Plataforma: Pretende ter mais competências técnicas registadas na plataforma
* Freelancer: Pretende ter competências técnicas inseridas na plataforma


### Pré-condições

1. A plataforma tem que ter pelo menos um Administrativo registado.
2. É necessário haver pelo menos uma área de atividade inserida na plataforma.

### Pós-condições

* A competência técnica é definida com sucesso, que fica disponível na plataforma.

### Cenário de sucesso principal

1. O administrativo inicia o processo de definição de uma competência técnica.
2.	O sistema solicita os dados.
3.	O Administrativo introduz os dados.
4.	O sistema solicita a confirmação dos dados.
5.	O Administrativo confirma os dados.
6.	O sistema devolve mensagem a confirmar o sucesso da operação.


### Fluxos alternativos

1. O Administrativo cancela o processo de definir uma competência técnica.
*  a. O caso de uso termina.
2. O Administrativo verifica que não existem mais competências técnicas para definir.
*  a. O caso de uso termina.
3. O Administrativo introduz dados inválidos:
*  a. O sistema permite a correção dos dados introduzidos (Passo 3).
*  b. O Administrativo não altera os dados.
*  c. O caso de uso termina.
4. O sistema deteta que os dados (ou algum subconjunto dos dados) introduzidos devem ser únicos e que já existem no sistema:
*  a. O sistema alerta o gestor para o facto.
*  b. O sistema permite a sua alteração (passo 3).
*  c. O Administrativo não altera os dados. O caso de uso termina.

### SSD
![UC04_Especificar_Competencia_Tecnica](https://github.com/blestonbandeiraUPSKILL/upskill_java1_labprg_grupo2/blob/main/Sprint%202/UC04_Especificar_Competencia_Tecnica/UC04_Especificar_Competencia_Tecnica.png)

### Excerto do Modelo de Domínio
![UC04_ExcertoMD_Especificar_Competencia_Tecnica](https://github.com/blestonbandeiraUPSKILL/upskill_java1_labprg_grupo2/blob/main/Sprint%202/UC04_Especificar_Competencia_Tecnica/UC04_ExcertoMD_Especificar_Competencia_Tecnica.png)

### Diagrama de Sequência
![UC04_DiagSeq_Especificar_Competencia_Tecnica](https://github.com/blestonbandeiraUPSKILL/upskill_java1_labprg_grupo2/blob/main/Sprint%202/UC04_Especificar_Competencia_Tecnica/UC04_DiagSeq_Especificar_Competencia_Tecnica.png)

### Diagrama de Classes
![UC04_DiagClas_Especificar_Competencia_Tecnica](https://github.com/blestonbandeiraUPSKILL/upskill_java1_labprg_grupo2/blob/main/Sprint%202/UC04_Especificar_Competencia_Tecnica/UC04_DiagClas_Especificar_Competencia_Tecnica.png)

##### [Voltar ao início](https://github.com/blestonbandeiraUPSKILL/upskill_java1_labprg_grupo2/tree/main/README.md)