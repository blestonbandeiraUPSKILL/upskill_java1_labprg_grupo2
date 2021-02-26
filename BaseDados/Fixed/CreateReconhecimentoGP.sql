CREATE OR REPLACE PROCEDURE createReconhecimentoGP(
    p_idGrauProficiencia reconhecimentoGP.idGrauProficiencia%type,
    p_email reconhecimentoGP.emailFreelancer%type,
    p_dataReconhecimento reconhecimentoGP.dataReconhecimento%type
    )

IS


BEGIN

    
    INSERT INTO ReconhecimentoGP
        (idGrauProficiencia, emailFreelancer, dataReconhecimento)
    VALUES
        (p_idGrauProficiencia, p_email, p_dataReconhecimento);
        
    
END;
/