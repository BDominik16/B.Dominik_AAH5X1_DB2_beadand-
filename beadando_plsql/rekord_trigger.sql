create or replace trigger auto_1 after delete on auto
begin
insert into naplo values('Rekord v�ltoztat�s.', sysdate, user);
end;