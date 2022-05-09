CREATE OR REPLACE PROCEDURE updates (ujszin char,fajta char,preferaltmodositasok in out number) is

begin
update auto set AUTO.SZÍN=ujszin where auto.TÍPUS=fajta;
if sql%rowcount=preferaltmodositasok then
dbms_output.put_line('jó');
else
preferaltmodositasok:=sql%rowcount;
end if;
end;



