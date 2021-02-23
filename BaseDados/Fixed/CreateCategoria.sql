CREATE OR REPLACE PROCEDURE createCategoria(
    p_codigoCategoria categoria.codigoCategoria%type,
    p_descBreve categoria.descbreve%type,
    p_descDetalhada categoria.descdetalhada%type,
    p_codigoAreaActividade areaactividade.codigoareaactividade%type,
    p_obrigatoria caracterCT.obrigatoria%type,
    p_grauProfMinimo caracterCT.grauProfMinimo%type
    )
    
IS
    
    
BEGIN

    INSERT INTO Categoria
        (codigoCategoria, descbreve, descdetalhada, codigoareaactividade)
    VALUES
        (p_codigoCategoria, p_descbreve, p_descdetalhada, p_codigoareaactividade);
   
        
    createCaracterCT(p_obrigatoria, p_grauProfMinimo, p_codigoCategoria);
        
END;
/