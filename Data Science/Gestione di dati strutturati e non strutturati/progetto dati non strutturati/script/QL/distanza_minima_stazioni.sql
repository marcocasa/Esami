CREATE OR REPLACE FUNCTION public.distanza_minima_stazioni()
    RETURNS TABLE(id_staz1 integer, nome_stazione1 character varying, geom_stazione1 geometry, tipo_zona_stazione1 character, tipo_stazione1 character, id_staz2 integer, nome_stazione2 character varying, geom_stazione2 geometry, tipo_zona_stazione2 character, tipo_stazione2 character, distanza double precision) 
    LANGUAGE 'plpgsql'

AS $BODY$
BEGIN
	RETURN query
	SELECT 	s1.id_staz, s1.nome, s1.geom, s1.tipo_zona, s1.tipo_staz, 
		s2.id_staz, s2.nome, s2.geom, s2.tipo_zona, s2.tipo_staz,
		ST_DISTANCE(geography(s1.geom), geography(s2.geom))
	FROM stazioni s1 CROSS JOIN stazioni s2
	WHERE s1.id_staz <> s2.id_staz
	ORDER BY ST_DISTANCE(s1.geom, s2.geom) LIMIT 1; 
	
END
$BODY$;

SELECT * FROM distanza_minima_stazioni()