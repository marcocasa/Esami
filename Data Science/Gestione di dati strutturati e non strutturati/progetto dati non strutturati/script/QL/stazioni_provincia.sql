CREATE OR REPLACE FUNCTION public.stazioni_provincia(nome_provincia character varying)
RETURNS TABLE(id_staz integer, nome character varying, geom geometry, indicatore character varying) 
LANGUAGE 'plpgsql'
AS 
$BODY$
BEGIN
	RETURN query
	SELECT 	s.id_staz, s.nome, s.geom, r.id_indicatore
	FROM province p, stazioni s
	JOIN rileva r ON (s.id_staz = r.id_stazione)
	WHERE p.nome = $1 AND ST_Contains(p.geom, s.geom);
END
$BODY$;

SELECT * 
FROM stazioni_provincia('Taranto');


