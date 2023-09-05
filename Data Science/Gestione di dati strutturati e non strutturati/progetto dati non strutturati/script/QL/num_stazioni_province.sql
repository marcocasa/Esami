CREATE OR REPLACE FUNCTION public.num_stazioni_province(indicatore varchar)
    RETURNS TABLE(
 	id_provincia double precision,
	provincia varchar,
	numero bigint)

LANGUAGE 'plpgsql'  

AS $BODY$
BEGIN
	RETURN query
	SELECT p.id_prov, p.nome, count(*) as num
	FROM province p, stazioni s JOIN rileva r ON s.id_staz = r.id_stazione
	WHERE r.id_indicatore = $1 AND ST_Contains(p.geom, s.geom)
	GROUP BY id_prov
	ORDER BY num DESC;

	
END
$BODY$;


SELECT * FROM num_stazioni_province('Particolato PM10')

									


