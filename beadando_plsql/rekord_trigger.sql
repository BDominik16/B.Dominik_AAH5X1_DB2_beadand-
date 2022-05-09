create or replace trigger auto_1 after delete on auto
begin
insert into naplo values('Rekord változtatás.', sysdate, user);
end;