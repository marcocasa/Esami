CREATE OR REPLACE FUNCTION public.num_stazioni_superamento_soglia_giornaliera_provincia(provincia varchar, indicatore varchar, anno integer)
RETURNS TABLE(numero_centraline_superamento_soglia bigint) 
LANGUAGE 'plpgsql'
AS 
$BODY$
BEGIN
	RETURN query
	SELECT count(*)
	FROM province p, stazioni s JOIN rilevazioni r ON s.id_staz = r.id_stazione JOIN indicatori i ON r.nome_indicatore = i.nome
	WHERE p.nome = $1 AND r.nome_indicatore = $2 AND ST_Contains(p.geom, s.geom) AND r.anno = $3 AND r.giorni_superamento > i.soglia_giorni_superamento
	GROUP BY id_prov;
	END
$BODY$;

SELECT * 
FROM num_stazioni_superamento_soglia_giornaliera_provincia('Bari', 'Particolato PM10', 2020);