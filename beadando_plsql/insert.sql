Create or replace procedure InsertAuto (szin in char,evjarat in number,tipus in char,rendszam in char, id in number) is 

begin 
insert into AUTO(SZÍN,ÉVJÁRAT,TÍPUS,RENDSZÁM,ID) values(szin,evjarat,tipus,rendszam,id); 
 

end;