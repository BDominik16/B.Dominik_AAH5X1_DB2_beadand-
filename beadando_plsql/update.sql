CREATE OR REPLACE PROCEDURE updates (ujszin char,fajta char,preferaltmodositasok in out number) is

begin
update auto set AUTO.SZ�N=ujszin where auto.T�PUS=fajta;
if sql%rowcount=preferaltmodositasok then
dbms_output.put_line('j�');
else
preferaltmodositasok:=sql%rowcount;
end if;
end;



