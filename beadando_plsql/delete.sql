create or replace procedure DeleteFromAUTO(evjaratBE in number) as 
begin
delete from AUTO where évjárat = evjaratbe; 

end;
