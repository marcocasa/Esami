CREATE OR REPLACE FUNCTION public.malattia_province_media_superata(anno integer)
    RETURNS TABLE(
 	nome_malattia varchar, 
	provincia varchar,
	morti integer, 
	media_annuale double precision, 
	stazione varchar
	)

LANGUAGE 'plpgsql'  

AS $BODY$
BEGIN
	RETURN query
	SELECT DISTINCT d.nome_malattia, p.nome,(d.numero_decessi_maschi+d.numero_decessi_femmine), r.media_annuale, s.nome
	FROM decessi d, 
		stazioni s JOIN rilevazioni r ON s.id_staz = r.id_stazione JOIN 
		indicatori i ON r.nome_indicatore = i.nome, 
		(SELECT d.id_provincia, max(d.numero_decessi_maschi+d.numero_decessi_femmine) as morti
			FROM decessi d
			GROUP BY d.id_provincia) as g JOIN 
		province p ON g.id_provincia = p.id_prov 
	WHERE d.id_provincia = g.id_provincia AND
	 	(d.numero_decessi_maschi+d.numero_decessi_femmine) = g.morti AND
	 	ST_Contains(p.geom, s.geom) AND
	 	r.media_annuale > i.soglia_media_annuale AND
		r.anno = $1;
END
$BODY$;


SELECT * FROM malattia_province_media_superata(2020)

									


