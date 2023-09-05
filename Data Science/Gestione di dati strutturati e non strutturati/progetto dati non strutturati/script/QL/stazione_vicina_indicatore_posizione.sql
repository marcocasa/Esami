CREATE OR REPLACE FUNCTION public.stazione_vicina_indicatore_posizione(posizione geometry, nome_indicatore character varying)
RETURNS TABLE(id_staz integer, nome character varying, geom geometry) 
LANGUAGE 'plpgsql'
AS 
$BODY$
BEGIN
	RETURN query
	SELECT 	s.id_staz, s.nome, s.geom
	FROM stazioni s
	JOIN rilevazioni r ON (s.id_staz = r.id_stazione)
	JOIN indicatori i ON (i.nome = r.nome_indicatore)
	WHERE i.nome = $2
	ORDER BY ST_DISTANCE($1, s.geom) LIMIT 1;
END
$BODY$;

SELECT * 
FROM stazione_vicina_indicatore_posizione(ST_SetSRID(ST_MakePoint(15.451351, 41.546802), 4326), 'Particolato PM10');