Create or replace function GetAuto(mezonev in char,kulcs in number) 
return varchar2 as 
x varchar2(100);
BEGIN
select count(*) into x from AUTO where id=kulcs; 
if sql%rowcount!=0 then 
if upper(mezonev) = 'SZ�N' THEN 
select sz�n into x from AUTO where id=kulcs; 
elsif upper(mezonev) = '�VJ�RAT' THEN
select �vj�rat into x from AUTO where id=kulcs; 
elsif upper(mezonev) = 'T�PUS' THEN 
select t�pus into x from AUTO where id=kulcs; 
elsif upper(mezonev) = 'RENDSZ�M' THEN 
select rendsz�m into x from AUTO where id=kulcs; 
else x:= 'Rossz mez�!'; 
end if; 
else x:= 'Rossz kulcs!'; 
end if; 
return x; 
end;