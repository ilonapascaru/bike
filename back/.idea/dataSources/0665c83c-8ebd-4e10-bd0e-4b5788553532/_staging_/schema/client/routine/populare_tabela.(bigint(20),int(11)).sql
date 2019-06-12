CREATE PROCEDURE populare_tabela()
  begin
    declare id bigint(20);
    declare cc int(6);
    declare  baza_de_date varchar(40);
    declare r1 int;
    declare r2 int;

set r1=round(random()*10);
set r2=round(random()*10);

set id=round(random()*999999999999);
set cc=round(random()*999999);
select concat("utilizator_",round(rand()*100)) into baza_de_date;

    insert into cod_client(id,cc,baza_de_date) values (id,cc,baza_de_date);


  end;
