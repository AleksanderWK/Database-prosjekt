select *
from verk;

select *
from ((film natural join verksjanger) natural join selskapverk)
; 

select selskapID, sjangerID, count(sjangerID) as antallfilmerisjanger
from ((film natural join verksjanger) natural join selskapverk)
group by selskapID, sjangerID;

select sjangerID, selskapID, max(SelskapFilmerISjanger.antall) as "Max Antall filmer i sjanger"
from (select selskapID, sjangerID, count(sjangerID) as antall
		from ((film natural join verksjanger) natural join selskapverk)
		group by selskapID, sjangerID) as SelskapFilmerISjanger
group by sjangerID;
 
select *
from person;

select *
from skuespilleriverk;

select distinct rolle
from skuespilleriverk as spiv
where spiv.personID = 1;

select *
from film natural join verk;

select *
from episode natural join verk;

select *
from media;

select *
from ansattiverk natural join person
where ansattType = "Regissoer" or ansattType = "Regissør";