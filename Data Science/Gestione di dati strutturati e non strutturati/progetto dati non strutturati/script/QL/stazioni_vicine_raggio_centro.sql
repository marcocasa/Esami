CREATE OR REPLACE FUNCTION public.stazioni_vicine_raggio_centro(centro geometry, raggio float)
    RETURNS TABLE(id_staz integer,
	nome_stazione character varying,
	geom_stazione geometry,
	tipo_zona_stazione character,
	tipo_stazione character,
	indicatore character varying,
	nome_comune character varying
	)
    LANGUAGE 'plpgsql'  

AS $BODY$
DECLARE area geometry;
BEGIN
	area := ST_SetSRID(ST_Buffer(geography($1), $2), 4326);
	RETURN query
	SELECT 	s.id_staz, s.nome, s.geom, s.tipo_zona, s.tipo_staz, r.id_indicatore, c.nome
	FROM stazioni s JOIN comuni c ON s.id_comune = c.id_comune 
	JOIN rileva r ON s.id_staz = r.id_stazione
	WHERE ST_Contains(area, s.geom);
	
END
$BODY$;


SELECT * FROM stazioni_vicine_raggio_centro(ST_MakePoint(15.451351, 41.546806), 80000)

