CREATE OR REPLACE FUNCTION public.stazione_vicina_indicatore_comune(nome_comune character varying, nome_indicatore character varying)
RETURNS TABLE(id_staz integer, nome character varying, geom geometry) 
LANGUAGE 'plpgsql'
AS 
$BODY$
BEGIN
	RETURN query
	SELECT 	s.id_staz, s.nome, s.geom
	FROM comuni c, stazioni s
	JOIN rileva r ON (s.id_staz = r.id_stazione)
	WHERE r.id_indicatore = $2 AND c.nome = $1
	ORDER BY ST_DISTANCE(c.geom, s.geom) LIMIT 1;
END
$BODY$;

SELECT * 
FROM stazione_vicina_indicatore_comune('Castellaneta', 'Particolato PM10');


