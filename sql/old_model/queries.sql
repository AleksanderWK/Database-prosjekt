select *
from verk;

select *
from skuespilleriverk;

select distinct rolle
from skuespilleriverk as spiv
where spiv.skuespillerID = 1;

select *
from film natural join verk;

select *
from episode natural join verk;

select *
from media;

select *
from produksjon natural join verk;

select *
from regissoer natural join person;