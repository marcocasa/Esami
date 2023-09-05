CREATE OR REPLACE FUNCTION public.stazioni_vicine_comune_distanza(nome_comune character varying, distanza float)
RETURNS TABLE(id_staz integer, nome character varying, geom geometry, comune character varying, indicatore character varying) 
LANGUAGE 'plpgsql'
AS 
$BODY$
BEGIN
	RETURN query
	SELECT 	s.id_staz, s.nome, s.geom, c.nome, r.id_indicatore
	FROM comuni c JOIN stazioni s on (c.id_comune = s.id_comune) JOIN rileva r ON (s.id_staz = r.id_stazione),
		(SELECT c.geom
		FROM comuni c
		WHERE c.nome = $1) as geom_comune
	WHERE ST_DWithin(geography(geom_comune.geom), geography(s.geom), $2);
END
$BODY$;


SELECT * 
FROM stazioni_vicine_comune_distanza('Castellaneta', 100000);





