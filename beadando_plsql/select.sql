Create or replace function GetAuto(mezonev in char,kulcs in number) 
return varchar2 as 
x varchar2(100);
BEGIN
select count(*) into x from AUTO where id=kulcs; 
if sql%rowcount!=0 then 
if upper(mezonev) = 'SZÍN' THEN 
select szín into x from AUTO where id=kulcs; 
elsif upper(mezonev) = 'ÉVJÁRAT' THEN
select évjárat into x from AUTO where id=kulcs; 
elsif upper(mezonev) = 'TÍPUS' THEN 
select típus into x from AUTO where id=kulcs; 
elsif upper(mezonev) = 'RENDSZÁM' THEN 
select rendszám into x from AUTO where id=kulcs; 
else x:= 'Rossz mezõ!'; 
end if; 
else x:= 'Rossz kulcs!'; 
end if; 
return x; 
end;