select *
from verk;

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