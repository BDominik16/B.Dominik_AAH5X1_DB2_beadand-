CREATE or replace PROCEDURE SelectAUTO IS
tipus2 number(10);
begin
SELECT count(auto.t�pus) into tipus2 FROM auto WHERE t�pus LIKE 'B%';
dbms_output.put_line('B-vel kezd�d� aut�k: ' ||tipus2);
end; 


begin
SelectAUTO;
end;

set SERVEROUTPUT ON